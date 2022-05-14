
package interfacesDAO;

import com.mongodb.client.MongoDatabase;

public interface IConexionBD {
    public MongoDatabase crearConexion();
    public MongoDatabase getConexion();
}
