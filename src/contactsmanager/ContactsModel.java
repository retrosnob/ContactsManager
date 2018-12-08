package contactsmanager;

import java.util.ArrayList;
import java.util.ListIterator;


// This class implements the logic of the contact management application. It has nothing
// to do with the user interface; it just provides methods to add, edit and delete contacts
// and stores them in an ArrayList object.
public class ContactsModel {

    // This is where the contacts are stored
    ArrayList<Contact> contacts = new ArrayList(100);

    // This object reads contacts from the file when the model is instantiated.
    // Every time a contact is added, edited or deleted, this object re-writes the file.
    FileHandler fileHandler = new FileHandler();

    // This is the constructor. It asks the file handler for the contents of the contacts.txt
    // file and uses them to populate the ArrayList of contacts.
    public ContactsModel() {
        ArrayList<String> strings = fileHandler.read();
        for (String s : strings) {
            String[] fields = s.split("#");
            Contact c = new Contact();
            c.setFirstName(fields[0]);
            c.setLastName(fields[1]);
            c.setPhone(fields[2]);
            c.setEmail(fields[3]);
            contacts.add(c);
        }
    }
    
    // Called by the MainForm when the user wants to add a contact.
    void addContact(
            String firstName,
            String lastName,
            String phone,
            String email) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setEmail(email);
        contacts.add(contact);
        fileHandler.write(getDelimitedContactsStrings());
    }

    // Called by the MainForm when the user wants to delete a contact.
    boolean deleteContact(int index) {
        // The value of index specifies which contact should be deleted. It is sent by 
        // the MainForm using the JList.getSelectedIndex() method. I just do a little 
        // check to make sure that it's within an acceptable range.
        if (index > -1 && index < contacts.size()) {
            contacts.remove(index);
            fileHandler.write(getDelimitedContactsStrings());
            return true;
        } else {
            // The MainForm can use this value to tell that something went wrong.
            return false;
        }
    }

    // Called by the MainForm when the user wants to edit a contact. The values passed
    // by the MainForm specify which contact is to be changed (index) and what the 
    // new field values are.
    boolean editContact(
            int index, String firstName,
            String lastName,
            String phone,
            String email) {
        Contact c = contacts. get(index);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setPhone(phone);
        c.setEmail(email);
        fileHandler.write(getDelimitedContactsStrings());
        return true;
    }

    // This is called by the MainForm to update the values in the JList. The JList
    // wants an array of String and will convert the ArrayList to an array.
    ArrayList<String> getContactsListStrings() {
        ArrayList<String> strings = new ArrayList(contacts.size());
        for (Contact c : contacts) {
            strings.add(c.toString());
        }
        return strings;
    }

    // This is called by the FileHandler to write the contacts to the contacts.txt
    // file. It returns an ArrayList of the contacts delimited strings, which you 
    // can find out about in the Contact class.
    ArrayList<String> getDelimitedContactsStrings() {
        ArrayList<String> strings = new ArrayList(contacts.size());
        for (Contact c : contacts) {
            strings.add(c.getDelimitedString("#"));
        }
        return strings;
    }
    
    // This is used by the MainForm and the AddEditForm to display the field values
    // of a selected contact.
    String[] getContactFields(int selected) {
        if (selected > -1) {
            Contact c = contacts.get(selected);
            String[] fields = new String[4];
            fields[0] = c.getFirstName();
            fields[1] = c.getLastName();
            fields[2] = c.getPhone();
            fields[3] = c.getEmail();
            return fields;
        } else {
            return null;
        }
    }    
}
