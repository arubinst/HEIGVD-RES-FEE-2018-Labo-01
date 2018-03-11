package ch.heigvd.res.lab01.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    ArrayList<String> tableau = new ArrayList<>();
    String[] returnTab = new String[2];
    int beginIndex = 0;
    int endIndex = 0;

    if (lines.indexOf("\n") != -1){

      beginIndex = 0;
      endIndex = lines.indexOf("\n");

      while (endIndex != -1) {
        tableau.add(lines.substring(beginIndex, ++endIndex));
        beginIndex = endIndex;
        endIndex = lines.indexOf("\n", endIndex + 1);
      }

      if (tableau.size() == 1) {
        tableau.add("");
      }

      returnTab = tableau.toArray(returnTab);

      return returnTab;
    }
    else if (lines.indexOf("\r") != -1){
      beginIndex = 0;
      endIndex = lines.indexOf("\r");

      while (endIndex != -1) {
        tableau.add(lines.substring(beginIndex, ++endIndex));
        beginIndex = endIndex;
        endIndex = lines.indexOf("\r", endIndex + 1);
      }

      if (tableau.size() == 1) {
        tableau.add("");
      }

      returnTab = tableau.toArray(returnTab);

      return returnTab;
    }
    else{
      returnTab[0] = "";
      returnTab[1] = lines;
      return returnTab;
    }
  }

}
