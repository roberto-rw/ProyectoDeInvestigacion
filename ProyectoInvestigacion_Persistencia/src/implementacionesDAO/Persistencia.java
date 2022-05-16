/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import dtos.AutorDTO;
import dtos.PeriodoSupervisionDTO;
import dtos.ProfesorProyectoDTO;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import entidades.Programa;
import entidades.Proyecto;
import entidades.Publicacion;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import interfacesDAO.IDoctoresDAO;
import interfacesDAO.ILineaInvestigacionDAO;
import interfacesDAO.INoDoctoresDAO;
import interfacesDAO.IPersistencia;
import interfacesDAO.IProgramasDAO;
import interfacesDAO.IProyectoDAO;
import interfacesDAO.IPublicacionesCongresoDAO;
import interfacesDAO.IPublicacionesRevistaDAO;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class Persistencia implements IPersistencia{
    IDoctoresDAO doctoresDAO;
    INoDoctoresDAO noDoctoresDAO;
    ILineaInvestigacionDAO lineasInvestigacionDAO;
    IProgramasDAO programasDAO;
    IProyectoDAO proyectoDAO;
    IPublicacionesCongresoDAO publicacionesCongresoDAO;
    IPublicacionesRevistaDAO publicacionesRevistaDAO;
    
    public Persistencia(){
        doctoresDAO = DAOSFactory.crearDoctoresDAO();
        noDoctoresDAO = DAOSFactory.crearNoDoctoresDAO();
        lineasInvestigacionDAO = DAOSFactory.crearLineaInvestigacionDAO();
        programasDAO = DAOSFactory.crearProgramasDAO();
        proyectoDAO = DAOSFactory.crearProyectoDAO();
        publicacionesCongresoDAO = DAOSFactory.crearPublicacionesCongresoDAO();
        publicacionesRevistaDAO = DAOSFactory.crearPublicacionesRevistaDAO();
    }

    
    //DoctoresDAO
    @Override
    public boolean agregar(Doctor doctor) {
        return doctoresDAO.agregar(doctor);
    }

    @Override
    public boolean actualizar(Doctor doctor) {
        return doctoresDAO.actualizar(doctor);
    }

    @Override
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor) {
        return doctoresDAO.agregarInvestigadorDoctor(investigadorDoctor);
    }

    @Override
    public boolean eliminarDoctor(ObjectId idDoctor) {
        return doctoresDAO.eliminar(idDoctor);
    }

    @Override
    public Doctor consultarDoctor(ObjectId idDoctor) {
        return doctoresDAO.consultar(idDoctor);
    }

    @Override
    public List<Doctor> consultarTodosDoctor() {
        return doctoresDAO.consultarTodos();
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadoresDoctor() {
        return doctoresDAO.consultarTodosInvestigadores();
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacionDoctor(ObjectId idDoctor) {
         return doctoresDAO.consultarLineasInvestigacion(idDoctor);
    }

    @Override
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador) {
        return doctoresDAO.consultarInvestigador(idInvestigador);
    }

    
    //NoDoctoresDAO
    @Override
    public boolean agregar(NoDoctor noDoctor) {
        return noDoctoresDAO.agregar(noDoctor);
    }

    @Override
    public boolean actualizar(NoDoctor noDoctor) {
        return noDoctoresDAO.actualizar(noDoctor);
    }

    @Override
    public boolean agregarInvestigadorNoDoctor(InvestigadorNoDoctor investigadorNoDoctor) {
        return noDoctoresDAO.agregarInvestigadorNoDoctor(investigadorNoDoctor);
    }

    @Override
    public boolean eliminarNoDoctor(ObjectId idNoDoctor) {
        return noDoctoresDAO.eliminar(idNoDoctor);
    }

    @Override
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultar(idNoDoctor);
    }

    @Override
    public List<NoDoctor> consultarTodosNoDoctor() {
        return noDoctoresDAO.consultarTodos();
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacionNoDoctor(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultarLineasInvestigacion(idNoDoctor);
    }

    @Override
    public List<PeriodoSupervisionDTO> consultarSupervisiones(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultarSupervisiones(idNoDoctor);
    }

    @Override
    public InvestigadorNoDoctor consultarInvestigadorNoDoctor(ObjectId idInvestigador) {
        return noDoctoresDAO.consultarInvestigador(idInvestigador);
    }
    
    //LineaInvestigacionDAO
    @Override
    public boolean agregar(LineaInvestigacion lineaInvestigacion) {
        return this.lineasInvestigacionDAO.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizar(LineaInvestigacion lineaInvestigacion) {
        return this.lineasInvestigacionDAO.actualizar(lineaInvestigacion);
    }

    @Override
    public boolean eliminarLineaInvestigacion(ObjectId idLineaInvestigacion) {
        return this.lineasInvestigacionDAO.eliminar(idLineaInvestigacion);
    }

    @Override
    public LineaInvestigacion consultarLineaInvestigacion(ObjectId idLineaInvestigacion) {
        return this.lineasInvestigacionDAO.consultar(idLineaInvestigacion);
    }

    @Override
    public List<LineaInvestigacion> consultarTodosLineaInvestigacion() {
        return this.lineasInvestigacionDAO.consultarTodos();
    }
    
    //ProgramasDAO
    @Override
    public boolean agregar(Programa programa) {
        return this.programasDAO.agregar(programa);
    }

    @Override
    public boolean actualizar(Programa programa) {
        return this.programasDAO.actualizar(programa);
    }

    @Override
    public boolean eliminarPrograma(ObjectId idPrograma) {
        return this.programasDAO.eliminar(idPrograma);
    }

    @Override
    public Programa consultarPrograma(ObjectId idPrograma) {
        return this.programasDAO.consultar(idPrograma);
    }

    @Override
    public List<Programa> consultarTodosPrograma() {
        return this.programasDAO.consultarTodos();
    }

    //ProyectoDAO
    @Override
    public boolean agregar(Proyecto proyecto) {
        return proyectoDAO.agregar(proyecto);
    }

    @Override
    public boolean actualizar(Proyecto proyecto) {
        return proyectoDAO.actualizar(proyecto);
    }

    @Override
    public boolean eliminarProyecto(ObjectId idProyecto) {
        return proyectoDAO.eliminar(idProyecto);
    }

    @Override
    public List<Proyecto> consultarTodosProyecto() {
        return proyectoDAO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idProyecto) {
        return proyectoDAO.consultar(idProyecto);
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacionProyecto(ObjectId idProyecto) {
        return proyectoDAO.consultarLineasInvestigacion(idProyecto);
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        return proyectoDAO.consultarIntegrantes(idProyecto);
    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        return true;
    }

    @Override
    public Proyecto consultarProyectoPorNombre(String nombre) {
        return proyectoDAO.consultarPorNombre(nombre);
    }

    @Override
    public Proyecto consultarProyectoPorCodigo(String codigo) {
        return proyectoDAO.consultarPorCodigo(codigo);
    }

    @Override
    public Proyecto consultarProyectoPorAcronimo(String acronimo) {
        return proyectoDAO.consultarPorAcronimo(acronimo);
    }

    @Override
    public List<Proyecto> consultarProyectoPorFechas(Date fechaInicio, Date fechaFin) {
        return proyectoDAO.consultarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        return proyectoDAO.consultarPorCaracteristicas(idPrograma, presupuesto, filtroPresupuesto, investigador, patrocinador);
    }
    
    //PublicacionCongresoDAO

    @Override
    public boolean agregar(PublicacionCongreso publicacionCongreso) {
        return this.publicacionesCongresoDAO.agregar(publicacionCongreso);
    }

    @Override
    public boolean actualizar(PublicacionCongreso publicacionCongreso) {
        return this.publicacionesCongresoDAO.actualizar(publicacionCongreso);
    }

    @Override
    public boolean eliminarPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return this.publicacionesCongresoDAO.eliminar(idPublicacionCongreso);
    }

    @Override
    public PublicacionCongreso consultarPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return this.publicacionesCongresoDAO.consultar(idPublicacionCongreso);
    }

    @Override
    public List<PublicacionCongreso> consultarTodosPublicacionCongreso() {
        return this.publicacionesCongresoDAO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyectoPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return this.publicacionesCongresoDAO.consultarProyecto(idPublicacionCongreso);
    }

    @Override
    public List<AutorDTO> consultarAutoresPublicacionCongreso(ObjectId idPublicacionCongreso) {
        return this.publicacionesCongresoDAO.consultarAutores(idPublicacionCongreso);
    }
    
    //PublicacionRevistaDAO

    @Override
    public boolean agregar(PublicacionRevista publicacionRevista) {
        return this.publicacionesRevistaDAO.agregar(publicacionRevista);
    }

    @Override
    public boolean actualizar(PublicacionRevista publicacionRevista) {
        return this.publicacionesRevistaDAO.actualizar(publicacionRevista);
    }

    @Override
    public boolean eliminarPublicacionRevista(ObjectId idPublicacionRevista) {
        return this.publicacionesRevistaDAO.eliminar(idPublicacionRevista);
    }

    @Override
    public PublicacionRevista consultarPublicacionRevista(ObjectId idPublicacionRevista) {
        return this.publicacionesRevistaDAO.consultar(idPublicacionRevista);
    }

    @Override
    public List<PublicacionRevista> consultarTodosPublicacionRevista() {
        return this.publicacionesRevistaDAO.consultarTodos();
    }

    @Override
    public Proyecto consultarProyectoPublicacionRevista(ObjectId idPublicacionRevista) {
        return this.publicacionesRevistaDAO.consultarProyecto(idPublicacionRevista);
    }

    @Override
    public List<AutorDTO> consultarAutoresPublicacionRevista(ObjectId idPublicacionRevista) {
        return this.publicacionesRevistaDAO.consultarAutores(idPublicacionRevista);
    }
    
}
