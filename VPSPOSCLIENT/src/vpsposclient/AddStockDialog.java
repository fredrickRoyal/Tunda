/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddStockDialog.java
 *
 * Created on Nov 24, 2012, 8:40:21 PM
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
public class AddStockDialog extends javax.swing.JDialog {

    /** Creates new form AddStockDialog */
    ArrayList<Item> itemList = new Controller().getItemList("all");
    ArrayList<UOM> uomList = new Controller().getUOMList();

    public AddStockDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        date.setSelectedItem(dateTokens.get(0));
        month.setSelectedItem(dateTokens.get(1));
        year.setSelectedItem(dateTokens.get(2));
        /*=======================================================================*/
        try {
            if (!itemList.isEmpty()) {
                /* SORTING THE ARRAYLIST*/
                Collections.sort(itemList, new Comparator<Item>() {

                    public int compare(Item s1, Item s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < itemList.size(); i++) {
                    item.addItem(itemList.get(i).getName());
                }
                //item.setSelectedItem(null);


            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*=======================================================================*/

        /*=======================================================================*/
        try {
            if (!uomList.isEmpty()) {
                /* SORTING THE ARRAYLIST*/
                Collections.sort(uomList, new Comparator<UOM>() {

                    public int compare(UOM s1, UOM s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < uomList.size(); i++) {
                    uom.addItem(uomList.get(i).getName());
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*=======================================================================*/

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
                        } else {
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

        jLabel1 = new javax.swing.JLabel();
        item = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        uom = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        costPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        add = new javax.swing.JButton();
        finish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Item");

        item.setEditable(true);
        item.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        item.setMaximumRowCount(10);
        item.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                itemItemStateChanged(evt);
            }
        });
        item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Quantity");

        quantity.setFont(new java.awt.Font("Times New Roman", 1, 24));
        quantity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantityKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("UOM");

        uom.setEditable(true);
        uom.setFont(new java.awt.Font("Times New Roman", 0, 20));
        uom.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("CostPrice");

        costPrice.setFont(new java.awt.Font("Times New Roman", 1, 24));
        costPrice.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        costPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                costPriceKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateFocusGained(evt);
            }
        });
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Month");

        month.setFont(new java.awt.Font("Times New Roman", 0, 20));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.setFocusable(false);
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 20));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.setFocusable(false);
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 0, 12));
        add.setText("Add");
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        finish.setFont(new java.awt.Font("Tahoma", 0, 12));
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
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item, 0, 410, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addComponent(costPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(178, 178, 178))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {date, month, year});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uom, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(costPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
// TODO add your handling code here:
    try {
        if (new Controller().isInteger(quantity.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please Enter Quantity");
        } else if (new Controller().isInteger(costPrice.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please Enter Cost Price");
        } else {
            String stockItem = itemList.get(item.getSelectedIndex()).getId();
            int stockQuantity = Integer.parseInt(quantity.getText());
            String stockUOM = uomList.get(uom.getSelectedIndex()).getId();
            int stockCostPrice = Integer.parseInt(costPrice.getText());
            //int stockSellingPrice=Integer.parseInt(sellingPrice.getText());
            String createDate = date.getSelectedItem().toString();
            String createMonth = month.getSelectedItem().toString();
            String creatYear = year.getSelectedItem().toString();
            String creationDate = createDate + "/" + createMonth + "/" + creatYear;
            new Controller().addStockToDB(new Stock(stockItem, stockQuantity, stockUOM, stockCostPrice, creationDate));
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    reset();

}//GEN-LAST:event_addActionPerformed

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    AdminWindow.addStockDialog.setVisible(false);
    AdminWindow.addStockDialog.getRootPane().removeAll();
    AdminWindow.addStockDialog.removeAll();
    AdminWindow.addStockDialog.dispose();
}//GEN-LAST:event_finishActionPerformed

private void itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemKeyReleased

}//GEN-LAST:event_itemKeyReleased

private void itemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemItemStateChanged
// TODO add your handling code here:
}//GEN-LAST:event_itemItemStateChanged

private void costPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costPriceKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            date.requestFocus();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            quantity.requestFocus();
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_costPriceKeyPressed

private void itemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_itemKeyPressed

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

private void dateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusGained
// TODO add your handling code here:
    System.out.println("We meet there........");
}//GEN-LAST:event_dateFocusGained

private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_quantityActionPerformed

private void quantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add.requestFocus();
            addActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            costPrice.requestFocus();
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_quantityKeyPressed

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
            java.util.logging.Logger.getLogger(AddStockDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStockDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStockDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStockDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddStockDialog dialog = new AddStockDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField costPrice;
    private javax.swing.JComboBox date;
    private javax.swing.JButton finish;
    private javax.swing.JComboBox item;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField quantity;
    private javax.swing.JComboBox uom;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        try {
            quantity.setText("");
            costPrice.setText("");
            item.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
