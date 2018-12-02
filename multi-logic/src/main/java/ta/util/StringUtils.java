package ta.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ta.util.Constants.LINE_DELIMITER;

public final class StringUtils extends AbstractUtils {

    private static final String EMPTY = "";

    /**
     * Строка пустая, либо содержит только пробельные символы?
     *
     * @param str проверяемая строка
     * @return true, если строка == null, или содержит только "пробельные" символы
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    /**
     * Строка пустая?
     *
     * @param str проверяемая строка
     * @return true, если строка == null, или содержит только "пробельные" символы
     */
    public static boolean isNullOrEmpty(String str) {
//        return jdk.internal.joptsimple.internal.Strings.isNullOrEmpty(str);
        return str == null || EMPTY.equals(str);
    }

    /**
     * Преобразовать мапу в String; каждый элемент мапы - отдельная строка текста.
     *
     * @param map мапа
     * @param <K> класс ключа мапы
     * @param <V> класс значения мапы
     */
    public static <K extends Comparable, V> String mapToString(Map<K, V> map) {
        return mapToString(map, LINE_DELIMITER, "%s -> %s", "", "  ");
    }

    @SuppressWarnings("unchecked")
    private static <K extends Comparable, V> String mapToString(Map<K, V> map, String delimiter, String pattern, String beforeLine, String tabStr) {
        return mapToString(map, delimiter,
                entry -> {
                    Object value = entry.getValue();
                    // считаем, что это именно Map<K, V>
                    if (value instanceof Map) {
                        value = delimiter + mapToString((Map<K, V>) value, delimiter, pattern, beforeLine + tabStr, tabStr);
                    }
                    return beforeLine + String.format(pattern, entry.getKey(), value);
                });
    }

    /**
     * Преобразовать мапу в String
     *
     * @param map        мапа
     * @param delimiter  разделитель строковых представлений элементов мапы
     * @param entryToStr преобразователь элемента мапы в строку
     * @param <K>        класс ключа мапы
     * @param <V>        класс значения мапы
     */
    @SuppressWarnings("unchecked")
    public static <K extends Comparable, V> String mapToString(Map<K, V> map, String delimiter, Function<Map.Entry, String> entryToStr) {
        if (map == null) {
            return "null";
        }
        return map.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .map(entryToStr)
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Соединить строки
     *
     * @param delimiter разделитель
     * @param strings   строки
     */
    public static String join(String delimiter, String... strings) {
        return Arrays.stream(strings).collect(Collectors.joining(delimiter));
    }

    public static String oToString(Object o) {
        if (o != null) {
            return o.toString();
        } else {
            return null;
        }
    }
}
