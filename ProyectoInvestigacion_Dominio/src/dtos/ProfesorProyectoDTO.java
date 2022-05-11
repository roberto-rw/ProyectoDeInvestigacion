
package dtos;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * @author Tilín
 */
public class ProfesorProyectoDTO {
    private ObjectId idProfesor;
    private String nombreProfesor;
    private String apellidoPaternoProfesor;
    private Date fechaInicioParticipacion;
    private Date fechaFinParticipacion;

    public ObjectId getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ObjectId idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoPaternoProfesor() {
        return apellidoPaternoProfesor;
    }

    public void setApellidoPaternoProfesor(String apellidoPaternoProfesor) {
        this.apellidoPaternoProfesor = apellidoPaternoProfesor;
    }

    public Date getFechaInicioParticipacion() {
        return fechaInicioParticipacion;
    }

    public void setFechaInicioParticipacion(Date fechaInicioParticipacion) {
        this.fechaInicioParticipacion = fechaInicioParticipacion;
    }

    public Date getFechaFinParticipacion() {
        return fechaFinParticipacion;
    }

    public void setFechaFinParticipacion(Date fechaFinParticipacion) {
        this.fechaFinParticipacion = fechaFinParticipacion;
    }

    

    @Override
    public String toString() {
        return "ProfesorProyectoDTO{" + "idProfesor=" + idProfesor + ", nombreProfesor=" + nombreProfesor + ", apellidoPaternoProfesor=" + apellidoPaternoProfesor + ", fechaInicioParticipacion=" + fechaInicioParticipacion + ", fechaFinParticipacion=" + fechaFinParticipacion + '}';
    }
    
    
    
    
}
