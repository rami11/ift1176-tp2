package tp1;

import java.util.*;

/**
 * Created by rsn on 2017-06-03.
 */
public class AuteurCollection {

    private Map<Auteur, Set<Livre>> auteurLivresMap;
    private Set<Auteur> auteurSet;
    private Set<Livre> livreSet;

    public AuteurCollection() {
        auteurLivresMap = new LinkedHashMap<>();
        auteurSet = new TreeSet<>();
        livreSet = new TreeSet<>();
    }

    public void clear() {
        auteurLivresMap.clear();
        auteurSet.clear();
        livreSet.clear();
    }

    public void addAuteur(Auteur auteur) {
        auteurLivresMap.put(auteur, new LinkedHashSet<>());
        if (!auteurSet.contains(auteur)) {
            auteurSet.add(auteur);
        }
    }

    public void addLivre(int codeAuteur, Livre livre) {
        Set<Livre> livres = auteurLivresMap.get(new Auteur(codeAuteur));
        if (livres != null) {
            livres.add(livre);
        }

        livreSet.add(livre);
    }

    public Map<Auteur, Set<Livre>> getAuteurLivresMap() {
        return auteurLivresMap;
    }

    public Auteur getAuteur(String nom) {
        List<Auteur> auteurSortedList = new ArrayList<>(auteurSet);
        int auteurIndex = Collections.binarySearch(auteurSortedList, new Auteur(nom));
        return auteurIndex >= 0 ? auteurSortedList.get(auteurIndex) : null;
    }

    public Auteur getAuteur(int codeAuteur) {
        Iterator<Auteur> iterator = auteurSet.iterator();
        while (iterator.hasNext()) {
            Auteur auteur = iterator.next();
            if (auteur.getCode() == codeAuteur) {
                return auteur;
            }
        }
        return null;
    }

    public Collection getColLivresAut(Auteur unAuteur) {
        return auteurLivresMap.get(unAuteur);
    }

    public Livre getLivre(String titre) {
        List<Livre> livreSortedList = new ArrayList<>(livreSet);
        int livreIndex = Collections.binarySearch(livreSortedList, new Livre(titre));

        return livreSortedList.get(livreIndex);
    }

    public Livre getLivre(int codeLivre) {
        Iterator<Livre> iterator = livreSet.iterator();
        while (iterator.hasNext()) {
            Livre livre = iterator.next();
            if (livre.getCode() == codeLivre) {
                return livre;
            }
        }
        return null;
    }
}
