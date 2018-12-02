package ta.thread;

import ta.util.Logger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface HandledRunnable extends Runnable {

    void run(Map<String, Serializable> context) throws InterruptedException;

    default Logger getLogger() {
        return null;
    }

    default void logException(Throwable throwable) {
        Optional.ofNullable(getLogger()).ifPresent(
                logger -> logger.out("Exception: [%s] - '%s'",
                        throwable.getClass().getSimpleName(),
                        throwable.getLocalizedMessage()
                )
        );
    }

    default void run() {
        try {
            run(new HashMap<>());
        } catch (InterruptedException e) {
            logException(e);
            Thread.currentThread().interrupt();
        }
    }
}
