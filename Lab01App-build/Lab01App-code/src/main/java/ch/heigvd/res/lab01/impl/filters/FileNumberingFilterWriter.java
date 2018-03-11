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

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param str String à numéroter et à indenter
   * @param off Offset de décalage sur la String
   * @param len Nombre de caractères à prendre en compte si spécifié
   * @throws IOException
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int index = off; index < str.length() && index < off+len; ++index){
      write(str.charAt(index));
    }
  }

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param cbuf Tableau de caractères à numéroter et à indenter
   * @param off Offset de décalage sur la String
   * @param len Nombre de caractères à prendre en compte si spécifié
   * @throws IOException
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int index = off; index < cbuf.length && index < off+len; ++index){
      write(cbuf[index]);
    }
  }

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param c caractère à analyser
   * @throws IOException
   */
  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    /*
    Permet de tester que l'on est au début de la ligne
     */
    if(lineNumber == 0){
      ++lineNumber;
      super.write("" + lineNumber + '\t');
    }
    /*
    Permet de tester que l'on a un "ligne Feed"
     */
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
    /*
      Permet de tester que l'on a un "carriage return"
       */
    else if(c == '\r'){
      super.write(c);
      lastCharWasReturn = true;
    }
    /*
    Permet de prendre en compte le cas d'un caractère
     */
    else{
      if(lastCharWasReturn){
        ++lineNumber;
        /*
        Permet de décomposer le numéro de ligne dans le cas ou l'on a plus d'un digit (pour éviter l'erreur
        à l'appel super.write avec un chiffre dépassant 9)
         */
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
