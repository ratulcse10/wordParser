
package wordparser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import static wordparser.WordParser.allWordList;
import static wordparser.WordParser.allWordListCount;
import static wordparser.WordParser.count;
import java.lang.Object;
import java.util.regex.Pattern;


public class WordParserCore {
    private static final Pattern UNDESIRABLES = Pattern.compile("/[0-9.!?,;:১২৩৪৫৬৭৮৯০ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789]$/");
    
    public static char[] banglaCahracterList ={ 'অ', 'আ', 'ই', 'ঈ', 'উ','ঊ','ঋ','এ','ঐ','ও','ঔ','ক','খ','গ','ঘ','ঙ','চ','ছ','জ','ঝ','ঞ','ট','ঠ','ড','ঢ','ণ','ত','থ','দ','ধ','ন','প','ফ','ব','ভ','ম','য','র','ল','শ','ষ','স','হ','য়','ড়','ঢ়'};
    
    public static void parse(ArrayList<String> wholeFile){
        
        for(int i =0;i<wholeFile.size();i++){
//            String replaceText=wholeFile.get(i).replace(":", "");
//            String replaceText1=replaceText.replace(",", "");
             String replace=wholeFile.get(i).replaceAll("[,:()*!+‘’..\"%'<>=১২৩৪৫৬৭৮৯০ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789]", "");
            //StringTokenizer token = new StringTokenizer(wholeFile.get(i)," ।-?—");
            StringTokenizer token = new StringTokenizer(replace," ।-?—");
            //System.out.println(wholeFile.get(i));
            while(token.hasMoreTokens()){
                String temp = token.nextToken();
                temp = temp.trim();
                
                
                //temp = UNDESIRABLES.matcher(temp).replaceAll("");
                
                
                //temp=temp1.replaceAll("[‘’..\"%'<> =:১২৩৪৫৬৭৮৯০ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789,()*!+ ]", "");
               
                //temp = Pure.done(temp);
                if(allWordList.contains(temp)){
                    int tempCount = allWordList.indexOf(temp);
                    int previousCount = allWordListCount.get(tempCount);
                    previousCount++;
                    allWordListCount.set(tempCount, previousCount);
                    tempCount=0;
                    previousCount=0;
                    count++;
                }
                else{
                    if(temp.length() >= 1)
                    {
                         if(isBanglaCharacter(temp.charAt(0)))
                        {
                            //System.out.println(temp);
                         allWordList.add(temp);
                         allWordListCount.add(1); 
                         count++;
                        }
                    }
                }
        
            }
        }
        
    }
    
    public static boolean isBanglaCharacter(char ch){
        for(int i=0;i<banglaCahracterList.length;i++){
            if(ch == banglaCahracterList[i]){
                return true;
            }
        }
        return false;
    }
}
