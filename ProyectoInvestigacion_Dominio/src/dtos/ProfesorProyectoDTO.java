
package dtos;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * @author Tilín
 */
public class ProfesorProyectoDTO {
    private ObjectId idProfesor;
    private ObjectId idProyecto;
    private String nombreProfesor;
    private Date fechaInicioParticipacion;
    private Date fechaFinParticipacion;
    
}
