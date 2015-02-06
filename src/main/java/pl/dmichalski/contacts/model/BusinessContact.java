package pl.dmichalski.contacts.model;


/**
 * Author: Daniel
 */
public class BusinessContact extends Contact {

    private ContactType contactType = ContactType.BUSINESS;

    public BusinessContact() {
    }

    public BusinessContact(String name, String surname, String phoneNumber, String address) {
        super(name, surname, phoneNumber, address);
    }

    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " " + getPhoneNumber();
    }
}
