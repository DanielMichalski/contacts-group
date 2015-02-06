package pl.dmichalski.contacts.dao;

import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.model.ContactGroup;
import pl.dmichalski.contacts.xml.XmlFileWriter;

import java.nio.file.Path;
import java.util.List;

/**
 * Author: Daniel
 */
public class ContactDao {

    private XmlFileWriter xmlFileWriter;

    public ContactDao() {
        this.xmlFileWriter = new XmlFileWriter();
    }

    public List<ContactGroup> getAllContacts(Path path) throws Exception {
        return xmlFileWriter.readContractsFromFile(path);
    }

    public void saveAllContacts(List<ContactGroup> contacts, Path path) throws Exception {
        xmlFileWriter.writeContactsIntoFile(contacts, path);
    }


    public void updateContact(int contactId, Contact contactFromFields) {
        System.out.println("Updating contact");
    }

    public void saveContact(Contact contact) {
        System.out.println("Saving contact");
    }

    public void deleteContact(Contact contactToDelete) {
        System.out.println("Deleting contact");
    }
}
