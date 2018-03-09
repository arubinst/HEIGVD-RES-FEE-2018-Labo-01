package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    while(len-- > 0){
      write(str.toUpperCase().charAt(off++));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    while(len-- > 0){
      write(cbuf[off++]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //TODO Change int c to upper int c
    out.write(Character.toUpperCase(c));
  }

}
