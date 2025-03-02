package main.java.Hudou.exception;

// Custom Exception for Date Parsing Errors
public class InvalidDateFormatException extends Exception {
    public static final String invalidDateFormatNotifier = "You have entered an invalid date format.";
    public static final String validFormatSuggestion =
            "Suggested format:\n" +
                              "\tyyyy/MM/dd HH:mm, \n" +
                              "\tdd/MM/yyyy HH:mm, \n" +
                              "\tdd/MM/yyyy";

    public InvalidDateFormatException(String message) {
        super(message);
    }
}
