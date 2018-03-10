package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

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

  // Add instance variables
  int nbRows = 1;
  boolean firstRow = true;
  int lastCharacter = ' ';


  @Override
  public void write(String str, int off, int len) throws IOException {
    // Call write method with character array
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++) {
      // For each character, call character write method
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // Handle case of 1st row
    if(firstRow) {
      firstRow = false;
      out.write(nbRows + "\t");
    }

    // Handle \n
    if(c == '\n') {
      nbRows++;
      out.write("" + (char)c + nbRows + "\t");
    }
    // Special case for Windows EOL
    else if(lastCharacter == '\r') {
      // Pre-increment nbRows before writing
      out.write(++nbRows + "\t" + (char)c);
    }
    // Write single character (so, we are not at the end of a line)
    else {
      out.write(c);
    }

    lastCharacter = c;
  }
}
