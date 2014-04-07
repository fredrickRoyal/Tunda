/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateExpenditureDialog.java
 *
 * Created on Mar 21, 2013, 9:27:38 AM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Royal
 */
public class UpdateExpenditureDialog extends javax.swing.JDialog {

    /** Creates new form UpdateExpenditureDialog */
    static String expenditureId;
    static String amount;
    static String reason;
    static String currentDate;
    static String currentMonth;
    static String currentYear;

    public UpdateExpenditureDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        amountSpent.setText(amount);
        purpose.setText(reason);
        date.setSelectedItem(currentDate);
        month.setSelectedItem(currentMonth);
        year.setSelectedItem(currentYear);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        amountSpent = new javax.swing.JTextField();
        purpose = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Amount Spent");

        amountSpent.setFont(new java.awt.Font("Times New Roman", 1, 18));
        amountSpent.setText("0");
        amountSpent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        amountSpent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountSpentKeyPressed(evt);
            }
        });

        purpose.setFont(new java.awt.Font("Times New Roman", 1, 18));
        purpose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        purpose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purposeKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Reason");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 18));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        save.setText("Save");
        save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Month");

        month.setFont(new java.awt.Font("Times New Roman", 0, 18));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 15));
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(amountSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(purpose, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(purpose, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {date, month, year});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
// TODO add your handling code here:
    String cashier = expenditureId;
    String reasons = purpose.getText();
    String expenditureDate = (date.getSelectedItem().toString()) + "/" + (month.getSelectedItem().toString()) + "/" + (year.getSelectedItem().toString());

    if (cashier.isEmpty() || cashier.length() == 0) {
        JOptionPane.showMessageDialog(null, "Enter your id");

    } else if (reason.isEmpty() || reason.length() == 0) {
        JOptionPane.showMessageDialog(null, "Enter the Reason for the Expenditure");
    } else if (new Controller().isInteger(new Controller().unformatCash(amountSpent.getText())) == false) {
        JOptionPane.showMessageDialog(null, "Enter Amount Spent as Numbers");

    } else {
        int amounts = Integer.parseInt(new Controller().unformatCash(amountSpent.getText()));
        new Controller().updateExpenditure(new CashierExpendure(cashier, amounts, reasons, expenditureDate));
        ExpenditureViewDialog.totalExpenditure.setText(ExpenditureViewDialog.expenditure);

    }
}//GEN-LAST:event_saveActionPerformed

private void amountSpentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountSpentKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.saveActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
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
            this.saveActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            amountSpent.requestFocus();
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_purposeKeyPressed

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
            java.util.logging.Logger.getLogger(UpdateExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateExpenditureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                UpdateExpenditureDialog dialog = new UpdateExpenditureDialog(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTextField amountSpent;
    public static javax.swing.JComboBox date;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JComboBox month;
    public static javax.swing.JTextField purpose;
    private javax.swing.JButton save;
    public static javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}