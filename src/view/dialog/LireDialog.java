package view.dialog;

import tp1.Bdd;
import tp1.Signatures;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-17.
 */
@Deprecated
public class LireDialog extends JDialog implements ActionListener {
    private JButton lireAuteurBouton;
    private JButton lireLivreBouton;

    private JTextField auteurTextField;
    private JTextField livreTextField;

    private JButton appliquerBouton;

    private boolean isFichierAuteurChoisi;
    private boolean isFichierLivreChoisi;

    public LireDialog() {
        setTitle("Lire fichiers");
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel mainPanel = buildMainPanel();
        appliquerBouton = new JButton("Appliquer");
        appliquerBouton.addActionListener(this);

        contentPanel.add(mainPanel, BorderLayout.NORTH);
        contentPanel.add(appliquerBouton, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        isFichierAuteurChoisi = false;
        isFichierLivreChoisi = false;

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JPanel buildMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new TitledBorder("Veuiller choisir des fichier de données pour un auteur et un livre"));

        JPanel textFieldsPanel = new JPanel(new GridLayout(2, 1));
        auteurTextField = new JTextField(40);
        auteurTextField.setEnabled(false);
        textFieldsPanel.add(auteurTextField);
        livreTextField = new JTextField(40);
        livreTextField.setEnabled(false);
        textFieldsPanel.add(livreTextField);

        JPanel boutonsPanel = new JPanel(new GridLayout(2, 1));
        lireAuteurBouton = new JButton("Auteur");
        lireAuteurBouton.addActionListener(this);

        Action lireAuteurAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser auteurChooser = new FileChooserDialog("Choisir un fichier de données d'auteur", new File("Auteur/"));
                int result = auteurChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    isFichierAuteurChoisi = true;
                    auteurTextField.setText(auteurChooser.getSelectedFile().getAbsolutePath());
                }
            }
        };
        lireAuteurAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));

        lireAuteurBouton.getActionMap().put("lireAuteurAction", lireAuteurAction);
        lireAuteurBouton.getInputMap().put((KeyStroke) lireAuteurAction.getValue(Action.ACCELERATOR_KEY), "lireAuteurAction");
        boutonsPanel.add(lireAuteurBouton);

        lireLivreBouton = new JButton("Livre");
        lireLivreBouton.addActionListener(this);
        boutonsPanel.add(lireLivreBouton);

        mainPanel.add(textFieldsPanel, BorderLayout.WEST);
        mainPanel.add(boutonsPanel, BorderLayout.EAST);

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Signatures bdd = Bdd.getInstance();
        Object composant = e.getSource();
        if (composant == lireAuteurBouton) {
            JFileChooser auteurChooser = new FileChooserDialog("Choisir un fichier de données d'auteur", new File("Auteur/"));
            int result = auteurChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                isFichierAuteurChoisi = true;
                auteurTextField.setText(auteurChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (composant == lireLivreBouton) {
            JFileChooser livreChooser = new FileChooserDialog("Choisir un fichier de données de livre", new File("Livre/"));
            int result = livreChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                isFichierLivreChoisi = true;
                livreTextField.setText(livreChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (composant == appliquerBouton) {
            if (isFichierAuteurChoisi && isFichierLivreChoisi) {
                try {
                    ((Bdd) bdd).clear();

                    bdd.lireBddAut(auteurTextField.getText());
                    bdd.lireBddLivre(livreTextField.getText());

                    JOptionPane.showMessageDialog(null, "Les fichiers Auteur et Livre ont été lus", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez remplir les deux champs avec les fichiers appropriés afin de réussir", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
