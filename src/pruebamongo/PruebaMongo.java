/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebamongo;

/**
 *
 * @author oracle
 */
public class PruebaMongo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Metodos m= new Metodos();
        m.conexion("training", "scores");
      //  m.actualizar();
        m.desconexion();
        
    }
}
