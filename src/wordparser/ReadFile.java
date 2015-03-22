
package wordparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadFile {
        public static void read(String path,int i){
        String line=new String();
        ArrayList<String> wholefile=new ArrayList<String>();
        try {
                BufferedReader br = new BufferedReader(new FileReader(path+i+".txt"));
                wholefile.clear();
                while((line=br.readLine())!=null){
                    wholefile.add(line);
                }
                
            
            }
        catch(IOException Ex){
            System.out.println("Exception");
        }
        
        //WordParserCore parserCore = new WordParserCore();
        WordParserCore.parse(wholefile);
        
        
    }

}
