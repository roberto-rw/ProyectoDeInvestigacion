/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dtos.AutorDTO;
import dtos.PublicacionProyectoDTO;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import interfacesDAO.IConexionBD;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import interfacesDAO.IPublicacionesCongresoDAO;

/**
 *
 * @author jegav
 */
public class PublicacionesCongresoDAO implements IPublicacionesCongresoDAO{
    
    
    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public PublicacionesCongresoDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.getConexion();
    }
    
    private MongoCollection<PublicacionCongreso> getColeccion(){
        return baseDatos.getCollection("publicacionesCongreso", PublicacionCongreso.class);
    }

    @Override
    public boolean agregar(PublicacionCongreso publicacionCongreso) {
       this.getColeccion().insertOne(publicacionCongreso);
        return true;
    }

    @Override
    public boolean actualizar(PublicacionCongreso publicacionCongreso) {
        Document filtro = new Document("_id", publicacionCongreso.getId());
        Document cambios = new Document()
                .append("numeroSecuencia", publicacionCongreso.getNumeroSecuencia())
                .append("titulo", publicacionCongreso.getTitulo())
                .append("idProyecto", publicacionCongreso.getIdProyecto())
                .append("autores", publicacionCongreso.getAutores())
                .append("nombreCongreso", publicacionCongreso.getNombreCongreso())
                .append("fechaInicio", publicacionCongreso.getFechaInicio())
                .append("fechaFin", publicacionCongreso.getFechaFin())
                .append("lugarCelebracion", publicacionCongreso.getLugarCelebracion())
                .append("pais", publicacionCongreso.getPais())
                .append("editorial", publicacionCongreso.getEditorial());
        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idPublicacionCongreso) {
        this.getColeccion().deleteOne(new Document("_id", idPublicacionCongreso));
        return true;
    }

    @Override
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso) {
        List<PublicacionCongreso> publicacionCongreso = this.getColeccion().find(new Document("_id", idPublicacionCongreso)).into(new ArrayList());
        if(publicacionCongreso.isEmpty()){
            return null;
        }
        return publicacionCongreso.get(0);
    }

    @Override
    public List<PublicacionCongreso> consultarTodos() {
        return this.getColeccion().find().into(new ArrayList());
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idPublicacionCongreso) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idPublicacionCongreso)));
        //etapas.add(new Document("$unwind", new Document("path", "$supervisiones")));
        etapas.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "proyectos")
                        .append("localField", "idProyecto")
                        .append("foreignField", "_id")
                        .append("as", "proyecto")
        ));
        etapas.add(new Document("$unwind", new Document("path", "$proyecto")));
        etapas.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("proyecto", 1)
        ));
        
        List<PublicacionProyectoDTO> proyecto = baseDatos.getCollection("publicacionesCongreso", PublicacionProyectoDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(proyecto.isEmpty()){
            return null;
        }
        
        return proyecto.get(0).getProyecto();
    }

    @Override
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionCongreso) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idPublicacionCongreso)));
        etapas.add(new Document("$unwind", new Document("path", "$autores")));
        etapas.add(
                new Document("$lookup", 
                        new Document()
                        .append("from", "proyectos")
                        .append("localField", "autores.idProfesor")
                        .append("foreignField", "_id")
                        .append("as", "autoresPublicacion")
        ));
        etapas.add(new Document("$unwind", new Document("path", "$autoresPublicacion")));
        etapas.add(new Document("$project", 
                new Document()
                .append("_id", 0)
                .append("idAutor", "$autoresPublicacion._id")
                .append("nombreAutor", "$autoresPublicacion.nombre")
                .append("apellidoPaternoAutor", "$autoresPublicacion.apellidoPaterno")
                .append("telefonoAutor", "$autoresPublicacion.telefono")
                .append("orden", "$autores.orden")
        ));
        
        List<AutorDTO> autores = baseDatos.getCollection("publicacionesCongreso", AutorDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(autores.isEmpty()){
            return null;
        }
        
        return autores;
    }
    
}
