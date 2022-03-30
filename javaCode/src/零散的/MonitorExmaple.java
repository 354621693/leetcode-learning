package 零散的;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2020-08-28-9:46
 */
public class MonitorExmaple {
    int a = 0;

    public synchronized void writer() {
        a++;
        System.out.println("w" + a);
    }

    public synchronized void reader() {
        int i = a;
        System.out.println("r" + a);
    }

    public static void main(String[] args) {
        MonitorExmaple monitorExmaple = new MonitorExmaple();
        Thread w = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    monitorExmaple.writer();
                }
            }
        });

        Thread r = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    monitorExmaple.reader();
                }
            }
        });
        w.start();
        r.start();

    }
}
