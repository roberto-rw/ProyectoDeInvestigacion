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
import interfacesBO.IDoctoresBO;
import interfacesDAO.IDoctoresDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class DoctoresBO implements IDoctoresBO {

    IDoctoresDAO doctoresDAO = DAOSFactory.crearDoctoresDAO();
    
    @Override
    public boolean agregar(Doctor doctor) {
        return doctoresDAO.agregar(doctor);
    }

    @Override
    public boolean actualizar(Doctor doctor) {
        return doctoresDAO.actualizar(doctor);
    }

    @Override
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor) {
        return doctoresDAO.agregarInvestigadorDoctor(investigadorDoctor);
    }

    @Override
    public boolean eliminar(ObjectId idDoctor) {
        return doctoresDAO.eliminar(idDoctor);
    }

    @Override
    public Doctor consultar(ObjectId idDoctor) {
        return doctoresDAO.consultar(idDoctor);
    }

    @Override
    public List<Doctor> consultarTodos() {
        return doctoresDAO.consultarTodos();
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idDoctor) {
        return doctoresDAO.consultarLineasInvestigacion(idDoctor);
    }

    @Override
    public InvestigadorDoctor consultarInvestigador(ObjectId idInvestigador) {
        return doctoresDAO.consultarInvestigador(idInvestigador);
    }
    
}
