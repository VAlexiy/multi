package ta.thread.unit;

import ta.thread.TestThreadContext;
import ta.thread.ThreadContext;
import ta.util.Logger;

public class TestUnit implements Unit {

    private final Logger logger;
    private final String id;

    public TestUnit(final Logger logger, final String id) {
        this.logger = logger;
        this.id = id;
    }

    @Override
    public void run(ThreadContext context) {
        final Thread thread = Thread.currentThread();
        logger.out("thread = [%s], id = [%s]", thread.getName(), getId());
    }

    @Override
    public ThreadContext getContext() {
        return new TestThreadContext();
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public String getId() {
        return id;
    }
}
