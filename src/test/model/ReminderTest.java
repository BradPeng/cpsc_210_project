package model;

import org.joda.time.DateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ReminderTest {
    Reminder reminder;

    @BeforeEach
    public void runBefore() {
        reminder = new Reminder("title", "description", 2020, 12, 12, 12, 15);
    }

    @Test
    public void testConstructor() {
        assertEquals("title", reminder.getTitle());
        assertEquals("description", reminder.getDescription());

        DateTime testDt = new DateTime(2020, 12, 12, 12, 15 );
        assertEquals(testDt, reminder.getDateTime());
    }

    @Test
    public void testSetTitleNoChange() {
        reminder.setTitle("title");
        assertEquals("title", reminder.getTitle());
    }

    @Test
    public void testSetTitleChange() {
        reminder.setTitle("new title");
        assertEquals("new title", reminder.getTitle());
    }

    @Test
    public void testSetDescriptionNoChange() {
        reminder.setDescription("description");
        assertEquals("description", reminder.getDescription());
    }

    @Test
    public void testSetDescriptionChange() {
        reminder.setDescription("new description");
        assertEquals("new description", reminder.getDescription());
    }

    @Test
    public void testSetDateNoChange() {
        DateTime testDt = new DateTime(2020, 12, 12, 12, 15 );
        reminder.setDateTime(testDt);
        assertEquals(testDt, reminder.getDateTime());
    }

    @Test
    public void testSetDateChange() {
        DateTime testDt = new DateTime(2021, 11, 11, 11, 20 );

        reminder.setDateTime(testDt);
        assertEquals(testDt, reminder.getDateTime());
    }
}
