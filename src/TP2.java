import view.MainView;

import javax.swing.*;

/**
 * Created by rsn on 2017-06-09.
 */
public class TP2 extends JFrame {

    public static void main(String[] args) {
        /*Signatures BdDonnees = Bdd.getInstance();

        try {
            BdDonnees.lireBddAut("Auteurs.txt");
            BdDonnees.lireBddLivre("Livres.txt");

            BdDonnees.rapportParAuteurs();
            BdDonnees.rapportParLivres();

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }*/

        JFrame mainView = MainView.getInstance();
        ((MainView) mainView).showView();
    }
}
