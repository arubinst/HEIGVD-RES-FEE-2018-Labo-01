package ch.heigvd.res.lab01.impl;

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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String nextLine = "";
    String currentLine = "";
    for(int index = 0; index < lines.length(); ++index){
      /*
      Permet de prendre le cas de windows ou Mac
       */
      if(lines.charAt(index) == '\r'){
        if(index < lines.length() -1 && lines.charAt(index + 1) == '\n') {
          nextLine = lines.substring(index + 2);
          currentLine = lines.substring(0,index + 2);
        }
        else{
          nextLine = lines.substring(index + 1);
          currentLine = lines.substring(0,index + 1);
        }
        break;
      }
      /*
      Permet de prendre le cas de Linux
       */
      else if(lines.charAt(index) == '\n'){
        nextLine = lines.substring(index+1);
        currentLine = lines.substring(0,index +1);
        break;
      }
    }
    /*
    Permet de prendre le cas ou aucun retour de ligne est présent
     */
    if(currentLine.length() == 0){
      nextLine = lines;
    }
    return new String[]{currentLine, nextLine};
  }

}
