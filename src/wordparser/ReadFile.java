
package wordparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


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
        public static void graph_read(String dataset){
        String line=new String();
        try {
                BufferedReader br = new BufferedReader(new FileReader("wordset/"+dataset+"/words_zipf.txt"));
                int bount=0;
                int dount=0;
                WriteFile wFileGraph = new WriteFile();
                while((line=br.readLine())!=null){
                    bount++;
                    if(bount>2){
                        dount++;
                        StringTokenizer countToken = new StringTokenizer(line," ");
                        if(countToken.hasMoreTokens()){
                            countToken.nextToken();
                            int number =  Integer.parseInt(countToken.nextToken());
                            System.out.println(dount+","+number);
                            wFileGraph.write_data_graph(dount+","+number,dataset);
                        }
                    }
                    if(dount==1000){
                        break;
                    }
                    
                }
                
            
            }
        catch(IOException Ex){
            System.out.println("Exception");
        }
        
    }

}
