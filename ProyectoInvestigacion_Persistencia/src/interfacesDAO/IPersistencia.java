/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesDAO;

import dtos.AutorDTO;
import dtos.PeriodoSupervisionDTO;
import dtos.ProfesorProyectoDTO;
import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import entidades.Programa;
import entidades.Proyecto;
import entidades.Publicacion;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jegav
 */
public interface IPersistencia {
    //DoctoresDAO
    public boolean agregar(Doctor doctor);
    public boolean actualizar(Doctor doctor);
    public boolean agregarInvestigadorDoctor(InvestigadorDoctor investigadorDoctor);
    public boolean eliminarDoctor(ObjectId idDoctor);
    public Doctor consultarDoctor(ObjectId idDoctor);
    public List<Doctor> consultarTodosDoctor();
    public List<InvestigadorDoctor> consultarTodosInvestigadoresDoctor();
    public List<LineaInvestigacion> consultarLineasInvestigacionDoctor(ObjectId idDoctor);
    public InvestigadorDoctor consultarInvestigadorDoctor(ObjectId idInvestigador);
    
    //NoDoctoresDAO
    public boolean agregar(NoDoctor noDoctor);
    public boolean actualizar(NoDoctor noDoctor);
    public boolean agregarInvestigadorNoDoctor(InvestigadorNoDoctor investigadorNoDoctor);
    public boolean eliminarNoDoctor(ObjectId idNoDoctor);
    public NoDoctor consultarNoDoctor(ObjectId idNoDoctor);
    public List<NoDoctor> consultarTodosNoDoctor();
    public List<LineaInvestigacion> consultarLineasInvestigacionNoDoctor(ObjectId idNoDoctor);
    public List<PeriodoSupervisionDTO> consultarSupervisiones(ObjectId idNoDoctor);
    public InvestigadorNoDoctor consultarInvestigadorNoDoctor(ObjectId idInvestigador);
    
    //LineaInvestigacionDAO
    public boolean agregar(LineaInvestigacion lineaInvestigacion);
    public boolean actualizar(LineaInvestigacion lineaInvestigacion);
    public boolean eliminarLineaInvestigacion(ObjectId idLineaInvestigacion);
    public LineaInvestigacion consultarLineaInvestigacion(ObjectId idLineaInvestigacion);
    public List<LineaInvestigacion> consultarTodosLineaInvestigacion();
    
    //ProgramasDAO
    public boolean agregar(Programa programa);
    public boolean actualizar(Programa programa);
    public boolean eliminarPrograma(ObjectId idPrograma);
    public Programa consultarPrograma(ObjectId idPrograma);
    public List<Programa> consultarTodosPrograma();
    
    //ProyectoDAO
    public boolean agregar(Proyecto proyecto);
    public boolean actualizar(Proyecto proyecto);
    public boolean eliminarProyecto(ObjectId idProyecto);
    public List<Proyecto> consultarTodosProyecto();
    public Proyecto consultarProyecto(ObjectId idProyecto);
    public List<LineaInvestigacion> consultarLineasInvestigacionProyecto(ObjectId idProyecto);
    public List<ProfesorProyectoDTO> consultarIntegrantes(ObjectId idProyecto); 
    public boolean agregarPublicacion(ObjectId idProyecto, Publicacion publicacion);
    public Proyecto consultarProyectoPorNombre(String nombre);
    public Proyecto consultarProyectoPorCodigo(String codigo);
    public Proyecto consultarProyectoPorAcronimo(String acronimo);
    public List<Proyecto> consultarProyectoPorFechas(Date fechaInicio, Date fechaFin);
    public List<Proyecto> consultarPorCaracteristicas(ObjectId idPrograma, Float presupuesto, Integer filtroPresupuesto, InvestigadorDoctor investigador, String patrocinador);
    
    //PublicacionesCongresoDAO
    public boolean agregar(PublicacionCongreso publicacionCongreso);
    public boolean actualizar(PublicacionCongreso publicacionCongreso);
    public boolean eliminarPublicacionCongreso(ObjectId idPublicacionCongreso);
    public PublicacionCongreso consultarPublicacionCongreso(ObjectId idPublicacionCongreso);
    public List<PublicacionCongreso> consultarTodosPublicacionCongreso();
    public Proyecto consultarProyectoPublicacionCongreso(ObjectId idPublicacionCongreso);
    public List<AutorDTO> consultarAutoresPublicacionCongreso(ObjectId idPublicacionCongreso);
    
    //PublicacionesRevistaDAO
    public boolean agregar(PublicacionRevista publicacionRevista);
    public boolean actualizar(PublicacionRevista publicacionRevista);
    public boolean eliminarPublicacionRevista(ObjectId idPublicacionRevista);
    public PublicacionRevista consultarPublicacionRevista(ObjectId idPublicacionRevista);
    public List<PublicacionRevista> consultarTodosPublicacionRevista();
    public Proyecto consultarProyectoPublicacionRevista(ObjectId idPublicacionRevista);
    public List<AutorDTO> consultarAutoresPublicacionRevista(ObjectId idPublicacionRevista);
    
    
}
