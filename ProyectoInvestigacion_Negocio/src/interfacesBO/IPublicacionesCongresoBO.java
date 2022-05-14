
package interfacesBO;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import java.util.List;
import org.bson.types.ObjectId;

public interface IPublicacionesCongresoBO {
    
    public boolean agregar(PublicacionCongreso publicacionCongreso);
    public boolean actualizar(PublicacionCongreso publicacionCongreso);
    public boolean eliminar(ObjectId idPublicacionCongreso);
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso);
    public List<PublicacionCongreso> consultarTodos();
    public Proyecto consultarProyecto(ObjectId idPublicacionCongreso);
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionCongreso);
}
