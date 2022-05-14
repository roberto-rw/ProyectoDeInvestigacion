
package interfacesBO;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionRevista;
import java.util.List;
import org.bson.types.ObjectId;


public interface IPublicacionesRevistaBO {
    public boolean agregar(PublicacionRevista publicacionRevista);
    public boolean actualizar(PublicacionRevista publicacionRevista);
    public boolean eliminar(ObjectId idPublicacionRevista);
    public PublicacionRevista consultar(ObjectId idPublicacionRevista);
    public List<PublicacionRevista> consultarTodos();
    public Proyecto consultarProyecto(ObjectId idPublicacionRevista);
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionRevista);
}
