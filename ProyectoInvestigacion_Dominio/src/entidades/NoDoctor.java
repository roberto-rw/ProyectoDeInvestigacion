/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class NoDoctor extends Profesor {

    private List<PeriodoSupervision> supervisiones; //Supervisores que ha tenido el NoDoctor
    
    public NoDoctor() {
    }

    public NoDoctor(String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
    }

    public NoDoctor(ObjectId id, String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(id, nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
    }

    public List<PeriodoSupervision> getSupervisiones() {
        return supervisiones;
    }

    public void addSupervision(PeriodoSupervision supervision) {
         if(supervisiones == null){
             supervisiones = new ArrayList();
         }
         supervisiones.add(supervision);
    }

    public void setSupervisiones(List<PeriodoSupervision> supervisiones) {
        this.supervisiones = supervisiones;
    }
    
    

    
}
