/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class AutorDTO {
    private ObjectId idAutor;
    private String nombreAutor;
    private String apellidoPaternoAutor;
    private String telefonoAutor;
    private Integer orden;

    public ObjectId getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(ObjectId idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoPaternoAutor() {
        return apellidoPaternoAutor;
    }

    public void setApellidoPaternoAutor(String apellidoPaternoAutor) {
        this.apellidoPaternoAutor = apellidoPaternoAutor;
    }

    public String getTelefonoAutor() {
        return telefonoAutor;
    }

    public void setTelefonoAutor(String telefonoAutor) {
        this.telefonoAutor = telefonoAutor;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}


