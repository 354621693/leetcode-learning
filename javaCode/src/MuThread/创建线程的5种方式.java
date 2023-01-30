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
            throw new RuntimeException("dddddd");
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
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread tmpThread = new Thread(r);
                tmpThread.setUncaughtExceptionHandler(new SuccessCatchThreadExceptionHandler());
                return tmpThread;
            }
        });
        // execute方法无返回值
        executorService.execute(new MyRunnable());
        // submit方法返回一个future对象，future.get()会阻塞等待
        Future<String> submit = executorService.submit(new MyCallable());
        while (!submit.isDone()) {
            // 等待
//            System.out.println("waiting");
        }
        System.out.println(submit.get());
        executorService.shutdown();
    }


    public static void main(String args[]) throws ExecutionException, InterruptedException {
        new MyThread().start();
        //多线程的异常的有别于主线程的异常，多线程的异常异常一般只能在各自的run（）方法中进行try-catch，外层的主线程使用try-catch，
        // 一般情况下对线程异常无法成功捕获。这是因为在线程Thread类中有一个接口 UncaughtExceptionHandler，它包含了一个uncaughtRxception（）方法。
        // 当线程运行时候出现了异常而终止的时候，但该异常又没有try-catch的时候就会触发此方法，由于Java的Thread类默认使用uncaughtException（）进行空处理，
        // 而 Java 虚拟机又会忽略该方法之后的异常抛出。所以就导致在外线程无法对内线程进行try-catch，捕获到内线程的异常。
        try {
            new Thread(new MyRunnable()).start();
        }catch (Exception e){
            System.out.println("抛出了异常：" + e.toString());
        }
        new Thread(new FutureTask<String>(new MyCallable())).start();
        new Thread(() -> {
            System.out.println("使用lambda表达式创建线程");
        }).start();
        executor();
    }

    /**
     * 自定义异常处理类
     */
    static class SuccessCatchThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        /**
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         *
         * @param t the thread
         * @param e the exception
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + "抛出了异常：" + e.toString());
        }
    }
}
