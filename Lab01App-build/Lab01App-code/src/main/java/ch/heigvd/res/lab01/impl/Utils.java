package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  private static String returnLine = "\r";
  private static String newLine = "\n";

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
    String[] nextLines = new String[2];

    if(lines.contains(newLine) || lines.contains(returnLine) || lines.contains(returnLine + newLine)) {
      int minIndex = lines.length();
      int newLineIndex = lines.indexOf(newLine);
      int returnLineIndex = lines.indexOf(returnLine);

      //Search a newLine \n
      if(newLineIndex != -1 && minIndex > newLineIndex) {
        minIndex = newLineIndex;
      }

      //Try to find if we have a better new line \r
      if(returnLineIndex != -1 && minIndex > returnLineIndex) {
        minIndex = returnLineIndex;
      }

      //Case if we have \r\n
      if(returnLineIndex != -1 && returnLineIndex + 1 == newLineIndex) {
        minIndex++;
      }

      minIndex++; //Correction for the substring

      nextLines[0] = lines.substring(0, minIndex);
      nextLines[1] = lines.substring(minIndex, lines.length());

    }
    else {
      nextLines[0] = "";
      nextLines[1] = lines;
    }
    return nextLines;
  }
}
