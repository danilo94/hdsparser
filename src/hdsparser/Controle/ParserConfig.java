/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser.Controle;

import Utils.SimbolTable.SimbolTable;
import hdsparser.Modelo.ModuloaInstanciar;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danilo
 */
public class ParserConfig {
    
    public ParserConfig(String filename){
        
        try {
            generateTables(filename);
        } catch (IOException ex) {
            Logger.getLogger(ParserConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    private void generateTables(String fileName) throws FileNotFoundException, IOException{
        int stage = 0;
        BufferedReader br;   
        br = new BufferedReader(new FileReader(fileName));
        while (br.ready()){
            String line = br.readLine();
            String substrings[] = line.split(" ");
            if (substrings.length==1 && !substrings[0].equals("")){
                if (substrings[0].equals("wires")){
                 stage++;   
                }
                if (substrings[0].equals("endfile")){
                    br.close();
                    break;
                }else{
                    if (stage == 1 && !substrings[0].equals("wires")){
                        SimbolTable.getInstance().insertWire(substrings[0]);   
                    }
                }
                
            }
            else {
               if (substrings.length==3 && stage == 0){
                SimbolTable.getInstance().insertItem(substrings[0],substrings[2]);
                if (!substrings[1].equals("@"))
                SimbolTable.getInstance().getTabelaDeModulos().put(substrings[0],substrings[1]);
             //  SimbolTable.getInstance().getTabelaDeModulos.add();
               }
            else{
               if (substrings.length==2 && stage == 0){
                   if (substrings[0].equals("SourceFiles")){
                       SimbolTable.getInstance().sourceLocation = substrings[1];
                   }
               }
                
            }               
               
            }


            
            
            
            
        }
        br.close();
        
        
    }
    
    
}
