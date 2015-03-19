
package wordparser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import static wordparser.WordParser.count;


public class WordParserCore {
    public void parse(ArrayList<String> wholeFile){
        for(int i =0;i<wholeFile.size();i++){
            StringTokenizer token = new StringTokenizer(wholeFile.get(i)," ");
            while(token.hasMoreTokens()){
                System.out.println(token.nextToken());
                count++;
            }
        }
        
    }
}
