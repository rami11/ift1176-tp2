package view.menu;

import util.DialogUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by rsn on 2017-06-17.
 */
public class AboutMenu extends JMenu implements ActionListener {
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
            JOptionPane.showMessageDialog(null, "bla bal blab");
        }
    }
}
