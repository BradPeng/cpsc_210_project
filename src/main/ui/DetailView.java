package ui;

import model.ReminderList;
import org.joda.time.DateTime;
import ui.helpers.DateFormatter;
import ui.listeners.CloseDetailViewListener;

import javax.swing.*;
import java.awt.*;

// Frame that shows the details of the reminder.
public class DetailView extends ReminderViewSuperClass {

    private CloseDetailViewListener closeDetailViewListener;

    // EFFECTS: Create a DetailView and set fields. JTextAreas and JTextFields are set to be uneditable.
    public DetailView(JList list, DefaultListModel listModel, ReminderList reminderList, JFrame detailsListener) {
        super(list, listModel, reminderList);
        this.titleField.setText(reminderList.getReminderAtIndex(list.getSelectedIndex()).getTitle());
        this.descriptionTextArea.setText(reminderList.getReminderAtIndex(list.getSelectedIndex()).getDescription());

        DateTime dt = reminderList.getReminderAtIndex(list.getSelectedIndex()).getDateTime();
        this.dateField.setText(dt.toString(DateFormatter.formatter));

        createButton("Close");
        enterButton.setFont(new Font("Arial",0, 28));
        closeDetailViewListener = new CloseDetailViewListener(list, listModel, reminderList, this,
                null, detailsListener);
        enterButton.addActionListener(closeDetailViewListener);

        add(enterButton);

        titleField.setEditable(false);
        descriptionTextArea.setEditable(false);
        dateField.setEditable(false);
    }
}