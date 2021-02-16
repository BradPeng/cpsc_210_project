package ui.listeners;

import model.ReminderList;
import ui.ReminderAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {
    JList list;
    DefaultListModel listModel;
    JButton deleteButton;
    ReminderList reminderList;
    ReminderAppGUI reminderAppGUI;

    // EFFECTS: create a CreateReminderListener and set fields
    public DeleteListener(JList list, DefaultListModel listModel, JButton deleteButton, ReminderList reminderList,
                          ReminderAppGUI reminderAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.deleteButton = deleteButton;
        this.reminderList = reminderList;
        this.reminderAppGUI = reminderAppGUI;
    }

    // MODIFIES: listModel, reminderList, deleteButton
    // EFFECTS: remove the reminder from the listModel and reminderList at the currently selected index.
    //          Set the currently selected index to be one less than it was before the deletion
    //          If the list is empty after the deletion, disable the button.
    @Override
    public void actionPerformed(ActionEvent e) {

        int index = list.getSelectedIndex();
        listModel.remove(index);

        reminderList.removeAtIndex(index);
        reminderAppGUI.getSaveChecker().setSaveStatus(false);
        int size = listModel.getSize();

        if (size == 0) {
            deleteButton.setEnabled(false);

        } else {
            if (index == listModel.getSize()) {
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }
}
