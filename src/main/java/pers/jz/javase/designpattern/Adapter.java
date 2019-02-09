package pers.jz.javase.designpattern;

/**
 * 适配器模式
 *
 * @author Jemmy Zhang on 2019/2/9.
 */
public class Adapter {

    /**
     * 目标接口，可以被客户使用。
     */
    static interface Target {
        void request();
    }

    /**
     * 被适配者，Client可能出于某些原因无法直接调用。
     */
    static class Adaptee {
        void specificRequest() {
            System.out.println("Provide some service.");
        }
    }

    /**
     * 类适配器，直接被Client所使用。
     */
    static class ClassAdapter extends Adaptee implements Target {
        @Override
        public void request() {
            super.specificRequest();
        }
    }

    /**
     * 对象适配器，直接被Client所使用。
     */
    static class ObjectAdapter implements Target {

        Adaptee adaptee;

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.specificRequest();
        }
    }

    public static void testClassAdapter() {
        Target target = new ClassAdapter();
        target.request();
    }

    public static void testObjectAdaptor() {
        //被适配者可以以任何方式获取到，其目的是传入适配器进行适配。
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }


    interface TwoWayAdaptee {
        void specificRequest();
    }

    interface TwoWayTarget {
        void request();
    }

    static class TwoWayAdapteeImpl implements TwoWayAdaptee {

        @Override
        public void specificRequest() {
            System.out.println("适配者代码被调用。");
        }
    }

    static class TwoWayTargetImpl implements TwoWayTarget {

        @Override
        public void request() {
            System.out.println("目标代码被调用");
        }
    }

    static class TwoWayAdapter implements TwoWayTarget, TwoWayAdaptee {

        TwoWayAdaptee twoWayAdaptee;
        TwoWayTarget twoWayTarget;

        public TwoWayAdapter(TwoWayAdaptee twoWayAdaptee) {
            this.twoWayAdaptee = twoWayAdaptee;
        }

        public TwoWayAdapter(TwoWayTarget twoWayTarget) {
            this.twoWayTarget = twoWayTarget;
        }

        @Override
        public void specificRequest() {
            twoWayTarget.request();
        }

        @Override
        public void request() {
            twoWayAdaptee.specificRequest();
        }
    }

    public static void testTwoWayAdapter() {
        TwoWayAdaptee twoWayAdaptee = new TwoWayAdapteeImpl();
        TwoWayTarget twoWayTarget=new TwoWayAdapter(twoWayAdaptee);
        twoWayTarget.request();
        TwoWayTarget newTarget=new TwoWayTargetImpl();
        twoWayAdaptee=new TwoWayAdapter(newTarget);
        twoWayAdaptee.specificRequest();
    }


    public static void main(String[] args) {
        testClassAdapter();
        testObjectAdaptor();
        testTwoWayAdapter();
    }
}
