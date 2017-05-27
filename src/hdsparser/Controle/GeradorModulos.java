/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser.Controle;

import Utils.SimbolTable.SimbolTable;
import hdsparser.Modelo.Modulo1Entrada;
import hdsparser.Modelo.Modulo2Entradas;
import hdsparser.Modelo.ModuloInterface;
import hdsparser.Modelo.Wire;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;




public class GeradorModulos {
    
   
        // Esta função irá basicamente agregar todos os módulos instânciados
    public static void CreateProject () throws IOException{
        
        String nomePasta = SimbolTable.getInstance().getModuleName() + "/";
        String caminhoDestino = CreateFolder(nomePasta);
        SimbolTable.getInstance().folderLocation= caminhoDestino;
        System.out.println(caminhoDestino);
        
        ArrayList<String> listadeModulos = SimbolTable.getInstance().getModulosAInstanciar();
        Map<String,String> tabelaDeModulos = SimbolTable.getInstance().getTabelaDeModulos();
        
        for (int i=0; i<listadeModulos.size(); i++){
            if (tabelaDeModulos.containsKey(listadeModulos.get(i))){
               // System.out.println();
               File origem = new File(tabelaDeModulos.get(listadeModulos.get(i)));
               File destino = new File(caminhoDestino+"/"+origem.getName());
               copy(origem,destino);
            }
        }
        CreateTopLevel();
   
    }
    
  
    
    
    private static void CreateTopLevel(){
        String topLevelFolder = SimbolTable.getInstance().folderLocation+"/";
        String topLevelName = SimbolTable.getInstance().getModuleName();
        ArrayList<Wire> listaFios = SimbolTable.getInstance().getFios();
        ArrayList<Modulo1Entrada> modulosImediatos = SimbolTable.getInstance().getModulosImediatos();
        ArrayList<Modulo2Entradas> modulos2Entradas = SimbolTable.getInstance().getModulosDuasEntradas();
        ArrayList<ModuloInterface> modulosInterface = SimbolTable.getInstance().getModulosInterface();
        try {
            FileWriter writer = new FileWriter(topLevelFolder+topLevelName+".v");
            BufferedWriter buffWriter = new BufferedWriter(writer);
            //);\n
            buffWriter.write("module "+topLevelName+"(");  
            // Esta função será responsável por gerar todas as ligações entre as estruturas que irão compor o top level
            // Criação da interface com o exterior
            for (int i=0; i<modulosInterface.size(); i++){
                for (int j=0; j<modulosInterface.get(i).getNames().size(); j++){
                    if (modulosInterface.get(i).getTipoInterface().equals("IN_")){
                        buffWriter.write(modulosInterface.get(i).getNames().get(j)+",");
                        for (int k=0; k<modulosInterface.get(i).getNamesRinRout().size(); k++){
                           buffWriter.write(modulosInterface.get(i).getNamesRinRout().get(k)+",");
                        }    
                    }else{
                      if (modulosInterface.get(i).getTipoInterface().equals("OUT")){
                        buffWriter.write(modulosInterface.get(i).getNames().get(j)+",");
                        buffWriter.write("r_out,");
                    }  
                    }
                }
            }
            
            buffWriter.write(" clk, rst, enable);\n");
            
            buffWriter.write("input clk,rst,enable;\n");
            // Criação da interface com o exterior
            for (int i=0; i<modulosInterface.size(); i++){
                for (int j=0; j<modulosInterface.get(i).getNames().size(); j++){
                    if (modulosInterface.get(i).getTipoInterface().equals("IN_")){
                        buffWriter.write("input ["+modulosInterface.get(i).getWireWidth()+"-1:0]"  +modulosInterface.get(i).getNames().get(j)+";\n");
                        for (int k=0; k<modulosInterface.get(i).getNamesRinRout().size(); k++){
                           buffWriter.write("input "+modulosInterface.get(i).getNamesRinRout().get(k)+";\n");
                        }                        
                    }else{
                      if (modulosInterface.get(i).getTipoInterface().equals("OUT")){
                        buffWriter.write("output ["+modulosInterface.get(i).getWireWidth()+"-1:0]"  +modulosInterface.get(i).getNames().get(j)+";\n");
                        buffWriter.write("output r_out;\n");
                      }  
                    }
                }
            }            
            
            buffWriter.write("//Fim da criação da interface com o mundo exterior\n");

            for (int i=0; i<listaFios.size(); i++){
                buffWriter.write("wire ["+listaFios.get(i).getWire_Width()+"-1:0]"+listaFios.get(i).getId()+";\n");
            }
            
            for (int i=0; i<listaFios.size(); i++){
                if (listaFios.get(i).getId().contains("clock")){
                    buffWriter.write("assign "+listaFios.get(i).getId()+"= clk;\n");
                }else{
                    if (listaFios.get(i).getId().contains("reset")){
                        buffWriter.write("assign "+listaFios.get(i).getId()+"= rst;\n");
                    }else{
                        
                    if (listaFios.get(i).getId().contains("en_")){
                        buffWriter.write("assign "+listaFios.get(i).getId()+"= enable;\n");
                    }                       
                        
                    }
                }
            }
             
            for (int i=0; i<modulosInterface.size(); i++){
                for (int j=0; j<modulosInterface.get(i).getNames().size(); j++){
                    if (modulosInterface.get(i).getTipoInterface().equals("IN_")){
                        buffWriter.write("assign "+modulosInterface.get(i).getOutputs().get(j)+"="+modulosInterface.get(i).getNames().get(j)+";\n");
                        for (int k=0; k<modulosInterface.get(i).getNamesRinRout().size(); k++){
                           buffWriter.write("assign "+modulosInterface.get(i).getrIn().get(k)+"="+modulosInterface.get(i).getNamesRinRout().get(k)+";\n");
                        }  
                        
                        
                    }else{
                      if (modulosInterface.get(i).getTipoInterface().equals("OUT")){
                        buffWriter.write("assign "+modulosInterface.get(i).getNames().get(j)+"="+modulosInterface.get(i).getOutputs().get(j)+";\n");
                        buffWriter.write("assign r_out = "+modulosInterface.get(i).getrOut()+";\n" );
                      }  
                    }
                }
            }              
            
            
            
            
            
            
            
            
            
            
            
            
            
            for (int i=0; i<modulosImediatos.size(); i++){
                buffWriter.write(modulosImediatos.get(i).getNomeModulo()+" #(.N("+modulosImediatos.get(i).getWireWidth()+"),.I("+modulosImediatos.get(i).getConstant()+")) "+modulosImediatos.get(i).getNomeModulo()+i+
                "(.CLK("+modulosImediatos.get(i).getClkWire()+"),.RST("+modulosImediatos.get(i).getReset()+"),.EN("+modulosImediatos.get(i).getEnable()+")"
                        + ",.R_IN("+modulosImediatos.get(i).getEnablein1()+"),.D_IN("+modulosImediatos.get(i).getEntrada1()+")"
                        + ",.R_OUT("+modulosImediatos.get(i).getOutR()+"),.D_OUT("+modulosImediatos.get(i).getOutput()+"));\n");
            }
            for (int i=0; i<modulos2Entradas.size(); i++){
                buffWriter.write(modulos2Entradas.get(i).getNomeModulo()+" #(.N("+modulos2Entradas.get(i).getWireWidth()+")) "+modulos2Entradas.get(i).getNomeModulo()+i+
                "(.CLK("+modulos2Entradas.get(i).getClkWire()+"),.RST("+modulos2Entradas.get(i).getReset()+"),.EN("+modulos2Entradas.get(i).getEnable()+")"
                        + ",.R_IN1("+modulos2Entradas.get(i).getEnablein1()+"),.D_IN1("+modulos2Entradas.get(i).getEntrada1()+")"
                        + ",.R_OUT("+modulos2Entradas.get(i).getOutR()+"),.D_OUT("+modulos2Entradas.get(i).getOutput()+"),.D_IN2("+modulos2Entradas.get(i).getEntrada2()+"),.R_IN2("+modulos2Entradas.get(i).getEnablein2()+"));\n");            
            }            
           
            
            
            
            
            buffWriter.write("endmodule");
            buffWriter.close();

        } catch (IOException ex) {
            System.out.println("Diretório Não encontrado !!");
        }
    }
    
    
    
    
    private static String CreateFolder(String nameFolder){
        File directory = new File(nameFolder);
        if (!directory.exists()){
            directory.mkdir();
        }
        return directory.getAbsolutePath();
    }

    private static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
            // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
}
    
    
    
    
    

    
}
