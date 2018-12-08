/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsmanager;

// This class represents a single contact.
public class Contact {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    // This just provides a string representation of the contact. It is used to 
    // display the contacts in the JList of the MainForm.
    public String toString() {
        return lastName + ", " + firstName;
    }

    // This concatenates the contact's field values with the provided delimiter string 
    // and returns the result. It is used to proved the FileHandler object with 
    // strings it can write to file.
    public String getDelimitedString(String delimiter) {
        return this.firstName + delimiter + this.lastName  + delimiter + this.phone + delimiter + this.email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
}
