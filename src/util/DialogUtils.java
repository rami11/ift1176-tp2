package util;

import tp1.Auteur;
import tp1.Livre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rsn on 2017-06-12.
 */
public class DialogUtils {

    public static void showMessageDialog(Component parent, Object object) {

        if (object != null) {
            if (object instanceof Auteur) {
                JOptionPane.showMessageDialog(
                        parent,
                        ((Auteur) object).afficherInfo(),
                        "Info de l'auteur",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("icon_auteur_petit.png"));

            } else if (object instanceof Livre) {

            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "N/A",
                    "Info",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void showAppInfoDialog(Component parent) {
        JOptionPane.showMessageDialog(
                parent,
                "Université de montreal\nIFT-1176 (TP2)\nDroits d'auteur\u00a9 Rami Serapian 2017",
                "Info",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("udem_icon_petit.jpg"));
    }

}
