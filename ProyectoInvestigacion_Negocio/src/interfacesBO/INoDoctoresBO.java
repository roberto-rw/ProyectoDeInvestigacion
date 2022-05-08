/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import dtos.PeriodoSupervisionDTO;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface INoDoctoresBO {
    public boolean agregar(NoDoctor noDoctor);
    public boolean actualizar(NoDoctor noDoctor);
    public boolean agregarInvestigadorNoDoctor(InvestigadorNoDoctor investigadorNoDoctor);
    public boolean eliminar(ObjectId idNoDoctor);
    public NoDoctor consultar(ObjectId idNoDoctor);
    public List<NoDoctor> consultarTodos();
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idNoDoctor);
    public List<PeriodoSupervisionDTO> consultarSupervisiones(ObjectId idNoDoctor);
    public InvestigadorNoDoctor consultarInvestigador(ObjectId idInvestigador);
}
