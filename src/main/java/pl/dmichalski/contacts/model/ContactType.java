package pl.dmichalski.contacts.model;

/**
 * Author: Daniel
 */
public enum ContactType {

    PRIVATE("Prywatny"),
    BUSINESS("Biznesowy");

    ContactType(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return label;
    }

}
