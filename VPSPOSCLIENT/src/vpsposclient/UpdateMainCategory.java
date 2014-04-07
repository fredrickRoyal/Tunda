/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateMainCategory.java
 *
 * Created on Jan 5, 2013, 12:06:34 PM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author royal
 */
public class UpdateMainCategory extends javax.swing.JDialog {
    public static String id;

    /** Creates new form UpdateMainCategory */
    public UpdateMainCategory(java.awt.Frame parent, boolean modal) {
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
        date = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        finish = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Times New Roman", 1, 24));
        name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        status.setFont(new java.awt.Font("Times New Roman", 0, 18));
        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-active" }));
        status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Status");

        date.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Date");

        month.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Month");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        finish.setFont(new java.awt.Font("Times New Roman", 0, 14));
        finish.setText("Finish");
        finish.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Times New Roman", 0, 14));
        update.setText("Update");
        update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(month, 0, 95, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGap(41, 41, 41)
                        .addComponent(year, 0, 111, Short.MAX_VALUE)))
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    //AdminWindow.dialog
    AdminWindow.updateMainCategoryDialog.setVisible(false);
    AdminWindow.updateMainCategoryDialog.getRootPane().removeAll();
    AdminWindow.updateMainCategoryDialog.removeAll();
    AdminWindow.updateMainCategoryDialog.dispose();
    
    
}//GEN-LAST:event_finishActionPerformed

private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
// TODO add your handling code here:
    String catName=name.getText();
    String catStatus=status.getSelectedItem().toString();
    String createDate=date.getSelectedItem().toString();
    String createMonth=month.getSelectedItem().toString();
    String createYear=year.getSelectedItem().toString();
    if(catName.equals("")||catName.equals("  ")){
        JOptionPane.showMessageDialog(null, "Please Enter Main Category Name");
    }
    else{
        new Controller().updateMainCategory(id, catName, catStatus, createDate+"/"+createMonth+"/"+createYear);
    }
    
   
}//GEN-LAST:event_updateActionPerformed

private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update.requestFocus();
            updateActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_nameKeyPressed

private void statusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update.requestFocus();
            updateActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_statusKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update.requestFocus();
            updateActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_dateKeyPressed

private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update.requestFocus();
            updateActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_monthKeyPressed

private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update.requestFocus();
            updateActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_yearKeyPressed

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
            java.util.logging.Logger.getLogger(UpdateMainCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateMainCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateMainCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateMainCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                UpdateMainCategory dialog = new UpdateMainCategory(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JComboBox month;
    public static javax.swing.JTextField name;
    public static final javax.swing.JComboBox status = new javax.swing.JComboBox();
    private javax.swing.JButton update;
    public static javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}