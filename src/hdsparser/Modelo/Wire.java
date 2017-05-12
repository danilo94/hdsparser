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
public class Wire {
    
    private String id;
    private String wire_Width;
    
    
    public String getId() {
        return id;
    }

    public String getWire_Width() {
        return wire_Width;
    }

    public Wire(String id,String wire_width) {
        this.id = id;
        this.wire_Width = wire_width;
    }


    
    
    
    
}
