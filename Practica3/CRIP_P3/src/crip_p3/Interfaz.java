/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crip_p3;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Jesus Fernandez Basso
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        numeroImpar = new javax.swing.JTextField();
        millerRabin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        GenerarClave = new javax.swing.JButton();
        ClavePublica = new javax.swing.JTextField();
        ClavePrivada = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numeroImpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroImparActionPerformed(evt);
            }
        });

        millerRabin.setText("Comprobacion Primalidad");
        millerRabin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                millerRabinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(numeroImpar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(millerRabin)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(numeroImpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(millerRabin)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Test de Miller-Rabin", jPanel1);

        GenerarClave.setText("Generar");
        GenerarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarClaveActionPerformed(evt);
            }
        });

        ClavePublica.setEditable(false);
        ClavePublica.setText("Clave");
        ClavePublica.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ClavePublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClavePublicaActionPerformed(evt);
            }
        });

        ClavePrivada.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GenerarClave)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ClavePublica, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(ClavePrivada)))
                .addGap(73, 73, 73))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(GenerarClave)
                .addGap(18, 18, 18)
                .addComponent(ClavePublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClavePrivada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clave publica (C. elipticas)", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Firma digital (C. elipticas)", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numeroImparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroImparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroImparActionPerformed

    private void millerRabinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_millerRabinActionPerformed
        BigInteger numero = new BigInteger("1");
        String cadena = numeroImpar.getText();
        // if (Funciones.isInteger(cadena)) {
        numero = new BigInteger(cadena);
        //}
        int primo = Funciones.millerRabin(numero);
        if (primo == 1) {
            JOptionPane.showMessageDialog(null, "primo",
                    "primalidad", JOptionPane.INFORMATION_MESSAGE);
        }
        if (primo == 0) {
            JOptionPane.showMessageDialog(null, "no primo",
                    "primalidad", JOptionPane.INFORMATION_MESSAGE);
        }
        if (primo == -1) {
            JOptionPane.showMessageDialog(null, "no es impar",
                    "primalidad", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_millerRabinActionPerformed

    private void GenerarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarClaveActionPerformed
        ArrayList<ArrayList> s = Funciones.RSA();
        ClavePublica.setText("("+s.get(0).get(0).toString() + "," + s.get(0).get(0).toString()+")");
        ClavePrivada.setText("("+s.get(1).get(0).toString()+")");
        System.err.println(s.get(0).get(0));
    }//GEN-LAST:event_GenerarClaveActionPerformed

    private void ClavePublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClavePublicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClavePublicaActionPerformed

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
    private javax.swing.JTextField ClavePrivada;
    private javax.swing.JTextField ClavePublica;
    private javax.swing.JButton GenerarClave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton millerRabin;
    private javax.swing.JTextField numeroImpar;
    // End of variables declaration//GEN-END:variables
}
