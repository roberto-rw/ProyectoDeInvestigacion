/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOSFactory.crearProyectoDAO().consultarIntegrantes(new ObjectId("627ae300b1b5ef66f1af7190"));
    }
    
}
