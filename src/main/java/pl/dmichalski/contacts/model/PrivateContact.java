package pl.dmichalski.contacts.model;


/**
 * Author: Daniel
 */
public class PrivateContact extends Contact {

    private ContactType contactType = ContactType.PRIVATE;

    public PrivateContact() {
    }

    public PrivateContact(String name, String surname, String phoneNumber, String address, String groupName) {
        super(name, surname, phoneNumber, address, groupName);
    }

    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " " + getPhoneNumber();
    }

}
