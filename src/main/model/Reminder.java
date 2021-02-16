package model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;
import persistence.Writable;

// A class that represents a reminder that might be found on a calendar app.
// Stores information such as the title, description, date and time of the reminder.
public class Reminder implements Writable {
    private String title;
    private String description;
    private DateTime dateTime;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("MM/dd/yyyy 'at' HH:mm");


    // EFFECTS: creates a reminder with title, description, date and time
    public Reminder(String title, String description, int year, int month, int day, int hour, int minute) {
        this.title = title;
        this.description = description;
        dateTime = new DateTime(year, month, day, hour, minute);
    }

    // REQUIRED: time must be a valid military time
    // MODIFIES: this
    // EFFECTS: sets this.title to time
    public void setTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: sets this.description to description
    public void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS: sets this.dateTime to dt
    public void setDateTime(DateTime dt) {
        this.dateTime = dt;
    }

    // EFFECTS: returns this.title
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns this.description
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns this.dateTime
    public DateTime getDateTime() {
        return this.dateTime;
    }

    // Method created based on code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: creates a new JSONObject fields title, description and date
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("date", dateTime.toString(DATE_FORMAT));

        return json;
    }
}