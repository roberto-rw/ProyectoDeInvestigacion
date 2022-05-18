/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import entidades.InvestigadorDoctor;
import entidades.Programa;
import entidades.Proyecto;
import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utils.ButtonColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jegav
 */
public class BuscarProyecto extends javax.swing.JFrame {

    IFacadeBO fachadaBO;
    PrincipalForm pantallaPrincipal;
    /**
     * Creates new form BuscarProyecto
     */
    public BuscarProyecto() {
        initComponents();
        this.demasSeleccionado();
        this.fachadaBO = new FacadeBO();
         TableColumnModel modeloColumnasLineasInvestigacion = this.tablaProyectos.getColumnModel();
        tablaProyectos.removeColumn( modeloColumnasLineasInvestigacion.getColumn(0));
        
        
        this.llenarCodigoComboBox();
        this.llenarNombreComboBox();
        this.llenarAcronimoComboBox();
        AutoCompleteDecorator.decorate(codigoComboBox);
        AutoCompleteDecorator.decorate(nombreComboBox);
        AutoCompleteDecorator.decorate(acronimoComboBox);
        this.llenarProgramasComboBox();
        this.llenarComboBoxInvestigadorDoctor();
        AutoCompleteDecorator.decorate(programasComboBox);
        AutoCompleteDecorator.decorate(investigadorComboBox);
    }
    
    
    protected void llenarCodigoComboBox(){
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(fachadaBO.consultarCodigos().toArray());
        codigoComboBox.setModel(modeloComboBox);
    }
    
    protected void llenarNombreComboBox(){
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(fachadaBO.consultarNombres().toArray());
        
        nombreComboBox.setModel(modeloComboBox);
    }
    
    protected void llenarAcronimoComboBox(){
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(fachadaBO.consultarAcronimos().toArray());
        
        acronimoComboBox.setModel(modeloComboBox);
    }
    
    
    private void llenarProgramasComboBox(){
        List<Object> programas = new ArrayList();
        programas.add("-Selecciona-");
        programas.addAll(fachadaBO.consultarTodosProgramas());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(programas.toArray());
        
        programasComboBox.setModel(modeloComboBox);
    }
    
    
    private void llenarComboBoxInvestigadorDoctor(){
        List<Object> investigadoresDoctores = new ArrayList();
        investigadoresDoctores.add("-Selecciona-");
        investigadoresDoctores.addAll(fachadaBO.consultarTodosInvestigadorDoctores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(investigadoresDoctores.toArray());
        investigadorComboBox.setModel(modeloComboBox);
    }
    
    
    private ObjectId getIdProyectoSeleccionado(){
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
    
    
    private void llenarTablaResultados(List<Proyecto> resultados){
        Action editarProyecto = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                editarProyecto();
            }
        };
        
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaProyectos.getModel();
        modeloTabla.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        
        resultados.forEach(proyecto -> {
            Object[] fila = new Object[11];
            fila[0] = proyecto.getId();
            fila[1] = proyecto.getCodigoReferencia();
            fila[2] = proyecto.getNombre();
            fila[3] = proyecto.getAcronimo();
            fila[4] = formatter.format(proyecto.getFechaInicio());
            fila[5] = formatter.format(proyecto.getFechaFin());
            fila[6] = fachadaBO.consultarPrograma(proyecto.getIdPrograma());
            fila[7] = proyecto.getPresupuestoTotal();
            fila[8] = proyecto.getInvestigadorPrincipal();
            fila[9] = proyecto.getPatrocinador();
            fila[10] = "Editar";
            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnVer = new ButtonColumn(this.tablaProyectos, editarProyecto, 9);
    }
    
    private void editarProyecto(){
        EditarProyectoForm proyectoPantalla = EditarProyectoForm.getInstance(this.getIdProyectoSeleccionado(), this);
        proyectoPantalla.setVisible(true);
    }
    
    private void codigoSeleccionado(){
        codigoComboBox.setEnabled(true);
        nombreComboBox.setEnabled(false);
        acronimoComboBox.setEnabled(false);
        programasComboBox.setEnabled(false);
        presupuestoComboBox.setEnabled(false);
        presupuestoTxt.setEnabled(false);
        investigadorComboBox.setEnabled(false);
        patrocinadorTxt.setEnabled(false);
        fechaITxt.setEnabled(false);
        fechaFTxt.setEnabled(false);
    }
    
    private void nombreSeleccionado(){
        codigoComboBox.setEnabled(false);
        nombreComboBox.setEnabled(true);
        acronimoComboBox.setEnabled(false);
        programasComboBox.setEnabled(false);
        presupuestoComboBox.setEnabled(false);
        presupuestoTxt.setEnabled(false);
        investigadorComboBox.setEnabled(false);
        patrocinadorTxt.setEnabled(false);
        fechaITxt.setEnabled(false);
        fechaFTxt.setEnabled(false);
    }
    
    private void acronimoSeleccionado(){
        codigoComboBox.setEnabled(false);
        nombreComboBox.setEnabled(false);
        acronimoComboBox.setEnabled(true);
        programasComboBox.setEnabled(false);
        presupuestoComboBox.setEnabled(false);
        presupuestoTxt.setEnabled(false);
        investigadorComboBox.setEnabled(false);
        patrocinadorTxt.setEnabled(false);
        fechaITxt.setEnabled(false);
        fechaFTxt.setEnabled(false);
    }
    
    private void fechaSeleccionado(){
        codigoComboBox.setEnabled(false);
        nombreComboBox.setEnabled(false);
        acronimoComboBox.setEnabled(false);
        programasComboBox.setEnabled(false);
        presupuestoComboBox.setEnabled(false);
        presupuestoTxt.setEnabled(false);
        investigadorComboBox.setEnabled(false);
        patrocinadorTxt.setEnabled(false);
        fechaITxt.setEnabled(true);
        fechaFTxt.setEnabled(true);
    }
//    
    private void demasSeleccionado(){
        codigoComboBox.setEnabled(false);
        nombreComboBox.setEnabled(false);
        acronimoComboBox.setEnabled(false);
        programasComboBox.setEnabled(true);
        presupuestoComboBox.setEnabled(true);
        presupuestoTxt.setEnabled(true);
        investigadorComboBox.setEnabled(true);
        patrocinadorTxt.setEnabled(true);
        fechaITxt.setEnabled(false);
        fechaFTxt.setEnabled(false);
    }
    
    
    protected void buscar(){
        List<Proyecto> resultados;
        if(porCodigo.isSelected()){ //Por Cógigo
            
            resultados = new ArrayList();
            Proyecto proyecto = fachadaBO.consultarPorCodigo((String) codigoComboBox.getSelectedItem());
            
            resultados.add(proyecto);
            
        } else if(porNombre.isSelected()){ //Por Nombre
            
            resultados = new ArrayList();
            Proyecto proyecto = fachadaBO.consultarPorNombre((String) nombreComboBox.getSelectedItem());
            resultados.add(proyecto);
            
        } else if(porAcronimo.isSelected()){ //Por Acrónimo
            
            resultados = new ArrayList();
            Proyecto proyecto = fachadaBO.consultarPorAcronimo((String) acronimoComboBox.getSelectedItem());
            resultados.add(proyecto);
            
        } else if(porFecha.isSelected()){ //Por Fecha
            if(!validarCamposVaciosFechas()){
                JOptionPane.showMessageDialog(this, "Es necesario llenar ambas fechas", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!validarFechas()){
                JOptionPane.showMessageDialog(this, "Las fechas Introducidas son inválidas", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fechaInicio = Date.from(fechaITxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaFin = Date.from(fechaFTxt.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            
             
            resultados = fachadaBO.consultarPorFechas(fechaInicio, fechaFin);
            
        } else{ //Por Demas
            if(!validarCamposVaciosDemas()){
                JOptionPane.showMessageDialog(this, "Es necesario llenar al menos un campo", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ObjectId idPrograma = this.getIdPrograma();
            Float presupuesto = null;
            String patrocinador = null;
            if(validarFiltroPresupuesto()){
                JOptionPane.showMessageDialog(this, "Se deben llenar ambos campos del Presupuesto", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!presupuestoTxt.getText().equals("") && presupuestoComboBox.getSelectedIndex() >0){
                if(!validarFormatoPresupuesto()){
                    JOptionPane.showMessageDialog(this, "El presupuesto introducido no es válido", "información", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                presupuesto = Float.parseFloat(presupuestoTxt.getText());
            }
            
            if(!patrocinadorTxt.getText().equals("")){
                patrocinador = patrocinadorTxt.getText();
            }
            resultados = fachadaBO.consultarPorCaracteristicas(idPrograma, presupuesto, presupuestoComboBox.getSelectedIndex(), this.getInvestigador(), patrocinador);
            
        }
        
        if (resultados == null){
            JOptionPane.showMessageDialog(this, "No hay Resultados", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.llenarTablaResultados(resultados);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcionesBusqueda = new javax.swing.ButtonGroup();
        contenedor = new javax.swing.JPanel();
        porCodigo = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        codigoComboBox = new javax.swing.JComboBox<>();
        porNombre = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        nombreComboBox = new javax.swing.JComboBox<>();
        porFecha = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        fechaITxt = new com.github.lgooddatepicker.components.DatePicker();
        fechaFTxt = new com.github.lgooddatepicker.components.DatePicker();
        fechaILbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        porAcronimo = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        acronimoComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        porDemas = new javax.swing.JRadioButton();
        programaLbl = new javax.swing.JLabel();
        programasComboBox = new javax.swing.JComboBox<>();
        presupuestoLbl = new javax.swing.JLabel();
        presupuestoComboBox = new javax.swing.JComboBox<>();
        presupuestoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        investigadorComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        patrocinadorTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        buscarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        botonInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Proyecto");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        opcionesBusqueda.add(porCodigo);
        porCodigo.setText("Buscar por Codigo de Referencia");
        porCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porCodigoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Coloque un Código de Referencia"));

        codigoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        codigoComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codigoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(codigoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        opcionesBusqueda.add(porNombre);
        porNombre.setText("Buscar por Nombre");
        porNombre.setToolTipText("");
        porNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porNombreActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Coloque el nombre del Proyecto"));

        nombreComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        nombreComboBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(nombreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        opcionesBusqueda.add(porFecha);
        porFecha.setText("Buscar  por Fecha");
        porFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porFechaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Coloque un Rango de Fechas"));

        fechaILbl.setText("Inicio:");
        fechaILbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel1.setText("Fin:");
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaILbl)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaFTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(fechaITxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaITxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaILbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaFTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        opcionesBusqueda.add(porAcronimo);
        porAcronimo.setText("Buscar por Acronimo");
        porAcronimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porAcronimoActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Coloque el Acronimo del Proyecto"));

        acronimoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        acronimoComboBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acronimoComboBox, 0, 294, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(acronimoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel2.setText("Buscar Proyecto");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        opcionesBusqueda.add(porDemas);
        porDemas.setSelected(true);
        porDemas.setText("Buscar por Demas Características");
        porDemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porDemasActionPerformed(evt);
            }
        });

        programaLbl.setText("Programa:");
        programaLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        programasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        presupuestoLbl.setText("Presupuesto:");
        presupuestoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        presupuestoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecciona-", "Mayor que", "Menor que", "Igual a" }));

        presupuestoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                presupuestoTxtKeyTyped(evt);
            }
        });

        jLabel3.setText("$");
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel4.setText("Investigador Principal:");
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        investigadorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Patrocinador");
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Acrónimo", "FechaI", "FechaF", "Programa", "Presupuesto", "Inv Principal", "Patrocinador", "Ver"
            }
        ));
        tablaProyectos.setRowHeight(30);
        jScrollPane2.setViewportView(tablaProyectos);

        buscarBtn.setText("Buscar");
        buscarBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        botonInicio.setText("Inicio");
        botonInicio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonInicio.setToolTipText("");
        botonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(porAcronimo)
                    .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(porFecha)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(porNombre)
                        .addComponent(porCodigo)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cancelarBtn)
                        .addGap(35, 35, 35)
                        .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(porDemas))
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addComponent(programaLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(programasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addComponent(presupuestoLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(presupuestoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(investigadorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel2))))
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addComponent(porCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(porNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(porAcronimo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addComponent(porDemas)
                        .addGap(18, 18, 18)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(programaLbl)
                            .addComponent(programasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(presupuestoLbl)
                            .addComponent(presupuestoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(presupuestoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(investigadorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(patrocinadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBtn)
                    .addComponent(cancelarBtn)
                    .addComponent(botonInicio))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void porCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porCodigoActionPerformed
       this.codigoSeleccionado();
    }//GEN-LAST:event_porCodigoActionPerformed

    private void porNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porNombreActionPerformed
        this.nombreSeleccionado();
    }//GEN-LAST:event_porNombreActionPerformed

    private void porAcronimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porAcronimoActionPerformed
        this.acronimoSeleccionado();
    }//GEN-LAST:event_porAcronimoActionPerformed

    private void porFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porFechaActionPerformed
        this.fechaSeleccionado();
    }//GEN-LAST:event_porFechaActionPerformed

    private void porDemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porDemasActionPerformed
        this.demasSeleccionado();
    }//GEN-LAST:event_porDemasActionPerformed

    private void presupuestoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presupuestoTxtKeyTyped
       char c = evt.getKeyChar();
       
       if((c < '0' || c>'9') && (c > '.')){
           evt.consume();
       }
    }//GEN-LAST:event_presupuestoTxtKeyTyped

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        this.buscar();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        vaciarForm();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void botonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioActionPerformed
        this.dispose();
        pantallaPrincipal = new PrincipalForm();
        pantallaPrincipal.setVisible(true);        
    }//GEN-LAST:event_botonInicioActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(pantallaPrincipal==null){
           pantallaPrincipal = new PrincipalForm();
        }else{
            pantallaPrincipal.setVisible(true);
        }
        
        pantallaPrincipal.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    
    private void vaciarForm(){
        porDemas.setSelected(true);
        this.demasSeleccionado();
        this.programasComboBox.setSelectedIndex(0);
        this.presupuestoComboBox.setSelectedIndex(0);
        this.presupuestoTxt.setText("");
        this.investigadorComboBox.setSelectedIndex(0);
        this.patrocinadorTxt.setText("");
        this.codigoComboBox.setSelectedIndex(0);
        this.nombreComboBox.setSelectedIndex(0);
        this.acronimoComboBox.setSelectedIndex(0);
        this.fechaITxt.setDate(null);
        this.fechaFTxt.setDate(null);
    }
    
    private ObjectId getIdPrograma(){
        return (this.programasComboBox.getSelectedIndex() == 0)? null: ((Programa) this.programasComboBox.getSelectedItem()).getId();
    }
    
    private InvestigadorDoctor getInvestigador(){
        return (this.investigadorComboBox.getSelectedIndex() == 0)? null: ((InvestigadorDoctor) this.investigadorComboBox.getSelectedItem());
    }
    
    
    private boolean validarCamposVaciosDemas(){
        return !(this.programasComboBox.getSelectedIndex() == 0 
                && presupuestoComboBox.getSelectedIndex() == 0 && presupuestoTxt.getText().equals("") 
                && investigadorComboBox.getSelectedIndex() == 0
                && patrocinadorTxt.getText().equals(""));
    }
    
    private boolean validarFormatoPresupuesto(){
        try{
            Float presupuesto = Float.parseFloat(this.presupuestoTxt.getText());
            return presupuesto > 0;
        } catch(NumberFormatException nfe){
            return false;
        }
    }
    
    private boolean validarFiltroPresupuesto(){
        Integer filtroPresupuesto = presupuestoComboBox.getSelectedIndex();
        return (presupuestoTxt.getText().equals("") && filtroPresupuesto != 0) || (filtroPresupuesto == 0 && !presupuestoTxt.getText().equals(""));
    }
    
    private boolean validarFechas(){
        return !(fechaITxt.getDate().compareTo(fechaFTxt.getDate()) >= 0);
    }
    
    private boolean validarCamposVaciosFechas(){
 
        return !(fechaITxt.getDate() == null || fechaFTxt.getDate() == null);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> acronimoComboBox;
    private javax.swing.JButton botonInicio;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JComboBox<String> codigoComboBox;
    private javax.swing.JPanel contenedor;
    private com.github.lgooddatepicker.components.DatePicker fechaFTxt;
    private javax.swing.JLabel fechaILbl;
    private com.github.lgooddatepicker.components.DatePicker fechaITxt;
    private javax.swing.JComboBox<String> investigadorComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> nombreComboBox;
    private javax.swing.ButtonGroup opcionesBusqueda;
    private javax.swing.JTextField patrocinadorTxt;
    private javax.swing.JRadioButton porAcronimo;
    private javax.swing.JRadioButton porCodigo;
    private javax.swing.JRadioButton porDemas;
    private javax.swing.JRadioButton porFecha;
    private javax.swing.JRadioButton porNombre;
    private javax.swing.JComboBox<String> presupuestoComboBox;
    private javax.swing.JLabel presupuestoLbl;
    private javax.swing.JTextField presupuestoTxt;
    private javax.swing.JLabel programaLbl;
    private javax.swing.JComboBox<String> programasComboBox;
    private javax.swing.JTable tablaProyectos;
    // End of variables declaration//GEN-END:variables
}
