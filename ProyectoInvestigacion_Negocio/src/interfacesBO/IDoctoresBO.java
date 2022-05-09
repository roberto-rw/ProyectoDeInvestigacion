/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IDoctoresBO {
    public boolean agregar(Doctor doctor);
    public boolean actualizar(Doctor doctor);
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor);
    public boolean eliminar(ObjectId idDoctor);
    public Doctor consultar(ObjectId idDoctor);
    public List<Doctor> consultarTodos();
    public List<InvestigadorDoctor> consultarTodosInvestigadores();
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idDoctor);
    public InvestigadorDoctor consultarInvestigador(ObjectId idInvestigador);
}
