/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import implementacionesBO.FacadeBO;
import interfacesBO.IFacadeBO;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class PrincipalForm extends javax.swing.JFrame {

    IFacadeBO fachadaBO;
    
    public PrincipalForm() {
        initComponents();
        this.fachadaBO = new FacadeBO();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegistrarProyecto = new javax.swing.JButton();
        botonRegistrarProfesor = new javax.swing.JButton();
        botonAgregarPublicacion = new javax.swing.JButton();
        botonBuscarProyecto = new javax.swing.JButton();
        botonRegistrarLinea = new javax.swing.JButton();
        registrarProgramaBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pantalla principal");

        botonRegistrarProyecto.setText("Registrar Proyecto");
        botonRegistrarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarProyectoActionPerformed(evt);
            }
        });

        botonRegistrarProfesor.setText("Registrar profesor");
        botonRegistrarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarProfesorActionPerformed(evt);
            }
        });

        botonAgregarPublicacion.setText("Agregar publicación");
        botonAgregarPublicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarPublicacionActionPerformed(evt);
            }
        });

        botonBuscarProyecto.setText("Buscar Proyecto");
        botonBuscarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarProyectoActionPerformed(evt);
            }
        });

        botonRegistrarLinea.setText("Registrar Linea Investigacion");
        botonRegistrarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarLineaActionPerformed(evt);
            }
        });

        registrarProgramaBtn.setText("Registrar Programa");
        registrarProgramaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarProgramaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(botonRegistrarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonAgregarPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonRegistrarLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(64, 64, 64)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonBuscarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonRegistrarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(registrarProgramaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegistrarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegistrarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonBuscarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregarPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonRegistrarLinea, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(registrarProgramaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarProyectoActionPerformed
        if(fachadaBO.consultarTodosProfesores().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede registrar un Proyecto si no hay Profesores registrados", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(fachadaBO.consultarTodosInvestigadorDoctores().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede registrar un Proyecto si no hay al menos un Investigador Doctor registrado", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(fachadaBO.consultarTodosProgramas().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede registrar un Proyecto si no hay al menos un Programa registrado", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.dispose();
        ProyectoForm pantallaProyecto = new ProyectoForm();
        pantallaProyecto.setVisible(true);
        
    }//GEN-LAST:event_botonRegistrarProyectoActionPerformed

    private void botonRegistrarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarProfesorActionPerformed
        if(fachadaBO.consultarTodosLineas().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede registrar un Profesor si no hay al menos una Linea de Investigación registrada", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.dispose();
        ProfesorForm pantallaProfesor = new ProfesorForm();
        pantallaProfesor.setVisible(true);
    }//GEN-LAST:event_botonRegistrarProfesorActionPerformed

    private void botonAgregarPublicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarPublicacionActionPerformed
        if(fachadaBO.consultarVigentes().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede registrar un Publicación si no hay Proyectos Vigentes registrados", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        this.dispose();
        RegistrarPublicacionForm pantallaPublicacion = new RegistrarPublicacionForm();
        pantallaPublicacion.setVisible(true);
    }//GEN-LAST:event_botonAgregarPublicacionActionPerformed

    private void botonBuscarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarProyectoActionPerformed
        if(fachadaBO.consultarTodosProyectos().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se puede Buscar un Proyecto si no hay ningun Proyecto registrado", "información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        this.dispose();
        BuscarProyecto pantallaBuscar = new BuscarProyecto();
        pantallaBuscar.setVisible(true);
    }//GEN-LAST:event_botonBuscarProyectoActionPerformed

    private void botonRegistrarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarLineaActionPerformed
        this.dispose();
        LineaInvestigacionForm pantallaLinea = new LineaInvestigacionForm();
        pantallaLinea.setVisible(true);
    }//GEN-LAST:event_botonRegistrarLineaActionPerformed

    private void registrarProgramaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarProgramaBtnActionPerformed
        this.dispose();
        ProgramaForm pantallaPrograma = new ProgramaForm();
        pantallaPrograma.setVisible(true);
    }//GEN-LAST:event_registrarProgramaBtnActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarPublicacion;
    private javax.swing.JButton botonBuscarProyecto;
    private javax.swing.JButton botonRegistrarLinea;
    private javax.swing.JButton botonRegistrarProfesor;
    private javax.swing.JButton botonRegistrarProyecto;
    private javax.swing.JButton registrarProgramaBtn;
    // End of variables declaration//GEN-END:variables
}
