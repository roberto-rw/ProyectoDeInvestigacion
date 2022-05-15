/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.InvestigadorNoDoctor;
import entidades.NoDoctor;
import entidades.Profesor;
import implementacionesDAO.DAOSFactory;
import interfacesBO.IProfesoresBO;
import interfacesDAO.IDoctoresDAO;
import interfacesDAO.INoDoctoresDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class ProfesoresBO implements IProfesoresBO{
    private IDoctoresDAO doctoresDAO = DAOSFactory.crearDoctoresDAO();
    private INoDoctoresDAO noDoctoresDAO = DAOSFactory.crearNoDoctoresDAO();

    @Override
    public List<Profesor> consultarTodosProfesores() {
        List<Profesor> profesores = new ArrayList();
        profesores.addAll(doctoresDAO.consultarTodos());
        profesores.addAll(noDoctoresDAO.consultarTodos());
        return profesores;
    }

    @Override
    public boolean agregar(Profesor profesor) {
        
        
        if(profesor.getClass() == Doctor.class){
            Doctor doctor = (Doctor) profesor;
            return doctoresDAO.agregar(doctor);
        } else if(profesor.getClass() == InvestigadorDoctor.class){
            InvestigadorDoctor doctor = (InvestigadorDoctor) profesor;
            return doctoresDAO.agregarInvestigadorDoctor(doctor);
        } else if(profesor.getClass() == NoDoctor.class){
            NoDoctor noDoctor = (NoDoctor) profesor;
            return noDoctoresDAO.agregar(noDoctor);
        } else{
            InvestigadorNoDoctor noDoctor = (InvestigadorNoDoctor) profesor;
            return noDoctoresDAO.agregarInvestigadorNoDoctor(noDoctor);
        }
        
    }

    @Override
    public boolean eliminar(ObjectId idProfesor) {
        if(doctoresDAO.consultar(idProfesor) != null){
            return doctoresDAO.eliminar(idProfesor);
        } else{
            return noDoctoresDAO.eliminar(idProfesor);
        }
    }

    @Override
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultar(idNoDoctor);
    }

    @Override
    public Doctor consultarDoctor(ObjectId idDoctor) {
        return doctoresDAO.consultar(idDoctor);
    }

    @Override
    public boolean esInvestigador(ObjectId idProfesor) {
        if(doctoresDAO.consultarInvestigador(idProfesor) != null){
            return true;
        }
        if(noDoctoresDAO.consultarInvestigador(idProfesor) != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Doctor> consultarTodosDoctores() {
        return doctoresDAO.consultarTodos();
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores() {
        return doctoresDAO.consultarTodosInvestigadores();
    }

    @Override
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador) {
        return doctoresDAO.consultarInvestigador(idInvestigador);
    }

    @Override
    public Profesor consultar(ObjectId idProfesor) {
        Profesor profesor = doctoresDAO.consultar(idProfesor);
        if(profesor == null){
            return noDoctoresDAO.consultar(idProfesor);
        } 
        return profesor;
    }
}
