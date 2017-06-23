package view.menu;

import tp1.Bdd;
import tp1.Signatures;
import util.IOUtils;
import view.dialog.AddDialog;
import view.dialog.GetDialog;
import view.dialog.RapportDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-15.
 */
public class ApplicationMenu extends JMenu implements ActionListener {
    public static final String[] AUTEUR_PARAMS = {"Code", "Nom", "Pays"};
    public static final String[] LIVRE_PARAMS = {"Code", "Titre", "Catégorie", "Code de l'auteur", "Prix", "Nombre de pages"};

    private JMenuItem addAuteurMenuItem;
    private JMenuItem addLivreMenuItem;

    private JMenuItem getAuteurMenuItem;
    private JMenuItem getLivreMenuItem;

    private JMenuItem rapportParAuteurMenuItem;
    private JMenuItem rapportParLivreMenuItem;

    public ApplicationMenu(String s) {
        super(s);

        JMenu addMenu = new JMenu("Ajouter");
        addMenu.setIcon(new ImageIcon("icon_add_tout_petit.png"));
        addAuteurMenuItem = new JMenuItem("un auteur");
        addAuteurMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        addAuteurMenuItem.addActionListener(this);

        addLivreMenuItem = new JMenuItem("un livre");
        addLivreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));
        addLivreMenuItem.addActionListener(this);

        addMenu.add(addAuteurMenuItem);
        addMenu.add(addLivreMenuItem);

        //------------------------------------------------------
        JMenu getMenu = new JMenu("Chercher");
        getMenu.setIcon(new ImageIcon("icon_search_tout_petit.png"));
        getAuteurMenuItem = new JMenuItem("un auteur");
        getAuteurMenuItem.addActionListener(this);
        getAuteurMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

        getLivreMenuItem = new JMenuItem("un livre");
        getLivreMenuItem.addActionListener(this);
        getLivreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

        getMenu.add(getAuteurMenuItem);
        getMenu.add(getLivreMenuItem);

        //------------------------------------------------------
        JMenu rapportMenu = new JMenu("Rapport");
        rapportMenu.setIcon(new ImageIcon("icon_rapport_tout_petit.png"));
        rapportParAuteurMenuItem = new JMenuItem("par auteur");
        rapportParAuteurMenuItem.addActionListener(this);

        rapportParLivreMenuItem = new JMenuItem("par livre");
        rapportParLivreMenuItem.addActionListener(this);

        rapportMenu.add(rapportParAuteurMenuItem);
        rapportMenu.add(rapportParLivreMenuItem);

        add(addMenu);
        add(getMenu);
        add(new JSeparator());
        add(rapportMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Signatures bdd = Bdd.getInstance();
        Object composant = e.getSource();

        if (composant == addAuteurMenuItem) {
            new AddDialog("Ajouter un auteur", "Veuillez enter les valeurs de l'auteur", AUTEUR_PARAMS);
        } else if (composant == addLivreMenuItem) {
            new AddDialog("Ajouter un Livre", "Veuillez entrer les valeurs du livre", LIVRE_PARAMS);
        } else if (composant == getAuteurMenuItem) {
            new GetDialog("Chercher un auteur", "Veuillez entrer le nom de l'auteur");
        } else if (composant == getLivreMenuItem) {
            new GetDialog("Chercher un livre", "Veuillez entrer le nom du livre");
        } else if (composant == rapportParAuteurMenuItem) {
            try {
                bdd.rapportParAuteurs();
                String parAuteurContenu = IOUtils.lireFichierTexte("parAuteur.txt");
                new RapportDialog("Rapport par auteur", parAuteurContenu);
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } else if (composant == rapportParLivreMenuItem) {
            try {
                bdd.rapportParLivres();
                String parLivreContenu = IOUtils.lireFichierTexte("parLivre.txt");
                new RapportDialog("Rapport par livre", parLivreContenu);
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }
    }
}
