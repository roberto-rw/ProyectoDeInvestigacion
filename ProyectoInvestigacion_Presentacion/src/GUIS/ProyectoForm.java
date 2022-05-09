/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import entidades.DetalleProyectoProfesor;
import entidades.InvestigadorDoctor;
import entidades.LineaInvestigacion;
import entidades.PeriodoParticipacion;
import entidades.Profesor;
import entidades.Programa;
import entidades.Proyecto;
import implementacionesBO.BOSFactory;
import interfacesBO.IDoctoresBO;
import interfacesBO.ILineaInvestigacionBO;
import interfacesBO.INoDoctoresBO;
import interfacesBO.IProgramasBO;
import interfacesBO.IProyectoBO;
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
import org.bson.types.ObjectId;
import utils.ButtonColumn;

/**
 *
 * @author pc
 */
public class ProyectoForm extends javax.swing.JFrame {

    private IDoctoresBO doctoresBO;
    private INoDoctoresBO noDoctoresBO;
    private ILineaInvestigacionBO lineaInvestigacion;
    private IProgramasBO programasBO;
    private IProyectoBO proyectoBO;
    private List<Profesor> integrantesASeleccionar;
    private Integer editarIntegrante;
    private List<PeriodoParticipacion> periodosIntegrantes;
    
    /**
     * Creates new form ProyectoForm
     */
    public ProyectoForm() {
        initComponents();
        this.doctoresBO = BOSFactory.crearDoctoresBO();
        this.noDoctoresBO = BOSFactory.crearNoDoctoresBO();
        
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
        List<Profesor> profesores = new ArrayList();
        profesores.addAll(doctoresBO.consultarTodos());
        profesores.addAll(noDoctoresBO.consultarTodos());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(profesores.toArray());
        integrantesComboBox.setModel(modeloComboBox);
    }
    
    private void llenarComboBoxProgramas(){
        List<Programa> programas = new ArrayList();
        programas.addAll(programasBO.consultarTodos());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(programas.toArray());
        programaComboBox.setModel(modeloComboBox);
    }
    
    private void llenarComboBoxInvestigadorDoctor(){
        List<InvestigadorDoctor> doctores = new ArrayList();
        doctores.addAll(doctoresBO.consultarTodosInvestigadores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(doctores.toArray());
        investigadorDoctorComboBox.setModel(modeloComboBox);
    }
    
    private boolean validarCamposVaciosIntegrantes() {
        return this.finSPicker.getDate() == null || this.inicioSPicker.getDate() == null;
    }
    
    private boolean validarFechasIntegrantes() {
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.set(inicioSPicker.getDate().getYear(), inicioSPicker.getDate().getMonthValue() - 1, inicioSPicker.getDate().getDayOfMonth(), 0, 0, 0);
        Calendar fechaFinCalendar = Calendar.getInstance();
        fechaFinCalendar.set(finSPicker.getDate().getYear(), finSPicker.getDate().getMonthValue() - 1, finSPicker.getDate().getDayOfMonth(), 0, 0, 0);
        return !(fechaInicioCalendar.compareTo(fechaFinCalendar) < 0);
    }
    
    private boolean validarFechasProyecto(){
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.set(fechaInicioPicker.getDate().getYear(), fechaInicioPicker.getDate().getMonthValue() - 1, fechaInicioPicker.getDate().getDayOfMonth(), 0, 0, 0);
        Calendar fechaFinCalendar = Calendar.getInstance();
        fechaFinCalendar.set(fechaFinPicker.getDate().getYear(), fechaFinPicker.getDate().getMonthValue() - 1, fechaFinPicker.getDate().getDayOfMonth(), 0, 0, 0);
        return !(fechaInicioCalendar.compareTo(fechaFinCalendar) < 0);
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

    private void editarParticipacion() {
        Profesor profesorSeleccionado = doctoresBO.consultar(this.getIdIntegranteSeleccionado());
        if(profesorSeleccionado == null){
            profesorSeleccionado = noDoctoresBO.consultar(this.getIdIntegranteSeleccionado());
        }
        this.integrantesComboBox.setSelectedItem(profesorSeleccionado);

        int indiceFilaSeleccionada = this.tablaIntegrantes.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) this.tablaIntegrantes.getModel();
        String fechaInicio = (String) modelo.getValueAt(indiceFilaSeleccionada, 3);
        String fechaFin = (String) modelo.getValueAt(indiceFilaSeleccionada, 4);

        String[] inicioSplit = fechaInicio.split("/");
        String[] finSplit = fechaFin.split("/");

        this.fechaInicioPicker.setDate(LocalDate.of(Integer.parseInt(inicioSplit[2]), Integer.parseInt(inicioSplit[1]), Integer.parseInt(inicioSplit[0]))); //Despliega la fecha del Inicio en el DatePicker
        this.finSPicker.setDate(LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]))); //Despliega la fecha fin en el DatePicker
        this.editarIntegrante = this.tablaIntegrantes.getSelectedRow();
        this.agregarParticipacionBtn.setText("Editar Integrante");
    }
    
    private void eliminarSupervision() {
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
                eliminarSupervision();
            }
        };
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        this.periodosIntegrantes.forEach(periodo -> {
           Profesor integrante = doctoresBO.consultar(periodo.getIdProfesor());
           if(integrante == null){
               integrante = noDoctoresBO.consultar(periodo.getIdProfesor());
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
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaIntegrantes, editar, 5);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaIntegrantes, eliminar, 6);
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
        proyectos.forEach(linea -> {
            Object[] fila = new Object[10];
            fila[0] = linea.getId();
            fila[1] = linea.getNombre();
            fila[2] = linea.getAcronimo();
            fila[3] = linea.getPresupuestoTotal();
            fila[4] = linea.getIdPrograma();
            fila[5] = linea.getPatrocinador();
            fila[6] = linea.getFechaInicio();
            fila[7] = linea.getFechaFin();
            fila[8] = linea.getDescripcion();
            fila[9] = linea.getInvestigadorPrincipal();
            modeloTabla.addRow(fila);
        });
    }
    
    private boolean validarLineasSeleccionadas(){
        return this.lineaInvestigacionTabla.getSelectedRows().length == 0;
    }
    
    private boolean validarCamposVacios() {
        if(codigoTxt.getText().equals("") || this.nombreTxt.getText().equals("") || this.acronimoTxt.getText().equals("") || this.patrocinadorTxt.getText().equals("") 
        || this.presupuestoTxt.getText().equals("") || this.descripciontxta.getText().equals("")){
            return true;
        }
        else if(this.integrantesComboBox.getSelectedItem()==null){
            return true;
        }
        return false;
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
    
      private List<DetalleProyectoProfesor> getIntegrantesSeleccionados() {
        List<DetalleProyectoProfesor> integrantesSeleccionados = new ArrayList();
        

        DefaultTableModel modelo = (DefaultTableModel) this.tablaIntegrantes.getModel();
        for (int i = 0; i<this.tablaIntegrantes.getRowCount(); i++) {
            ObjectId integrantes = (ObjectId) modelo.getValueAt(i, 0);
            String fechaInicio = (String) modelo.getValueAt(i, 3);
            String fechaFin = (String) modelo.getValueAt(i, 4);
            String[] fechaInicioSplit = fechaInicio.split("/");
            String[] fechaFinSplit = fechaFin.split("/");
            Calendar fechaI = Calendar.getInstance();
            fechaI.set(Integer.parseInt(fechaInicioSplit[2]), Integer.parseInt(fechaInicioSplit[1]), Integer.parseInt(fechaInicioSplit[0]));
            Calendar fechaF = Calendar.getInstance();
            fechaI.set(Integer.parseInt(fechaFinSplit[2]), Integer.parseInt(fechaFinSplit[1]), Integer.parseInt(fechaFinSplit[0]));
            DetalleProyectoProfesor dpp = new DetalleProyectoProfesor(integrantes, fechaI.getTime(), fechaF.getTime());
            integrantesSeleccionados.add(dpp);
        }
        return integrantesSeleccionados;
    }
    
    private void guardar(){
        if (validarCamposVacios()) {
            JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         if(this.validarLineasSeleccionadas()){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna linea", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (this.validarFechasProyecto()) {
            JOptionPane.showMessageDialog(this, "Las fechas colcadas no son válidas", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String codigoReferencia = codigoTxt.getText();
        String nombreProfesor = nombreTxt.getText();
        String acronimo = acronimoTxt.getText();
        Float presupuesto = Float.parseFloat(presupuestoTxt.getText());
        String patrocinador = patrocinadorTxt.getText();
        Programa programa = (Programa) programaComboBox.getSelectedItem();
        InvestigadorDoctor investigadorDoctor = (InvestigadorDoctor) investigadorDoctorComboBox.getSelectedItem();
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.set(fechaInicioPicker.getDate().getYear(), fechaInicioPicker.getDate().getMonthValue() - 1, fechaInicioPicker.getDate().getDayOfMonth(), 0, 0, 0);
        Calendar fechaFinCalendar = Calendar.getInstance();
        fechaFinCalendar.set(fechaFinPicker.getDate().getYear(), fechaFinPicker.getDate().getMonthValue() - 1, fechaFinPicker.getDate().getDayOfMonth(), 0, 0, 0);
        String descripcion = descripciontxta.getText();
        
        List<ObjectId> lineasSeleccionadas = this.getLineasInvestigacionSeleccionadas();
        List<DetalleProyectoProfesor> integrantesSeleccionados = this.getIntegrantesSeleccionados();
        boolean seAgregoProyecto = false;
        Proyecto proyecto = new Proyecto(codigoReferencia, nombreProfesor, acronimo, presupuesto, programa.getId(), patrocinador, fechaInicioCalendar.getTime(), fechaFinCalendar.getTime(), descripcion, investigadorDoctor, integrantesSeleccionados, lineasSeleccionadas);
        if(proyectoBO.agregar(proyecto)){
            seAgregoProyecto = true;
        }
        if (seAgregoProyecto) {
            JOptionPane.showMessageDialog(this, "Se agregó el proyecto correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
            this.llenarTablaProyectos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el Proyecto", "información", JOptionPane.INFORMATION_MESSAGE);
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
        periodosIntegrantes = new ArrayList();
        this.codigoTxt.setText("");
        this.nombreTxt.setText("");
        this.acronimoTxt.setText("");
        this.programaComboBox.setSelectedIndex(0);
        this.presupuestoTxt.setText("");
        this.patrocinadorTxt.setText("");
        this.investigadorDoctorComboBox.setSelectedIndex(0);
        this.descripciontxta.setText("");
        this.llenarTablaLineasInvestigacion();
        this.llenarTablaProyectos();
        this.btnGuardarProyecto.setText("Guardar Proyecto");

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
        descripciontxta = new javax.swing.JTextArea();
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
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lineaInvestigacionTabla = new javax.swing.JTable();
        patrocinadorLbl1 = new javax.swing.JLabel();
        investigadorDoctorComboBox = new javax.swing.JComboBox<>();
        fechaInicioPicker = new com.github.lgooddatepicker.components.DatePicker();
        fechaFinPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        programaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        presupuestoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                presupuestoTxtKeyPressed(evt);
            }
        });

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Acronimo", "Presupuesto", "Programa", "Patrocinador", "Fecha de inicio", "Fecha de fin", "Descripción", "Investigador Principal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaProyectos);

        descripciontxta.setColumns(20);
        descripciontxta.setRows(5);
        jScrollPane6.setViewportView(descripciontxta);

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaIntegrantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido Paterno", "Inicio Participación", "Fin Participación", "Editar", "Eliminar"
            }
        ));
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

        jLabel5.setText("Participante");
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

        jLabel6.setText("Agregar Integrante:");

        lineaInvestigacionTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descriptores"
            }
        ));
        jScrollPane2.setViewportView(lineaInvestigacionTabla);

        patrocinadorLbl1.setText("Investigador Doctor: ");
        patrocinadorLbl1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        investigadorDoctorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Lineas de Investigación:");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addGap(0, 92, Short.MAX_VALUE)
                                        .addComponent(btnGuardarProyecto)
                                        .addGap(192, 192, 192)
                                        .addComponent(jButton2)))
                                .addGap(332, 332, 332))
                            .addGroup(layout.createSequentialGroup()
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
                                        .addComponent(investigadorDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(574, 574, 574)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89))))
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
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProyecto)
                            .addComponent(jButton2))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void presupuestoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presupuestoTxtKeyPressed

    }//GEN-LAST:event_presupuestoTxtKeyPressed

    private void agregarParticipacionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarParticipacionBtnActionPerformed

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
    private javax.swing.JTextArea descripciontxta;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
