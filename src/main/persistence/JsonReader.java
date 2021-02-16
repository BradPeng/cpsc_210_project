package persistence;

import model.Reminder;
import model.ReminderList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.json.*;

// This class is heavily structured based on the persistence
// from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads reminders from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ReminderList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ReminderList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseReminderList(jsonObject);
    }

    // EFFECTS: reads source file a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses reminders from JSON object and returns it
    private ReminderList parseReminderList(JSONObject jsonObject) {
        ReminderList reminderList = new ReminderList();
        addReminders(reminderList, jsonObject);
        return reminderList;
    }

    // MODIFIES: rl
    // EFFECTS: parses reminders from JSON object and adds them to reminderList
    private void addReminders(ReminderList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reminders");
        for (Object json : jsonArray) {
            JSONObject nextReminder = (JSONObject) json;
            addReminder(rl, nextReminder);
        }
    }

    // MODIFIES: rl
    // EFFECTS: parses reminder from JSON object and adds it to reminderList
    private void addReminder(ReminderList rl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String date = jsonObject.getString("date");
        DateTime dt = Reminder.DATE_FORMAT.parseDateTime(date);
        Reminder r = new Reminder(title, description, dt.getYear(), dt.getMonthOfYear(),
                dt.getDayOfMonth(), dt.getHourOfDay(), dt.getMinuteOfHour());
        rl.addReminder(r);
    }
}
