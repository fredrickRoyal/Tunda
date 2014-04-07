/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddSubCategoryDialog.java
 *
 * Created on Nov 19, 2012, 11:52:22 PM
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
public class AddSubCategoryDialog extends javax.swing.JDialog {

    /** Creates new form AddSubCategoryDialog */
    ArrayList<MainCategory> mainCategoryList = new Controller().getMainCategoryList("all");
    String mainCat;//main category

    public AddSubCategoryDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        String currentDate = new Controller().getCurrentTimeStamp();
        ArrayList<String> dateTokens = new Controller().dateTokens(currentDate);
        date.setSelectedItem(dateTokens.get(0));
        month.setSelectedItem(dateTokens.get(1));
        year.setSelectedItem(dateTokens.get(2));
        try {
            if (!mainCategoryList.isEmpty()) {
                /* SORTING THE ARRAYLIST*/
                Collections.sort(mainCategoryList, new Comparator<MainCategory>() {

                    public int compare(MainCategory s1, MainCategory s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < mainCategoryList.size(); i++) {
                    mainCategory.addItem(mainCategoryList.get(i).getName());
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        /*==========================================================================*/

        /*=======================================================================*/

        /*Trying to implement a search property on the Category combobox*/
        try {

            mainCategory.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

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
                        mainCategoryList = new Controller().getMainCategoryList("all");
                    } else {

                        mainCategoryList = new Controller().getMainCategoryList(text);
                        mainCategoryList.add(0, new MainCategory("0", text, "0", "0"));
                    }
                    try {
                        if (!mainCategoryList.isEmpty()) {
                            Collections.sort(mainCategoryList, new Comparator<MainCategory>() {

                                public int compare(MainCategory s1, MainCategory s2) {
                                    return s1.getName().compareToIgnoreCase(s2.getName());
                                }
                            });

                            for (int i = 0; i < mainCategoryList.size(); i++) {
                                v.add(mainCategoryList.get(i).getName());
                            }
                            DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(v);
                            if (nextChar.isEmpty()) {
                            } else {
                                cboNewModel.setSelectedItem(v.get(0).toString().substring(0, v.get(0).toString().length() - 1));
                            }

                            mainCategory.setModel(cboNewModel);
                            mainCategory.setEditable(true);
                            mainCategory.setEnabled(true);
                            mainCategory.showPopup();
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
        jLabel3 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        mainCategory = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox();
        addSubCategory = new javax.swing.JButton();
        finish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Times New Roman", 1, 24));
        name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Main Category");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Status");

        status.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-active" }));
        status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusKeyPressed(evt);
            }
        });

        mainCategory.setEditable(true);
        mainCategory.setFont(new java.awt.Font("Times New Roman", 0, 18));
        mainCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainCategoryItemStateChanged(evt);
            }
        });
        mainCategory.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mainCategoryPropertyChange(evt);
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

        addSubCategory.setText("Add");
        addSubCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubCategoryActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mainCategory, 0, 259, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(year, 0, 125, Short.MAX_VALUE)))
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addComponent(addSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_nameActionPerformed

private void addSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubCategoryActionPerformed
// TODO add your handling code here:
    String subCategoryId = new Controller().generateId();
    String catName = name.getText();
    String catStatus = status.getSelectedItem().toString();
    String mainCategoryId = mainCategoryList.get(mainCategory.getSelectedIndex()).getId();
    String createDate = date.getSelectedItem().toString();
    String createMonth = month.getSelectedItem().toString();
    String creatYear = year.getSelectedItem().toString();
    String creationDate = createDate + "/" + createMonth + "/" + creatYear;
    if (catName.equals("") || catName.equals("  ")) {
        JOptionPane.showMessageDialog(null, "Please Enter Sub Category Name");
    } else {
        new Controller().addSubCategoryToDB(new SubCategory(subCategoryId, mainCategoryId, catName, catStatus, creationDate));
    }
    reset();
}//GEN-LAST:event_addSubCategoryActionPerformed

private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
// TODO add your handling code here:
    AdminWindow.addSubCategoryDialog.setVisible(false);
    AdminWindow.addSubCategoryDialog.getRootPane().removeAll();
    AdminWindow.addSubCategoryDialog.removeAll();
    AdminWindow.addSubCategoryDialog.dispose();
}//GEN-LAST:event_finishActionPerformed

private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            addSubCategory.requestFocus();
            addSubCategoryActionPerformed(null);
            
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            mainCategory.requestFocus();
            //JOptionPane.showMessageDialog(null, "down key pressed");
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_nameKeyPressed

private void statusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            addSubCategory.requestFocus();
            addSubCategoryActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_statusKeyPressed

private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            addSubCategory.requestFocus();
            addSubCategoryActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_dateKeyPressed

private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            addSubCategory.requestFocus();
            addSubCategoryActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_monthKeyPressed

private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
// TODO add your handling code here:
    try{
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            addSubCategory.requestFocus();
            addSubCategoryActionPerformed(null);
            
        }
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    
    }
}//GEN-LAST:event_yearKeyPressed

private void mainCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainCategoryItemStateChanged
// TODO add your handling code here:
}//GEN-LAST:event_mainCategoryItemStateChanged

private void mainCategoryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mainCategoryPropertyChange
// TODO add your handling code here:
}//GEN-LAST:event_mainCategoryPropertyChange

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
            java.util.logging.Logger.getLogger(AddSubCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSubCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSubCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSubCategoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddSubCategoryDialog dialog = new AddSubCategoryDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addSubCategory;
    private javax.swing.JComboBox date;
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox mainCategory;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox status;
    private javax.swing.JComboBox year;
    // End of variables declaration//GEN-END:variables

private void reset(){
try{
    name.setText("");
    name.requestFocus();
}catch(Exception ex){
    System.out.println(ex.getMessage());
}
}

}
