/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import implementacionesDAO.DAOSFactory;
import implementacionesDAO.Persistencia;
import interfacesBO.IDoctoresBO;
import interfacesDAO.IDoctoresDAO;
import interfacesDAO.IPersistencia;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class DoctoresBO implements IDoctoresBO {

    IPersistencia persistencia = new Persistencia();
    
    @Override
    public boolean agregar(Doctor doctor) {
        return persistencia.agregar(doctor);
    }

    @Override
    public boolean actualizar(Doctor doctor) {
        return persistencia.actualizar(doctor);
    }

    @Override
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor) {
        return persistencia.agregarInvestigadorDoctor(investigadorDoctor);
    }

    @Override
    public boolean eliminar(ObjectId idDoctor) {
        return persistencia.eliminarDoctor(idDoctor);
    }

    @Override
    public Doctor consultar(ObjectId idDoctor) {
        return persistencia.consultarDoctor(idDoctor);
    }

    @Override
    public List<Doctor> consultarTodos() {
        return persistencia.consultarTodosDoctor();
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idDoctor) {
        return persistencia.consultarLineasInvestigacionDoctor(idDoctor);
    }

    @Override
    public InvestigadorDoctor consultarInvestigador(ObjectId idInvestigador) {
        return persistencia.consultarInvestigadorDoctor(idInvestigador);
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadores() {
        return persistencia.consultarTodosInvestigadoresDoctor();
    }
    
}
