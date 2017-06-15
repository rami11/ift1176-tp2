import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rsn on 2017-06-13.
 */
public class IOUtils {

    public static String lireFichierTexte(String nom) {
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nom))) {
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                contenu.append(ligne);
                contenu.append('\n');

                ligne = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        return contenu.toString();
    }
}
