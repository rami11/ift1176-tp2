package tp1;

import java.util.Comparator;

// classe servant a stocker les infos d'un auteur
public class Auteur implements Comparable<Auteur> {
    private int codeAuteur;
    private String nom;
    private String pays;

    public Auteur(String[] info) {
        this.codeAuteur = Integer.valueOf(info[0]);
        this.nom = info[1];
        this.pays = info[2];
    }

    public Auteur() {
        codeAuteur = 0;
        nom = "";
        pays = "";
    }

    // pour la recherche dans une map
    public Auteur(int code) {
        this.codeAuteur = code;
        nom = "";
        pays = "";
    }

    public Auteur(String nom, int codeAuteur, String pays) {
        this.codeAuteur = codeAuteur;
        this.nom = nom;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    int getCode() {
        return codeAuteur;
    }

    String getPays() {
        return pays;
    }

    // comparaison selon le nom de l'auteur
    public int compareTo(Auteur autre) {
        //Auteur autre = (Auteur)obj;
        return codeAuteur - autre.codeAuteur;
    }

    // egalit� selon le code de l'auteur
    public boolean equals(Object obj) {
        if (this == obj) // comparer a lui-meme
            return true;
        if (!(obj instanceof Auteur))
            return false;
        Auteur autre = (Auteur) obj;
        return (codeAuteur == autre.codeAuteur) ? true : false;
    }

    public int hashCode() {
        return (new Integer(codeAuteur)).hashCode();
    }

    public String toString() {
        return nom;
        //return codeAuteur + " " + nom + " " + pays;
    }

    public String afficherInfo() {
        return String.format("Code:\t%d\nNom:\t%-5s\nPays:\t%-5s",
                this.codeAuteur, this.nom, this.pays);
    }

}

class ParNom implements Comparator<Auteur> {
    public int compare(Auteur a1, Auteur a2) {
        return a1.getNom().compareToIgnoreCase(a2.getNom());

    }
}
