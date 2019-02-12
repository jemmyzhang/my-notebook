package pers.jz.javase.designpattern;

import java.util.Objects;

/**
 * @author Jemmy Zhang on 2019/2/12.
 */
public class ResponseChain {
    static abstract class Handler {
        Handler next;

        public Handler getNext() {
            return next;
        }

        public void setNext(Handler next) {
            this.next = next;
        }

        public abstract void handleRequest(String request);
    }

    static class ConcreteHandler1 extends Handler {

        @Override
        public void handleRequest(String request) {
            if (Objects.nonNull(request)) {
                System.out.println("ConcreteHandler1: " + request);
            }
            if (Objects.nonNull(getNext())) {
                getNext().handleRequest(request);
            }

        }
    }

    static class ConcreteHandler2 extends Handler {

        @Override
        public void handleRequest(String request) {
            if (Objects.nonNull(request) && request.contains("Hello")) {
                System.out.println("ConcreteHandler2: " + request);
            }
            if (Objects.nonNull(getNext())) {
                getNext().handleRequest(request);
            }
        }
    }

    static class ConcreteHandler3 extends Handler {

        @Override
        public void handleRequest(String request) {
            if (Objects.equals(request, "Hello")) {
                System.out.println("ConcreteHandler3: " + request);
            }
            if (Objects.nonNull(getNext())) {
                getNext().handleRequest(request);
            }
        }
    }

    public static void main(String[] args) {
        Handler handler = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler.setNext(handler2);
        handler2.setNext(handler3);

        handler.handleRequest("HelloWorld");
    }
}

