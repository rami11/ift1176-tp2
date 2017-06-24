package view.dialog;

import tp1.Auteur;
import tp1.Bdd;
import tp1.Livre;
import tp1.Signatures;
import view.menu.ApplicationMenu;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rsn on 2017-06-10.
 */
public class AddDialog extends JDialog implements ActionListener {
    private int nombreParams;

    private JTextField[] textFields;
    private JButton addBouton;

    public AddDialog(String titre, String message, String[] params) {

        setTitle(titre);
        setLayout(new BorderLayout());

        nombreParams = params.length;

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(message));

        JLabel[] labels = new JLabel[nombreParams];
        textFields = new JTextField[nombreParams];

        JPanel labelPanel = new JPanel(new GridLayout(nombreParams, 1));
        JPanel valeurPanel = new JPanel(new GridLayout(nombreParams, 1));
        for (int i = 0; i < nombreParams; i++) {
            labels[i] = new JLabel(params[i]);
            labelPanel.add(labels[i]);

            textFields[i] = new JTextField(20);
            valeurPanel.add(textFields[i]);
        }
        contentPanel.add(labelPanel, BorderLayout.WEST);
        contentPanel.add(valeurPanel, BorderLayout.EAST);

        add(contentPanel, BorderLayout.NORTH);

        addBouton = new JButton("Ajouter");
        addBouton.addActionListener(this);
        add(addBouton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private static String validateAuteurInfo(String[] info) {
        boolean valide = true;
        StringBuilder messageBuilder = new StringBuilder("Veuillez vérifier les points suivants:\n\n");
        for (int i = 0; i < info.length; i++) {
            if (info[i].isEmpty()) {
                messageBuilder.append("- Le champs [" + ApplicationMenu.AUTEUR_PARAMS[i] + "] est vide\n");
                valide = false;
            } else if (i == 0) {
                try {
                    Integer.valueOf(info[i]);
                } catch (Exception ex) {
                    messageBuilder.append("- Le champs [" + ApplicationMenu.AUTEUR_PARAMS[i] + "] doit être entier\n");
                    valide = false;
                }
            }
        }
        return valide ? "ok" : messageBuilder.toString();
    }

    private static String validateLivreInfo(String[] info) {
        boolean valide = true;
        StringBuilder messageBuilder = new StringBuilder("Veuillez vérifier les points suivants:\n\n");
        for (int i = 0; i < info.length; i++) {
            if (info[i].isEmpty()) {
                messageBuilder.append("- Le champs [" + ApplicationMenu.LIVRE_PARAMS[i] + "] est vide\n");
                valide = false;
            } else if (i == 0 || i == 3 || i == 4 || i == 5) {
                try {
                    Integer.valueOf(info[i]);
                } catch (Exception ex) {
                    messageBuilder.append("- Le champs [" + ApplicationMenu.LIVRE_PARAMS[i] + "] doit être entier\n");
                    valide = false;
                }
            }
        }
        return valide ? "ok" : messageBuilder.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Signatures bdd = Bdd.getInstance();

        Object composant = e.getSource();

        if (composant == addBouton) {

            String[] info = new String[nombreParams];
            for (int i = 0; i < nombreParams; i++) {
                info[i] = textFields[i].getText();
            }

            // Nous savons s'il faut ajouter un auteur ou un livre à partir du nombre de champs de saisie dans la boîte de dialogue
            // Nombre de champs de saisie 3 = Ajouter auteur
            if (nombreParams == 3) {
                addAuteur(bdd, info);

            } else {
                addLivre(bdd, info);
            }
        }
    }

    private void addAuteur(Signatures bdd, String[] info) {
        String resultMessage = validateAuteurInfo(info);
        if (resultMessage.equals("ok")) {

            // verifier si l'auteur exist déjà dans la map
            int oldSize = ((Bdd) bdd).getMap().keySet().size();
            bdd.addAuteur(new Auteur(info));
            int newSize = ((Bdd) bdd).getMap().keySet().size();
            if (newSize == oldSize) {
                JOptionPane.showMessageDialog(null, "Vérifiez que:\n" +
                                "- Le code de l'auteur n'existe pas déjà\n" +
                                "- Le nom de l'auteur n'existe pas déjà",
                        "Attention", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "L'auteur a été ajouter avec succés", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, resultMessage, "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addLivre(Signatures bdd, String[] info) {
        String resultMessage = validateLivreInfo(info);
        if (resultMessage.equals("ok")) {

            // verifier si le livre exist déjà dans la map
            int oldSize = ((Bdd) bdd).getMap().get(new Auteur(Integer.valueOf(info[3]))).size();
            bdd.addLivre(new Livre(info));
            int newSize = ((Bdd) bdd).getMap().get(new Auteur(Integer.valueOf(info[3]))).size();
            if (newSize == oldSize) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vérifiez que:\n" +
                                "- Le code du livre n'existe pas déjà\n" +
                                "- Le nom du livre n'existe pas déjà\n" +
                                "- Le code de l'auteur existe dans la base de données",
                        "Attention",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Le livre a été ajouter avec succés", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, resultMessage, "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
