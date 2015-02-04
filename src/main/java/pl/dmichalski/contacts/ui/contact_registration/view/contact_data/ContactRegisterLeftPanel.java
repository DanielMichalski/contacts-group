package pl.dmichalski.contacts.ui.contact_registration.view.contact_data;

import javax.swing.*;
import java.awt.*;

public class ContactRegisterLeftPanel extends JPanel {

    private FormPanel formPanel;

    private DataButtonPanel buttonPanel;

    public ContactRegisterLeftPanel() {
        setUpPanel();
        initComponents();
    }

    private void setUpPanel() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        formPanel = new FormPanel();
        add(formPanel, BorderLayout.CENTER);

        buttonPanel = new DataButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public DataButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
