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
    private static final String YYYY_MM_DD_HH_MM = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";  // yyyy-MM-dd HH:mm
    private static final String DD_MM_YYYY_HH_MM = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";  // dd/MM/yyyy HH:mm
    private static final String DD_MM_YYYY_HHMM = "\\d{2}/\\d{2}/\\d{4} \\d{4}";         // dd/MM/yyyy HHmm
    private static final String YYYY_MM_DD = "\\d{4}-\\d{2}-\\d{2}";                     // yyyy-MM-dd
    private static final String DD_MM_YYYY = "\\d{2}/\\d{2}/\\d{4}";                     // dd/MM/yyyy

    // Combined regex for matching
    private static final String DATE_TIME_REGEX = "(" + YYYY_MM_DD_HH_MM + ")|" +
            "(" + DD_MM_YYYY_HH_MM + ")|" +
            "(" + DD_MM_YYYY_HHMM + ")|" +
            "(" + YYYY_MM_DD + ")|" +
            "(" + DD_MM_YYYY + ")";

    // Static final formatters for parsing
    private static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMATTER_DD_MM_YYYY_HH_MM = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATTER_DD_MM_YYYY_HHMM = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Converts matched date/time to the format "2 of December 2019, 6pm"
    private static String formatDateTime(String dateTime) {
        LocalDate date;
        LocalTime time = null;

        if (dateTime.matches(YYYY_MM_DD_HH_MM)) {
            LocalDateTime dt = LocalDateTime.parse(dateTime, FORMATTER_YYYY_MM_DD_HH_MM);
            date = dt.toLocalDate();
            time = dt.toLocalTime();
        } else if (dateTime.matches(DD_MM_YYYY_HH_MM)) {
            LocalDateTime dt = LocalDateTime.parse(dateTime, FORMATTER_DD_MM_YYYY_HH_MM);
            date = dt.toLocalDate();
            time = dt.toLocalTime();
        } else if (dateTime.matches(DD_MM_YYYY_HHMM)) {
            date = LocalDate.parse(dateTime.substring(0, 10), FORMATTER_DD_MM_YYYY);
            time = LocalTime.parse(dateTime.substring(11), FORMATTER_DD_MM_YYYY_HHMM);
        } else if (dateTime.matches(YYYY_MM_DD)) {
            date = LocalDate.parse(dateTime, FORMATTER_YYYY_MM_DD);
        } else if (dateTime.matches(DD_MM_YYYY)) {
            date = LocalDate.parse(dateTime, FORMATTER_DD_MM_YYYY);
        } else {
            return dateTime;
        }

        // Format the date as "2 of December 2019"
        String formattedDate = date.getDayOfMonth() + " of " +
                date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                date.getYear();

        // Format time if available
        String formattedTime = (time != null) ? formatTime(time) : "";

        return formattedDate + (formattedTime.isEmpty() ? "" : ", " + formattedTime);
    }

    private static String formatTime(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        String amPm = (hour >= 12) ? "pm" : "am";
        //convert 24-hour format to 12-hour format
        hour = (hour % 12 == 0) ? 12 : hour % 12;

        if (minute == 0) {
            return hour + amPm;
        } else {
            return hour + ":" + String.format("%02d", minute) + amPm;
        }
    }

    // Extracts date and time from a sentence
    public static String extractDateTime(String input) throws InvalidDateFormatException {
        Pattern pattern = Pattern.compile(DATE_TIME_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return formatDateTime(matcher.group());
        }
        throw new InvalidDateFormatException(
                InvalidDateFormatException.invalidDateFormatNotifier + '\n' +
                InvalidDateFormatException.validFormatSuggestion);
    }
}
