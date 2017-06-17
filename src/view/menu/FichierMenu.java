package view.menu;

import tp1.Bdd;
import tp1.Signatures;
import view.MainView;
import view.dialog.FileChooserDialog;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by rsn on 2017-06-15.
 */
public class FichierMenu extends JMenu implements ActionListener {
    private JMenuItem quitterMenuItem;

    private JMenuItem lireAuteurMenuItem;
    private JMenuItem lireLivreMenuItem;

    public FichierMenu(String s) {
        super(s);

        JMenu lireMenu = new JMenu("Lire");
        lireAuteurMenuItem = new JMenuItem("Auteur");
        lireAuteurMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        lireLivreMenuItem = new JMenuItem("Livre");
        lireLivreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        lireMenu.add(lireAuteurMenuItem);
        lireMenu.add(lireLivreMenuItem);

        quitterMenuItem = new JMenuItem("Quitter", new ImageIcon("icon_exit_tout_petit.png"));
        quitterMenuItem.addActionListener(this);
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));

        add(lireMenu);
        add(new JSeparator());
        add(quitterMenuItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = MainView.getInstance();
        Signatures bdd = Bdd.getInstance();

        Object composant = e.getSource();
        /*if (composant == lireMenuItem) {
            new LireDialog();

        }*/
        if (composant == lireAuteurMenuItem) {
            JFileChooser auteurChooser = new FileChooserDialog("Choisir un fichier de données d'auteur", new File("Auteur/"));
            int result = auteurChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                MainView.isFichierAuteurChoisi = true;
                //auteurTextField.setText(auteurChooser.getSelectedFile().getAbsolutePath());
            }

        } else if (composant == lireLivreMenuItem) {
            JFileChooser livreChooser = new FileChooserDialog("Choisir un fichier de données de livre", new File("Livre/"));
            int result = livreChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                MainView.isFichierLivreChoisi = true;
                //livreTextField.setText(livreChooser.getSelectedFile().getAbsolutePath());
            }

        } else if (composant == quitterMenuItem) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
