package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/9.
 */
public class Proxy {
    interface Subject{
        void request();
    }

    static class RealSubject implements Subject{

        @Override
        public void request() {
            System.out.println("This is real subject.");
        }
    }

    static class StaticProxy implements Subject{

        private RealSubject subject;

        public StaticProxy() {
            this.subject = new RealSubject();
        }

        @Override
        public void request() {
            preRequest();
            subject.request();
            postRequest();
        }

        public void preRequest(){
            System.out.println("Before request.");
        }

        public void postRequest(){
            System.out.println("After request.");
        }
    }

    public static void main(String[] args) {
        Subject subject=new StaticProxy();
        subject.request();
    }
}
