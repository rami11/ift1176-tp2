package view.menu;

import util.DialogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-17.
 */
public class AboutMenu extends JMenu implements ActionListener {
    private static final String MANUEL_UTILISATEUR_URL = "manuel_utilisateur.html";
    private JMenuItem infoMenuItem;
    private JMenuItem aideMenuItem;

    public AboutMenu(String s) {
        super(s);

        infoMenuItem = new JMenuItem("Info");
        infoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        infoMenuItem.addActionListener(this);

        aideMenuItem = new JMenuItem("Aide");
        aideMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aideMenuItem.addActionListener(this);

        add(infoMenuItem);
        add(aideMenuItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object composant = e.getSource();

        if (composant == infoMenuItem) {
            DialogUtils.showAppInfoDialog(null);
        } else if (composant == aideMenuItem) {
            try {
                File htmlFile = new File(MANUEL_UTILISATEUR_URL);
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }
    }
}
