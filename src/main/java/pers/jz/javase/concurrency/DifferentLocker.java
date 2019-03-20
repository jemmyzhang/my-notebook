package pers.jz.javase.concurrency;

/**
 * @author Jemmy Zhang on 2019/3/20.
 */
public class DifferentLocker {

    static class LockerA {
    }

    public static final int DEFAULT_SLEEP_TIME = 2;

    private static void sleepSeconds(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class InnerClass {
        public static void testLockerA() {
            synchronized (LockerA.class) {
                System.out.println("This is the InnerClass testLockerA. Thread: " + Thread.currentThread().getName());
                DifferentLocker.sleepSeconds(DEFAULT_SLEEP_TIME);
            }
        }
    }

    public synchronized void testMethodLocker() {
        System.out.println("This is the testMethodLocker. Thread: " + Thread.currentThread().getName());
        DifferentLocker.sleepSeconds(DEFAULT_SLEEP_TIME);
    }

    public static synchronized void testStaticMethodLocker() {
        System.out.println("This is the testStaticMethodLocker. Thread: " + Thread.currentThread().getName());
        DifferentLocker.sleepSeconds(DEFAULT_SLEEP_TIME);
    }

    public void testLockerA() {
        synchronized (LockerA.class){
            System.out.println("This is the testLockerA. Thread: " + Thread.currentThread().getName());
            DifferentLocker.sleepSeconds(DEFAULT_SLEEP_TIME);
        }
    }

    public static void main(String[] args) {
        DifferentLocker tester = new DifferentLocker();
        Thread thread1 = new Thread(tester::testMethodLocker, "thread1");
        Thread thread2 = new Thread(DifferentLocker::testStaticMethodLocker, "thread2");
        Thread thread3 = new Thread(tester::testLockerA, "thread3");

        Thread thread4 = new Thread(tester::testMethodLocker, "thread4");
        Thread thread5 = new Thread(DifferentLocker::testStaticMethodLocker, "thread5");
        Thread thread6 = new Thread(tester::testLockerA, "thread6");

        Thread thread7 = new Thread(InnerClass::testLockerA, "thread7");

        thread1.start();
        thread2.start();
        thread3.start();
        thread7.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
