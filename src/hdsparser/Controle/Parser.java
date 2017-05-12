package hdsparser.Controle;

import Utils.SimbolTable.SimbolTable;
import hdsparser.Modelo.Modulo1Entrada;
import hdsparser.Modelo.Modulo2Entradas;
import hdsparser.Modelo.ModuloInterface;
import hdsparser.Modelo.Wire;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    
    private String moduleName;
    private BufferedReader br;
    
    
    public Parser(String fileName){
        int stage = 0;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while (br.ready()){
                String line = br.readLine();
                
                
                if (stage==0){
                    String substrings[] = line.split(" ");
                    for (int i=0; i<substrings.length; i++){
                        if (substrings[i].equals("[name]")){
                            moduleName = substrings[i+1];
                            break;
                        }
                    }
                }
                
                if (line.equals("[components]")){
                    stage++;
                }
                
                if (stage == 1){
                    if(line.equals("[end components]")){
                        stage++;
                    }
                    else{
                        if(!line.equals("[components]")){
                            String subStrings[] = line.split(" ");
                            String moduleNameSubstring[] = subStrings[0].split("\\.");
                            String nomeOp = moduleNameSubstring[moduleNameSubstring.length-1];
                            String identificador="";
                            String wireWidth ="";
                            String constant="";
                            switch(SimbolTable.getInstance().existInList(nomeOp)){
                                case 1:
                                    identificador = subStrings[1];
                                    wireWidth = subStrings[6];
                                    constant = subStrings[7];
                                    SimbolTable.getInstance().inserirImediato(new Modulo1Entrada(nomeOp,identificador,wireWidth,constant));
                                    // Crio Um novo objeto chamado Módulo Imediato
                                    break;
                                case 2:
                                    identificador = subStrings[1];
                                    wireWidth = subStrings[6];
                                    SimbolTable.getInstance().inserir2Entradas(new Modulo2Entradas(nomeOp,identificador,wireWidth));
                                    // Crio um novo objeto chamado do tipo Módulo duas entradas
                                    break;
                                case 3:
                                    identificador = subStrings[1];
                                    wireWidth = subStrings[6];
                                    SimbolTable.getInstance().inserirInterface(new ModuloInterface(nomeOp,identificador,wireWidth));
                                    // Crio um novo objeto do tipo Interface
                                    break;
                                default:
                                    break;
                            }
                            
                            
                            
                            
                        }
                    }
                }
                
                if (stage==2){
                    if (line.equals("[end signals]")){
                        stage++;
                    }
                    else{
                        if ((!line.equals("[signals]")) && (!line.equals("[end components]"))){
                            // Inicia o processo de wire Parsing
                            String substring[] = line.split(" ");
                            String subWireString[] = substring[0].split("\\.");
                            String wireType = subWireString[subWireString.length-1];
                            switch(wireType){
                                case "SignalStdLogic1164":
                                  //  System.out.println("Unico");
                                    SimbolTable.getInstance().inserirfio(new Wire(substring[1],"1"));
                                    for (int i=3; i<substring.length-1; i+=2){
                                        
                                        for (int j=0; j<SimbolTable.getInstance().getModulosImediatos().size(); j++){
                                            Modulo1Entrada aux=null;
                                            if (SimbolTable.getInstance().getModulosImediatos().get(j).getIdentificador().equals(substring[i])){
                                                aux = SimbolTable.getInstance().getModulosImediatos().get(j);
                                                simbolMatchModulo1Entrada(aux,substring[i+1],substring[1]);
                                            } 
                                        }

                                        for (int j=0; j<SimbolTable.getInstance().getModulosDuasEntradas().size(); j++){
                                            Modulo2Entradas aux=null;
                                            if (SimbolTable.getInstance().getModulosDuasEntradas().get(j).getIdentificador().equals(substring[i])){
                                                aux = SimbolTable.getInstance().getModulosDuasEntradas().get(j);
                                                simbolMatchModulo2Entradas(aux,substring[i+1],substring[1]);
                                            } 
                                        }


                                        
                                     //   System.out.print(substring[i]+" ");
                                     //   System.out.println(substring[i+1]);
                                    }
                                    break;
                                case "SignalStdLogicVector":
                                   // System.out.println("Vetor");
                                    SimbolTable.getInstance().inserirfio(new Wire(substring[1],substring[2]));
                                    for (int i=4; i<substring.length-1; i+=2){
                                        for (int j=0; j<SimbolTable.getInstance().getModulosImediatos().size(); j++){
                                            Modulo1Entrada aux=null;
                                            if (SimbolTable.getInstance().getModulosImediatos().get(j).getIdentificador().equals(substring[i])){
                                                aux = SimbolTable.getInstance().getModulosImediatos().get(j);
                                                simbolMatchModulo1Entrada(aux,substring[i+1],substring[1]);
                                            } 
                                        }

                                        for (int j=0; j<SimbolTable.getInstance().getModulosDuasEntradas().size(); j++){
                                            Modulo2Entradas aux=null;
                                            if (SimbolTable.getInstance().getModulosDuasEntradas().get(j).getIdentificador().equals(substring[i])){
                                                aux = SimbolTable.getInstance().getModulosDuasEntradas().get(j);
                                                simbolMatchModulo2Entradas(aux,substring[i+1],substring[1]);
                                            } 
                                        }       
                                    }
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    break;
                            }

                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    
    
    private void simbolMatchModulo1Entrada(Modulo1Entrada item,String simbol,String wireName){
       // System.out.println(item.getIdentificador());
       // System.out.println(simbol);
       // System.out.println(wireName);
        
        switch(simbol){
            case "R_OUT":
                item.setOutR(wireName);
                break;
            case "R_IN":
                item.setEnablein1(wireName);
                break;
            case "RST":
                item.setReset(wireName);
                break;
            case "CLK":
                item.setClkWire(wireName);
                break;
                
            case "EN":
                item.setEnable(wireName);
                break;
            case "D_IN":
                item.setEntrada1(wireName);
                break;
            case "D_OUT":
                item.setOutput(simbol);
                break;
        }
    }
    
    
    
        private void simbolMatchModulo2Entradas(Modulo2Entradas item,String simbol,String wireName){
       // System.out.println(item.getIdentificador());
       // System.out.println(simbol);
       // System.out.println(wireName);
        
        switch(simbol){
            case "R_OUT":
                item.setOutR(wireName);
                break;
            case "R_IN1":
                item.setEnablein1(wireName);
                break;
            case "RST":
                item.setReset(wireName);
                break;
            case "CLK":
                item.setClkWire(wireName);
                break;
            case "R_IN2":
                item.setEnablein2(wireName);
                break;
            case "EN":
                item.setEnable(wireName);
                break;
            case "D_IN1":
                item.setEntrada1(wireName);
                break;
            case "D_IN2":
                item.setEntrada2(wireName);
                break;
            case "D_OUT":
                item.setOutput(wireName);
                break;
        }
    }
    
    
}
