package ta.util.logger;

public interface Logger {

    default void stop() {
    }

    String getErrorFormat();

    default void error(Throwable throwable) {
        out(getErrorFormat(),
                Thread.currentThread().getName(),
                throwable.getClass().getSimpleName(),
                throwable.getLocalizedMessage()
        );
    }

    void out(String message, Object... values);
}
