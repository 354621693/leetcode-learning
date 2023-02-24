package MuThread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode.cn/problems/print-foobar-alternately/description/
 *
 * @author leewencan
 * @date 2023/1/28 16:27
 */
public class 交替打印FooBar1115 {
    int n;

    public 交替打印FooBar1115(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }

    /**
     * 方法1:Semaphore
     * Semaphore是一个计数信号量。
     * 从概念上将，Semaphore包含一组许可证。
     * 如果有需要的话，每个acquire()方法都会阻塞，直到获取一个可用的许可证。
     * 每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
     * 然而，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护
     * 总结：如果线程要访问一个资源就必须先获得信号量。如果信号量内部计数器大于0，信号量减1，然后允许共享这个资源；否则，如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于0.当信号量使用完时，必须释放
     */
    static class Foobar_Semaphore extends 交替打印FooBar1115 {
        Semaphore semaphoreFoo = new Semaphore(1);
        Semaphore semaphoreBar = new Semaphore(0);

        public Foobar_Semaphore(int n) {
            super(n);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                semaphoreFoo.acquire();
                printFoo.run();
                semaphoreBar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                semaphoreBar.acquire();
                printBar.run();
                semaphoreFoo.release();
            }
        }
    }

    /**
     * 循环栅栏，适合协调多个线程同步执行操作的场景，所有线程等待完成后一起做某件事情
     */
    public static class Foobar2_CyclicBarrier extends 交替打印FooBar1115 {
        private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);//表示有两个线程参与等待
        //因为要控制先后，所以要用一个标志位表示foo先打印
        volatile boolean fooExec = true;

        public Foobar2_CyclicBarrier(int n) {
            super(n);
        }


        public void foo(Runnable printFoo) {
            for (int i = 0; i < n; i++) {
                while (!fooExec) {

                }
                printFoo.run();
                fooExec = false;
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        public void bar(Runnable printBar) {
            for (int i = 0; i < n; i++) {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                printBar.run();
                fooExec = true;
            }
        }
    }

    /**
     * 使用yield实现自旋+让出cpu；
     */
    static class FooBar_Yield extends 交替打印FooBar1115 {
        volatile boolean permitFoo = true;

        public FooBar_Yield(int n) {
            super(n);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            int count = 0;
            for (int i = 0; i < n; ) {
                if (permitFoo) {
                    printFoo.run();
                    permitFoo = false;
                    i++;
                } else {
                    System.err.println("自旋foo" + (count++));
                    Thread.yield();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            int count = 0;
            for (int i = 0; i < n; ) {
                if (!permitFoo) {
                    printBar.run();
                    permitFoo = true;
                    i++;
                } else {
                    System.err.println("自旋bar" + (count++));
                    Thread.yield();
                }
            }
        }
    }

    /**
     * 使用reentrnatLock可重入锁自旋
     */
    static class Foobar_ReentrantLock extends 交替打印FooBar1115 {
        Lock lock = new ReentrantLock();
        boolean execFoo = true;

        public Foobar_ReentrantLock(int n) {
            super(n);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; ) {
                try {
                    lock.lock();
                    if (execFoo) {
                        printFoo.run();
                        execFoo = false;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; ) {
                try {
                    lock.lock();
                    if (!execFoo) {
                        printBar.run();
                        execFoo = true;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 使用reentrnatLock可重入锁自旋，并使用Condition解决空转竞争锁的情况
     */
    static class Foobar_ReentrantLock_Condition extends 交替打印FooBar1115 {
        Lock lock = new ReentrantLock(true);
        private final Condition foo = lock.newCondition();
        boolean execFoo = true;


        public Foobar_ReentrantLock_Condition(int n) {
            super(n);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    if (execFoo) {
                        printFoo.run();
                        execFoo = false;
                        Thread.sleep(200);
                        foo.signal();
                    } else {
                        foo.await();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    if (!execFoo) {
                        printBar.run();
                        execFoo = true;
                        foo.signal();
                    } else {
                        foo.await();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 使用synchronized代码块
     */
    static class Foobar_Synchronized extends 交替打印FooBar1115 {

        volatile boolean printFooFlag = true;
        private final Object lock = new Object();

        public Foobar_Synchronized(int n) {
            super(n);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    if (printFooFlag) {
                        printFoo.run();
                        printFooFlag = false;
                        lock.notifyAll();
                    }
                    lock.wait();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    if (printFooFlag) {
                        lock.wait();
                    }
                    printBar.run();
                    printFooFlag = true;
                    lock.notifyAll();
                }
            }
        }
    }

    /**
     * LockSupport类的核心方法其实就两个：park()和unpark()，其中park()方法用来阻塞当前调用线程，unpark()方法用于唤醒指定线程。
     * 这其实和Object类的wait()和signal()方法有些类似，但是LockSupport的这两种方法从语意上讲比Object类的方法更清晰，而且可以针对指定线程进行阻塞和唤醒。
     * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，可以把许可看成是一种(0,1)信号量（Semaphore），但与 Semaphore 不同的是，许可的累加上限是1。
     * 初始时，permit为0，当调用unpark()方法时，线程的permit加1，当调用park()方法时，如果permit为0，则调用线程进入阻塞状态。
     *
     * 作者：a-fei-8
     * 链接：https://leetcode.cn/problems/print-foobar-alternately/solution/chang-you-duo-xian-cheng-zhi-1115-by-a-f-mf5u/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Foobar_LockSupport extends 交替打印FooBar1115{

        public Foobar_LockSupport(int n) {
            super(n);
        }

        private volatile boolean printFooFlag = true;

        private Map<String,Thread> map = new ConcurrentHashMap<>(3);

        // 使用looksupport来实现
        public void foo(Runnable printFoo) throws InterruptedException {
            map.put("foo",Thread.currentThread());
            for (int i = 0; i < n; i++) {
                while (!printFooFlag) {
                    LockSupport.park();
                }
                printFoo.run();
                printFooFlag = false;
                LockSupport.unpark(map.get("bar"));
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            map.put("bar",Thread.currentThread());
            for (int i = 0; i < n; i++) {
                while(printFooFlag){
                    LockSupport.park();
                }
                printBar.run();
                printFooFlag = true;
                LockSupport.unpark(map.get("foo"));
            }
        }
    }

    public static void main(String a[]) {
        Runnable printFoo = () -> {
            System.out.print("foo");
        };
        Runnable printBar = () -> {
            System.out.print("bar");
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        交替打印FooBar1115 theTask = new Foobar_LockSupport(50);
        executorService.submit(new Thread(() -> {
            try {
                theTask.foo(new Thread(() -> System.out.print("foo")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        executorService.submit(new Thread(() -> {
            try {
                theTask.bar(new Thread(() -> System.out.println("bar")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        executorService.shutdown();
    }

}
