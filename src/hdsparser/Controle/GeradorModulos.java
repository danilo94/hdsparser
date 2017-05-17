/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser.Controle;

import Utils.SimbolTable.SimbolTable;
import hdsparser.Modelo.Modulo1Entrada;
import hdsparser.Modelo.Modulo2Entradas;
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
import java.util.logging.Level;
import java.util.logging.Logger;



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
        try {
            FileWriter writer = new FileWriter(topLevelFolder+topLevelName+".v");
            BufferedWriter buffWriter = new BufferedWriter(writer);
            
            buffWriter.write("module "+topLevelName+"();\n");
            // Esta função será responsável por gerar todas as ligações entre as estruturas que irão compor o top level
            
            for (int i=0; i<listaFios.size(); i++){
                buffWriter.write("wire ["+listaFios.get(i).getWire_Width()+"-1:0]"+listaFios.get(i).getId()+";\n");
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
