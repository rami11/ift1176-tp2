package tp1;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by rsn on 2017-05-11.
 */
public interface Signatures {

    public void lireBddAut(String nomFichier) throws IOException;

    public void addAuteur(Auteur a);

    public void lireBddLivre(String nomFichier)throws IOException;

    public void addLivre(Livre l);

    public Auteur getAuteur(String nom);

    public Auteur getAuteur(int codeAuteur);

    public Livre getLivre(String titre);

    public Livre getLivre(int codeLivre);

    public Collection getColLivresAut(Auteur unAuteur);
    public void rapportParAuteurs()throws IOException;
    public void rapportParLivres()throws IOException;
}
