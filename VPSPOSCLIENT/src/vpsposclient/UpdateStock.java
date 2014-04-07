/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateStock.java
 *
 * Created on Jan 9, 2013, 10:36:35 AM
 */
package vpsposclient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author royal
 */
public class UpdateStock extends javax.swing.JDialog {

    ArrayList<Item> itemList = new Controller().getItemList("all");
    ArrayList<UOM> uomList = new Controller().getUOMList();
    public static String stockItemId;

    /** Creates new form UpdateStock */
    public UpdateStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        try{
             if(!itemList.isEmpty()){ 
                 /* SORTING THE ARRAYLIST*/
                Collections.sort(itemList, new Comparator<Item>() {

                    public int compare(Item s1, Item s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
            for(int i=0; i<itemList.size();i++){
                item.addItem(itemList.get(i).getName());
            }
            
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*=======================================================================*/
        try{
             if(!uomList.isEmpty()){ 
            for(int i=0; i<uomList.size();i++){
                uom.addItem(uomList.get(i).getName());
            }
            
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*=========================================================================*/
                /*=======================================================================*/

        /*Trying to implement a search property on the Item combox*/
        try {

            item.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

                String text = "";

                @Override
                public void keyTyped(KeyEvent e) {
                    Vector v = new Vector();
                    String nextChar = Character.toString(e.getKeyChar()).trim();
                    /*capturing the key typed*/

                    if (nextChar.isEmpty()) {
                        if (text.isEmpty()) {
                        } else {
                            text = text.substring(0, text.length() - 1);
                        }

                    } else {
                        text += nextChar;

                    }
                    /*End of capturing key typed*/

                    if (text.isEmpty()) {
                        itemList = new Controller().getItemList("all");
                    } else {

                        itemList = new Controller().getItemList(text);
                        itemList.add(0, new Item("0", text));
                    }
                    try {
                        if (!itemList.isEmpty()) {
                            Collections.sort(itemList, new Comparator<Item>() {

                                public int compare(Item s1, Item s2) {
                                    return s1.getName().compareToIgnoreCase(s2.getName());
                                }
                            });

                            for (int i = 0; i < itemList.size(); i++) {
                                v.add(itemList.get(i).getName());
                            }
                            DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(v);
                            if (nextChar.isEmpty()) {
                            } else {
                                cboNewModel.setSelectedItem(v.get(0).toString().substring(0, v.get(0).toString().length() - 1));
                            }

                            item.setModel(cboNewModel);
                            item.setEditable(true);
                            item.setEnabled(true);
                            item.showPopup();
                        }else{
                        /*Do not perform any operations on the an EMPTY ArrayList*/
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*=======================================================================*/
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        item = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        uom = new javax.swing.JComboBox();
        quantity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        costPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        updateStockItem = new javax.swing.JButton();
        finish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        item.setEditable(true);
        item.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Item");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("UOM");

        uom.setEditable(true);
        uom.setFont(new java.awt.Font("Times New Roman", 0, 20));
        uom.setFocusable(false);

        quantity.setFont(new java.awt.Font("Times New Roman", 1, 24));
        quantity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Quantity");

        costPrice.setFont(new java.awt.Font("Times New Roman", 1, 24));
        costPrice.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("CostPrice");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setText("Date");

        date.setEditable(true);
        date.setFont(new java.awt.Font("Times New Roman", 0, 20));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.setFocusable(false);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Month");

        month.setEditable(true);
        month.setFont(new java.awt.Font("Times New Roman", 0, 20));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Year");

        year.setEditable(true);
        year.setFont(new java.awt.Font("Times New Roman", 0, 20));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.setFocusable(false);

        updateStockItem.setText("Update");
        updateStockItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStockItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockItemActionPerformed(evt);
            }
        });

        finish.setText("Finish");
        finish.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item, 0, 381, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(date, 0, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(costPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                .addGap(178, 178, 178))
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(updateStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(finish, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addGap(291, 291, 291))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(costPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(month, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(year, javax.swing.GroupLayout.Alignment.LEADING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void updateStockItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockItemActionPerformed
// TODO add your handling code here:
    //String stockItem = itemList.get(item.getSelectedIndex()).getId();
    int stockQuantity = Integer.parseInt(quantity.getText());
    String stockUOM = uomList.get(uom.getSelectedIndex()).getId();
    int stockCostPrice = Integer.parseInt(costPrice.getText());
    //int stockSellingPrice=Integer.parseInt(sellingPrice.getText());
    String createDate = date.getSelectedItem().toString();
    String createMonth = month.getSelectedItem().toString();
    String creatYear = year.getSelectedItem().toString();
    String creationDate = createDate + "/" + createMonth + "/" + creatYear;
    new Controller().updateStock(new Stock(stockItemId, stockQuantity, stockUOM, stockCostPrice, creationDate));
}//GEN-LAST:event_updateStockItemActionPerformed

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    AdminWindow.updateStockDialog.setVisible(false);
    AdminWindow.updateStockDialog.getRootPane().removeAll();
    AdminWindow.updateStockDialog.removeAll();
    AdminWindow.updateStockDialog.dispose();
}//GEN-LAST:event_finishActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                UpdateStock dialog = new UpdateStock(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTextField costPrice;
    public static javax.swing.JComboBox date;
    private javax.swing.JButton finish;
    public static javax.swing.JComboBox item;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JComboBox month;
    public static javax.swing.JTextField quantity;
    public static javax.swing.JComboBox uom;
    private javax.swing.JButton updateStockItem;
    public static javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables
}
