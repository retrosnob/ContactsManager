/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsmanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// This class takes care of reading and writing to the contacts.txt file.
// It makes sure that the contacts we have created get saved even when we
// quit the application.
public class FileHandler {

    final String FILE_PATH = "contacts.txt";
    BufferedReader reader;
    PrintWriter writer;

    public ArrayList<String> read() {
        // This just opens the file and reads each line into an ArrayList.
        // It is used by the model when the application is started so that 
        // it can preload the contacts list with our saved contacts. The model
        // is responsible for breaking up the delimited strings into separate
        // object field values.
        ArrayList<String> list = new ArrayList();
        try {
            // Reads stuff from the file and returns it as an array of strings
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // This is called by the model when any change has been made to the data.
    // The list parameter is prepared by the model and the FileHandler just 
    // writes it out to file.
    public void write(ArrayList<String> list) {
        try {
            // Writes the strings to file
            writer = new PrintWriter(new FileWriter(FILE_PATH));
            for (String item : list) {
                writer.write(item);
                writer.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }

}
