/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import dtos.ProfesorProyectoDTO;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.PeriodoParticipacion;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import implementacionesDAO.Persistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import interfacesBO.IProyectosBO;
import interfacesDAO.IPersistencia;

/**
 *
 * @author pc
 */
public class ProyectoBO implements IProyectosBO{
    
    IPersistencia persistencia = new Persistencia();

    @Override
    public boolean agregar(Proyecto proyecto) throws Exception {
        if(this.estaRepetidoCodigo(proyecto.getCodigoReferencia())){
            throw new Exception("No se permiten codigos repetidos");
        }
        if(this.estaRepetidoNombre(proyecto.getNombre())){
            throw new Exception("No se permiten nombres repetidos");
        }
        if(this.estaRepetidoAcronimo(proyecto.getAcronimo())){
            throw new Exception("No se permiten acronimos repetidos");
        }
        if(!this.validarPeriodoFechas(proyecto)){
            throw new Exception("El periodo de fechas no es válida");
        }
        if(!this.validarNumeroIntegrantes(proyecto)){
            throw new Exception("El Proyecto debe tener al menos 2 integrantes");
        }
        if(!this.validarValorPresupuesto(proyecto)){
            throw new Exception("El Presupuesto del proyecto debe ser un número positivo");
        }
        
        return persistencia.agregar(proyecto);
    }

    @Override
    public boolean actualizar(Proyecto proyecto) throws Exception {
        if(this.consultarPorCodigo(proyecto.getCodigoReferencia()) != null){
            if(!proyecto.equals(this.consultarPorCodigo(proyecto.getCodigoReferencia()))){ //Si el código se repite pero no es el mismo que ya se tiene, se lanza una excepción
                throw new Exception("No se permiten codigos repetidos");
            }
        }
        if(this.consultarPorNombre(proyecto.getNombre()) != null){
            if(!proyecto.equals(this.consultarPorNombre(proyecto.getNombre()))){
                throw new Exception("No se permiten nombres repetidos");
            }
        }
       if(this.consultarPorAcronimo(proyecto.getAcronimo()) != null){
            if(!proyecto.equals(this.consultarPorAcronimo(proyecto.getAcronimo()))){
                throw new Exception("No se permiten acronimos repetidos");
            }
        }
        if(!this.validarPeriodoFechas(proyecto)){
            throw new Exception("Las fechas no son reales");
        }
        if(!this.validarNumeroIntegrantes(proyecto)){
            throw new Exception("El Proyecto debe tener al menos 2 integrantes");
        }
        if(!this.validarValorPresupuesto(proyecto)){
            throw new Exception("El Presupuesto del proyecto debe ser un número positivo");
        }
        return persistencia.actualizar(proyecto);
    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {
        return persistencia.eliminarProyecto(idProyecto);
    }

    @Override
    public List<Proyecto> consultarTodos() {
        return persistencia.consultarTodosProyecto();
    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {
        return persistencia.consultarProyecto(idProyecto);
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        return persistencia.consultarLineasInvestigacionProyecto(idProyecto);
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        return persistencia.consultarIntegrantes(idProyecto);
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
    public boolean estaRepetidoTituloPublicacion(String titulo) {
        return this.consultarTitulosPublicaciones().contains(titulo);
    }
    
    @Override
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin) {
        return persistencia.consultarProyectoPorFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        return persistencia.consultarPorCaracteristicas(idPrograma, presupuesto, filtroPresupuesto, investigador, patrocinador);
    }

    @Override
    public List<String> consultarCodigos() {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getCodigoReferencia());
        });
        return resultado;
    }

    @Override
    public List<String> consultarNombres() {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getNombre());
        });
        return resultado;
    }

    @Override
    public List<String> consultarAcronimos() {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        List<String> resultado = new ArrayList();
        proyectos.forEach(proyecto ->{
            resultado.add(proyecto.getAcronimo());
        });
        return resultado;
    }
    
    @Override
    public List<String> consultarTitulosPublicaciones() {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        List<PublicacionCongreso> publicacionesCongreso;
        List<PublicacionRevista> publicacionesRevista;
        List<String> titulos = new ArrayList();
        for (Proyecto p: proyectos) {
            publicacionesCongreso = p.getPublicacionesCongreso();
            if(publicacionesCongreso != null){
                for (PublicacionCongreso pC: publicacionesCongreso) {
                    titulos.add(pC.getTitulo());
                    System.out.println(pC.getTitulo());
                }
            }
            publicacionesRevista = p.getPublicacionesRevista();
            if(publicacionesRevista != null){
                for (PublicacionRevista pR: publicacionesRevista) {
                    titulos.add(pR.getTitulo());
                    System.out.println(pR.getTitulo());
                }
            }
            
            
        }
        return titulos;
    }
    
    @Override
    public boolean validarPeriodoFechas(Proyecto proyecto) {
        return !(proyecto.getFechaInicio().compareTo(proyecto.getFechaFin()) >= 0);
    }

    @Override
    public Proyecto consultarPorCodigo(String codigo) {
        return persistencia.consultarProyectoPorCodigo(codigo);
    }

    @Override
    public Proyecto consultarPorNombre(String nombre) {
        return persistencia.consultarProyectoPorNombre(nombre);
    }

    @Override
    public Proyecto consultarPorAcronimo(String acronimo) {
        return persistencia.consultarProyectoPorAcronimo(acronimo);
    }

    @Override
    public boolean validarPeriodoFechasIntegrante(PeriodoParticipacion periodo) {
        return !(periodo.getFechaInicio().compareTo(periodo.getFechaFin()) >= 0);
    } 
    
    public List<Proyecto> consultarVigentes() {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        List<Proyecto> proyectosVigentes = new ArrayList();
        
        Date fechaActual = new Date();
        
        for (Proyecto p: proyectos) {
            
            if(p.getFechaFin().compareTo(fechaActual)>=0){
                proyectosVigentes.add(p);
            }
        }
        
        return proyectosVigentes;
    }
        
    @Override
    public boolean agregarPublicacionCongreso(ObjectId idProyecto, PublicacionCongreso publicacion) {
        return persistencia.agregarPublicacionCongreso(idProyecto, publicacion);
    }

    @Override
    public boolean agregarPublicacionRevista(ObjectId idProyecto, PublicacionRevista publicacion) {
        return persistencia.agregarPublicacionRevista(idProyecto, publicacion);
    }

    @Override
    public boolean validarNumeroIntegrantes(Proyecto proyecto) {
        return proyecto.getIntegrantes().size() >= 2;
    }

    @Override
    public boolean validarValorPresupuesto(Proyecto proyecto) {
        return proyecto.getPresupuestoTotal() > 0;
    }


    
    
    
}
