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

    /*@Override
    public int compareTo(tp1.Auteur o) {
        *//*int res = code - o.code;
        if (res != 0) {
            return res;
        } else {
            return getNom().compareTo(o.getNom());
        }*//*
        return code - o.code;
    }*/

    @Override
    public int compareTo(Auteur o) {
        return nom.compareTo(o.getNom());
    }
}
