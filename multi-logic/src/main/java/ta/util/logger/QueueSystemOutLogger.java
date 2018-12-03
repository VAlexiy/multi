package ta.util.logger;

import java.util.Calendar;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static ta.util.StringUtils.isNullOrEmpty;
import static ta.util.logger.Constants.LOG_ERROR_FORMAT;

public class QueueSystemOutLogger implements Logger {

    private String chronoFormat;

    private volatile boolean started;

    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    public QueueSystemOutLogger(final String chronoFormat) {
        this.chronoFormat = chronoFormat;
        this.started = true;
        Thread pooler = new Thread(new Pooler());
        pooler.start();
    }

    private class Pooler implements Runnable {

        public void run() {
//            sleep(1000);
            while (started || !queue.isEmpty()) {
                while (!queue.isEmpty()) {
                    out(queue.poll());
                    sleep(500);
                }
            }
        }

        private void out(String s) {
            if (s != null) {
                System.out.println(s);
            }
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public String getErrorFormat() {
        return LOG_ERROR_FORMAT;
    }

    @Override
    public void out(final String message, Object... values) {
        queue.add(getChronoString().concat(getFormattedString(message, values)));
    }

    private String getChronoString() {
        return isNullOrEmpty(chronoFormat) ? "" : getFormattedString(chronoFormat, Calendar.getInstance());
    }

    private String getFormattedString(final String message, final Object... values) {
        return String.format(message, values);
    }
}
