
package dtos;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * @author Tilín
 */
public class ProfesorProyectoDTO {
    private ObjectId idProfesor;
    private String nombreProfesor;
    private String apellidoPaternoProfesor;
    private Date fechaInicioParticipacion;
    private Date fechaFinParticipacion;
    
}
