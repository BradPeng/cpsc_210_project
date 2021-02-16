package ui.listeners;

import model.ReminderList;
import ui.ReminderAppGUI;
import ui.ReminderViewSuperClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ReminderListenerSuperClass implements ActionListener {
    protected JList list;
    protected DefaultListModel listModel;
    protected ReminderList reminderList;
    protected JFrame frame;
    protected ReminderAppGUI reminderAppGUI;

    protected ReminderViewSuperClass reminderView;

    // EFFECTS: Create a new ReminderListenerSupercClass and set fields
    public ReminderListenerSuperClass(JList list, DefaultListModel listModel, ReminderList reminderList,
                                      ReminderViewSuperClass reminderView, ReminderAppGUI reminderAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.reminderList = reminderList;
        this.reminderView = reminderView;
        this.reminderAppGUI = reminderAppGUI;
    }

    // EFFECTS: None
    @Override
    public abstract void actionPerformed(ActionEvent e);
}
