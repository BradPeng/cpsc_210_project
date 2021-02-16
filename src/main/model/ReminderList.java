package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A class that functions as a collection of Reminder objects. A ReminderList object behaves much like a calender,
// storing information about tasks that need to be done.
public class ReminderList implements Writable {
    private List<Reminder> reminderList;

    // EFFECTS: create an empty ReminderList
    public ReminderList() {
        reminderList = new ArrayList<>();
    }

    // EFFECTS: returns the number of elements in reminderList
    public int getSize() {
        return this.reminderList.size();
    }

    // MODIFIES: this
    // EFFECTS: adds r to end of reminderList
    public void addReminder(Reminder r) {
        this.reminderList.add(r);
    }

    // REQUIRES: index i is in bounds of reminderList
    // MODIFIES: this
    // EFFECTS: remove remove the reminder stored at index i of reminderList
    public void removeAtIndex(int i) {
        reminderList.remove(i);
    }

    // REQUIRES: ReminderList must not be empty
    // EFFECTS: Returns the reminder stored at index i of reminderList.
    //          return null if index is out of bounds
    public Reminder getReminderAtIndex(int i) {
        if (i < reminderList.size() && i >= 0) {
            return reminderList.get(i);
        } else {
            return null;
        }
    }

    // EFFECTS: return a collection of all reminders
    public List<Reminder> getReminders() {
        return Collections.unmodifiableList(reminderList);
    }


    // Method created based on code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: create a new JSONObject and add reminders
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("reminders", remindersToJson());
        return json;
    }

    // Method created based on code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: loop through reminderList and store each reminder into a JSONArray. Return JSONArray
    private JSONArray remindersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Reminder r : reminderList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

}
