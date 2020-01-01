package pers.jz.netty.example.restaurant.common;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.Charset;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
public abstract class AbstractMessage<T extends AbstractMessageBody> {

    private MessageHeader messageHeader;
    private T messageBody;

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(new Gson().toJson(messageBody).getBytes());
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opcode);

    public void decode(ByteBuf message) {
        int version = message.readInt();
        long streamId = message.readLong();
        int opCode = message.readInt();

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamId(streamId);
        this.messageHeader = messageHeader;

        Class<T> bodyClazz = getMessageBodyDecodeClass(opCode);
        T body = new Gson().fromJson(message.toString(Charset.forName("UTF-8")), bodyClazz);
        this.messageBody = body;
    }
}
