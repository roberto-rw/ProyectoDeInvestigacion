/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidades.DetalleProyectoProfesor;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.Proyecto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
//        NoDoctoresDAO noDoctoresDAO = new NoDoctoresDAO(new ConexionBD());
//        InvestigadorNoDoctor investigadorNoDoctor = new InvestigadorNoDoctor("Antonio", "Inzunza", "Blight", "MSVMSDMSEJS", "6449438443");
//        /*noDoctor.addLineaInvestigacion(new ObjectId("62748de75051990edb1391b0"));
//        noDoctor.addLineaInvestigacion(new ObjectId("62748de75051990edb1391aa"));
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(2022, 8, 5);
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(2022, 8, 10);
//        
//        noDoctor.addSupervision(new PeriodoSupervision(new ObjectId("627558085051990edb139f91"), fechaInicio.getTime(), fechaFin.getTime()));
//        noDoctoresDAO.agregar(noDoctor);*/
//        noDoctoresDAO.eliminar(new ObjectId("62756cbf9ee9c727ca8bafb2"));
           
        DoctoresDAO doctoresDAO = new DoctoresDAO(new ConexionBD());
        InvestigadorDoctor doctor = doctoresDAO.consultarInvestigadorDoctor(new ObjectId("6275b031cbf6489305fe00c6"));

        Proyecto proyecto = new Proyecto("2", "ProyectoX", "x", 2f, new ObjectId("6275afd7cbf6489305fe00af"), "Oxxo", fechaInicio.getTime(), fechaFin.getTime(), "Descripcion", doctor);
        ProyectoDAO proyectoDAO = new ProyectoDAO(new ConexionBD());
        proyectoDAO.agregar(proyecto);
           
        ObjectId idProyecto = proyecto.getId();
        Doctor doctor1 = doctoresDAO.consultar(new ObjectId("6275bda4cbf6489305fe0396"));
        Doctor doctor2 = doctoresDAO.consultar(new ObjectId("6275bde1cbf6489305fe03ae"));
        Doctor doctor3 = doctoresDAO.consultar(new ObjectId("6275d698cbf6489305fe0945"));
        
        DetalleProyectoProfesor d1 = new DetalleProyectoProfesor(doctor1.getId(), fechaInicio.getTime(),fechaFin.getTime());
        DetalleProyectoProfesor d2 = new DetalleProyectoProfesor(doctor2.getId(), fechaInicio.getTime(),fechaFin.getTime());
        DetalleProyectoProfesor d3 = new DetalleProyectoProfesor(doctor3.getId(), fechaInicio.getTime(),fechaFin.getTime());
        
        List<DetalleProyectoProfesor> integrantesAgregar = new ArrayList();
        integrantesAgregar.add(d1);
        integrantesAgregar.add(d2);
        
        List<DetalleProyectoProfesor> integrantesAgregar2 = new ArrayList();
        integrantesAgregar2.add(d3);
        
//        proyectoDAO.agregarIntegrantes(integrantesAgregar, idProyecto);

        proyectoDAO.agregarIntegrantes(integrantesAgregar2, idProyecto);
        
        
        
        

    }
    
}
