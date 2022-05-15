/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.NoDoctor;
import entidades.Profesor;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IProfesoresBO {
    public List<Profesor> consultarTodosProfesores(); 
    public List<Doctor> consultarTodosDoctores();
    public List<InvestigadorDoctor> consultarTodosInvestigadorDoctores(); 
    public boolean agregar(Profesor profesor);
    public boolean eliminar(ObjectId idProfesor);
    public Profesor consultar(ObjectId idProfesor);
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor);
    public Doctor consultarDoctor(ObjectId idDoctor);
    public boolean esInvestigador(ObjectId idProfesor);
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador);
    
    
}
