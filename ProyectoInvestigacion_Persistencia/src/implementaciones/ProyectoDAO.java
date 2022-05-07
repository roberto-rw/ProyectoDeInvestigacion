
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
<<<<<<< HEAD
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dtos.ProfesorLineaInvestigacionDTO;
import entidades.DetalleProyectoProfesor;
import entidades.LineaInvestigacion;
=======
import entidades.LineaInvestigacion;
import entidades.Profesor;
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
import entidades.Proyecto;
import entidades.Publicacion;
import interfaces.IConexionBD;
import interfaces.IProyectoDAO;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
=======
import java.util.List;
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
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
<<<<<<< HEAD
    public boolean agregar(Proyecto proyecto) {        
=======
    public boolean agregar(Proyecto proyecto) {
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
        this.getCollection().insertOne(proyecto);
        return true;
    }

    @Override
    public boolean actualizar(Proyecto proyecto) {
<<<<<<< HEAD
        Document filtro = new Document("_id", proyecto.getId());
        Document cambios = new Document()
                .append("codigoReferencia", proyecto.getCodigoReferencia())
                .append("nombre", proyecto.getNombre())
                .append("acronimo", proyecto.getAcronimo())
                .append("idPrograma", proyecto.getIdPrograma())
                .append("patrocinador", proyecto.getPatrocinador())
                .append("fechaInicio", proyecto.getFechaInicio())
                .append("fechaFin", proyecto.getFechaFin());
        

        this.getCollection().updateOne(filtro, new Document("$set", cambios));
        
        return true;
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {
<<<<<<< HEAD
        this.getCollection().deleteOne(new Document("_id", idProyecto));
        return true;
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
    }

    @Override
    public List<Proyecto> consultarTodos() {
<<<<<<< HEAD
        return this.getCollection().find().into(new ArrayList());
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {
<<<<<<< HEAD
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


    @Override
    public boolean agregarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId idProyecto) {
          
        Bson filtro = Filters.eq(idProyecto);
        Bson updates = Updates.set("detalles", integrantes);
        this.getCollection().findOneAndUpdate(filtro, updates);
             
//        this.getCollection().updateOne(new Document("_id",idProyecto), new Document("$set", new Document("detalles", integrantes)));
        
        return true;
    }
    
    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        return true;
    }
    
    
    @Override
    public boolean eliminarIntegrantes(List<DetalleProyectoProfesor> integrantesEliminar, ObjectId idProyecto) {
        Proyecto proyecto = this.consultar(idProyecto);
        List<DetalleProyectoProfesor> listaIntegrantesProyecto = proyecto.getDetalles();
        List<DetalleProyectoProfesor> listaIntegrantesEliminar = integrantesEliminar;
        
        for (DetalleProyectoProfesor integrantes: listaIntegrantesProyecto) {
            
            for (DetalleProyectoProfesor integrantesE: listaIntegrantesEliminar) {
                
                    if(integrantesE.getIdProfesor().equals(integrantes.getIdProfesor())){
                        
                        listaIntegrantesProyecto.remove(integrantes);
                    }
            }
        }
        
        this.getCollection().updateOne(new Document("_id:",idProyecto), new Document("$set", new Document("detalles", listaIntegrantesProyecto)));
        
        return true;
    }
    
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean agregarProfesores(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
    @Override
    public boolean agregarPublicacion(Publicacion publicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD

    

    

    
=======
>>>>>>> a7b7a98 (Se añadió IProyectoDAO)
    
}
