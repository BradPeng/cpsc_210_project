package persistence;


import model.Reminder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class is heavily structured based on the persistence
// from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkReminder(String title, String description, DateTime date, Reminder r) {
        assertEquals(title, r.getTitle());
        assertEquals(description, r.getDescription());

        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm");

        assertEquals(fmt.print(date), fmt.print(r.getDateTime()));
    }
}
