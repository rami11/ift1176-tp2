package tp1;

// classe servant a stockef les details d'un livre
public class Livre implements Comparable<Livre> {
    private int codeLivre;
    private String titre;
    private String categorie;
    private int codeAuteur;
    private double prix;
    private int nbPages;

    public Livre(String[] info) {
        this.codeLivre = Integer.valueOf(info[0]);
        this.titre = info[1];
        this.categorie = info[2];
        this.codeAuteur = Integer.valueOf(info[3]);
        this.prix = Double.valueOf(info[4]);
        this.nbPages = Integer.valueOf(info[5]);
    }


    public Livre() {
        codeLivre = 0;
        titre = "";
        categorie = "";
        codeAuteur = 0;
        prix = 0;
        nbPages = 0;
    }

    public Livre(String titre, int codeLivre, int codeAuteur, String categorie, int nbPages, double prix) {
        this.codeLivre = codeLivre;
        this.titre = titre;
        this.categorie = categorie;
        this.codeAuteur = codeAuteur;
        this.prix = prix;
        this.nbPages = nbPages;
    }

    public String getTitre() {
        return titre;
    }

    public int getCode() {
        return codeLivre;
    }

    public int getCodeAuteur() {
        return codeAuteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNbPages() {
        return nbPages;
    }

    public double getPrix() {
        return prix;
    }

    // comparaison selon le titre
    public int compareTo(Livre autre) {

        return (titre.compareToIgnoreCase(autre.titre));
    }

    // egalit� selon le code du livre
    public boolean equals(Object obj) {
        if (this == obj) // comparer a lui-meme
            return true;
        if (!(obj instanceof Livre))
            return false;
        Livre autre = (Livre) obj;
        return (codeLivre == autre.codeLivre) ? true : false;

    }

    public String toString() {
        return String.format("%-25.25s %-12s %6.2f$ %d", getTitre(), getCategorie(), getPrix(), getNbPages());
        //return codeLivre + " " + titre + " " + categorie + " " + codeAuteur + " " + prix + " " + nbPages;
    }

}
