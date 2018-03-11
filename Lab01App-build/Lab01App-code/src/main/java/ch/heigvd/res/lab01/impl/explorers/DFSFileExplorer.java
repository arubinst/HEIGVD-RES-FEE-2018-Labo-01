package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    File[] files = rootDirectory.listFiles();
    vistor.visit(rootDirectory);
    
    if (rootDirectory.isDirectory()){
        
        if (files != null){
            //premiere verification -> on verifie tous les fichiers
            for (File currentFile : files){
                if (currentFile.isFile()){
                    explore(currentFile, vistor);
                }
            }
            //deuxieme verification -> on visite les dossier
            for (File currentFile : files){
                if (currentFile.isDirectory()){
                    explore(currentFile, vistor);
                }
            }
        }
    }
    
  }

}
