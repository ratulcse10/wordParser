
package wordparser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import static wordparser.WordParser.allWordList;
import static wordparser.WordParser.allWordListCount;
import static wordparser.WordParser.count;


public class WordParserCore {
    public void parse(ArrayList<String> wholeFile){
        for(int i =0;i<wholeFile.size();i++){
            StringTokenizer token = new StringTokenizer(wholeFile.get(i)," ।-");
            while(token.hasMoreTokens()){
                String temp = token.nextToken();
                temp=temp.replaceAll("[‘’১২৩৪৫৬৭৮৯০ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,;()]", "");
                if(allWordList.contains(temp)){
                    int tempCount = allWordList.indexOf(temp);
                    int previousCount = allWordListCount.get(tempCount);
                    previousCount++;
                    allWordListCount.set(tempCount, previousCount);
                    tempCount=0;
                    previousCount=0;
                }
                else{
                    allWordList.add(temp);
                    allWordListCount.add(1);
                }
                count++;
            }
        }
        
    }
}
