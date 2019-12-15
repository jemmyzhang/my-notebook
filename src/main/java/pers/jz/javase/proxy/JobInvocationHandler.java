package pers.jz.javase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Jemmy Zhang on 2019/12/15.
 */
public class JobInvocationHandler<T> implements InvocationHandler {

    T target;

    public JobInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("Invoke by dynamic proxy class = %s, method = %s, return type = %s",
                proxy.getClass().getName(), method.getName(), method.getReturnType().getName()));
        Object result = method.invoke(target, args);
        System.out.println("Method invoke finished.");
        return result;
    }
}
