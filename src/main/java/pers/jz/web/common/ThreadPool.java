package pers.jz.web.common;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Jemmy Zhang
 */
public class ThreadPool {

    private static class CPUBusy {
        private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
        private static final int MAX_CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;
        private static final int MAX_POOL_SIZE = 200;
        private static final long KEEP_ALIVE_TIME = 60L;
        public static final ThreadPoolExecutor INSTANCE = new ThreadPoolExecutor(CORE_SIZE, MAX_CORE_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(MAX_POOL_SIZE));
    }

    private static class IOBusy {
        private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 5;
        private static final int MAX_CORE_SIZE = Runtime.getRuntime().availableProcessors() * 50;
        private static final int MAX_POOL_SIZE = 200;
        private static final long KEEP_ALIVE_TIME = 60L;
        public static final ThreadPoolExecutor INSTANCE = new ThreadPoolExecutor(CORE_SIZE, MAX_CORE_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(MAX_POOL_SIZE));
    }

    public static ThreadPoolExecutor cpuBusy() {
        return CPUBusy.INSTANCE;
    }

    public static ThreadPoolExecutor ioBusy() {
        return IOBusy.INSTANCE;
    }


    public static class ThreadPoolTest {

        @Test
        public void testCpuBusy() {
            for (int i = 0; i < 200; i++) {
                final int counter = i;
                ThreadPool.cpuBusy().execute(() -> System.out.println());
            }
        }

        @Test
        public void testIoBusy(){
            for (int i = 0; i < 200; i++) {
                final int counter = i;
                ThreadPool.cpuBusy().execute(() -> System.out.println(counter));
            }
        }
    }
}
