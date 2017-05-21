
package Utils.SimbolTable;

import hdsparser.Modelo.Modulo1Entrada;
import hdsparser.Modelo.Modulo2Entradas;
import hdsparser.Modelo.ModuloInterface;
import hdsparser.Modelo.Wire;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// Classe Singleton que agrupa todas as estruturas de dados utilizadas para elaborar o top level

public class SimbolTable {
    private  String moduleName;
    public  String folderLocation;
    public  String sourceLocation;
    private static SimbolTable uniqueInstance;
    private static ArrayList<String> instrucoes_imediatas;
    private static ArrayList<String> instrucoes_2entradas;
    private static ArrayList<String> interfaces;
    private static ArrayList<String> tiposFio;
    private static ArrayList<Modulo1Entrada> modulosImediatos;
    private static ArrayList<Modulo2Entradas> modulosDuasEntradas;
    private static ArrayList<ModuloInterface> modulosInterface;
    private static ArrayList<Wire> listaFios;
    private static ArrayList<String> modulosAInstanciar;
    private static Map<String,String> tabelaDeModulos;
    public Map<String,String> getTabelaDeModulos() {
        return tabelaDeModulos;
    }

    public  String getModuleName() {
        return moduleName;
    }

    public  void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    
    private SimbolTable(){
        tabelaDeModulos = new HashMap<>();
        modulosAInstanciar = new ArrayList<>();
        moduleName = "";
        listaFios = new ArrayList<>();
        instrucoes_imediatas = new ArrayList<>();
        instrucoes_2entradas = new ArrayList<>();
        interfaces = new ArrayList<>();
        tiposFio = new ArrayList<>();
        modulosImediatos = new ArrayList<>();
        modulosDuasEntradas = new ArrayList<>();
        modulosInterface = new ArrayList<>();
    }
    
    public static synchronized SimbolTable getInstance(){
        if (uniqueInstance == null){
            uniqueInstance= new SimbolTable();
        }
        return uniqueInstance;
    }
    
    
    
    
    public void insertItem(String instruction,String type){
        switch(type){
            case "type1":
                instrucoes_imediatas.add(instruction);
                break;
            case "type2":
                instrucoes_2entradas.add(instruction);
                break;
            case "typeI":
                interfaces.add(instruction);
                break;   
            default:
                System.out.println("Deu Ruim");
                break;   
        }
    }
    
    
    public int existInList(String item){
        for (int i=0; i<instrucoes_imediatas.size(); i++){
            if (instrucoes_imediatas.get(i).equals(item)){
                return 1;
            }
        }
        
        for (int i=0; i<instrucoes_2entradas.size(); i++){
            if (instrucoes_2entradas.get(i).equals(item)){
                return 2;
            }
        }        
        
        for (int i=0; i<interfaces.size(); i++){
            if (interfaces.get(i).equals(item)){
                return 3;
            }
        }        
        return -1;
    }
    

    
    public void insertWire(String wire){
        tiposFio.add(wire);
    }
    
    
    public void inserirImediato(Modulo1Entrada item){
        modulosImediatos.add(item);
    }    
    public void inserir2Entradas(Modulo2Entradas item){
        modulosDuasEntradas.add(item);
    }
    public void inserirInterface(ModuloInterface item){
        modulosInterface.add(item);
    }
    public void inserirfio (Wire item){
            listaFios.add(item);
        }

    public  ArrayList<Modulo1Entrada> getModulosImediatos() {
        return modulosImediatos;
    }

    public  ArrayList<Modulo2Entradas> getModulosDuasEntradas() {
        return modulosDuasEntradas;
    }

    public  ArrayList<ModuloInterface> getModulosInterface() {
        return modulosInterface;
    }

    public ArrayList<Wire> getFios() {
        return listaFios;
    }
      
    public ArrayList<String> getModulosAInstanciar(){
        return modulosAInstanciar;
    }
 
}
