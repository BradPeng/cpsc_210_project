package ui.listeners;

import model.ReminderList;
import ui.EditReminderView;
import ui.MainMenu;
import ui.ReminderApp;
import ui.ReminderAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuClickEditReminderListener extends JFrame implements ActionListener {
    private JList list;
    private DefaultListModel listModel;
    private ReminderList reminderList;
    private JFrame frame;
    private ReminderAppGUI reminderAppGUI;
    private MainMenu mainMenu;

    // EFFECTS: create a new MainMenuClickEditReminderListener and set fields.
    public MainMenuClickEditReminderListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                                             ReminderAppGUI reminderAppGUI, MainMenu mainMenu) {
        this.list = list;
        this.listModel = listModel;
        this.reminderList = reminderList;
        this.reminderAppGUI = reminderAppGUI;
        this.mainMenu = mainMenu;
    }

    // EFFECTS: create a new JFrame titled "Edit Reminder" and add in a EditReminderView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("Edit Reminder");
        JComponent newContentPane = new EditReminderView(list, listModel, reminderList, reminderAppGUI, mainMenu);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
