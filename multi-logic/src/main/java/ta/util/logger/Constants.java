package ta.util.logger;

import ta.util.AbstractUtils;

public final class Constants extends AbstractUtils {
    // see Date/Time Conversions
    // %1$tL - Millisecond
    // %1$tN - Nanosecond
    public static final String LOG_CHRONO_FORMAT = "[%1$tY-%1$tm-%1$td] - [%tT %1$tL] ";
    public static final String LOG_ERROR_FORMAT = "Thread: [%s], Exception: [%s] - '%s'";
}
