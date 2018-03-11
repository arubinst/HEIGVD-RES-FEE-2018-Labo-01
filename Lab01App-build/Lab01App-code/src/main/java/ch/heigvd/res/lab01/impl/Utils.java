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
    String[] returnArray = {"",""};
    
    //We are looking for the first '\n' on Windows and Unix systems.
    //we don't need an extra case for Windows because of the "\r\n". 
    //the '\r' is always before the '\n' and will be treated like a normal char.
    int lineSeparatorPosition = lines.indexOf('\n');
    //but, as always, we need an extra treatement for MacOS.
    if(lineSeparatorPosition == -1){
        lineSeparatorPosition = lines.indexOf('\r');
    }

    //1st case, we found a line separator
    //We need the first part of the string with the line separator (no problem with \r\n
    if(lineSeparatorPosition != -1){
        returnArray[0] = lines.substring(0, lineSeparatorPosition + 1);
        returnArray[1] = lines.substring(lineSeparatorPosition+1);
    }   
    //2nd case, no line separator.
    else{
        returnArray[1] = lines;
    }
    return returnArray;
 }
}
