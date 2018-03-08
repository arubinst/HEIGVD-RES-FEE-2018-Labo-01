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

    String[] ret = new String[2];

    if (lines.indexOf("\n") != -1){
      ret[0] = lines.substring(0,lines.indexOf("\n") + 1);
      ret[1] = lines.substring(lines.indexOf("\n") + 1, lines.length());
    }else if (lines.indexOf("\r") != -1){
      ret[0] = lines.substring(0,lines.indexOf("\r") + 1);
      ret[1] = lines.substring(lines.indexOf("\r") + 1, lines.length());
    }else{
      ret[0] = "";
      ret[1] = lines;
    }

    return ret;

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
