package pl.dmichalski.contacts.ui.show_contact_tree.controller;

import pl.dmichalski.contacts.dao.ContactDao;
import pl.dmichalski.contacts.model.ContactGroup;
import pl.dmichalski.contacts.provider.ContactGroupsProvider;
import pl.dmichalski.contacts.ui.contact_registration.controller.RegistrationController;
import pl.dmichalski.contacts.ui.contact_registration.view.RegistrationFrame;
import pl.dmichalski.contacts.ui.show_contact_tree.model.UserGroupsTreeModel;
import pl.dmichalski.contacts.ui.show_contact_tree.view.panel.ContactButtonPanel;
import pl.dmichalski.contacts.ui.show_contact_tree.view.ContactsFrame;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Author: Daniel
 */
public class ShowContactTreeController implements ViewRefresher {

    public static final String XML_FILE_EXTENSION = ".xml";

    private UserGroupsTreeModel treeModel;

    private ContactDao contactDao;

    private JTextField groupTF;

    private ContactGroupsProvider contactGroupsProvider;

    public ShowContactTreeController(ContactsFrame contactsFrame) {
        this.treeModel = contactsFrame.getContactTreePanel().getModel();
        this.contactDao = new ContactDao();
        this.groupTF = contactsFrame.getContactButtonPanel().getGroupTF();
        contactGroupsProvider = ContactGroupsProvider.getInstance();

        ContactButtonPanel contactButtonPanel = contactsFrame.getContactButtonPanel();
        contactButtonPanel.getContactManagementBtn().addActionListener(new OnContactManagementBtnClick());
        contactButtonPanel.getAddBtn().addActionListener(new OnAddBtnListener());

        contactsFrame.addWindowListener(new OnWindowCloseListener());

        readXmlFile();
        addDefaultContactGroupIfNeeded();
    }

    class OnContactManagementBtnClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RegistrationFrame frame = new RegistrationFrame();
            new RegistrationController(frame, ShowContactTreeController.this);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }

    }

    private class OnAddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContactGroupsProvider contactGroupsProvider = ContactGroupsProvider.getInstance();
            String groupName = groupTF.getText();

            if (groupTF.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.GROUP_NAME_REQUIRED,
                        Const.Strings.INFORMATION,
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else if (!contactGroupsProvider.existGroup(groupName)) {
                ContactGroup contactGroup = new ContactGroup();
                contactGroup.setName(groupName);
                contactGroupsProvider.addGroup(contactGroup);
                treeModel.setContactGroups(contactGroupsProvider.getGroups());
                groupTF.setText("");
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        Const.Strings.GROUP_EXIST,
                        Const.Strings.INFORMATION,
                        JOptionPane.ERROR_MESSAGE
                );
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
                List<ContactGroup> groups = contactDao.getAllContacts(selectedFile);
                contactGroupsProvider.addGroups(groups);
                for (ContactGroup group : groups) {
                    treeModel.addContactGroup(group);
                }
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
                    String absolutePathToFile = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!absolutePathToFile.endsWith(XML_FILE_EXTENSION)) {
                        absolutePathToFile += XML_FILE_EXTENSION;
                    }
                    Path selectedFile = Paths.get(absolutePathToFile);
                    ContactGroupsProvider contactGroupsProvider = ContactGroupsProvider.getInstance();
                    List<ContactGroup> contactGroups = contactGroupsProvider.getGroups();
                    contactDao.saveAllContacts(contactGroups, selectedFile);
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

    private void addDefaultContactGroupIfNeeded() {
        for (ContactGroup group : contactGroupsProvider.getGroups()) {
            if (Const.Labels.DEFAULT_CONTACT_GROUP.equals(group.getName())) {
                return;
            }
        }
        ContactGroup defaultContactGroup = new ContactGroup();
        defaultContactGroup.setName(Const.Labels.DEFAULT_CONTACT_GROUP);
        contactGroupsProvider.addGroup(defaultContactGroup);
        treeModel.addContactGroup(defaultContactGroup);
    }

    @Override
    public synchronized void refreshView() {
        treeModel.clear();
        List<ContactGroup> groups = contactGroupsProvider.getGroups();
        for (ContactGroup group : groups) {
            treeModel.addContactGroup(group);
        }
    }


}
