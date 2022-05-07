/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class Proyecto {
    private ObjectId _id;
    private String codigoReferencia;
    private String nombre;
    private String acronimo;
    private Float presupuestoTotal;
    private ObjectId idPrograma;
    private String patrocinador;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;
    private InvestigadorDoctor investigadorPrincipal;
    private List<ObjectId> idsLineasInvestigacion; //Representa ProyectoLineadeInvestigacion
    private List<DetalleProyectoProfesor> detalles;
    private List<Publicacion> publicaciones;

    

    public Proyecto() {
    }

    public Proyecto(ObjectId _id, String codigoReferencia, String nombre, String acronimo, Float presupuestoTotal, ObjectId idPrograma, String patrocinador, Date fechaInicio, Date fechaFin, String descripcion, InvestigadorDoctor investigadorPrincipal) {
        this._id = _id;
        this.codigoReferencia = codigoReferencia;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.presupuestoTotal = presupuestoTotal;
        this.idPrograma = idPrograma;
        this.patrocinador = patrocinador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.investigadorPrincipal = investigadorPrincipal;
    }

    public Proyecto(String codigoReferencia, String nombre, String acronimo, Float presupuestoTotal, ObjectId idPrograma, String patrocinador, Date fechaInicio, Date fechaFin, String descripcion, InvestigadorDoctor investigadorPrincipal) {
        this.codigoReferencia = codigoReferencia;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.presupuestoTotal = presupuestoTotal;
        this.idPrograma = idPrograma;
        this.patrocinador = patrocinador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.investigadorPrincipal = investigadorPrincipal;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public Float getPresupuestoTotal() {
        return presupuestoTotal;
    }

    public void setPresupuestoTotal(Float presupuestoTotal) {
        this.presupuestoTotal = presupuestoTotal;
    }

    public ObjectId getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(ObjectId idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InvestigadorDoctor getInvestigadorPrincipal() {
        return investigadorPrincipal;
    }

    public void setInvestigadorPrincipal(InvestigadorDoctor investigadorPrincipal) {
        this.investigadorPrincipal = investigadorPrincipal;
    }

    public List<ObjectId> getIdsLineasInvestigacion() {
        return idsLineasInvestigacion;
    }

    public void addLineaInvestigacion(ObjectId idLineaInvestigacion) {
        if(this.idsLineasInvestigacion == null){
            this.idsLineasInvestigacion = new ArrayList();
        }
        this.idsLineasInvestigacion.add(idLineaInvestigacion);
    }

    public void addDetalles(DetalleProyectoProfesor detalle) {
        if(this.detalles == null){
            this.detalles = new ArrayList();
        }
        this.detalles.add(detalle);
    }

    public List<DetalleProyectoProfesor> getDetalles() {
        return this.detalles;
    }

    public void setIdsLineasInvestigacion(List<ObjectId> idsLineasInvestigacion) {
        this.idsLineasInvestigacion = idsLineasInvestigacion;
    }

    public void setDetalles(List<DetalleProyectoProfesor> detalles) {
        this.detalles = detalles;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }


    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    
    
    
    
}
