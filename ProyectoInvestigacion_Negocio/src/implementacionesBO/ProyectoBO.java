/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.Proyecto;
import entidades.Publicacion;
import implementacionesDAO.DAOSFactory;
import interfacesDAO.IProyectoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import interfacesBO.IProyectosBO;

/**
 *
 * @author pc
 */
public class ProyectoBO implements IProyectosBO{
    
    IProyectoDAO proyectoDAO = DAOSFactory.crearProyectoDAO();

    @Override
    public boolean agregar(Proyecto proyecto) {
        
        
        
        return proyectoDAO.agregar(proyecto);
    }

    @Override
    public boolean actualizar(Proyecto proyecto) {
        return proyectoDAO.actualizar(proyecto);
    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {
        return proyectoDAO.eliminar(idProyecto);
    }

    @Override
    public List<Proyecto> consultarTodos() {
        return proyectoDAO.consultarTodos();
    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {
        return proyectoDAO.consultar(idProyecto);
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        return proyectoDAO.consultarLineasInvestigacion(idProyecto);
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        return proyectoDAO.consultarIntegrantes(idProyecto);
    }

    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        return proyectoDAO.actualizarIntegrantes(integrantes, id);
    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        return proyectoDAO.agregarPublicacion(idProyecto, publicacion);
    }

    @Override
    public boolean estaRepetidoNombre(String nombre) {
        return this.consultarNombres().contains(nombre);
    }

    @Override
    public boolean estaRepetidoCodigo(String codigo) {
        return this.consultarCodigos().contains(codigo);
    }

    @Override
    public boolean estaRepetidoAcronimo(String acronimo) {
       return this.consultarAcronimos().contains(acronimo);
    }

    @Override
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin) {
        return proyectoDAO.consultarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        return proyectoDAO.consultarPorCaracteristicas(idPrograma, presupuesto, filtroPresupuesto, investigador, patrocinador);
    }

    @Override
    public List<String> consultarCodigos() {
        List<Proyecto> proyectos = proyectoDAO.consultarTodos();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getCodigoReferencia());
        });
        return resultado;
    }

    @Override
    public List<String> consultarNombres() {
        List<Proyecto> proyectos = proyectoDAO.consultarTodos();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getNombre());
        });
        return resultado;
    }

    @Override
    public List<String> consultarAcronimos() {
        List<Proyecto> proyectos = proyectoDAO.consultarTodos();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getAcronimo());
        });
        return resultado;
    }

    @Override
    public boolean validarFechasReales(Proyecto proyecto) {
        return !(proyecto.getFechaInicio().compareTo(proyecto.getFechaFin()) >= 0);
    }

    @Override
    public Proyecto consultarPorCodigo(String codigo) {
        return proyectoDAO.consultarPorCodigo(codigo);
    }

    @Override
    public Proyecto consultarPorNombre(String nombre) {
        return proyectoDAO.consultarPorNombre(nombre);
    }

    @Override
    public Proyecto consultarPorAcronimo(String acronimo) {
        return proyectoDAO.consultarPorAcronimo(acronimo);
    }



}
