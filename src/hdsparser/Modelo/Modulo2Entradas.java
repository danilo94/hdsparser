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
public class Modulo2Entradas{
    private String nomeModulo;
    private String identificador;
    private String entrada1;
    private String entrada2;
    private String enable;
    private String clkWire;
    private String enablein1;
    private String enablein2;
    private String outR;
    private String output;
    private String wireWidth;
    private String reset;

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public Modulo2Entradas(String nomeModulo, String identificador, String wireWidth) {
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

    public String getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(String entrada1) {
        this.entrada1 = entrada1;
    }

    public String getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(String entrada2) {
        this.entrada2 = entrada2;
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

    public String getEnablein2() {
        return enablein2;
    }

    public void setEnablein2(String enablein2) {
        this.enablein2 = enablein2;
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
    
    
    
    
}
