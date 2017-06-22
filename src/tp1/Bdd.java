package tp1;

import java.io.*;
import java.util.*;

// implementer une map d'auteurs de la collections de livre ecrit par ces auteurs
public class Bdd implements Signatures {
    private static final Signatures bdd = new Bdd();
    private Map<Auteur, TreeSet<Livre>> m;

    public Bdd() {
        m = new LinkedHashMap<Auteur, TreeSet<Livre>>(100);
    }

    public static Signatures getInstance() {
        return bdd;
    }

    public void clear() {
        m.clear();
    }

    // lire un fichier d'auteur et remplis la map
    public void lireBddAut(String nomFichier) throws IOException {

        boolean existeFichier = true; // � ajuster apr�s

        FileReader fr = null; // initialiser pour Java

        // essayer de LOCALISER le fichier � partir de son nom
        try {
            fr = new FileReader(nomFichier);
        }
        // intercepter l'erreur si le fichier n'existe pas
        catch (FileNotFoundException erreur) {
            System.out.println("Probleme d'ouvrir le fichier " +
                    nomFichier);
            existeFichier = false; // ajuster
        }

        if (existeFichier) {

            // construire l'objet d'entr�e qui va permettre
            // d'appliquer la lecture d'une ligne de texte

            BufferedReader entree = new BufferedReader(fr);
            boolean finFichier = false;

            while (!finFichier) {

                // lire une ligne
                String uneLigne = entree.readLine();

                if (uneLigne == null)
                    finFichier = true;
                else {
                    String champ[] = uneLigne.split("\t");
                    Auteur test = new Auteur(champ[1], Integer.parseInt(champ[0]), champ[2]);
                    this.addAuteur(test);
                }

            }
            entree.close();
        }
    }

    // ajouter un auteur a la map (ignore si un auteur avec le meme code existe
    public void addAuteur(Auteur a) {
        // verifie si deja la, sinon ajoute
        if (!m.containsKey(a)) {
            TreeSet<Livre> livre = new TreeSet<Livre>();
            m.put(a, livre); // cree auteur avec TreeSet vide
        }
    }

    // lire un fichier d'auteur et remplis les valeur de la map, un auteur doit etre pre-existant
    public void lireBddLivre(String nomFichier) throws IOException {

        boolean existeFichier = true; // � ajuster apr�s

        FileReader fr = null; // initialiser pour Java

        // essayer de LOCALISER le fichier � partir de son nom
        try {
            fr = new FileReader(nomFichier);
        }
        // intercepter l'erreur si le fichier n'existe pas
        catch (FileNotFoundException erreur) {
            System.out.println("Probleme d'ouvrir le fichier " + nomFichier);
            existeFichier = false; // ajuster
        }

        if (existeFichier) {

            // construire l'objet d'entr�e qui va permettre
            // d'appliquer la lecture d'une ligne de texte

            BufferedReader entree = new BufferedReader(fr);
            boolean finFichier = false;

            while (!finFichier) {

                // lire une ligne
                String uneLigne = entree.readLine();

                if (uneLigne == null)
                    finFichier = true;
                else {
                    String champ[] = uneLigne.split("\t");
                    Livre test = new Livre(champ[1], Integer.parseInt(champ[0]), Integer.parseInt(champ[3]), champ[2], Integer.parseInt(champ[5]), Double.parseDouble(champ[4]));
                    this.addLivre(test);
                }
            }
            entree.close();
        }
    }

    // ajouter un livre a la collection d'un auteur
    public void addLivre(Livre l) {
        // verifie si l'auteur est deja la, sinon rien
        Auteur aTemp = new Auteur(l.getCodeAuteur());
        if (m.containsKey(aTemp)) {
            TreeSet<Livre> livres = m.get(aTemp);
            livres.add(l);
            //m.put(aTemp, livres);
        }
    }

    // generer un rapport des livres ecrit pour chaque auteur et enregistre dans un fichier
    // pas tr�s efficace puisque recherche auteur 2 fois.
    // Traitement : obtenir une collection tri�e des auteurs, it�rer cette collection et, pour
    // chacun des auteurs, obtenir sa collection de livre.  Cette derni�re op�ration est ind�pendante
    // de l'it�ration sur la collection des auteurs, et donc, n�cessite une recherche des auteurs dans
    // la map.

//	public void rapportParAuteurs()throws IOException{
//		String sortie = "";
//		LinkedList<Auteur> sortedListe = new LinkedList<Auteur>(m.keySet());
//		Collections.sort(sortedListe, new ParNom());
//		for(Auteur a:sortedListe){
//			sortie += a.toString() + " :\n";
//			Collection<Livre> livres = getColLivresAut(a);
//			for(Livre l:livres){
//				sortie += "       " + l.toString()+"\n";
//			}
//		}
// 		PrintWriter fichier = new PrintWriter(new FileWriter("parAuteur.txt"));
//		fichier.print(sortie);
//		fichier.close();
//	}


    // generer un rapport des livres ecrit pour chaque auteur et enregistre dans un fichier
    // utilisant Map.Entry et comparator
    public void rapportParAuteurs() throws IOException {
        String sortie = "";
        TreeSet<Map.Entry<Auteur, TreeSet<Livre>>> ts = new TreeSet<Map.Entry<Auteur, TreeSet<Livre>>>(new ParNomMap());
        ts.addAll(m.entrySet());
        for (Map.Entry<Auteur, TreeSet<Livre>> a : ts) {
            sortie += a.getKey() + " :\n";
            Collection<Livre> livres = a.getValue();
            if (livres.isEmpty()) {
                sortie += "       N/A\n";
            } else {
                for (Livre l : livres) {
                    sortie += "       " + l.toString() + "\n";
                }
            }
            sortie += "\n";
        }
        PrintWriter fichier = new PrintWriter(new FileWriter("parAuteur.txt"));
        fichier.print(sortie);
        fichier.close();
    }


    // generer un rapport des livres et leur auteur et enregistre dans un fichier
    // Pour �viter de trier tous les livres et ensuite rechercher l'auteur de chacun,
    // j'utilise une classe interne qui d�rive Livre et permet de regrouper les infos
    // du livre avec le nom de l'auteur.
    public void rapportParLivres() throws IOException {
        // class permettant d'inclure le nom de l'auteur
        class LivreSigne extends Livre {
            String nomAuteur;

            public LivreSigne() {
                super();
                nomAuteur = "";
            }

            public LivreSigne(String titre, String categorie, int nbPages, double prix, String nomAuteur) {
                super(titre, 0, 0, categorie, nbPages, prix);
                this.nomAuteur = nomAuteur;
            }

            public String toString() {
                return super.toString() + "   " + nomAuteur;
            }

            public int compareTo(Livre autre) {
                int res = super.compareTo(autre);
                if (res != 0)
                    return res;
                else if (autre instanceof LivreSigne) {
                    LivreSigne unAutre = (LivreSigne) autre;
                    return nomAuteur.compareTo(unAutre.nomAuteur);
                } else
                    return 0;
            }
        }


        // recuperer la liste des livres dans la map
        LinkedList<LivreSigne> livres = new LinkedList<LivreSigne>();
        for (Map.Entry<Auteur, TreeSet<Livre>> iter : m.entrySet()) {
            for (Livre l : iter.getValue()) {  // pour chaque livre cree un object LivreSigne
                livres.add(new LivreSigne(l.getTitre(), l.getCategorie(), l.getNbPages(), l.getPrix(), ((Auteur) iter.getKey()).getNom()));
            }
        }
        Collections.sort(livres);
        String sortie = "";
        for (LivreSigne l : livres) {
            sortie += l.toString() + "\n";
        }

        PrintWriter fichier = new PrintWriter(new FileWriter("parLivre.txt"));
        fichier.print(sortie);
        fichier.close();
    }


// M�thode Alternative qui r�cup�re l'ensemble des valeurs, les tries puis fait une it�ration
// tout en cherchant l'auteur correspondant.  Cette m�thode n'est pas tr�s efficace.
//
// Traitement :
//  1- R�cup�rer le entrySet de la map
//  2- R�cup�rer les Values de la map, les placer dans un TreeSet
//  3- It�rer sur le TreeSet
//  4- It�rer sur le entrySet en utilisant contains du getValue pour
//     d�terminer si le livre est pr�sent pour cet auteur.
//  5- Ajouter, dans le String de sortie, les infos du livre et le nom de l'auteur.
//  � consid�rer, affiche-t-on tous les auteurs d'un livre ou un seul?  Ici, tous affich�s.
//  Remarque: Cette m�thode ne fait pas parti de Signature, pour la tester, l'objet doit
//  �tre de type Bdd.

    public void rapportParLivresAlt() throws IOException {
        // R�cup�rer les Map.Entry pour lier Auteurs et livres
        Set<Map.Entry<Auteur, TreeSet<Livre>>> leTout = m.entrySet();

        // recuperer l'ensemble de tous les livres � partir des ensembles
        // de livres de chacun des auteurs.
        TreeSet<Livre> livres = new TreeSet<Livre>();
        for (TreeSet<Livre> ts : m.values()) {
            livres.addAll(ts);
        }
        String sortie = "";

        for (Livre l : livres) {

            // Oups! j'avais oubli� que le code de l'auteur est disponible dans Livre
        /*	for(Map.Entry<Auteur,TreeSet<Livre>> laPaire: leTout){
                if((laPaire.getValue()).contains(l)){
					sortie += l.toString() + "   " + laPaire.getKey().getNom()+ "\n";
				}
			}
			*/

            sortie += l.toString() + "   " + getAuteur(l.getCodeAuteur()).getNom() + "\n";
        }

        PrintWriter fichier = new PrintWriter(new FileWriter("parLivreAlt.txt"));
        fichier.print(sortie);
        fichier.close();
    }


    // Celui demand� dans l'�nonc�
    public Auteur getAuteur(Auteur autre) {
        for (Auteur iter : m.keySet()) {
            if (autre.equals(iter))
                return iter;
        }
        return null;

    }


    // parcourir la liste de key a la recherche d'un nom d'auteur
    public Auteur getAuteur(String nom) {
        for (Auteur iter : m.keySet()) {
            if (nom.toUpperCase().equals(iter.getNom().toUpperCase()))
                return iter;
        }
        return null;
    }

    // parcourir la liste des key a la recherche d'un nom d'auteur
    public Auteur getAuteur(int codeAuteur) {
        for (Auteur iter : m.keySet()) {
            if (codeAuteur == iter.getCode())
                return iter;
        }
        return null;
    }

    // parcourir la liste des values a la recherche d'un titre de livre
    public Livre getLivre(String titre) {
        String titreTemp = titre.toUpperCase();
        for (Map.Entry<Auteur, TreeSet<Livre>> iter : m.entrySet()) {
            for (Livre l : iter.getValue()) {
                if (l.getTitre().toUpperCase().equals(titreTemp))
                    return l;
            }
        }
        return null;
    }

    // parcourir la liste des values a la recherche d'un code de livre
    public Livre getLivre(int codeLivre) {
        for (Map.Entry<Auteur, TreeSet<Livre>> iter : m.entrySet()) {
            for (Livre l : iter.getValue()) {
                if (l.getCode() == codeLivre)
                    return l;
            }
        }
        return null;
    }

    // retourner la collection de livre ecrit par un auteur
    public Collection<Livre> getColLivresAut(Auteur unAuteur) {
        return m.get(unAuteur);
    }

//	// retourner les cle valeur de la map de facon lisible mais en ordre d'insertion
//    public String toString(){
//        String retour = "";
//		for(Map.Entry<Auteur,TreeSet<Livre>> iter:m.entrySet()){
//            Auteur a = iter.getKey();
//            retour += a.toString() + "\n";  //auteur
//            for(Livre l:iter.getValue()){
//            	retour += "  " + l.toString() + "\n"; // chacun des livre de l'auteur
//            }
//            retour +=  "\n";
//		}
//        return retour;
//    }

    // retourner les cle valeur de la map de facon lisible en ordre croissant
    // en utilisant Comparator ParNom, d�fini dans Auteur.java
    public String toString() {
        String retour = "";

        // Pour obtenir une Map tri�e en ordre alternative, je me cr�e un
        // comparator ParNom, ensuite une nouvelle map en utilisant le constructeur
        // pouvant recevoir le comparator et j'y ajoute la map initiale.
        ParNom c = new ParNom();
        TreeMap<Auteur, TreeSet<Livre>> tm = new TreeMap<Auteur, TreeSet<Livre>>(c);
        tm.putAll(m);

        // It�ration sur les paires de la Map alternative
        for (Map.Entry<Auteur, TreeSet<Livre>> iter : tm.entrySet()) {
            Auteur a = iter.getKey();
            retour += a.toString() + "\n";  //auteur
            for (Livre l : iter.getValue()) {
                retour += "  " + l.toString() + "\n"; // chacun des livre de l'auteur
            }
            retour += "\n";
        }
        return retour;
    }


// retourner les cle valeur de la map de facon lisible en ordre croissant auteur.
// Pas l'ordre recherch�, car compareTo de la classe Auteur v�rifie code

//  public String toString(){
//        String retour = "";
//        TreeMap<Auteur,TreeSet<Livre>> tm = new TreeMap<Auteur,TreeSet<Livre>>(m);
//		for(Map.Entry<Auteur,TreeSet<Livre>> iter:tm.entrySet()){
//            Auteur a = iter.getKey();
//            retour += a.toString() + "\n";  //auteur
//            for(Livre l:iter.getValue()){
//            	retour += "  " + l.toString() + "\n"; // chacun des livre de l'auteur
//            }
//            retour +=  "\n";
//		}
//        return retour;
//    }

}


// Classe pour comparer des Map.Entry d'auteurs/livres en fonction du nom de l'auteur.
// Utilis�e pour le fichier rapport par auteur.

class ParNomMap implements Comparator<Map.Entry<Auteur, TreeSet<Livre>>> {
    public int compare(Map.Entry<Auteur, TreeSet<Livre>> e1, Map.Entry<Auteur, TreeSet<Livre>> e2) {
        ParNom pn = new ParNom();     //Creer un objet comparator pour Auteur (voir Auteur.java)

        return pn.compare(e1.getKey(), e2.getKey());   //Extraire les auteurs et utiliser comparator ParNom
    }
}

