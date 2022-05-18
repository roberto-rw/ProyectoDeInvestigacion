/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import entidades.Profesor;
import entidades.Proyecto;
import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
import utils.ButtonColumn;

/**
 *
 * @author user
 */
public class RegistrarPublicacionForm extends javax.swing.JFrame {

    IFacadeBO fachadaBO;
    PrincipalForm pantallaPrincipal;
       
    public RegistrarPublicacionForm() {
        initComponents();
        fachadaBO = new FacadeBO();
        this.llenarTablaProyectosVigentes();
    }


    private void llenarTablaProyectosVigentes(){
        Action agregarPublicacion = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaAgregarPublicacion();
            }
        };
        
        List<Proyecto> proyectosVigentes = fachadaBO.consultarVigentes();
        
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaProyectosVigentes.getModel();
        modeloTabla.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        
        proyectosVigentes.forEach(proyecto -> {
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
            fila[10] = "Agregar Publicación";
            modeloTabla.addRow(fila);
        });
        ButtonColumn buttonColumnAgregarPublicacion = new ButtonColumn(this.tablaProyectosVigentes, agregarPublicacion, 10);
    }
    
    private ObjectId getIdProyectoSeleccionado(){
        int indiceFilaSeleccionada = this.tablaProyectosVigentes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaProyectosVigentes.getModel();
            int indiceColumnaId = 0;
            ObjectId idProyectoSeleccionado = (ObjectId) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idProyectoSeleccionado;
        } else {
            return null;
        }
    }
    
    public void mostrarPantallaAgregarPublicacion(){
        AgregarPublicacionForm pantallaAgregarPublicacion = new AgregarPublicacionForm(this.getIdProyectoSeleccionado());
        pantallaAgregarPublicacion.setVisible(true);
        this.dispose();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanelProyectos = new javax.swing.JScrollPane();
        tablaProyectosVigentes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Publicación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tablaProyectosVigentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Código", "Nombre", "Acrónimo", "Fecha Inicio", "Fecha Fin", "Programa", "Presupuesto", "Investigador Principal", "Patrocinador", "Agregar Publicación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProyectosVigentes.setRowHeight(30);
        scrollPanelProyectos.setViewportView(tablaProyectosVigentes);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Proyectos Vigentes");

        jButton1.setText("Volver al menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPanelProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanelProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        pantallaPrincipal = new PrincipalForm();
        pantallaPrincipal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        if(pantallaPrincipal==null){
//           pantallaPrincipal = new PrincipalForm();
//        }else{
//            pantallaPrincipal.setVisible(true);
//        }
//        pantallaPrincipal.setVisible(true);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollPanelProyectos;
    private javax.swing.JTable tablaProyectosVigentes;
    // End of variables declaration//GEN-END:variables
}
