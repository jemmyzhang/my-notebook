package pers.jz.javase.designpattern;

/**
 * 工厂方法模式
 *
 * @author Jemmy Zhang on 2019/2/8.
 */
public class FactoryMethod {

    interface AbstractFactory {
        Product createProduct();
    }

    interface Product {
        void show();
    }

    static class ConcreteFactory1 implements AbstractFactory {

        @Override
        public Product createProduct() {
            return new ConcreteProduct1();
        }
    }

    static class ConcreteFactory2 implements AbstractFactory {

        @Override
        public Product createProduct() {
            return new ConcreteProduct2();
        }
    }

    static class ConcreteProduct1 implements Product {

        @Override
        public void show() {
            System.out.println("This is concrete product1.");
        }
    }

    static class ConcreteProduct2 implements Product {

        @Override
        public void show() {
            System.out.println("This is concrete product2.");
        }
    }

    public static void factoryMethodClient() {
        //客户端知道自己需要的产品具体来自哪个工厂，并可以使用各种方式拿到这个工厂，包括XML，网络等。
        AbstractFactory factory = new ConcreteFactory1();
        Product product = factory.createProduct();
        product.show();
    }

    /**
     * 简单工厂，不符合开闭原则。简单工厂适合知道产品名称；而工厂方法比较适合只知道工厂名而不关心产品名。
     */
    static class SimpleFactory<T extends Product> {
        Product createProduct(Class<T> clazz) {
            requiresNonNull(clazz);
            if (ConcreteProduct1.class.getName().equals(clazz.getName())) {
                return new ConcreteProduct1();
            } else if (ConcreteProduct2.class.getName().equals(clazz.getName())) {
                return new ConcreteProduct2();
            } else {
                throw new IllegalArgumentException();
            }
        }

        private void requiresNonNull(Class<T> clazz) {
            if (null == clazz) {
                throw new NullPointerException();
            }
        }
    }

    public static void simpleFactoryClient() {
        SimpleFactory factory = new SimpleFactory();
        Product product = factory.createProduct(ConcreteProduct2.class);
        product.show();
    }

    public static void main(String[] args) {
        factoryMethodClient();
        simpleFactoryClient();
    }

}
