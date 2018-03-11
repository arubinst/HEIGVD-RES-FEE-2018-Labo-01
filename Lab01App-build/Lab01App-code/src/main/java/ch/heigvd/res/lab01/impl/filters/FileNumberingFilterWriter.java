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

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 1;
  private boolean fstLine = true;
  private int lastCharSeen = ' ';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++){
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    String tabChar = "\t";
    int returnChar = '\r';
    int newLineChar = '\n';

    if(fstLine) {
      fstLine = false;
      out.write(lineNumber + tabChar);
    }

    if(c == newLineChar) {
      lineNumber++;
      out.write("" + (char)c + lineNumber + tabChar);
    }else if(lastCharSeen == returnChar){
      lineNumber++;
      out.write(lineNumber + tabChar + (char)c);
    }else {
      out.write(c);
    }

    lastCharSeen = c;
  }


}
