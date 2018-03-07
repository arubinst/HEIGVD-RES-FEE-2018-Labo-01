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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static String tabulation = "\t";
  private static int returnLine = '\r';
  private static int newLine = '\n';

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber;
  private boolean firstLine;
  private int lastChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    lineNumber = 1;
    firstLine = true;
    lastChar = ' ';
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    int end = off + len;
    for(int i = off; i < end; i++) {
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(firstLine) {
      firstLine = false;
      out.write(lineNumber + tabulation);
    }

    boolean returnLineNewLine = (lastChar == returnLine && c == newLine);

    if(!returnLineNewLine) {
      out.write(c);
    }

    if(c == newLine || c == returnLine) {
      if(!returnLineNewLine) {
        lineNumber++;
        out.write(lineNumber + tabulation);
      }
    }

    lastChar = c;
  }
}
