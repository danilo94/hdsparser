/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser.Modelo;

import java.util.ArrayList;

/**
 *
 * @author danilo
 */
public class ModuloInterface{
    private String nomeModulo;
    private String identificador;
    private String wireWidth;
    private String tipoInterface;
    private int elements;
    private int interfacesCreated=1;
    private ArrayList <String> names;
    private ArrayList <String> inputs;
    private ArrayList <String> outputs;
    private  ArrayList <String> rIn;
    private  String rOut;
    private ArrayList <String> namesRinRout;

    public String getrOut() {
        return rOut;
    }

    public void setrOut(String rOut) {
        this.rOut = rOut;
    }

    public int getInterfacesCreated() {
        return interfacesCreated;
    }

    public void setInterfacesCreated(int interfacesCreated) {
        this.interfacesCreated = interfacesCreated;
    }

    public ModuloInterface(String nomeModulo, String identificador, String wireWidth, int elements, String tipoInterface) {
        namesRinRout = new ArrayList<>();
        rIn = new ArrayList<>();
        this.tipoInterface = tipoInterface;
        this.elements = elements;
        names = new ArrayList<>();
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        this.nomeModulo = nomeModulo;
        this.identificador = identificador;
        this.wireWidth = wireWidth;
    }

    

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public String getWireWidth() {
        return wireWidth;
    }

    public void setWireWidth(String wireWidth) {
        this.wireWidth = wireWidth;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }

    public String getTipoInterface() {
        return tipoInterface;
    }

    public void setTipoInterface(String tipoInterface) {
        this.tipoInterface = tipoInterface;
    }

    public void setInputs(ArrayList<String> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<String> outputs) {
        this.outputs = outputs;
    }
   
    public void insereLista (String fio,String name){
        if (tipoInterface.equals("IN_")){
            outputs.add(fio);
            names.add(name);
        }else{
            if  (tipoInterface.endsWith("OUT")){
                inputs.add(fio);
                outputs.add(fio);
                names.add(name);
            }
        }
    }
    
    
    public void insereRinRout (String fio, String name){
        if (tipoInterface.equals("IN_")){
            namesRinRout.add(name);
            
        }else{
            if (tipoInterface.equals("OUT")){
                
            }
        }
    }
    
    
    
}
