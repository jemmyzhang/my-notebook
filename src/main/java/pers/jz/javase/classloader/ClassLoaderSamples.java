package pers.jz.javase.classloader;

/**
 * @author Jemmy Zhang on 2019/2/5.
 */
public class ClassLoaderSamples {

    static class Tester{
        static {
            System.out.println("Init Tester static block.");
        }
        public Tester(){
            System.out.println("Init Tester constructor.");
        }
    }

    /**
     * 使用ClassLoader静态方法。
     */
    public void showClassLoader() {
        //获取默认的系统类加载器
        ClassLoader currentClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System ClassLoader: " + currentClassLoader);
        System.out.println("System ClassLoader's parent:" + currentClassLoader.getParent());
    }

    public void loadClass() throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = systemClassLoader.loadClass(Tester.class.getName());
        System.out.println("Current classloader: " + clazz.getClassLoader());

        //默认类初始化的参数为true
        Class<?> clazzForName = Class.forName(Tester.class.getName());
        System.out.println("For name classloader: " + clazzForName.getClassLoader());

        Class<?> clazzForName2 = Class.forName(Tester.class.getName(), false, systemClassLoader);
        System.out.println("For name2 classloader: " + clazzForName.getClassLoader());

    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaderSamples classLoaderTest = new ClassLoaderSamples();
        classLoaderTest.showClassLoader();
        classLoaderTest.loadClass();
    }
}
