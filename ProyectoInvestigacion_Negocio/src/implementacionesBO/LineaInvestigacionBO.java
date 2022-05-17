/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.LineaInvestigacion;
import implementacionesDAO.DAOSFactory;
import implementacionesDAO.Persistencia;
import interfacesBO.ILineaInvestigacionBO;
import interfacesDAO.ILineaInvestigacionDAO;
import interfacesDAO.IPersistencia;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class LineaInvestigacionBO implements ILineaInvestigacionBO{
    
    IPersistencia persistencia = new Persistencia();

    @Override
    public boolean agregar(LineaInvestigacion lineaInvestigacion) {
        return persistencia.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizar(LineaInvestigacion lineaInvestigacion) {
        return persistencia.actualizar(lineaInvestigacion);
    }

    @Override
    public boolean eliminar(ObjectId idLineaInvestigacion) {
        return persistencia.eliminarLineaInvestigacion(idLineaInvestigacion);
    }

    @Override
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion) {
        return persistencia.consultarLineaInvestigacion(idLineaInvestigacion);
    }

    @Override
    public List<LineaInvestigacion> consultarTodos() {
        return persistencia.consultarTodosLineaInvestigacion();
    }
    
}
