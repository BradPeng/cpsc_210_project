package ui;

import model.ReminderList;
import model.SaveChecker;
import persistence.JsonReader;
import ui.listeners.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainMenu extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private JPanel buttonPane;
    private JFrame frame;

    ReminderList reminderList;
    private SaveChecker saveChecker;

    private JsonReader jsonReader;

    private JButton deleteButton;
    private static final String DELETE_STRING = "Delete";
    private DeleteListener deleteListener;

    private JButton detailsButton;
    private static final String DETAILS_STRING = "Details";
    private DetailsListener detailsListener;

    private JButton newReminderButton;
    private static final String NEW_REMINDER_STRING = "New Reminder";
    private MainMenuClickNewReminderListener newReminderListener;

    private JButton editReminderButton;
    private static final String EDIT_REMINDER_STRING = "Edit Reminder";
    private MainMenuClickEditReminderListener editReminderListener;

    private JButton loadButton;
    private static final String LOAD_STRING = "Load From File";
    private LoadListener loadListener;

    private JButton saveButton;
    private static final String SAVE_STRING = "Save";
    private SaveListener saveListener;

    JScrollPane listScrollPane;
    private  ReminderAppGUI reminderAppGUI;



    // EFFECTS: create MainMenu and set fields
    public MainMenu(ReminderAppGUI reminderAppGUI) {
        super(new BorderLayout());
        this.reminderAppGUI = reminderAppGUI;
        this.jsonReader = reminderAppGUI.getJsonReader();
        this.reminderList = reminderAppGUI.getReminderList();
        this.saveChecker = reminderAppGUI.getSaveChecker();
        this.frame = reminderAppGUI.getFrame();

        init();
        disableButtons();
    }

    // MODIFIES: this, reminderAppGUI
    // EFFECTS: - create new DefaultListModel and JList
    //          - loop through reminders and assign the title of each reminder to the listModel
    //          - set selected index of list to 0
    //          - create button pane and assign it:
    //              - createNewReminderButton
    //              - createDetailsButton
    //              - createEditReminderButton
    //              - createDeleteButton
    //              - createLoadButton
    //              - createSaveButton
    //          - create a JScrollPane and populate it JScrollPane the list
    //          - add both the JScrollPane and the buttonPanel to the frame
    public void init() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setFont(new Font("Arial",0, 28));

        for (int i = 0; i < reminderList.getSize(); i++) {
            listModel.addElement(reminderList.getReminderAtIndex(i).getTitle());
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        createNewReminderButton();
        createDetailsButton();
        createEditReminderButton();

        createDeleteButton();
        createLoadButton();
        createSaveButton();

        listScrollPane = new JScrollPane(list);
        checkButtonStatus();
        add(listScrollPane, BorderLayout.CENTER);

        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this, reminderAppGUI
    // EFFECTS: when called, re-instantiate listModel and list. Remove the JScrollPane and the buttonPane.
    //          call init() to re-add all of these fields.
    public void resetPanels(ReminderList reminderList) {
        this.reminderList = reminderList;
        listModel = new DefaultListModel();
        list = new JList(listModel);
        revalidate();
        remove(listScrollPane);
        remove(buttonPane);
        saveChecker.setSaveStatus(false);
        init();
    }

    // MODIFIES: this
    // EFFECTS: create the deleteButton, assign it the deleteListener and add it to the buttonPane.
    private void createDeleteButton() {
        deleteButton = new JButton(DELETE_STRING);
        deleteButton.setFont(new Font("Arial",0, 28));
        deleteButton.setActionCommand(DELETE_STRING);
        deleteListener = new DeleteListener(list, listModel, deleteButton, reminderList, reminderAppGUI);
        deleteButton.addActionListener(deleteListener);
        buttonPane.add(deleteButton);
    }

    // MODIFIES: this
    // EFFECTS: create the detailsButton, assign it the DetailsListener and add it to the buttonPane.
    private void createDetailsButton() {
        detailsButton = new JButton(DETAILS_STRING);
        detailsButton.setActionCommand(DETAILS_STRING);
        detailsButton.setFont(new Font("Arial",0, 28));
        detailsListener = new DetailsListener(list, listModel, reminderList, reminderAppGUI);
        detailsButton.addActionListener(detailsListener);
        buttonPane.add(detailsButton);
    }

    // MODIFIES: this
    // EFFECTS: create the newReminderButton, assign it the MainMenuClickNewReminderListener
    //          and add it to the buttonPane.
    private void createNewReminderButton() {
        newReminderButton = new JButton(NEW_REMINDER_STRING);
        newReminderButton.setFont(new Font("Arial",0, 28));
        newReminderButton.setActionCommand(NEW_REMINDER_STRING);
        newReminderListener = new MainMenuClickNewReminderListener(list, listModel, reminderList,
                reminderAppGUI);
        newReminderButton.addActionListener(newReminderListener);
        buttonPane.add(newReminderButton);
    }

    // MODIFIES: this
    // EFFECTS: create the editReminderButton, assign it the MainMenuClickEditReminderListener
    //          and add it to the buttonPane.
    private void createEditReminderButton() {
        editReminderButton = new JButton(EDIT_REMINDER_STRING);
        editReminderButton.setFont(new Font("Arial",0, 28));
        editReminderButton.setActionCommand(EDIT_REMINDER_STRING);
        editReminderListener = new MainMenuClickEditReminderListener(list, listModel, reminderList,
                reminderAppGUI, this);
        editReminderButton.addActionListener(editReminderListener);
        buttonPane.add(editReminderButton);
    }

    // MODIFIES: this
    // EFFECTS: create the loadButton, assign it the LoadListener and add it to the buttonPane.
    private void createLoadButton() {
        loadButton = new JButton(LOAD_STRING);
        loadButton.setFont(new Font("Arial",0, 28));
        loadButton.setActionCommand(LOAD_STRING);
        loadListener = new LoadListener(jsonReader, frame, reminderList, this);
        loadButton.addActionListener(loadListener);
        buttonPane.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: create the saveButton, assign it the SaveListener and add it to the buttonPane.
    private void createSaveButton() {
        saveButton = new JButton(SAVE_STRING);
        saveButton.setFont(new Font("Arial",0, 28));
        saveButton.setActionCommand(SAVE_STRING);
        saveListener = new SaveListener(reminderList, reminderAppGUI);
        saveButton.addActionListener(saveListener);
        buttonPane.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: when the value of the listModel is updated, check to see if the listModel is empty.
    //          If listModel is empty: disable the edit, delete and details button
    //          Otherwise, do nothing.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        checkButtonStatus();
        enableButtons();
    }


    private void checkButtonStatus() {
        if (listModel.isEmpty()) {
            disableButtons();
        }
    }

    // MODIFIES: this
    // EFFECTS: disables the details, delete and edit reminder buttons.
    private void disableButtons() {
        detailsButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editReminderButton.setEnabled(false);
    }

    public void enableButtons() {
        detailsButton.setEnabled(true);
        deleteButton.setEnabled(true);
        editReminderButton.setEnabled(true);
        list.setSelectedIndex(0);
    }
}
