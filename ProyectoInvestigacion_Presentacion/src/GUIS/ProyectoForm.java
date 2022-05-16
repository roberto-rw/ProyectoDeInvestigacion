/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.PeriodoParticipacion;
import entidades.Profesor;
import entidades.Programa;
import entidades.Proyecto;
import implementacionesBO.BOSFactory;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.IProfesoresBO;
import interfacesBO.IProgramasBO;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;
import utils.ButtonColumn;
import interfacesBO.IProyectosBO;
import java.time.ZoneId;

/**
 *
 * @author pc
 */
public class ProyectoForm extends javax.swing.JFrame {

    private IProfesoresBO profesoresBO;
    private ILineaInvestigacionBO lineaInvestigacion;
    private IProgramasBO programasBO;
    private IProyectosBO proyectoBO;

    private Integer editarIntegrante;
    private List<PeriodoParticipacion> periodosIntegrantes;
    
    private ObjectId editar;
    
    /**
     * Creates new form ProyectoForm
     */
    public ProyectoForm() {
        initComponents();
        this.profesoresBO = BOSFactory.crearProfesoresBO();
        
        //Ocultar Ids
        
        //Proyectos
        TableColumnModel modeloColumnasProyecto = this.tablaProyectos.getColumnModel();
        tablaProyectos.removeColumn( modeloColumnasProyecto.getColumn(0));
        
        //Lineas Investigación
        TableColumnModel modeloColumnasLineas = this.lineaInvestigacionTabla.getColumnModel();
        lineaInvestigacionTabla.removeColumn( modeloColumnasLineas.getColumn(0));
        
        //Integrantes
        TableColumnModel modeloColumnasIntegrantes = this.tablaIntegrantes.getColumnModel();
        tablaIntegrantes.removeColumn( modeloColumnasIntegrantes.getColumn(0));
        
        this.lineaInvestigacion = BOSFactory.crearLineaInvestigacionBO();
        this.proyectoBO = BOSFactory.crearProyectoBO();
        this.programasBO = BOSFactory.crearProgramaBO();
        this.periodosIntegrantes = new ArrayList();
        this.editarIntegrante = null;
        this.llenarComboBoxIntegrantes();
        this.llenarTablaLineasInvestigacion();
        this.llenarTablaProyectos();
        this.llenarComboBoxInvestigadorDoctor();
        this.llenarComboBoxProgramas();
        
    }
    
    private void llenarComboBoxIntegrantes(){
        List<Object> profesores = new ArrayList();
        profesores.add("-Selecciona-");
        profesores.addAll(profesoresBO.consultarTodosProfesores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(profesores.toArray());
        integrantesComboBox.setModel(modeloComboBox);
    }
    
    private void llenarComboBoxProgramas(){
        List<Object> programas = new ArrayList();
        programas.add("-Selecciona-");
        programas.addAll(programasBO.consultarTodos());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(programas.toArray());
        
        programaComboBox.setModel(modeloComboBox);
        
        
    }
    
    private void llenarComboBoxInvestigadorDoctor(){
        List<Object> investigadoresDoctores = new ArrayList();
        investigadoresDoctores.add("-Selecciona-");
        investigadoresDoctores.addAll(profesoresBO.consultarTodosInvestigadorDoctores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(investigadoresDoctores.toArray());
        investigadorDoctorComboBox.setModel(modeloComboBox);
    }
    
    //Validaciones
    private boolean validarCamposVaciosIntegrantes() {
        return this.finSPicker.getDate() == null || this.inicioSPicker.getDate() == null;
    }
    
    private boolean validarFechasIntegrantes() {
        return !(inicioSPicker.getDate().compareTo(finSPicker.getDate()) < 0);
    }
    
//    private boolean validarFechasProyecto(){
//        return !(fechaInicioPicker.getDate().compareTo(fechaFinPicker.getDate()) >= 0);
//    }
    
    private boolean validarLineasSeleccionadas(){
        return this.lineaInvestigacionTabla.getSelectedRows().length > 0;
    }
    
    private boolean validarCamposVacios() {
        return !(codigoTxt.getText().equals("") || this.nombreTxt.getText().equals("") || this.acronimoTxt.getText().equals("") || this.patrocinadorTxt.getText().equals("") 
        || this.presupuestoTxt.getText().equals("") || this.descripcionTxt.getText().equals(""));
        
    }
    
    private boolean validarIntegrantesSeleccionados(){
        return this.tablaIntegrantes.getRowCount() >= 2;
    }
    
    private boolean validarSeleccionIntegrante(){
        return this.integrantesComboBox.getSelectedIndex() != 0;
    }
    
    private boolean validarPresupuesto(){
        try{
            Float presupuesto = Float.parseFloat(this.presupuestoTxt.getText());
            return presupuesto > 0;
            
        } catch(NumberFormatException nfe){
            return false;
        }
    }
    
    private boolean validarSeleccionPrograma(){
        return this.programaComboBox.getSelectedIndex() != 0;
    }
    
    private boolean validarSeleccionInvestigadorPrincipal(){
        return this.investigadorDoctorComboBox.getSelectedIndex() != 0;
    }
    
    
    private ObjectId getIdIntegranteSeleccionado() {
        int indiceFilaSeleccionada = this.tablaIntegrantes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaIntegrantes.getModel();
            int indiceColumnaId = 0;
            ObjectId idIntegranteSeleccionado = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idIntegranteSeleccionado;
        } else {
            return null;
        }
    }
    
    private ObjectId getIdProyectoSeleccionado() {
        int indiceFilaSeleccionada = this.tablaProyectos.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProyectos.getModel();
            int indiceColumnaId = 0;
            ObjectId idProyectoSeleccionado = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idProyectoSeleccionado;
        } else {
            return null;
        }
    }

    private void editarParticipacion() {
        Profesor profesorSeleccionado = profesoresBO.consultarDoctor(this.getIdIntegranteSeleccionado());
        if(profesorSeleccionado == null){
            profesorSeleccionado = profesoresBO.consultarNoDoctor(this.getIdIntegranteSeleccionado());
        }
        this.integrantesComboBox.setSelectedItem(profesorSeleccionado);

        int indiceFilaSeleccionada = this.tablaIntegrantes.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) this.tablaIntegrantes.getModel();
        String fechaInicio = (String) modelo.getValueAt(indiceFilaSeleccionada, 3);
        String fechaFin = (String) modelo.getValueAt(indiceFilaSeleccionada, 4);

        String[] inicioSplit = fechaInicio.split("/");
        String[] finSplit = fechaFin.split("/");

        this.inicioSPicker.setDate(LocalDate.of(Integer.parseInt(inicioSplit[2]), Integer.parseInt(inicioSplit[1]), Integer.parseInt(inicioSplit[0]))); //Despliega la fecha del Inicio en el DatePicker
        this.finSPicker.setDate(LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]))); //Despliega la fecha fin en el DatePicker
        this.editarIntegrante = this.tablaIntegrantes.getSelectedRow();
        this.agregarParticipacionBtn.setText("Editar Integrante");
    }
    
    private void eliminarIntegrante() {
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el integrante seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcionSeleccionada == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (opcionSeleccionada == JOptionPane.NO_OPTION) {
            return;
        }
        int indiceFilaSeleccionada = this.tablaIntegrantes.getSelectedRow();
        this.periodosIntegrantes.remove(indiceFilaSeleccionada);
        this.llenarTablaIntegrantes();

    }
     
    private void llenarTablaIntegrantes() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaIntegrantes.getModel();
        modeloTabla.setRowCount(0);
        Action editar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editarParticipacion();
            }
        };

        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarIntegrante();
            }
        };
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        this.periodosIntegrantes.forEach(periodo -> {
           Profesor integrante = profesoresBO.consultarDoctor(periodo.getIdProfesor());
           if(integrante == null){
               integrante = profesoresBO.consultarNoDoctor(periodo.getIdProfesor());
           }
            Object[] fila = new Object[7];
            fila[0] = periodo.getIdProfesor();
            fila[1] = integrante.getNombre();
            fila[2] = integrante.getApellidoPaterno();
            fila[3] = formatter.format(periodo.getFechaInicio());
            fila[4] = formatter.format(periodo.getFechaFin());
            fila[5] = "Editar";
            fila[6] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaIntegrantes, editar, 4);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaIntegrantes, eliminar, 5);
    }

    private void llenarTablaLineasInvestigacion() {
        List<LineaInvestigacion> lineasInvestigacion = lineaInvestigacion.consultarTodos();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.lineaInvestigacionTabla.getModel();
        modeloTabla.setRowCount(0);
        lineasInvestigacion.forEach(linea -> {
            Object[] fila = new Object[4];
            fila[0] = linea.getId();
            fila[1] = linea.getCodigo();
            fila[2] = linea.getNombre();
            fila[3] = linea.getConjuntoDescriptores();

            modeloTabla.addRow(fila);
        });
    }
    
    private void llenarTablaProyectos(){
        List<Proyecto> proyectos = proyectoBO.consultarTodos();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaProyectos.getModel();
        modeloTabla.setRowCount(0);
        
        Action editar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editarProyecto();
            }
        };

        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarProyecto();
            }
        };
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        proyectos.forEach(linea -> {
            Object[] fila = new Object[12];
            fila[0] = linea.getId();
            fila[1] = linea.getNombre();
            fila[2] = linea.getAcronimo();
            fila[3] = linea.getPresupuestoTotal();
            fila[4] = linea.getIdPrograma();
            fila[5] = linea.getPatrocinador();
            fila[6] = formatter.format(linea.getFechaInicio());
            fila[7] = formatter.format(linea.getFechaFin());
            fila[8] = linea.getDescripcion();
            fila[9] = linea.getInvestigadorPrincipal();
            fila[10] = "Editar";
            fila[11] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        
        ButtonColumn buttonColumnEditar = new ButtonColumn(this.tablaProyectos, editar, 9);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(this.tablaProyectos, eliminar, 10);
    }
    
    private void eliminarProyecto(){
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el proyecto seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if(opcionSeleccionada == JOptionPane.CLOSED_OPTION){
            return;
        }
        
        if(opcionSeleccionada  == JOptionPane.NO_OPTION){
            return;
        }
//        int indiceFilaSeleccionada = this.tablaProyectos.getSelectedRow();
        this.proyectoBO.eliminar(this.getIdProyectoSeleccionado());
        this.llenarTablaProyectos();
    }
    
    private void editarProyecto(){
        Proyecto proyectoSeleccionado = proyectoBO.consultar(this.getIdProyectoSeleccionado());
        
        this.codigoTxt.setText(proyectoSeleccionado.getCodigoReferencia());
        this.nombreTxt.setText(proyectoSeleccionado.getNombre());
        this.acronimoTxt.setText(proyectoSeleccionado.getAcronimo());
        this.programaComboBox.setSelectedItem(programasBO.consultar(proyectoSeleccionado.getIdPrograma()));
        this.presupuestoTxt.setText(proyectoSeleccionado.getPresupuestoTotal().toString());
        this.patrocinadorTxt.setText(proyectoSeleccionado.getPatrocinador());
        this.investigadorDoctorComboBox.setSelectedItem(proyectoSeleccionado.getInvestigadorPrincipal());
        Date fechaInicio = proyectoSeleccionado.getFechaInicio();
        fechaInicioPicker.setDate(LocalDate.of(fechaInicio.getYear()+1900, fechaInicio.getMonth()+1, fechaInicio.getDate()));
        Date fechaFin = proyectoSeleccionado.getFechaFin();
        fechaFinPicker.setDate(LocalDate.of(fechaFin.getYear()+1900, fechaFin.getMonth()+1, fechaFin.getDate()));
        descripcionTxt.setText(proyectoSeleccionado.getDescripcion());
        
//        this.llenarTablaIntegrantes(proyectoBO.consultarIntegrantes(proyectoSeleccionado.getId()));
        this.periodosIntegrantes = proyectoSeleccionado.getDetalles();
        this.llenarTablaIntegrantes();
        this.mostrarLineasInvestigacionSeleccionadas(proyectoBO.consultarLineasInvestigacion(proyectoSeleccionado.getId()));
        editar = proyectoSeleccionado.getId();
        btnGuardarProyecto.setText("Editar Proyecto");
        
    }
    
    private void mostrarLineasInvestigacionSeleccionadas(List<LineaInvestigacion> lineasInvestigacion){
         DefaultTableModel modelo = (DefaultTableModel)this.lineaInvestigacionTabla.getModel();
            int indiceColumnaId = 0;
            //ObjectId idProfesorSeleccionado = (ObjectId)modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            
            for (int i = 0; i < modelo.getRowCount(); i++) {
                ObjectId idLineaSeleccionada = (ObjectId)modelo.getValueAt(i, indiceColumnaId);
                LineaInvestigacion lineaInvestigacion = new LineaInvestigacion(idLineaSeleccionada);
                if(lineasInvestigacion.contains(lineaInvestigacion)) this.lineaInvestigacionTabla.getSelectionModel().addSelectionInterval(i, i);
            }
     }
    
    private List<ObjectId> getLineasInvestigacionSeleccionadas() {
        List<ObjectId> lineasSeleccionadas = new ArrayList();
        int[] filas = lineaInvestigacionTabla.getSelectedRows();

        DefaultTableModel modelo = (DefaultTableModel) this.lineaInvestigacionTabla.getModel();
        for (int fila : filas) {
            ObjectId idLineaInvestigacion = (ObjectId) modelo.getValueAt(fila, 0);
            lineasSeleccionadas.add(idLineaInvestigacion);
        }
        return lineasSeleccionadas;
    }
    
    private void guardar(){
        if (!validarCamposVacios()) {
            JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         if(!this.validarLineasSeleccionadas()){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna linea", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
//        if (!this.validarFechasProyecto()) {
//            JOptionPane.showMessageDialog(this, "Las fechas colcadas no son válidas", "información", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        
        if(!this.validarIntegrantesSeleccionados()){
            JOptionPane.showMessageDialog(this, "Deben seleccionarse al menos 2 integrantes", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!this.validarPresupuesto()){
            JOptionPane.showMessageDialog(this, "Presupuesto inválido", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!this.validarSeleccionPrograma()){
            JOptionPane.showMessageDialog(this, "Se debe seleccionar un programa", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!this.validarSeleccionInvestigadorPrincipal()){
            JOptionPane.showMessageDialog(this, "Se debe seleccionar un investigador principal", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        String codigoReferencia = codigoTxt.getText();
      
        String nombreProyecto = nombreTxt.getText();
        
        String acronimo = acronimoTxt.getText();
        
        Proyecto proyecto = null;
        Float presupuesto = Float.parseFloat(presupuestoTxt.getText());
        String patrocinador = patrocinadorTxt.getText();
        Programa programa = (Programa) programaComboBox.getSelectedItem();
        InvestigadorDoctor investigadorDoctor = (InvestigadorDoctor) investigadorDoctorComboBox.getSelectedItem();
        Date fechaInicio = Date.from(this.fechaInicioPicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
       Date fechaFin = Date.from(this.fechaFinPicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String descripcion = descripcionTxt.getText();
        
        
        List<ObjectId> lineasSeleccionadas = this.getLineasInvestigacionSeleccionadas();
//        List<DetalleProyectoProfesor> integrantesSeleccionados = this.getIntegrantesSeleccionados();
        if(editar != null){
            proyecto = new Proyecto(editar, codigoReferencia, nombreProyecto, acronimo, presupuesto, programa.getId(), patrocinador, fechaInicio, fechaFin, descripcion, investigadorDoctor, this.periodosIntegrantes, lineasSeleccionadas);
            boolean seEditoProyecto = proyectoBO.actualizar(proyecto);
            if(seEditoProyecto){
                JOptionPane.showMessageDialog(this, "Se modificó el proyecto correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
                this.llenarTablaProyectos();
            } else{
                JOptionPane.showMessageDialog(this, "No se pudo modificar el Proyecto", "información", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else{
            if(proyectoBO.estaRepetidoCodigo(codigoReferencia)){
                JOptionPane.showMessageDialog(this, "El código ya está en uso", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(proyectoBO.estaRepetidoAcronimo(acronimo)){
                JOptionPane.showMessageDialog(this, "El acronimo ya está en uso", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(proyectoBO.estaRepetidoNombre(nombreProyecto)){
                JOptionPane.showMessageDialog(this, "El nombre ya está en uso", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            proyecto = new Proyecto(codigoReferencia, nombreProyecto, acronimo, presupuesto, programa.getId(), patrocinador, fechaInicio, fechaFin, descripcion, investigadorDoctor, this.periodosIntegrantes, lineasSeleccionadas);
            if(!proyectoBO.validarFechasReales(proyecto)){
                JOptionPane.showMessageDialog(this, "Las fechas colcadas no son válidas", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean seAgregoProyecto = proyectoBO.agregar(proyecto);
                 
            if (seAgregoProyecto) {
                JOptionPane.showMessageDialog(this, "Se agregó el proyecto correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
                this.llenarTablaProyectos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el Proyecto", "información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        vaciarForm();
    }
    
    private void vaciarProyectoForm() {
        this.integrantesComboBox.setSelectedIndex(0);
        fechaInicioPicker.setDate(null);
        fechaFinPicker.setDate(null);
        inicioSPicker.setDate(null);
        finSPicker.setDate(null);
        this.editarIntegrante = null;
        this.agregarParticipacionBtn.setText("Agregar Integrante");
    }
       
    private void vaciarIntegrantesPanel(){
        inicioSPicker.setDate(null);
        finSPicker.setDate(null);
        
    }
    
    private void vaciarForm() {
        this.vaciarProyectoForm();
        this.periodosIntegrantes.clear();
        this.codigoTxt.setText("");
        this.nombreTxt.setText("");
        this.acronimoTxt.setText("");
        this.programaComboBox.setSelectedIndex(0);
        this.presupuestoTxt.setText("");
        this.patrocinadorTxt.setText("");
        this.investigadorDoctorComboBox.setSelectedIndex(0);
        this.descripcionTxt.setText("");
        this.llenarTablaLineasInvestigacion();
        this.llenarTablaProyectos();
        this.btnGuardarProyecto.setText("Guardar Proyecto");
        editar = null;
        btnGuardarProyecto.setText("Guardar Proyecto");
        this.periodosIntegrantes.clear();
        this.llenarTablaIntegrantes();

    }
   
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        codigoTxt = new javax.swing.JTextField();
        nombreTxt = new javax.swing.JTextField();
        acronimoTxt = new javax.swing.JTextField();
        programaComboBox = new javax.swing.JComboBox<>();
        presupuestoTxt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        descripcionTxt = new javax.swing.JTextArea();
        codigoLbl = new javax.swing.JLabel();
        nombreLbl = new javax.swing.JLabel();
        acronimoLbl = new javax.swing.JLabel();
        programaLbl = new javax.swing.JLabel();
        presupuestoLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fechaInicioLbl = new javax.swing.JLabel();
        fechaFinLbl = new javax.swing.JLabel();
        patrocinadorLbl = new javax.swing.JLabel();
        patrocinadorTxt = new javax.swing.JTextField();
        descripcionLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardarProyecto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIntegrantes = new javax.swing.JTable();
        finSPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        agregarParticipacionBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        integrantesComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        inicioSPicker = new com.github.lgooddatepicker.components.DatePicker();
        jScrollPane2 = new javax.swing.JScrollPane();
        lineaInvestigacionTabla = new javax.swing.JTable();
        patrocinadorLbl1 = new javax.swing.JLabel();
        investigadorDoctorComboBox = new javax.swing.JComboBox<>();
        fechaInicioPicker = new com.github.lgooddatepicker.components.DatePicker();
        fechaFinPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Proyecto");

        programaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        presupuestoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                presupuestoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                presupuestoTxtKeyTyped(evt);
            }
        });

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Acronimo", "Presupuesto", "Programa", "Patrocinador", "Fecha de inicio", "Fecha de fin", "Descripción", "Investigador Principal", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProyectos.setRowHeight(30);
        jScrollPane4.setViewportView(tablaProyectos);

        descripcionTxt.setColumns(20);
        descripcionTxt.setRows(5);
        jScrollPane6.setViewportView(descripcionTxt);

        codigoLbl.setText("Codigo de Referencia:");
        codigoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        nombreLbl.setText("Nombre:");
        nombreLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        acronimoLbl.setText("Acrónimo:");
        acronimoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        programaLbl.setText("Programa:");
        programaLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        presupuestoLbl.setText("Presupuesto:");
        presupuestoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel1.setText("$");

        fechaInicioLbl.setText("Fecha Inicio:");
        fechaInicioLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        fechaFinLbl.setText("Fecha Fin:");
        fechaFinLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        patrocinadorLbl.setText("Patrocinador:");
        patrocinadorLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        descripcionLbl.setText("Descripcion:");
        descripcionLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel2.setText("Registrar Proyecto");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        btnGuardarProyecto.setText("Guardar Proyecto");
        btnGuardarProyecto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProyectoActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Integrante"));

        tablaIntegrantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido Paterno", "Inicio Participación", "Fin Participación", "Editar", "Eliminar"
            }
        ));
        tablaIntegrantes.setRowHeight(30);
        jScrollPane1.setViewportView(tablaIntegrantes);

        jLabel3.setText("Inicio Participación:");
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel4.setText("Fin Participación:");
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        agregarParticipacionBtn.setText("Agregar integrante");
        agregarParticipacionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarParticipacionBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");

        integrantesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Integrante:");
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(integrantesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(finSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(inicioSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(127, 127, 127))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(agregarParticipacionBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarBtn)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(integrantesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inicioSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(finSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarParticipacionBtn)
                    .addComponent(cancelarBtn))
                .addGap(28, 28, 28))
        );

        lineaInvestigacionTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descriptores"
            }
        ));
        lineaInvestigacionTabla.setRowHeight(30);
        jScrollPane2.setViewportView(lineaInvestigacionTabla);

        patrocinadorLbl1.setText("Investigador Principal: ");
        patrocinadorLbl1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        investigadorDoctorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Lineas de Investigación:");

        jLabel8.setText("Proyectos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(743, 743, 743))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(descripcionLbl)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fechaInicioLbl)
                                        .addComponent(fechaFinLbl))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fechaInicioPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fechaFinPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(presupuestoLbl)
                                        .addComponent(patrocinadorLbl))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(patrocinadorLbl1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(investigadorDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(programaLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(programaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(acronimoLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acronimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(628, 628, 628)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreLbl)
                                .addGap(18, 18, 18)
                                .addComponent(nombreTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(codigoTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardarProyecto)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(570, 570, 570))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(acronimoLbl)
                                .addComponent(acronimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codigoLbl)
                                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombreLbl))
                                .addGap(57, 57, 57)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(programaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(programaLbl))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presupuestoLbl)
                            .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patrocinadorLbl)
                            .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patrocinadorLbl1)
                            .addComponent(investigadorDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaInicioLbl)
                            .addComponent(fechaInicioPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaFinLbl)
                            .addComponent(fechaFinPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(descripcionLbl)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProyecto)
                            .addComponent(jButton2))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void presupuestoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presupuestoTxtKeyPressed

    }//GEN-LAST:event_presupuestoTxtKeyPressed

    private void agregarParticipacionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarParticipacionBtnActionPerformed

        
        if (!this.validarSeleccionIntegrante()) {
            JOptionPane.showMessageDialog(this, "No hay ningun integrante seleccionado", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.validarCamposVaciosIntegrantes()){
            JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (this.validarFechasIntegrantes()) {
            JOptionPane.showMessageDialog(this, "Las fechas colcadas no son válidas", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        

        Profesor profesor = (Profesor) this.integrantesComboBox.getSelectedItem();
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.set(inicioSPicker.getDate().getYear(), inicioSPicker.getDate().getMonthValue() - 1, inicioSPicker.getDate().getDayOfMonth(), 0, 0, 0);
        Calendar fechaFinCalendar = Calendar.getInstance();
        fechaFinCalendar.set(finSPicker.getDate().getYear(), finSPicker.getDate().getMonthValue() - 1, finSPicker.getDate().getDayOfMonth(), 0, 0, 0);

        ObjectId idIntegranteSeleccionado = profesor.getId();
        Date fechaInicioSeleccionada = fechaInicioCalendar.getTime();
        Date fechaFinSeleccionada = fechaFinCalendar.getTime();

        if (editarIntegrante != null) {

            PeriodoParticipacion pParticipacionEditar = this.periodosIntegrantes.get(editarIntegrante);
            pParticipacionEditar.setIdProfesor(idIntegranteSeleccionado);
            pParticipacionEditar.setFechaInicio(fechaInicioSeleccionada);
            pParticipacionEditar.setFechaFin(fechaFinSeleccionada);
            this.periodosIntegrantes.set(editarIntegrante, pParticipacionEditar);
            this.editarIntegrante = null;
            this.agregarParticipacionBtn.setText("Agregar Integrante");
            JOptionPane.showMessageDialog(this, "Se realizaron los cambios correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            PeriodoParticipacion pParticipacion = new PeriodoParticipacion(idIntegranteSeleccionado, fechaInicioSeleccionada, fechaFinSeleccionada);
            this.periodosIntegrantes.add(pParticipacion);
            JOptionPane.showMessageDialog(this, "Se agregó el integrante", "información", JOptionPane.INFORMATION_MESSAGE);
        }
        
        vaciarIntegrantesPanel();
        llenarTablaIntegrantes();
    }//GEN-LAST:event_agregarParticipacionBtnActionPerformed

    private void btnGuardarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProyectoActionPerformed
        this.guardar();

    }//GEN-LAST:event_btnGuardarProyectoActionPerformed

    private void presupuestoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presupuestoTxtKeyTyped
        char c = evt.getKeyChar();
       
       if((c < '0' || c>'9') && (c > '.')){
           evt.consume();
       }
    }//GEN-LAST:event_presupuestoTxtKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.vaciarForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProyectoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProyectoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProyectoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProyectoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProyectoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acronimoLbl;
    private javax.swing.JTextField acronimoTxt;
    private javax.swing.JButton agregarParticipacionBtn;
    private javax.swing.JButton btnGuardarProyecto;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JLabel codigoLbl;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JLabel descripcionLbl;
    private javax.swing.JTextArea descripcionTxt;
    private javax.swing.JLabel fechaFinLbl;
    private com.github.lgooddatepicker.components.DatePicker fechaFinPicker;
    private javax.swing.JLabel fechaInicioLbl;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioPicker;
    private com.github.lgooddatepicker.components.DatePicker finSPicker;
    private com.github.lgooddatepicker.components.DatePicker inicioSPicker;
    private javax.swing.JComboBox<String> integrantesComboBox;
    private javax.swing.JComboBox<String> investigadorDoctorComboBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable lineaInvestigacionTabla;
    private javax.swing.JLabel nombreLbl;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JLabel patrocinadorLbl;
    private javax.swing.JLabel patrocinadorLbl1;
    private javax.swing.JTextField patrocinadorTxt;
    private javax.swing.JLabel presupuestoLbl;
    private javax.swing.JTextField presupuestoTxt;
    private javax.swing.JComboBox<String> programaComboBox;
    private javax.swing.JLabel programaLbl;
    private javax.swing.JTable tablaIntegrantes;
    private javax.swing.JTable tablaProyectos;
    // End of variables declaration//GEN-END:variables
}
