package pers.jz.netty.example.restaurant.common;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class CommonRequestMessage extends Message<Operation> {

    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationClazz();
    }

    public CommonRequestMessage() {
    }

    public CommonRequestMessage(Long streamId, Operation operation) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setStreamId(streamId);
        messageHeader.setOpCode(OperationType.fromOperation(operation).getOpCode());
        this.setMessageHeader(messageHeader);
        this.setMessageBody(operation);
    }

}
