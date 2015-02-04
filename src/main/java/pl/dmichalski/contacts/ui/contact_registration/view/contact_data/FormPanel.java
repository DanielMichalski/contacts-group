package pl.dmichalski.contacts.ui.contact_registration.view.contact_data;

import pl.dmichalski.contacts.model.BusinessContact;
import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.model.ContactType;
import pl.dmichalski.contacts.model.PrivateContact;
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

    public FormPanel() {
        setUpPanel();
        initComponents();
    }

    private void setUpPanel() {
        GridLayout layout = new GridLayout(6, 2);
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
    }

    public Contact getContactFromFields() {
        switch (getContactType()) {
            case PRIVATE:
                return new PrivateContact(
                        nameTF.getText(),
                        surnameTF.getText(),
                        phoneNumberTF.getText(),
                        addressTF.getText());
            case BUSINESS:
                return new BusinessContact(
                        nameTF.getText(),
                        surnameTF.getText(),
                        phoneNumberTF.getText(),
                        addressTF.getText());
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
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public void setNameTF(JTextField nameTF) {
        this.nameTF = nameTF;
    }

    public JTextField getSurnameTF() {
        return surnameTF;
    }

    public void setSurnameTF(JTextField surnameTF) {
        this.surnameTF = surnameTF;
    }

    public JRadioButton getPrivateContactTypeRB() {
        return privateContactTypeRB;
    }

    public void setPrivateContactTypeRB(JRadioButton privateContactTypeRB) {
        this.privateContactTypeRB = privateContactTypeRB;
    }

    public JRadioButton getBusinessContactTypeRB() {
        return businessContactTypeRB;
    }

    public void setBusinessContactTypeRB(JRadioButton businessContactTypeRB) {
        this.businessContactTypeRB = businessContactTypeRB;
    }

    public JTextField getPhoneNumberTF() {
        return phoneNumberTF;
    }

    public void setPhoneNumberTF(JTextField phoneNumberTF) {
        this.phoneNumberTF = phoneNumberTF;
    }

    public JTextField getAddressTF() {
        return addressTF;
    }

    public void setAddressTF(JTextField addressTF) {
        this.addressTF = addressTF;
    }
}
