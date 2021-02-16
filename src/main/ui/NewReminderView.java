package ui;

import model.ReminderList;
import ui.listeners.CreateReminderListener;

import javax.swing.*;
import java.awt.*;

// Class that represents the menu that displays when the user is creating a new reminder
public class NewReminderView extends ReminderViewSuperClass {
    private CreateReminderListener createReminderListener;

    // MODIFIES: reminderAppGUI
    // EFFECTS: create the NewReminderView and set fields. Also create the "Create Reminder" button that will create
    //          the reminder (assign this button the CreateReminderListener). Add the button to the pane.
    public NewReminderView(JList list, DefaultListModel listModel, ReminderList reminderList,
                           ReminderAppGUI reminderAppGUI) {
        super(list, listModel, reminderList);

        createButton("Create Reminder");
        createReminderListener = new CreateReminderListener(list, listModel, reminderList, this,
                reminderAppGUI);
        enterButton.addActionListener(createReminderListener);
        enterButton.setFont(new Font("Arial",0, 28));

        add(enterButton);
    }
}
