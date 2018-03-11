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

  private int count=1;
  private boolean nouveau = true;

  @Override
  public void write(String str, int off, int len) throws IOException {
      int addedChar = 0;
      String[] next = Utils.getNextLine(str.substring(off, off+len));
      StringBuilder number = new StringBuilder();
      if(nouveau){
          number.append(count+"");
          number.append("\t");
          addedChar+=2;
          nouveau = false;
      }
      while(!next[0].equals("")){
          count++;
          number.append(next[0]);
          number.append(count+"");
          number.append("\t");
          addedChar+=2;
          if (count>9){
              addedChar++;
          }
          if(count>99){
              addedChar++;
          }
          next = Utils.getNextLine(next[1]);
      }
      number.append(next[1]);
      super.write(number.toString(), 0, len+addedChar);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      write(cbuf.toString(), off, len);
  }



  private int previous;
  @Override
  public void write(int c) throws IOException {
      if (nouveau){
          super.write("");
          count++;
      }
      if (c == '\n') {
          if (previous == '\r') {
              super.write(previous);
          }
          super.write(c);
          super.write(""+count);
          super.write('\t');
          count++;
      }
      else if (previous == '\r') {
          super.write(previous);
          super.write(""+count);
          super.write('\t');
          count++;
          super.write(c);
      }
      else if(c=='\r'){

      }
      else {
          super.write(c);
      }
      previous=c;
  }


}
