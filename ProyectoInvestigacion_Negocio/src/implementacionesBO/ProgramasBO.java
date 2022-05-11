/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Programa;
import implementacionesDAO.DAOSFactory;
import interfacesBO.IProgramasBO;
import interfacesDAO.IProgramasDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public class ProgramasBO implements IProgramasBO{

    IProgramasDAO programasDAO = DAOSFactory.crearProgramasDAO();

    @Override
    public boolean agregar(Programa programa) {
        return programasDAO.agregar(programa);
    }

    @Override
    public boolean actualizar(Programa programa) {
        return programasDAO.actualizar(programa);
    }

    @Override
    public boolean eliminar(ObjectId idPrograma) {
        return programasDAO.eliminar(idPrograma);
    }

    @Override
    public List<Programa> consultarTodos() {
        return programasDAO.consultarTodos();
    }

    @Override
    public Programa consultar(ObjectId idPrograma) {
        return programasDAO.consultar(idPrograma);
    }

    
}
