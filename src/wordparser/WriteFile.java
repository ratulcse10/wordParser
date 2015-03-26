
package wordparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteFile {
    public  void write_data(String result){
        
        try{
            File file = new File("wordset/"+WordParser.tableName+"/words_zipf.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(result);
            bw.newLine();
            bw.close();
            
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void write_data_graph(String result,String dataset){
        
        try{
            File file = new File("wordset/"+dataset+"/words_graph.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(result);
            bw.newLine();
            bw.close();
            
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
