/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.LineaInvestigacion;
import implementacionesDAO.DAOSFactory;
import interfacesBO.ILineaInvestigacionBO;
import interfacesDAO.ILineaInvestigacionDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class LineaInvestigacionBO implements ILineaInvestigacionBO{
    
    ILineaInvestigacionDAO lineaInvestigacionDAO = DAOSFactory.crearLineaInvestigacionDAO();

    @Override
    public boolean agregar(LineaInvestigacion lineaInvestigacion) {
        return lineaInvestigacionDAO.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizar(LineaInvestigacion lineaInvestigacion) {
        return lineaInvestigacionDAO.actualizar(lineaInvestigacion);
    }

    @Override
    public boolean eliminar(ObjectId idLineaInvestigacion) {
        return lineaInvestigacionDAO.eliminar(idLineaInvestigacion);
    }

    @Override
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion) {
        return lineaInvestigacionDAO.consultar(idLineaInvestigacion);
    }

    @Override
    public List<LineaInvestigacion> consultarTodos() {
        return lineaInvestigacionDAO.consultarTodos();
    }
    
}
