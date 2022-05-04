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
public class Autor {
    
    private ObjectId idProfesor;
    private Integer orden;

    public Autor() {
    }

    public Autor(ObjectId idProfesor, Integer orden) {
        this.idProfesor = idProfesor;
        this.orden = orden;
    }

    public ObjectId getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(ObjectId idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    
    
}
