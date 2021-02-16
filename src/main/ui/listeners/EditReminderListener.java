package ui.listeners;

import model.Reminder;
import model.ReminderList;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import ui.MainMenu;
import ui.ReminderAppGUI;
import ui.helpers.DateFormatter;
import ui.ReminderViewSuperClass;
import ui.helpers.SoundPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditReminderListener extends ReminderListenerSuperClass {

    private MainMenu mainMenu;

    // EFFECTS: create a CreateReminderListener and set fields.
    public EditReminderListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                                ReminderViewSuperClass reminderView, ReminderAppGUI reminderAppGUI, MainMenu mainMenu) {
        super(list, listModel, reminderList, reminderView, reminderAppGUI);
        this.mainMenu = mainMenu;
    }

    // MODIFIES: reminderView, reminderList, listModel
    // EFFECTS: Try to create a new reminder based on text in the fields.
    //          If successful: - get the reminder at the current index and replace the fields of the reminder with
    //                           the fields of the NEW reminder
    //                         - play a sound and create a popup to notify the user that the reminder was created.
    //          If unsuccessful: - Create a popup to notify the user that the date they input was invalid.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DateTime dt = DateFormatter.formatter.parseDateTime(reminderView.getDateString());

            Reminder r = reminderList.getReminderAtIndex(list.getSelectedIndex());
            r.setTitle(reminderView.getTitle());
            r.setDescription(reminderView.getDescription());
            r.setDateTime(dt);

            SoundPlayer.playSound();
            reminderAppGUI.getSaveChecker().setSaveStatus(false);
            mainMenu.resetPanels(reminderList);
            JOptionPane.showMessageDialog(new JPanel(), "Reminder updated!");
        } catch (IllegalFieldValueException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "Invalid date");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "Invalid date");
        }
    }
}
