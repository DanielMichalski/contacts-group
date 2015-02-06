package pl.dmichalski.contacts.ui.show_contact_tree.view.panel;

import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ContactButtonPanel extends JPanel {

    private JTextField groupTF;

    private JButton addBtn;

    private JButton contactManagementBtn;

    public ContactButtonPanel() {
        initComponents();
    }

    private void initComponents() {

        JLabel groupLbl = new JLabel(Const.Labels.GROUP);
        groupTF = new JTextField("");
        groupTF.setPreferredSize(new Dimension(120, 25));
        addBtn = new JButton(Const.ButtonLbls.BTN_GROUP_ADD);
        contactManagementBtn = new JButton(Const.ButtonLbls.BTN_CONTACTS);

        add(groupLbl);
        add(groupTF);
        add(addBtn);
        add(contactManagementBtn);
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JTextField getGroupTF() {
        return groupTF;
    }

    public JButton getContactManagementBtn() {
        return contactManagementBtn;
    }

}
