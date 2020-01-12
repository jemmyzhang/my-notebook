package pers.jz.netty.example.restaurant.common;

/**
 * 响应消息抽象类
 *
 * @author Jemmy Zhang on 2020/1/2.
 */
public class CommonResponseMessage extends Message<OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationResultClazz();
    }
}
