
package interfacesDAO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import java.util.Date;
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
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion);
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion);
    public Proyecto consultarPorNombre(String nombre);
    public Proyecto consultarPorCodigo(String codigo);
    public Proyecto consultarPorAcronimo(String acronimo);
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin);
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador);
    
}
