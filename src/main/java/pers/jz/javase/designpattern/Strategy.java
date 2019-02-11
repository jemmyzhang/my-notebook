package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/11.
 */
public class Strategy {
    interface AbstractStrategy{
        void strategyMethod();
    }

    static class ConcreteStrategy1 implements AbstractStrategy{

        @Override
        public void strategyMethod() {
            System.out.println("Strategy Method 1.");
        }
    }

    static class ConcreteStrategy2 implements AbstractStrategy{

        @Override
        public void strategyMethod() {
            System.out.println("Strategy Method 2.");
        }
    }

    static class Context{
        AbstractStrategy strategy;

        public AbstractStrategy getStrategy() {
            return strategy;
        }

        public void setStrategy(AbstractStrategy strategy) {
            this.strategy = strategy;
        }

        public void contextMethod(){
            method1();
            strategy.strategyMethod();
            method2();
        }

        private void method1() {
            System.out.println("method1");
        }

        private void method2() {
            System.out.println("method2");
        }
    }

    public static void main(String[] args) {
        Context context=new Context();
        context.setStrategy(new ConcreteStrategy1());
        context.contextMethod();
    }

}
