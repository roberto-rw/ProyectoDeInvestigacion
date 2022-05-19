
package GUIS;

import entidades.Autor;
import entidades.Profesor;
import entidades.PublicacionCongreso;
import entidades.PublicacionRevista;
import enums.TipoPublicacionCongreso;
import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;
import utils.ButtonColumn;


public class AgregarPublicacionForm extends javax.swing.JFrame {
    RegistrarPublicacionForm pantallaRegistrarPublicacion;
    IFacadeBO fachadaBO;
    List<Profesor> autoresAgregados;
    ObjectId idProyecto;

    public AgregarPublicacionForm(ObjectId idProyecto) {
        initComponents();
        autoresAgregados = new ArrayList();
        fachadaBO = new FacadeBO();
        this.idProyecto = idProyecto;
        this.llenarComboBoxAutores();
        this.bloquearTodo();
        this.llenarComboBoxTipo();
        
        //Tabla Autores
        TableColumnModel modeloColumnasAutores = this.tablaAutoresAgregados.getColumnModel();
        modeloColumnasAutores.removeColumn(modeloColumnasAutores.getColumn(0));
        
    }
    

    private void llenarComboBoxTipo(){
        List<Object> obj = new ArrayList();
        obj.add("-Selecciona-");
        obj.addAll(Arrays.asList(TipoPublicacionCongreso.values()));
        
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(obj.toArray());
        comboBoxTipoCongreso.setModel(modeloComboBox);  
    }
    
    private void bloquearTodo(){
        this.campoNumeroSecuencia.setEnabled(false);
        this.campoTitulo.setEnabled(false);
        this.textAreaAutores.setEnabled(false);
        this.comboBoxProfesores.setEnabled(false);
        this.botonAgregarAutor.setEnabled(false);
        
        //Campos Congreso
        this.campoNombreCongreso.setEnabled(false);
        this.comboBoxTipoCongreso.setEnabled(false);
        this.campoFechaInicio.setEnabled(false);
        this.campoFechaFin.setEnabled(false);
        this.campoLugarCelebracion.setEnabled(false);
        this.campoPais.setEnabled(false);
        this.campoEditorialCongreso.setEnabled(false);
        
        //Campos Revista
        this.campoNombreRevista.setEnabled(false);
        this.campoEditorialRevista.setEnabled(false);
        this.campoVolumen.setEnabled(false);
        this.campoNumero.setEnabled(false);
        this.campoPagInicio.setEnabled(false);
        this.campoPagFin.setEnabled(false);   
        
        this.botonGuardar.setEnabled(false);
    }
    
    private void llenarComboBoxAutores(){       
        List<Object> profesores = new ArrayList();
        profesores.add("-Selecciona-");
        profesores.addAll(fachadaBO.consultarTodosProfesores());
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(profesores.toArray());
        comboBoxProfesores.setModel(modeloComboBox);  
    }
    
    private void llenarTablaAutores(){
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaAutoresAgregados.getModel();
        modeloTabla.setRowCount(0);
        
        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarIntegrante();
            }
        };

        this.autoresAgregados.forEach(autor -> {
            
            Object[] fila = new Object[7];
            fila[0] = autor.getId();
            fila[1] = autor.getNombre();
            fila[2] = autor.getApellidoPaterno();
            fila[3] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        
        ButtonColumn buttonColumnEliminar = new ButtonColumn(this.tablaAutoresAgregados, eliminar, 2);
    }
    
    
    private void eliminarIntegrante() {
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el autor seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcionSeleccionada == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (opcionSeleccionada == JOptionPane.NO_OPTION) {
            return;
        }
        int indiceFilaSeleccionada = this.tablaAutoresAgregados.getSelectedRow();
        this.autoresAgregados.remove(indiceFilaSeleccionada);
        this.llenarTablaAutores();
        this.llenarTextArea();
    }
    
    private void llenarTextArea(){
        String autores = "";
        
        for (Profesor autor: this.autoresAgregados) {
                
                autores += autor.getNombre() + " " + autor.getApellidoPaterno() + ", ";
        }
       
        this.textAreaAutores.setText(autores);
    }
    
    private void validarCongreso(){
        
    }
    
    private void validarRevista(){
        
    }
    
    public boolean validarCamposVaciosRevista(){
        return !(this.campoNumeroSecuencia.getText().equals("") 
           || this.campoTitulo.getText().equals("")
           || this.autoresAgregados.isEmpty()
           || this.campoNombreRevista.getText().equals("")
           || this.campoEditorialRevista.getText().equals("")
           || this.campoVolumen.getText().equals("")
           || this.campoNumero.getText().equals("")
           || this.campoPagInicio.getText().equals("")
           || this.campoPagFin.getText().equals(""));
    }
    
    private boolean validarCamposVaciosCongreso(){
        return !(this.campoNumeroSecuencia.getText().equals("") 
           || this.campoTitulo.getText().equals("")
           || this.autoresAgregados.isEmpty()
           || this.campoNombreCongreso.getText().equals("")
           || this.comboBoxTipoCongreso.getSelectedIndex() == 0
           || !(this.validarCamposVaciosFechas())
           || this.campoLugarCelebracion.getText().equals("")
           || this.campoPais.getText().equals("")
           || this.campoEditorialCongreso.getText().equals(""));      
    }
    
    private boolean validarCamposVaciosFechas(){
        return !(this.campoFechaInicio.getDate() == null || this.campoFechaFin.getDate() == null);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        campoTitulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoNumeroSecuencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaAutores = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        comboBoxProfesores = new javax.swing.JComboBox<>();
        panelTablaAutoresSeleccionados = new javax.swing.JScrollPane();
        tablaAutoresAgregados = new javax.swing.JTable();
        botonAgregarAutor = new javax.swing.JButton();
        radioCongreso = new javax.swing.JRadioButton();
        radioRevista = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        panelPublicacionCongreso = new javax.swing.JPanel();
        campoNombreCongreso = new javax.swing.JTextField();
        comboBoxTipoCongreso = new javax.swing.JComboBox<>();
        fechaILbl = new javax.swing.JLabel();
        campoFechaInicio = new com.github.lgooddatepicker.components.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        campoFechaFin = new com.github.lgooddatepicker.components.DatePicker();
        campoLugarCelebracion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        campoPais = new javax.swing.JTextField();
        campoEditorialCongreso = new javax.swing.JTextField();
        panelPublicacionRevista = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        campoNombreRevista = new javax.swing.JTextField();
        campoEditorialRevista = new javax.swing.JTextField();
        campoVolumen = new javax.swing.JTextField();
        campoNumero = new javax.swing.JTextField();
        campoPagInicio = new javax.swing.JTextField();
        campoPagFin = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Publicación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Agregar Publicación");
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel2.setText("Titulo:");
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        campoNumeroSecuencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNumeroSecuenciaKeyTyped(evt);
            }
        });

        jLabel3.setText("Número en Secuencia:");
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        textAreaAutores.setEditable(false);
        textAreaAutores.setColumns(20);
        textAreaAutores.setRows(5);
        jScrollPane1.setViewportView(textAreaAutores);

        jLabel4.setText("Autores:");
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        comboBoxProfesores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaAutoresAgregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido Paterno", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAutoresAgregados.setRowHeight(30);
        panelTablaAutoresSeleccionados.setViewportView(tablaAutoresAgregados);

        botonAgregarAutor.setText("Agregar Autor");
        botonAgregarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarAutorActionPerformed(evt);
            }
        });

        grupo.add(radioCongreso);
        radioCongreso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        radioCongreso.setText("Congreso");
        radioCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCongresoActionPerformed(evt);
            }
        });

        grupo.add(radioRevista);
        radioRevista.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        radioRevista.setText("Revista");
        radioRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRevistaActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo de Publicación:");
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        panelPublicacionCongreso.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboBoxTipoCongreso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fechaILbl.setText("Inicio:");
        fechaILbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel6.setText("Fin:");
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        campoLugarCelebracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoLugarCelebracionActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre del Congreso:");
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel8.setText("Tipo:");
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel9.setText("Lugar de Celebración:");
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel10.setText("Editorial:");
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel11.setText("País:");
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        campoPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPaisActionPerformed(evt);
            }
        });

        campoEditorialCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEditorialCongresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPublicacionCongresoLayout = new javax.swing.GroupLayout(panelPublicacionCongreso);
        panelPublicacionCongreso.setLayout(panelPublicacionCongresoLayout);
        panelPublicacionCongresoLayout.setHorizontalGroup(
            panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                        .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPublicacionCongresoLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoEditorialCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPais, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                        .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPublicacionCongresoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fechaILbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(campoFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoNombreCongreso)
                                    .addComponent(campoLugarCelebracion, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(comboBoxTipoCongreso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelPublicacionCongresoLayout.setVerticalGroup(
            panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicacionCongresoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNombreCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTipoCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(33, 33, 33)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaILbl))
                .addGap(21, 21, 21)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(campoFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoLugarCelebracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(panelPublicacionCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campoEditorialCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPublicacionRevista.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Nombre de la Revista:");
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel13.setText("Volumen:");
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel14.setText("Editorial:");
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel15.setText("Número:");
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel16.setText("Página de Inicio:");
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel17.setText("Página de Fin:");
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        campoVolumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoVolumenKeyTyped(evt);
            }
        });

        campoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNumeroActionPerformed(evt);
            }
        });
        campoNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNumeroKeyTyped(evt);
            }
        });

        campoPagInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPagInicioKeyTyped(evt);
            }
        });

        campoPagFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPagFinKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelPublicacionRevistaLayout = new javax.swing.GroupLayout(panelPublicacionRevista);
        panelPublicacionRevista.setLayout(panelPublicacionRevistaLayout);
        panelPublicacionRevistaLayout.setHorizontalGroup(
            panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicacionRevistaLayout.createSequentialGroup()
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPublicacionRevistaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel13)))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPublicacionRevistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)))
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoPagInicio)
                    .addComponent(campoNombreRevista)
                    .addComponent(campoEditorialRevista)
                    .addComponent(campoVolumen)
                    .addComponent(campoNumero)
                    .addComponent(campoPagFin, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        panelPublicacionRevistaLayout.setVerticalGroup(
            panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicacionRevistaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(campoNombreRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(campoEditorialRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(campoVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPagInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(38, 38, 38)
                .addGroup(panelPublicacionRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(campoPagFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        botonGuardar.setText("Agregar Publicación");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonInicio.setText("Volver");
        botonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(panelPublicacionCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(panelPublicacionRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(471, 471, 471)
                            .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(531, 531, 531)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(campoTitulo))
                                .addGap(27, 27, 27)
                                .addComponent(comboBoxProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonAgregarAutor))
                        .addGap(18, 18, 18)
                        .addComponent(panelTablaAutoresSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioCongreso)
                        .addGap(18, 18, 18)
                        .addComponent(radioRevista)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoNumeroSecuencia)
                        .addGap(857, 857, 857))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCongreso)
                    .addComponent(jLabel5)
                    .addComponent(radioRevista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNumeroSecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(botonAgregarAutor))
                            .addComponent(panelTablaAutoresSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelPublicacionRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelPublicacionCongreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoLugarCelebracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoLugarCelebracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoLugarCelebracionActionPerformed

    private void campoPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPaisActionPerformed

    private void campoEditorialCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEditorialCongresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEditorialCongresoActionPerformed

    private void campoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNumeroActionPerformed

    private void botonAgregarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarAutorActionPerformed
        
        Profesor profesor = new Profesor();
        
        if(this.comboBoxProfesores.getSelectedIndex() != 0){
            profesor = (Profesor) this.comboBoxProfesores.getSelectedItem();
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un autor", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        if(this.autoresAgregados.contains(profesor)){
            JOptionPane.showMessageDialog(this, "El autor ya está en la lista", "información", JOptionPane.ERROR_MESSAGE);
        }else{
            this.autoresAgregados.add(profesor);
            JOptionPane.showMessageDialog(this, "Se agregó el autor", "información", JOptionPane.INFORMATION_MESSAGE);
        }
 
        this.llenarTablaAutores();
        this.llenarTextArea();
        
    }//GEN-LAST:event_botonAgregarAutorActionPerformed

    private void radioCongresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCongresoActionPerformed
            
            this.campoNumeroSecuencia.setEnabled(true);
            this.campoTitulo.setEnabled(true);
            this.textAreaAutores.setEnabled(true);
            this.comboBoxProfesores.setEnabled(true);
            this.botonAgregarAutor.setEnabled(true);
            
            //Campos Congreso
            this.campoNombreCongreso.setEnabled(true);
            this.comboBoxTipoCongreso.setEnabled(true);
            this.campoFechaInicio.setEnabled(true);
            this.campoFechaFin.setEnabled(true);
            this.campoLugarCelebracion.setEnabled(true);
            this.campoPais.setEnabled(true);
            this.campoEditorialCongreso.setEnabled(true);
            
            //Campos Revista
            this.campoNombreRevista.setEnabled(false);
            this.campoEditorialRevista.setEnabled(false);
            this.campoVolumen.setEnabled(false);
            this.campoNumero.setEnabled(false);
            this.campoPagInicio.setEnabled(false);
            this.campoPagFin.setEnabled(false);   
        
            //Botón Guardar
            this.botonGuardar.setEnabled(true);
            
    }//GEN-LAST:event_radioCongresoActionPerformed

    private void radioRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioRevistaActionPerformed
            this.campoNumeroSecuencia.setEnabled(true);
            this.campoTitulo.setEnabled(true);
            this.textAreaAutores.setEnabled(true);
            this.comboBoxProfesores.setEnabled(true);
            this.botonAgregarAutor.setEnabled(true);
            
            //Campos Congreso
            this.campoNombreCongreso.setEnabled(false);
            this.comboBoxTipoCongreso.setEnabled(false);
            this.campoFechaInicio.setEnabled(false);
            this.campoFechaFin.setEnabled(false);
            this.campoLugarCelebracion.setEnabled(false);
            this.campoPais.setEnabled(false);
            this.campoEditorialCongreso.setEnabled(false);
            
            //Campos Revista
            this.campoNombreRevista.setEnabled(true);
            this.campoEditorialRevista.setEnabled(true);
            this.campoVolumen.setEnabled(true);
            this.campoNumero.setEnabled(true);
            this.campoPagInicio.setEnabled(true);
            this.campoPagFin.setEnabled(true);   
        
            //Botón Guardar
            this.botonGuardar.setEnabled(true);
    }//GEN-LAST:event_radioRevistaActionPerformed

    private void guardarPublicacion(){
        if(this.radioCongreso.isSelected()){
            
            if(!(this.validarCamposVaciosCongreso())){
                JOptionPane.showMessageDialog(this, "No dejes campos vacíos", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //System.out.println(fachadaBO.estaRepetidoTituloPublicacion(this.campoTitulo.getText()));
            if(fachadaBO.estaRepetidoTituloPublicacion(this.campoTitulo.getText())){
                JOptionPane.showMessageDialog(this, "El titulo de la publicación ya existe", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<Autor> autores = new ArrayList();
       
            for (int i = 0; i < autoresAgregados.size(); i++) {
            autores.add(new Autor(autoresAgregados.get(i).getId(), i+1 ));
            }
            
            Long numeroSecuencia = Long.parseLong(this.campoNumeroSecuencia.getText());
            String titulo = this.campoTitulo.getText();
            String nombreCongreso = this.campoNombreCongreso.getText();
            TipoPublicacionCongreso tipo = (TipoPublicacionCongreso)this.comboBoxTipoCongreso.getSelectedItem();
            Calendar fechaInicioCalendar = Calendar.getInstance();
            Calendar fechaFinCalendar = Calendar.getInstance();
            String lugar = this.campoLugarCelebracion.getText();
            String pais = this.campoPais.getText();
            String editorial = this.campoEditorialCongreso.getText();
            
            
            fechaFinCalendar.set(campoFechaFin.getDate().getYear(), campoFechaFin.getDate().getMonthValue() - 1, campoFechaFin.getDate().getDayOfMonth(), 0, 0, 0);
            fechaInicioCalendar.set(campoFechaInicio.getDate().getYear(), campoFechaInicio.getDate().getMonthValue() - 1, campoFechaInicio.getDate().getDayOfMonth(), 0, 0, 0);
            
            
            
            PublicacionCongreso publicacionCongreso = new PublicacionCongreso(nombreCongreso,tipo,fechaInicioCalendar.getTime(),fechaFinCalendar.getTime()
                                                                        ,lugar,pais,editorial,numeroSecuencia,titulo,this.idProyecto);
            
            for (Autor autor: autores) {
                publicacionCongreso.addAutor(autor);
            }
            
            if(fachadaBO.agregarPublicacionCongreso(this.idProyecto, publicacionCongreso)){
                JOptionPane.showMessageDialog(this, "Se agregó la publicación", "información", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "No se pudo agregar la publicación", "Error", JOptionPane.ERROR);
            }
            
            
            
        }else{
            if(!(this.validarCamposVaciosRevista())){
            JOptionPane.showMessageDialog(this, "No dejes campos vacíos", "información", JOptionPane.ERROR_MESSAGE);
            return;
            }

            if(fachadaBO.estaRepetidoTituloPublicacion(this.campoTitulo.getText())){
                JOptionPane.showMessageDialog(this, "El titulo de la publicación ya existe", "información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<Autor> autores = new ArrayList();
       
            for (int i = 0; i < autoresAgregados.size(); i++) {
            autores.add(new Autor(autoresAgregados.get(i).getId(), i+1 ));
            }
            
            Long numeroSecuencia = Long.parseLong(this.campoNumeroSecuencia.getText());
            String titulo = this.campoTitulo.getText();
            String nombreRevista = this.campoNombreRevista.getText();
            String editorialRevista = this.campoEditorialRevista.getText();
            String volumen = this.campoVolumen.getText();
            String numero = this.campoNumero.getText();
            String pagInicio = this.campoPagInicio.getText();
            String pagFin = this.campoPagFin.getText();
            
            PublicacionRevista publicacionRevista = new PublicacionRevista(nombreRevista,editorialRevista, volumen, numero,  pagInicio, pagFin,
                                                                            numeroSecuencia, titulo, this.idProyecto);
            
            for (Autor autor: autores) {
                publicacionRevista.addAutor(autor);
            }
            
            if(fachadaBO.agregarPublicacionRevista(this.idProyecto, publicacionRevista)){
                JOptionPane.showMessageDialog(this, "Se agregó la publicación", "información", JOptionPane.INFORMATION_MESSAGE);
                this.vaciarForm();
            }else{
                JOptionPane.showMessageDialog(this, "No se pudo agregar la publicación", "Error", JOptionPane.ERROR);
            }
            
        }     
    }
    
    private void vaciarForm(){
        campoNumeroSecuencia.setText("");
        campoTitulo.setText("");
        textAreaAutores.setText("");
        comboBoxProfesores.setSelectedIndex(0);
        campoNombreCongreso.setText("");
        comboBoxTipoCongreso.setSelectedIndex(0);
        campoFechaInicio.setDate(null);
        campoFechaFin.setDate(null);
        campoLugarCelebracion.setText("");
        campoPais.setText("");
        campoEditorialCongreso.setText("");
        campoNombreRevista.setText("");
        campoEditorialRevista.setText("");
        campoVolumen.setText("");
        campoNumero.setText("");
        campoPagInicio.setText("");
        campoPagFin.setText("");
    }
    
    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        this.guardarPublicacion();
        
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioActionPerformed
        this.dispose();
        pantallaRegistrarPublicacion = new RegistrarPublicacionForm();
        pantallaRegistrarPublicacion.setVisible(true);
    }//GEN-LAST:event_botonInicioActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(pantallaRegistrarPublicacion==null){
           pantallaRegistrarPublicacion = new RegistrarPublicacionForm();
        }else{
            pantallaRegistrarPublicacion.setVisible(true);
        }
        pantallaRegistrarPublicacion.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void campoNumeroSecuenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumeroSecuenciaKeyTyped
       char c = evt.getKeyChar();
       
       if((c < '0' || c>'9')){
           evt.consume();
       }
    }//GEN-LAST:event_campoNumeroSecuenciaKeyTyped

    private void campoVolumenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoVolumenKeyTyped
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_campoVolumenKeyTyped

    private void campoNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumeroKeyTyped
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_campoNumeroKeyTyped

    private void campoPagInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPagInicioKeyTyped
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_campoPagInicioKeyTyped

    private void campoPagFinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPagFinKeyTyped
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_campoPagFinKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarAutor;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonInicio;
    private javax.swing.JTextField campoEditorialCongreso;
    private javax.swing.JTextField campoEditorialRevista;
    private com.github.lgooddatepicker.components.DatePicker campoFechaFin;
    private com.github.lgooddatepicker.components.DatePicker campoFechaInicio;
    private javax.swing.JTextField campoLugarCelebracion;
    private javax.swing.JTextField campoNombreCongreso;
    private javax.swing.JTextField campoNombreRevista;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoNumeroSecuencia;
    private javax.swing.JTextField campoPagFin;
    private javax.swing.JTextField campoPagInicio;
    private javax.swing.JTextField campoPais;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoVolumen;
    private javax.swing.JComboBox<String> comboBoxProfesores;
    private javax.swing.JComboBox<String> comboBoxTipoCongreso;
    private javax.swing.JLabel fechaILbl;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPublicacionCongreso;
    private javax.swing.JPanel panelPublicacionRevista;
    private javax.swing.JScrollPane panelTablaAutoresSeleccionados;
    private javax.swing.JRadioButton radioCongreso;
    private javax.swing.JRadioButton radioRevista;
    private javax.swing.JTable tablaAutoresAgregados;
    private javax.swing.JTextArea textAreaAutores;
    // End of variables declaration//GEN-END:variables
}
