package ta.util;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static ta.util.StringUtils.isNullOrEmpty;

public class SystemOutLogger implements Logger {

    // see Date/Time Conversions
    // %1$tL - Millisecond
    // %1$tN - Nanosecond
    public static final String LOG_CHRONO_FORMAT = "[%1$tY-%1$tm-%1$td] - [%tT %1$tL] ";
//    public static final String LOG_CHRONO_FORMAT = "[%tY-%tm-%td] - [%tT %1$tL %1$tN] ";

    private String chronoFormat;

    public SystemOutLogger(final String chronoFormat) {
        this.chronoFormat = chronoFormat;
    }

    public synchronized void out(final String message, String... values) {
/*
        String result = values.length > 0
                ? String.format(message, values)
                : message;
        System.out.println(result);
*/
        PrintStream out = System.out;

//        Calendar c = new GregorianCalendar(1995, MAY, 23);
//        String s = String.format("Duke's Birthday: %1$tb %1$te, %1$tY", c);
        // -> s == "Duke's Birthday: May 23, 1995"

//        Calendar calendar = GregorianCalendar.getInstance();
        Calendar calendar = Calendar.getInstance();
//        GregorianCalendar.

        if (!isNullOrEmpty(chronoFormat)) {
            out = out.format(chronoFormat, calendar);
        }

        out.printf(message, values).println();
    }
}
