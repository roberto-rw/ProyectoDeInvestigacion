package interfacesDAO;

import dtos.AutorDTO;
import entidades.LineaInvestigacion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author J.Fernando <josefer.hernandez@hotmail.com>
 */
public interface ILineaInvestigacionDAO {
    public boolean agregar(LineaInvestigacion lineaInvestigacion);
    public boolean actualizar(LineaInvestigacion lineaInvestigacion);
    public boolean eliminar(ObjectId idLineaInvestigacion);
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion);
    public List<LineaInvestigacion> consultarTodos();
}
