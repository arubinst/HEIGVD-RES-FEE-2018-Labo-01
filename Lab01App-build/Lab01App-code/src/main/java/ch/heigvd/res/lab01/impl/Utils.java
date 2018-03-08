package ch.heigvd.res.lab01.impl;

import java.lang.reflect.Array;
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
    StringBuilder ligne = new StringBuilder(lines);
    boolean finish = false;
    int charPosition = 0;
    while (!finish){
      char car = ligne.charAt(charPosition);
      if (car == '\n' || car == '\r'){
            finish = true;
            charPosition = charPosition + 1;
      }
      else{
        //pas de à la ligne, il faut tester le suivant char.
        charPosition = charPosition + 1;
        if (ligne.length() == charPosition){
          charPosition = 0;
          finish = true;}
      }
    }
    return new String[]{ligne.substring(0, charPosition), ligne.substring(charPosition, ligne.length())};
  }

}
