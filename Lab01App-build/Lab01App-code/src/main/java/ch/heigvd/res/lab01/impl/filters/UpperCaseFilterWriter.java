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

  /**
   * Writes a portion of a string.
   *
   * @param  str  String to be written
   * @param  off  Offset from which to start reading characters
   * @param  len  Number of characters to be written
   *
   * @exception  IOException  If an I/O error occurs
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++) {
      super.write(Character.toUpperCase(str.charAt(i)));
    }
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++) {
      this.write(cbuf[i]);
    }
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
