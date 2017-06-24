package view.dialog;

import tp1.Auteur;
import tp1.Bdd;
import tp1.Livre;
import tp1.Signatures;
import util.DialogUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rsn on 2017-06-11.
 */
public class GetDialog extends JDialog implements ActionListener {
    private String titre;

    private JTextField textField;
    private JButton getButton;

    public GetDialog(String titre, String message) {
        this.titre = titre;
        setTitle(titre);
        setResizable(false);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(message));

        JPanel getPanel = new JPanel(new FlowLayout());
        textField = new JTextField(20);
        getButton = new JButton("Chercher");
        getButton.addActionListener(this);
        getRootPane().setDefaultButton(getButton);

        getPanel.add(textField);
        getPanel.add(getButton);

        contentPanel.add(getPanel, BorderLayout.CENTER);

        add(contentPanel);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Signatures bdd = Bdd.getInstance();

        Object composant = e.getSource();
        if (composant == getButton) {
            if (titre.contains("auteur")) {

                // GET AUTEUR
                Auteur auteur;
                try {
                    int codeAuteur = Integer.valueOf(textField.getText());
                    auteur = bdd.getAuteur(codeAuteur);
                } catch (Exception ex) {
                    auteur = bdd.getAuteur(textField.getText());
                }
                DialogUtils.showOuvresAuteurDialog(auteur);
                textField.setText("");
            } else {
                // GET LIVRE
                Livre livre;
                try {
                    int codeLivre = Integer.valueOf(textField.getText());
                    livre = bdd.getLivre(codeLivre);
                } catch (Exception ex) {
                    livre = bdd.getLivre(textField.getText());
                }
                DialogUtils.showLivreInfoDialog(livre);
                textField.setText("");
            }
        }
    }

}
