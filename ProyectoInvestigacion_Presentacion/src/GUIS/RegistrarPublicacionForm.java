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
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanelProyectos = new javax.swing.JScrollPane();
        tablaProyectosVigentes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Publicación");

        tablaProyectosVigentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Codigo", "Nombre", "Acrónimo", "Fecha Inicio", "Fecha Fin", "Programa", "Presupuesto", "Investigador Principal", "Patrocinador", "Agregar Publicación"
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
                        .addComponent(scrollPanelProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanelProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RegistrarPublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarPublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarPublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarPublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarPublicacionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollPanelProyectos;
    private javax.swing.JTable tablaProyectosVigentes;
    // End of variables declaration//GEN-END:variables
}
