package pers.jz.javase.designpattern;

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
    }
}
