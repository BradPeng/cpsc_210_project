package ui.helpers;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

// class for storing the consistent DateTimeFormatter used throughout the app
public class DateFormatter {
    public static DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm");
}
