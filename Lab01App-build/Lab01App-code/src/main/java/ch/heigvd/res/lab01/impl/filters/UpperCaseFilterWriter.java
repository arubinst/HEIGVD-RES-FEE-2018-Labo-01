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

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param str String devant subir la transformation
   * @param off Offset dans le cas ou l'on souhaiterais un décalage
   * @param len Nombre de caractères à transformer si spécifié
   * @throws IOException
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < str.length() && i < len + off; ++i){
        super.write(Character.toUpperCase(str.charAt(i)));
    }
  }

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param cbuf Tableau de caractères devant subir la transformation
   * @param off Offset dans le cas ou l'on souhaiterais un décalage
   * @param len Nombre de caractères à transformer si spécifié
   * @throws IOException
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i <cbuf.length && i < len + off; ++i){
        super.write(Character.toUpperCase(cbuf[i]));
    }
  }

  /**
   * Permet de prendre chaque caractères et de les envoyer dans la méthode write prennant un seul caractères.
   * @param c Caractère à transformer en majuscule
   * @throws IOException
   */
  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    super.write(Character.toUpperCase(c));
  }
}
