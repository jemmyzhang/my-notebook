package pers.jz.javase.concurrency;

import java.lang.reflect.Field;

/**
 * @author Jemmy Zhang
 */
public class CompareAndSwapTest {

    volatile Node tail;
    volatile int number = 0;

    public void compareAndSwapInt() {
        int a = number;
        int b = 10;
        UNSAFE.compareAndSwapInt(this, numberOffset, a, b);
        System.out.println(number);
    }

    public void compareAndSwapObject() {
        tail = new Node();
        tail.setValue(123);
        Node t = tail;
        Node other = new Node();
        other.setValue(456);
        UNSAFE.compareAndSwapObject(this, tailOffset, t, other);
        System.out.println(tail.getValue());
    }

    public static void main(String[] args) {
        CompareAndSwapTest tester = new CompareAndSwapTest();
        tester.compareAndSwapObject();
        tester.compareAndSwapInt();
    }

    public class Node {
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    private static final sun.misc.Unsafe UNSAFE;
    private static final long tailOffset;
    private static final long numberOffset;

    static {
        try {
            Object unsafeInstance = null;
            try {
                Class unsafeClass = Class.forName("sun.misc.Unsafe");
                Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
                boolean originalAccessible = theUnsafeField.isAccessible();
                theUnsafeField.setAccessible(true);
                unsafeInstance = theUnsafeField.get(null);
                theUnsafeField.setAccessible(originalAccessible);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            UNSAFE = (sun.misc.Unsafe) unsafeInstance;
            Class<?> k = CompareAndSwapTest.class;
            //内存中要读写的位置
            tailOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("tail"));
            numberOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("number"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
