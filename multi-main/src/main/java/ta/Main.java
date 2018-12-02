package ta;

import ta.thread.unit.TestUnitFabric;
import ta.thread.unit.UnitFabric;
import ta.util.Logger;
import ta.util.SystemOutLogger;

import java.util.ArrayList;
import java.util.List;

import static ta.util.SystemOutLogger.LOG_CHRONO_FORMAT;

public class Main {

    private static final Logger logger = new SystemOutLogger(LOG_CHRONO_FORMAT);
    private static final UnitFabric unitFabric = new TestUnitFabric(logger);

    public static void main(String[] args) {
        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(unitFabric.getUnit()));
        }

        logger.out("Start");

        threads.forEach(Thread::start);
        threads.forEach(Main::threadJoin);

        logger.out("Stop");
    }

    private static void threadJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread currentThread = Thread.currentThread();
            logger.out("Thread: [%s], Exception: [%s] - '%s'",
                    currentThread.getName(),
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()
            );
            currentThread.interrupt();
//            thread.interrupt();
        }
    }
}
