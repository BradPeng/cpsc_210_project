package ui;

import model.ReminderList;
import model.SaveChecker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReminderAppGUI {
    private static final String JSON_STORE = "./data/reminders.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ReminderList reminderList;
    private JFrame frame;
    private SaveChecker saveChecker;
    private JComponent newContentPane;

    // MODIFIES: this;
    // EFFECTS: - create the ReminderAppGUI and set fields.
    //          - create the JFrame with title "Reminders"
    //          - create the content pane and add it to the frame
    //          - set frame properties
    public ReminderAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        reminderList = new ReminderList();
        saveChecker = new SaveChecker();

        frame = new JFrame("Reminders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newContentPane = new MainMenu(this);
        newContentPane.setOpaque(true);

        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setSize(new Dimension(990, 800));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        createCloseListener();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public ReminderList getReminderList() {
        return reminderList;
    }

    public SaveChecker getSaveChecker() {
        return saveChecker;
    }

    public JComponent getMainMenu() {
        return this.newContentPane;
    }

    // Method structured based on: https://stackoverflow.com/questions/15449022/show-prompt-before-closing-jframe
    // EFFECTS: create a listener that triggers when user tries to exit the application.
    //          When triggered:
    //              - If there are unsaved changes, create a popup asking if the user wants to exit without saving
    //                  - If they choose yes, exit application.
    //                  - If they choose no, return to the application
    //              - If there are no unsaved changes, just exit the application.
    public void createCloseListener() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                if (!saveChecker.getSaveStats()) {
                    int result = JOptionPane.showConfirmDialog(frame,
                            "You have unsaved changes, are you sure you want to exit without saving?",
                            "Unsaved Changes ",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else if (result == JOptionPane.NO_OPTION) {
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            }
        });
    }
}
