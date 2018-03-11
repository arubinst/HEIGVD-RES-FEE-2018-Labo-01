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
  private int lineNumber = 0;
  private boolean lastCharWasReturn = false;
  private boolean returnWithN = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int index = off; index < str.length() && index < off+len; ++index){
      write(str.charAt(index));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    if(lineNumber == 0){
      ++lineNumber;
      super.write("" + lineNumber + '\t');
    }
    if (c == '\n'){
      ++lineNumber;
      super.write(c);
      if(String.valueOf(lineNumber).length() > 1){
        String tmp = "" + lineNumber;
        for(int index = 0; index < tmp.length(); ++index){
          super.write(tmp.charAt(index));
        }
      }
      else {
        super.write(Character.forDigit(lineNumber, 10));
      }
      super.write('\t');
        lastCharWasReturn = false;
    }
    else if(c == '\r'){
      super.write(c);
      lastCharWasReturn = true;
    }
    else{
      if(lastCharWasReturn){
        ++lineNumber;
        if(String.valueOf(lineNumber).length() > 1){
          String tmp = "" + lineNumber;
          for(int index = 0; index < tmp.length(); ++index){
            super.write(tmp.charAt(index));
          }
        }
        else {
          super.write(Character.forDigit(lineNumber, 10));
        }
       super.write('\t');
      }
      super.write(c);
      lastCharWasReturn = false;
      }
  }

}
