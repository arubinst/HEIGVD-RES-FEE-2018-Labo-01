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

  private int numLine;
  private boolean firstChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    numLine = 1;
    firstChar = true;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    int endOff = off + len;
    /*if(endOff > str.length()){
      endOff = str.length();
    }*/
    String[] lineSplit = Utils.getNextLine(str.substring(off, endOff));
    while(!lineSplit[0].equals("")) {
      out.write(numLine++ + "\t" + lineSplit[0]);
      lineSplit = Utils.getNextLine(lineSplit[1]);
    }
    if(!lineSplit[1].equals("")) {
      out.write(numLine + "\t" + lineSplit[1]);
      if(lineSplit[1].charAt(lineSplit[1].length() - 1) == '\n') {
        out.write("\n" + numLine + "\t");
      }
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int endOff = off + len;
    for(int i = off; i < endOff; i++) {
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    if(firstChar){
      firstChar = false;
      out.write(numLine++ + "\t");
    }
    if(c == '\n'){
      out.write((char)c + numLine++ + "\t");
    }
    else if(c == '\r'){

    }
    else{
      out.write(c);
    }
  }

}
