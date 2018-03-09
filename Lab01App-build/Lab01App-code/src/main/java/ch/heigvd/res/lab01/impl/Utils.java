package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String[] SEP = {"\r\n","\r", "\n"};
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
    String separator = "";
    int index = 0;
    for (String sepStr : SEP) {
      index = lines.indexOf(sepStr);
      if (index > -1) {
        separator = sepStr;
        break;
      }
    }
    if( index == -1)
      return new String[] {"", lines};

    return new String[] {lines.substring(0, index + separator.length()), lines.substring(index + separator.length(), lines.length())};
  }

}
