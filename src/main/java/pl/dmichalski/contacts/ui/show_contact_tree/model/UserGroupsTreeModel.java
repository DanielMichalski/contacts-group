package pl.dmichalski.contacts.ui.show_contact_tree.model;

import pl.dmichalski.contacts.model.Contact;
import pl.dmichalski.contacts.model.ContactGroup;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class UserGroupsTreeModel implements TreeModel {

    private String rootLabel;

    private List<ContactGroup> group = new ArrayList<>();

    private List<TreeModelListener> listeners = new ArrayList<>();

    public UserGroupsTreeModel(String rootLabel) {
        this.rootLabel = rootLabel;
    }

    public Object getRoot() {
        return rootLabel;
    }

    private void fireTreeStructureChanged() {
        Object[] o = {rootLabel};
        TreeModelEvent e = new TreeModelEvent(this, o);
        for (TreeModelListener l : listeners)
            l.treeStructureChanged(e);
    }

    public void addContactGroup(ContactGroup group) {
        this.group.add(group);
        fireTreeStructureChanged();
    }

    public void addContact(ContactGroup group, Contact contact) {
        ContactGroup g = this.group.get(this.group.indexOf(group));
        g.getContacts().add(contact);
        TreePath p = new TreePath(new Object[]{g});
        this.fireTreeStructureChanged();
    }

    public void addContact(Contact contact) {
        for (ContactGroup defaultContactGroup : group) {
            if (Const.Labels.DEFAULT_CONTACT_GROUP.equals(defaultContactGroup.getName())) {
                defaultContactGroup.addContact(contact);
                this.fireTreeStructureChanged();
                return;
            }
        }
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setName(Const.Labels.DEFAULT_CONTACT_GROUP);
        contactGroup.addContact(contact);
        group.add(contactGroup);
        this.fireTreeStructureChanged();
    }

    public void clear() {
        group = null;
        group = new ArrayList<>();
        this.fireTreeStructureChanged();
    }

    public void removeContactGroup(ContactGroup grupo) {
        if (!group.remove(grupo))
            throw new NullPointerException("ContactGroup: " + grupo + " not exist in Tree");
        this.fireTreeStructureChanged();
    }

    public List<ContactGroup> getContactGroups() {
        return this.group;
    }

    public void setContactGroups(List<ContactGroup> group) {
        this.group = group;
        this.fireTreeStructureChanged();
    }

    public List<Contact> getContacts(ContactGroup group) {
        ContactGroup g = this.group.get(this.group.indexOf(group));
        return g.getContacts();
    }

    public void removeContact(ContactGroup group, Contact contact) {
        ContactGroup g = this.group.get(this.group.indexOf(group));
        if (!(g.getContacts()).remove(contact))
            throw new NullPointerException("Contact: " + contact + " non exist ing group" + group);
        TreePath p = new TreePath(new Object[]{g});
        this.fireTreeStructureChanged();
    }

    public Object getChild(Object parent, int index) {
        if (parent == rootLabel) {
            return group.get(index);
        }
        if (parent instanceof ContactGroup) {
            ContactGroup g = group.get(group.indexOf(parent));
            return g.getContacts().get(index);
        }
        throw new IllegalArgumentException("Parent is not a type supported by Tree");
    }

    public int getChildCount(Object parent) {
        if (parent == rootLabel)
            return group.size();
        if (parent instanceof ContactGroup) {
            ContactGroup g = group.get(group.indexOf(parent));
            return g.getContacts().size();
        }
        throw new IllegalArgumentException("Parent is not a type supported by Tree");
    }

    public boolean isLeaf(Object node) {
        return node instanceof Contact;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    public int getIndexOfChild(Object parent, Object child) {
        if (parent == rootLabel)
            return group.indexOf(child);
        if (parent instanceof ContactGroup)
            return group.get(group.indexOf(child)).getContacts().size();
        return 0;
    }

    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

}
