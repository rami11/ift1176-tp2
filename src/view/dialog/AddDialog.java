package view.dialog;

import tp1.Auteur;
import tp1.Bdd;
import tp1.Livre;
import tp1.Signatures;

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
        String message = "";
        if (info[0].isEmpty()) {
            message += "Le champ Code est vide\n";
        }
        if (info[1].isEmpty()) {
            message += "Le champ Nom est vide\n";
        }
        if (info[2].isEmpty()) {
            message += "Le champ Pays est vide\n";
        }
        if (!info[0].isEmpty()) {
            try {
                Integer.valueOf(info[0]);
                return "ok";
            } catch (Exception ex) {
                message += "Le champ Code doit être entier\n";
                return message;
            }
        }
        return message;
    }

    private static boolean validateLivreInfo() {
        return false;
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

            if (nombreParams == 3) {
                String resultMessage = validateAuteurInfo(info);
                if (resultMessage.equalsIgnoreCase("ok")) {
                    bdd.addAuteur(new Auteur(info));
                    JOptionPane.showMessageDialog(null, "Success");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, resultMessage);
                }

            } else {
                bdd.addLivre(new Livre(info));
            }

        }

    }
}
