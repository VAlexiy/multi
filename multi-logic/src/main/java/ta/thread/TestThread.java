package ta.thread;

import ta.util.logger.Logger;

public class TestThread implements HandledRunnable {

    private final Logger logger;

    public TestThread(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void run(ThreadContext context) throws InterruptedException {
        final Thread thread = Thread.currentThread();
        logger.out("thread = [%s]", thread.getName());
    }

    @Override
    public ThreadContext getContext() {
        return new TestThreadContext();
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

}

