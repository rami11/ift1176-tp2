import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by rsn on 2017-06-10.
 */
@Deprecated
public class AddAuteurDialog extends JDialog {

    public AddAuteurDialog() {
        setTitle("Ajouter un auteur");
        /*setLayout(new GridLayout(4, 1));*/

        // --------------------------------TEXTE CODE-------------------------------------
        JPanel codePanel = new JPanel();

        JLabel codeLabel = new JLabel("Code");
        JTextField codeTextField = new JTextField(20);

        codePanel.add(codeLabel);
        codePanel.add(codeTextField);

        // --------------------------------TEXTE NAME--------------------------------------
        JPanel nomPanel = new JPanel();

        JLabel nomLabel = new JLabel("Nom");
        JTextField nomTextField = new JTextField(20);

        nomPanel.add(nomLabel);
        nomPanel.add(nomTextField);

        // ---------------------------------TEXTE PAYES-------------------------------------
        JPanel paysPanel = new JPanel();
        JLabel paysLabel = new JLabel("Pays");
        JTextField paysTextField = new JTextField(20);

        paysPanel.add(paysLabel);
        paysPanel.add(paysTextField);

        // ---------------------------------BOUTON AJOUTER-------------------------------------
        JButton addButton = new JButton("Ajouter");


        JPanel contentPanel = new JPanel(new GridLayout(4, 1));
        contentPanel.setBorder(new TitledBorder("Veuillez entrez les valeurs de l'auteur"));

        contentPanel.add(codePanel);
        contentPanel.add(nomPanel);
        contentPanel.add(paysPanel);
        contentPanel.add(addButton);

        add(contentPanel);


        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
