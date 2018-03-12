package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Kevin Pradervand
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //avec le super on retourne vers la classe déco Filenumberline directement
    //on utilise le toUpperCase pour passer le tout en majuscule.
    super.write(str.toUpperCase(), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // ici il faut juste transformer en string puis la renvoyer en tableau de char
    String strToUpper = String.valueOf(cbuf).toUpperCase();
    super.write(strToUpper.toCharArray(),off,len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    c = Character.toUpperCase(c);
    out.write(c);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
