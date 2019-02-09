package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/10.
 */
public class Facade {
    static class SubSystem1{
        public void method1(){
            System.out.println("This is sub-system1");
        }
    }

    static class SubSystem2{
        public void method2(){
            System.out.println("This is sub-system2");
        }
    }

    static class SubSystem3{
        public void method3(){
            System.out.println("This is sub-system3");
        }
    }

    interface AbstractFacade{
        void method();
    }

    static class ConcreteFacade implements AbstractFacade{

        private SubSystem1 sys1=new SubSystem1();
        private SubSystem2 sys2=new SubSystem2();
        private SubSystem3 sys3=new SubSystem3();

        @Override
        public void method() {
            sys1.method1();
            sys2.method2();
            sys3.method3();
        }
    }

    public static void main(String[] args) {
        AbstractFacade facade=new ConcreteFacade();
        facade.method();
    }
}
