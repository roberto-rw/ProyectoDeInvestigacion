/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import interfacesDAO.IConexionBD;
import interfacesDAO.IDoctoresDAO;
import interfacesDAO.ILineaInvestigacionDAO;
import interfacesDAO.INoDoctoresDAO;
import interfacesDAO.IProgramasDAO;
import interfacesDAO.IProyectoDAO;
import interfacesDAO.IPublicacionesRevistaDAO;
import interfacesDAO.IPublicacionesCongresoDAO;
/**
 *
 * @author jegav
 */
public class DAOSFactory {
    private static final IConexionBD conexionBD = ConexionBD.getInstance();
    
    public static IDoctoresDAO crearDoctoresDAO(){
        return new DoctoresDAO(conexionBD);
    }
    
    public static ILineaInvestigacionDAO crearLineaInvestigacionDAO(){
        return new LineaInvestigacionDAO(conexionBD);
    }
    
    public static INoDoctoresDAO crearNoDoctoresDAO(){
        return new NoDoctoresDAO(conexionBD);
    }
    
    public static IProgramasDAO crearProgramasDAO(){
        return new ProgramasDAO(conexionBD);
    }
    
    public static IProyectoDAO crearProyectoDAO(){
        return new ProyectoDAO(conexionBD);
    }
    
    public static IPublicacionesCongresoDAO crearPublicacionesCongresoDAO(){
        return new PublicacionesCongresoDAO(conexionBD);
    }
    
    public static IPublicacionesRevistaDAO crearPublicacionesRevistaDAO(){
        return new PublicacionesRevistaDAO(conexionBD);
    }
}
