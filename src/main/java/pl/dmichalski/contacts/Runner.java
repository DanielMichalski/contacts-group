package pl.dmichalski.contacts;

import pl.dmichalski.contacts.ui.contact_registration.controller.RegistrationController;
import pl.dmichalski.contacts.ui.contact_registration.view.RegistrationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class Runner {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RegistrationFrame frame = new RegistrationFrame();
            new RegistrationController(frame);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
