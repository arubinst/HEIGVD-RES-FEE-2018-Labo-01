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

  private int line_number;
  private int int_cnt_lf;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    line_number = 1;
    int_cnt_lf = 0;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    /*    String line1_1 = "this is";
          String line1_2 = "a first line\n";
          String line2 = "this is a second line";*/

    String getNextLine[] = new String[2];
    String tmp_string;
    boolean itt = true;

    if (off != 0|| len != 0 ) {
      tmp_string = str.substring(off, off + len);
    }else {
      tmp_string = str;
    }

    getNextLine = Utils.getNextLine(tmp_string);
    if (getNextLine[0] == ""){
      if (line_number == 1) {
        out.write(line_number + "\t" + getNextLine[1]);
      }else{
        out.write(getNextLine[1]);
      }
      line_number++;
      itt = false;
      tmp_string = "";
    }else{
      if (line_number == 1) {
        out.write(line_number + "\t" + getNextLine[0]);
        line_number++;
      }else{
        out.write(getNextLine[0]);
      }
      tmp_string = getNextLine[1];
    }
    while (itt) {
      getNextLine = Utils.getNextLine(tmp_string);
      out.write(line_number + "\t" + getNextLine[0]);
      line_number++;
      if (getNextLine[0] == "") {
        itt = false;
      }
      tmp_string = getNextLine[1];
    }
    out.write(tmp_string);

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(cbuf.toString(),off,len);
  }

  @Override
  public void write(int c) throws IOException {
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
    // (\r, \n, \r\n)
    //"This is line 1\r\nThis is line 2\nThis is line 3"

    if(line_number == 1){
      out.write(line_number + "\t");
      line_number++;
    }
    int tmp = '\r';

    switch (int_cnt_lf){
      case 0:
        if((char)c == '\r' ){
          int_cnt_lf = 1;
        }else if((char)c == '\n'){
          int_cnt_lf = 2;
        }
        break;
      case 1:
        if((char)c == '\n') {
          int_cnt_lf = 2;
        }else{
          int_cnt_lf = 0;
          out.write(line_number + "\t");
          line_number++;
        }
        break;
      case 2:
        out.write(line_number + "\t");
        line_number++;
        if((char)c == '\r' ){
          int_cnt_lf = 1;
        }else if((char)c == '\n'){
          int_cnt_lf = 2;
        }else {
          int_cnt_lf = 0;
        }
        break;
      default:
        int_cnt_lf = 0;
        break;
    }

    out.write(c);
  }

}
