/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import enums.TipoPublicacionCongreso;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class PublicacionCongreso extends Publicacion {
    private String nombreCongreso;
    private TipoPublicacionCongreso tipo;
    private Date fechaInicio;
    private Date fechaFin;
    private String lugarCelebracion;
    private String pais;
    private String editorial;
    private String tipoPublicacion;

    public PublicacionCongreso() {
    }

    public PublicacionCongreso(String nombreCongreso, TipoPublicacionCongreso tipo, Date fechaInicio, Date fechaFin, String lugarCelebracion, String pais, String editorial, Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        super(numeroSecuencia, titulo, idProyecto);
        this.nombreCongreso = nombreCongreso;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugarCelebracion = lugarCelebracion;
        this.pais = pais;
        this.editorial = editorial;
        this.tipoPublicacion = "Congreso";
    }

    public PublicacionCongreso(String nombreCongreso, TipoPublicacionCongreso tipo, Date fechaInicio, Date fechaFin, String lugarCelebracion, String pais, String editorial, ObjectId _id, Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        super(_id, numeroSecuencia, titulo, idProyecto);
        this.nombreCongreso = nombreCongreso;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugarCelebracion = lugarCelebracion;
        this.pais = pais;
        this.editorial = editorial;
        this.tipoPublicacion = "Congreso";
    }

    public String getNombreCongreso() {
        return nombreCongreso;
    }

    public void setNombreCongreso(String nombreCongreso) {
        this.nombreCongreso = nombreCongreso;
    }

    public TipoPublicacionCongreso getTipo() {
        return tipo;
    }

    public void setTipo(TipoPublicacionCongreso tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getLugarCelebracion() {
        return lugarCelebracion;
    }

    public void setLugarCelebracion(String lugarCelebracion) {
        this.lugarCelebracion = lugarCelebracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    
    
}
