package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;


import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

public class ContactSearchPanel extends JPanel {

    private JTextField searchTF;

    private JButton searchBtn;

    public ContactSearchPanel() {
        initComponents();
    }

    private void initComponents() {
        JLabel nameAndSurnameLbl = new JLabel(Const.Labels.NAME_AND_SURNAME);
        add(nameAndSurnameLbl);

        searchTF = new JTextField();
        searchTF.setPreferredSize(new Dimension(220, 22));
        add(searchTF);

        searchBtn = new JButton(Const.Labels.FIND);
        add(searchBtn);
    }

    public JTextField getSearchTF() {
        return searchTF;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

}
