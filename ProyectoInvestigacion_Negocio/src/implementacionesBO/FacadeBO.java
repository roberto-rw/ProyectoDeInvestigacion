/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

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
import interfacesBO.IFacadeBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.IProfesoresBO;
import interfacesBO.IProgramasBO;
import interfacesBO.IProyectosBO;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class FacadeBO implements IFacadeBO{
    ILineaInvestigacionBO lineasInvestigacionBO;
    IProgramasBO programasBO;
    IProyectosBO proyectosBO;
    IProfesoresBO profesoresBO;
    
    
    public FacadeBO(){
        
        lineasInvestigacionBO = BOSFactory.crearLineaInvestigacionBO();
        programasBO = BOSFactory.crearProgramaBO();
        proyectosBO = BOSFactory.crearProyectoBO();
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
    public boolean eliminarProfesor(ObjectId idProfesor) throws Exception {
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
    public Profesor consultarProfesor(ObjectId idProfesor) {
        return profesoresBO.consultar(idProfesor);
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
    public boolean agregarProyecto(Proyecto proyecto) throws Exception{
        return proyectosBO.agregar(proyecto);
    }

    @Override
    public boolean actualizarProyecto(Proyecto proyecto) throws Exception{
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

//    @Override
//    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
//        return proyectosBO.actualizarIntegrantes(integrantes, id);
//    }

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
    
    @Override
    public List<Proyecto> consultarVigentes() {
        return proyectosBO.consultarVigentes();
    }
    @Override
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion) {
        return proyectosBO.agregarPublicacionCongreso(idProyecto, publicacion);
    }

    @Override
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion) {
        return proyectosBO.agregarPublicacionRevista(idProyecto, publicacion);
    }
    
    
    @Override
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador) {
        return profesoresBO.consultarInvestigadorDoctor(idInvestigador);
    }

    @Override
    public boolean validarFechasRealesIntegrante(PeriodoParticipacion periodo) {
        return proyectosBO.validarFechasRealesIntegrante(periodo);
    }
}
