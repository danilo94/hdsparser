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
    private ArrayList <String> inputs;
    private ArrayList <String> outputs;

    public ModuloInterface(String nomeModulo, String identificador, String wireWidth, int elements, String tipoInterface) {
        this.tipoInterface = tipoInterface;
        this.elements = elements;
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
   
    public void insereLista (String fio){
        if (tipoInterface.equals("IN_")){
            outputs.add(fio);
        }else{
            if  (tipoInterface.endsWith("OUT_")){
                inputs.add(fio);
            }
        }
    }
}
