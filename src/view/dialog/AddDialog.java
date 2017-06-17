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

        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
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
        contentPanel.add(labelPanel);
        contentPanel.add(valeurPanel);

        add(contentPanel, BorderLayout.NORTH);

        addBouton = new JButton("Ajouter");
        addBouton.addActionListener(this);
        add(addBouton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                bdd.addAuteur(new Auteur(info));
            } else {
                bdd.addLivre(new Livre(info));
            }
        }

    }
}
