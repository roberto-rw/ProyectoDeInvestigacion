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
 * @author pc
 */
public class PeriodoParticipacion {
    private ObjectId idProfesor; 
    private Date fechaInicio;
    private Date fechaFin;

    public PeriodoParticipacion() {
    }

    public PeriodoParticipacion(ObjectId idSupervisor, Date fechaInicio, Date fechaFin) {
        this.idProfesor = idSupervisor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    
}
