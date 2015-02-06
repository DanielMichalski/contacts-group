package pl.dmichalski.contacts.ui.show_contact_tree.view;

import pl.dmichalski.contacts.utils.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class GroupRightPanel extends JPanel {

    private JTextField groupTF;

    private JButton addBtn;

    public static final int WIDTH = 350;

    public static final int HEIGHT = 80;

    public GroupRightPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        JLabel groupLbl = new JLabel(Const.Labels.GROUP);
        groupTF = new JTextField("");
        groupTF.setPreferredSize(new Dimension(120, 25));
        addBtn = new JButton(Const.ButtonLbls.BTN_ADD);

        add(groupLbl);
        add(groupTF);
        add(addBtn);
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JTextField getGroupTF() {
        return groupTF;
    }

}
