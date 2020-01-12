package pers.jz.netty.example.restaurant.common;

/**
 * 操作抽象类
 *
 * @author Jemmy Zhang on 2020/1/1.
 */
public abstract class Operation extends MessageBody {

    public abstract OperationResult execute();
}
