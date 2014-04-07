/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductSalesDialog.java
 *
 * Created on Apr 19, 2013, 11:20:44 PM
 */
package vpsposclient;

import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Royal
 */
public class ProductSalesDialog extends javax.swing.JDialog {

    ArrayList<MainCategory> mainCategoryList = new Controller().getMainCategoryList("all");
    ArrayList<SubCategory> subCategoryList = new Controller().getSubCategoryList("all");

    /** Creates new form ProductSalesDialog */
    public ProductSalesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            if (!mainCategoryList.isEmpty()) {
                mainCategory.addItem("All MainCategory");
                for (int i = 0; i < mainCategoryList.size(); i++) {
                    mainCategory.addItem(mainCategoryList.get(i).getName());
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        try {
            if (!subCategoryList.isEmpty()) {
                subCategory.addItem("All subCategory");
                for (int i = 0; i < subCategoryList.size(); i++) {
                    subCategory.addItem(subCategoryList.get(i).getName());
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        fromDate.setSelectedItem(dateTokens.get(0));
        fromMonth.setSelectedItem(dateTokens.get(1));
        fromYear.setSelectedItem(dateTokens.get(2));
        toDate.setSelectedItem(dateTokens.get(0));
        toMonth.setSelectedItem(dateTokens.get(1));
        toYear.setSelectedItem(dateTokens.get(2));
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
        mainCategory = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        subCategory = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fromDate = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        fromMonth = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        fromYear = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        toDate = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        toMonth = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        toYear = new javax.swing.JComboBox();
        viewReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("CashierId");

        cashierId.setFont(new java.awt.Font("Times New Roman", 1, 18));
        cashierId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Category");

        mainCategory.setFont(new java.awt.Font("Times New Roman", 1, 18));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Sub Category");

        subCategory.setFont(new java.awt.Font("Times New Roman", 1, 18));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("FROM");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Date");

        fromDate.setFont(new java.awt.Font("Times New Roman", 1, 14));
        fromDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Month");

        fromMonth.setFont(new java.awt.Font("Times New Roman", 1, 14));
        fromMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("Year");

        fromYear.setFont(new java.awt.Font("Times New Roman", 1, 14));
        fromYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("TO");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));
        jLabel9.setText("Date");

        toDate.setFont(new java.awt.Font("Times New Roman", 1, 14));
        toDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel10.setForeground(new java.awt.Color(0, 51, 255));
        jLabel10.setText("Month");

        toMonth.setFont(new java.awt.Font("Times New Roman", 1, 14));
        toMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel11.setForeground(new java.awt.Color(0, 51, 255));
        jLabel11.setText("Year");

        toYear.setFont(new java.awt.Font("Times New Roman", 1, 14));
        toYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        viewReport.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        viewReport.setText("View Report");
        viewReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashierId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(mainCategory, javax.swing.GroupLayout.Alignment.TRAILING, 0, 345, Short.MAX_VALUE)
                    .addComponent(subCategory, 0, 345, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(toYear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fromYear, 0, 74, Short.MAX_VALUE)))))
                .addGap(176, 176, 176))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(viewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cashierId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(subCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fromMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fromYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(toMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(toYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(viewReport, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cashierId, fromDate, fromMonth, fromYear, mainCategory, subCategory, toDate, toMonth, toYear});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void viewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportActionPerformed
// TODO add your handling code here:
    int categoryIndex = mainCategory.getSelectedIndex();
    int subCategoryIndex = subCategory.getSelectedIndex();

    String id = cashierId.getText();
    String from = (fromDate.getSelectedItem().toString()) + "/" + (fromMonth.getSelectedItem().toString()) + "/" + (fromYear.getSelectedItem().toString());
    String to = (toDate.getSelectedItem().toString()) + "/" + (toMonth.getSelectedItem().toString()) + "/" + (toYear.getSelectedItem().toString());
    AdminWindow.productSalesDialog.setVisible(false);
    AdminWindow.productSalesDialog.getRootPane().removeAll();
    AdminWindow.productSalesDialog.removeAll();
    AdminWindow.productSalesDialog.dispose();

    try {
        boolean processing = true;
        do {
            AdminWindow.reportsTab.requestFocus();
            AdminWindow.reportsTab.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            if ((categoryIndex == 0) && (subCategoryIndex == 0)) {
                if (id.isEmpty()) {

                    new Controller().generateCustomDateTotalCollectionReport(from, to);
                } else {
                    new Controller().generateCustomCashierDateTotalCollectionReport(id, from, to);
                }
            } else if ((categoryIndex == 0) && (subCategoryIndex != 0)) {
                String subCategoryId = subCategoryList.get(subCategoryIndex - 1).getId();
                if (id.isEmpty()) {

                    new Controller().generateSubCategoryCollectionReport(subCategoryId, from, to);
                } else {
                    new Controller().generateCashierSubCategoryCollectionReport(id, subCategoryId, from, to);


                }


            } else if ((categoryIndex != 0) && (subCategoryIndex == 0)) {
                String categoryId = mainCategoryList.get(categoryIndex - 1).getId();
                if (id.isEmpty()) {

                    new Controller().generateMainCategoryCollectionReport(categoryId, from, to);
                } else {
                    new Controller().generateCashierMainCategoryCollectionReport(id, categoryId, from, to);

                }
            } else if ((categoryIndex != 0) && (subCategoryIndex != 0)) {

                String subCategoryId = subCategoryList.get(subCategoryIndex - 1).getId();
                if (id.isEmpty()) {

                    new Controller().generateSubCategoryCollectionReport(subCategoryId, from, to);
                } else {
                    new Controller().generateCashierSubCategoryCollectionReport(id, subCategoryId, from, to);


                }

            }
            processing = false;
        } while (processing == true);
        AdminWindow.reportsTab.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_viewReportActionPerformed

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
            java.util.logging.Logger.getLogger(ProductSalesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductSalesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductSalesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductSalesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ProductSalesDialog dialog = new ProductSalesDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField cashierId;
    private javax.swing.JComboBox fromDate;
    private javax.swing.JComboBox fromMonth;
    private javax.swing.JComboBox fromYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox mainCategory;
    private javax.swing.JComboBox subCategory;
    private javax.swing.JComboBox toDate;
    private javax.swing.JComboBox toMonth;
    private javax.swing.JComboBox toYear;
    private javax.swing.JButton viewReport;
    // End of variables declaration//GEN-END:variables
}