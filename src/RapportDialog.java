import javax.swing.*;
import java.awt.*;

/**
 * Created by rsn on 2017-06-13.
 */
public class RapportDialog extends JDialog {

    public RapportDialog(String titre, String contenu) {
        setTitle(titre);

        JTextArea textArea = new JTextArea(40, 50);
        textArea.setText(contenu);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
