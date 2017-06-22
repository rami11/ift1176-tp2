package tp1;

import java.io.IOException;
import java.util.Collection;

public interface Signatures {

	public void lireBddAut(String nomFichier) throws IOException;

	/*Pr�voyez un constructeur de la classe Auteur comme suit :
	  public Auteur(String nom,int code,String pays)*/
	public void addAuteur(Auteur a);

	public void lireBddLivre(String nomFichier) throws IOException;

	/*Pr�voyez un constructeur de la classe Livre comme suit :
	 public Livre(String titre, int codeLivre, int codeAuteur,
	              String categorie, int nbPages, double prix) */
	public void addLivre(Livre l);

	public Auteur getAuteur(String nom);

	public Auteur getAuteur(int codeAuteur);

	public Livre getLivre(String titre);

	public Livre getLivre(int codeLivre);

	public Collection getColLivresAut(Auteur unAuteur);

	public void rapportParAuteurs() throws IOException;

	public void rapportParLivres() throws IOException;

}

