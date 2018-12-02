package ta;

import ta.thread.TestThread;
import ta.thread.unit.TestUnitFabric;
import ta.thread.unit.UnitFabric;
import ta.util.Logger;
import ta.util.SystemOutLogger;

import static ta.util.SystemOutLogger.LOG_CHRONO_FORMAT;

public class Main {

    private static final Logger logger = new SystemOutLogger(LOG_CHRONO_FORMAT);
    private static final UnitFabric unitFabric = new TestUnitFabric(logger);

    public static void main(String[] args) {

        final Thread thread1 = new Thread(unitFabric.getUnit());
        final Thread thread2 = new Thread(unitFabric.getUnit());
        final Thread thread3 = new Thread(unitFabric.getUnit());

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
