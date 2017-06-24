package view.menu;

import view.FichiersDonneesPanel;
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
        lireAuteurMenuItem.addActionListener(this);
        lireAuteurMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        lireLivreMenuItem = new JMenuItem("Livre");
        lireLivreMenuItem.addActionListener(this);
        lireLivreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        lireMenu.add(lireAuteurMenuItem);
        lireMenu.add(lireLivreMenuItem);

        quitterMenuItem = new JMenuItem("Quitter", new ImageIcon("img/icon_exit_tout_petit.png"));
        quitterMenuItem.addActionListener(this);
        quitterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));

        add(lireMenu);
        add(new JSeparator());
        add(quitterMenuItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = MainView.getInstance();

        Object composant = e.getSource();

        if (composant == lireAuteurMenuItem) {
            JFileChooser auteurChooser = new FileChooserDialog("Choisir un fichier de données d'auteur", new File("Auteur/"));
            int result = auteurChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String nomFichier = auteurChooser.getSelectedFile().getAbsolutePath();
                FichiersDonneesPanel.getAuteurTextField().setText(nomFichier);
                if (!FichiersDonneesPanel.getLivreTextField().getText().isEmpty()) {
                    FichiersDonneesPanel.initAppliquerBouton();
                }
            }

        } else if (composant == lireLivreMenuItem) {
            JFileChooser livreChooser = new FileChooserDialog("Choisir un fichier de données de livre", new File("Livre/"));
            int result = livreChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String nomFichier = livreChooser.getSelectedFile().getAbsolutePath();
                FichiersDonneesPanel.getLivreTextField().setText(nomFichier);
                if (!FichiersDonneesPanel.getAuteurTextField().getText().isEmpty()) {
                    FichiersDonneesPanel.initAppliquerBouton();
                }
            }
        } else if (composant == quitterMenuItem) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
