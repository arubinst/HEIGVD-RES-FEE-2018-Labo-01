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
    String upStr = str.toUpperCase();
    super.write(upStr, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = 0; i<cbuf.length; i++) {
      if ((int)cbuf[i] < 123 && (int)cbuf[i] > 96) {
          cbuf[i] = (char)((int)cbuf[i]-32);
      }
    }
    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    if (c < 123 && c > 96) {
      super.write(c - 32);
    }
    else{
      super.write(c);
    }
  }

}
