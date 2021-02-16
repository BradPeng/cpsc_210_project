package ui;

import model.ReminderList;
import org.joda.time.DateTime;
import ui.helpers.DateFormatter;
import ui.listeners.EditReminderListener;

import javax.swing.*;
import java.awt.*;

// Frame that allows users to edit an existing reminder
public class EditReminderView extends ReminderViewSuperClass {
    private EditReminderListener editReminderListener;

    // EFFECTS: Create a DetailView and set fields. Create the submit button that saves the changes to the reminder.
    public EditReminderView(JList list, DefaultListModel listModel, ReminderList reminderList,
                            ReminderAppGUI reminderAppGUI, MainMenu mainMenu) {
        super(list, listModel, reminderList);
        this.titleField.setText(reminderList.getReminderAtIndex(list.getSelectedIndex()).getTitle());
        this.descriptionTextArea.setText(reminderList.getReminderAtIndex(list.getSelectedIndex()).getDescription());

        DateTime dt = reminderList.getReminderAtIndex(list.getSelectedIndex()).getDateTime();
        this.dateField.setText(dt.toString(DateFormatter.formatter));

        createButton("Save Changes");
        enterButton.setFont(new Font("Arial",0, 28));
        editReminderListener = new EditReminderListener(list, listModel, reminderList, this,
                reminderAppGUI, mainMenu);
        enterButton.addActionListener(editReminderListener);
        add(enterButton);
    }
}
