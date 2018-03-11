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


  private int line_num;
  private int last_char;
  private boolean return_line;
  private boolean start;
  public static char TAB = '\t';
  public static char RETURN_N = '\n';
  public static char RETURN_R = '\r';


  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    this.line_num = 1;
    this.last_char = 'a';
    this.return_line = false;
    this.start = true;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for (int i = off; i < off + len; i++) {
      this.write(str.charAt(i));
    }
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < off + len; i++) {
      this.write(cbuf[i]);
    }
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {

    if (this.start) {
      // First line - Start with 1 and a TAB
      super.write(Character.forDigit(line_num, 10));
      super.write(TAB);
      this.start = false;
      this.line_num++;
    }

    if (c == RETURN_N) {
        out.write("" + (char)c + line_num + TAB);
        this.line_num++;
    } else if (last_char == RETURN_R) {
        out.write("" + line_num + TAB + (char)c);
        this.line_num++;
    } else {
      super.write(c);
    }

    last_char = c;

    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
