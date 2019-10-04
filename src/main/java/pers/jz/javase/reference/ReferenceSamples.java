package pers.jz.javase.reference;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Jemmy Zhang on 2019/9/30.
 */
public class ReferenceSamples {

    @Test
    public void weakReferenceTest() {
        WeakReference<Integer> weakReference = new WeakReference<>(new Integer(100));
        System.gc();
        System.out.println(weakReference.get());
    }

    @Test
    public void weakReferenceWithQueue() {
        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();
        WeakReference<Integer> weakReference = new WeakReference<>(new Integer(100), referenceQueue);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.gc();
        System.out.println(referenceQueue.poll());
    }
}
