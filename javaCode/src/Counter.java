package javaCode.src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2020-08-12-10:55
 */
public class Counter {
    private AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<> (0, 0);
    //    private AtomicInteger atomicInteger  = new AtomicInteger (0);
    private int i;

    public static void main(String args[]) {
        final Counter counter = new Counter ();
        List<Thread> list = new ArrayList<> (10000);
        long start = System.currentTimeMillis ();
        for (int j = 0; j < 1000000; j++) {
            Thread t = new Thread (() -> {
                for (int i = 0; i < 10000; i++) {
                    counter.safeCount ();
                    counter.count ();
                }
            });
            list.add (t);
        }
        list.forEach (Thread::start);
        list.forEach (v -> {
            try {
                v.join ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        });
        System.out.println (counter.i);
        System.out.println (counter.atomicInteger.getReference ());
        System.out.println (System.currentTimeMillis () - start);
    }

    private void safeCount() {
        for (; ; ) {
            int stamp = atomicInteger.getStamp ();
            Integer i = atomicInteger.getReference ();
//            int i  = atomicInteger.get ();
            boolean b = atomicInteger.compareAndSet (i, ++i, stamp, ++stamp);
            if (b) {
                break;
            } else {
                System.out.println (i + "失败" +stamp+"======>"+ Thread.currentThread ().getName ());
            }
        }
    }

    private void count() {
        i++;
    }

}
