/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dtos.ProfesorLineaInvestigacionDTO;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import interfacesDAO.IConexionBD;
import java.util.List;
import org.bson.types.ObjectId;
import interfacesDAO.IDoctoresDAO;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author jegav
 */
public class DoctoresDAO implements IDoctoresDAO {
    
    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public DoctoresDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.getConexion();
    }
    
    private MongoCollection<Doctor> getColeccion(){
        return baseDatos.getCollection("doctores", Doctor.class);
    }

    @Override
    public boolean agregar(Doctor doctor) {
        this.getColeccion().insertOne(doctor);
        return true;
    }

    @Override
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor) {
        baseDatos.getCollection("doctores", InvestigadorDoctor.class).insertOne(investigadorDoctor);
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idDoctor) {
        this.getColeccion().deleteOne(new Document("_id", idDoctor));
        return true;
    }

    @Override
    public Doctor consultar(ObjectId idDoctor) {
        List<Doctor> doctores = this.getColeccion().find(new Document("_id", idDoctor)).into(new ArrayList());
        if(doctores.isEmpty()){
            return null;
        }
        return doctores.get(0);
    }

    @Override
    public List<Doctor> consultarTodos() {
        return this.getColeccion().find().into(new ArrayList());
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idDoctor) {
        
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idDoctor)));
        etapas.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "lineasInvestigacion")
                        .append("localField", "idsLineasInvestigacion")
                        .append("foreignField", "_id")
                        .append("as", "lineasInvestigacion")
        ));
        etapas.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("lineasInvestigacion", "$lineasInvestigacion")
        ));
        List<ProfesorLineaInvestigacionDTO> lineasInvestigacion = baseDatos.getCollection("doctores", ProfesorLineaInvestigacionDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(lineasInvestigacion.isEmpty()){
            return null;
        }
        
        return lineasInvestigacion.get(0).getLineasInvestigacion();    
    }

    @Override
    public boolean actualizar(Doctor doctor) {
        Document filtro = new Document("_id", doctor.getId());
        Document cambios = new Document()
                .append("nombre", doctor.getNombre())
                .append("apellidoMaterno", doctor.getApellidoMaterno())
                .append("apellidoPaterno", doctor.getApellidoPaterno())
                .append("despacho", doctor.getDespacho())
                .append("telefono", doctor.getTelefono())
                .append("idsLineasInvestigacion", doctor.getIdsLineasInvestigacion());
        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        
        return true;
    }

    @Override
    public InvestigadorDoctor consultarInvestigador(ObjectId idInvestigador) {
        List<InvestigadorDoctor> doctores =  baseDatos.getCollection("doctores", InvestigadorDoctor.class).find(new Document()
                                                                                                .append("_id", idInvestigador)
                                                                                                .append("tipo", "InvestigadorDoctor")).into(new ArrayList());
        if(doctores.isEmpty()){
            return null;
        }
        return doctores.get(0);
    }

    @Override
    public List<InvestigadorDoctor> consultarTodosInvestigadores() {
        List<InvestigadorDoctor> doctores = new ArrayList();
        doctores = baseDatos.getCollection("doctores", InvestigadorDoctor.class).find(new Document()
                .append("tipo", "InvestigadorDoctor")).into(new ArrayList());
        return doctores;
    }

    
    
}
