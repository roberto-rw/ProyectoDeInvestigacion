/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import entidades.LineaInvestigacion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface ILineaInvestigacionBO {
    public boolean agregar(LineaInvestigacion lineaInvestigacion);
    public boolean actualizar(LineaInvestigacion lineaInvestigacion);
    public boolean eliminar(ObjectId idLineaInvestigacion) throws Exception;
    public LineaInvestigacion consultar(ObjectId idLineaInvestigacion);
    public List<LineaInvestigacion> consultarTodos();
    public boolean validarEliminarLineasProfesor(ObjectId idLineaInvestigacion);
    public boolean validarEliminarLineasProyecto(ObjectId idLineaInvestigacion);
}
