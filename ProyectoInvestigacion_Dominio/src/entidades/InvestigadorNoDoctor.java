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
public class InvestigadorNoDoctor extends NoDoctor { //Va dentro de la colección NoDoctor
    
    private String tipo;

    public InvestigadorNoDoctor() {
        this.tipo = "InvestigadorNoDoctor";
    }

    public InvestigadorNoDoctor(String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
        this.tipo = "InvestigadorNoDoctor";
    }

    public InvestigadorNoDoctor(ObjectId id, String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        super(id, nombre, apellidoMaterno, apellidoPaterno, despacho, telefono);
        this.tipo = "InvestigadorNoDoctor";
    }

    public String getTipo() {
        return tipo;
    }
    
    
}
