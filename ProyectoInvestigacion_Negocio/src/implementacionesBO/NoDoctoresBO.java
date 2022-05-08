/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesBO;

import dtos.PeriodoSupervisionDTO;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import implementacionesDAO.DAOSFactory;
import interfacesBO.INoDoctoresBO;
import interfacesDAO.INoDoctoresDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class NoDoctoresBO implements INoDoctoresBO {

    INoDoctoresDAO noDoctoresDAO = DAOSFactory.crearNoDoctoresDAO();
    
    @Override
    public boolean agregar(NoDoctor noDoctor) {
        return noDoctoresDAO.agregar(noDoctor);
    }

    @Override
    public boolean actualizar(NoDoctor noDoctor) {
        return noDoctoresDAO.actualizar(noDoctor);
    }

    @Override
    public boolean agregarInvestigadorNoDoctor(InvestigadorNoDoctor investigadorNoDoctor) {
        return noDoctoresDAO.agregarInvestigadorNoDoctor(investigadorNoDoctor);
    }

    @Override
    public boolean eliminar(ObjectId idNoDoctor) {
        return noDoctoresDAO.eliminar(idNoDoctor);
    }

    @Override
    public NoDoctor consultar(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultar(idNoDoctor);
    }

    @Override
    public List<NoDoctor> consultarTodos() {
        return noDoctoresDAO.consultarTodos();
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultarLineasInvestigacion(idNoDoctor);
    }

    @Override
    public List<PeriodoSupervisionDTO> consultarSupervisiones(ObjectId idNoDoctor) {
        return noDoctoresDAO.consultarSupervisiones(idNoDoctor);
    }

    @Override
    public InvestigadorNoDoctor consultarInvestigador(ObjectId idInvestigador) {
        return noDoctoresDAO.consultarInvestigador(idInvestigador);
    }
    
}
