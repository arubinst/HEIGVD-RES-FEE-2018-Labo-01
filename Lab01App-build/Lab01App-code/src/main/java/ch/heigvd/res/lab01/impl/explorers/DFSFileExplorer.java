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
 * @author Yannis Ansermoz
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        visitor.visit(rootDirectory);
        if(rootDirectory.isDirectory()) {
            File[] filesList = rootDirectory.listFiles();
            if(filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        visitor.visit(file);
                    }
                }
                for (File file : filesList) {
                    if (file.isDirectory()) {
                        explore(file, visitor);
                    }
                }
            }
        }
    }
}