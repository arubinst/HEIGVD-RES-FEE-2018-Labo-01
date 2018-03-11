package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.impl.transformers.FileTransformer;
import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

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
  public void explore(File rootDirectory, IFileVisitor vistor) throws IOException {
    vistor.visit(rootDirectory);

    if (rootDirectory.isDirectory()) {
      File[] list_file = rootDirectory.listFiles();
      int length = list_file.length;
      if (length > 0) {
        for (int i = 0; i < length; i++) {
          if (list_file[i].isFile())
            vistor.visit(list_file[i]);
        }

        for (int i = 0; i < length; i++) {
          if (list_file[i].isDirectory())
            explore(list_file[i], vistor);
        }
      }
    }
  }
}