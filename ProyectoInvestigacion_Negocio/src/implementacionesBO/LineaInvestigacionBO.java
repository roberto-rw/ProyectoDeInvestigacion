/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.LineaInvestigacion;
import entidades.Profesor;
import entidades.Proyecto;
import implementacionesDAO.Persistencia;
import interfacesBO.ILineaInvestigacionBO;
import interfacesDAO.IPersistencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class LineaInvestigacionBO implements ILineaInvestigacionBO{
    
    IPersistencia persistencia = new Persistencia();

    @Override
    public boolean agregar(LineaInvestigacion lineaInvestigacion) {
        return persistencia.agregar(lineaInvestigacion);
    }

    @Override
    public boolean actualizar(LineaInvestigacion lineaInvestigacion) {
        return persistencia.actualizar(lineaInvestigacion);
    }

    @Override
    public boolean eliminar(ObjectId idLineaInvestigacion) throws Exception {
        if(!this.validarEliminarLineasProfesor(idLineaInvestigacion)){
            throw new Exception("No se puede eliminar una Linea de Investigación si la contiene un profesor");
        }
        if(!this.validarEliminarLineasProyecto(idLineaInvestigacion)){
            throw new Exception("No se puede eliminar una Linea de Investigación si la contiene un proyecto");
        }
        return persistencia.eliminarLineaInvestigacion(idLineaInvestigacion);
    }

    @Override
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion) {
        return persistencia.consultarLineaInvestigacion(idLineaInvestigacion);
    }

    @Override
    public List<LineaInvestigacion> consultarTodos() {
        return persistencia.consultarTodosLineaInvestigacion();
    }

    @Override
    public boolean validarEliminarLineasProfesor(ObjectId idLineaInvestigacion) {
        List<Profesor> profesores = new ArrayList();
        profesores.addAll(persistencia.consultarTodosDoctor());
        profesores.addAll(persistencia.consultarTodosNoDoctor());
        for(Profesor profesor: profesores){
            if(profesor.getIdsLineasInvestigacion().contains(idLineaInvestigacion)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validarEliminarLineasProyecto(ObjectId idLineaInvestigacion) {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        for(Proyecto proyecto: proyectos){
            if(proyecto.getIdsLineasInvestigacion().contains(idLineaInvestigacion)){
                return false;
            }
        }
        return true;
    }
    
}
