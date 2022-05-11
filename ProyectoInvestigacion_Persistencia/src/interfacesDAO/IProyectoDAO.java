
package interfacesDAO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
import entidades.LineaInvestigacion;
import entidades.Profesor;
import entidades.Proyecto;
import entidades.Publicacion;
import java.util.List;
import org.bson.types.ObjectId;


public interface IProyectoDAO {
    public boolean agregar(Proyecto proyecto);
    public boolean actualizar(Proyecto proyecto);
    public boolean eliminar(ObjectId idProyecto);
    public List<Proyecto> consultarTodos();
    public Proyecto consultar(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion);
    public boolean estaRepetidoNombre(String nombre);
    public boolean estaRepetidoCodigo(String codigo);
    public boolean estaRepetidoAcronimo(String acronimo);
    
}
