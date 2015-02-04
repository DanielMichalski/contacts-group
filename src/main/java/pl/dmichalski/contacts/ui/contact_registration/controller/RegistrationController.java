package pl.dmichalski.contacts.ui.contact_registration.controller;

import pl.dmichalski.contacts.dao.ContactDao;
import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.ui.contact_registration.model.ContactTableModel;
import pl.dmichalski.contacts.ui.contact_registration.view.RegistrationFrame;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.DataButtonPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.FormPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_table.ContactSearchPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_table.ContactTablePanel;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

public class RegistrationController {

    private FormPanel leftFormPanel;

    private ContactDao contactDao;

    private JTextField searchTF;

    private JTable contactTable;

    private ContactTableModel contactTableModel;

    public RegistrationController(RegistrationFrame registrationFrame) {
        this.contactDao = new ContactDao();
        initializeComponents(registrationFrame);
    }

    private void initializeComponents(RegistrationFrame registrationFrame) {
        DataButtonPanel leftBtnPanel = registrationFrame.getContactRegisterLeftPanel().getButtonPanel();
        leftFormPanel = registrationFrame.getContactRegisterLeftPanel().getFormPanel();
        ContactTablePanel contactTablePanel = registrationFrame.getContactTablePanel();
        ContactSearchPanel searchRightPanel = contactTablePanel.getContactSearchPanel();

        JButton saveBtn = leftBtnPanel.getSaveBtn();
        JButton clearBtn = leftBtnPanel.getCancelBtn();
        JButton searchBtn = contactTablePanel.getContactSearchPanel().getSearchBtn();
        JButton deleteBtn = contactTablePanel.getBtnPanel().getDeleteContactBtn();

        this.searchTF = searchRightPanel.getSearchTF();
        this.contactTable = contactTablePanel.getContactTable();
        this.contactTableModel = (ContactTableModel) contactTable.getModel();

        saveBtn.addActionListener(new OnSaveClickListener());
        clearBtn.addActionListener(new OnClearClickListener());
        searchBtn.addActionListener(new OnSearchClickListener());
        deleteBtn.addActionListener(new OnDeleteClickListener());
        contactTable.getSelectionModel().addListSelectionListener(new OnContactRowClickListener());
        registrationFrame.addWindowListener(new OnWindowCloseListener());

        readXmlFile();
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
    private class OnSearchClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StringTokenizer tokenizer = new StringTokenizer(searchTF.getText());
            if (searchTF.getText().equals("")) {
                contactTableModel.resetFilter();
                contactTableModel.sortContacts();
            } else if (tokenizer.countTokens() == 1) {
                String token = tokenizer.nextToken();
                contactTableModel.refilter(token, token);
            } else if (tokenizer.countTokens() == 2) {
                String name = tokenizer.nextToken();
                String surname = tokenizer.nextToken();
                contactTableModel.refilter(name, surname);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.NAME_REQUIRED,
                        Const.Strings.INFORMATION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
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
            UIManager.put("OptionPane.yesButtonText", "Tak");
            UIManager.put("OptionPane.noButtonText", "Nie");

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    Const.Strings.SAVE_CONFIRMATION,
                    Const.Strings.INFORMATION,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                saveChanges();
            }
        }

        private void saveChanges() {
            try {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Xml files (*.xml)", "xml");
                fileChooser.setFileFilter(xmlFilter);
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String pathToFile = fileChooser.getSelectedFile().getAbsolutePath() + ".xml";
                    Path selectedFile = Paths.get(pathToFile);
                    List<Contact> contacts = contactTableModel.getContacts();
                    contactDao.saveAllContacts(contacts, selectedFile);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.FILE_WRITE_ERROR + e.getMessage(),
                        Const.Strings.ERROR,
                        JOptionPane.ERROR_MESSAGE
                );
            }
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

    private void readXmlFile() {
        UIManager.put("OptionPane.yesButtonText", "Tak");
        UIManager.put("OptionPane.noButtonText", "Nie");

        int confirm = JOptionPane.showConfirmDialog(
                null,
                Const.Strings.READ_CONFIRMATION,
                Const.Strings.INFORMATION,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            readContacts();
        }
    }

    private void readContacts() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Xml files (*.xml)", "xml");
            fileChooser.setFileFilter(xmlFilter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                Path selectedFile = Paths.get(fileChooser.getSelectedFile().toURI());
                List<Contact> contacts = contactDao.getAllContacts(selectedFile);
                contactTableModel.addContacts(contacts);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    Const.Strings.FILE_OPEN_ERROR + e.getMessage(),
                    Const.Strings.ERROR,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
