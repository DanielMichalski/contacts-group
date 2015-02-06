package pl.dmichalski.contacts.ui.contact_registration.view.contact_table;

import pl.dmichalski.contacts.ui.contact_registration.model.ContactTableModel;

import javax.swing.*;
import java.awt.*;

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
        contactTable = new JTable(new ContactTableModel(null));
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
