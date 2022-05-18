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
import entidades.PeriodoParticipacion;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public interface IProyectosBO {
    public boolean agregar(Proyecto proyecto) throws Exception;
    public boolean actualizar(Proyecto proyecto) throws Exception;
    public boolean eliminar(ObjectId idProyecto);
    public List<Proyecto> consultarTodos();
    public Proyecto consultar(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion);
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion);
    public boolean estaRepetidoNombre(String nombre);
    public boolean estaRepetidoCodigo(String codigo);
    public boolean estaRepetidoAcronimo(String acronimo);
    public boolean estaRepetidoTituloPublicacion(String titulo);
    public Proyecto consultarPorCodigo(String codigo);
    public Proyecto consultarPorNombre(String nombre);
    public Proyecto consultarPorAcronimo(String acronimo);
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin);
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador);
    public List<String> consultarCodigos();
    public List<String> consultarNombres();
    public List<String> consultarAcronimos();
    public List<Proyecto> consultarVigentes();
    public List<String> consultarTitulosPublicaciones();
    public boolean validarPeriodoFechas(Proyecto proyecto);
    public boolean validarPeriodoFechasIntegrante(PeriodoParticipacion periodo);
    public boolean validarNumeroIntegrantes(Proyecto proyecto);
    public boolean validarValorPresupuesto(Proyecto proyecto);
}
