package ta;

import ta.thread.TestThread;
import ta.util.SystemOutLogger;

import static ta.util.SystemOutLogger.LOG_CHRONO_FORMAT;

public class Main {

    public static void main(String[] args) {

        final SystemOutLogger logger = new SystemOutLogger(LOG_CHRONO_FORMAT);

        final TestThread testThread1 = new TestThread(logger);
        final Thread thread1 = new Thread(testThread1);

        final Thread thread2 = new Thread(new TestThread(logger));
        final Thread thread3 = new Thread(new TestThread(logger));

        logger.out("Start");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            logger.out("Exception: [%s] - '%s'", e.getClass().getSimpleName(), e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }

        logger.out("Stop");

    }
}
