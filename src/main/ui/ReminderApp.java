package ui;

import model.Reminder;
import model.ReminderList;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This class acts as the interface between the program and the user.
public class ReminderApp {

    private static final String JSON_STORE = "./data/reminders.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;
    private ReminderList reminderList;
    private String command = null;
    boolean hasBeenSaved = true;
    boolean applicationRunning = true;

    // EFFECTS: run the reminder application and create the JsonReader and JsonWriter to store and read data from
    //          JSON_STORE
    public ReminderApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runReminderApp();
    }

    // MODIFIES this;
    // EFFECTS: If the user enters "exit" command, end the application
    //          If the user has unsaved changes, warn them to save
    //          Otherwise, process the command they input
    private void runReminderApp() {
        init();
        loadReminders();

        while (applicationRunning) {
            displayMenu();
            command = input.next().toLowerCase();

            if (command.equals("exit")) {
                if (hasBeenSaved) {
                    applicationRunning = false;
                } else {
                    safeExitMenu();
                    String answer = input.next().toLowerCase();

                    if (answer.equals("y")) {
                        break;
                    } else if (answer.equals("se")) {
                        saveReminders();
                        break;
                    }
                }
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // EFFECTS: display menu options for exiting the application with unsaved changes
    private void safeExitMenu() {
        System.out.println("Changes have not been saved, are you sure you want to exit?");
        System.out.println("y -> exit without saving");
        System.out.println("n -> return to application");
        System.out.println(("se -> save and exit"));
    }

    // MODIFIES: this
    // EFFECTS: initializes empty ReminderList and Scanner
    private void init() {
        reminderList = new ReminderList();
        input = new Scanner(System.in);
    }

    // EFFECTS: prints out the initial menu options to console
    private void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("vr -> View reminders");
        System.out.println("nr -> Create new reminder");
        System.out.println("dr -> Delete existing reminder");
        System.out.println("er -> Edit existing reminder");
        System.out.println("s -> save reminders to file");
        System.out.println("l -> load reminders from file");
        System.out.println("exit -> Close application");
    }

    // MODIFIES: this
    // EFFECTS: run method correlated to user input. If user input is invalid, print a message to console.
    //          valid commands and their associated methods:
    //          vr -> printAllReminder
    //          nr -> newReminder
    //          dr -> deleteReminder
    //          er -> editReminder
    //           s -> saveReminder
    //           l -> loadReminders
    private void processCommand(String command) {
        if (command.equals("vr")) {
            printAllReminders(reminderList);
        } else if (command.equals("nr")) {
            newReminder();
        } else if (command.equals("dr")) {
            deleteReminder();
        } else if (command.equals("er")) {
            editReminder();
        } else if (command.equals("s")) {
            saveReminders();
        } else if (command.equals("l")) {
            loadReminders();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new reminder based on user input and adds the new reminder to reminderList
    //          If a valid reminder is created, set hasBeenSaved to false to indicate changes
    private void newReminder() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter title: ");
        String title = in.nextLine();

        System.out.println("Enter description: ");
        String description = in.nextLine();

        try {
            DateTime dt = createDateTime();
            reminderList.addReminder(new Reminder(title,
                    description,
                    dt.getYear(),
                    dt.getMonthOfYear(),
                    dt.getDayOfMonth(),
                    dt.getHourOfDay(),
                    dt.getMinuteOfHour()));

            System.out.println("New reminder created: ");
            hasBeenSaved = false;
            printReminder(reminderList.getReminderAtIndex((reminderList.getSize() - 1)));
        } catch (NumberFormatException e) {
            System.out.println(("That is not an integer input"));
        } catch (IllegalFieldValueException e) {
            System.out.println(("That is an invalid date"));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a Reminder from reminderList based on user input if it exists.
    //          continue asking for a valid reminder until a valid input is provided.
    //          If a valid reminder is deleted, set hasBeenSaved to false to indicate changes
    private void deleteReminder() {
        Scanner in = new Scanner(System.in);

        System.out.println("Which reminder number would you like to delete?: ");

        while (!in.hasNextInt()) {
            System.out.println("Invalid input, please try again");
            System.out.println("Which reminder number would you like to delete?: ");
            in.next();
        }

        int index = in.nextInt();

        if (index > 0 && index <= reminderList.getSize()) {
            reminderList.removeAtIndex(index - 1);
            hasBeenSaved = false;
        } else {
            System.out.println("That is not a valid reminder number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Edit a reminder based on user input.
    //          If the user enters an invalid reminder to edit, method ends
    //          If user enters valid input, ask for which field they want to change
    //          If the field is valid, change the field based on their next input
    //          If the field is not valid, ask the user to input a value again (loop)
    private void editReminder() {
        Scanner in = new Scanner(System.in);
        String f = "";
        System.out.println("Which reminder number would you like to edit?: ");

        while (!in.hasNextInt()) {
            System.out.println("Invalid input, please try again");
            System.out.println("Which reminder number would you like to edit?: ");
            in.next();
        }

        int index = in.nextInt();

        if (index > 0 && index <= reminderList.getSize()) {
            f = getFieldToEdit();

            if (f.equals("title")) {
                editField(reminderList.getReminderAtIndex(index - 1), "title");
            } else if (f.equals("description")) {
                editField(reminderList.getReminderAtIndex(index - 1), "description");

            } else if (f.equals("date")) {
                editField(reminderList.getReminderAtIndex(index - 1), "date");
            }

        } else {
            System.out.println("That is not a valid reminder number!");
        }
    }

    // EFFECTS: Asks user which field they want to edit.
    //          If the input is valid (one of: "title", "description", "date", "time"), return that value
    //          If the input is invalid, ask the user for another input (loop)
    private String getFieldToEdit() {
        Scanner in = new Scanner(System.in);
        String f = "";
        boolean validField = false;
        while (!validField) {
            System.out.println("What would you like to edit?: ");
            f = in.next();
            if (f.equals("title") || f.equals("description") || f.equals("date")) {
                validField = true;
            } else {
                System.out.println("That is not a valid field! ");
            }
        }
        return f;
    }

    // MODIFIES: r
    // EFFECTS: Asks user for an input and assigns field of r to the input
    //          If a reminder is changed legally, set hasBeenSaved to false to indicate changes
    private void editField(Reminder r, String field) {
        Scanner in = new Scanner(System.in);
        if (field == "date") {
            try {
                DateTime dt = createDateTime();
                r.setDateTime(dt);
            } catch (NumberFormatException e) {
                System.out.println(("That is not an integer input"));
            } catch (IllegalFieldValueException e) {
                System.out.println(("That is an invalid date"));
            }
        } else {
            System.out.println("Enter new " + field + " :");
            String newInput = in.nextLine();
            if (field == "title") {
                r.setTitle(newInput);
            } else if (field == "description") {
                r.setDescription(newInput);
            }
            hasBeenSaved = false;
        }
    }

    // EFFECTS: opens the JSON file and saves the reminders in reminderList to file, then close file.
    private void saveReminders() {
        try {
            jsonWriter.open();
            jsonWriter.write(reminderList);
            jsonWriter.close();
            System.out.println("Saved reminders to " + JSON_STORE);
            hasBeenSaved = true;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Load reminders from JSON file
    private void loadReminders() {
        try {
            reminderList = jsonReader.read();
            System.out.println("Loaded reminders from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Get user input of DateTime fields and create a DateTime object with those inputs.
    //          throws NumberFormatException if a non-number is input
    //          throws IllegalFieldValueException if one of the inputs violates the date/time restriction
    //                 e.g. entering 25 for month would throw IllegalFieldException
    private DateTime createDateTime() throws NumberFormatException, IllegalFieldValueException {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter year in format 'yyyy' ");
        int year = Integer.parseInt(in.nextLine());

        System.out.println("Enter month in format 'mm': ");
        int month = Integer.parseInt(in.nextLine());

        System.out.println("Enter day in format 'dd': ");
        int day = Integer.parseInt(in.nextLine());

        System.out.println("Enter hour in format 'hh': ");
        int hour = Integer.parseInt(in.nextLine());

        System.out.println("Enter minute in format 'mm': ");
        int min = Integer.parseInt(in.nextLine());

        return new DateTime(year, month, day, hour, min);
    }

    // EFFECTS: Prints the following information about Reminder r:
    //          - the title of the reminder
    //          - the description of the reminder
    //          - the date of the reminder
    //          - the time of the reminder
    //          - blank line (for readability)
    private void printReminder(Reminder r) {
        System.out.println("Title: " + r.getTitle());
        System.out.println("Description: " + r.getDescription());
        System.out.println("Date: " + Reminder.DATE_FORMAT.print(r.getDateTime()));
        System.out.println("");
    }

    // EFFECTS: Prints the following information about every Reminder in reminderList:
    //          - The number of the reminder, which is its index position + 1
    //          - the title of the reminder
    //          - the description of the reminder
    //          - the date of the reminder
    //          - the time of the reminder
    //          - blank line (for readability)
    private void printAllReminders(ReminderList rl) {
        for (int i = 0; i < rl.getSize(); i++) {
            System.out.println(("Reminder Number: " + (i + 1)));
            System.out.println("Title: " + rl.getReminderAtIndex(i).getTitle());
            System.out.println("Description: " + rl.getReminderAtIndex(i).getDescription());
            System.out.println("date: " + Reminder.DATE_FORMAT.print(rl.getReminderAtIndex(i).getDateTime()));
            System.out.println("");
        }
    }
}
