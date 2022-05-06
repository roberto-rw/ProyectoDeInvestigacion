/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidades.Doctor;
import entidades.Programa;
import interfaces.IConexionBD;
import interfaces.IProgramasDAO;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public class ProgramasDAO implements IProgramasDAO{

    private IConexionBD conexion;
    private MongoDatabase baseDatos;
    
    
    public ProgramasDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.crearConexion();
    }

    private MongoCollection<Programa> getColeccion() {
        return baseDatos.getCollection("programas", Programa.class);
    }
    
    @Override
    public boolean agregar(Programa programa) {
        this.getColeccion().insertOne(programa);
        return true;
    }

    @Override
    public boolean actualizar(Programa programa) {
        Document filtro = new Document("_id", programa.getId());
        Document cambios = new Document()
                .append("nombre", programa.getNombre());
        this.getColeccion().updateOne(filtro, new Document("$set", cambios));
        return true;
    }

    @Override
    public boolean eliminar(ObjectId idPrograma) {
        this.getColeccion().deleteOne(new Document("_id", idPrograma));
        return true;
    }
    
}