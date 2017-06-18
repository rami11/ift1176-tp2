package view;

import tp1.Bdd;
import tp1.Signatures;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-17.
 */
public class FichiersDonneesPanel extends JPanel implements ActionListener {

    private static JTextField auteurTextField;
    private static JTextField livreTextField;

    private static JButton appliquerBouton;

    public FichiersDonneesPanel() {

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder("Les fichier de données");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        setBorder(titledBorder);

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel auteurLabel = new JLabel("Auteur");
        JLabel livreLabel = new JLabel("Livre");
        labelPanel.add(auteurLabel);
        labelPanel.add(livreLabel);

        JPanel textFieldPanel = new JPanel(new GridLayout(2, 1));
        auteurTextField = new JTextField(45);
        auteurTextField.setEnabled(false);
        textFieldPanel.add(auteurTextField);
        livreTextField = new JTextField(45);
        livreTextField.setEnabled(false);
        textFieldPanel.add(livreTextField);

        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(textFieldPanel, BorderLayout.EAST);

        appliquerBouton = new JButton("Appliquer");
        appliquerBouton.setEnabled(false);
        appliquerBouton.addActionListener(this);

        contentPanel.add(panel, BorderLayout.NORTH);
        contentPanel.add(appliquerBouton, BorderLayout.SOUTH);

        add(contentPanel);
    }

    public static JTextField getAuteurTextField() {
        return auteurTextField;
    }

    public static JTextField getLivreTextField() {
        return livreTextField;
    }

    public static JButton getAppliquerBouton() {
        return appliquerBouton;
    }

    public static void initAppliquerBouton() {
        appliquerBouton.setEnabled(true);
        appliquerBouton.setText("Appliquer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Signatures bdd = Bdd.getInstance();
        if (!auteurTextField.getText().isEmpty() && !livreTextField.getText().isEmpty()) {

            ((Bdd) bdd).clear();
            try {
                bdd.lireBddAut(auteurTextField.getText());
                bdd.lireBddLivre(livreTextField.getText());
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }

            appliquerBouton.setText("OK");
            appliquerBouton.setEnabled(false);
        }

    }
}
