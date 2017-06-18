package view.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by rsn on 2017-06-17.
 */
public class FileChooserDialog extends JFileChooser {

    public FileChooserDialog(String titre, File currentDirectory) {
        super(currentDirectory);
        setDialogTitle(titre);

        setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers textes", "txt");
        addChoosableFileFilter(filter);

    }
}
