package ch.heigvd.res.lab01.impl;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  public static char RETURN_R = '\r';
  public static char RETURN_N = '\n';

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
    String[] result = new String[2];
    boolean find = false;

    for (int i=0; i<lines.length() && find == false; i++) {
      if(lines.charAt(i) == RETURN_R) {
        if (i == lines.length()-1) { // last char
          result[0] = lines.substring(0, i+1);
          result[1] = lines.substring(i+1, lines.length());
          find = true;
        } else {
          if(lines.charAt(i+1) == RETURN_N) {
            result[0] = lines.substring(0,i+2);
            result[1] = lines.substring(i+2, lines.length());
          } else {
            result[0] = lines.substring(0,i+1);
            result[1] = lines.substring(i+1, lines.length());
          }
          find = true;
        }
      } else if (lines.charAt(i) == RETURN_N || lines.charAt(i) == RETURN_R) {
      result[0] = lines.substring(0, i+1);
      result[1] = lines.substring(i+1, lines.length());
      find = true;
      }
    }
    if (find == false) {
      result[0] = "";
      result[1] = lines;
    }
    return result;
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
