# Brad Peng - CPSC 210 Personal Project
Project worked on September 2020 - December 2020
## Reminder Application

**Overview**
<br />
This is a simple desktop application that allows users to create reminders for themselves.
Users can create reminders to for tasks, and will be notified when the time of the task is reached.
The user can choose to either have the reminder set at a specific date or time, 
or have the reminder send an alert after a specified period. 
The user also has the option to set multiple alerts for each event, so that they could, 
for example, be alerted 5 minutes *before* the event, as well as when the event *starts*.
Reminders can also be set to repeat, for *recurring events*.


**Features**
<br />
A summary of the features of the app:
- Create reminders and be notified at a user-chosen time
- Set reminders to repeat for *recurring events*
- Have the option to have multiple alerts for a single reminder, such as 
a reminder 5 minutes *before* the event, as well as when the event *begins*

**User Base**
<br />
This application can be used by anyone who is looking to manage their schedule better. 
With a intuitive and simple UI, this application is easily *accessible* to those who could benefit from it.

**Reasons for Pursuing this Project**
<br />
The overall purpose of this project is to *learn*. I chose to make a reminder app to enable my learning 
because it is something that has been done multiple times before. 
By creating a product that already exists, I can focus more of the computer science aspects of the project, 
rather than the design aspects. This project is also of interest because it covers many aspects of software design 
that I believe are fundamental to many types of modern applications, such as the ability to save and retrieve data,
interact with the user in meaningful ways, and execute tasks at specific times. As a bonus to completing this project,
I have an application that is beneficial to me!

**Currently Implemented User Stories**
- As a user, I want to be able to add a reminder to my list of reminders
- As a user, I want to be able to remove a reminder from my list of reminders
- As a user, I want to be able to change the details an existing reminder in my list of reminders
- As a user, I want to be able to view all the reminders in my list of reminders
- As a user, I want to be able to save my list of reminders on my local device
- As a user, I want to be able to load up my save reminders from a file when the application opens
- As a user, I want to be warned if I try to exit the application without saving after making changes

**Phase 4: Task 2**
There are 2 instances of type hierarchies in the code. The first superclass is the ReminderListenerSuperClass
which is subclassed by the CreateReminderListener, CloseDetailViewListener and the EditReminderListener classes.
The CreateReminderListener and EditReminderListener only vary slightly. The main difference is that
the CreatReminderListener adds a new reminder to the end of the DefaultListModel and the ReminderList, while the 
EditReminderListener removes the current Reminder at the selected index, and adds a new Reminder at that index to 
simulate updating the Reminder. The notable subclass behaviour for this task is between the 
EditReminderListener/CreateReminderListener and the CloseDetailViewListener. While the
EditReminderListener/CreateReminderListener subclasses focus on modifying the list of reminders,
the CloseDetailViewListener simply closes the DetailView frame.

The type hierarchy noted above is important because of the second type hierarchy used in this program.
The superclass is the ReminderViewSuperClass, which is subclassed by the DetailView, EditReminderView, and the
NewReminderView. Once again, the differences between the EditReminderView and the NewReminderView are rather 
small, where the EditReminderView will display up Reminder information from the selected index, while the 
NewReminderView displays blank fields. The main difference is between the EditReminderView/NewReminderView
and the DetailView. The detail view requires displays information of the Reminder at the selected index
and makes the fields non-editable, so it requires all the fields of the ReminderViewSuperClass. However,
in order to incorporate the closing behaviour mentioned in the above type hierarchy, additional fields 
need to be passed in, in addition to the fields used to display information. 

The combination of the two type hierarchy described allow the classes focused on editing/creating 
Reminders and the classes
focused on displaying reminder details perform different tasks with the same fields while removing
a lot of code duplication.

**Phase 4: Task 3**
Just by looking at the UML class diagram I drew, it is obvious that there is far too much coupling in my system.
This problem occurred because I did not properly plan out each of my classes beforehand, and thus I did not know
what each class depended on. Because of this, I was constantly passing in fields into classes whenever I reealized
a method needed access to a specific object. This meant I was constantly passing around multiple references from 
object to object, often inconsistently. 

I could remedy this issue by first re-drawing the UML class diagram. With my knowledge now on what fields each 
class needs access too, I would create a class dedicated to holding all of the information that
 needed to be passed around. This class would contain fields such as JsonWriter, ReminderList, JFrame, List and so
 on. My MainMenu class would have a field of this new class, and each time we go down a layer on the current diagram,
 I would pass along just the class that holds all of the references. Doing this one simple change would remove
 a large portion the association arrows present on the Listener classes, and heavily reduce coupling within the system.
 
 
