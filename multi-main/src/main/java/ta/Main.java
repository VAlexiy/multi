package ta;

import ta.thread.unit.TestUnitFabric;
import ta.thread.unit.UnitFabric;
import ta.util.logger.Logger;
import ta.util.logger.QueueSystemOutLogger;
import ta.util.logger.SynchronizedSystemOutLogger;

import java.util.ArrayList;
import java.util.List;

import static ta.util.logger.Constants.LOG_CHRONO_FORMAT;

public class Main {

    //    private static final Logger logger = new SynchronizedSystemOutLogger(LOG_CHRONO_FORMAT);
    private static final Logger logger = new QueueSystemOutLogger(LOG_CHRONO_FORMAT);
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
        //TODO: придумать неявный способ остановки
        logger.stop();
    }

    private static void threadJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error(e);
//            thread.interrupt();
            final Thread currentThread = Thread.currentThread();
            currentThread.interrupt();
        }
    }
}
