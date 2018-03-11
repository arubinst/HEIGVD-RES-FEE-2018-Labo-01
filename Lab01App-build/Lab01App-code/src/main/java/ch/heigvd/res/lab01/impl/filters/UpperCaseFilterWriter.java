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

    char[] charArray = str.toCharArray();
    write(charArray, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    // Make sure we do not request a len longer than cbuf
    assert len <= cbuf.length;
    assert off >= 0;  // Offset should be strictly >= 0

    for (int i = off; i < len+off; i++){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    // Make sure char is ASCII
    // assert c >= 32 && c < 127;
    c = Character.toUpperCase((char)c);
    out.append((char)c);
  }

}
