package main.java.Hudou.parser;

import main.java.Hudou.exception.InvalidDateFormatException;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeParser {
    private static final String YYYY_MM_DD_HH_MM = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}";  // yyyy-MM-dd HH:mm
    private static final String DD_MM_YYYY_HH_MM = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";  // dd/MM/yyyy HH:mm
    private static final String DD_MM_YYYY_HHMM = "\\d{2}/\\d{2}/\\d{4} \\d{4}";         // dd/MM/yyyy HHmm

    private static final String YYYY_MM_DD = "\\d{4}/\\d{2}/\\d{2}";                     // yyyy-MM-dd
    private static final String DD_MM_YYYY = "\\d{2}/\\d{2}/\\d{4}";                     // dd/MM/yyyy

    // Combined regex for matching
    private static final String DATE_TIME_REGEX = "(" + YYYY_MM_DD_HH_MM + ")|" +
            "(" + DD_MM_YYYY_HH_MM + ")|" +
            "(" + DD_MM_YYYY_HHMM + ")|" +
            "(" + YYYY_MM_DD + ")|" +
            "(" + DD_MM_YYYY + ")";

    // Static final formatters for parsing
    private static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final DateTimeFormatter FORMATTER_DD_MM_YYYY_HH_MM = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATTER_DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static Pair<LocalDate, LocalTime> extractDateTime(String input) throws InvalidDateFormatException {
        Pattern pattern = Pattern.compile(DATE_TIME_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return parseDateTime(matcher.group());
        }
        throw new InvalidDateFormatException(
                InvalidDateFormatException.invalidDateFormatNotifier + '\n' +
                        InvalidDateFormatException.validFormatSuggestion);
    }

    private static Pair<LocalDate, LocalTime> parseDateTime(String dateTime) {
        if (dateTime.matches(YYYY_MM_DD_HH_MM)) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, FORMATTER_YYYY_MM_DD_HH_MM);
            return new Pair<>(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        } else if (dateTime.matches(DD_MM_YYYY_HH_MM)) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, FORMATTER_DD_MM_YYYY_HH_MM);
            return new Pair<>(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        } else if (dateTime.matches(DD_MM_YYYY)) {
            LocalDate date = LocalDate.parse(dateTime, FORMATTER_DD_MM_YYYY);
            return new Pair<>(date, null);
        } else if (dateTime.matches(YYYY_MM_DD)) {
            LocalDate date = LocalDate.parse(dateTime, FORMATTER_YYYY_MM_DD);
            return new Pair<>(date, null);
        } else {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

}

