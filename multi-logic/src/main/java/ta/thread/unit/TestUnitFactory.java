package ta.thread.unit;

import ta.util.logger.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class TestUnitFactory implements UnitFactory {

    private final Logger logger;
    private AtomicLong lastUnitId = new AtomicLong(0L);

    public TestUnitFactory(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Unit getUnit() {
        return new TestUnit(this.logger, String.valueOf(lastUnitId.incrementAndGet()));
    }
}
