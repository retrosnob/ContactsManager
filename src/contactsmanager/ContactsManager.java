package contactsmanager;

import javax.swing.SwingUtilities;

public class ContactsManager {

    // This is the entry point for the application. We make a ContactsModel object
    // and then pass it into the new MainForm object. In that way, the MainForm
    // can make requests of the ContactsModel to Add, Edit, Delete, etc, on the 
    // basis of what the user is doing.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
            public void run() {
                new MainForm(new ContactsModel()).setVisible(true);
            }
        }
        );
    }
}
