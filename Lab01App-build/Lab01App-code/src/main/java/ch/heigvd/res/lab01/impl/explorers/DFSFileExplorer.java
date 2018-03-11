package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {
    private StringBuilder chaine = new StringBuilder();
  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

      if (chaine.length() == 0) {
          vistor.visit(rootDirectory);
          chaine.append(rootDirectory + "\n");
      }

      if(rootDirectory.isDirectory()) {

          for (File fileEntry : rootDirectory.listFiles()) {
              if (fileEntry.isDirectory()) {
                  vistor.visit(fileEntry);
                  chaine.append(fileEntry.getPath() + "\n");
                  for (File file : fileEntry.listFiles()) {
                      if (!file.isDirectory()) {
                          vistor.visit(file);
                          chaine.append(file.getPath() + "\n");
                      }
                  }
                  explore(fileEntry, vistor);
              }
          }
      }
  }
}