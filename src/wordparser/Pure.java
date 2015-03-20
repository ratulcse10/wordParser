/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author yousufkhan
 */
public class Pure {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        Pure it = new Pure();
//        it.read();
//    }
    
    public static String done(String input){
           String temp = input.replaceAll("[,: ]", "");
           return temp;
       }
 }
    
