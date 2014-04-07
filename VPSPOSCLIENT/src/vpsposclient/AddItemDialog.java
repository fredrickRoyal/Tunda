/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddItemDialog.java
 *
 * Created on Sep 3, 2011, 8:23:29 AM
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
public class AddItemDialog extends javax.swing.JDialog {

    /** Creates new form AddItemDialog */
    ArrayList<SubCategory> subCategoryList = new Controller().getSubCategoryList("all");

    public AddItemDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        date.setSelectedItem(dateTokens.get(0));
        month.setSelectedItem(dateTokens.get(1));
        year.setSelectedItem(dateTokens.get(2));

        /*======================================================================*/
        try {
            if (!subCategoryList.isEmpty()) {
                /* SORTING THE ARRAYLIST*/
                Collections.sort(subCategoryList, new Comparator<SubCategory>() {

                    public int compare(SubCategory s1, SubCategory s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < subCategoryList.size(); i++) {
                    category.addItem(subCategoryList.get(i).getName());
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*======================================================================*/



        /*=======================================================================*/

        /*Trying to implement a search property on the Category combobox*/
        try {

            category.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

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
                        subCategoryList = new Controller().getSubCategoryList("all");
                    } else {

                        subCategoryList = new Controller().getSubCategoryList(text);
                        subCategoryList.add(0, new SubCategory("0", "0", text, "0", "0"));
                    }
                    try {
                        if (!subCategoryList.isEmpty()) {
                            Collections.sort(subCategoryList, new Comparator<SubCategory>() {

                                public int compare(SubCategory s1, SubCategory s2) {
                                    return s1.getName().compareToIgnoreCase(s2.getName());
                                }
                            });

                            for (int i = 0; i < subCategoryList.size(); i++) {
                                v.add(subCategoryList.get(i).getName());
                            }
                            DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(v);
                            if (nextChar.isEmpty()) {
                            } else {
                                cboNewModel.setSelectedItem(v.get(0).toString().substring(0, v.get(0).toString().length() - 1));
                            }

                            category.setModel(cboNewModel);
                            category.setEditable(true);
                            category.setEnabled(true);
                            category.showPopup();
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
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        category = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        addItem = new javax.swing.JButton();
        finish = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        barcode = new javax.swing.JTextField();
        barcodeReader = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Times New Roman", 1, 18));
        name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Status");

        status.setFont(new java.awt.Font("Times New Roman", 0, 18));
        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-active" }));
        status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                statusKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Sub Category");

        category.setEditable(true);
        category.setFont(new java.awt.Font("Times New Roman", 1, 18));
        category.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                categoryFocusGained(evt);
            }
        });
        category.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                categoryKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Date");

        date.setFont(new java.awt.Font("Times New Roman", 0, 18));
        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Month");

        month.setFont(new java.awt.Font("Times New Roman", 0, 18));
        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setText("Year");

        year.setFont(new java.awt.Font("Times New Roman", 0, 18));
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        addItem.setText("Add");
        addItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemActionPerformed(evt);
            }
        });

        finish.setText("Finish");
        finish.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Price");

        price.setFont(new java.awt.Font("Times New Roman", 1, 18));
        price.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                priceKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Barcode");

        barcode.setFont(new java.awt.Font("Times New Roman", 1, 18));
        barcode.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        barcode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                barcodeFocusLost(evt);
            }
        });
        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });

        barcodeReader.setFont(new java.awt.Font("Times New Roman", 1, 18));
        barcodeReader.setForeground(new java.awt.Color(0, 51, 255));
        barcodeReader.setText("Barcode Reader");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(barcodeReader)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(category, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(year, 0, 145, Short.MAX_VALUE)
                                                .addComponent(status, 0, 145, Short.MAX_VALUE)
                                                .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(39, 39, 39)
                                            .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel6)))
                                    .addGap(172, 172, 172)))
                            .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(160, 160, 160))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(barcodeReader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {barcode, name});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
// TODO add your handling code here:


    if (name.getText().equals("") || name.getText().equals("  ")) {
        JOptionPane.showMessageDialog(null, "Please Enter Item Name");
    } else if (price.getText().equals("") || price.getText().equals(" ")) {
        JOptionPane.showMessageDialog(null, "Please Enter Item Price");
    } else {
        if (barcodeReader.isSelected()) {
            if (barcode.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Read the Product Barcode\n with the barcode Reader\n or Disable the Barcode Reader");
            } else {
                String itemId = barcode.getText();
                String itemName = name.getText();
                int itemPrice = Integer.parseInt(price.getText());
                String itemStatus = status.getSelectedItem().toString();
                String subCategoryId = subCategoryList.get(category.getSelectedIndex()).getId();
                String createDate = date.getSelectedItem().toString();
                String createMonth = month.getSelectedItem().toString();
                String creatYear = year.getSelectedItem().toString();
                String creationDate = createDate + "/" + createMonth + "/" + creatYear;
                if (new Controller().findIfExists(barcode.getText()) == true) {
                    JOptionPane.showMessageDialog(null, "ERROR \nItem Already Exists.\n Please try and check in the item list.");
                } else {
                    new Controller().addItemToDB(new Item(itemId, subCategoryId, itemName, itemPrice, itemStatus, creationDate));
                    reset();
                }


            }


        } else {
            String itemId = new Controller().generateId();
            String itemName = name.getText();
            int itemPrice = Integer.parseInt(price.getText());
            String itemStatus = status.getSelectedItem().toString();
            String subCategoryId = subCategoryList.get(category.getSelectedIndex()).getId();
            String createDate = date.getSelectedItem().toString();
            String createMonth = month.getSelectedItem().toString();
            String creatYear = year.getSelectedItem().toString();
            String creationDate = createDate + "/" + createMonth + "/" + creatYear;
            new Controller().addItemToDB(new Item(itemId, subCategoryId, itemName, itemPrice, itemStatus, creationDate));
            reset();
        }

    }
    //reset();
}//GEN-LAST:event_addItemActionPerformed

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    AdminWindow.addItemDialog.setVisible(false);
    AdminWindow.addItemDialog.getRootPane().removeAll();
    AdminWindow.addItemDialog.removeAll();
    AdminWindow.addItemDialog.dispose();
}//GEN-LAST:event_finishActionPerformed

private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_formKeyReleased

private void categoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_categoryFocusGained
// TODO add your handling code here:
}//GEN-LAST:event_categoryFocusGained

private void categoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_categoryKeyTyped

private void statusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_statusKeyTyped

private void barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //addItem.requestFocus();
            //addItemActionPerformed(null);
            if (barcode.getText().isEmpty()) {
            } else {
                if (new Controller().findIfExists(barcode.getText()) == true) {
                    JOptionPane.showMessageDialog(null, "ERROR \nItem Already Exists.\n Please try and check in the item list.");
                } else {
                    name.requestFocus();
                }
            }


        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            name.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_barcodeKeyPressed

private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            price.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            barcode.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            category.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_nameKeyPressed

private void priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            name.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            barcode.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            status.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_priceKeyPressed

private void statusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            category.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            price.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            year.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_statusKeyPressed

private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            month.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            status.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            addItem.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_yearKeyPressed

private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            date.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            category.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            year.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_monthKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addItem.requestFocus();
            addItemActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            addItem.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            category.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            month.requestFocus();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_dateKeyPressed

private void barcodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_barcodeFocusLost
// TODO add your handling code here:
}//GEN-LAST:event_barcodeFocusLost

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
            java.util.logging.Logger.getLogger(AddItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddItemDialog dialog = new AddItemDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addItem;
    private javax.swing.JTextField barcode;
    private javax.swing.JRadioButton barcodeReader;
    private javax.swing.JComboBox category;
    private javax.swing.JComboBox date;
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField name;
    private javax.swing.JTextField price;
    private javax.swing.JComboBox status;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables

    public void reset() {
        try {
            barcode.setText("");
            name.setText("");
            price.setText("");
            barcode.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
