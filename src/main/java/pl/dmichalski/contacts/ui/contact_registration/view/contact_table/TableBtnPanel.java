package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;

import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;

public class TableBtnPanel extends JPanel {

    private JButton deleteContactBtn;

    public TableBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        deleteContactBtn = new JButton(Const.ButtonLbls.BTN_DELETE);
        add(deleteContactBtn);
    }

    public JButton getDeleteContactBtn() {
        return deleteContactBtn;
    }
}
