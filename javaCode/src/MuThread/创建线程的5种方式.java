package MuThread;

import java.util.concurrent.*;

/**
 * @author leewencan
 * @date 2023/1/29 10:07
 */
public class 创建线程的5种方式 {
    /**
     * 继承Thread类
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("通过继承thread创建,name:" + Thread.currentThread().getThreadGroup().getName());
        }
    }

    /**
     * 实现Runnable接口
     */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.print("create a thread with Runable");
        }
    }

    /**
     * 实现Callable接口，指定返回类型
     */
    static class MyCallable implements Callable<String> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            System.out.print("create a thread with Callable");
            if (true) {
                throw new Exception("dddddd");
            }
            return "Callable returning";
        }

    }


    static void executor() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // execute方法无返回值
        executorService.execute(new MyRunnable());
        // submit方法返回一个future对象，future.get()会阻塞等待
        Future<String> submit = executorService.submit(new MyCallable());
        while (!submit.isDone()) {
            System.out.println("waiting");
        }
        System.out.println(submit.get());
        executorService.shutdown();
    }


    public static void main(String args[]) throws ExecutionException, InterruptedException {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(new FutureTask<String>(new MyCallable())).start();
        new Thread(() -> {
            System.out.println("使用lambda表达式创建线程");
        }).start();
        executor();
    }
}
