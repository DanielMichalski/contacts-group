package pl.dmichalski.contacts.ui.contact_registration.view.contact_data;


import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;

public class DataButtonPanel extends JPanel {

    private JButton saveBtn;

    private JButton cancelBtn;

    public DataButtonPanel() {
        initComponents();
    }

    private void initComponents() {
        saveBtn = new JButton(Const.ButtonLbls.BTN_SAVE);
        add(saveBtn);

        cancelBtn = new JButton(Const.ButtonLbls.BTN_CANCEL);
        add(cancelBtn);
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }
}
