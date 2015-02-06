package pl.dmichalski.contacts;

import pl.dmichalski.contacts.ui.show_contact_tree.controller.ShowContactTreeController;
import pl.dmichalski.contacts.ui.show_contact_tree.view.ContactsFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class Runner {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ContactsFrame frame = new ContactsFrame();
            new ShowContactTreeController(frame);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
//
//            RegistrationFrame frame = new RegistrationFrame();
//            new RegistrationController(frame);
//            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            frame.setVisible(true);
        });
    }

}
