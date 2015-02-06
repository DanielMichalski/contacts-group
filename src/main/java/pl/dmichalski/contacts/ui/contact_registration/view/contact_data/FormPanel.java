package pl.dmichalski.contacts.ui.contact_registration.view.contact_data;

import pl.dmichalski.contacts.model.*;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FormPanel extends JPanel {

    private JTextField nameTF;

    private JTextField surnameTF;

    private JRadioButton privateContactTypeRB;

    private JRadioButton businessContactTypeRB;

    private JTextField phoneNumberTF;

    private JTextField addressTF;

    private JComboBox<ContactGroup> groupComboBox;

    public FormPanel() {
        setUpPanel();
        initComponents();
    }

    private void setUpPanel() {
        GridLayout layout = new GridLayout(7, 2);
        layout.setVgap(15);
        setLayout(layout);
        setBorder(new TitledBorder(Const.Labels.CONTACT_FORM));
    }

    private void initComponents() {
        JLabel nameLbl = new JLabel(Const.Labels.NAME);
        nameTF = new JTextField();

        JLabel surnameLbl = new JLabel(Const.Labels.SURNAME);
        surnameTF = new JTextField();

        JPanel radioBtnPanel = new JPanel();
        JLabel clientTypeLbl = new JLabel(Const.Labels.CLIENT_TYPE);
        ButtonGroup radioBtnGroup = new ButtonGroup();

        privateContactTypeRB = new JRadioButton(Const.Labels.PRIVATE);
        businessContactTypeRB = new JRadioButton(Const.Labels.BUSINESS);
        radioBtnGroup.add(privateContactTypeRB);
        radioBtnGroup.add(businessContactTypeRB);
        privateContactTypeRB.setSelected(true);

        radioBtnPanel.add(privateContactTypeRB);
        radioBtnPanel.add(businessContactTypeRB);

        JLabel phoneNumberLbl = new JLabel(Const.Labels.PHONE_NUMBER);
        phoneNumberTF = new JTextField();

        JLabel addressLbl = new JLabel(Const.Labels.ADDRESS);
        addressTF = new JTextField();

        JLabel groupLbl = new JLabel(Const.Labels.GROUP);
        groupComboBox = new JComboBox();

        add(nameLbl);
        add(nameTF);

        add(surnameLbl);
        add(surnameTF);

        add(clientTypeLbl);
        add(radioBtnPanel);

        add(phoneNumberLbl);
        add(phoneNumberTF);

        add(addressLbl);
        add(addressTF);

        add(groupLbl);
        add(groupComboBox);
    }

    public Contact getContactFromFields() {
        switch (getContactType()) {
            case PRIVATE:
                return new PrivateContact(
                        nameTF.getText(),
                        surnameTF.getText(),
                        phoneNumberTF.getText(),
                        addressTF.getText(),
                        groupComboBox.getSelectedItem().toString());
            case BUSINESS:
                return new BusinessContact(
                        nameTF.getText(),
                        surnameTF.getText(),
                        phoneNumberTF.getText(),
                        addressTF.getText(),
                        groupComboBox.getSelectedItem().toString());
            default:
                return null;
        }
    }

    private ContactType getContactType() {
        if (privateContactTypeRB.isSelected())
            return ContactType.PRIVATE;
        else
            return ContactType.BUSINESS;
    }

    public void clearForm() {
        nameTF.setText("");
        surnameTF.setText("");
        privateContactTypeRB.setSelected(true);
        phoneNumberTF.setText("");
        addressTF.setText("");
        groupComboBox.setSelectedIndex(0);
    }

    public void fillForm(Contact contact) {
        nameTF.setText(contact.getName());
        surnameTF.setText(contact.getSurname());
        if (contact.getContactType() == ContactType.PRIVATE) {
            privateContactTypeRB.setSelected(true);
        } else {
            businessContactTypeRB.setSelected(true);
        }
        phoneNumberTF.setText(contact.getPhoneNumber());
        addressTF.setText(contact.getAddress());
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setName(contact.getGroupName());
        groupComboBox.setSelectedItem(contactGroup);
    }

    public JTextField getNameTF() {
        return nameTF;
    }


    public JTextField getSurnameTF() {
        return surnameTF;
    }


    public JTextField getPhoneNumberTF() {
        return phoneNumberTF;
    }

    public JTextField getAddressTF() {
        return addressTF;
    }

    public JComboBox<ContactGroup> getGroupComboBox() {
        return groupComboBox;
    }
}
