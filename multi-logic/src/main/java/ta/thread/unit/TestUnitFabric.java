package ta.thread.unit;

import ta.util.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class TestUnitFabric implements UnitFabric {

    private final Logger logger;
    private AtomicLong lastUnitId = new AtomicLong(0L);

    public TestUnitFabric(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Unit getUnit() {
        return new TestUnit(this.logger, String.valueOf(lastUnitId.incrementAndGet()));
    }
}
