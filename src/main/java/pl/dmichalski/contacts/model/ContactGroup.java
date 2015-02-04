package pl.dmichalski.contacts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class ContactGroup {

    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "ContactGroup{" +
                "contacts=" + contacts +
                '}';
    }
}
