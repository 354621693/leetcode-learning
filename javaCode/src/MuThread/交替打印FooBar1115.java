package MuThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * https://leetcode.cn/problems/print-foobar-alternately/description/
 * @author leewencan
 * @date 2023/1/28 16:27
 */
public class 交替打印FooBar1115 {
    private int n;

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
    static class Foobar1 {
        private int n;
        Semaphore semaphoreFoo = new Semaphore(1);
        Semaphore semaphoreBar = new Semaphore(0);

        public Foobar1(int n) {
            this.n = n;
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

    public static void main(String a[]){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Foobar1 foobar1 = new Foobar1(10);
        executorService.submit(new Thread(()->{
            try {
                foobar1.foo(new Thread(()->System.out.println("foo")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        executorService.submit(new Thread(()->{
            try {
                foobar1.bar(new Thread(()->System.out.println("bar")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
