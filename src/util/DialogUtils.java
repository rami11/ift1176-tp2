package util;

import tp1.Auteur;
import tp1.Bdd;
import tp1.Livre;
import tp1.Signatures;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * Created by rsn on 2017-06-12.
 */
public class DialogUtils {
    private static Signatures bdd = Bdd.getInstance();

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

    public static void showOuvresAuteurDialog(Auteur auteur) {
        if (auteur == null) {
            JOptionPane.showMessageDialog(null,
                    "L'auteur que vous cherchez n'existe pas",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Collection oeuvres = bdd.getColLivresAut(auteur);
            StringBuilder builder = new StringBuilder();
            builder.append("Les oeuvres de ");
            builder.append(auteur.getNom());
            builder.append(":\n");
            if (oeuvres.isEmpty()) {
                builder.append("N/A\n");
            } else {
                for (Object oeuvre : oeuvres) {
                    builder.append(oeuvre);
                    builder.append('\n');
                }
            }
            JOptionPane.showMessageDialog(null,
                    builder.toString(),
                    "Oeuvres de l'auteur", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("icon_auteur_petit.png"));
        }
    }

    public static void showLivreInfoDialog(Livre livre) {
        if (livre == null) {
            JOptionPane.showMessageDialog(null,
                    "Le livre que vous cherchez n'existe pas",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String nomAuteur = bdd.getAuteur(livre.getCodeAuteur()).getNom();

            String message = livre.getTitre() + " de " + nomAuteur + " :\n";
            message += "Code:\t" + livre.getCode() + '\n';
            message += "Titre:\t" + livre.getTitre() + '\n';
            message += "Catégorie:\t" + livre.getCategorie() + '\n';
            message += "Prix:\t" + livre.getPrix() + '\n';
            message += "Nombre de pages:\t" + livre.getNbPages();

            JOptionPane.showMessageDialog(null,
                    message, "Info", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("icon_livre_petit.png"));
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
