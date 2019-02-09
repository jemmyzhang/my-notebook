package pers.jz.javase.designpattern;

import sun.nio.ch.AbstractPollArrayWrapper;

/**
 * @author Jemmy Zhang on 2019/2/9.
 */
public class Builder {

    static class Product {
        private String partA;
        private String partB;
        private String partC;

        public String getPartA() {
            return partA;
        }

        public void setPartA(String partA) {
            this.partA = partA;
        }

        public String getPartB() {
            return partB;
        }

        public void setPartB(String partB) {
            this.partB = partB;
        }

        public String getPartC() {
            return partC;
        }

        public void setPartC(String partC) {
            this.partC = partC;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "partA='" + partA + '\'' +
                    ", partB='" + partB + '\'' +
                    ", partC='" + partC + '\'' +
                    '}';
        }

        public void show() {
            System.out.println(this.toString());
        }
    }

    static abstract class AbstractBuilder {
        protected Product product = new Product();
        public abstract void buildPartA();
        public abstract void buildPartB();
        public abstract void buildPartC();
        public Product build(){
            return product;
        }
    }

    static class ConcreteBuilder extends AbstractBuilder{

        @Override
        public void buildPartA() {
            product.setPartA("add partA");
        }

        @Override
        public void buildPartB() {
            product.setPartB("add partB");
        }

        @Override
        public void buildPartC() {
            product.setPartC("add partC");
        }
    }

    public static void main(String[] args) {
        AbstractBuilder builder=new ConcreteBuilder();
        builder.buildPartA();
        builder.buildPartB();
        Product build = builder.build();
        System.out.println(build);
    }
}
