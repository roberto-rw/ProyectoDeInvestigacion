/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class PeriodoSupervisionDTO {
    private ObjectId idDoctor;
    private String nombreDoctor;
    private String telefonoDoctor;
    private Date inicioSupervision;
    private Date finSupervision;

    public ObjectId getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(ObjectId idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getTelefonoDoctor() {
        return telefonoDoctor;
    }

    public void setTelefonoDoctor(String telefonoDoctor) {
        this.telefonoDoctor = telefonoDoctor;
    }

    public Date getInicioSupervision() {
        return inicioSupervision;
    }

    public void setInicioSupervision(Date inicioSupervision) {
        this.inicioSupervision = inicioSupervision;
    }

    public Date getFinSupervision() {
        return finSupervision;
    }

    public void setFinSupervision(Date finSupervision) {
        this.finSupervision = finSupervision;
    }

    @Override
    public String toString() {
        return "idDoctor=" + idDoctor + ", nombreDoctor=" + nombreDoctor + ", telefonoDoctor=" + telefonoDoctor + ", inicioSupervision=" + inicioSupervision + ", finSupervision=" + finSupervision ;
    }
    
    
    
}
