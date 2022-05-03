/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class DetalleProyectoProfesor {
    private ObjectId idProyecto;
    private ObjectId idProfesor;
    private Date fechaInicio;
    private Date fechaFin;

    public DetalleProyectoProfesor() {
    }

    public DetalleProyectoProfesor(ObjectId idProyecto, ObjectId idProfesor, Date fechaInicio, Date fechaFin) {
        this.idProyecto = idProyecto;
        this.idProfesor = idProfesor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ObjectId getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(ObjectId idProyecto) {
        this.idProyecto = idProyecto;
    }

    public ObjectId getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ObjectId idProfesor) {
        this.idProfesor = idProfesor;
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

    @Override
    public String toString() {
        return "DetalleProyectoProfesor{" + "idProyecto=" + idProyecto + ", idProfesor=" + idProfesor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
    
    
}
