package pl.dmichalski.contacts.dao;

import pl.dmichalski.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class ContactDao {

    public void updateContact(int contactId, Contact contactFromFields) {
        System.out.println("Updating contact");
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>();
    }

    public void saveAllContacts() {
        System.out.println("Saving contacts");
    }

    public void saveContact(Contact contact) {
        System.out.println("Saving contact");
    }

    public void deleteContact(Contact contactToDelete) {
        System.out.println("Deleting contact");
    }
}
