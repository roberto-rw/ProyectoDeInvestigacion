/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
import entidades.Proyecto;
import entidades.Publicacion;
import implementacionesDAO.DAOSFactory;
import interfacesBO.IProyectoBO;
import interfacesDAO.IProyectoDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public class ProyectoBO implements IProyectoBO{
    
    IProyectoDAO proyectoDAO = DAOSFactory.crearProyectoDAO();

    @Override
    public boolean agregar(Proyecto proyecto) {
        return proyectoDAO.agregar(proyecto);
    }

    @Override
    public boolean actualizar(Proyecto proyecto) {
        return proyectoDAO.actualizar(proyecto);
    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {
        return proyectoDAO.eliminar(idProyecto);
    }

    @Override
    public List<Proyecto> consultarTodos() {
        return proyectoDAO.consultarTodos();
    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {
        return proyectoDAO.consultar(idProyecto);
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        return proyectoDAO.consultarLineasInvestigacion(idProyecto);
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        return proyectoDAO.consultarIntegrantes(idProyecto);
    }

    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        return proyectoDAO.actualizarIntegrantes(integrantes, id);
    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        return proyectoDAO.agregarPublicacion(idProyecto, publicacion);
    }

    

    
}
