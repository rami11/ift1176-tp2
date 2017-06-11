import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rsn on 2017-06-10.
 */
public class AddDialog extends JDialog implements ActionListener {

    public AddDialog(String titre, String message, String[] params) {
        setTitle(titre);
        setLayout(new BorderLayout());

        int nombreLignes = params.length;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));
        contentPanel.setBorder(new TitledBorder(message));

        JLabel[] labels = new JLabel[nombreLignes];
        JTextField[] textFields = new JTextField[nombreLignes];

        JPanel labelPanel = new JPanel(new GridLayout(nombreLignes, 1));
        JPanel valeurPanel = new JPanel(new GridLayout(nombreLignes, 1));
        for (int i = 0; i < nombreLignes; i++) {
            labels[i] = new JLabel(params[i]);
            labelPanel.add(labels[i]);

            textFields[i] = new JTextField(20);
            valeurPanel.add(textFields[i]);
        }
        contentPanel.add(labelPanel);
        contentPanel.add(valeurPanel);

        add(contentPanel, BorderLayout.NORTH);

        JButton bouton = new JButton("Ajouter");
        add(bouton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
