package ch.heigvd.res.lab01.impl;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {

    String[] retour = {"",""};
    //recuperer l'index de la nouvelle ligne
    int index = lines.indexOf("\n"); //si on ne trouve rien index vaut -1
    if(index == -1){
      index = lines.indexOf("\r");
    }

    if(-1 == index){ //cas ou il n'y a qu'une seule ligne
      retour[1] = lines;
    }else{
      retour[0] = lines.substring(0,index+1);

      if(lines.length() > index + 1){
        retour[1] = lines.substring(index+1);
      }
    }

    return retour;
  }

}
