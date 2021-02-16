package ui.listeners;

import model.ReminderList;
import ui.DetailView;
import ui.ReminderAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsListener extends JFrame implements ActionListener {

    private JList list;
    private DefaultListModel listModel;
    private ReminderList reminderList;
    private ReminderAppGUI reminderAppGUI;

    // EFFECTS: create a DetailsListener and set fields
    public DetailsListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                           ReminderAppGUI reminderAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.reminderList = reminderList;
        this.reminderAppGUI = reminderAppGUI;
    }

    // EFFECTS: create a new JFrame titled "Reminder Details" and add in a DetailView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Reminder Details");
        JComponent newContentPane = new DetailView(list, listModel, reminderList, frame);
        newContentPane.setOpaque(true);
        reminderAppGUI.getSaveChecker().setSaveStatus(false);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
