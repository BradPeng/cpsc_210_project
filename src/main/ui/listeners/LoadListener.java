package ui.listeners;

import model.ReminderList;
import persistence.JsonReader;
import ui.MainMenu;
import ui.helpers.SoundPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadListener implements ActionListener {
    private JsonReader jsonReader;
    private JFrame frame;
    private static final String JSON_STORE = "./data/reminders.json";

    private ReminderList reminderList;
    private MainMenu contentFrame;

    // EFFECTS: Create a LoadListener and set fields.
    public LoadListener(JsonReader jsonReader, JFrame frame, ReminderList rl, MainMenu contentFrame) {
        this.jsonReader = jsonReader;
        this.frame = frame;
        this.reminderList = rl;
        this.contentFrame = contentFrame;
    }

    // MODIFIES: reminderList, contentFrame
    // EFFECTS: Attempt to load reminderList from JSON file stored at "./data/reminders.json" and
    //          update the reminderList and list model.
    //          If successful: Play a sound a create a popup notifying the user that reminders were loaded
    //          If unsuccessful: Create a popup notifying the user that the load failed.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            reminderList = jsonReader.read();
            contentFrame.resetPanels(reminderList);
            SoundPlayer.playSound();
            JOptionPane.showMessageDialog(frame, "Successfully loaded from file!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Unable to load from file: " + JSON_STORE);
        }
    }
}
