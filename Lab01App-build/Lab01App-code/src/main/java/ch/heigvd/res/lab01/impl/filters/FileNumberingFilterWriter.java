package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int compteur = 1;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String chaine = str.substring(off, off + len);

    int beginIndex = 0;
    int endIndex = 0;
    int lastEndIndex = 0;
    String finalString = "";

    String outContent = out.toString();
    compteur = nbSubstring(outContent, "\t");

    if (compteur == 1) {
      if(!(outContent.length() != 0 && outContent.indexOf("\n") <= 0)){
        finalString = finalString + compteur + "\t";
      }
    }

    if (chaine.indexOf("\r") == -1 || (outContent.indexOf("\r") == -1 && outContent.length() != 0) || chaine.indexOf("\r\n") != -1) {
      beginIndex = 0;
      endIndex = chaine.indexOf("\n");

      while (endIndex != -1) {
        compteur++;
        finalString = finalString + chaine.substring(beginIndex, ++endIndex);
        beginIndex = endIndex;

        lastEndIndex = endIndex;
        endIndex = chaine.indexOf("\n", endIndex + 1);
        lastEndIndex = endIndex == -1 ? lastEndIndex : 0;

        finalString = finalString + compteur + "\t";

      }
      if(lastEndIndex != chaine.length()){
        finalString = finalString + chaine.substring(lastEndIndex, chaine.length());
      }
      out.write(finalString);
    }
    else if (chaine.indexOf("\r") != -1){
      beginIndex = 0;
      endIndex = chaine.indexOf("\r");
      while (endIndex != -1) {
        compteur++;
        finalString = finalString + chaine.substring(beginIndex, ++endIndex);
        beginIndex = endIndex;

        lastEndIndex = endIndex;
        endIndex = chaine.indexOf("\r", endIndex + 1);
        lastEndIndex = endIndex == -1 ? lastEndIndex : 0;

        finalString = finalString + compteur + "\t";
      }
      if(lastEndIndex != chaine.length()){
        finalString = finalString + chaine.substring(lastEndIndex, chaine.length());
      }
      out.write(finalString);
    }
    compteur = 0;
  }

  int nbSubstring(String str, String subStr){
    int index = str.indexOf(subStr);
    int count = 0;
    while (index != -1) {
      count++;
      str = str.substring(index + 1);
      index = str.indexOf(subStr);
    }
    count = count == 0 ? 1 : count;
    return count;
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    String outContent = out.toString();

    if(outContent.length() == 0){
      out.write(compteur + "\t");
      compteur++;
    }

    if((char) c != '\n'){
      out.write((char)c);
    }
    else{
      out.write("\n" + compteur + "\t");
      compteur++;
    }
  }

}
