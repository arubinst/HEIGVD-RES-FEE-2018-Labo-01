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
  private final String tab = "\t";
  private final String lineFeed = "\n";
  private final String carriageReturn = "\r";
  private final String lineFeedAndCarriageReturn = "\r\n";
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }
  
  @Override
  public void write(String str) throws IOException{
      try{
           StringBuilder sb = new StringBuilder();
            if(out.toString().equals(empty)){
                count = 1;
                sb.append(count);
                sb.append(tab);
            }else if(out instanceof UpperCaseFilterWriter){
                sb.append(count);
                sb.append(tab);
            }
            
            String temp[];
            int index = 0;
            String s;
            do{   
                s = new String(str.substring(index));
                temp = Utils.getNextLine(s);
                if(temp[0].equals("")){
                    sb.append(temp[1]);
                }else{
                    sb.append(temp[0]);
                    ++count;
                }
                
                
                if(temp[0].endsWith(lineFeed) || temp[0].endsWith(carriageReturn) || temp[0].endsWith(lineFeedAndCarriageReturn)){
                    sb.append(count);
                    sb.append(tab);
                }
                index += temp[0].length();
            }while(!temp[0].equals(""));
            
            str = new String(sb);
          
          super.write(str, 0, str.length());
          
          
      }catch(Exception e){
          e.printStackTrace();
      }
          
  }
  
  /*
  @Override
  public void write(String str) throws IOException{
      try{
          int OS = 0;
          
          if(findLineFeed(str) > -1){
                if(findLineFeedAndCarriageReturn(str) > - 1){
                    OS = 3; // Windows
                }else{
                    OS = 2; // Unix
                }
          }else{
              OS = 1; // MAC
          }
 
          switch(OS){
              case 1: str = prepareString(str, carriageReturn);
                  break;
              case 2: str = prepareString(str, lineFeed);
                  break;
              case 3: str = prepareString(str, lineFeedAndCarriageReturn);
                  break;
              default:
          }
          
          super.write(str, 0, str.length());
      }catch(Exception e){
          e.printStackTrace();
      }
          
  }
*/
  @Override
  public void write(String str, int off, int len) throws IOException {
      try{
          StringBuilder sb = new StringBuilder();
        if(out.toString().equals(empty)){
            count = 1;
        }
        
        sb.append(count);
        sb.append(tab);
        
        sb.append(str.substring(off, off + len));
        
        
        
        str = new String(sb);
        super.write(str, 0, str.length());
      }catch(Exception e){
          e.printStackTrace();
      }
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < cbuf.length; ++i){
          sb.append(cbuf[i]);
      }
      
      String s = new String(sb);
      write(s, off, len);
  }

  @Override
  public void write(int c) throws IOException {
      String temp = empty + (char)c;
      StringBuilder sb = new StringBuilder();
      if(out.toString().equals(empty)){
          count = 1;
          sb.append(count);
          sb.append(tab);
      }
      
      sb.append(temp);
      
      if(temp.equals(lineFeed)){
          ++count;
          sb.append(count);
          sb.append(tab);
      }
      
      String s = new String(sb);
      
      super.write(s, 0, s.length());
  }
  
  private int findLineFeed(String str, int index){
      return str.indexOf(lineFeed, index);
  }
  
  private int findCarriageReturn(String str, int index){
      return str.indexOf(carriageReturn, index);
  }

  private int findLineFeedAndCarriageReturn(String str, int index){
      return str.indexOf(lineFeedAndCarriageReturn, index);
  }
 
 /* 
  private int findLineFeed(String str){
      return str.indexOf(lineFeed);
  }
  
  private int findCarriageReturn(String str){
      return str.indexOf(carriageReturn);
  }

  private int findLineFeedAndCarriageReturn(String str){
      return str.indexOf(lineFeedAndCarriageReturn);
  }

*/
  private String prepareString(String str, String separator){
      StringBuilder sb = new StringBuilder();
        if(out.toString().equals(empty)){
            count = 1;
            sb.append(count);
            sb.append(tab);
        }
        
        for(int i = 0; i < str.length(); ++i){
            if(i > 0 && separator.equals(lineFeedAndCarriageReturn)){
                ++i;
            }
            int index = str.indexOf(separator, i);
            if(index > 0){
                sb.append(str.substring(i, index));
                sb.append(separator);
                i += str.indexOf(separator);
                ++count;
                sb.append(count);
                sb.append(tab);
            }else{
                sb.append(str.substring(i));
                i = str.length();
            }  
        }
        
        return new String(sb);
  }
}
