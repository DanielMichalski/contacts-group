package pl.dmichalski.contacts.ui.show_contact_tree.view.panel;

import pl.dmichalski.contacts.ui.show_contact_tree.model.UserGroupsTreeModel;
import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ContactTreePanel extends JPanel {

    private UserGroupsTreeModel model;

    public ContactTreePanel() {
        setPanelUp();
        initializeComponents();
    }

    private void setPanelUp() {
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        model = new UserGroupsTreeModel(Const.Labels.CONTACTS);
        JTree tree = new JTree(model);
        tree.setEditable(false);
        JScrollPane sp = new JScrollPane(tree);
        add(sp, BorderLayout.CENTER);
    }

    public UserGroupsTreeModel getModel() {
        return model;
    }

}
