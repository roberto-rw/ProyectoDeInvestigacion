
package implementacionesBO;

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
import implementacionesDAO.DAOSFactory;
import interfacesBO.IFacadeBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.IProgramasBO;
import interfacesBO.IProyectosBO;
import interfacesBO.IPublicacionesCongresoBO;
import interfacesBO.IPublicacionesRevistaBO;
import interfacesDAO.ILineaInvestigacionDAO;
import interfacesDAO.IProgramasDAO;
import interfacesDAO.IProyectoDAO;
import interfacesDAO.IPublicacionesCongresoDAO;
import interfacesDAO.IPublicacionesRevistaDAO;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;


public class FacadeBO implements IFacadeBO{

    ILineaInvestigacionBO lineasInvestigacionBO;
    IProgramasBO programasBO;
    IProyectosBO proyectosBO;
    IPublicacionesCongresoBO publicacionesCongresoBO;
    IPublicacionesRevistaBO publicacionesRevistaBO;
    
    ILineaInvestigacionDAO lineasInvestigacionDAO;
    IProgramasDAO programasDAO;i
    IProyectoDAO proyectoDAO;
    IPublicacionesCongresoDAO publicacionesCongresoDAO;
    IPublicacionesRevistaDAO publicacionesRevistaDAO;
    
    public FacadeBO(){
        lineasInvestigacionDAO = DAOSFactory.crearLineaInvestigacionDAO();
        programasDAO = DAOSFactory.crearProgramasDAO();
        proyectoDAO = DAOSFactory.crearProyectoDAO();
        publicacionesCongresoDAO = DAOSFactory.crearPublicacionesCongresoDAO();
        publicacionesRevistaDAO = DAOSFactory.crearPublicacionesRevistaDAO();
    }

    //LineasInvestigacionBO
    @Override
    public boolean agregarLinea(LineaInvestigacion lineaInvestigacion) {
        return lineasInvestigacionBO.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizarLinea(LineaInvestigacion lineaInvestigacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarLinea(ObjectId idLineaInvestigacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineaInvestigacion consultarLineas(ObjectId idLineaInvestigacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaInvestigacion> consultarTodosLineas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    //ProfesoresBO
    @Override
    public List<Profesor> consultarTodosProfesores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Doctor> consultarTodosDoctores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarProfesor(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarProfesor(ObjectId idProfesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Doctor consultarDoctorProfesor(ObjectId idDoctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esInvestigador(ObjectId idProfesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InvestigadorDoctor consultarProfesor(ObjectId idInvestigador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    //ProgramasBO
    @Override
    public boolean agregarPrograma(Programa programa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarPrograma(Programa programa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarPrograma(ObjectId idPrograma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Programa consultarPrograma(ObjectId idPrograma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Programa> consultarTodosProgramas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    //ProyectosBO
    @Override
    public boolean agregarProyecto(Proyecto proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarProyecto(Proyecto proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarProyecto(ObjectId idProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyecto> consultarTodosProyectos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaRepetidoNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaRepetidoCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaRepetidoAcronimo(String acronimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultarPorCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultarPorAcronimo(String acronimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> consultarCodigos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> consultarNombres() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> consultarAcronimos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarFechasReales(Proyecto proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
