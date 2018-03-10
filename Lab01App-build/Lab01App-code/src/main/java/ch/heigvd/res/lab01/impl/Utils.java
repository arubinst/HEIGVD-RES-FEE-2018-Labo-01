package ch.heigvd.res.lab01.impl;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.StringReader;
import java.util.Scanner;
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
      // Declare variables and StringBuffers
      String[] rows = new String[2];
      StringBuffer sb0 = new StringBuffer();
      StringBuffer sb1 = new StringBuffer();
      boolean metReturn = false;

      if (!lines.contains("\n") && !lines.contains("\r")) {
          rows[0] = "";
          rows[1] = lines;
          return rows;
      }

      // Put 'lines' content into the sb1 StirngBuffer, then move characters to sb0, until a EOL is encountered
      sb1.append(lines);
      for (int i = 0; i < lines.length(); i++) {
          sb0.append(sb1.charAt(0));
          sb1.deleteCharAt(0);
          if (metReturn) break;
          if (sb1.length() != 0 && sb1.charAt(0) == '\n') metReturn = true;             // Line return on UNIX
          if (sb1.length() != 0 && sb1.charAt(0) == '\r') metReturn = true;             // Line return on Mac
          if (sb1.length() > 1 && sb1.charAt(0) == '\r' && sb1.charAt(1) == '\n') {     // Line return on Windows
              metReturn = true;
              sb0.append("\r");
              sb1.deleteCharAt(0);
          }
      }

      // Put the 2 StringBuffers together, in a String array and return it
      rows[0] = sb0.toString();
      rows[1] = sb1.toString();
      return rows;
  }

}
