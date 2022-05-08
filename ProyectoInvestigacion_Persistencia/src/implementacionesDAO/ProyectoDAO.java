
package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dtos.ProfesorLineaInvestigacionDTO;
import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
import entidades.LineaInvestigacion;
import entidades.Profesor;
import entidades.Proyecto;
import entidades.Publicacion;
import interfacesDAO.IConexionBD;
import interfacesDAO.IProyectoDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.List;
import org.bson.types.ObjectId;


public class ProyectoDAO implements IProyectoDAO{

    private IConexionBD conexion;
    private MongoDatabase baseDatos;
    
    public ProyectoDAO(IConexionBD conexion){
        this.conexion = conexion;
        this.baseDatos = this.conexion.crearConexion();
    }
    
    private MongoCollection<Proyecto> getCollection(){
        return baseDatos.getCollection("proyectos", Proyecto.class);
    }
    
    @Override      
    public boolean agregar(Proyecto proyecto) {

        this.getCollection().insertOne(proyecto);
        return true;
    }

    @Override
    public boolean actualizar(Proyecto proyecto) {

        Document filtro = new Document("_id", proyecto.getId());
        Document cambios = new Document()
                .append("codigoReferencia", proyecto.getCodigoReferencia())
                .append("nombre", proyecto.getNombre())
                .append("acronimo", proyecto.getAcronimo())
                .append("idPrograma", proyecto.getIdPrograma())
                .append("patrocinador", proyecto.getPatrocinador())
                .append("fechaInicio", proyecto.getFechaInicio())
                .append("fechaFin", proyecto.getFechaFin())
                .append("publicaciones", proyecto.getPublicaciones());
        

        this.getCollection().updateOne(filtro, new Document("$set", cambios));
        
        return true;

    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {

        this.getCollection().deleteOne(new Document("_id", idProyecto));
        return true;

    }

    @Override
    public List<Proyecto> consultarTodos() {

        return this.getCollection().find().into(new ArrayList());

    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {

        List<Proyecto> proyectos = this.getCollection().find(new Document("_id", idProyecto)).into(new ArrayList());
        if(proyectos.isEmpty()){
            return null;
        }
        return proyectos.get(0);
    }
    
    @Override
    public List<LineaInvestigacion> consultarLineasInvestigacion(ObjectId idProyecto) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idProyecto)));
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
        List<ProfesorLineaInvestigacionDTO> lineasInvestigacion = baseDatos.getCollection("proyectos", ProfesorLineaInvestigacionDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(lineasInvestigacion.isEmpty()){
            return null;
        }
        
        return lineasInvestigacion.get(0).getLineasInvestigacion(); 
    }


//    @Override
//    public boolean agregarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId idProyecto) {
//          
////        Bson filtro = Filters.eq(idProyecto);
////        Bson updates = Updates.set("detalles", integrantes);
////        this.getCollection().findOneAndUpdate(filtro, updates);
//             
//        this.getCollection().updateOne(new Document("_id",idProyecto), new Document("$set", new Document("detalles", integrantes)));
//        
//        return true;
//    }
    
    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        this.getCollection().updateOne(new Document("_id",id), new Document("$set", new Document("detalles", integrantes)));
        return true;
    }
    
    
//    @Override
//    public boolean eliminarIntegrantes(List<DetalleProyectoProfesor> integrantesEliminar, ObjectId idProyecto) {
//        Proyecto proyecto = this.consultar(idProyecto);
//        List<DetalleProyectoProfesor> listaIntegrantesProyecto = proyecto.getDetalles();
//        List<DetalleProyectoProfesor> listaIntegrantesEliminar = integrantesEliminar;
//        
//        for (DetalleProyectoProfesor integrantes: listaIntegrantesProyecto) {
//            
//            for (DetalleProyectoProfesor integrantesE: listaIntegrantesEliminar) {
//                
//                    if(integrantesE.getIdProfesor().equals(integrantes.getIdProfesor())){
//                        
//                        listaIntegrantesProyecto.remove(integrantes);
//                    }
//            }
//        }
//        
//        this.getCollection().updateOne(new Document("_id:",idProyecto), new Document("$set", new Document("detalles", listaIntegrantesProyecto)));
//        
//        return true;
//    }

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
        Proyecto proyecto = this.consultar(idProyecto);
        if(proyecto != null){
            proyecto.getPublicaciones().add(publicacion);
            this.actualizar(proyecto);
            return true;
        }
        return false;
    }

    @Override
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto) {
        List<Document> etapasDoctor = new ArrayList();
        etapasDoctor.add(new Document("$match", new Document("_id", idProyecto)));
        etapasDoctor.add(new Document("$unwind", new Document("path", "$detalles")));
        etapasDoctor.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "doctores")
                        .append("localField", "detalles.idProfesor")
                        .append("foreignField", "_id")
                        .append("as", "integrantes")
        ));
        etapasDoctor.add(new Document("$unwind", new Document("path", "$integrantes")));
        etapasDoctor.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("idProfesor", "$integrantes._id")
                .append("nombreProfesor", "$integrantes.nombre"))
                .append("inicioSupervision", "detalles.fechaInicio")
                .append("finSupervision", "$detalles.fechaFin")
        );
        List<ProfesorProyectoDTO> detallesDoctor = baseDatos.getCollection("noDoctores", ProfesorProyectoDTO.class).aggregate(etapasDoctor).into(new ArrayList());
        
        List<Document> etapasNoDoctor = new ArrayList();
        etapasNoDoctor.add(new Document("$match", new Document("_id", idProyecto)));
        etapasNoDoctor.add(new Document("$unwind", new Document("path", "$detalles")));
        etapasNoDoctor.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "noDoctores")
                        .append("localField", "detalles.idProfesor")
                        .append("foreignField", "_id")
                        .append("as", "integrantes")
        ));
        etapasNoDoctor.add(new Document("$unwind", new Document("path", "$integrantes")));
        etapasNoDoctor.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("idProfesor", "$integrantes._id")
                .append("nombreProfesor", "$integrantes.nombre"))
                .append("inicioSupervision", "detalles.fechaInicio")
                .append("finSupervision", "$detalles.fechaFin")
        );
        List<ProfesorProyectoDTO> detallesNoDoctor = baseDatos.getCollection("noDoctores", ProfesorProyectoDTO.class).aggregate(etapasDoctor).into(new ArrayList());
        List<ProfesorProyectoDTO> detalles = new ArrayList();
        detalles.addAll(detallesDoctor);
        detalles.addAll(detallesNoDoctor);
        return detalles;
    }


}
