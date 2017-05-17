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
public class ModuloaInstanciar {

    
    private String nomeModulo;
    private String caminhoModulo;

    public ModuloaInstanciar(String nomeModulo, String caminhoModulo) {
        this.nomeModulo = nomeModulo;
        this.caminhoModulo = caminhoModulo;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public String getCaminhoModulo() {
        return caminhoModulo;
    }
    
    
    
}
