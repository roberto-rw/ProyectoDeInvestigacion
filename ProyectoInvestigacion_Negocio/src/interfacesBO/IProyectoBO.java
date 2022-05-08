/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
import entidades.Proyecto;
import entidades.Publicacion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public interface IProyectoBO {
    public boolean agregar(Proyecto proyecto);
    public boolean actualizar(Proyecto proyecto);
    public boolean eliminar(ObjectId idProyecto);
    public List<Proyecto> consultarTodos();
    public Proyecto consultar(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id);
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion);
}