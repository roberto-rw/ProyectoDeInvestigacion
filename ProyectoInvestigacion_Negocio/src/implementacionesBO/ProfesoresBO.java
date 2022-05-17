/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Autor;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.InvestigadorNoDoctor;
import entidades.NoDoctor;
import entidades.PeriodoParticipacion;
import entidades.Profesor;
import entidades.Proyecto;
import entidades.Publicacion;
import implementacionesDAO.Persistencia;
import interfacesBO.IProfesoresBO;
import interfacesDAO.IPersistencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class ProfesoresBO implements IProfesoresBO{
    IPersistencia persistencia = new Persistencia();

    @Override
    public List<Profesor> consultarTodosProfesores() {
        List<Profesor> profesores = new ArrayList();
        profesores.addAll(persistencia.consultarTodosDoctor());
        profesores.addAll(persistencia.consultarTodosNoDoctor());
        return profesores;
    }

    @Override
    public boolean agregar(Profesor profesor) {
        
        
        if(profesor.getClass() == Doctor.class){
            Doctor doctor = (Doctor) profesor;
            return persistencia.agregar(doctor);
        } else if(profesor.getClass() == InvestigadorDoctor.class){
            InvestigadorDoctor doctor = (InvestigadorDoctor) profesor;
            return persistencia.agregarInvestigadorDoctor(doctor);
        } else if(profesor.getClass() == NoDoctor.class){
            NoDoctor noDoctor = (NoDoctor) profesor;
            return persistencia.agregar(noDoctor);
        } else{
            InvestigadorNoDoctor noDoctor = (InvestigadorNoDoctor) profesor;
            return persistencia.agregarInvestigadorNoDoctor(noDoctor);
        }
        
    }

    @Override
    public boolean eliminar(ObjectId idProfesor) throws Exception {
        
        if(!this.validarEliminarIntegrante(idProfesor)){
            throw new Exception("No se puede eliminar un Profesor si es integrante de un proyecto");
        }
        if(!this.validarEliminarAutores(idProfesor)){
            throw new Exception("No se puede eliminar un Profesor si es autor de una publicacion");
        }
        
        if(persistencia.consultarDoctor(idProfesor) != null){
            return persistencia.eliminarDoctor(idProfesor);
        } else{
            return persistencia.eliminarNoDoctor(idProfesor);
        }
    }

    @Override
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor) {
        return persistencia.consultarNoDoctor(idNoDoctor);
    }

    @Override
    public Doctor consultarDoctor(ObjectId idDoctor) {
        return persistencia.consultarDoctor(idDoctor);
    }

    @Override
    public boolean esInvestigador(ObjectId idProfesor) {
        if(persistencia.consultarInvestigadorDoctor(idProfesor) != null){
            return true;
        }
        if(persistencia.consultarInvestigadorNoDoctor(idProfesor) != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Doctor> consultarTodosDoctores() {
        return persistencia.consultarTodosDoctor();
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores() {
        return persistencia.consultarTodosInvestigadoresDoctor();
    }

    @Override
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador) {
        return persistencia.consultarInvestigadorDoctor(idInvestigador);
    }

    @Override
    public Profesor consultar(ObjectId idProfesor) {
        Profesor profesor = persistencia.consultarDoctor(idProfesor);
        if(profesor == null){
            return persistencia.consultarNoDoctor(idProfesor);
        } 
        return profesor;
    }

    @Override
    public boolean validarEliminarIntegrante(ObjectId idProfesor) {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        for(Proyecto proyecto: proyectos){
            List<PeriodoParticipacion> periodos = proyecto.getIntegrantes();
            for(PeriodoParticipacion periodo: periodos){
                if(periodo.getIdProfesor().equals(idProfesor)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean validarEliminarAutores(ObjectId idProfesor) {
        List<Proyecto> proyectos = persistencia.consultarTodosProyecto();
        for(Proyecto proyecto: proyectos){
            List<Publicacion> publicaciones = new ArrayList();
            publicaciones.addAll(proyecto.getPublicacionesCongreso());
            publicaciones.addAll(proyecto.getPublicacionesRevista());
            for(Publicacion publicacion: publicaciones){
                List<Autor> autores = publicacion.getAutores();
                for(Autor autor: autores){
                    if(autor.getIdProfesor().equals(idProfesor)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    
       
}

