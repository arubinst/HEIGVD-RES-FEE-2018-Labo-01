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
    int compteur = 1;
    String finalString = "";

    if (chaine.indexOf("\n") != -1) {
      beginIndex = 0;
      endIndex = chaine.indexOf("\n");
      finalString = finalString + (compteur + "\t");
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
      finalString = finalString + (compteur + "\t");
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

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
