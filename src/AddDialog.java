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

        int nombreLignes = params.length;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(nombreLignes + 1, 1));
        contentPanel.setBorder(new TitledBorder(message));

        JLabel[] labels = new JLabel[nombreLignes];
        JTextField[] textFields = new JTextField[nombreLignes];
        for (int i = 0; i < nombreLignes; i++) {
            labels[i] = new JLabel(params[i]);
            textFields[i] = new JTextField(20);

            JPanel panel = new JPanel();
            panel.add(labels[i]);
            panel.add(textFields[i]);

            contentPanel.add(panel);
        }

        JButton bouton = new JButton("Ajouter");
        contentPanel.add(bouton);

        add(contentPanel);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
