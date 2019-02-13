package pers.jz.javase.designpattern;

/**
 * @author Jemmy Zhang on 2019/2/13.
 */
public class Memento {

    static class Originator {
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public ConcreteMemento createMemento() {
            return new ConcreteMemento(state);
        }

        public void restoreMemento(ConcreteMemento memento) {
            state = memento.getState();
        }
    }

    static class ConcreteMemento {
        private String state;

        public ConcreteMemento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    static class Caretaker{
        ConcreteMemento memento;

        public Caretaker(ConcreteMemento memento) {
            this.memento = memento;
        }

        public ConcreteMemento getMemento() {
            return memento;
        }

        public void setMemento(ConcreteMemento memento) {
            this.memento = memento;
        }
    }

    public static void main(String[] args) {
        Originator originator=new Originator();
        originator.setState("Good");
        System.out.println(originator.getState());
        Caretaker caretaker=new Caretaker( originator.createMemento());
        originator.setState("Normal");
        System.out.println(originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator.getState());
    }
}
