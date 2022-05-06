/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.Profesor;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IDoctoresDAO {
    public boolean agregar(Doctor doctor);
    public boolean actualizar(Doctor doctor);
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor);
    public boolean eliminar(ObjectId idDoctor);
    public Doctor consultar(ObjectId idDoctor);
    public List<Doctor> consultarTodos();
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idDoctor);
}
