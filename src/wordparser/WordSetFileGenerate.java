
package wordparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class WordSetFileGenerate {
    
   public static char[] banglaCahracterList ={ 'অ', 'আ', 'ই', 'ঈ', 'উ','ঊ','ঋ','এ','ঐ','ও','ঔ','ক','খ','গ','ঘ','ঙ','চ','ছ','জ','ঝ','ঞ','ট','ঠ','ড','ঢ','ণ','ত','থ','দ','ধ','ন','প','ফ','ব','ভ','ম','য','র','ল','শ','ষ','স','হ','য়','ড়','ঢ়','O'};

    public static void main(String[] args) {
        
        for(int i=0;i<banglaCahracterList.length;i++){
            try{
                File file = new File("wordset/"+WordParser.tableName+"/"+ banglaCahracterList[i] + ".txt");
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write("");
//                bw.newLine();
//                bw.close();
                
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
}
