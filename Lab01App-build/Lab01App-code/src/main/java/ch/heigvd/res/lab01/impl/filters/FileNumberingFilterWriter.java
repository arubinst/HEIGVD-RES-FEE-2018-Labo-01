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
 * @author Kevin Pradervand
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int nbrLines;
  private boolean first;
  private int lastChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    nbrLines = 1;
    first = true;
    lastChar = ' ';
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //on transforme la string en tableau de char
    this.write(str.toCharArray(),off,len);

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //on renvois le premier char pour écrire le numéro de ligne juste devant
    for(int i = off; i < (off + len) ; i++){
      this.write(cbuf[i]);
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {

    if(first){
      first = false;
      out.write("" + nbrLines++ + "\t");
    }

    if (c == '\n'){
      out.write("" + (char)c + nbrLines++ + "\t");
    }else if(lastChar == '\r'){
      out.write("" + nbrLines++ + "\t" + (char)c);
    }else{
      out.write(c);
    }

    lastChar = c;
    //hrow new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
