package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReminderListTest {
    ReminderList reminderList;

    @BeforeEach
    public void runBefore() {
        reminderList = new ReminderList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, reminderList.getSize());
    }

    @Test
    public void testGetSizeEmpty() {
        assertEquals(0, reminderList.getSize());
    }

    @Test
    public void testGetSizeNotEmpty() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        assertEquals(1, reminderList.getSize());
    }

    @Test
    public void testAddReminderToEmpty() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        assertEquals(r, reminderList.getReminderAtIndex(0));
    }

    @Test
    public void testAddReminderToNotEmpty() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);

        for (int i = 0; i < 10; i++) {
            reminderList.addReminder(r);
            assertEquals(i + 1, reminderList.getSize());
        }
    }

    @Test
    public void testRemoveAtIndexStart() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        assertEquals(1, reminderList.getSize());
        reminderList.removeAtIndex(0);
        assertEquals(0, reminderList.getSize());
    }

    @Test
    public void testRemoveAtIndexMiddle() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r2 = new Reminder("title 2", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r3 = new Reminder("title 3", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r3);
        assertEquals(3, reminderList.getSize());
        reminderList.removeAtIndex(1);
        assertEquals(2, reminderList.getSize());

        assertEquals(r, reminderList.getReminderAtIndex(0));
        assertEquals(r3, reminderList.getReminderAtIndex(1));
    }

    @Test
    public void testRemoveAtIndexEnd() {
        Reminder r = new Reminder("title", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r2 = new Reminder("title 2", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r2);
        Reminder r3 = new Reminder("title 3", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r3);

        assertEquals(3, reminderList.getSize());
        reminderList.removeAtIndex(2);
        assertEquals(2, reminderList.getSize());

        assertEquals(r, reminderList.getReminderAtIndex(0));
        assertEquals(r2, reminderList.getReminderAtIndex(1));
    }

    @Test
    public void testGetReminderAtIndexStart() {
        Reminder r = new Reminder("title 1", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r2 = new Reminder("title 2", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r2);
        Reminder r3 = new Reminder("title 3", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r3);

        assertEquals(3, reminderList.getSize());

        assertEquals(r, reminderList.getReminderAtIndex(0));
        assertEquals(3, reminderList.getSize());
    }

    @Test
    public void testGetReminderAtIndexMiddle() {
        Reminder r = new Reminder("title 1", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r2 = new Reminder("title 2", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r2);
        Reminder r3 = new Reminder("title 3", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r3);

        assertEquals(3, reminderList.getSize());

        assertEquals(r2, reminderList.getReminderAtIndex(1));
        assertEquals(3, reminderList.getSize());
    }

    @Test
    public void testGetReminderAtIndexEnd() {
        Reminder r = new Reminder("title 1", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);
        Reminder r2 = new Reminder("title 2", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r2);
        Reminder r3 = new Reminder("title 3", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r3);

        assertEquals(3, reminderList.getSize());

        assertEquals(r3, reminderList.getReminderAtIndex(2));
        assertEquals(3, reminderList.getSize());
    }

    @Test
    public void testGetReminderAtIndexOneElement() {
        Reminder r = new Reminder("title 1", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);

        assertEquals(1, reminderList.getSize());
        assertEquals(r, reminderList.getReminderAtIndex(0));
    }

    @Test
    public void testGetReminderAtIndexOutOfBounds() {
        Reminder r = new Reminder("title 1", "description", 2020, 12, 12, 12, 15);
        reminderList.addReminder(r);

        assertEquals(1, reminderList.getSize());
        assertEquals(null, reminderList.getReminderAtIndex(15));
        assertEquals(null, reminderList.getReminderAtIndex(-15));
    }
}