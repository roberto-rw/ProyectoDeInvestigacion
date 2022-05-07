
package interfaces;

import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
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
    public boolean agregarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean eliminarIntegrantes(List<DetalleProyectoProfesor> ids, ObjectId id);
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean agregarPublicacion(Publicacion publicacion);
}
