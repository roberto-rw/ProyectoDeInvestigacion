/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dtos.PeriodoSupervisionDTO;
import dtos.ProfesorLineaInvestigacionDTO;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import interfacesDAO.IConexionBD;
import interfacesDAO.INoDoctoresDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class NoDoctoresDAO implements INoDoctoresDAO {
    
    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public NoDoctoresDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.getConexion();
    }
    
    private MongoCollection<NoDoctor> getColeccion(){
        return baseDatos.getCollection("noDoctores", NoDoctor.class);
    }

    @Override
    public boolean agregar(NoDoctor noDoctor) {
        this.getColeccion().insertOne(noDoctor);
        return true;
    }

    @Override
    public boolean actualizar(NoDoctor noDoctor) {
        Document filtro = new Document("_id", noDoctor.getId());
        Document cambios = new Document()
                .append("nombre", noDoctor.getNombre())
                .append("apellidoMaterno", noDoctor.getApellidoMaterno())
                .append("apellidoPaterno", noDoctor.getApellidoPaterno())
                .append("despacho", noDoctor.getDespacho())
                .append("telefono", noDoctor.getTelefono())
                .append("idsLineasInvestigacion", noDoctor.getIdsLineasInvestigacion())
                .append("supervisiones", noDoctor.getSupervisiones());
        

        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        
        return true;
    }

    @Override
    public boolean agregarInvestigadorNoDoctor(InvestigadorNoDoctor investigadorNoDoctor) {
        baseDatos.getCollection("noDoctores", InvestigadorNoDoctor.class).insertOne(investigadorNoDoctor);
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idNoDoctor) {
        this.getColeccion().deleteOne(new Document("_id", idNoDoctor));
        return true;
    }

    @Override
    public NoDoctor consultar(ObjectId idNoDoctor) {
        List<NoDoctor> noDoctores = this.getColeccion().find(new Document("_id", idNoDoctor)).into(new ArrayList());
        if(noDoctores.isEmpty()){
            return null;
        }
        return noDoctores.get(0);
    }

    @Override
    public List<NoDoctor> consultarTodos() {
        return this.getColeccion().find().into(new ArrayList());
    }

    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idNoDoctor) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idNoDoctor)));
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
        List<ProfesorLineaInvestigacionDTO> lineasInvestigacion = baseDatos.getCollection("noDoctores", ProfesorLineaInvestigacionDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(lineasInvestigacion.isEmpty()){
            return null;
        }
        
        return lineasInvestigacion.get(0).getLineasInvestigacion();  
    }

    @Override
    public List<PeriodoSupervisionDTO> consultarSupervisiones(ObjectId idNoDoctor) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idNoDoctor)));
        etapas.add(new Document("$unwind", new Document("path", "$supervisiones")));
        etapas.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "doctores")
                        .append("localField", "supervisiones.idSupervisor")
                        .append("foreignField", "_id")
                        .append("as", "periodosSupervisiones")
        ));
        etapas.add(new Document("$unwind", new Document("path", "$periodosSupervisiones")));
        etapas.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("idDoctor", "$periodosSupervisiones._id")
                .append("nombreDoctor", "$periodosSupervisiones.nombre")
                .append("telefonoDoctor", "$periodosSupervisiones.telefono")
                .append("inicioSupervision", "$supervisiones.fechaInicio")
                .append("finSupervision", "$supervisiones.fechaFin")
        ));
        
        List<PeriodoSupervisionDTO> supervisiones = baseDatos.getCollection("noDoctores", PeriodoSupervisionDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(supervisiones.isEmpty()){
            return null;
        }
        
        return supervisiones;
        
    }

    @Override
    public InvestigadorNoDoctor consultarInvestigador(ObjectId idInvestigador) {
        List<InvestigadorNoDoctor> noDoctores = baseDatos.getCollection("noDoctores", InvestigadorNoDoctor.class).find(new Document()
                                                                                                .append("_id", idInvestigador)
                                                                                                .append("tipo", "InvestigadorNoDoctor")).into(new ArrayList());
        if(noDoctores.isEmpty()){
            return null;
        }
        return noDoctores.get(0);
    }


    
}
