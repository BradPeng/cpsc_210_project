package ui;

import model.ReminderList;

import javax.swing.*;
import java.awt.*;

// Class that will be extended by the popup menu that the edit, new and details functions will use.
public abstract class ReminderViewSuperClass extends JPanel {
    protected JList list;
    protected DefaultListModel listModel;
    protected ReminderList reminderList;

    protected JLabel titleLabel;
    protected JLabel descriptionLabel;
    protected JLabel dateLabel;

    protected JTextField titleField;
    protected JTextArea descriptionTextArea;
    protected JTextField dateField;

    protected JButton enterButton;

    protected JPanel titleSection;
    protected JPanel descriptionSection;
    protected JPanel dateSection;

    // MODIFIES: this
    // EFFECTS: - create the ReminderViewSuperClass and set the fields.
    //          - create the JLabels, JTextArea and the JTextField
    //          - Add labels and textboxes to the panel.
    protected ReminderViewSuperClass(JList list, DefaultListModel listModel, ReminderList reminderList) {
        this.list = list;
        this.listModel = listModel;
        this.reminderList = reminderList;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createLabels();
        createFields();

        add(titleSection);
        add(descriptionSection);
        add(dateSection);
    }

    // MODIFIES: this
    // EFFECTS: create the title, description and date labels that will be displayed. Set Font to Arial and size to 28
    private void createLabels() {
        titleLabel = new JLabel("Title: ");
        titleLabel.setFont(new Font("Arial",0, 28));

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("Arial",0, 28));

        dateLabel = new JLabel("Date: ");
        dateLabel.setFont(new Font("Arial",0, 28));
    }

    // MODIFIES: this
    // EFFECTS: create the fields for the title, description and date. Set font to Arial and size to 28.
    //          Set the TextArea to wrap text.
    //          Set up a grid to lay out the fields vertically
    //              - Each section will have its own panel, with each panel containing the label and the textbox
    //                next to each other horizontally
    //              - the 3 sections are laid out vertically to get the desired grid.
    private void createFields() {
        titleField = new JTextField(20);
        titleField.setFont(new Font("Arial",0, 28));

        descriptionTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        descriptionTextArea.setFont(new Font("Arial",0, 28));
        descriptionTextArea.setLineWrap(true);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial",0, 28));

        titleSection = new JPanel();
        titleSection.add(titleLabel);
        titleSection.add(titleField, c);

        descriptionSection = new JPanel();
        descriptionSection.add(descriptionLabel);
        descriptionSection.add(scrollPane, c);

        dateSection = new JPanel();
        dateSection.add(dateLabel);
        dateSection.add(dateField, c);
    }

    // MODIFIES: this
    // EFFECTS: create the button at the button of the frame.
    protected void createButton(String buttonString) {
        enterButton = new JButton(buttonString);
        enterButton.setActionCommand(buttonString);
    }

    public String getDateString() {
        return this.dateField.getText();
    }

    public String getTitle() {
        return this.titleField.getText();
    }

    public String getDescription() {
        return this.descriptionTextArea.getText();
    }

    public JTextField getTitleField() {
        return this.titleField;
    }

    public JTextArea getDescriptionTextArea() {
        return this.descriptionTextArea;
    }

    public JTextField getDateField() {
        return this.dateField;
    }
}
