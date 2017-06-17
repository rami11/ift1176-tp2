package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by rsn on 2017-06-17.
 */
public class FichiersDonneesPanel extends JPanel {

    public FichiersDonneesPanel() {
        setLayout(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder("Les fichier de données");
        titledBorder.setTitleJustification(TitledBorder.RIGHT);
        setBorder(titledBorder);

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

        add(labelPanel, BorderLayout.WEST);
        add(textFieldPanel, BorderLayout.EAST);
    }
}
