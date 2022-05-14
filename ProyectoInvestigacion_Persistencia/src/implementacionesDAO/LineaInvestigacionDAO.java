package implementacionesDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dtos.AutorDTO;
import entidades.LineaInvestigacion;
import interfacesDAO.IConexionBD;
import interfacesDAO.ILineaInvestigacionDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * 
 * @author J.Fernando <josefer.hernandez@hotmail.com>
 */
public class LineaInvestigacionDAO implements ILineaInvestigacionDAO{

    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public LineaInvestigacionDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.getConexion();
    }
    
    private MongoCollection<LineaInvestigacion> getColeccion(){
        return baseDatos.getCollection("lineasInvestigacion", LineaInvestigacion.class);
    }
    
    @Override
    public boolean agregar(LineaInvestigacion lineaInvestigacion) {
        this.getColeccion().insertOne(lineaInvestigacion);
        return true;
    }

    @Override
    public boolean actualizar(LineaInvestigacion lineaInvestigacion) {
        Document filtro = new Document("_id", lineaInvestigacion.getId());
        Document cambios = new Document()
                .append("codigo", lineaInvestigacion.getCodigo())
                .append("nombre", lineaInvestigacion.getNombre())
                .append("conjuntoDescriptores", lineaInvestigacion.getConjuntoDescriptores());
                
        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idLineaInvestigacion) {
        this.getColeccion().deleteOne(new Document("_id", idLineaInvestigacion));
        return true;
    }

    @Override
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion) {
        List<LineaInvestigacion> lineaInvestigacion = this.getColeccion().find(new Document("_id", idLineaInvestigacion)).into(new ArrayList());
        if(lineaInvestigacion.isEmpty()){
            return null;
        }
        return lineaInvestigacion.get(0);
    }

    @Override
    public List<LineaInvestigacion> consultarTodos() {
        return this.getColeccion().find().into(new ArrayList());
    }

    
}
