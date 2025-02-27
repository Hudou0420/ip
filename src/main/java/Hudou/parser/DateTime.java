package main.java.Hudou.parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTime {
    private LocalDate date;
    private LocalTime time;

    public DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public String printDateTime(){
        String printedDateTime = "";
        if (date != null) {
            printedDateTime += date.toString();
            if (time != null) {
                printedDateTime += ", ";
            }
        }
        if (time != null) {
            printedDateTime += time.toString();
        }
        return printedDateTime;
    }

    public String toString(){
        String dateToString = date == null ? "" : date.toString();
        String timeToString = time == null ? "" : time.toString();
        return dateToString + "+" + timeToString;
    }

    public static DateTime parse(String dateTimeString){
        String[] stringInputs = dateTimeString.split("\\+");
        LocalDate date = LocalDate.parse(stringInputs[0]);
        LocalTime time = stringInputs.length > 1 ? LocalTime.parse(stringInputs[1]) : null;
        return new DateTime(date, time);
    }
}
