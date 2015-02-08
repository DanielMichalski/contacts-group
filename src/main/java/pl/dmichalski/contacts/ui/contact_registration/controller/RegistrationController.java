package pl.dmichalski.contacts.ui.contact_registration.controller;

import pl.dmichalski.contacts.dao.ContactDao;
import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.model.ContactGroup;
import pl.dmichalski.contacts.model.ContactType;
import pl.dmichalski.contacts.provider.ContactGroupsProvider;
import pl.dmichalski.contacts.ui.contact_registration.model.ContactTableModel;
import pl.dmichalski.contacts.ui.contact_registration.view.RegistrationFrame;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.ContactRegisterLeftPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.DataButtonPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.FormPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_table.ContactSearchPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_table.ContactTablePanel;
import pl.dmichalski.contacts.ui.show_contact_tree.controller.ViewRefresher;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;
import java.util.StringTokenizer;

public class RegistrationController {

    private FormPanel leftFormPanel;

    private ContactDao contactDao;

    private JTextField searchSurnameTF;

    private JTextField searchAddressTF;

    private JTable contactTable;

    private ContactTableModel contactTableModel;

    private JComboBox<ContactGroup> groupComboBox;

    private ViewRefresher viewRefresher;

    public RegistrationController(RegistrationFrame registrationFrame, ViewRefresher viewRefresher) {
        this.contactDao = new ContactDao();
        this.viewRefresher = viewRefresher;
        initializeComponents(registrationFrame);
    }

    private void initializeComponents(RegistrationFrame registrationFrame) {
        ContactRegisterLeftPanel contactRegisterLeftPanel = registrationFrame.getContactRegisterLeftPanel();
        DataButtonPanel leftBtnPanel = contactRegisterLeftPanel.getButtonPanel();
        ContactTablePanel contactTablePanel = registrationFrame.getContactTablePanel();
        ContactSearchPanel searchRightPanel = contactTablePanel.getContactSearchPanel();
        leftFormPanel = contactRegisterLeftPanel.getFormPanel();

        JButton saveBtn = leftBtnPanel.getSaveBtn();
        JButton clearBtn = leftBtnPanel.getCancelBtn();
        JButton deleteBtn = contactTablePanel.getBtnPanel().getDeleteContactBtn();
        JRadioButton allContactsRadioBtn = searchRightPanel.getAllContactsRadioBtn();
        JRadioButton privateContactsRadioBtn = searchRightPanel.getPrivateContactsRadioBtn();
        JRadioButton businessContactsRadioBtn = searchRightPanel.getBusinessContactsRadioBtn();

        this.searchSurnameTF = searchRightPanel.getSearchSurnameTF();
        this.searchAddressTF = searchRightPanel.getSearchAddressTF();
        this.contactTable = contactTablePanel.getContactTable();
        this.contactTableModel = (ContactTableModel) contactTable.getModel();
        this.groupComboBox = contactRegisterLeftPanel.getFormPanel().getGroupComboBox();

        saveBtn.addActionListener(new OnSaveClickListener());
        clearBtn.addActionListener(new OnClearClickListener());
        deleteBtn.addActionListener(new OnDeleteClickListener());
        allContactsRadioBtn.addActionListener(new ContactTypeRadioButtonListener());
        privateContactsRadioBtn.addActionListener(new ContactTypeRadioButtonListener());
        businessContactsRadioBtn.addActionListener(new ContactTypeRadioButtonListener());
        contactTable.getSelectionModel().addListSelectionListener(new OnContactRowClickListener());
        registrationFrame.addWindowListener(new OnWindowCloseListener());
        searchSurnameTF.addKeyListener(new OnEnterTypedInTextFieldListener());
        searchAddressTF.addKeyListener(new OnEnterTypedInTextFieldListener());

        initGroupComboBox();
        initContactsInTable();
    }

    private void initGroupComboBox() {
        ContactGroupsProvider contactGroupsProvider = ContactGroupsProvider.getInstance();
        List<ContactGroup> groups = contactGroupsProvider.getGroups();
        for (ContactGroup group : groups) {
            groupComboBox.addItem(group);
        }
    }

    private void initContactsInTable() {
        ContactGroupsProvider contactGroupsProvider = ContactGroupsProvider.getInstance();
        List<ContactGroup> groups = contactGroupsProvider.getGroups();
        for (ContactGroup group : groups) {
            List<Contact> contacts = group.getContacts();
            contactTableModel.addContacts(contacts);
        }
    }

    class OnSaveClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = contactTable.getSelectedRow();

            if (leftFormPanel.getNameTF().getText().isEmpty()
                    || leftFormPanel.getSurnameTF().getText().isEmpty()
                    || leftFormPanel.getPhoneNumberTF().getText().isEmpty()
                    || leftFormPanel.getAddressTF().getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.FIELDS_REQUIRED,
                        Const.Strings.INFORMATION,
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (selectedRowIndex != -1) {
                    int contactId = contactTableModel.getContactByRow(selectedRowIndex).getId();
                    Contact contact = leftFormPanel.getContactFromFields();
                    contactDao.updateContact(contactId, leftFormPanel.getContactFromFields());
                    contact.setId(contactId);
                    contactTableModel.editContactByRow(contact, selectedRowIndex);
                    contactTable.clearSelection();
                    leftFormPanel.clearForm();
                } else {
                    Contact contact = leftFormPanel.getContactFromFields();
                    contactDao.saveContact(contact);
                    contactTableModel.addContact(contact);
                    leftFormPanel.clearForm();
                }
            }
        }
    }

    class OnClearClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            leftFormPanel.clearForm();
        }
    }

    private class OnDeleteClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int numOfRow = contactTable.getSelectedRow();
            if (numOfRow != -1) {
                Contact contactToDelete = contactTableModel.getContactByRow(numOfRow);
                contactTableModel.removeRow(numOfRow);
                contactDao.deleteContact(contactToDelete);
                leftFormPanel.clearForm();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.ROW_DELETE_REQUIRED,
                        Const.Strings.INFORMATION,
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private class OnWindowCloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {
            ContactGroupsProvider contactGroupsProvider = ContactGroupsProvider.getInstance();
            contactGroupsProvider.clearInGroups();
            List<Contact> contacts = contactTableModel.getContacts();

            for (Contact contact : contacts) {
                String groupName = contact.getGroupName();
                ContactGroup groupByName = contactGroupsProvider.getGroupByName(groupName);
                groupByName.addContact(contact);
            }
            viewRefresher.refreshView();
        }

    }

    private class OnContactRowClickListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            int rowIndex = contactTable.getSelectedRow();

            if (rowIndex >= 0) {
                Contact contact = contactTableModel.getContactByRow(rowIndex);
                leftFormPanel.fillForm(contact);
            }
        }
    }

    private class OnEnterTypedInTextFieldListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            searchContacts();
        }
    }

    private void searchContacts() {
        String surname = searchSurnameTF.getText().replaceAll("[*]", "");
        String address = searchAddressTF.getText().replaceAll("[*]", "");
        contactTableModel.refilter(surname, address);
    }

    private class ContactTypeRadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case Const.ButtonLbls.ALL_CONTACTS:
                    contactTableModel.setSelectedContactType(null);
                    break;
                case Const.ButtonLbls.PRIVATE_CONTACTS:
                    contactTableModel.setSelectedContactType(ContactType.PRIVATE);
                    break;
                case Const.ButtonLbls.BUSINESS_CONTACTS:
                    contactTableModel.setSelectedContactType(ContactType.BUSINESS);
                    break;
            }

            searchContacts();
        }
    }

}
