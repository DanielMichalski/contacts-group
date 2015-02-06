package pl.dmichalski.contacts.ui.show_contact_tree.view;

import pl.dmichalski.contacts.ui.show_contact_tree.view.panel.ContactButtonPanel;
import pl.dmichalski.contacts.ui.show_contact_tree.view.panel.ContactTreePanel;
import pl.dmichalski.contacts.utils.Const;
import pl.dmichalski.contacts.utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ContactsFrame extends JFrame {

    private ContactTreePanel contactTreePanel;

    private ContactButtonPanel contactButtonPanel;

    public static final int WIDTH = 450;

    public static final int HEIGHT = 400;

    public ContactsFrame() {
        setFrameUp();
        initializeComponents();
    }

    private void setFrameUp() {
        Utils.setWindowsLookAndFeel();
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle(Const.TitleFramesAndDlgs.SHOW_CONTACT_FRAME);
    }

    private void initializeComponents() {
        contactTreePanel = new ContactTreePanel();
        contactButtonPanel = new ContactButtonPanel();

        add(contactTreePanel, BorderLayout.CENTER);
        add(contactButtonPanel, BorderLayout.SOUTH);
    }

    public ContactTreePanel getContactTreePanel() {
        return contactTreePanel;
    }

    public ContactButtonPanel getContactButtonPanel() {
        return contactButtonPanel;
    }

}
