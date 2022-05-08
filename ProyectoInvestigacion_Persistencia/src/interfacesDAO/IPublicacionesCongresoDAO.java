/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesDAO;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionCongreso;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IPublicacionesCongresoDAO {
    public boolean agregar(PublicacionCongreso publicacionCongreso);
    public boolean actualizar(PublicacionCongreso publicacionCongreso);
    public boolean eliminar(ObjectId idPublicacionCongreso);
    public PublicacionCongreso consultar(ObjectId idPublicacionCongreso);
    public List<PublicacionCongreso> consultarTodos();
    public Proyecto consultarProyecto(ObjectId idPublicacionCongreso);
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionCongreso);
}
