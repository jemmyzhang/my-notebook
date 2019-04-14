package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/13.
 */
public class Visitor {
    interface AbstractVisitor {
        void visit(ConcreteElementA element);

        void visit(ConcreteElementB element);
    }

    interface Element {
        void accept(Visitor visitor);
    }

    static class ConcreteVisitorA implements AbstractVisitor {

        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者A访问-->" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者A访问-->" + element.operationB());
        }
    }

    static class ConcreteVisitorB implements AbstractVisitor{

        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者B访问-->" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者B访问-->" + element.operationB());
        }
    }

    static class ConcreteElementA implements Element {

        @Override
        public void accept(Visitor visitor) {

        }

        public String operationA() {
            return "This is operationA";
        }
    }

    static class ConcreteElementB implements Element {

        @Override
        public void accept(Visitor visitor) {

        }

        public String operationB() {
            return "This is operationB";
        }
    }
}
