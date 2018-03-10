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
    // Write with uppercase character
    out.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String cbufString = new String(cbuf);
    cbufString = cbufString.toUpperCase();
    cbuf = cbufString.toCharArray();
    // Write with uppercase character
    out.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // Write with uppercase character
    out.write(Character.toUpperCase(c));
  }

}
