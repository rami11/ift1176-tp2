package tp1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Une classe pour gérer la map.
 * Created by rsn on 2017-05-11.
 */
public class Bdd implements Signatures {

    private AuteurCollection auteurCollection;

    public Bdd() {
        auteurCollection = new AuteurCollection();
    }

    /**
     * Permet de lire un fichier d’auteurs passé en argument de créer la map et ajouter les auteurs à la map.
     *
     * @param nomFichier Le nom du fichier
     * @throws IOException Si le fichier n'a pas été trouvé
     */
    @Override
    public void lireBddAut(String nomFichier) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomFichier))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] auteurInfo = line.split("\\t");

                Auteur auteur = new Auteur(auteurInfo);

                auteurCollection.addAuteur(auteur);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Ajoute un auteur, passé en argument, dans la map.  La partie valeur associée est une nouvelle collection vide.
     *
     * @param a L'auteur
     */
    @Override
    public void addAuteur(Auteur a) {
        auteurCollection.addAuteur(a);
    }

    /**
     * Permet de lire les livres à partir d'un fichier passé en argument.
     *
     * @param nomFichier Le nom du fichier
     * @throws IOException Si le fichier n'a pas été trouvé
     */
    @Override
    public void lireBddLivre(String nomFichier) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomFichier))) {

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] livreInfo = line.split("\\t");

                int codeAuteur = Integer.valueOf(livreInfo[3]);
                Livre livre = new Livre(livreInfo);

                auteurCollection.addLivre(codeAuteur, livre);

                line = bufferedReader.readLine();
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Ajoute un objet livre, passé en argument, à la collection de l’auteur dont le code est aussi passé en argument.
     *
     * @param l Le livre
     */
    @Override
    public void addLivre(Livre l) {
        auteurCollection.addLivre(l.getCodeAuteur(), l);
    }

    /**
     * Retourne l’objet tp1.Auteur, se trouvant dans la map associé à un auteur dont le nom de l'auteur
     * est passé en paramètre.
     *
     * @param nom Le nom de l'auteur
     * @return L'auteur
     */
    @Override
    public Auteur getAuteur(String nom) {
        return auteurCollection.getAuteur(nom);
    }

    /**
     * Retourne l’objet tp1.Auteur, se trouvant dans la map associé à un auteur dont le code numérique de l’auteur
     * est passé en paramètre.
     *
     * @param codeAuteur Le code numérique de l'auteur
     * @return L'auteur
     */
    @Override
    public Auteur getAuteur(int codeAuteur) {

        return auteurCollection.getAuteur(codeAuteur);
    }

    /**
     * Retourne l’objet livre, dont le titre est passé en argument.
     *
     * @param titre Le titre du livre
     * @return Le livre
     */
    @Override
    public Livre getLivre(String titre) {

        /*for (tp1.Auteur auteur : auteurLivresMap.keySet()) {
            Set<tp1.Livre> livres = auteurLivresMap.get(auteur);
            for (tp1.Livre livre : livres) {
                if (livre.getTitre().equalsIgnoreCase(titre)) {
                    return livre;
                }
            }
        }*/
        return auteurCollection.getLivre(titre);
    }

    /**
     * Retourne l’objet livre, dont le code numérique du livre est passé en argument.
     *
     * @param codeLivre Le code numérique du livre
     * @return Le livre
     */
    @Override
    public Livre getLivre(int codeLivre) {
        return auteurCollection.getLivre(codeLivre);
    }

    /**
     * Reçoit en paramètre un auteur et retourne sa collection de livre (valeur associée dans la Map)
     *
     * @param unAuteur Un auteur
     * @return La collection de livre de l'auteur
     */
    @Override
    public Collection getColLivresAut(Auteur unAuteur) {
        return auteurCollection.getColLivresAut(unAuteur);
    }

    /**
     * Crée un fichier parAuteur.txt contenant la liste des auteurs et de leurs livres
     * par ordre alphabétique des auteurs puis des livres.
     *
     * @throws IOException
     */
    @Override
    public void rapportParAuteurs() throws IOException {

        try (PrintWriter printWriter = new PrintWriter("parAuteur.txt")) {

            Map<Auteur, Set<Livre>> auteurs = new TreeMap<>(auteurCollection.getAuteurLivresMap());
            for (Auteur auteur : auteurs.keySet()) {
                printWriter.println(auteur + ": ");

                Set<Livre> livres = new TreeSet<>(auteurs.get(auteur));
                if (!livres.isEmpty()) {
                    for (Livre livre : livres) {
                        printWriter.println("\t" + livre.afficher());
                    }
                } else {
                    printWriter.println("\tN/A");
                }
                printWriter.println();
            }

            printWriter.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Crée un fichier parLivre.txt contenant la liste des livres et des auteurs
     * par ordre alphabétique des titres de livre.
     *
     * @throws IOException
     */
    @Override
    public void rapportParLivres() throws IOException {
        try (PrintWriter printWriter = new PrintWriter("parLivre.txt")) {

            Set<String> treeSet = new TreeSet<>();
            for (Map.Entry<Auteur, Set<Livre>> entry : auteurCollection.getAuteurLivresMap().entrySet()) {
                for (Livre livre : entry.getValue()) {
                    treeSet.add(livre.afficher() + "\t" + entry.getKey().getNom());
                }
            }

            for (String livre : treeSet) {
                printWriter.println(livre);
            }

            printWriter.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Pour permettre l’affichage de la map en ordre croissant du nom des auteurs.
     *
     * @return La map
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        Map<Auteur, Set<Livre>> sortedMap = new TreeMap<>(auteurCollection.getAuteurLivresMap());
        for (Auteur auteur : sortedMap.keySet()) {
            builder.append(auteur);
            builder.append(": \n");

            TreeSet<Livre> livreSet = new TreeSet<>(sortedMap.get(auteur));

            builder.append(livreSet);
            builder.append("\n\n");
        }

        return builder.toString();
    }
}
