package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Kevin Pradervand
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

    String[] separatedString = new String[2];

    String[] newLineChar = {"\r\n","\n","\r"};
    int position = 0;

    //contrôle des différents charactères de fin de ligne
    for (String nL : newLineChar){
      position =  lines.indexOf(nL);
      if (position != -1){
        if (nL == "\r\n") {
          position++;
          position++;
          break;
        }else{
          position++;
          break;
        }
      }
    }
    if(position == -1){
      return new String[]{"", lines};
    }else {

      separatedString[0] = lines.substring(0, position);
      separatedString[1] = lines.substring(position);

      return separatedString;
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
