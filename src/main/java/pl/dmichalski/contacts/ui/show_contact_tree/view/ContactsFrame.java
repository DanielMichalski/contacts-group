package pl.dmichalski.contacts.ui.show_contact_tree.view;

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

    private GroupRightPanel groupRightPanel;

    public static final int WIDTH = 500;

    public static final int HEIGHT = 400;

    public ContactsFrame() {
        setFrameUp();
        initializeComponents();
    }

    private void setFrameUp() {
        Utils.setWindowsLookAndFeel();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle(Const.TitleFramesAndDlgs.SHOW_CONTACT_FRAME);
    }

    private void initializeComponents() {
        contactTreePanel = new ContactTreePanel();
        contactButtonPanel = new ContactButtonPanel();
        groupRightPanel = new GroupRightPanel();

        add(contactTreePanel, BorderLayout.CENTER);
        add(contactButtonPanel, BorderLayout.SOUTH);
        add(groupRightPanel, BorderLayout.EAST);
    }

    public ContactTreePanel getContactTreePanel() {
        return contactTreePanel;
    }

    public ContactButtonPanel getContactButtonPanel() {
        return contactButtonPanel;
    }

    public GroupRightPanel getGroupRightPanel() {
        return groupRightPanel;
    }
}
