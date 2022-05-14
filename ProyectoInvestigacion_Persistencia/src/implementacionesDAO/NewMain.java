/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import interfacesDAO.INoDoctoresDAO;
import interfacesDAO.IProyectoDAO;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IProyectoDAO proyectoDAO = DAOSFactory.crearProyectoDAO();
        
        System.out.print(proyectoDAO.consultarIntegrantes(new ObjectId("6278bb0dc2ff985ee46c17f8")));
        
        
    }
    
}
