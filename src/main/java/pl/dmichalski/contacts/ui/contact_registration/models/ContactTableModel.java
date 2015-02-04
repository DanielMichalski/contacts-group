package pl.dmichalski.contacts.ui.contact_registration.models;


import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactTableModel extends AbstractTableModel {

    private String[] columnNames = new String[]{
            Const.TableColumns.NAME_AND_SURNAME,
            Const.TableColumns.PHONE_NUMBER,
            Const.TableColumns.TYPE,
            Const.TableColumns.ADDRESS};

    private List<Contact> contacts;

    private List<Contact> filteredContacts;

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ContactTableModel(List<Contact> contacts) {
        super();
        if (contacts != null) {
            this.contacts = contacts;
            this.filteredContacts = new ArrayList<>();
            refilter();
        } else {
            this.contacts = new ArrayList<>();
            this.filteredContacts = new ArrayList<>();
        }
        sortContacts();
    }

    @Override
    public int getRowCount() {
        return filteredContacts.size();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        sortContacts();
        refilter();
    }

    public void sortContacts() {
        Comparator<Contact> comparator = (contact1, contact2)
                -> contact1.getName().compareToIgnoreCase(contact2.getName());

        Collections.sort(contacts, comparator);
        Collections.sort(filteredContacts, comparator);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = filteredContacts.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return contact.getName() + " " + contact.getSurname();
            case 1:
                return contact.getPhoneNumber();
            case 2:
                return contact.getContactType();
            case 3:
                return contact.getAddress();
            default:
                return "";
        }

    }

    public Contact getContactByRow(int rowIndex) {
        return filteredContacts.get(rowIndex);
    }

    public void editContactByRow(Contact newContact, int rowIndex) {
        contacts.set(rowIndex, newContact);
        refilter();
        sortContacts();
        fireTableDataChanged();
    }

    public void removeRow(int row) {
        contacts.remove(row);
        filteredContacts.remove(row);
        sortContacts();
        fireTableRowsDeleted(row, row);
    }

    private void refilter() {
        filteredContacts.clear();
        for (Contact contact : contacts)
            filteredContacts.add(contact);
        fireTableDataChanged();
    }

    public void refilter(String imie, String nazwisko) {
        filteredContacts.clear();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(imie.toLowerCase())
                    || contact.getName().toLowerCase().contains(nazwisko.toLowerCase()))
                filteredContacts.add(contact);
        }

        fireTableDataChanged();
    }

    public void resetFilter() {
        filteredContacts.clear();
        for (Contact contact : contacts)
            filteredContacts.add(contact);
        fireTableDataChanged();
    }
}
