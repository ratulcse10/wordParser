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

/**
 *
 * @author Ratul
 */
public class WordParser {
    
        //Just Rename this Variable
        public static String tableName="environment";
        public static int count=0;
        public static ArrayList<String> allWordList;
        public static ArrayList<Integer> allWordListCount;
        public static ArrayList<String> combinedWordListCount;
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
                SqlRead = "SELECT id, link, status,file_status FROM "+tableName+"  where status = 1 and file_status=0 limit 50";
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
                
                System.out.println("Merging....");
                for(int j=0;j<allWordList.size();j++){
                    System.out.println(allWordList.get(j)+" "+allWordListCount.get(j));
                    combinedWordListCount.add(allWordList.get(j)+" "+allWordListCount.get(j));
                }
                
                Collections.sort(combinedWordListCount);
                
                for(int w=0;w<combinedWordListCount.size();w++){
                    System.out.println("Word Write Number: "+w);
                    wFile.write_data(combinedWordListCount.get(w));
                }
                
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
