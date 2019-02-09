package pers.jz.javase.designpattern;

/**
 * 桥接模式
 *
 * @author Jemmy Zhang on 2019/2/9.
 */
public class Bridge {

    interface Color {
        String getColor();
    }

    static class Blue implements Color {

        @Override
        public String getColor() {
            return "Blue";
        }
    }

    static class Red implements Color {

        @Override
        public String getColor() {
            return "Red";
        }
    }

    static class Yellow implements Color {

        YellowPrinter yellowPrinter;

        public Yellow(YellowPrinter yellowPrinter) {
            this.yellowPrinter = yellowPrinter;
        }

        @Override
        public String getColor() {
            return yellowPrinter.yellowPrinter();
        }
    }

    static class YellowPrinter {
        String yellowPrinter() {
            return "Yellow";
        }
    }

    static abstract class AbstractBridge {
        Color color;

        public AbstractBridge(Color color) {
            this.color = color;
        }

        public abstract String getShape();

        public void printBridge() {
            System.out.println("Color is: " + color.getColor() + ", Shape is: " + getShape());
        }
    }

    static class ArchBridge extends AbstractBridge {

        public ArchBridge(Color color) {
            super(color);
        }

        @Override
        public String getShape() {
            return "Arch";
        }
    }

    static class CableStayedBridge extends AbstractBridge {

        public CableStayedBridge(Color color) {
            super(color);
        }

        @Override
        public String getShape() {
            return "CableStayed";
        }
    }

    public static void main(String[] args) {
        Color blue = new Blue();
        AbstractBridge bridge1 = new ArchBridge(blue);
        bridge1.printBridge();
        Color yellow = new Yellow(new YellowPrinter());
        AbstractBridge bridge2 = new ArchBridge(yellow);
        bridge2.printBridge();
    }
}
