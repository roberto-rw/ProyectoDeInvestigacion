/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private List<PeriodoParticipacion> integrantes;
    private List<PublicacionRevista> publicacionesRevista;
    private List<PublicacionCongreso> publicacionesCongreso;

    

    public Proyecto() {
    }

    public Proyecto(ObjectId _id, String codigoReferencia, String nombre, String acronimo, Float presupuestoTotal, ObjectId idPrograma, String patrocinador, Date fechaInicio, Date fechaFin, String descripcion, InvestigadorDoctor investigadorPrincipal, List<PeriodoParticipacion> detalles, List<ObjectId> idsLineasInvestigacion) {
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
        this.integrantes = detalles;
        this.idsLineasInvestigacion = idsLineasInvestigacion;
    }

    public Proyecto(String codigoReferencia, String nombre, String acronimo, Float presupuestoTotal, ObjectId idPrograma, String patrocinador, Date fechaInicio, Date fechaFin, String descripcion, InvestigadorDoctor investigadorPrincipal, List<PeriodoParticipacion> detalles, List<ObjectId> idsLineasInvestigacion ) {
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
        this.integrantes = detalles;
        this.idsLineasInvestigacion = idsLineasInvestigacion;   
    }

    public Proyecto(ObjectId _id) {
        this._id = _id;
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


    public void setIdsLineasInvestigacion(List<ObjectId> idsLineasInvestigacion) {
        this.idsLineasInvestigacion = idsLineasInvestigacion;
    }

    public List<PublicacionRevista> getPublicacionesRevista() {
        return publicacionesRevista;
    }

    public void addPublicacionRevista(PublicacionRevista publicacionesRevista) {
        if(this.publicacionesRevista == null){
            this.publicacionesRevista = new ArrayList();
        }
        this.publicacionesRevista.add(publicacionesRevista);
    }

    public List<PublicacionCongreso> getPublicacionesCongreso() {
        return publicacionesCongreso;
    }

    public void setPublicacionesCongreso(List<PublicacionCongreso> publicacionesCongreso) {
        this.publicacionesCongreso = publicacionesCongreso;
    }

    public List<PeriodoParticipacion> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<PeriodoParticipacion> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this._id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proyecto other = (Proyecto) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "codigoReferencia=" + codigoReferencia + ", nombre=" + nombre + ", acronimo=" + acronimo + ", presupuestoTotal=" + presupuestoTotal + ", idPrograma=" + idPrograma + ", patrocinador=" + patrocinador + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", descripcion=" + descripcion + ", investigadorPrincipal=" + investigadorPrincipal + ", idsLineasInvestigacion=" + idsLineasInvestigacion + ", integrantes=" + integrantes + ", publicacionesRevista=" + publicacionesRevista + ", publicacionesCongreso=" + publicacionesCongreso + '}';
    }
    
}
