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
  private int lineCount = 1;

  public FileNumberingFilterWriter(Writer out) {
      super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      String line = new String(str.substring(off, len+off));
      String arrLines[] = Utils.getNextLine(line);
      // super.write((lineCount++) + "\t" + str, off, len+off);

      /*int i = 0;
      for (String s: arrLines) {
          if (s.indexOf("\n") != -1 ||s.indexOf("\r\n") != -1 ||s.indexOf("\r") != -1) {
              out.write(lineCount + "\t" + s);
              lineCount++;
          }
      }*/
      out.write(lineCount + "\t" + line);
      lineCount++;

      /*for (int i = 0; i < arrLines.length; ++i) {
          if (arrLines[i].indexOf("\n") != -1 || arrLines[i].indexOf("\r\n") != -1 || arrLines[i].indexOf("\r") != -1) {
              arrLines[i] = lineCount + "\t" + arrLines[i];
              out.write(arrLines[i]);
              lineCount++;
          }
      }*/
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      String str = new String(cbuf);
      write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
      //
  }

}
