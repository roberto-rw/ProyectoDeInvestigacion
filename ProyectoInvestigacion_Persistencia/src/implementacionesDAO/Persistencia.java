/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

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
    
    public Persistencia(){
        doctoresDAO = DAOSFactory.crearDoctoresDAO();
        noDoctoresDAO = DAOSFactory.crearNoDoctoresDAO();
        lineasInvestigacionDAO = DAOSFactory.crearLineaInvestigacionDAO();
        programasDAO = DAOSFactory.crearProgramasDAO();
        proyectoDAO = DAOSFactory.crearProyectoDAO();
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

    @Override
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion) {
        return proyectoDAO.agregarPublicacionCongreso(idProyecto, publicacion);
    }

    @Override
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion) {
        return proyectoDAO.agregarPublicacionRevista(idProyecto, publicacion);
    }

}
