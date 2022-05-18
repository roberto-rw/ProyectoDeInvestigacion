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
public class Publicacion {
    private ObjectId _id;
    private Long numeroSecuencia;
    private String titulo;
    private ObjectId idProyecto;
    private List<Autor> autores;

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public void setNumeroSecuencia(Long numeroSecuencia) {
        this.numeroSecuencia = numeroSecuencia;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdProyecto(ObjectId idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Publicacion() {
    }

    public Publicacion(Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        this.numeroSecuencia = numeroSecuencia;
        this.titulo = titulo;
        this.idProyecto = idProyecto;
    }

    public Publicacion(ObjectId _id, Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        this._id = _id;
        this.numeroSecuencia = numeroSecuencia;
        this.titulo = titulo;
        this.idProyecto = idProyecto;
    }

    public ObjectId getId() {
        return _id;
    }

    public Long getNumeroSecuencia() {
        return numeroSecuencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public ObjectId getIdProyecto() {
        return idProyecto;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void addAutor(Autor autor) {
        if(this.autores == null){
            this.autores = new ArrayList();
        }
        this.autores.add(autor);
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
        final Publicacion other = (Publicacion) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo;
    }
    
}
