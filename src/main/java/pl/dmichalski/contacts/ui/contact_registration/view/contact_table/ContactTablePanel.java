package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;

import pl.dmichalski.contacts.model.BusinessContact;
import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.model.PrivateContact;
import pl.dmichalski.contacts.ui.contact_registration.models.ContactTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ContactTablePanel extends JPanel {

    private ContactSearchPanel contactSearchPanel;

    private JTable contactTable;

    private TableBtnPanel btnPanel;

    public ContactTablePanel() {
        setUpPanel();
        initComponents();
        setBackground(Color.black);
    }

    private void setUpPanel() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        //TODO read from xml
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new BusinessContact("Jan", "Kowalski", "7484739283", "ul. Długa 22 Łódź"));
        contacts.add(new PrivateContact("Tomasz", "Malinowski", "604733234", "ul. Długa 12 Warszawa"));
        contacts.add(new BusinessContact("Anna", "Tomaszewska", "504930223", "ul. Bananowa 12 Poznań"));

        contactTable = new JTable(new ContactTableModel(contacts));
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel listSelectionModel = contactTable.getSelectionModel();
        contactTable.setSelectionModel(listSelectionModel);

        contactSearchPanel = new ContactSearchPanel();
        add(contactSearchPanel, BorderLayout.NORTH);

        JScrollPane paneWithTable = new JScrollPane(contactTable);
        add(paneWithTable, BorderLayout.CENTER);

        btnPanel = new TableBtnPanel();
        add(btnPanel, BorderLayout.SOUTH);
    }

    public ContactSearchPanel getContactSearchPanel() {
        return contactSearchPanel;
    }

    public JTable getContactTable() {
        return contactTable;
    }

    public TableBtnPanel getBtnPanel() {
        return btnPanel;
    }
}
