package MuThread;

import java.util.concurrent.*;

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

    public static void main(String a[]) {
        Runnable printFoo = () -> {
            System.out.print("foo");
        };
        Runnable printBar = () -> {
            System.out.print("bar");
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
//        交替打印FooBar1115 交替打印FooBar1115 = new Foobar_Semaphore(10);
        交替打印FooBar1115 交替打印FooBar1115 = new Foobar2_CyclicBarrier(10);
        FooBar_Yield fooBar_Yield = new FooBar_Yield(10);
        交替打印FooBar1115 theTask = fooBar_Yield;
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
