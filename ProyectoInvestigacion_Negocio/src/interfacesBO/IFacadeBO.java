
package interfacesBO;

import dtos.ProfesorProyectoDTO;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import entidades.PeriodoParticipacion;
import entidades.Profesor;
import entidades.Programa;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;


public interface IFacadeBO {
    
    //LineasInvestigacionBO
    public boolean agregarLinea(LineaInvestigacion lineaInvestigacion);
    public boolean actualizarLinea(LineaInvestigacion lineaInvestigacion);
    public boolean eliminarLinea(ObjectId idLineaInvestigacion);
    public LineaInvestigacion consultarLineas(ObjectId idLineaInvestigacion);
    public List<LineaInvestigacion> consultarTodosLineas();

    //ProfesoresBO
    public List<Profesor> consultarTodosProfesores(); 
    public List<Doctor> consultarTodosDoctores();
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores(); 
    public boolean agregarProfesor(Profesor profesor);
    public boolean eliminarProfesor(ObjectId idProfesor) throws Exception;
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor);
    public Doctor consultarDoctorProfesor(ObjectId idDoctor);
    public boolean esInvestigador(ObjectId idProfesor);
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador);
    public Profesor consultarProfesor(ObjectId idProfesor);
    
    //ProgramasBO
    public boolean agregarPrograma(Programa programa);
    public boolean actualizarPrograma(Programa programa);
    public boolean eliminarPrograma(ObjectId idPrograma);
    public Programa consultarPrograma(ObjectId idPrograma);
    public List<Programa> consultarTodosProgramas();
    
    //ProyectosBO
    public boolean agregarProyecto(Proyecto proyecto) throws Exception;
    public boolean actualizarProyecto(Proyecto proyecto) throws Exception;
    public boolean eliminarProyecto(ObjectId idProyecto);
    public List<Proyecto> consultarTodosProyectos();
    public Proyecto consultarProyecto(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
//    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean estaRepetidoNombre(String nombre);
    public boolean estaRepetidoCodigo(String codigo);
    public boolean estaRepetidoAcronimo(String acronimo);
    public Proyecto consultarPorCodigo(String codigo);
    public Proyecto consultarPorNombre(String nombre);
    public Proyecto consultarPorAcronimo(String acronimo);
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin);
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador);
    public List<String> consultarCodigos();
    public List<String> consultarNombres();
    public List<String> consultarAcronimos();
    public List<Proyecto> consultarVigentes();
    public boolean validarFechasReales(Proyecto proyecto);
    public boolean validarFechasRealesIntegrante(PeriodoParticipacion periodo);
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion);
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion);

}
