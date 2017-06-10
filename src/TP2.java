import tp1.Bdd;
import tp1.Signatures;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-09.
 */
public class TP2 extends JFrame {

    public static void main(String[] args) {
        Signatures BdDonnees = new Bdd();

        try {
            BdDonnees.lireBddAut("Auteurs.txt");
            BdDonnees.lireBddLivre("Livres.txt");

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }


        new MainView("IFT-1176 (TP2)");
    }
}
