package ch.heigvd.res.lab01.impl;

import java.lang.reflect.Array;
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
    int charPosition = 0;

    if(!lines.isEmpty()){
        while (true) {
            char car = lines.charAt(charPosition);

            if (car == '\n' || car == '\r') {
                if (charPosition+1<lines.length()) {
                    if (car == '\r' && lines.charAt(charPosition + 1) == '\n') {
                        return new String[]{lines.substring(0, charPosition + 2), lines.substring(charPosition + 2, lines.length())};
                    }
                }
                return new String[]{lines.substring(0, charPosition+1), lines.substring(charPosition+1, lines.length())};
            } else {
                charPosition++;
                //pas de à la ligne, il faut tester le suivant char.
                if (lines.length() == charPosition) {
                    return new String[]{"", lines};
                }
            }
        }
    }
    return new String[]{"",""};
  }

}
