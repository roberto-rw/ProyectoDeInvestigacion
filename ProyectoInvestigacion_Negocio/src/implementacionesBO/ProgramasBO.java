/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Programa;
import implementacionesDAO.Persistencia;
import interfacesBO.IProgramasBO;
import interfacesDAO.IPersistencia;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public class ProgramasBO implements IProgramasBO{

    IPersistencia persistencia = new Persistencia();

    @Override
    public boolean agregar(Programa programa) {
        return persistencia.agregar(programa);
    }

    @Override
    public boolean actualizar(Programa programa) {
        return persistencia.actualizar(programa);
    }

    @Override
    public boolean eliminar(ObjectId idPrograma) {
        return persistencia.eliminarPrograma(idPrograma);
    }

    @Override
    public List<Programa> consultarTodos() {
        return persistencia.consultarTodosPrograma();
    }

    @Override
    public Programa consultar(ObjectId idPrograma) {
        return persistencia.consultarPrograma(idPrograma);
    }

    
}
