
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidades.LineaInvestigacion;
import entidades.Profesor;
import entidades.Proyecto;
import entidades.Publicacion;
import interfaces.IConexionBD;
import interfaces.IProyectoDAO;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(ObjectId idProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proyecto> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultar(ObjectId idProyecto) {
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

    @Override
    public boolean agregarPublicacion(Publicacion publicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
