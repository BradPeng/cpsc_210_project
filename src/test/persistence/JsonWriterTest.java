package persistence;

import model.Reminder;
import model.ReminderList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class is heavily structured based on the persistence
// from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    ReminderList rl;

    @BeforeEach
    private void runBefore() {
        rl = new ReminderList();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyReminderList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyReminderList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyReminderList.json");
            rl = reader.read();
            assertEquals(0, rl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralReminderList() {
        try {
            DateTime dt = new DateTime(2020, 10, 15, 15, 15);

            rl.addReminder(new Reminder("test 1", "test description 1",
                    dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour()));
            rl.addReminder(new Reminder("test 2", "test description 2",
                    dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour()));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralReminderList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralReminderList.json");
            rl = reader.read();
            List<Reminder> reminders = rl.getReminders();
            assertEquals(2, reminders.size());

            checkReminder("test 1", "test description 1", dt, reminders.get(0));
            checkReminder("test 2", "test description 2", dt, reminders.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}