package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/9.
 */
public class TemplateMethod {
    static abstract class Template{
        public void templateMethod(){
            if(hookMethod()){
                specificMethod();
            }
            abstractMethod1();
            abstractMethod2();
            abstractMethod3();
        }

        public void specificMethod(){
            System.out.println("抽象类中的具体方法被调用...");
        }
        protected abstract boolean hookMethod();

        public abstract void abstractMethod1();

        public abstract void abstractMethod2();

        public abstract void abstractMethod3();

    }

    static class ConcreteTemplate extends Template{

        @Override
        protected boolean hookMethod() {
            return true;
        }

        @Override
        public void abstractMethod1() {
            System.out.println("This is abstract method 1");
        }

        @Override
        public void abstractMethod2() {
            System.out.println("This is abstract method 2");

        }

        @Override
        public void abstractMethod3() {
            System.out.println("This is abstract method 3");
        }
    }

    public static void main(String[] args) {
        Template template=new ConcreteTemplate();
        template.templateMethod();
    }
}
