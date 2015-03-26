/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wordparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ratul
 */
public class WordParser {
    
        //Just Rename this Variable
        public static String tableName="sports";
        public static int count=0;
        public static ArrayList<String> allWordList;
        public static ArrayList<Integer> allWordListCount;
        public static ArrayList<String> combinedWordListCount;
        public static Map<String, Integer> freq;
        public static TreeMap<String,Integer> sorted_map;
        //Table Name

  
    public static void main(String[] args) {
        ArrayList<String> allData = new ArrayList<String>();
        
        allWordList = new ArrayList<String>();
        allWordListCount = new ArrayList<Integer>();
        combinedWordListCount = new ArrayList<String>();
        
        String path ="dataset/"+tableName+"/link";
        String SqlRead,SqlUpdate;
        try{
            Connection connection = Connect.CreateConntection();
            try {
                Statement statement = Connect.CreateStatement(connection);
                //Statement updateStatement = Connect.CreateStatement(connection);
                SqlRead = "SELECT id, link, status,file_status FROM "+tableName+"  where status = 1 and file_status=0 ";
                SqlUpdate = "update "+tableName+" set file_status=1 where id=?";
                
                ResultSet rs = statement.executeQuery(SqlRead);
                PreparedStatement preparedStatement =  connection.prepareStatement(SqlUpdate);                                  
                
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("id");
                    String link = rs.getString("link");
                    int status = rs.getInt("status");
                    
                    preparedStatement.setInt(1, id);
                    int rowsAffected = preparedStatement.executeUpdate();
                    connection.commit();
                    
                    //Read File according to File ID
                    //ReadFile readFile = new ReadFile();
                    ReadFile.read(path, id);
                    

                    System.out.print("ID: " + id);
                    System.out.print(", Status: " + status);
                    System.out.println("\n");
                 }
                
                WriteFile wFile = new WriteFile();
                wFile.write_data("Total Word Count: "+count);
                wFile.write_data("Total Unique Word: "+allWordList.size());
                
                //*** Map and Soting
                freq = new HashMap<String, Integer>();
                ValueComparator bvc =  new ValueComparator(freq);
                 sorted_map = new TreeMap<String,Integer>(bvc);
                
                
                System.out.println("Merging....");
                for(int j=0;j<allWordList.size();j++){
                    System.out.println(allWordList.get(j)+" "+allWordListCount.get(j));
                     freq.put(allWordList.get(j), allWordListCount.get(j));
                }
                
                sorted_map.putAll(freq);
        
                for (Map.Entry<String,Integer> entry : sorted_map.entrySet()) {
                    String key = entry.getKey();
                    int  value = entry.getValue();
                    System.out.println("Key : "+key + " value : "+ value);
                    wFile.write_data(key+" "+value);
                 }
                 //*** Map and Sorting End
               // Collections.sort(combinedWordListCount);
                
//                for(int w=0;w<combinedWordListCount.size();w++){
//                    System.out.println("Word Write Number: "+w);
//                    wFile.write_data(combinedWordListCount.get(w));
//                }
                
                System.out.println("Total Word Count: "+count);
                System.out.println("Total Unique Word: "+allWordList.size());
                
                
                
                rs.close();
                statement.close();
                connection.close();
                
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Couldn't create statement.");
            }
        }catch(Exception e){
            System.out.println("Couldn't create Connection.");
        }
    }
    
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
