package persistence;

import model.Reminder;
import model.ReminderList;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class is heavily structured based on the persistence
// from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ReminderList reminderList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyReminderList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReminderList.json");
        try {
            ReminderList rl = reader.read();
            assertEquals(0, rl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralReminderList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralReminderList.json");
        try {
            ReminderList rl = reader.read();
            List<Reminder> reminders = rl.getReminders();
            assertEquals(2, reminders.size());

            DateTime dt = new DateTime(2020, 10, 15, 15, 15);

            checkReminder("test 1", "test description 1", dt, reminders.get(0));
            checkReminder("test 2", "test description 2", dt, reminders.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}