package MuThread;

import java.util.concurrent.CountDownLatch;

/**
 * https://www.cnblogs.com/lixin-link/p/10998058.html
 *
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2021-04-13-16:21
 */
public class MuThreadTest {

    public void muThread() {
        Object lock = new Object();
        Integer a = 1;
        Thread thr = new Thread(() -> {
            int num = 1000;
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < num; i++) {
                s.append("java");
            }
            System.out.println("t over");
            synchronized (a) {
                lock.notify();
            }
        });
        long start = System.currentTimeMillis();
        thr.start();
        try {
            synchronized (a) {
                a.wait();
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MuThreadTest muThreadTest = new MuThreadTest();
//        muThreadTest.muThread();
        muThreadTest.useCountDownLatch();
    }

    public void useCountDownLatch() {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            startAThread(countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("takes time: " + (System.currentTimeMillis() - startTime));
    }


    public void startAThread(CountDownLatch cdl) {
        Thread t = new Thread(() -> {
            int num = 1000;
            String s = "";
            for (int i = 0; i < num; i++) {
                s += "Java";
            }
            cdl.countDown();//此方法是CountDownLatch的线程数-1
            System.out.println("t Over" + cdl.getCount());
        });
        t.start();
    }

}
