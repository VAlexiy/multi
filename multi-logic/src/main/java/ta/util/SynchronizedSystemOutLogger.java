package ta.util;

import java.io.PrintStream;
import java.util.Calendar;

import static ta.util.StringUtils.isNullOrEmpty;

public class SynchronizedSystemOutLogger implements Logger {

    // see Date/Time Conversions
    // %1$tL - Millisecond
    // %1$tN - Nanosecond
    public static final String LOG_CHRONO_FORMAT = "[%1$tY-%1$tm-%1$td] - [%tT %1$tL] ";

    private String chronoFormat;

    public SynchronizedSystemOutLogger(final String chronoFormat) {
        this.chronoFormat = chronoFormat;
    }

    @Override
    public synchronized void out(final String message, Object ... values) {
        PrintStream out = System.out;

//        Calendar calendar = GregorianCalendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        if (!isNullOrEmpty(chronoFormat)) {
            out = out.format(chronoFormat, calendar);
        }

        out.printf(message, values).println();
    }
}
