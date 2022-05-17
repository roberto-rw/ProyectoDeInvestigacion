/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIS;

import entidades.Profesor;

/**
 *
 * @author pc
 */
public class PrincipalForm extends javax.swing.JFrame {

    ProfesorForm pantallaProfesor;
    
    public PrincipalForm() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegistrarProyecto = new javax.swing.JButton();
        botonRegistrarProfesor = new javax.swing.JButton();
        botonAgregarPublicacion = new javax.swing.JButton();
        botonBuscarProyecto = new javax.swing.JButton();

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

        botonBuscarProyecto.setText("Buscar proyecto");
        botonBuscarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarProyectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonAgregarPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(botonRegistrarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonRegistrarProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(botonBuscarProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRegistrarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegistrarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonBuscarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregarPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarProyectoActionPerformed
        this.dispose();
        ProyectoForm pantallaProyecto = new ProyectoForm();
        pantallaProyecto.setVisible(true);
        
    }//GEN-LAST:event_botonRegistrarProyectoActionPerformed

    private void botonRegistrarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarProfesorActionPerformed
        this.dispose();
        ProfesorForm pantallaProfesor = new ProfesorForm();
        pantallaProfesor.setVisible(true);
    }//GEN-LAST:event_botonRegistrarProfesorActionPerformed

    private void botonAgregarPublicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarPublicacionActionPerformed
        this.dispose();
        ProfesorForm pantallaProfesor = new ProfesorForm();
        pantallaProfesor.setVisible(true);
    }//GEN-LAST:event_botonAgregarPublicacionActionPerformed

    private void botonBuscarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarProyectoActionPerformed
        this.dispose();
        ProfesorForm pantallaProfesor = new ProfesorForm();
        pantallaProfesor.setVisible(true);
    }//GEN-LAST:event_botonBuscarProyectoActionPerformed

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
    private javax.swing.JButton botonRegistrarProfesor;
    private javax.swing.JButton botonRegistrarProyecto;
    // End of variables declaration//GEN-END:variables
}
