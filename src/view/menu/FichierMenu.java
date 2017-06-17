package view.menu;

import tp1.Bdd;
import tp1.Signatures;
import view.MainView;
import view.dialog.LireDialog;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by rsn on 2017-06-15.
 */
public class FichierMenu extends JMenu implements ActionListener {
    private JMenuItem quitterMenuItem;
    private JMenuItem lireMenuItem;

    public FichierMenu(String s) {
        super(s);

        lireMenuItem = new JMenuItem("Lire");
        lireMenuItem.addActionListener(this);

        quitterMenuItem = new JMenuItem("Quitter", new ImageIcon("icon_exit_tout_petit.png"));
        quitterMenuItem.addActionListener(this);
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));

        add(lireMenuItem);
        add(new JSeparator());
        add(quitterMenuItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = MainView.getInstance();
        Signatures bdd = Bdd.getInstance();

        Object composant = e.getSource();
        if (composant == lireMenuItem) {
            new LireDialog();

        } else if (composant == quitterMenuItem) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
