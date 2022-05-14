
package implementacionesDAO;

import com.mongodb.client.MongoDatabase;
import interfacesDAO.IConexionBD;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import interfacesDAO.IConexionBD;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class ConexionBD implements IConexionBD{

    private static final String HOST = "localhost";
    private static final int PUERTO = 27017;
    private static final String BASE_DATOS = "ProyectoInvestigacion";
    private static ConexionBD conexionBD;
    private MongoDatabase conexion;
    
    private ConexionBD(){
        conexion = crearConexion();
    }
    
    public static ConexionBD getInstance(){
        if(conexionBD == null){
            conexionBD = new ConexionBD();
        }
        return conexionBD;
    }
    
    @Override
    public MongoDatabase crearConexion() {
        try{
            //CONFIGURACIÓN PARA QUE MONGODRIVE REALICE EL MAPEO DE POJOS AUMATICAMENTE
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build()); //Le estas diciendo que quieres que los
            //pojos sean considerados a la hora de hacer conversiones, es en si validar los codecs

            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry); //Le dice que incluya codecs por default como
            //los dates, double, etc

            ConnectionString cadenaConexion = new ConnectionString("mongodb://"+HOST+"/"+PUERTO); //Establece conexión con mongo

            MongoClientSettings clientsSettings = MongoClientSettings.builder() //objeto con configuraciones para conexión con la bs mongo
                .applyConnectionString(cadenaConexion)
                .codecRegistry(codecRegistry)
                .build();

            MongoClient clienteMongo = MongoClients.create(clientsSettings); //Se utilza el objeto de arriba para establecer la conexión

            MongoDatabase baseDatos = clienteMongo.getDatabase(BASE_DATOS);

            return baseDatos;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
