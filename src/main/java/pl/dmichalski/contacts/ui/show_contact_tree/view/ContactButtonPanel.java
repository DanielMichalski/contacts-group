package pl.dmichalski.contacts.ui.show_contact_tree.view;

import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class ContactButtonPanel extends JPanel {

    private JButton contactManagementBtn;

    public ContactButtonPanel() {
        initComponents();
    }

    private void initComponents() {
        contactManagementBtn = new JButton(Const.ButtonLbls.BTN_CONTACTS);
        add(contactManagementBtn);
    }

    public JButton getContactManagementBtn() {
        return contactManagementBtn;
    }
}
