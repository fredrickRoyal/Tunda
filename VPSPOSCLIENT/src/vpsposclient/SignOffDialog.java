/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SignOffDialog.java
 *
 * Created on May 20, 2013, 9:20:24 AM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Royal
 */
public class SignOffDialog extends javax.swing.JDialog {

    /** Creates new form SignOffDialog */
    static String cashierId;
    static String cashierName;

    public SignOffDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cashier.setText(cashierName);
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
        cashier = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        tookOver = new javax.swing.JTextField();
        timeRange1 = new javax.swing.JComboBox();
        signedOut = new javax.swing.JTextField();
        timeRange2 = new javax.swing.JComboBox();
        totalCollection = new javax.swing.JTextField();
        totalExpenditure = new javax.swing.JTextField();
        signOff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Cashier ");

        cashier.setBackground(new java.awt.Color(255, 255, 255));
        cashier.setEditable(false);
        cashier.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cashier.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashierActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Took Over");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Signed Out");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Total Collection");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Total Expenditure");

        date.setFont(new java.awt.Font("Times New Roman", 0, 14));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        month.setFont(new java.awt.Font("Times New Roman", 0, 14));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("Month");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 14));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        tookOver.setFont(new java.awt.Font("Tahoma", 1, 18));
        tookOver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tookOver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tookOverKeyPressed(evt);
            }
        });

        timeRange1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        timeRange1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        signedOut.setFont(new java.awt.Font("Tahoma", 1, 18));
        signedOut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        signedOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                signedOutKeyPressed(evt);
            }
        });

        timeRange2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        timeRange2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        totalCollection.setFont(new java.awt.Font("Tahoma", 1, 18));
        totalCollection.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        totalCollection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalCollectionKeyPressed(evt);
            }
        });

        totalExpenditure.setFont(new java.awt.Font("Times New Roman", 1, 18));
        totalExpenditure.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        totalExpenditure.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalExpenditureKeyPressed(evt);
            }
        });

        signOff.setFont(new java.awt.Font("Times New Roman", 1, 14));
        signOff.setText("Sign Off");
        signOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashier, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(totalExpenditure, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(totalCollection, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tookOver, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(signedOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(timeRange1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timeRange2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(signOff, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cashier, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tookOver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeRange1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(signedOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeRange2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(totalCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(signOff, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cashier, date, month, signedOut, timeRange1, timeRange2, tookOver, totalCollection, totalExpenditure, year});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashierActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cashierActionPerformed

private void signOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOffActionPerformed
// TODO add your handling code here:
    if (tookOver.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Fill in the Time when you TOOK OVER");
    } else if (signedOut.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Fill in the Time when you are Logging out");
    } else if (new Controller().isInteger(totalCollection.getText()) == false) {
        JOptionPane.showMessageDialog(null, "Enter Total Collection as Numbers");
    } else if (new Controller().isInteger(totalExpenditure.getText()) == false) {
        JOptionPane.showMessageDialog(null, "Enter Total Expenditure as Numbers");
    } else {
        String logedin = tookOver.getText() + ":" + timeRange1.getSelectedItem().toString();
        String logedout = signedOut.getText() + ":" + timeRange2.getSelectedItem().toString();
        String summaryDate = date.getSelectedItem().toString() + "/" + month.getSelectedItem().toString() + "/" + year.getSelectedItem().toString();
        int totalCollected = Integer.parseInt(totalCollection.getText());
        int totalSpent = Integer.parseInt(totalExpenditure.getText());
        int cashAtHand = totalCollected - totalSpent;
        if (new Controller().cashierSignOff(new Summary(cashierId, logedin, logedout, summaryDate, totalCollected, totalSpent, cashAtHand))) {
            JOptionPane.showMessageDialog(null, "Your Summary has been sent");
        } else {
            JOptionPane.showMessageDialog(null, "Your Summary has NOT been sent\n Error");
        }
    }

}//GEN-LAST:event_signOffActionPerformed

private void tookOverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tookOverKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.signOffActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            signedOut.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            timeRange1.requestFocus();
        }
            
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_tookOverKeyPressed

private void signedOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_signedOutKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.signOffActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            date.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            timeRange2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tookOver.requestFocus();
        }
            
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_signedOutKeyPressed

private void totalCollectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCollectionKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.signOffActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            date.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            totalExpenditure.requestFocus();
        }
            
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_totalCollectionKeyPressed

private void totalExpenditureKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalExpenditureKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.signOffActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            totalCollection.requestFocus();
        }
            
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_totalExpenditureKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            signOffActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            signedOut.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            totalCollection.requestFocus();
        }
        
            
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_dateKeyPressed

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
            java.util.logging.Logger.getLogger(SignOffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignOffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignOffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignOffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SignOffDialog dialog = new SignOffDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField cashier;
    private javax.swing.JComboBox date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox month;
    private javax.swing.JButton signOff;
    private javax.swing.JTextField signedOut;
    private javax.swing.JComboBox timeRange1;
    private javax.swing.JComboBox timeRange2;
    private javax.swing.JTextField tookOver;
    private javax.swing.JTextField totalCollection;
    private javax.swing.JTextField totalExpenditure;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}