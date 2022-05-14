
package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dtos.ProfesorLineaInvestigacionDTO;
import dtos.ProfesorProyectoDTO;
import entidades.DetalleProyectoProfesor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.LineaInvestigacion;
import entidades.Profesor;
import entidades.Proyecto;
import entidades.Publicacion;
import interfacesDAO.IConexionBD;
import interfacesDAO.IProyectoDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
        this.baseDatos = this.conexion.getConexion();
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
                .append("publicacionesRevista", proyecto.getPublicacionesRevista())
                .append("publicacionesCongreso", proyecto.getPublicacionesCongreso())
                .append("detalles", proyecto.getDetalles())
                .append("idsLineasInvestigacion", proyecto.getIdsLineasInvestigacion());
        

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
    
    @Override
    public boolean actualizarIntegrantes(List<DetalleProyectoProfesor> integrantes, ObjectId id) {
        this.getCollection().updateOne(new Document("_id",id), new Document("$set", new Document("detalles", integrantes)));
        return true;
    }
    

    @Override
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion) {
//        Proyecto proyecto = this.consultar(idProyecto);
//        if(proyecto != null){
//            proyecto.getPublicaciones().add(publicacion);
//            this.actualizar(proyecto);
//            return true;
//        }
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
                .append("nombreProfesor", "$integrantes.nombre")
                .append("apellidoPaternoProfesor", "$integrantes.apellidoPaterno")
                .append("fechaInicioParticipacion", "$detalles.fechaInicio")
                .append("fechaFinParticipacion", "$detalles.fechaFin")
        ));
        
        List<ProfesorProyectoDTO> integrantesDoctores = baseDatos.getCollection("proyectos", ProfesorProyectoDTO.class).aggregate(etapasDoctor).into(new ArrayList());
        //NoDoctores
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
                .append("nombreProfesor", "$integrantes.nombre")
                .append("apellidoPaternoProfesor", "$integrantes.apellidoPaterno")
                .append("fechaInicioParticipacion", "$detalles.fechaInicio")
                .append("fechaFinParticipacion", "$detalles.fechaFin")
        ));
        
       List<ProfesorProyectoDTO> integrantesNoDoctores = baseDatos.getCollection("proyectos", ProfesorProyectoDTO.class).aggregate(etapasNoDoctor).into(new ArrayList());
        if(integrantesDoctores.isEmpty()){
            return null;
        }
        integrantesDoctores.addAll(integrantesNoDoctores);
        return integrantesDoctores;
        
       
    }
    

    @Override
    public Proyecto consultarPorNombre(String nombre) {
        List<Proyecto> resultados = this.getCollection().find(new Document("nombre", nombre)).into(new ArrayList());
        if(resultados.isEmpty()){
            return null;
        }
        return resultados.get(0);
    }

    @Override
    public Proyecto consultarPorCodigo(String codigo) {
        List<Proyecto> resultados = this.getCollection().find(new Document("codigoReferencia", codigo)).into(new ArrayList());
        if(resultados.isEmpty()){
            return null;
        }
        return resultados.get(0);
    }

    @Override
    public Proyecto consultarPorAcronimo(String acronimo) {
        List<Proyecto> resultados = this.getCollection().find(new Document("acronimo", acronimo)).into(new ArrayList());
        if(resultados.isEmpty()){
            return null;
        }
        return resultados.get(0);
    }

    @Override
    public List<Proyecto> consultarPorFechas(Date fechaInicio, Date fechaFin) {
        Document filtro = new Document()
                .append("fechaInicio", new Document("$gte", fechaInicio))
                .append("fechaFin", new Document("$lte", fechaFin));
         List<Proyecto> resultados = this.getCollection().find(filtro).into(new ArrayList());
         if(resultados.isEmpty()){
             return null;
         }
         return resultados;
    }

    @Override
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador) {
        /*
            1 = Mayor que
            2 = Menor que
            3 = Igual que
        */
        
        Document filtro = new Document();
       
        switch(filtroPresupuesto){
            case 0:
                break;
            case 1:
                filtro.append("presupuestoTotal", new Document("$gt", presupuesto));
                break;
            case 2:
                filtro.append("presupuestoTotal", new Document("$lt", presupuesto));
                break;
            case 3:
                filtro.append("presupuestoTotal", new Document("$eq", presupuesto));
                break;
            default:
                break;
        }
        
        if(idPrograma != null){
            filtro.append("idPrograma", idPrograma);
        }
        if(investigador != null){
            filtro.append("investigadorPrincipal", investigador);
        }
        if(patrocinador != null){
            filtro.append("patrocinador", patrocinador);
        }
        List<Proyecto> resultados = this.getCollection().find(filtro).into(new ArrayList());
         if(resultados.isEmpty()){
             return null;
         }
         return resultados;
    }


}
