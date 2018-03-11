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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < str.length() && i < len + off; ++i){
        super.write(Character.toUpperCase(str.charAt(i)));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i <cbuf.length && i < len + off; ++i){
        super.write(Character.toUpperCase(cbuf[i]));
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    super.write(Character.toUpperCase(c));
    //System.out.println(Character.toUpperCase(c));
  }

}
