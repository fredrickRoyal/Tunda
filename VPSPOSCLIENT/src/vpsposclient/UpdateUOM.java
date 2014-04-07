/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateUOM.java
 *
 * Created on Jan 8, 2013, 10:50:25 AM
 */
package vpsposclient;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author royal
 */
public class UpdateUOM extends javax.swing.JDialog {
    public static String uOMId;

    /** Creates new form UpdateUOM */
    public UpdateUOM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        year = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        updateUOM = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Times New Roman", 1, 24));
        name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Status");

        status.setEditable(true);
        status.setFont(new java.awt.Font("Times New Roman", 0, 18));
        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-active" }));
        status.setFocusable(false);

        year.setEditable(true);
        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.setFocusable(false);

        month.setEditable(true);
        month.setFont(new java.awt.Font("Times New Roman", 0, 18));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Year");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Month");

        date.setEditable(true);
        date.setFont(new java.awt.Font("Times New Roman", 0, 18));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Date");

        updateUOM.setText("Update");
        updateUOM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateUOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUOMActionPerformed(evt);
            }
        });

        jButton2.setText("Finish");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(updateUOM, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(year, 0, 124, Short.MAX_VALUE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(month, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)))))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateUOM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void updateUOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUOMActionPerformed
// TODO add your handling code here:
    String uOMName=name.getText();
    String uOMStatus=status.getSelectedItem().toString();
    String createDate=date.getSelectedItem().toString();
    String createMonth=month.getSelectedItem().toString();
    String creatYear=year.getSelectedItem().toString();
    String creationDate=createDate+"/"+createMonth+"/"+creatYear;
    if(uOMName.equals("")||uOMName.equals(" ")){
        JOptionPane.showMessageDialog(null, "Please Enter UOM Name");
    
    }else{
    new Controller().updateUOM(new UOM(uOMId, uOMName, uOMStatus, creationDate));
    
    }
    
}//GEN-LAST:event_updateUOMActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    AdminWindow.updateUOMDialog.setVisible(false);
    AdminWindow.updateUOMDialog.getRootPane().removeAll();
    AdminWindow.updateUOMDialog.removeAll();
    AdminWindow.updateUOMDialog.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateUOM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUOM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUOM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUOM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                UpdateUOM dialog = new UpdateUOM(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox date;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JComboBox month;
    public static javax.swing.JTextField name;
    public static javax.swing.JComboBox status;
    private javax.swing.JButton updateUOM;
    public static javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}
