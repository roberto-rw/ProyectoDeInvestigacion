
package implementacionesBO;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import implementacionesDAO.DAOSFactory;
import interfacesBO.IPublicacionesCongresoBO;
import interfacesDAO.IPublicacionesCongresoDAO;
import java.util.List;
import org.bson.types.ObjectId;


public class PublicacionesCongresoBO implements IPublicacionesCongresoBO{

    IPublicacionesCongresoDAO publicacionesCongresoDAO  = DAOSFactory.crearPublicacionesCongresoDAO();
    
    @Override
    public boolean agregar(PublicacionCongreso publicacionCongreso) {
        return publicacionesCongresoDAO.agregar(publicacionCongreso);
    }

    @Override
    public boolean actualizar(PublicacionCongreso publicacionCongreso) {
        return publicacionesCongresoDAO.actualizar(publicacionCongreso);
    }

    @Override
    public boolean eliminar(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoDAO.eliminar(idPublicacionCongreso);
    }

    @Override
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoDAO.consultar(idPublicacionCongreso);
    }

    @Override
    public List<PublicacionCongreso> consultarTodos() {
        return publicacionesCongresoDAO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoDAO.consultarProyecto(idPublicacionCongreso);
    }

    @Override
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoDAO.consultarAutores(idPublicacionCongreso);
    }
    
}
