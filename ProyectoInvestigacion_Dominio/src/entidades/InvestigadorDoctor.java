/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class InvestigadorDoctor extends Doctor {
    private String tipo;

    public InvestigadorDoctor() {
        this.tipo = "InvestigadorDoctor";
    }

    public InvestigadorDoctor(String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
        this.tipo = "InvestigadorDoctor";
    }

    public InvestigadorDoctor(ObjectId id, String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(id, nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
        this.tipo = "InvestigadorDoctor";
    }
    
    
}
