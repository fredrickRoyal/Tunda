/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainCategoryAddDialog.java
 *
 * Created on Nov 19, 2012, 8:44:43 AM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author royal
 */
public class MainCategoryAddDialog extends javax.swing.JDialog {

    /** Creates new form mainCategoryAddDialog */
    public MainCategoryAddDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        date.setSelectedItem(dateTokens.get(0));
        month.setSelectedItem(dateTokens.get(1));
        year.setSelectedItem(dateTokens.get(2));
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
        jLabel3 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        year = new javax.swing.JComboBox();
        add = new javax.swing.JButton();
        finish = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Tahoma", 1, 14));
        name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Status");

        status.setFont(new java.awt.Font("Times New Roman", 0, 18));
        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-active" }));
        status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 18));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        month.setFont(new java.awt.Font("Times New Roman", 0, 18));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        add.setFont(new java.awt.Font("Times New Roman", 0, 14));
        add.setText("Add");
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Month");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("year");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(month, 0, 85, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGap(41, 41, 41)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(126, 126, 126))
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(405, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
// TODO add your handling code here:
    String id = new Controller().generateId();
    String catName = name.getText();
    String catStatus = status.getSelectedItem().toString();
    String createDate = date.getSelectedItem().toString();
    String createMonth = month.getSelectedItem().toString();
    String createYear = year.getSelectedItem().toString();
    if (catName.equals("") || catName.equals("  ")) {
        JOptionPane.showMessageDialog(null, "Please Enter Main Category Name");
    } else {
        new Controller().addMainCategoryToDB(id, catName, catStatus, createDate + "/" + createMonth + "/" + createYear);
    }

    reset();
}//GEN-LAST:event_addActionPerformed

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    //AdminWindow.dialog
    AdminWindow.mainCategoryAddDialog.setVisible(false);
    AdminWindow.mainCategoryAddDialog.getRootPane().removeAll();
    AdminWindow.mainCategoryAddDialog.removeAll();
    AdminWindow.mainCategoryAddDialog.dispose();


}//GEN-LAST:event_finishActionPerformed

private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_nameKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_dateKeyPressed

private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_monthKeyPressed

private void statusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_statusKeyPressed

private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        }
    } catch (Exception ex) {
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
            java.util.logging.Logger.getLogger(MainCategoryAddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCategoryAddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCategoryAddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCategoryAddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainCategoryAddDialog dialog = new MainCategoryAddDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton add;
    private javax.swing.JComboBox date;
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox status;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
    private void reset() {
        try {
            name.setText("");
            name.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
