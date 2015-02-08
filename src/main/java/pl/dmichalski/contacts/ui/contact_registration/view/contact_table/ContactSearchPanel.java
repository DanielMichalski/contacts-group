package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;


import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

public class ContactSearchPanel extends JPanel {

    private JTextField searchSurnameTF;

    private JTextField searchAddressTF;

    private JRadioButton allContactsRadioBtn;

    private JRadioButton privateContactsRadioBtn;

    private JRadioButton businessContactsRadioBtn;

    public ContactSearchPanel() {
        initComponents();
    }

    private void initComponents() {
        JLabel nameAndSurnameLbl = new JLabel(Const.Labels.SURNAME);
        searchSurnameTF = new JTextField();
        searchSurnameTF.setPreferredSize(new Dimension(120, 22));

        JLabel addressLbl = new JLabel(Const.Labels.ADDRESS);
        searchAddressTF = new JTextField();
        searchAddressTF.setPreferredSize(new Dimension(120, 22));

        ButtonGroup contactsBtnGroup = new ButtonGroup();
        allContactsRadioBtn = new JRadioButton(Const.ButtonLbls.ALL_CONTACTS, true);
        privateContactsRadioBtn = new JRadioButton(Const.ButtonLbls.PRIVATE_CONTACTS);
        businessContactsRadioBtn = new JRadioButton(Const.ButtonLbls.BUSINESS_CONTACTS);

        contactsBtnGroup.add(allContactsRadioBtn);
        contactsBtnGroup.add(privateContactsRadioBtn);
        contactsBtnGroup.add(businessContactsRadioBtn);

        add(nameAndSurnameLbl);
        add(searchSurnameTF);
        add(addressLbl);
        add(searchAddressTF);
        add(allContactsRadioBtn);
        add(privateContactsRadioBtn);
        add(businessContactsRadioBtn);
    }

    public JTextField getSearchSurnameTF() {
        return searchSurnameTF;
    }

    public JTextField getSearchAddressTF() {
        return searchAddressTF;
    }

    public JRadioButton getAllContactsRadioBtn() {
        return allContactsRadioBtn;
    }

    public JRadioButton getPrivateContactsRadioBtn() {
        return privateContactsRadioBtn;
    }

    public JRadioButton getBusinessContactsRadioBtn() {
        return businessContactsRadioBtn;
    }
}
