package tp1;

/**
 * Created by rsn on 2017-05-11.
 */
public class Auteur implements Comparable<Auteur> {

    private int code;
    private String nom;
    private String pays;

    public Auteur(String nom, int code, String pays) {
        this.nom = nom;
        this.code = code;
        this.pays = pays;
    }

    public Auteur(String[] info) {
        this.code = Integer.valueOf(info[0]);
        this.nom = info[1];
        this.pays = info[2];
    }

    public Auteur(int code) {
        this("", code, "");
    }

    public Auteur(String nom) {
        this(nom, -1, "");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auteur auteur = (Auteur) o;

        return code == auteur.code;
    }

    @Override
    public int hashCode() {
        return code;
    }

    @Override
    public String toString() {
        return getNom();
    }

    @Override
    public int compareTo(Auteur o) {
        return nom.compareTo(o.getNom());
    }

    public String afficherInfo() {

        return String.format("Code:\t%d\nNom:\t%-5s\nPays:\t%-5s",
                this.code, this.nom, this.pays);

        /*StringBuilder builder = new StringBuilder();
        // Code
        builder.append("Code:\t");
        builder.append(this.code);
        builder.append('\n');
        // Nom
        builder.append("Nom:\t");
        builder.append(this.nom);
        builder.append('\n');
        // Pays
        builder.append("Pays:\t");
        builder.append(this.pays);
        builder.append('\n');

        return builder.toString();*/
    }
}
