
package interfaces;

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
    public boolean agregarLineaInvestigacion(LineaInvestigacion lineaInvestigacion);
    public boolean agregarProfesores(Profesor profesor);
    public boolean agregarPublicacion(Publicacion publicacion);
}
