/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class Profesor {
    private ObjectId _id;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String despacho;
    private String telefono;
    private List<ObjectId> idsProyectos;
    private List<ObjectId> idsLineasInvestigacion; //Representa ProfesorInvestigacion

    public Profesor() {
    }

    public Profesor(String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.despacho = despacho;
        this.telefono = telefono;
    }

    public Profesor(ObjectId id, String nombre, String apellidoMaterno, String apellidoPaterno, String despacho, String telefono) {
        this._id = id;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.despacho = despacho;
        this.telefono = telefono;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<ObjectId> getIdsProyectos() {
        return idsProyectos;
    }

    public void addProyecto(ObjectId idProyecto) {
        if(idsProyectos == null){
            idsProyectos = new ArrayList();
        }
        idsProyectos.add(idProyecto);
    }

    public List<ObjectId> getIdsLineasInvestigacion() {
        return idsLineasInvestigacion;
    }

    public void addLineaInvestigacion(ObjectId idLineaInvestigacion) {
        if(this.idsLineasInvestigacion == null){
            this.idsLineasInvestigacion = new ArrayList();
        }
        this.idsLineasInvestigacion.add(idLineaInvestigacion);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
    
}
