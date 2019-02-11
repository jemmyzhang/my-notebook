package pers.jz.javase.classloader;

/**
 * @author Jemmy Zhang on 2019/2/10.
 */
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
