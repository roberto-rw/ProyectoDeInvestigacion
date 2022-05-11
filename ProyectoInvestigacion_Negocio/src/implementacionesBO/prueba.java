/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.NoDoctor;
import entidades.Profesor;
import interfacesBO.IProfesoresBO;

/**
 *
 * @author jegav
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IProfesoresBO profesoresBO = BOSFactory.crearProfesoresBO();
        Profesor profesor = new Doctor("Nombre", "ApellidoM", "ApellidoP", "Despacho", "6441315443");
        System.out.println(profesor.getClass() == InvestigadorDoctor.class);
        
    }
    
}
