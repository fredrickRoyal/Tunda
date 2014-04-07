/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExpenditureDialog.java
 *
 * Created on Mar 19, 2013, 9:53:30 AM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Royal
 */
public class ExpenditureDialog extends javax.swing.JDialog {

    /** Creates new form ExpenditureDialog */
    public ExpenditureDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            String currentDate = new Controller().getCurrentTimeStamp();
            ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
            date.setSelectedItem(dateTokens.get(0));
            month.setSelectedItem(dateTokens.get(1));
            year.setSelectedItem(dateTokens.get(2));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
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
        cashierId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        amountSpent = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        purpose = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Cashier  ID");

        cashierId.setFont(new java.awt.Font("Times New Roman", 1, 18));
        cashierId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cashierId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashierIdActionPerformed(evt);
            }
        });
        cashierId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cashierIdKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Amount Spent");

        amountSpent.setFont(new java.awt.Font("Times New Roman", 1, 18));
        amountSpent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        amountSpent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountSpentKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Reason");

        purpose.setFont(new java.awt.Font("Times New Roman", 1, 18));
        purpose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        purpose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purposeKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 18));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Month");

        month.setFont(new java.awt.Font("Times New Roman", 0, 18));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        save.setText("Save");
        save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cashierId, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(amountSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(date, 0, 93, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purpose, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                .addGap(133, 133, 133))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cashierId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountSpent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purpose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {amountSpent, cashierId, date, month, purpose, year});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cashierIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashierIdActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cashierIdActionPerformed

private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
// TODO add your handling code here:
    String expenditureId = new Controller().generateId();
    String cashier = this.cashierId.getText();
    String reason = this.purpose.getText();
    String expenditureDate = (date.getSelectedItem().toString()) + "/" + (month.getSelectedItem().toString()) + "/" + (year.getSelectedItem().toString());

    if (cashier.isEmpty() || cashier.length() == 0) {
        JOptionPane.showMessageDialog(null, "Enter your id");

    } else if (reason.isEmpty() || reason.length() == 0) {
        JOptionPane.showMessageDialog(null, "Enter the Reason for the Expenditure");
    } else if (new Controller().isInteger(amountSpent.getText()) == false) {
        JOptionPane.showMessageDialog(null, "Enter Amount Spent as Numbers");

    } else {
        int amount = Integer.parseInt(this.amountSpent.getText());
        if (new Controller().validateCashier(cashier) == true) {
            if ((new Controller().addCashierExpenditureToDB(new CashierExpendure(expenditureId, cashier, amount, reason, expenditureDate))) == true) {
                ExpenditureViewDialog.totalExpenditure.setText(ExpenditureViewDialog.expenditure);
                JOptionPane.showMessageDialog(null, "Expenditure Saved");

            } else {
                JOptionPane.showMessageDialog(null, "Expenditure not Saved\n ERROR");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter Your Correct ID");
        }

    }

}//GEN-LAST:event_saveActionPerformed

private void cashierIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashierIdKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            amountSpent.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
        purpose.requestFocus();
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_cashierIdKeyPressed

private void amountSpentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountSpentKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            cashierId.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
        purpose.requestFocus();
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_amountSpentKeyPressed

private void purposeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purposeKeyPressed
// TODO add your handling code here:
     try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_UP){
        amountSpent.requestFocus();
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_purposeKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_dateKeyPressed

private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_monthKeyPressed

private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            saveActionPerformed(null);
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
            java.util.logging.Logger.getLogger(ExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ExpenditureDialog dialog = new ExpenditureDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField amountSpent;
    private javax.swing.JTextField cashierId;
    private javax.swing.JComboBox date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField purpose;
    private javax.swing.JButton save;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}