package tp1;

import java.io.IOException;
import java.util.Collection;

@Deprecated
public class Tp1 {

    public static void main(String[] args) throws IOException {

        Signatures BdDonnees = new Bdd();

        BdDonnees.lireBddAut("Auteurs.txt");
        BdDonnees.lireBddLivre("Livres.txt");

        Auteur unAuteur = new Auteur("KING, STEPHEN", 1, "USA");
        BdDonnees.addAuteur(unAuteur); //code existe d�j�, doit �tre ignor�

        unAuteur = new Auteur("KING, STEPHEN", 321, "USA");
        BdDonnees.addAuteur(unAuteur);

        Livre unLivre = new Livre("Carrie", 222, 0, "Roman", 645, 7.99);
        BdDonnees.addLivre(unLivre); //auteur inexistant, � ignorer

        unLivre = new Livre("Carrie", 222, 321, "Roman", 645, 7.99);
        BdDonnees.addLivre(unLivre);

        unLivre = new Livre("LISTE MORTELLE", 232, 154, "Roman", 439, 17.99);
        BdDonnees.addLivre(unLivre);


        //Pour afficher les livres d'un auteur par son nom
        unAuteur = BdDonnees.getAuteur("VERNE, JULES");
        Collection oeuvres = BdDonnees.getColLivresAut(unAuteur);
        if (oeuvres != null)  /* pr�voyez la m�thode getNom dans Auteur */
            System.out.println("Les oeuvres de " + unAuteur.getNom() + "\n" + oeuvres);

        //Pour afficher les livres d'un auteur par son code
        unAuteur = BdDonnees.getAuteur(39);
        oeuvres = BdDonnees.getColLivresAut(unAuteur);
        if (oeuvres != null)
            System.out.println("Les oeuvres de " + unAuteur.getNom() + "\n" + oeuvres);

        //afficher un livre selon son titre et afficher aussi le nom l'auteur (pas si �vident)
        unLivre = BdDonnees.getLivre("ROBINSON CRUSOE");
        if (unLivre != null) {
            int codeNum = unLivre.getCodeAuteur();  //pr�voyez cette m�thode dans Livre
            System.out.println(unLivre + " de " + BdDonnees.getAuteur(codeNum).getNom());
        }

        //Cr�er les fichiers de rapports
        BdDonnees.rapportParAuteurs();
        BdDonnees.rapportParLivres();


        System.out.println(BdDonnees);  //Pr�voyez un toString dans Bdd pour faire afficher
        //en ordre de croissant des noms d'auteurs


    }

}

/*
 *  Pr�voyez les m�thodes d'acc�s de Livre
	String getTitre()
	int getCode()
	int getCodeAuteur()
	String getCategorie()
	int getNbPages()
	double getPrix()

	Et pour Auteur :
	String getNom()
	int getCode()
	String getPays()

	Elles pourraient �tre test�es � la correction
 *
 **/