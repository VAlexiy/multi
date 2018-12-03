package ta.util.logger;

import java.io.PrintStream;
import java.util.Calendar;

import static ta.util.StringUtils.isNullOrEmpty;
import static ta.util.logger.Constants.LOG_ERROR_FORMAT;

public class SynchronizedSystemOutLogger implements Logger {

    private String chronoFormat;

    public SynchronizedSystemOutLogger(final String chronoFormat) {
        this.chronoFormat = chronoFormat;
    }

    @Override
    public String getErrorFormat() {
        return LOG_ERROR_FORMAT;
    }

    @Override
    public synchronized void out(final String message, final Object ... values) {
        PrintStream out = System.out;

        if (!isNullOrEmpty(chronoFormat)) {
//        Calendar calendar = GregorianCalendar.getInstance();
            final Calendar calendar = Calendar.getInstance();
            out = out.format(chronoFormat, calendar);
        }

        out.printf(message, values).println();
    }
}
