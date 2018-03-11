package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    private StringBuilder path = new StringBuilder();

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        try {

            /* Check it the private variable is empty or not. Call the method of
             the parameter vistor. It is to visit the folder. Then it adds the 
             name of the root directory (relative path). */
            if (path.length() == 0) {
                vistor.visit(rootDirectory);
                path.append(rootDirectory + "\n");
            }

            /* Check all files in the root folder. Here File can be a file
               or a directory. */
            for (File fileList : rootDirectory.listFiles()) {

                /* Check if it is a folder */
                if (fileList.isDirectory()) {

                    /* Visit the folder */
                    vistor.visit(fileList);
                    /* Add the path to the variable path */
                    path.append(fileList.getPath() + "\n");

                    for (File file : fileList.listFiles()) {
                        /* Check if File is a file */
                        if (file.isFile()) {
                            /* Visit the file */
                            vistor.visit(file);
                            /* Add the path to the variable path */
                            path.append(file.getPath() + "\n");
                        }
                    }
                    /* Recursive call */
                    explore(fileList, vistor);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
