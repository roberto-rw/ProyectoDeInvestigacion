/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.Proyecto;
import entidades.Publicacion;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public interface IProyectoBO {
    public boolean agregar(Proyecto proyecto);
    public boolean actualizar(Proyecto proyecto);
    public boolean eliminar(ObjectId idProyecto);
    public List<Proyecto> consultarTodos();
    public Proyecto consultar(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion);
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
    public boolean validarFechasReales(Proyecto proyecto);
}
