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

    private int count = 1;
    private final String empty = "";

    /* Constante variable for the differente kind of separators */
    private final String tab = "\t";
    private final String lineFeed = "\n";
    private final String carriageReturn = "\r";
    private final String lineFeedAndCarriageReturn = "\r\n";

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /**
     * Method to write a string with line numbers The lines numbers are add
     * after the separators
     *
     * @param str the string to add the line numbers
     */
    @Override
    public void write(String str) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();

            /* Check if the variable out of the super class is empty or not */
            if (out.toString().equals(empty)) {
                count = 1;

                /* Add number of the line and a tab */
                sb.append(count);
                sb.append(tab);
            } else if (out instanceof UpperCaseFilterWriter) {

                /* Add number of the line and a tab */
                sb.append(count);
                sb.append(tab);
            }

            String temp[];
            int index = 0;
            String s;

            /* Loop until temp[0] is empty. It means that temp[1] is the last
               line of the string. */
            do {
                s = new String(str.substring(index));

                /* Use the method getNestLine to hae the next line */
                temp = Utils.getNextLine(s);
                if (temp[0].equals("")) {
                    sb.append(temp[1]);
                } else {
                    sb.append(temp[0]);
                    ++count;
                }

                /* Check if the last character of the string is a separator or not */
                if (temp[0].endsWith(lineFeed) || temp[0].endsWith(carriageReturn) || temp[0].endsWith(lineFeedAndCarriageReturn)) {

                    /* Add number of the line and a tab */
                    sb.append(count);
                    sb.append(tab);
                }

                /* Add the length of the string to the offset. Like that if there
                   is another line the offset is correct. */
                index += temp[0].length();

            } while (!temp[0].equals("")); // do ... while

            str = new String(sb);

            /* Call the method write of the super class */
            super.write(str, 0, str.length());

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    /**
     * Method to write a string with line numbers The lines numbers are add
     * after the separators
     *
     * @param str the string to add the line numbers
     * @param off is the offset of the beginning of the string to write
     * @param len is the length of the string
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();

            /* Check if the variable out of the super class is empty or not */
            if (out.toString().equals(empty)) {
                count = 1;
            }

            /* Add number of the line and a tab */
            sb.append(count);
            sb.append(tab);

            sb.append(str.substring(off, off + len));

            str = new String(sb);

            /* Call the method write of the super class */
            super.write(str, 0, str.length());

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    /**
     * Method to write a string with line numbers The lines numbers are add
     * after the separators
     *
     * @param cbuf is a tab of chars. It is the string to add the line numbers
     * and to write in the file
     * @param off is the offset of the beginning of the string to write
     * @param len is the length of the string
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();

            /* Prepare the string */
            for (int i = 0; i < cbuf.length; ++i) {
                sb.append(cbuf[i]);
            }

            String s = new String(sb);

            /* Call the method write (String, int, int) */
            write(s, off, len);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Method to write a string with line numbers The lines numbers are add
     * after the separators
     *
     * @param c the int to convert in string and to add in the file with the
     * line numbers.
     */
    @Override
    public void write(int c) throws IOException {

        /* Convert the int in a String */
        String temp = empty + (char) c;
        StringBuilder sb = new StringBuilder();

        /* Check if the variable out of the super class is empty or not */
        if (out.toString().equals(empty)) {
            count = 1;

            /* Add number of the line and a tab */
            sb.append(count);
            sb.append(tab);
        }

        sb.append(temp);

        if (temp.equals(lineFeed)) {
            ++count;

            /* Add number of the line and a tab */
            sb.append(count);
            sb.append(tab);
        }

        /* Create the string with the lines and the separators */
        String s = new String(sb);

        /* Call the method write of the super class */
        super.write(s, 0, s.length());
    }
}
