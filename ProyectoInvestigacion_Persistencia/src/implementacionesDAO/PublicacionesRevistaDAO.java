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
import entidades.PublicacionRevista;
import interfacesDAO.IConexionBD;
import interfacesDAO.IPublicacionesRevistaDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class PublicacionesRevistaDAO implements IPublicacionesRevistaDAO {
    
    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public PublicacionesRevistaDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.getConexion();
    }
    
    private MongoCollection<PublicacionRevista> getColeccion(){
        return baseDatos.getCollection("publicacionesRevista", PublicacionRevista.class);
    }
    

    @Override
    public boolean agregar(PublicacionRevista publicacionRevista) {
        this.getColeccion().insertOne(publicacionRevista);
        return true;
    }

    @Override
    public boolean actualizar(PublicacionRevista publicacionRevista) {
        Document filtro = new Document("_id", publicacionRevista.getId());
        Document cambios = new Document()
                .append("numeroSecuencia", publicacionRevista.getNumeroSecuencia())
                .append("titulo", publicacionRevista.getTitulo())
                .append("idProyecto", publicacionRevista.getIdProyecto())
                .append("autores", publicacionRevista.getAutores())
                .append("nombreRevista", publicacionRevista.getNombreRevista())
                .append("editorial", publicacionRevista.getEditorial())
                .append("volumen", publicacionRevista.getVolumen())
                .append("numero", publicacionRevista.getNumero())
                .append("pagInicio", publicacionRevista.getPagInicio())
                .append("pagFin", publicacionRevista.getPagFin());
        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idPublicacionRevista) {
        this.getColeccion().deleteOne(new Document("_id", idPublicacionRevista));
        return true;
    }

    @Override
    public PublicacionRevista consultar(ObjectId idPublicacionRevista) {
        List<PublicacionRevista> publicacionRevista = this.getColeccion().find(new Document("_id", idPublicacionRevista)).into(new ArrayList());
        if(publicacionRevista.isEmpty()){
            return null;
        }
        return publicacionRevista.get(0);
    }

    @Override
    public List<PublicacionRevista> consultarTodos() {
        return this.getColeccion().find().into(new ArrayList());
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idPublicacionRevista) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idPublicacionRevista)));
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
        
        List<PublicacionProyectoDTO> proyecto = baseDatos.getCollection("publicacionesRevista", PublicacionProyectoDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(proyecto.isEmpty()){
            return null;
        }
        
        return proyecto.get(0).getProyecto();
    }

    @Override
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionRevista) {
        List<Document> etapas = new ArrayList();
        etapas.add(new Document("$match", new Document("_id", idPublicacionRevista)));
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
        
        List<AutorDTO> autores = baseDatos.getCollection("publicacionesRevista", AutorDTO.class).aggregate(etapas).into(new ArrayList());

       
        if(autores.isEmpty()){
            return null;
        }
        
        return autores;
    }
    
}
