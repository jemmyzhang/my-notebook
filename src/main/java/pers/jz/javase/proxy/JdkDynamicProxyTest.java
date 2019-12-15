package pers.jz.javase.proxy;

import com.sun.deploy.net.proxy.ProxyHandler;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author Jemmy Zhang on 2019/12/15.
 */
public class JdkDynamicProxyTest {

    @Test
    public void method1(){
        Job jobImpl = new JobImpl();
        InvocationHandler handler = new JobInvocationHandler<>(jobImpl);
        Job jobProxy = (Job) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[]{Job.class}, handler);
        System.out.println(jobProxy.handle(new Date()));
    }

    @Test
    public void method2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Job jobImpl = new JobImpl();
        InvocationHandler handler = new JobInvocationHandler<>(jobImpl);
        Class<?> proxyClass = Proxy.getProxyClass(getClass().getClassLoader(), new Class<?>[]{Job.class});
        Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
        Job jobProxy = (Job) proxyClassConstructor.newInstance(handler);
        int result = jobProxy.handle(new Date());
        System.out.println(result);

    }
}
