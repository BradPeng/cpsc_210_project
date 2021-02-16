package ui.listeners;

import model.ReminderList;
import ui.ReminderAppGUI;
import ui.ReminderViewSuperClass;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloseDetailViewListener extends ReminderListenerSuperClass {
    private JFrame frame;

    public CloseDetailViewListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                                   ReminderViewSuperClass reminderView, ReminderAppGUI reminderAppGUI, JFrame frame) {
        super(list, listModel, reminderList, reminderView, reminderAppGUI);
        this.frame = frame;
    }

    // Close the frame on button press.
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
