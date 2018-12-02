package ta.thread;

import ta.util.Logger;

import java.util.Optional;

public interface HandledRunnable extends Runnable {

    void run(ThreadContext context) throws InterruptedException;

    ThreadContext getContext();

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
            run(getContext());
        } catch (InterruptedException e) {
            logException(e);
            Thread.currentThread().interrupt();
        }
    }
}
