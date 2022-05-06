/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import dtos.PeriodoSupervisionDTO;
import entidades.InvestigadorNoDoctor;
import entidades.NoDoctor;
import entidades.PeriodoSupervision;
import java.util.Calendar;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NoDoctoresDAO noDoctoresDAO = new NoDoctoresDAO(new ConexionBD());
        InvestigadorNoDoctor investigadorNoDoctor = new InvestigadorNoDoctor("Antonio", "Inzunza", "Blight", "MSVMSDMSEJS", "6449438443");
        /*noDoctor.addLineaInvestigacion(new ObjectId("62748de75051990edb1391b0"));
        noDoctor.addLineaInvestigacion(new ObjectId("62748de75051990edb1391aa"));
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(2022, 8, 5);
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(2022, 8, 10);
        
        noDoctor.addSupervision(new PeriodoSupervision(new ObjectId("627558085051990edb139f91"), fechaInicio.getTime(), fechaFin.getTime()));
        noDoctoresDAO.agregar(noDoctor);*/
        noDoctoresDAO.eliminar(new ObjectId("62756cbf9ee9c727ca8bafb2"));
    }
    
}
