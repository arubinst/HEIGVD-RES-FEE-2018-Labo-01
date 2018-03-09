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
  private int beginIndex = 0;
  private int endIndex = 0;
  private int lastEndIndex = 0;
  String finalString = "";


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
    if(outContent.equals("")){
      finalString += compteur + "\t";
    }

    while (chaine.length() != 0){
      if(chaine.indexOf("\n") != -1){
        endIndex = chaine.indexOf("\n");
        finalString += chaine.substring(0, endIndex + 1);
        finalString += ++compteur + "\t";
        chaine = chaine.substring(endIndex + 1, chaine.length());
      }
      else if(chaine.indexOf("\r") != -1){
        endIndex = chaine.indexOf("\r");
        finalString += chaine.substring(0, endIndex + 1);
        finalString += ++compteur + "\t";
        chaine = chaine.substring(endIndex + 1, chaine.length());
      }
      else{
        endIndex = endIndex == 0 ? chaine.length() : endIndex;
        endIndex = endIndex > chaine.length() ? chaine.length() : endIndex;
        finalString += chaine.substring(0, endIndex);
        if(endIndex == chaine.length()){
          chaine = "";
        }
        else{
          chaine = chaine.substring(endIndex + 1, chaine.length());
        }
      }
    }
    out.write(finalString);
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
