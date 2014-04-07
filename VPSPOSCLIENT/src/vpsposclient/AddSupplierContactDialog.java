/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddSupplierContactDialog.java
 *
 * Created on Nov 11, 2013, 11:49:47 AM
 */
package vpsposclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Royal
 */
public class AddSupplierContactDialog extends javax.swing.JDialog {

    ArrayList<Supplier> supplierList = new Controller().getSupplierList();

    /** Creates new form AddSupplierContactDialog */
    public AddSupplierContactDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        date.setSelectedItem(dateTokens.get(0));
        month.setSelectedItem(dateTokens.get(1));
        year.setSelectedItem(dateTokens.get(2));
        try {
            if (!supplierList.isEmpty()) {
                /* SORTING THE ARRAYLIST*/
                Collections.sort(supplierList, new Comparator<Supplier>() {

                    public int compare(Supplier s1, Supplier s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < supplierList.size(); i++) {
                    supplier.addItem(supplierList.get(i).getName());
                }

            }
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
        supplier = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        contact = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        addContact = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Supplier");

        supplier.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Contact");

        contact.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        contact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 14));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Month");

        month.setFont(new java.awt.Font("Times New Roman", 0, 14));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 14));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        addContact.setText("Add");
        addContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addContact, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(contact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(supplier, javax.swing.GroupLayout.Alignment.LEADING, 0, 341, Short.MAX_VALUE))
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(addContact, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {date, month, year});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void addContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContactActionPerformed
// TODO add your handling code here:
    try {
        String id = new Controller().generateId();
        String supplierId = supplierList.get(supplier.getSelectedIndex()).getId();
        String supplierContact = contact.getText();
        String createDate = date.getSelectedItem().toString();
        String createMonth = month.getSelectedItem().toString();
        String creatYear = year.getSelectedItem().toString();
        String creationDate = createDate + "/" + createMonth + "/" + creatYear;
        if (supplierContact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Supplier's Contact");
        } else {
            boolean added = new Controller().addSupplierContact(new SupplierContact(id, supplierId, supplierContact, creationDate));
            if (added == true) {
                JOptionPane.showMessageDialog(null, "Supplier's Contact Added successfully");
                AdminWindow.supplierContactsTable.setModel(new Controller().supplierContactTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Supplier's Contact\nNOT\nAdded");
            }
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_addContactActionPerformed

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
            java.util.logging.Logger.getLogger(AddSupplierContactDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSupplierContactDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSupplierContactDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSupplierContactDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddSupplierContactDialog dialog = new AddSupplierContactDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addContact;
    private javax.swing.JTextField contact;
    private javax.swing.JComboBox date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox month;
    private javax.swing.JComboBox supplier;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}
