package view.dialog;

import tp1.Auteur;
import tp1.Bdd;
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
    private String titre;
    private int nombreParams;

    private JTextField[] textFields;
    private JButton addBouton;

    public AddDialog(String titre, String message, String[] params) {
        this.titre = titre;

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
        return valide ? "L'auteur a été ajouté avec succès" : messageBuilder.toString();
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
        return valide ? "Le livre a été ajouté avec succès" : messageBuilder.toString();
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
                addAuteurOuLivre(bdd, info);

            } else {
                addAuteurOuLivre(bdd, info);
            }

        }
    }

    private void addAuteurOuLivre(Signatures bdd, String[] info) {
        String resultMessage = info.length == 3 ? validateAuteurInfo(info) : validateLivreInfo(info);
        if (resultMessage.contains("succès")) {
            bdd.addAuteur(new Auteur(info));
            JOptionPane.showMessageDialog(null, resultMessage, "Succès", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, resultMessage, "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*private static String validateAuteurInfo(String[] info) {
        String message = "Please do the following\n\n";
        if (info[0].isEmpty()) {
            message += "Le champ [Code] est vide\n";
        }
        if (info[1].isEmpty()) {
            message += "Le champ [Nom] est vide\n";
        }
        if (info[2].isEmpty()) {
            message += "Le champ [Pays] est vide\n";
        }
        if (!info[0].isEmpty()) {
            try {
                Integer.valueOf(info[0]);
                return "L'auteur a été ajouté avec succès";
            } catch (Exception ex) {
                message += "Le champ [Code] doit être entier\n";
            }
        }
        return message;
    }*/

    /*private static String validateLivreInfo(String[] info) {
        String message = "";
        if (info[0].isEmpty()) {
            message += "Le champ [Code] est vide\n";
        }
        if (info[1].isEmpty()) {
            message += "Le champ [Titre] est vide\n";
        }
        if (info[2].isEmpty()) {
            message += "Le champ [Catégorie] est vide\n";
        }
        if (info[3].isEmpty()) {
            message += "Le champ [Code de l'auteur] est vide\n";
        }
        if (info[4].isEmpty()) {
            message += "Le champ [Prix] est vide\n";
        }
        if (info[5].isEmpty()) {
            message += "Le champ [Nombre de pages] est vide\n";
        }
        if (!info[0].isEmpty()) {
            try {
                Integer.valueOf(info[0]);
                return "Le livre a été ajouté avec succès";
            } catch (Exception ex) {
                message += "Le champ [Code] doit être entier\n";
            }
        }
        if (!info[3].isEmpty()) {
            try {
                Integer.valueOf(info[3]);
                return "ok";
            } catch (Exception ex) {
                message += "Le champ [Code de l'auteur] doit être entier\n";
            }
        }
        if (!info[4].isEmpty()) {
            try {
                Integer.valueOf(info[4]);
                return "ok";
            } catch (Exception ex) {
                message += "Le champ [Prix] doit être entier\n";
            }
        }
        if (!info[5].isEmpty()) {
            try {
                Integer.valueOf(info[5]);
                return "ok";
            } catch (Exception ex) {
                message += "Le champ [Nombre de pages] doit être entier\n";
            }
        }
        return message;
    }*/
}
