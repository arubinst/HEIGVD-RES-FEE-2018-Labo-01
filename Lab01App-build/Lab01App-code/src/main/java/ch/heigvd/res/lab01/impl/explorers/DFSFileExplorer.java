package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

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

        /*
        I would have prefered the following option but could not get it working

        for (File file : rootDirectory.listFiles()) {
            directories = file.listFiles(File::isDirectory);
            files = file.listFiles(File::isFile);
        }
         */

        // https://stackoverflow.com/a/27940412/1336418
        File[] files = rootDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        File[] folders = rootDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        // Start with rootDirectory
        vistor.visit(rootDirectory);

        // Handle files in folder
        if(files != null) {
            for (File file : files) {
                vistor.visit(file);
            }
        }

        // Recursive call for that sub-folder
        if(folders != null) {
            for (File folder : folders) {
                explore(folder, vistor);
            }
        }
    }
}