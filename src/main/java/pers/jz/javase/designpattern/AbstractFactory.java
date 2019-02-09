package pers.jz.javase.designpattern;

/**
 * 抽象工厂模式
 *
 * @author Jemmy Zhang on 2019/2/8.
 */
public class AbstractFactory {

    interface MyAbstractFactory {
        Product1 newProduct1();

        Product2 newProduct2();
    }

    interface Product1 {
        void show();
    }

    interface Product2 {
        void display();
    }

    static class ConcreteProduct1 implements Product1 {

        @Override
        public void show() {
            System.out.println("This is product1.");
        }
    }

    static class ConcreteProduct2 implements Product2 {

        @Override
        public void display() {
            System.out.println("This is product2.");
        }
    }

    static class ConcreteMyAbstractFactory implements MyAbstractFactory {

        @Override
        public Product1 newProduct1() {
            return new ConcreteProduct1();
        }

        @Override
        public Product2 newProduct2() {
            return new ConcreteProduct2();
        }
    }

    public static void main(String[] args) {
        MyAbstractFactory factory = new ConcreteMyAbstractFactory();
        Product1 product1 = factory.newProduct1();
        Product2 product2 = factory.newProduct2();
        product1.show();
        product2.display();
    }
}
