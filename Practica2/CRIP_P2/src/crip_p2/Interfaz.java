/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p2;

/**
 *
 * @author Alex
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JTabbedPane();
        periodicidad = new javax.swing.JPanel();
        titulo1Periodicidad = new javax.swing.JLabel();
        titulo2Periodicidad = new javax.swing.JLabel();
        sucesionPeriodicidad = new javax.swing.JTextField();
        aceptarPeriodicidad = new javax.swing.JButton();
        respuestaPeriodicidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo1Periodicidad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titulo1Periodicidad.setText("Periodicidad de una");

        titulo2Periodicidad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titulo2Periodicidad.setText("sucesión de bits");

        aceptarPeriodicidad.setText("Aceptar");

        respuestaPeriodicidad.setEditable(false);
        respuestaPeriodicidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                respuestaPeriodicidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout periodicidadLayout = new javax.swing.GroupLayout(periodicidad);
        periodicidad.setLayout(periodicidadLayout);
        periodicidadLayout.setHorizontalGroup(
            periodicidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(periodicidadLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(sucesionPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodicidadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(periodicidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodicidadLayout.createSequentialGroup()
                        .addGroup(periodicidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo1Periodicidad)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodicidadLayout.createSequentialGroup()
                                .addComponent(titulo2Periodicidad)
                                .addGap(18, 18, 18)))
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, periodicidadLayout.createSequentialGroup()
                        .addComponent(aceptarPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(respuestaPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );
        periodicidadLayout.setVerticalGroup(
            periodicidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(periodicidadLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titulo1Periodicidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo2Periodicidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(sucesionPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(periodicidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(respuestaPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        panel.addTab("Periodicidad", periodicidad);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void respuestaPeriodicidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_respuestaPeriodicidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_respuestaPeriodicidadActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarPeriodicidad;
    private javax.swing.JTabbedPane panel;
    private javax.swing.JPanel periodicidad;
    private javax.swing.JTextField respuestaPeriodicidad;
    private javax.swing.JTextField sucesionPeriodicidad;
    private javax.swing.JLabel titulo1Periodicidad;
    private javax.swing.JLabel titulo2Periodicidad;
    // End of variables declaration//GEN-END:variables
}
