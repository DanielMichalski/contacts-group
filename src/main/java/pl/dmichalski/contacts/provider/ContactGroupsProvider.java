package pl.dmichalski.contacts.provider;

import pl.dmichalski.contacts.model.ContactGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class ContactGroupsProvider {

    private List<ContactGroup> groups = new ArrayList<>();

    private static ContactGroupsProvider instance = new ContactGroupsProvider();

    private ContactGroupsProvider() {
    }

    public static ContactGroupsProvider getInstance() {
        return instance;
    }

    public boolean existGroup(String name) {
        if (name == null) return false;

        for (ContactGroup group : groups) {
            if (name.equals(group.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addGroup(ContactGroup contactGroup) {
        groups.add(contactGroup);
    }

    public List<ContactGroup> getGroups() {
        return groups;
    }

}
