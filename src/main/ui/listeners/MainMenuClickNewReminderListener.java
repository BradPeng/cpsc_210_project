package ui.listeners;

import model.ReminderList;
import ui.NewReminderView;
import ui.ReminderAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuClickNewReminderListener extends JFrame implements ActionListener {

    private JList list;
    private DefaultListModel listModel;
    private ReminderList reminderList;
    private JFrame frame;
    private ReminderAppGUI reminderAppGUI;

    // EFFECTS: create a new MainMenuClickNewReminderListener and set fields.
    public MainMenuClickNewReminderListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                                            ReminderAppGUI reminderAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.reminderList = reminderList;
        this.reminderAppGUI = reminderAppGUI;
    }

    // EFFECTS: create a new JFrame titled "New Reminder" and add in a NewReminderView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("New Reminder");
        JComponent newContentPane = new NewReminderView(list, listModel, reminderList, reminderAppGUI);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
