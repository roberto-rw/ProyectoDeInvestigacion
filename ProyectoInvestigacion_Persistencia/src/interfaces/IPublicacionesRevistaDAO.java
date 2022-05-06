/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dtos.AutorDTO;
import entidades.Proyecto;
import entidades.PublicacionRevista;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IPublicacionesRevistaDAO {
    public boolean agregar(PublicacionRevista publicacionRevista);
    public boolean actualizar(PublicacionRevista publicacionRevista);
    public boolean eliminar(ObjectId idPublicacionRevista);
    public PublicacionRevista consultar(ObjectId idPublicacionRevista);
    public List<PublicacionRevista> consultarTodos();
    public Proyecto consultarProyecto(ObjectId idPublicacionRevista);
    public List<AutorDTO> consultarAutores(ObjectId idPublicacionRevista);
}
