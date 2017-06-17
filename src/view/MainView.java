package view;

import util.DialogUtils;
import view.menu.ApplicationMenu;
import view.menu.FichierMenu;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rsn on 2017-06-09.
 */
public class MainView extends JFrame implements ActionListener {
    private static final JFrame mainView = new MainView("IFT-1176 (TP2)");
    private JMenuItem infoMenuItem;
    private JMenuItem aideMenuItem;

    private MainView(String titre) {
        setLayout(new BorderLayout());
        setTitle(titre);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fichierMenu = new FichierMenu("Fichier");

        JMenu applicationMenu = new ApplicationMenu("Application");
        JMenu aboutMenu = new JMenu("À propos");

        infoMenuItem = new JMenuItem("Info");
        infoMenuItem.addActionListener(this);

        aideMenuItem = new JMenuItem("Aide");
        aideMenuItem.addActionListener(this);

        aboutMenu.add(infoMenuItem);
        aboutMenu.add(aideMenuItem);

        menuBar.add(fichierMenu);
        menuBar.add(applicationMenu);
        menuBar.add(aboutMenu);

        JPanel fichierDonneesPanel = new JPanel(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder("Les fichier de données");
        titledBorder.setTitleJustification(TitledBorder.RIGHT);
        fichierDonneesPanel.setBorder(titledBorder);

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel auteurLabel = new JLabel("Auteur");
        JLabel livreLabel = new JLabel("Livre");
        labelPanel.add(auteurLabel);
        labelPanel.add(livreLabel);

        JPanel textFieldPanel = new JPanel(new GridLayout(2, 1));
        JTextField auteurTextField = new JTextField(45);
        auteurTextField.setEnabled(false);
        textFieldPanel.add(auteurTextField);
        JTextField livreTextField = new JTextField(45);
        livreTextField.setEnabled(false);
        textFieldPanel.add(livreTextField);

        fichierDonneesPanel.add(labelPanel, BorderLayout.WEST);
        fichierDonneesPanel.add(textFieldPanel, BorderLayout.EAST);

        add(fichierDonneesPanel, BorderLayout.NORTH);

        ImageIcon udemImage = new ImageIcon("udem_logo_medium.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(udemImage);

        add(imageLabel, BorderLayout.CENTER);
    }

    public static JFrame getInstance() {
        return mainView;
    }

    public void showView() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object composant = e.getSource();

        if (composant == infoMenuItem) {
            DialogUtils.showAppInfoDialog(this);
        }
    }
}
