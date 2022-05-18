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
import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import utils.ButtonColumn;

/**
 *
 * @author jegav
 */
public class EditarProyectoForm extends javax.swing.JFrame {
    
    private Proyecto proyectoSeleccionado;
    private IFacadeBO facadeBO = new FacadeBO();
    private List<ObjectId> lineasInvestigacion;
    private List<PeriodoParticipacion> integrantesSeleccionados;
    private Integer editarIntegrante;
    private BuscarProyecto frameProveniente;
    private static EditarProyectoForm editarProyectoForm;
    /**
     * Creates new form VerProyectoForm
     * @param idProyecto
     * @param frameProveniente
     */
    public EditarProyectoForm(ObjectId idProyecto, BuscarProyecto frameProveniente) {
        initComponents();
        this.frameProveniente = frameProveniente;
        this.proyectoSeleccionado = facadeBO.consultarProyecto(idProyecto);
        this.lineasInvestigacion = proyectoSeleccionado.getIdsLineasInvestigacion();
        this.integrantesSeleccionados = proyectoSeleccionado.getIntegrantes();
        this.llenarComboBoxInvestigadores();
        this.llenarComboBoxProgramas();
        
        TableColumnModel modeloColumnasLineasInvestigacion = this.tablaLineasInvestigacion.getColumnModel();
        tablaLineasInvestigacion.removeColumn( modeloColumnasLineasInvestigacion.getColumn(0));
        
        TableColumnModel modeloColumnasLineasProyecto = this.tablaLineasProyecto.getColumnModel();
        tablaLineasProyecto.removeColumn( modeloColumnasLineasProyecto.getColumn(0));
        
        TableColumnModel modeloColumnasIntegrantes = this.tablaIntegrantes.getColumnModel();
        tablaIntegrantes.removeColumn( modeloColumnasIntegrantes.getColumn(0));
        this.desplegarProyecto();
        
    }
    
    public static EditarProyectoForm getInstance(ObjectId idProyecto, BuscarProyecto frameProveniente){
        if(editarProyectoForm != null){
           editarProyectoForm.dispose();
        }
        editarProyectoForm = new EditarProyectoForm(idProyecto, frameProveniente);
        return editarProyectoForm;
    }
    
    private void desplegarProyecto(){

        this.codigoTxt.setText(proyectoSeleccionado.getCodigoReferencia());
        this.acronimoTxt.setText(proyectoSeleccionado.getAcronimo());
        this.nombreTxt.setText(proyectoSeleccionado.getNombre());
        this.presupuestoTxt.setText(proyectoSeleccionado.getPresupuestoTotal().toString());
        this.programasComboBox.setSelectedItem(facadeBO.consultarPrograma(proyectoSeleccionado.getIdPrograma()));
        this.patrocinadorTxt.setText(proyectoSeleccionado.getCodigoReferencia());
        this.investigadoresComboBox.setSelectedItem(proyectoSeleccionado.getInvestigadorPrincipal());
        this.llenarTablaLineas();
        this.llenarTablaLineasProyecto();
        this.inicioPicker.setDate(Instant.ofEpochMilli(proyectoSeleccionado.getFechaInicio().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        this.finPicker.setDate(Instant.ofEpochMilli(proyectoSeleccionado.getFechaFin().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        this.descripcionTxt.setText(proyectoSeleccionado.getDescripcion());
        this.llenarTablaIntegrantes();
        this.llenarComboBoxIntegrantes();
        
        
    }
    
    private void llenarComboBoxIntegrantes(){
        List<Object> profesores = new ArrayList();
        profesores.add("-Selecciona-");
        profesores.addAll(facadeBO.consultarTodosProfesores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(profesores.toArray());
        integrantesComboBox.setModel(modeloComboBox);
    }
    
    private void llenarComboBoxProgramas(){
        List<Object> programas = new ArrayList();
        programas.add("-Selecciona-");
        programas.addAll(facadeBO.consultarTodosProgramas());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(programas.toArray());
        programasComboBox.setModel(modeloComboBox);
    }
    
    private void llenarComboBoxInvestigadores(){
        List<Object> investigadores = new ArrayList();
        investigadores.add("-Selecciona-");
        investigadores.addAll(facadeBO.consultarTodosInvestigadorDoctores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(investigadores.toArray());
        investigadoresComboBox.setModel(modeloComboBox);
    }
    
    private void llenarTablaLineas(){
        List<LineaInvestigacion> lineasInvestigacion = facadeBO.consultarTodosLineas();
        lineasInvestigacion.removeIf(linea -> this.lineasInvestigacion.contains(linea.getId()));
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaLineasInvestigacion.getModel();
        modeloTabla.setRowCount(0);
        
        Action agregar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                agregarLineaInvestigacion();

            }
        };
        
        lineasInvestigacion.forEach(linea -> {
            Object[] fila = new Object[5];
            fila[0] = linea.getId();
            fila[1] = linea.getCodigo();
            fila[2] = linea.getNombre();
            fila[3] = linea.getConjuntoDescriptores();
            fila[4] = "Agregar";

            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnAgregar = new ButtonColumn(tablaLineasInvestigacion, agregar, 3);
    }
    
    private void llenarTablaLineasProyecto(){
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaLineasProyecto.getModel();
        modeloTabla.setRowCount(0);
    
        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarLineaInvestigacion();
            }
        };

        lineasInvestigacion.forEach(idLinea -> {
            LineaInvestigacion linea = facadeBO.consultarLineas(idLinea);
            Object[] fila = new Object[5];
            fila[0] = linea.getId();
            fila[1] = linea.getCodigo();
            fila[2] = linea.getNombre();
            fila[3] = linea.getConjuntoDescriptores();
            fila[4] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaLineasProyecto, eliminar, 3);
    }
    
    private void llenarTablaIntegrantes(){
//        List<ProfesorProyectoDTO> integrantes = facadeBO.consultarIntegrantes(proyectoSeleccionado.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaIntegrantes.getModel();
        modeloTabla.setRowCount(0);
        
        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarIntegrante();
            }
        };
        
        Action editar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editarIntegrante();
            }
        };
        
        
        this.integrantesSeleccionados.forEach(periodoIntegrante ->{
            Profesor integrante = facadeBO.consultarProfesor(periodoIntegrante.getIdProfesor());

            Object[] fila = new Object[7];
            fila[0] = integrante.getId();
            fila[1] = integrante.getNombre();
            fila[2] = integrante.getApellidoPaterno();
            fila[3] = formatter.format(periodoIntegrante.getFechaInicio());
            fila[4] = formatter.format(periodoIntegrante.getFechaFin());
            fila[5] = "Editar";
            fila[6] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaIntegrantes, editar, 4);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaIntegrantes, eliminar, 5);
        
    }

    
    private ObjectId getIdSeleccionadoLineaInvestigacion(){
        int indiceFilaSeleccionada = this.tablaLineasInvestigacion.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaLineasInvestigacion.getModel();
            int indiceColumnaId = 0;
            ObjectId idLineaSeleccionada = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idLineaSeleccionada;
        } else {
            return null;
        }
    }
    
    private ObjectId getIdSeleccionadoLineaProyecto(){
        int indiceFilaSeleccionada = this.tablaLineasProyecto.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaLineasProyecto.getModel();
            int indiceColumnaId = 0;
            ObjectId idLineaSeleccionada = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idLineaSeleccionada;
        } else {
            return null;
        }
    }
    
    private ObjectId getIdSeleccionadoIntegrantes(){
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
    
    
    private void agregarLineaInvestigacion(){
        ObjectId idSeleccionado = this.getIdSeleccionadoLineaInvestigacion();
        this.lineasInvestigacion.add(idSeleccionado);
        this.llenarTablaLineas();
        this.llenarTablaLineasProyecto();
    }
    
    private void eliminarLineaInvestigacion(){
        ObjectId idSeleccionado = this.getIdSeleccionadoLineaProyecto();
        this.lineasInvestigacion.remove(idSeleccionado);
        this.llenarTablaLineas();
        this.llenarTablaLineasProyecto();
    }
    
    private void agregarIntegrante(){
        if(!validarCamposVaciosIntegrantes()){
            JOptionPane.showMessageDialog(this, "No se permiten campos vácios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Profesor profesor = (Profesor) integrantesComboBox.getSelectedItem();
        Date fechaInicio = Date.from(this.inicioParticipacionTxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(this.finParticipacionTxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PeriodoParticipacion periodoParticipacion = new PeriodoParticipacion(profesor.getId(), fechaInicio, fechaFin);
        if(!facadeBO.validarPeriodoFechasIntegrante(periodoParticipacion)){
            JOptionPane.showMessageDialog(this, "No se introdujo un periodo válido", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.integrantesSeleccionados.add(periodoParticipacion);
        JOptionPane.showMessageDialog(this, "Se agregó correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
        this.llenarTablaIntegrantes();
        this.vaciarIntegrantesForm();
    }
    
    private boolean validarCamposVaciosIntegrantes(){
        return !(integrantesComboBox.getSelectedIndex() == 0 || inicioParticipacionTxt.getDate() == null || finParticipacionTxt.getDate() == null);
    }
    
    private void vaciarIntegrantesForm(){
        integrantesComboBox.setSelectedIndex(0);
        inicioParticipacionTxt.setDate(null);
        finParticipacionTxt.setDate(null);
        this.editarIntegrante = null;
        agregarIntegranteBtn.setText("Agregar Integrante");
    }
    
    
    private void editarIntegrante(){
        Profesor profesorSeleccionado = facadeBO.consultarProfesor(this.getIdSeleccionadoIntegrantes());
        this.editarIntegrante = this.tablaIntegrantes.getSelectedRow();
        integrantesComboBox.setSelectedItem(profesorSeleccionado);
        DefaultTableModel modelo = (DefaultTableModel) this.tablaIntegrantes.getModel();
        String fechaInicio = (String) modelo.getValueAt(editarIntegrante, 3);
        String fechaFin = (String) modelo.getValueAt(editarIntegrante, 4);
        
        String[] inicioSplit = fechaInicio.split("/");
        String[] finSplit = fechaFin.split("/");

        this.inicioParticipacionTxt.setDate(LocalDate.of(Integer.parseInt(inicioSplit[2]), Integer.parseInt(inicioSplit[1]), Integer.parseInt(inicioSplit[0]))); //Despliega la fecha del Inicio en el DatePicker
        this.finParticipacionTxt.setDate(LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]))); //Despliega la fecha fin en el DatePicker
        this.editarIntegrante = this.tablaIntegrantes.getSelectedRow();
        this.agregarIntegranteBtn.setText("Editar Integrante");
        
        
    }
    
    
    private void actualizarIntegrante(){
          if(!validarCamposVaciosIntegrantes()){
            JOptionPane.showMessageDialog(this, "No se permiten campos vácios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Profesor profesor = (Profesor) integrantesComboBox.getSelectedItem();
        Date fechaInicio = Date.from(this.inicioParticipacionTxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(this.finParticipacionTxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PeriodoParticipacion periodoParticipacion = new PeriodoParticipacion(profesor.getId(), fechaInicio, fechaFin);
        if(!facadeBO.validarPeriodoFechasIntegrante(periodoParticipacion)){
            JOptionPane.showMessageDialog(this, "No se introdujo un periodo válido", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.integrantesSeleccionados.set(this.editarIntegrante, periodoParticipacion);
        JOptionPane.showMessageDialog(this, "Se editó correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
        this.llenarTablaIntegrantes();
        this.vaciarIntegrantesForm();
    }
    
    
    private void eliminarIntegrante(){
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el integrante seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcionSeleccionada == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (opcionSeleccionada == JOptionPane.NO_OPTION) {
            return;
        }
        int indiceFilaSeleccionada = this.tablaIntegrantes.getSelectedRow();
        this.integrantesSeleccionados.remove(indiceFilaSeleccionada);
        this.llenarTablaIntegrantes();
    }
    
    
    private void actualizarProyecto(){
        if (!validarCamposVacios()) {
            JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.validarLineasSeleccionadas()){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna Linea de Investigación", "información", JOptionPane.ERROR_MESSAGE);
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
        Programa programa = (Programa) programasComboBox.getSelectedItem();
        InvestigadorDoctor investigadorDoctor = (InvestigadorDoctor) investigadoresComboBox.getSelectedItem();
        Date fechaInicio = Date.from(this.inicioPicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(this.finPicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String descripcion = descripcionTxt.getText();
        
        
        proyecto = new Proyecto(proyectoSeleccionado.getId(), codigoReferencia, nombreProyecto, acronimo, presupuesto, programa.getId(), patrocinador, fechaInicio, fechaFin, descripcion, investigadorDoctor, this.integrantesSeleccionados, this.lineasInvestigacion);

        try {
            facadeBO.actualizarProyecto(proyecto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
            JOptionPane.showMessageDialog(this, "Se modificó el proyecto correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        this.frameProveniente.llenarAcronimoComboBox();
        this.frameProveniente.llenarCodigoComboBox();
        this.frameProveniente.llenarNombreComboBox();
        this.frameProveniente.buscar();
    }
    
    private boolean validarCamposVacios(){
        return !(codigoTxt.getText().equals("") || nombreTxt.getText().equals("") || acronimoTxt.getText().equals("") || presupuestoTxt.getText().equals("") ||
                programasComboBox.getSelectedIndex() == 0 || patrocinadorTxt.getText().equals("") || investigadoresComboBox.getSelectedIndex() == 0 || inicioPicker.getDate() == null ||
                finPicker.getDate() == null || descripcionTxt.getText().equals(""));
    }
    
    private boolean validarLineasSeleccionadas(){
        return !this.lineasInvestigacion.isEmpty();
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
        return this.programasComboBox.getSelectedIndex() != 0;
    }
    
    private boolean validarSeleccionInvestigadorPrincipal(){
        return this.investigadoresComboBox.getSelectedIndex() != 0;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreLbl = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        codigoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        acronimoTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        presupuestoTxt = new javax.swing.JTextField();
        programaLbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionTxt = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        patrocinadorTxt = new javax.swing.JTextField();
        actualizarBtn = new javax.swing.JButton();
        lineasIntegrantesTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLineasInvestigacion = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLineasProyecto = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaIntegrantes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        integrantesComboBox = new javax.swing.JComboBox<>();
        integranteLbl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        inicioParticipacionTxt = new com.github.lgooddatepicker.components.DatePicker();
        finParticipacionTxt = new com.github.lgooddatepicker.components.DatePicker();
        agregarIntegranteBtn = new javax.swing.JButton();
        cancelaIBtn = new javax.swing.JButton();
        inicioPicker = new com.github.lgooddatepicker.components.DatePicker();
        finPicker = new com.github.lgooddatepicker.components.DatePicker();
        cancelarBtn = new javax.swing.JButton();
        programasComboBox = new javax.swing.JComboBox<>();
        investigadoresComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ver Proyecto");

        jLabel1.setText("Ver Proyecto");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        nombreLbl.setText("Nombre:");
        nombreLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        nombreTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setText("Código de Referencia:");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        codigoTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setText("Acrónimo:");
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        acronimoTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setText("Presupuesto:");
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        presupuestoTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        presupuestoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                presupuestoTxtKeyTyped(evt);
            }
        });

        programaLbl.setText("Programa:");
        programaLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel5.setText("Fecha de Inicio:");
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel6.setText("Fecha de Fin:");
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel7.setText("Descripción:");
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        descripcionTxt.setColumns(20);
        descripcionTxt.setEditable(false);
        descripcionTxt.setRows(5);
        jScrollPane1.setViewportView(descripcionTxt);

        jLabel10.setText("Patrocinador:");
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        patrocinadorTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        actualizarBtn.setText("Realizar Cambios");
        actualizarBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        actualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarBtnActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaLineasInvestigacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descriptores", "Agregar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLineasInvestigacion.setRowHeight(30);
        jScrollPane2.setViewportView(tablaLineasInvestigacion);

        tablaLineasProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descriptores", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLineasProyecto.setRowHeight(30);
        jScrollPane3.setViewportView(tablaLineasProyecto);

        jLabel8.setText("Lineas de Investigación:");

        jLabel9.setText("Lineas de Investigación del Proyecto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        lineasIntegrantesTab.addTab("Lineas de Investigación", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaIntegrantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido Paterno", "Inicio Participación", "Fin Participación", "Editar", "Eliminar"
            }
        ));
        tablaIntegrantes.setRowHeight(30);
        jScrollPane4.setViewportView(tablaIntegrantes);

        jLabel11.setText("Integrantes del Proyecto");

        integrantesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        integrantesComboBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        integranteLbl.setText("Integrante:");
        integranteLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel12.setText("Inicio Participación:");
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel13.setText("Fin de Participación:");
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        agregarIntegranteBtn.setText("Agregar Integrante");
        agregarIntegranteBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarIntegranteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarIntegranteBtnActionPerformed(evt);
            }
        });

        cancelaIBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelaIBtn.setText("Cancelar");
        cancelaIBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaIBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(integranteLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(integrantesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inicioParticipacionTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(finParticipacionTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(171, 171, 171))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(agregarIntegranteBtn)
                        .addGap(205, 205, 205)
                        .addComponent(cancelaIBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(integrantesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(integranteLbl))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(inicioParticipacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(finParticipacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarIntegranteBtn)
                    .addComponent(cancelaIBtn))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        lineasIntegrantesTab.addTab("Integrantes", jPanel4);

        cancelarBtn.setText("Cancelar");
        cancelarBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        programasComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        programasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        investigadoresComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        investigadoresComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Investigador Principal:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(527, 527, 527)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(nombreLbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(acronimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(programaLbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(programasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(actualizarBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(investigadoresComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(finPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inicioPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(83, 83, 83)))
                        .addComponent(lineasIntegrantesTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lineasIntegrantesTab, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 149, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreLbl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(acronimoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(programasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(programaLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(investigadoresComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(inicioPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actualizarBtn)
                            .addComponent(cancelarBtn))
                        .addGap(44, 44, 44))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarIntegranteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarIntegranteBtnActionPerformed
        if(this.editarIntegrante != null){
            this.actualizarIntegrante();
            return;
        }
        
        this.agregarIntegrante();
    }//GEN-LAST:event_agregarIntegranteBtnActionPerformed

    private void cancelaIBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaIBtnActionPerformed
        this.vaciarIntegrantesForm();
    }//GEN-LAST:event_cancelaIBtnActionPerformed

    private void presupuestoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presupuestoTxtKeyTyped
        char c = evt.getKeyChar();
       
       if((c < '0' || c>'9') && (c > '.')){
           evt.consume();
       }
    }//GEN-LAST:event_presupuestoTxtKeyTyped

    private void actualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarBtnActionPerformed
        this.actualizarProyecto();
    }//GEN-LAST:event_actualizarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acronimoTxt;
    private javax.swing.JButton actualizarBtn;
    private javax.swing.JButton agregarIntegranteBtn;
    private javax.swing.JButton cancelaIBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JTextArea descripcionTxt;
    private com.github.lgooddatepicker.components.DatePicker finParticipacionTxt;
    private com.github.lgooddatepicker.components.DatePicker finPicker;
    private com.github.lgooddatepicker.components.DatePicker inicioParticipacionTxt;
    private com.github.lgooddatepicker.components.DatePicker inicioPicker;
    private javax.swing.JLabel integranteLbl;
    private javax.swing.JComboBox<String> integrantesComboBox;
    private javax.swing.JComboBox<String> investigadoresComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTabbedPane lineasIntegrantesTab;
    private javax.swing.JLabel nombreLbl;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField patrocinadorTxt;
    private javax.swing.JTextField presupuestoTxt;
    private javax.swing.JLabel programaLbl;
    private javax.swing.JComboBox<String> programasComboBox;
    private javax.swing.JTable tablaIntegrantes;
    private javax.swing.JTable tablaLineasInvestigacion;
    private javax.swing.JTable tablaLineasProyecto;
    // End of variables declaration//GEN-END:variables
}
