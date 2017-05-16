/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hdsparser;

import Utils.SimbolTable.SimbolTable;
import hdsparser.Controle.Parser;


public class HdsParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          SimbolTable t1 = SimbolTable.getInstance();
          t1.insertItem("ADD", "type2");
          t1.insertItem("MULI", "type1");
          t1.insertItem("REG","type1");
          t1.insertItem("OUT", "typeI");
          t1.insertItem("IN_1", "typeI");
          t1.insertWire("SignalStdLogic1164");
          t1.insertWire("SignalStdLogicVector");
          Parser p1 = new Parser("/home/danilo/PycharmProjects/hdsParser/FiltroFir4.hds");
          
          System.out.println("Parsing Complete !!!!");
          
          for (int i=0; i< SimbolTable.getInstance().getModulosAInstanciar().size(); i++){
              System.out.println(SimbolTable.getInstance().getModulosAInstanciar().get(i));
          }
          
          
          
          
          
        //  System.out.println("Wires: "+t1.getFios().size()+" Modulos Imediatos: "+t1.getModulosImediatos().size()+" Modulos de duas Entradas: "+t1.getModulosDuasEntradas().size());
          
          
    }
    
}
