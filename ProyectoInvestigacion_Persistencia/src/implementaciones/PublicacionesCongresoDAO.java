/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import interfaces.IConexionBD;
import interfaces.IPublicacionesCongreso;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public class PublicacionesCongresoDAO implements IPublicacionesCongreso{
    
    
    private IConexionBD conexion;
    private MongoDatabase baseDatos;

    public PublicacionesCongresoDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.crearConexion();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(ObjectId idPublicacionCongreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PublicacionCongreso> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto consultarProyecto(ObjectId idPublicacionCongreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionCongreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
