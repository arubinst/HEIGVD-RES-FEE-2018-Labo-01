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
     * Method to change the string in uppercase.
     *
     * @param str the string to change
     */
    @Override
    public void write(String str) throws IOException {
        try{
            super.write(str.toUpperCase(), 0, str.length());
        }catch(IOException io){
            io.printStackTrace();
        }
        
    }

    /**
     * Method to change the string in uppercase.
     *
     * @param str the string to change
     * @param off the offset were the string
     * @param len the length of the string
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        try {
            write(str.substring(off, off + len));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Method to change the string in uppercase.
     *
     * @param str the string is in a tab of char
     * @param off the offset were the string
     * @param len the length of the string
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = off; i < off + len; ++i) {
                sb.append(cbuf[i]);
            }
            String s = new String(sb);
            write(s);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Method to change the string in uppercase.
     *
     * @param c is the int to change in a string
     * and then to change to uppercase
     */
    @Override
    public void write(int c) throws IOException {
        try {
            String s = "" + (char) c;
            write(s);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
