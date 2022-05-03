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
public class PeriodoSupervision {
    private ObjectId idSupervisor; //Debe ser un Doctor
    private Date fechaInicio;
    private Date fechaFin;

    public PeriodoSupervision() {
    }

    public PeriodoSupervision(ObjectId idSupervisor, Date fechaInicio, Date fechaFin) {
        this.idSupervisor = idSupervisor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ObjectId getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(ObjectId idSupervisor) {
        this.idSupervisor = idSupervisor;
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
