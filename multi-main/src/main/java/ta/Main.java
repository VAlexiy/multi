package ta;

import ta.thread.unit.TestUnitFactory;
import ta.thread.unit.UnitFactory;
import ta.util.logger.Logger;
import ta.util.logger.QueueSystemOutLogger;

import java.util.ArrayList;
import java.util.List;

import static ta.util.logger.Constants.LOG_CHRONO_FORMAT;

public class Main {

    //    private static final Logger logger = new SynchronizedSystemOutLogger(LOG_CHRONO_FORMAT);
    private static final Logger logger = new QueueSystemOutLogger(LOG_CHRONO_FORMAT);
    private static final UnitFactory UNIT_FACTORY = new TestUnitFactory(logger);

    public static void main(String[] args) {
        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(UNIT_FACTORY.getUnit()));
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
