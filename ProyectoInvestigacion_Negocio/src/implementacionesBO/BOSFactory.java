/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import interfacesBO.IDoctoresBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.INoDoctoresBO;
import interfacesBO.IProfesoresBO;
import interfacesBO.IProgramasBO;
import interfacesBO.IProyectosBO;

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
    
    public static IProyectosBO crearProyectoBO(){
        return new ProyectoBO();
    }
    
    public static ILineaInvestigacionBO crearLineaInvestigacionBO(){
        return new LineaInvestigacionBO();
    }
    
    public static IProgramasBO crearProgramaBO(){
        return new ProgramasBO();
    }
    
    public static IProfesoresBO crearProfesoresBO(){
        return new ProfesoresBO();
    }
    
}
