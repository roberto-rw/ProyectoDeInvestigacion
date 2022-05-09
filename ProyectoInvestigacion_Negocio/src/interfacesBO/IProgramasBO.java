/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesBO;

import entidades.Programa;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public interface IProgramasBO {
    public boolean agregar(Programa programa);
    public boolean actualizar(Programa programa);
    public boolean eliminar(ObjectId idPrograma);
    public List<Programa> consultarTodos();
}
