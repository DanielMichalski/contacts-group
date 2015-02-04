package pl.dmichalski.contacts.model;

/**
 * Author: Daniel
 */
public class PrivateContact extends Contact {

    private ContactType contactType = ContactType.PRIVATE;

    public PrivateContact(String name, String surname, String phoneNumber, String address) {
        super(name, surname, phoneNumber, address);
    }

    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public String toString() {
        return "PrivateContact{" +
                "contactType=" + contactType +
                "} " + super.toString();
    }

}
