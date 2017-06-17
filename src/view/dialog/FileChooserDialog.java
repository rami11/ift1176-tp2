package view.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by rsn on 2017-06-17.
 */
public class FileChooserDialog extends JFileChooser {
    public static final int AUTEUR = 1;
    public static final int LIVRE = 2;

    private String fichierChoisi;


    public FileChooserDialog(String titre, File currentDirectory) {
        super(currentDirectory);
        /*setDialogTitle("Choisir un fichier texte");*/
        setDialogTitle(titre);

        setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers textes", "txt");
        addChoosableFileFilter(filter);

        /*int result = showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            fichierChoisi = getSelectedFile().getAbsolutePath();
        }*/

    }

    public String getFichierChoisi() {
        return fichierChoisi;
    }
}
