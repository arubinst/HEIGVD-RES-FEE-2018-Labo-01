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
  private int lineCount = 0;
  private static final char TAB = '\t';
  private static final char BACKSLASH_N = '\n';
  private static final char BACKSLASH_R = '\r';
  private boolean lastBackSlashR = false;

  public FileNumberingFilterWriter(Writer out) {
      super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      // Vars
      String output = "";
      String[] lines = new String[2];
      lines[1] = str.substring(off, off + len); // Handle offset / length

      // First output ?
      if (lineCount == 0) {
          output += String.valueOf(++lineCount) + TAB;
      }

      // Chunk line feeds
      while (!(lines = Utils.getNextLine(lines[1]))[0].isEmpty()) {
          output += lines[0] + String.valueOf(++lineCount) + TAB;
      }
      // Append the rest
      output += lines[1];

      // Write output
      out.write(output);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      // Basically make a String from chars array and call the above method
      String str = new String(cbuf);
      write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {

      String output = "";
      if (c != BACKSLASH_R || lastBackSlashR) {
          if (c != BACKSLASH_N && lastBackSlashR) {
              lastBackSlashR = false;
              output = String.valueOf(++lineCount) + TAB + (char) c;
          } else {
              output = String.valueOf((char) c);
              lastBackSlashR = false;
          }
          write(output);
      } else {
          lastBackSlashR = true;
          out.write(c);
      }
  }

}
