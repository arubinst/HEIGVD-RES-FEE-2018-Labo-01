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
    private StringBuilder path = new StringBuilder();
  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) { 
      try{
          if(path.length() == 0){
              vistor.visit(rootDirectory);
              path.append(rootDirectory + "\n");
          }
          
          //if(rootDirectory.isDirectory()){
            for(File fileList : rootDirectory.listFiles()){
                if(fileList.isDirectory()){
                    //System.out.println(path);
                    //System.out.println(fileList.getPath());
                    vistor.visit(fileList);
                    path.append(fileList.getPath() + "\n");
                    

                    for(File file : fileList.listFiles()){
                          if(!file.isDirectory()){
                              vistor.visit(file);
                              path.append(file.getPath() + "\n");
                          }   
                    }
                    explore(fileList, vistor);
                }
            }
         // }
          
          /*
          String s[] = rootDirectory.list();
          
          for(int i = 0; i < s.length; i++){
              File dirTemp = new File(rootDirectory.getAbsolutePath() + s[i] + "/");
              
              if(dirTemp.isDirectory()){
                  explore(dirTemp, vistor);
              }
              
              vistor.visit(dirTemp);
          }
          
          */
      }catch(Exception e){
          e.printStackTrace();
      }
    
  }

}
