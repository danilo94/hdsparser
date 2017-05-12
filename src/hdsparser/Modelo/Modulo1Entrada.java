/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser.Modelo;

/**
 *
 * @author danilo
 */
public class Modulo1Entrada {
    
    private String nomeModulo;
    private String identificador;
    private String entrada1;
    private String enable;
    private String clkWire;
    private String enablein1;
    private String outR;
    private String output;
    private String wireWidth;
    private String constant;

    public Modulo1Entrada(String nomeModulo, String identificador, String wireWidth, String constant) {
        this.nomeModulo = nomeModulo;
        this.identificador = identificador;
        this.wireWidth = wireWidth;
        this.constant = constant;
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

    public String getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(String entrada1) {
        this.entrada1 = entrada1;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getClkWire() {
        return clkWire;
    }

    public void setClkWire(String clkWire) {
        this.clkWire = clkWire;
    }

    public String getEnablein1() {
        return enablein1;
    }

    public void setEnablein1(String enablein1) {
        this.enablein1 = enablein1;
    }

    public String getOutR() {
        return outR;
    }

    public void setOutR(String outR) {
        this.outR = outR;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getWireWidth() {
        return wireWidth;
    }

    public void setWireWidth(String wireWidth) {
        this.wireWidth = wireWidth;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }
    
    
    
    
}
