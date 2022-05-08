/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import interfacesBO.IDoctoresBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.INoDoctoresBO;
import interfacesBO.IProyectoBO;

/**
 *
 * @author jegav
 */
public class BOSFactory {
    public static IDoctoresBO crearDoctoresBO(){
        return new DoctoresBO();
    }
    
    public static INoDoctoresBO crearNoDoctoresBO(){
        return new NoDoctoresBO();
    }
    
    public static IProyectoBO crearProyectoBO(){
        return new ProyectoBO();
    }
    
    public static ILineaInvestigacionBO crearLineaInvestigacionBO(){
        return new LineaInvestigacionBO();
    }
    
}