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
  public void write(String str) throws IOException {
      super.write(str.toUpperCase(), 0, str.length());     
  }


  @Override
  public void write(String str, int off, int len) throws IOException {
      try{
          write(str.substring(off, off + len));
      }catch(Exception e){
          e.printStackTrace();
      }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      try{
          StringBuilder sb = new StringBuilder();
          for(int i = off; i < off + len; ++i){
              sb.append(cbuf[i]);
          }
          String s = new String(sb);
          write(s);
      }catch(Exception e){
          e.printStackTrace();
      }
  }

  @Override
  public void write(int c) throws IOException {
      try{
            String s = "" + (char) c;
            write(s);
      }catch(Exception e){
          e.printStackTrace();
      }
  }

}
