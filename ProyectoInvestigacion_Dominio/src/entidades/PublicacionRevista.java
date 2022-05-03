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
public class PublicacionRevista extends Publicacion {
    private String nombreRevista;
    private String editorial;
    private String volumen;
    private String numero;
    private String pagInicio;
    private String pagFin;

    public PublicacionRevista() {
    }

    public PublicacionRevista(String nombreRevista, String editorial, String volumen, String numero, String pagInicio, String pagFin, Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        super(numeroSecuencia, titulo, idProyecto);
        this.nombreRevista = nombreRevista;
        this.editorial = editorial;
        this.volumen = volumen;
        this.numero = numero;
        this.pagInicio = pagInicio;
        this.pagFin = pagFin;
    }

    public PublicacionRevista(String nombreRevista, String editorial, String volumen, String numero, String pagInicio, String pagFin, ObjectId _id, Long numeroSecuencia, String titulo, ObjectId idProyecto) {
        super(_id, numeroSecuencia, titulo, idProyecto);
        this.nombreRevista = nombreRevista;
        this.editorial = editorial;
        this.volumen = volumen;
        this.numero = numero;
        this.pagInicio = pagInicio;
        this.pagFin = pagFin;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPagInicio() {
        return pagInicio;
    }

    public void setPagInicio(String pagInicio) {
        this.pagInicio = pagInicio;
    }

    public String getPagFin() {
        return pagFin;
    }

    public void setPagFin(String pagFin) {
        this.pagFin = pagFin;
    }
    
    
}
