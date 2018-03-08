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
    //if(currentChar == \r or currentChar == \n)
    //return lines.split("\n");
    //find + substring
    int pos = lines.indexOf("\n");
    int pos1 = lines.indexOf("\r");
    if(pos > pos1){
      pos = pos1;
    }
    String[] separated = new String[2];
    separated[0] = lines.substring(0, pos);
    separated[1] = lines.substring(pos);
    return separated;
  }

}
