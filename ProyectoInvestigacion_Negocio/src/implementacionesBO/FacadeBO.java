
package implementacionesBO;

import dtos.AutorDTO;
import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import entidades.Profesor;
import entidades.Programa;
import entidades.Proyecto;
import entidades.Publicacion;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import interfacesBO.IFacadeBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.IProfesoresBO;
import interfacesBO.IProgramasBO;
import interfacesBO.IProyectosBO;
import interfacesBO.IPublicacionesCongresoBO;
import interfacesBO.IPublicacionesRevistaBO;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;


public class FacadeBO implements IFacadeBO{

    ILineaInvestigacionBO lineasInvestigacionBO;
    IProgramasBO programasBO;
    IProyectosBO proyectosBO;
    IPublicacionesCongresoBO publicacionesCongresoBO;
    IPublicacionesRevistaBO publicacionesRevistaBO;
    IProfesoresBO profesoresBO;
    
    
    public FacadeBO(){
        
        lineasInvestigacionBO = BOSFactory.crearLineaInvestigacionBO();
        programasBO = BOSFactory.crearProgramaBO();
        proyectosBO = BOSFactory.crearProyectoBO();
        publicacionesCongresoBO = BOSFactory.crearPublicacionesCongresoBO();
        publicacionesRevistaBO = BOSFactory.crearPublicacionesRevistaBO();
        profesoresBO = BOSFactory.crearProfesoresBO();
    }

    //LineasInvestigacionBO
    @Override
    public boolean agregarLinea(LineaInvestigacion lineaInvestigacion) {
        return lineasInvestigacionBO.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizarLinea(LineaInvestigacion lineaInvestigacion) {
        return lineasInvestigacionBO.actualizar(lineaInvestigacion);
    }

    @Override
    public boolean eliminarLinea(ObjectId idLineaInvestigacion) {
        return lineasInvestigacionBO.eliminar(idLineaInvestigacion);
    }

    @Override
    public LineaInvestigacion consultarLineas(ObjectId idLineaInvestigacion) {
        return lineasInvestigacionBO.consultar(idLineaInvestigacion);
    }

    @Override
    public List<LineaInvestigacion> consultarTodosLineas() {
        return lineasInvestigacionBO.consultarTodos();
    }

    
    //ProfesoresBO
    @Override
    public List<Profesor> consultarTodosProfesores() {
        return profesoresBO.consultarTodosProfesores();
    }

    @Override
    public List<Doctor> consultarTodosDoctores() {
        return profesoresBO.consultarTodosDoctores();
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores() {
        return profesoresBO.consultarTodosInvestigadorDoctores();
    }

    @Override
    public boolean agregarProfesor(Profesor profesor) {
        return profesoresBO.agregar(profesor);
    }

    @Override
    public boolean eliminarProfesor(ObjectId idProfesor) {
        return profesoresBO.eliminar(idProfesor);
    }

    @Override
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor) {
        return profesoresBO.consultarNoDoctor(idNoDoctor);
    }

    @Override
    public Doctor consultarDoctorProfesor(ObjectId idDoctor) {
        return profesoresBO.consultarDoctor(idDoctor);
    }

    @Override
    public boolean esInvestigador(ObjectId idProfesor) {
        return profesoresBO.esInvestigador(idProfesor);
    }

    @Override
    public InvestigadorDoctor consultarProfesor(ObjectId idInvestigador) {
        return profesoresBO.consultarInvestigadorDoctor(idInvestigador);
    }

    
    
    
    //ProgramasBO
    @Override
    public boolean agregarPrograma(Programa programa) {
        return programasBO.agregar(programa);
    }

    @Override
    public boolean actualizarPrograma(Programa programa) {
        return programasBO.actualizar(programa);
    }

    @Override
    public boolean eliminarPrograma(ObjectId idPrograma) {
        return programasBO.eliminar(idPrograma);
    }

    @Override
    public Programa consultarPrograma(ObjectId idPrograma) {
        return programasBO.consultar(idPrograma);
    }

    @Override
    public List<Programa> consultarTodosProgramas() {
        return programasBO.consultarTodos();
    }

    
    
    //ProyectosBO
    @Override
    public boolean agregarProyecto(Proyecto proyecto) {
        return proyectosBO.agregar(proyecto);
    }

    @Override
    public boolean actualizarProyecto(Proyecto proyecto) {
        return proyectosBO.actualizar(proyecto);
    }

    @Override
    public boolean eliminarProyecto(ObjectId idProyecto) {
        return proyectosBO.eliminar(idProyecto);
    }

    @Override
    public List<Proyecto> consultarTodosProyectos() {
        return proyectosBO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idProyecto) {
        return proyectosBO.consultar(idProyecto);
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        return proyectosBO.consultarLineasInvestigacion(idProyecto);
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        return proyectosBO.consultarIntegrantes(idProyecto);
    }

    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        return proyectosBO.actualizarIntegrantes(integrantes, id);
    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        return proyectosBO.agregarPublicacion(idProyecto, publicacion);
    }

    @Override
    public boolean estaRepetidoNombre(String nombre) {
        return proyectosBO.estaRepetidoNombre(nombre);
    }

    @Override
    public boolean estaRepetidoCodigo(String codigo) {
        return proyectosBO.estaRepetidoCodigo(codigo);
    }

    @Override
    public boolean estaRepetidoAcronimo(String acronimo) {
        return proyectosBO.estaRepetidoAcronimo(acronimo);
    }

    @Override
    public Proyecto consultarPorCodigo(String codigo) {
        return proyectosBO.consultarPorCodigo(codigo);
    }

    @Override
    public Proyecto consultarPorNombre(String nombre) {
        return proyectosBO.consultarPorNombre(nombre);
    }

    @Override
    public Proyecto consultarPorAcronimo(String acronimo) {
        return proyectosBO.consultarPorAcronimo(acronimo);
    }

    @Override
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin) {
        return proyectosBO.consultarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        return proyectosBO.consultarPorCaracteristicas(idPrograma, presupuesto, filtroPresupuesto, investigador, patrocinador);
    }

    @Override
    public List<String> consultarCodigos() {
        return proyectosBO.consultarCodigos();
    }

    @Override
    public List<String> consultarNombres() {
        return proyectosBO.consultarNombres();
    }

    @Override
    public List<String> consultarAcronimos() {
        return proyectosBO.consultarAcronimos();
    }

    @Override
    public boolean validarFechasReales(Proyecto proyecto) {
        return proyectosBO.validarFechasReales(proyecto);
    }
    
    
    //PublicacionesCongresoBO

    @Override
    public boolean agregarPublicacionCongreso(PublicacionCongreso publicacionCongreso) {
        return publicacionesCongresoBO.agregar(publicacionCongreso);
    }

    @Override
    public boolean actualizarPublicacionCongreso(PublicacionCongreso publicacionCongreso) {
        return publicacionesCongresoBO.actualizar(publicacionCongreso);
    }

    @Override
    public boolean eliminarPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoBO.eliminar(idPublicacionCongreso);
    }

    @Override
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoBO.consultar(idPublicacionCongreso);
    }

    @Override
    public List<PublicacionCongreso> consultarTodos() {
        return publicacionesCongresoBO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyectoPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoBO.consultarProyecto(idPublicacionCongreso);
    }

    @Override
    public List<AutorDTO> consultarAutoresPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return publicacionesCongresoBO.consultarAutores(idPublicacionCongreso);
    }

    
    //PublicacionesRevistaBO
    
    @Override
    public boolean agregarPublicacionRevista(PublicacionRevista publicacionRevista) {
        return publicacionesRevistaBO.agregar(publicacionRevista);
    }

    @Override
    public boolean actualizarPublicacionRevista(PublicacionRevista publicacionRevista) {
        return publicacionesRevistaBO.actualizar(publicacionRevista);
    }

    @Override
    public boolean eliminarPublicacionRevista(ObjectId idPublicacionRevista) {
        return publicacionesRevistaBO.eliminar(idPublicacionRevista);
    }

    @Override
    public PublicacionRevista consultarPublicacionRevista(ObjectId idPublicacionRevista) {
        return publicacionesRevistaBO.consultar(idPublicacionRevista);
    }

    @Override
    public List<PublicacionRevista> consultarTodosPublicacionRevista() {
        return publicacionesRevistaBO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyectoPublicacionRevista(ObjectId idPublicacionRevista) {
        return publicacionesRevistaBO.consultarProyecto(idPublicacionRevista);
    }

    @Override
    public List<AutorDTO> consultarAutoresPublicacionRevista(ObjectId idPublicacionRevista) {
        return publicacionesRevistaBO.consultarAutores(idPublicacionRevista);
    }
    
}
