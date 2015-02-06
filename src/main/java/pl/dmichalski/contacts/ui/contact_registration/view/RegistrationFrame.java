package pl.dmichalski.contacts.ui.contact_registration.view;


import pl.dmichalski.contacts.ui.contact_registration.view.contact_data.ContactRegisterLeftPanel;
import pl.dmichalski.contacts.ui.contact_registration.view.contact_table.ContactTablePanel;
import pl.dmichalski.contacts.utils.Const;
import pl.dmichalski.contacts.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JDialog {

    private ContactRegisterLeftPanel contactRegisterLeftPanel;

    private ContactTablePanel contactTablePanel;

    public static final int DEFAULT_WIDTH = 950;

    public static final int DEFAULT_HEIGHT = 380;

    public RegistrationFrame() {
        setUpFrame();
        initComponents();
    }

    private void setUpFrame() {
        setModal(true);
        setTitle(Const.TitleFramesAndDlgs.CONTACT_REGISTRATION_FRAME);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        Utils.setWindowsLookAndFeel();
        setJMenuBar(createMenuBar());
    }

    private void initComponents() {
        contactRegisterLeftPanel = new ContactRegisterLeftPanel();
        contactTablePanel = new ContactTablePanel();

        add(contactRegisterLeftPanel, BorderLayout.WEST);
        add(contactTablePanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu applicationMenu = new JMenu(Const.Menu.APPLICATION_MENU);
        menuBar.add(applicationMenu);

        JMenuItem closeItem = new JMenuItem(Const.MenuItem.CLOSE_MENU_ITEM);
        closeItem.addActionListener(e -> System.exit(0));
        applicationMenu.add(closeItem);
        return menuBar;
    }

    public ContactRegisterLeftPanel getContactRegisterLeftPanel() {
        return contactRegisterLeftPanel;
    }

    public ContactTablePanel getContactTablePanel() {
        return contactTablePanel;
    }
}
