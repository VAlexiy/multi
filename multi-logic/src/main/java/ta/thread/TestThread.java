package ta.thread;

import ta.util.Logger;

import java.io.Serializable;
import java.util.Map;

public class TestThread implements HandledRunnable {

    private final Logger logger;

    public TestThread(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void run(Map<String, Serializable> context) throws InterruptedException {
        final Thread thread = Thread.currentThread();
        logger.out("thread = [%s]", thread.getName());
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

}

