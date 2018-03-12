package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author kevin Pradervand
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

    // on visite le dossier root
    visitor.visit(rootDirectory);

    if (rootDirectory.isDirectory()){
      File[] fileListe = rootDirectory.listFiles();
      if (fileListe != null){
        Arrays.sort(fileListe);
      }


      if (fileListe != null){
        //on parcours les fichier et dossier.
        for (File file: fileListe){
          System.out.println(file);
          if(file.isFile()){
            visitor.visit(file);
          }
        }
        for (File file : fileListe){
          if(file.isDirectory()){
            // si on tombe sur un dossier on par dans le recursif
            explore(file,visitor);
          }
        }
      }
    }

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
