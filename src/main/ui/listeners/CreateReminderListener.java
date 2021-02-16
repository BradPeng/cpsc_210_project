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

public class CreateReminderListener extends ReminderListenerSuperClass {

    // EFFECTS: create a CreateReminderListener and set fields
    public CreateReminderListener(JList list, DefaultListModel listModel, ReminderList reminderList,
                                  ReminderViewSuperClass reminderView, ReminderAppGUI reminderAppGUI) {
        super(list, listModel, reminderList, reminderView, reminderAppGUI);
    }

    // MODIFIES: reminderView, reminderList, listModel
    // EFFECTS: Try to create a new reminder based on text in the fields.
    //          If successful: - create a reminder and add it to reminderList and listModel.
    //                         - clear the text in the fields
    //                         - play a sound and create a popup to notify the user that the reminder was created.
    //          If unsuccessful: - Create a popup to notify the user that the date they input was invalid.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DateTime dt = DateFormatter.formatter.parseDateTime(reminderView.getDateString());
            Reminder r = new Reminder(reminderView.getTitle(), reminderView.getDescription(),
                    dt.getYear(),
                    dt.getMonthOfYear(),
                    dt.getDayOfMonth(),
                    dt.getHourOfDay(),
                    dt.getMinuteOfHour());

            reminderList.addReminder(r);
            listModel.addElement(r.getTitle());;
            SoundPlayer.playSound();
            clearFields();
            MainMenu m = (MainMenu) reminderAppGUI.getMainMenu();
            m.enableButtons();
            reminderAppGUI.getSaveChecker().setSaveStatus(false);
            JOptionPane.showMessageDialog(new JPanel(), "Reminder created!");
        } catch (IllegalFieldValueException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "Invalid date");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "Invalid date");
        }
    }

    // MODIFIES: reminderView
    // EFFECTS: removes the text from the JTextFields and JTextArea inside of reminderView
    private void clearFields() {
        reminderView.getTitleField().setText("");
        reminderView.getDateField().setText("");
        reminderView.getDescriptionTextArea().setText("");
    }
}
