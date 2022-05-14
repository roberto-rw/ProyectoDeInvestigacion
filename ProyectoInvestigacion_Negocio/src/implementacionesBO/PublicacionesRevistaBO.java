
package implementacionesBO;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionRevista;
import implementacionesDAO.DAOSFactory;
import interfacesBO.IPublicacionesRevistaBO;
import interfacesDAO.IPublicacionesRevistaDAO;
import java.util.List;
import org.bson.types.ObjectId;


public class PublicacionesRevistaBO implements IPublicacionesRevistaBO{
    
    IPublicacionesRevistaDAO publicacionesRevistaDAO = DAOSFactory.crearPublicacionesRevistaDAO();
    
    @Override
    public boolean agregar(PublicacionRevista publicacionRevista) {
        return publicacionesRevistaDAO.agregar(publicacionRevista);
    }

    @Override
    public boolean actualizar(PublicacionRevista publicacionRevista) {
        return publicacionesRevistaDAO.actualizar(publicacionRevista);
    }

    @Override
    public boolean eliminar(ObjectId idPublicacionRevista) {
        return publicacionesRevistaDAO.eliminar(idPublicacionRevista);
    }

    @Override
    public PublicacionRevista consultar(ObjectId idPublicacionRevista) {
        return publicacionesRevistaDAO.consultar(idPublicacionRevista);
    }

    @Override
    public List<PublicacionRevista> consultarTodos() {
        return publicacionesRevistaDAO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idPublicacionRevista) {
        return publicacionesRevistaDAO.consultarProyecto(idPublicacionRevista);
    }

    @Override
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionRevista) {
        return publicacionesRevistaDAO.consultarAutores(idPublicacionRevista);
    }
    
}
