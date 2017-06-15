import tp1.Bdd;
import tp1.Signatures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-09.
 */
public class MainView extends JFrame implements ActionListener {
    private static final String[] auteurParams = {"Code", "Nom", "Pays"};
    private static final String[] livreParams = {"Code", "Titre", "Categorie", "Code de l'auteur", "Prix", "Nombre des pages"};

    private JMenuItem addAuteurMenuItem;
    private JMenuItem addLivreMenuItem;

    private JMenuItem getAuteurMenuItem;
    private JMenuItem getLivreMenuItem;

    private JMenuItem rapportParAuteurMenuItem;
    private JMenuItem rapportParLivreMenuItem;

    private JMenuItem infoMenuItem;
    private JMenuItem aideMenuItem;

    private JMenu aboutMenu;

    public MainView(String titre) {

        setLayout(new BorderLayout());
        setTitle(titre);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu appMenu = new JMenu("Application");
        aboutMenu = new JMenu("À propos");

        infoMenuItem = new JMenuItem("Info");
        infoMenuItem.addActionListener(this);

        aideMenuItem = new JMenuItem("Aide");
        aideMenuItem.addActionListener(this);

        aboutMenu.add(infoMenuItem);
        aboutMenu.add(aideMenuItem);

        menuBar.add(appMenu);
        menuBar.add(aboutMenu);


        JMenu addMenu = new JMenu("Ajouter");
        addMenu.setIcon(new ImageIcon("icon_add_tout_petit.png"));
        addAuteurMenuItem = new JMenuItem("un auteur");
        addAuteurMenuItem.addActionListener(this);

        addLivreMenuItem = new JMenuItem("un livre");
        addLivreMenuItem.addActionListener(this);

        addMenu.add(addAuteurMenuItem);
        addMenu.add(addLivreMenuItem);

        //------------------------------------------------------
        JMenu getMenu = new JMenu("Chercher");
        getMenu.setIcon(new ImageIcon("icon_search_tout_petit.png"));
        getAuteurMenuItem = new JMenuItem("un auteur");
        getAuteurMenuItem.addActionListener(this);

        getLivreMenuItem = new JMenuItem("un livre");
        getLivreMenuItem.addActionListener(this);

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

        appMenu.add(addMenu);
        appMenu.add(getMenu);
        appMenu.add(new JSeparator());
        appMenu.add(rapportMenu);

        ImageIcon udemImage = new ImageIcon("udem_logo_medium.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(udemImage);

        add(imageLabel, BorderLayout.CENTER);

        // Rapport
        Signatures bdd = Bdd.getInstance();
        try {
            bdd.rapportParAuteurs();
            bdd.rapportParLivres();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object composant = e.getSource();

        if (composant == infoMenuItem) {
            DialogUtils.showAppInfotDialog(this);
        } else if (composant == addAuteurMenuItem) {
            new AddDialog("Ajouter un auteur", "Veuillez enter les valeurs de l'auteur", auteurParams);
        } else if (composant == addLivreMenuItem) {
            new AddDialog("Ajouter un Livre", "Veuillez entrer les valeurs du livre", livreParams);
        } else if (composant == getAuteurMenuItem) {
            new GetDialog("Chercher un auteur", "Veuillez entrer le nom de l'auteur");
        } else if (composant == getLivreMenuItem) {
            new GetDialog("Chercher un livre", "Veuillez entrer le nom du livre");
        } else if (composant == rapportParAuteurMenuItem) {

            String parAuteurContenu = IOUtils.lireFichierTexte("parAuteur.txt");
            new RapportDialog("Rapport par auteur", parAuteurContenu);
        } else if (composant == rapportParLivreMenuItem) {
            String parLivreContenu = IOUtils.lireFichierTexte("parLivre.txt");
            new RapportDialog("Rapport par livre", parLivreContenu);
        }
    }
}
