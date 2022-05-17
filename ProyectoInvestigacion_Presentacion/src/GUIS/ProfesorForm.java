/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;


import entidades.Doctor;
import entidades.InvestigadorDoctor;
import entidades.InvestigadorNoDoctor;
import entidades.LineaInvestigacion;
import entidades.NoDoctor;
import entidades.PeriodoSupervision;
import entidades.Profesor;
import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
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

/**
 *
 * @author jegav
 */
public class ProfesorForm extends javax.swing.JFrame {
    

    PrincipalForm pantallaPrincipal;
    IFacadeBO fachadaBO;
    private List<PeriodoSupervision> periodosSupervision;
    private Integer editarSupervisor;
    private ObjectId editar;

    /**
     * Creates new form ProfesorForm
     */
    public ProfesorForm() {
        initComponents();
        this.fachadaBO = new FacadeBO();
        this.editarSupervisor = null;
        this.editar = null;
        this.periodosSupervision = new ArrayList();
        this.llenarComboBoxSupervisores();
        
        //Profesores
        TableColumnModel modeloColumnasProfesor = this.profesoresTabla.getColumnModel();
        profesoresTabla.removeColumn( modeloColumnasProfesor.getColumn(0));
        this.llenarTablaProfesores();
        
        //LineasInvestigacion
        TableColumnModel modeloColumnasLineas = this.lineaInvestigacionTabla.getColumnModel();
        lineaInvestigacionTabla.removeColumn( modeloColumnasLineas.getColumn(0));
        this.llenarTablaLineasInvestigacion();
        
        //Supervisores
        TableColumnModel modeloColumnasSupervisor = this.tablaSupervisores.getColumnModel();
        tablaSupervisores.removeColumn( modeloColumnasSupervisor.getColumn(0));
        
    }
    
    private void llenarComboBoxSupervisores(){
        DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(fachadaBO.consultarTodosDoctores().toArray());
        supervisoresComboBox.setModel(modeloComboBox);
    }
    
    private void llenarTablaProfesores(){
        
        Action editar = new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
                editar();
            }
        };
        
        Action eliminar = new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
                eliminar();
            }
        };
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.profesoresTabla.getModel();
        modeloTabla.setRowCount(0);
        List<Profesor> profesores = fachadaBO.consultarTodosProfesores();
        profesores.forEach(profesor -> {
            Object[] fila = new Object[10];
            fila[0] = profesor.getId();
            fila[1] = profesor.getNombre();
            fila[2] = profesor.getApellidoMaterno();
            fila[3] = profesor.getApellidoPaterno();
            fila[4] = profesor.getDespacho();
            fila[5] = profesor.getTelefono();
            
            if(profesor.getClass() == Doctor.class){
                fila[6] = true; //Doctor
            } else {
                fila[6] = false; //Doctor
            } 
            if(fachadaBO.esInvestigador(profesor.getId())){
                fila[7] = true; 
            } else{
                fila[7] = false; 
            }
            
            fila[8] = "Editar";
            fila[9] = "Eliminar";
            modeloTabla.addRow(fila); 
        });

        ButtonColumn buttonColumnEditar = new ButtonColumn(profesoresTabla, editar, 7);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(profesoresTabla, eliminar, 8);
    }
    
     private void llenarTablaLineasInvestigacion(){
         List<LineaInvestigacion> lineasInvestigacion = fachadaBO.consultarTodosLineas();
         DefaultTableModel modeloTabla = (DefaultTableModel)this.lineaInvestigacionTabla.getModel();
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
     
     private void llenarTablaSupervisores(){
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tablaSupervisores.getModel();
        modeloTabla.setRowCount(0);
        Action editar = new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
                editarSupervision();
            }
        };
        
        Action eliminar = new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
                eliminarSupervision();
            }
        };
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        this.periodosSupervision.forEach(periodo ->{
            Doctor supervisor = fachadaBO.consultarDoctorProfesor(periodo.getIdSupervisor());
            Object[] fila = new Object[7];
            fila[0] = periodo.getIdSupervisor();
            fila[1] = supervisor.getNombre();
            fila[2] = supervisor.getTelefono();
            fila[3] = formatter.format(periodo.getFechaInicio());
            fila[4] = formatter.format(periodo.getFechaFin());
            fila[5] = "Editar";
            fila[6] = "Eliminar";
            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaSupervisores, editar, 4);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaSupervisores, eliminar, 5);
     }
    
     //getSeleecionados
     private ObjectId getIdProfesorSeleccionado(){
            int indiceFilaSeleccionada = this.profesoresTabla.getSelectedRow();
        if(indiceFilaSeleccionada != -1){
            DefaultTableModel modelo = (DefaultTableModel)this.profesoresTabla.getModel();
            int indiceColumnaId = 0;
            ObjectId idProfesorSeleccionado = (ObjectId)modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idProfesorSeleccionado;
        }
        else{
            return null;
        }
    }
     
     private ObjectId getIdSupervisorSeleccionado(){
            int indiceFilaSeleccionada = this.tablaSupervisores.getSelectedRow();
        if(indiceFilaSeleccionada != -1){
            DefaultTableModel modelo = (DefaultTableModel)this.tablaSupervisores.getModel();
            int indiceColumnaId = 0;
            ObjectId idSupervisorSeleccionado = (ObjectId)modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
//            System.out.println(idProductoSeleccionado);
            return idSupervisorSeleccionado;
        }
        else{
            return null;
        }
    }
     
     
     //Validaciones
     private boolean validarCamposVacios(){
         return nombreTxt.getText().equals("") || this.paternoTxt.getText().equals("")|| this.maternoTxt.getText().equals("") || this.despachoTxt.getText().equals("") || this.telefonoTxt.getText().equals("");
     }
     
     private boolean validarCamposVaciosSupervisores(){
         return this.finSPicker.getDate() == null || this.inicioSPicker.getDate() == null;
     }
     
     private boolean validarFechas(){
         Calendar fechaInicioCalendar = Calendar.getInstance();
         fechaInicioCalendar.set(inicioSPicker.getDate().getYear(), inicioSPicker.getDate().getMonthValue()-1, inicioSPicker.getDate().getDayOfMonth(), 0, 0, 0);
         Calendar fechaFinCalendar = Calendar.getInstance();
         fechaFinCalendar.set(finSPicker.getDate().getYear(), finSPicker.getDate().getMonthValue()-1, finSPicker.getDate().getDayOfMonth(), 0, 0, 0);
         return !(fechaInicioCalendar.compareTo(fechaFinCalendar) < 0);
     
     }
     
     private void eliminarSupervision(){
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el supervisor seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if(opcionSeleccionada == JOptionPane.CLOSED_OPTION){
            return;
        }
        
        if(opcionSeleccionada  == JOptionPane.NO_OPTION){
            return;
        }
        int indiceFilaSeleccionada = this.tablaSupervisores.getSelectedRow();
        this.periodosSupervision.remove(indiceFilaSeleccionada);
        this.llenarTablaSupervisores();
        
     }
     
     
     private void eliminar(){
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Está seguro de querer eliminar el profesor seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if(opcionSeleccionada == JOptionPane.CLOSED_OPTION){
            return;
        }
        
        if(opcionSeleccionada  == JOptionPane.NO_OPTION){
            return;
        }
        try {
            fachadaBO.eliminarProfesor(this.getIdProfesorSeleccionado());
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        JOptionPane.showMessageDialog(this, "Se eliminó el profesor correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
        this.llenarTablaProfesores();
     }
     
     
     private void editarSupervision(){
         Doctor doctorSeleccionado = fachadaBO.consultarDoctorProfesor(this.getIdSupervisorSeleccionado());
         this.supervisoresComboBox.setSelectedItem(doctorSeleccionado);
         
         int indiceFilaSeleccionada = this.tablaSupervisores.getSelectedRow();
         DefaultTableModel modelo = (DefaultTableModel)this.tablaSupervisores.getModel();
         String fechaInicio = (String) modelo.getValueAt(indiceFilaSeleccionada, 3);
         String fechaFin = (String) modelo.getValueAt(indiceFilaSeleccionada, 4);
         
         String[] inicioSplit = fechaInicio.split("/");
         String[] finSplit = fechaFin.split("/");
    
         this.inicioSPicker.setDate(LocalDate.of(Integer.parseInt(inicioSplit[2]), Integer.parseInt(inicioSplit[1]), Integer.parseInt(inicioSplit[0]))); //Despliega la fecha del Inicio en el DatePicker
         this.finSPicker.setDate(LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]))); //Despliega la fecha fin en el DatePicker
         this.editarSupervisor =this.tablaSupervisores.getSelectedRow();
         this.agregarSupervisorBtn.setText("Editar Supervisor");
         
     }
     
     private void editar(){
         this.vaciarForm();
         int indiceFilaSeleccionada = this.profesoresTabla.getSelectedRow();
         DefaultTableModel modelo = (DefaultTableModel)this.profesoresTabla.getModel();
         boolean esDoctorSeleccionado = (Boolean)modelo.getValueAt(indiceFilaSeleccionada, 6);
         boolean esInvestigadorSeleccionado = (Boolean)modelo.getValueAt(indiceFilaSeleccionada, 7);
         ObjectId idSeleccionado = this.getIdProfesorSeleccionado();
         if(esDoctorSeleccionado){
  
                Doctor profesorSeleccionado = fachadaBO.consultarDoctorProfesor(idSeleccionado);
                nombreTxt.setText(profesorSeleccionado.getNombre());
                maternoTxt.setText(profesorSeleccionado.getApellidoMaterno());
                paternoTxt.setText(profesorSeleccionado.getApellidoPaterno());
                telefonoTxt.setText(profesorSeleccionado.getTelefono());
                despachoTxt.setText(profesorSeleccionado.getDespacho());
                esDoctor.setSelected(true);
                this.mostrarLineasInvestigacionSeleccionadas(profesorSeleccionado.getIdsLineasInvestigacion());
                 
         } else{
              NoDoctor profesorSeleccionado = fachadaBO.consultarNoDoctor(idSeleccionado);
              nombreTxt.setText(profesorSeleccionado.getNombre());
              maternoTxt.setText(profesorSeleccionado.getApellidoMaterno());
              paternoTxt.setText(profesorSeleccionado.getApellidoPaterno());
              telefonoTxt.setText(profesorSeleccionado.getTelefono());
              despachoTxt.setText(profesorSeleccionado.getDespacho());
              periodosSupervision = profesorSeleccionado.getSupervisiones();
              this.llenarTablaSupervisores();
              this.mostrarLineasInvestigacionSeleccionadas(profesorSeleccionado.getIdsLineasInvestigacion());
             
         }
         
         if(esInvestigadorSeleccionado) esInvestigador.setSelected(true);
         botonGuardar.setText("Editar Proyecto");
         this.editar = this.getIdProfesorSeleccionado();
         esDoctor.setEnabled(false);
         esInvestigador.setEnabled(false);
     }
     
     
     private void mostrarLineasInvestigacionSeleccionadas(List<ObjectId> lineasInvestigacion){
         DefaultTableModel modelo = (DefaultTableModel)this.lineaInvestigacionTabla.getModel();
            int indiceColumnaId = 0;
            //ObjectId idProfesorSeleccionado = (ObjectId)modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            
            for (int i = 0; i < modelo.getRowCount(); i++) {
                ObjectId idProfesorSeleccionado = (ObjectId)modelo.getValueAt(i, indiceColumnaId);
                if(lineasInvestigacion.contains(idProfesorSeleccionado)) this.lineaInvestigacionTabla.getSelectionModel().addSelectionInterval(i, i);
            }
     }
     
     
     private void vaciarSupervisionesForm(){
         this.supervisoresComboBox.setSelectedIndex(0);
         inicioSPicker.setDate(null);
         finSPicker.setDate(null);
         this.editarSupervisor = null;
         this.agregarSupervisorBtn.setText("Agregar Supervisor");
         
     }
     
     private void vaciarForm(){
         this.vaciarSupervisionesForm();
         periodosSupervision = new ArrayList();
         esDoctor.setSelected(false);
         esInvestigador.setSelected(false);
         esDoctor.setEnabled(true);
         esInvestigador.setEnabled(true);
         nombreTxt.setText("");
         paternoTxt.setText("");
         maternoTxt.setText("");
         telefonoTxt.setText("");
         despachoTxt.setText("");
         this.llenarTablaLineasInvestigacion();
         this.llenarTablaSupervisores();
         botonGuardar.setText("Guardar Proyecto");
         this.editar = null;
         
     }
     
     
     private void agregar(){
         if(validarCamposVacios()){
             JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
             return;
         }
         
         
         String nombreProfesor = nombreTxt.getText();
         String aPaterno = paternoTxt.getText();
         String aMaterno = maternoTxt.getText();
         String despacho = despachoTxt.getText();
         String telefono = telefonoTxt.getText();
         List<ObjectId> lineasSeleccionadas  = this.getLineasInvestigacionSeleccionadas();
         boolean seAgregoProfesor = false;
         if(esDoctor.isSelected()){
             if(esInvestigador.isSelected()){
                 InvestigadorDoctor iDoctor = new InvestigadorDoctor(nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 iDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(iDoctor);
             } else{
                 Doctor doctor = new Doctor(nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 doctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(doctor);
             }
         } else{
             if(esInvestigador.isSelected()){
                 InvestigadorNoDoctor iNoDoctor = new InvestigadorNoDoctor(nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 iNoDoctor.setSupervisiones(periodosSupervision);
                 iNoDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(iNoDoctor);
             } else{
                 NoDoctor noDoctor = new NoDoctor(nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 noDoctor.setSupervisiones(periodosSupervision);
                 noDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(noDoctor);
             }
         }
         if(seAgregoProfesor){
             JOptionPane.showMessageDialog(this, "Se agregó el Profesor correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
             this.llenarTablaProfesores();
         } else{
             JOptionPane.showMessageDialog(this, "No se pudo agregar el Profesor", "información", JOptionPane.INFORMATION_MESSAGE);
         }
         vaciarForm();

     }
     
     private void actualizar(){
         if(validarCamposVacios()){
             JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
             return;
         }
         
         String nombreProfesor = nombreTxt.getText();
         String aPaterno = paternoTxt.getText();
         String aMaterno = maternoTxt.getText();
         String despacho = despachoTxt.getText();
         String telefono = telefonoTxt.getText();
         List<ObjectId> lineasSeleccionadas  = this.getLineasInvestigacionSeleccionadas();
         boolean seAgregoProfesor = false;
         if(esDoctor.isSelected()){
             if(esInvestigador.isSelected()){
                 InvestigadorDoctor iDoctor = new InvestigadorDoctor(editar, nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 iDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(iDoctor);
             } else{
                 Doctor doctor = new Doctor(editar, nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 doctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(doctor);
             }
         } else{
             if(esInvestigador.isSelected()){
                 InvestigadorNoDoctor iNoDoctor = new InvestigadorNoDoctor(editar, nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 iNoDoctor.setSupervisiones(periodosSupervision);
                 iNoDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(iNoDoctor);
             } else{
                 NoDoctor noDoctor = new NoDoctor(editar, nombreProfesor, aMaterno, aPaterno, despacho, telefono);
                 noDoctor.setSupervisiones(periodosSupervision);
                 noDoctor.setIdsLineasInvestigacion(lineasSeleccionadas);
                 seAgregoProfesor = fachadaBO.agregarProfesor(noDoctor);
             }
         }
         if(seAgregoProfesor){
             JOptionPane.showMessageDialog(this, "Se modificó el Profesor correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
             this.llenarTablaProfesores();
         } else{
             JOptionPane.showMessageDialog(this, "No se pudo modificar el Profesor", "información", JOptionPane.INFORMATION_MESSAGE);
         }
         vaciarForm();

     }
     
     
     private List<ObjectId> getLineasInvestigacionSeleccionadas(){
         List<ObjectId> lineasSeleccionadas = new ArrayList();
         int[] filas = lineaInvestigacionTabla.getSelectedRows();
     
         
         DefaultTableModel modelo = (DefaultTableModel)this.lineaInvestigacionTabla.getModel();
         for(int fila: filas){
             ObjectId idLineaInvestigacion = (ObjectId)modelo.getValueAt(fila, 0);
             lineasSeleccionadas.add(idLineaInvestigacion);
         }
         return lineasSeleccionadas;
     }
    
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorProfesorForm = new javax.swing.JPanel();
        nombreLbl = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        ProfesoresScroll = new javax.swing.JScrollPane();
        profesoresTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        paternoLbl = new javax.swing.JLabel();
        paternoTxt = new javax.swing.JTextField();
        maternoLbl = new javax.swing.JLabel();
        maternoTxt = new javax.swing.JTextField();
        despachoLbl = new javax.swing.JLabel();
        despachoTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        telefonoTxt = new javax.swing.JTextField();
        noDoctorPnl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSupervisores = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        inicioSPicker = new com.github.lgooddatepicker.components.DatePicker();
        finSPicker = new com.github.lgooddatepicker.components.DatePicker();
        inicioSLbl = new javax.swing.JLabel();
        finSLbl = new javax.swing.JLabel();
        supervisoresComboBox = new javax.swing.JComboBox<>();
        supervisorLbl = new javax.swing.JLabel();
        agregarSupervisorBtn = new javax.swing.JButton();
        cancelarSupervisionBtn = new javax.swing.JButton();
        esDoctor = new javax.swing.JCheckBox();
        esInvestigador = new javax.swing.JCheckBox();
        tituloLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lineaInvestigacionTabla = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profesores");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        nombreLbl.setText("Nombre:");
        nombreLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        nombreTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTxtActionPerformed(evt);
            }
        });
        nombreTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreTxtKeyTyped(evt);
            }
        });

        profesoresTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido Mat", "Apellido Pat", "Despacho", "Telefono", "Doctor?", "Investigador?", "Editar", "Eliminar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        profesoresTabla.setRowHeight(30);
        ProfesoresScroll.setViewportView(profesoresTabla);

        jLabel1.setText("Profesores:");

        paternoLbl.setText("Apellido Paterno:");
        paternoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        paternoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paternoTxtKeyTyped(evt);
            }
        });

        maternoLbl.setText("Apellido Materno:");
        maternoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        maternoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maternoTxtKeyTyped(evt);
            }
        });

        despachoLbl.setText("Despacho:");
        despachoLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel2.setText("Telefono:");
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        telefonoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoTxtKeyTyped(evt);
            }
        });

        noDoctorPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaSupervisores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Telefono", "Fecha Inicio", "Fecha Fin", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSupervisores.setRowHeight(30);
        jScrollPane2.setViewportView(tablaSupervisores);

        jLabel4.setText("Supervisores");

        inicioSLbl.setText("InicioSupervision:");
        inicioSLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        finSLbl.setText("Fin Supervision:");
        finSLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        supervisoresComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        supervisorLbl.setText("Supervisor:");
        supervisorLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        agregarSupervisorBtn.setText("Agregar Supervisor");
        agregarSupervisorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSupervisorBtnActionPerformed(evt);
            }
        });

        cancelarSupervisionBtn.setText("Cancelar");
        cancelarSupervisionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarSupervisionBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout noDoctorPnlLayout = new javax.swing.GroupLayout(noDoctorPnl);
        noDoctorPnl.setLayout(noDoctorPnlLayout);
        noDoctorPnlLayout.setHorizontalGroup(
            noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noDoctorPnlLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(noDoctorPnlLayout.createSequentialGroup()
                        .addComponent(finSLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(noDoctorPnlLayout.createSequentialGroup()
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inicioSLbl)
                            .addComponent(supervisorLbl))
                        .addGap(18, 18, 18)
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supervisoresComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inicioSPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(noDoctorPnlLayout.createSequentialGroup()
                        .addComponent(agregarSupervisorBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarSupervisionBtn)))
                .addGap(67, 67, 67)
                .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        noDoctorPnlLayout.setVerticalGroup(
            noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noDoctorPnlLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(noDoctorPnlLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(noDoctorPnlLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(supervisoresComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supervisorLbl))
                        .addGap(18, 18, 18)
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inicioSLbl)
                            .addComponent(inicioSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finSPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finSLbl))
                        .addGap(32, 32, 32)
                        .addGroup(noDoctorPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(agregarSupervisorBtn)
                            .addComponent(cancelarSupervisionBtn))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        esDoctor.setText("Doctor?");
        esDoctor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                esDoctorStateChanged(evt);
            }
        });

        esInvestigador.setText("Investigador?");

        tituloLbl.setText("Profesores");
        tituloLbl.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout contenedorProfesorFormLayout = new javax.swing.GroupLayout(contenedorProfesorForm);
        contenedorProfesorForm.setLayout(contenedorProfesorFormLayout);
        contenedorProfesorFormLayout.setHorizontalGroup(
            contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorProfesorFormLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noDoctorPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contenedorProfesorFormLayout.createSequentialGroup()
                        .addComponent(esDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(esInvestigador))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorProfesorFormLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nombreLbl)
                    .addComponent(paternoLbl)
                    .addComponent(maternoLbl)
                    .addComponent(despachoLbl)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreTxt)
                    .addComponent(paternoTxt)
                    .addComponent(maternoTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(despachoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(ProfesoresScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorProfesorFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloLbl)
                .addGap(137, 137, 137))
        );
        contenedorProfesorFormLayout.setVerticalGroup(
            contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorProfesorFormLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(tituloLbl)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorProfesorFormLayout.createSequentialGroup()
                        .addComponent(ProfesoresScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(esDoctor)
                            .addComponent(esInvestigador)))
                    .addGroup(contenedorProfesorFormLayout.createSequentialGroup()
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreLbl))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(paternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paternoLbl))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maternoLbl))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(despachoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(despachoLbl))
                        .addGap(18, 18, 18)
                        .addGroup(contenedorProfesorFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(noDoctorPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        lineaInvestigacionTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Nombre", "Descriptores"
            }
        ));
        jScrollPane1.setViewportView(lineaInvestigacionTabla);

        jLabel3.setText("Selecciona Lineas de Investigación");

        botonGuardar.setText("Guardar Profesor");
        botonGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonCancelar.setToolTipText("");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contenedorProfesorForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(botonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(volverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contenedorProfesorForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar)
                    .addComponent(volverBtn))
                .addGap(43, 43, 43))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void esDoctorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_esDoctorStateChanged
        if(esDoctor.isSelected()){
//            this.noDoctorPnl.setVisible(false);
           agregarSupervisorBtn.setEnabled(false);
           supervisoresComboBox.setEnabled(false);
           finSPicker.setEnabled(false);
           inicioSPicker.setEnabled(false);
           tablaSupervisores.setEnabled(false);
           this.cancelarSupervisionBtn.setEnabled(false);
        } else{
//            this.noDoctorPnl.setVisible(true);
            agregarSupervisorBtn.setEnabled(true);
           supervisoresComboBox.setEnabled(true);
           finSPicker.setEnabled(true);
           inicioSPicker.setEnabled(true);
           tablaSupervisores.setEnabled(true);
           this.cancelarSupervisionBtn.setEnabled(true);
        }
    }//GEN-LAST:event_esDoctorStateChanged

    private void telefonoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoTxtKeyTyped
       char c = evt.getKeyChar();
       
       if(c < '0' || c>'9'){
           evt.consume();
       }
       
    }//GEN-LAST:event_telefonoTxtKeyTyped

    private void nombreTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTxtKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c>'z') && (c < 'A' || c>'Z') && (c > ' ') ){
           evt.consume();
        }
    }//GEN-LAST:event_nombreTxtKeyTyped

    private void paternoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paternoTxtKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c>'z') && (c < 'A' || c>'Z') && (c > ' ')){
           evt.consume();
        }
    }//GEN-LAST:event_paternoTxtKeyTyped

    private void maternoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maternoTxtKeyTyped
        char c = evt.getKeyChar();
        if((c < 'a' || c>'z') && (c < 'A' || c>'Z') && (c > ' ')){
           evt.consume();
        }
    }//GEN-LAST:event_maternoTxtKeyTyped

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        if(this.editar != null){
            actualizar();
        } else{
            agregar();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void agregarSupervisorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSupervisorBtnActionPerformed
        if(this.validarCamposVaciosSupervisores()){
            JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "información", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
        if(this.validarFechas()){
            JOptionPane.showMessageDialog(this, "Las fechas colcadas no son válidas", "información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Doctor doctor = (Doctor) this.supervisoresComboBox.getSelectedItem();
         Calendar fechaInicioCalendar = Calendar.getInstance();
         fechaInicioCalendar.set(inicioSPicker.getDate().getYear(), inicioSPicker.getDate().getMonthValue()-1, inicioSPicker.getDate().getDayOfMonth(), 0, 0, 0);
         Calendar fechaFinCalendar = Calendar.getInstance();
         fechaFinCalendar.set(finSPicker.getDate().getYear(), finSPicker.getDate().getMonthValue()-1, finSPicker.getDate().getDayOfMonth(), 0, 0, 0);
         
         ObjectId idDoctorSeleccionado = doctor.getId();
         Date fechaInicioSeleccionada = fechaInicioCalendar.getTime();
         Date fechaFinSeleccionada = fechaFinCalendar.getTime();
        
        if(editarSupervisor != null){
            
            PeriodoSupervision pSupervisionEditar = this.periodosSupervision.get(editarSupervisor);
            pSupervisionEditar.setIdSupervisor(idDoctorSeleccionado);
            pSupervisionEditar.setFechaInicio(fechaInicioSeleccionada);
            pSupervisionEditar.setFechaFin(fechaFinSeleccionada);
            this.periodosSupervision.set(editarSupervisor, pSupervisionEditar);
            this.editarSupervisor = null;
            this.agregarSupervisorBtn.setText("Agregar Supervisor");
             JOptionPane.showMessageDialog(this, "Se realizaron los cambios correctamente", "información", JOptionPane.INFORMATION_MESSAGE);
        } else{
            PeriodoSupervision pSupervision = new PeriodoSupervision(idDoctorSeleccionado, fechaInicioSeleccionada, fechaFinSeleccionada);
            this.periodosSupervision.add(pSupervision);
            JOptionPane.showMessageDialog(this, "Se agregó el supervisor", "información", JOptionPane.INFORMATION_MESSAGE);
        }
        
         
         vaciarSupervisionesForm();
         llenarTablaSupervisores();
         
    }//GEN-LAST:event_agregarSupervisorBtnActionPerformed

    private void cancelarSupervisionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarSupervisionBtnActionPerformed
        this.vaciarSupervisionesForm();
        
    }//GEN-LAST:event_cancelarSupervisionBtnActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
       this.vaciarForm();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void nombreTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTxtActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        this.dispose();
        pantallaPrincipal = new PrincipalForm();
        pantallaPrincipal.setVisible(true);
    }//GEN-LAST:event_volverBtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(pantallaPrincipal==null){
           pantallaPrincipal = new PrincipalForm();
        }else{
            pantallaPrincipal.setVisible(true);
        }
        
        pantallaPrincipal.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ProfesorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfesorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfesorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfesorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new ProfesorForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ProfesoresScroll;
    private javax.swing.JButton agregarSupervisorBtn;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton cancelarSupervisionBtn;
    private javax.swing.JPanel contenedorProfesorForm;
    private javax.swing.JLabel despachoLbl;
    private javax.swing.JTextField despachoTxt;
    private javax.swing.JCheckBox esDoctor;
    private javax.swing.JCheckBox esInvestigador;
    private javax.swing.JLabel finSLbl;
    private com.github.lgooddatepicker.components.DatePicker finSPicker;
    private javax.swing.JLabel inicioSLbl;
    private com.github.lgooddatepicker.components.DatePicker inicioSPicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable lineaInvestigacionTabla;
    private javax.swing.JLabel maternoLbl;
    private javax.swing.JTextField maternoTxt;
    private javax.swing.JPanel noDoctorPnl;
    private javax.swing.JLabel nombreLbl;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JLabel paternoLbl;
    private javax.swing.JTextField paternoTxt;
    private javax.swing.JTable profesoresTabla;
    private javax.swing.JLabel supervisorLbl;
    private javax.swing.JComboBox<String> supervisoresComboBox;
    private javax.swing.JTable tablaSupervisores;
    private javax.swing.JTextField telefonoTxt;
    private javax.swing.JLabel tituloLbl;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables
}
