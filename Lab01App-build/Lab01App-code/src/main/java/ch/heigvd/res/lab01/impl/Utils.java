package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String[] SEPARATORS = {"\r\n","\r", "\n"};

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

    /*ArrayList<String> lineList = new ArrayList<String>();
    String[] data = lines.split("(\r\n|\r|\n)+",0);
    String[] result;

    if (data.length == 0) {
      result = new String[] {"", lines};
    } else {
      for (String line: data) {
        lineList.add(line);
        // System.out.println(line);
      }
      result = lineList.toArray(new String[0]);
    }

    return result;*/

    String separator = "";
    int indexSeparator = 0;
    for (String SEPARATOR : SEPARATORS) {
      indexSeparator = lines.indexOf(SEPARATOR);
      if (indexSeparator > -1) {
        separator = SEPARATOR;
        break;
      }
    }
    if( indexSeparator == -1)
      return new String[] {"", lines};

    return new String[] {lines.substring(0, indexSeparator + separator.length()), lines.substring(indexSeparator + separator.length(), lines.length())};
  }

}
