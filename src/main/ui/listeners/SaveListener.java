package ui.listeners;

import model.ReminderList;
import persistence.JsonWriter;
import ui.ReminderAppGUI;
import ui.helpers.SoundPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveListener implements ActionListener {

    private ReminderList reminderList;
    private ReminderAppGUI reminderAppGUI;

    // EFFECTS: Create a new SaveListener and set fields.
    public SaveListener(ReminderList reminderList, ReminderAppGUI reminderAppGUI) {
        this.reminderList = reminderList;
        this.reminderAppGUI = reminderAppGUI;
    }

    // MODIFIES: "./data/reminders.json"
    // EFFECTS: Tries to save the reminders in reminderList to "./data/reminders.json".
    //          If successful: Play a sound and create a popup notifying the user that reminders were saved.
    //          If unsuccessful: Create a popup notifying the user that the save has failed.
    @Override
    public void actionPerformed(ActionEvent e) {
        JsonWriter jsonWriter = reminderAppGUI.getJsonWriter();
        JFrame frame = reminderAppGUI.getFrame();
        try {
            jsonWriter.open();
            jsonWriter.write(reminderList);
            jsonWriter.close();
            SoundPlayer.playSound();
            JOptionPane.showMessageDialog(frame, "Save complete!");
            reminderAppGUI.getSaveChecker().setSaveStatus(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(frame, "Was not able to save");
        }
    }
}
