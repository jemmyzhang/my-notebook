package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/10.
 */
public class Decorator {
    interface Component {
        void operation();
    }

    static class ConcreteComponent implements Component {

        @Override
        public void operation() {
            System.out.println("this is target.");
        }
    }

    static abstract class AbstractDecorator implements Component {
        Component component;

        public AbstractDecorator(Component component) {
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }

    static class ConcreteDecorator extends AbstractDecorator{

        public ConcreteDecorator(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            addAdditionFunction();
        }
        public void addAdditionFunction(){
            System.out.println("Add addition function. ");
        }
    }

    public static void main(String[] args) {
        Component component=new ConcreteComponent();
        Component decorator=new ConcreteDecorator(component);
        decorator.operation();
    }
}
