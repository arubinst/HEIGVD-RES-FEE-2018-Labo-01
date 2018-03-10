package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  private static final String LINE_FEED = "\n";
  private static final String CARRIAGE_RETURN = "\r";
  private static final String LINE_FEED_AND_CARRIAGE_RETURN = "\r\n";
  
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
      String[] nextLine = new String[2];
      try{
          
          
          int OS;
          int indexMAC = findCarriageReturn(lines);
          int indexUnix = findLineFeed(lines);
          int indexWindows = findLineFeedAndCarriageReturn(lines);
          
          if(indexUnix > -1){
                if(indexWindows > - 1 && indexUnix == indexWindows){
                    OS = 3; // Windows
                }else{
                    OS = 2; // Unix
                }
          }else if(indexMAC > -1){
              OS = 1; // MAC
          }else{
              OS = 0; // Any separator
          }
          
          if(OS == 0){
              nextLine[0] = "";
              nextLine[1] = lines.substring(0);
          }else if(OS == 3){
              nextLine[0] = lines.substring(0, indexWindows + 2);
              nextLine[1] = lines.substring(indexWindows + 2);
          }else if(OS == 2){
              nextLine[0] = lines.substring(0, indexUnix + 1);
              nextLine[1] = lines.substring(indexUnix + 1);
          }else{
              nextLine[0] = lines.substring(0, indexMAC + 1);
              nextLine[1] = lines.substring(indexMAC + 1);
          }
          
          
          
          
          
      }catch(Exception e){
          e.printStackTrace();
      }
      return nextLine;
  }
  
  
    private static int findLineFeed(String str){
        return str.indexOf(LINE_FEED);
    }
    private static int findCarriageReturn(String str){
        return str.indexOf(CARRIAGE_RETURN);
    }

    private static int findLineFeedAndCarriageReturn(String str){
        return str.indexOf(LINE_FEED_AND_CARRIAGE_RETURN);
    }
}
