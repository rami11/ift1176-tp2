package tp1;

/**
 * Created by rsn on 2017-05-11.
 */
public class Livre implements Comparable<Livre> {

    private int code;
    private String titre;
    private String categorie;
    private int codeAuteur;
    private double prix;
    private int nbPage;

    public Livre(String titre, int code, int codeAuteur, String categorie, int nbPage, double prix) {
        this.titre = titre;
        this.code = code;
        this.codeAuteur = codeAuteur;
        this.categorie = categorie;
        this.nbPage = nbPage;
        this.prix = prix;
    }

    public Livre(String[] info) {
        this.code = Integer.valueOf(info[0]);
        this.titre = info[1];
        this.categorie = info[2];
        this.codeAuteur = Integer.valueOf(info[3]);
        this.prix = Double.valueOf(info[4]);
        this.nbPage = Integer.valueOf(info[5]);
    }

    public Livre(String titre) {
        this(titre, -1, -1, "", -1, -1.0);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCodeAuteur() {
        return codeAuteur;
    }

    public void setCodeAuteur(int codeAuteur) {
        this.codeAuteur = codeAuteur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNbPage() {
        return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livre livre = (Livre) o;

        return code == livre.code;
    }

    @Override
    public int hashCode() {
        return code;
    }

    @Override
    public String toString() {
        return titre;
    }

    public String afficher() {
        return String.format("%-35s\t%-10s\t%.2f$\t%d", getTitre(), getCategorie(), getPrix(), getNbPage());
    }

    @Override
    public int compareTo(Livre o) {
        return getTitre().compareTo(o.getTitre());
    }
}
