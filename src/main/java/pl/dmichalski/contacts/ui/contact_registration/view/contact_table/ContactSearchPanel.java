package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;


import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

public class ContactSearchPanel extends JPanel {

    private JTextField searchTF;

    public ContactSearchPanel() {
        initComponents();
    }

    private void initComponents() {
        JLabel nameAndSurnameLbl = new JLabel(Const.Labels.NAME_AND_SURNAME);
        add(nameAndSurnameLbl);

        searchTF = new JTextField();
        searchTF.setPreferredSize(new Dimension(220, 22));
        add(searchTF);
    }

    public JTextField getSearchTF() {
        return searchTF;
    }

}
