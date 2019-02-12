package pers.jz.javase.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jemmy Zhang on 2019/2/12.
 */
public class Command {

    interface AbstractCommand {
        void action();
    }

    static class ConcreteCommand implements AbstractCommand {

        private Receiver receiver = new Receiver();

        @Override
        public void action() {
            System.out.println("Invoke concrete command action.");
            receiver.doSomething();
        }
    }


    static class ConcreteCommand2 implements AbstractCommand {

        private Receiver receiver = new Receiver();

        @Override
        public void action() {
            System.out.println("Invoke concrete command action2.");
            receiver.doSomething();
        }
    }

    static class Receiver {
        void doSomething() {
            System.out.println("Receiver is invoked.");
        }
    }

    static class Invoker {

        private AbstractCommand command;

        public Invoker(AbstractCommand command) {
            this.command = command;
        }

        void call() {
            command.action();
        }
    }

    static class CompositeInvoker {
        private List<AbstractCommand> commands = new ArrayList<>();

        void addCommand(AbstractCommand command) {
            commands.add(command);
        }

        void removeCommand(AbstractCommand command) {
            commands.remove(command);
        }

        void execute() {
            commands.stream().forEach((var) -> var.action());
        }
    }

    public static void main(String[] args) {
        Invoker invoker = new Invoker(new ConcreteCommand());
        invoker.call();
        CompositeInvoker compositeInvoker=new CompositeInvoker();
        compositeInvoker.addCommand(new ConcreteCommand());
        compositeInvoker.addCommand(new ConcreteCommand2());
        compositeInvoker.execute();
    }
}
