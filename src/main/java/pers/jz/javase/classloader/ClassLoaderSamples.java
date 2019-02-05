package pers.jz.javase.classloader;

/**
 * @author Jemmy Zhang on 2019/2/5.
 */
public class ClassLoaderSamples {

    /**
     * 使用ClassLoader静态方法。
     */
    public void showClassLoader() {
        ClassLoader currentClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System ClassLoader:" + currentClassLoader);
        System.out.println("System ClassLoader's parent:" + currentClassLoader.getParent());
    }

    public static void main(String[] args) {
        ClassLoaderSamples classLoaderTest = new ClassLoaderSamples();
        classLoaderTest.showClassLoader();
    }
}
