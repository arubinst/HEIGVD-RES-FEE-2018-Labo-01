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

  private boolean first_visit = false;

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    /*File[] list_file = rootDirectory.listFiles();
    for (int i = 0; i < list_file.length; i++) {
      vistor.visit(list_file[i]);
      if (list_file[i].isDirectory()) {
        explore(list_file[i], vistor);
      }
    }
    */

    if (!first_visit){
      vistor.visit(rootDirectory);
      first_visit = true;
    }
    if (rootDirectory.isDirectory()) {
      for (File list_files : rootDirectory.listFiles()) {
        if (list_files.isDirectory()) {
          vistor.visit(list_files);
          for (File list_file_2nd : list_files.listFiles()) {
            if (list_file_2nd.isFile()) {
              vistor.visit(list_file_2nd);
            }
          }
          explore(list_files, vistor);
        }
      }
    }


  }
}
