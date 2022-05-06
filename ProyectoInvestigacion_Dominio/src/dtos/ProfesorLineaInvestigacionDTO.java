/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entidades.LineaInvestigacion;
import java.util.List;

/**
 *
 * @author jegav
 */
public class ProfesorLineaInvestigacionDTO {
    private List<LineaInvestigacion> lineasInvestigacion;

    public List<LineaInvestigacion> getLineasInvestigacion() {
        return lineasInvestigacion;
    }

    public void setLineasInvestigacion(List<LineaInvestigacion> lineasInvestigacion) {
        this.lineasInvestigacion = lineasInvestigacion;
    }

    
    
    
    
}
