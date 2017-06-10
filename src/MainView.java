import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rsn on 2017-06-09.
 */
public class MainView extends JFrame implements ActionListener {
    private static final String[] auteurParams = {"Code", "Nom", "Pays"};
    private static final String[] livreParams = {"Code", "Titre", "Categorie", "Code de l'auteur", "Prix", "Nombre des pages"};

    private JMenuItem auteurMenuItem;
    private JMenuItem livreMenuItem;

    public MainView(String titre) {

        setLayout(new BorderLayout());
        setTitle(titre);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu appMenu = new JMenu("Application");
        JMenu aboutMenu = new JMenu("À propos");

        menuBar.add(appMenu);
        menuBar.add(aboutMenu);


        JMenu addMenu = new JMenu("Ajouter");
        appMenu.add(addMenu);
        auteurMenuItem = new JMenuItem("Auteur");
        auteurMenuItem.addActionListener(this);

        livreMenuItem = new JMenuItem("Livre");
        livreMenuItem.addActionListener(this);

        addMenu.add(auteurMenuItem);
        addMenu.add(livreMenuItem);


        ImageIcon udemImage = new ImageIcon("udem_logo_medium.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(udemImage);

        add(imageLabel, BorderLayout.CENTER);


        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object component = e.getSource();
        if (component == auteurMenuItem) {
            new AddDialog("Ajouter un auteur", "Veuillez enter les valeurs de l'auteur", auteurParams);
        } else if (component == livreMenuItem) {
            new AddDialog("Ajouter un Livre", "Veuillez entrer les valeurs du livre", livreParams);
        }
    }
}
