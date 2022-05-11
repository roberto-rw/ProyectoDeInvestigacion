/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class LineaInvestigacion {
    private ObjectId _id;
    private String codigo;
    private String nombre;
    private List<String> conjuntoDescriptores;

    public LineaInvestigacion() {
    }

    public LineaInvestigacion(String codigo, String nombre, List<String> conjuntoDescriptores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.conjuntoDescriptores = conjuntoDescriptores;
    }

    public LineaInvestigacion(ObjectId _id, String codigo, String nombre, List<String> conjuntoDescriptores) {
        this._id = _id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.conjuntoDescriptores = conjuntoDescriptores;
    }

    public LineaInvestigacion(ObjectId _id) {
        this._id = _id;
    }
    
    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getConjuntoDescriptores() {
        return conjuntoDescriptores;
    }

    public void setConjuntoDescriptores(List<String> conjuntoDescriptores) {
        this.conjuntoDescriptores = conjuntoDescriptores;
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
        final LineaInvestigacion other = (LineaInvestigacion) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineaInvestigacion{" + "_id=" + _id + ", codigo=" + codigo + ", nombre=" + nombre + ", conjuntoDescriptores=" + conjuntoDescriptores + '}';
    }

}
