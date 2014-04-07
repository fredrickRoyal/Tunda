/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdminWindow.java
 *
 * Created on Nov 18, 2012, 11:15:21 AM
 */
package vpsposclient;

import java.awt.*;
import java.awt.Font;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
//AMD C-60 APU WITH RADEON(TM)0706-825442
//http://weblogs.java.net/blog/xuanyun/archive/2009/01/change_the_tab.html
//http://www.java-forums.org/new-java/39683-jtab-panes.html

/**
 *
 * @author royal
 */
public class AdminWindow extends javax.swing.JPanel {

    /** Creates new form AdminWindow */
    TableModel mainCategoryTableModel = new Controller().mainCategoryTableModel("all");
    TableModel subCategoryTableModel = new Controller().subCategoryTableModel("all");
    TableModel itemTableModel = new Controller().itemTableModel("all");
    TableModel uOMTableModel = new Controller().UOMTableModel("all");
    TableModel stockTableModel = new Controller().stockTableModel("all");
    TableModel cashierTableModel = new Controller().cashierTableModel();
    static MainCategoryAddDialog mainCategoryAddDialog;
    static AddSubCategoryDialog addSubCategoryDialog;
    static AddItemDialog addItemDialog;
    static AddUOMDialog addUOMDialog;
    static AddStockDialog addStockDialog;
    static CustomCollectionReport customCollectionReport;
    static ExpenditureReport expenditureReport;
    static ProductSalesDialog productSalesDialog;
    static UpdateMainCategory updateMainCategoryDialog;
    static UpdateSubCategory updateSubCategoryDialog;
    static UpdateItem updateItemDialog;
    static UpdateUOM updateUOMDialog;
    static UpdateCashier updateCashier;
    static UpdateStock updateStockDialog;
    static ViewStockDialog viewStockDialog;
    static AddSupplier addSupplier;
    static AddSupplierContactDialog addSupplierContactDialog;
    //String mainCategoryName = null;
    ///String mainCategoryId = null;
    ArrayList<Sale> salesList = new ArrayList<Sale>();

    public AdminWindow() {

        initComponents();
        try {
            supplierTable.setModel(new Controller().supplierTableModel());
            supplierContactsTable.setModel(new Controller().supplierContactTableModel());
            supplierProductsTable.setModel(new Controller().supplierProductTableModel());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            tabbedPane.setIconAt(0, new ImageIcon("images/icon.png"));

            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>MAIN CATEGORY</body></html>", maincategoryTab);
            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>SUB CATEGORY</body></html>", subcategoryTab);
            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>ITEMS</body></html>", itemTab);
            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>UOM</body></html>", UOMTab);
            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>STOCK</body></html>", stockTab);
            tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>CASHIER</body></html>", CashierTab);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


        //mainCategory Table settings
        mainCategoryTable.setGridColor(new Color(232, 225, 226));
        mainCategoryTable.setRowMargin(2);
        mainCategoryTable.setSelectionBackground(new Color(252, 96, 17));
        mainCategoryTable.setRowHeight(30);
        TableColumn mainCategoryTableCol1 = mainCategoryTable.getColumnModel().getColumn(0);
        mainCategoryTableCol1.setPreferredWidth(1);
        mainCategoryTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        mainCategoryTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
        mainCategoryTable.getTableHeader().setForeground(Color.BLUE);
        mainCategoryTable.getTableHeader().setBackground(new Color(241, 229, 238));


        /*mainCategoryTable.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent evt) {
        int row = mainCategoryTable.getSelectedRow();
        Object data = mainCategoryTable.getModel().getValueAt(row, 1);
        String mainCategoryId = data.toString();
        }
        });*/


        //sub Category Table settings
        subCategoryTable.setGridColor(new Color(232, 225, 226));
        subCategoryTable.setRowMargin(2);
        subCategoryTable.setSelectionBackground(new Color(252, 96, 17));
        subCategoryTable.setRowHeight(30);
        TableColumn subCategoryTableCol1 = subCategoryTable.getColumnModel().getColumn(0);
        subCategoryTableCol1.setPreferredWidth(1);
        subCategoryTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        subCategoryTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 22));
        subCategoryTable.getTableHeader().setForeground(Color.BLUE);
        subCategoryTable.getTableHeader().setBackground(new Color(241, 229, 238));


        //Item Table settings
        itemTable.setGridColor(new Color(232, 225, 226));
        itemTable.setRowMargin(2);
        itemTable.setSelectionBackground(new Color(252, 96, 17));
        itemTable.setRowHeight(30);
        TableColumn itemTableCol1 = itemTable.getColumnModel().getColumn(0);
        itemTableCol1.setPreferredWidth(1);
        itemTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        itemTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 22));
        itemTable.getTableHeader().setForeground(Color.BLUE);
        itemTable.getTableHeader().setBackground(new Color(241, 229, 238));

        //UOM Table settings
        uOMTable.setGridColor(new Color(232, 225, 226));
        uOMTable.setRowMargin(2);
        uOMTable.setSelectionBackground(new Color(252, 96, 17));
        uOMTable.setRowHeight(30);
        TableColumn uOMTableCol1 = uOMTable.getColumnModel().getColumn(0);
        uOMTableCol1.setPreferredWidth(1);
        uOMTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        uOMTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 22));
        uOMTable.getTableHeader().setForeground(Color.BLUE);
        uOMTable.getTableHeader().setBackground(new Color(241, 229, 238));


        //Stock Table settings
        stockTable.setGridColor(new Color(232, 225, 226));
        stockTable.setRowMargin(2);
        stockTable.setSelectionBackground(new Color(252, 96, 17));
        stockTable.setRowHeight(30);
        TableColumn stockTableCol1 = stockTable.getColumnModel().getColumn(0);
        //TableColumn stockTableCol5 = stockTable.getColumnModel().getColumn(5);
        stockTableCol1.setPreferredWidth(1);
        //stockTableCol5.setPreferredWidth(10);
        stockTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        stockTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 22));
        stockTable.getTableHeader().setForeground(Color.BLUE);
        stockTable.getTableHeader().setBackground(new Color(241, 229, 238));
        //Cashier Table setting

        cashierTable.setGridColor(new Color(232, 225, 226));
        cashierTable.setRowMargin(2);
        cashierTable.setSelectionBackground(new Color(252, 96, 17));
        cashierTable.setRowHeight(30);
        TableColumn cashierTableCol1 = cashierTable.getColumnModel().getColumn(0);
        cashierTableCol1.setPreferredWidth(1);
        cashierTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cashierTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 22));
        cashierTable.getTableHeader().setForeground(Color.BLUE);
        cashierTable.getTableHeader().setBackground(new Color(241, 229, 238));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        maincategoryTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainCategoryTable = new javax.swing.JTable();
        addNewCategory = new javax.swing.JButton();
        updateMainCategory = new javax.swing.JButton();
        deleteMainCategory = new javax.swing.JButton();
        searchMainCategory = new javax.swing.JFormattedTextField();
        jButton4 = new javax.swing.JButton();
        subcategoryTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subCategoryTable = new javax.swing.JTable();
        addSubCategory = new javax.swing.JButton();
        updateSubCategory = new javax.swing.JButton();
        deleteSubCategory = new javax.swing.JButton();
        searchSubCategryItem = new javax.swing.JFormattedTextField();
        jButton7 = new javax.swing.JButton();
        itemTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        addItem = new javax.swing.JButton();
        updateItem = new javax.swing.JButton();
        deleteItem = new javax.swing.JButton();
        searchItem = new javax.swing.JFormattedTextField();
        jButton6 = new javax.swing.JButton();
        UOMTab = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        uOMTable = new javax.swing.JTable();
        addUOM = new javax.swing.JButton();
        updateUOM = new javax.swing.JButton();
        deleteUOM = new javax.swing.JButton();
        searchUOM = new javax.swing.JFormattedTextField();
        jButton8 = new javax.swing.JButton();
        stockTab = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        addStock = new javax.swing.JButton();
        updateStock = new javax.swing.JButton();
        deleteStockItem = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        searchStockItem = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        CashierTab = new javax.swing.JPanel();
        addCashier = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        cashierTable = new javax.swing.JTable();
        reportsTab = new javax.swing.JPanel();
        dayTotalCollection = new javax.swing.JButton();
        customView = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        productSales = new javax.swing.JButton();
        viewStock = new javax.swing.JButton();
        poleDisplay = new javax.swing.JButton();
        itemList = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        supplierTab = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        supplierContactsTable = new javax.swing.JTable();
        addSupplierContact = new javax.swing.JButton();
        updateSupplierContact = new javax.swing.JButton();
        deleteSupplierContact = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        addSupplierproduct = new javax.swing.JButton();
        updateSupplierProduct = new javax.swing.JButton();
        deleteSupplierProduct = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        supplierProductsTable = new javax.swing.JTable();
        printSupplierList = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        addSupplies = new javax.swing.JButton();
        editSupplier = new javax.swing.JButton();
        deleteSupplier = new javax.swing.JButton();
        logout = new javax.swing.JLabel();
        changePassword = new javax.swing.JLabel();

        tabbedPane.setBackground(new java.awt.Color(255, 255, 204));
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setAlignmentX(1.0F);
        tabbedPane.setAlignmentY(1.0F);
        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabbedPane.setFont(new java.awt.Font("Times New Roman", 1, 14));

        mainCategoryTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        mainCategoryTable.setModel(mainCategoryTableModel);
        mainCategoryTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(mainCategoryTable);

        addNewCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addNewCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addNewCategory.setText("Add New");
        addNewCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addNewCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addNewCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addNewCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCategoryActionPerformed(evt);
            }
        });

        updateMainCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        updateMainCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateMainCategory.setText("Update");
        updateMainCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateMainCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateMainCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateMainCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMainCategoryActionPerformed(evt);
            }
        });

        deleteMainCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        deleteMainCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteMainCategory.setText("Delete");
        deleteMainCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteMainCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteMainCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteMainCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMainCategoryActionPerformed(evt);
            }
        });

        searchMainCategory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchMainCategory.setFont(new java.awt.Font("Times New Roman", 1, 24));
        searchMainCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchMainCategoryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchMainCategoryKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchMainCategoryKeyTyped(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jButton4.setText("Search");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maincategoryTabLayout = new javax.swing.GroupLayout(maincategoryTab);
        maincategoryTab.setLayout(maincategoryTabLayout);
        maincategoryTabLayout.setHorizontalGroup(
            maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincategoryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(maincategoryTabLayout.createSequentialGroup()
                        .addComponent(addNewCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateMainCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMainCategory)
                        .addGap(30, 30, 30)
                        .addComponent(searchMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        maincategoryTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addNewCategory, deleteMainCategory, updateMainCategory});

        maincategoryTabLayout.setVerticalGroup(
            maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincategoryTabLayout.createSequentialGroup()
                .addGroup(maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maincategoryTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addNewCategory)
                            .addComponent(updateMainCategory)
                            .addComponent(deleteMainCategory)))
                    .addGroup(maincategoryTabLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(maincategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );

        maincategoryTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addNewCategory, deleteMainCategory, updateMainCategory});

        tabbedPane.addTab("MAIN CATEGORY", maincategoryTab);

        subCategoryTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        subCategoryTable.setModel(subCategoryTableModel);
        jScrollPane2.setViewportView(subCategoryTable);

        addSubCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addSubCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addSubCategory.setText("Add New");
        addSubCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSubCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSubCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubCategoryActionPerformed(evt);
            }
        });

        updateSubCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        updateSubCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateSubCategory.setText("Update");
        updateSubCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateSubCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateSubCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSubCategoryActionPerformed(evt);
            }
        });

        deleteSubCategory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        deleteSubCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteSubCategory.setText("Delete");
        deleteSubCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSubCategory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteSubCategory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSubCategoryActionPerformed(evt);
            }
        });

        searchSubCategryItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchSubCategryItem.setFont(new java.awt.Font("Times New Roman", 1, 24));
        searchSubCategryItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSubCategryItemKeyReleased(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jButton7.setText("Search");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout subcategoryTabLayout = new javax.swing.GroupLayout(subcategoryTab);
        subcategoryTab.setLayout(subcategoryTabLayout);
        subcategoryTabLayout.setHorizontalGroup(
            subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subcategoryTabLayout.createSequentialGroup()
                .addGroup(subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subcategoryTabLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(addSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateSubCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSubCategory)
                        .addGap(18, 18, 18)
                        .addComponent(searchSubCategryItem, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subcategoryTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)))
                .addContainerGap())
        );

        subcategoryTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addSubCategory, deleteSubCategory, updateSubCategory});

        subcategoryTabLayout.setVerticalGroup(
            subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subcategoryTabLayout.createSequentialGroup()
                .addGroup(subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subcategoryTabLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchSubCategryItem, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jButton7)))
                    .addGroup(subcategoryTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(subcategoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateSubCategory)
                            .addComponent(addSubCategory)
                            .addComponent(deleteSubCategory))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(10, 10, 10))
        );

        subcategoryTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addSubCategory, deleteSubCategory, updateSubCategory});

        subcategoryTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton7, searchSubCategryItem});

        tabbedPane.addTab("SUB CATEGORY", subcategoryTab);

        itemTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        itemTable.setModel(itemTableModel);
        jScrollPane3.setViewportView(itemTable);

        addItem.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addItem.setText("Add New");
        addItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemActionPerformed(evt);
            }
        });

        updateItem.setFont(new java.awt.Font("Times New Roman", 0, 12));
        updateItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateItem.setText("Update");
        updateItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateItemActionPerformed(evt);
            }
        });

        deleteItem.setFont(new java.awt.Font("Times New Roman", 0, 12));
        deleteItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteItem.setText("Delete");
        deleteItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemActionPerformed(evt);
            }
        });

        searchItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchItem.setFont(new java.awt.Font("Times New Roman", 1, 24));
        searchItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchItemKeyReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jButton6.setText("Search");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout itemTabLayout = new javax.swing.GroupLayout(itemTab);
        itemTab.setLayout(itemTabLayout);
        itemTabLayout.setHorizontalGroup(
            itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addComponent(addItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        itemTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addItem, deleteItem, updateItem});

        itemTabLayout.setVerticalGroup(
            itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addGroup(itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(itemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addItem)
                            .addComponent(updateItem)
                            .addComponent(deleteItem))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("ITEMS", itemTab);

        uOMTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        uOMTable.setModel(uOMTableModel);
        jScrollPane4.setViewportView(uOMTable);

        addUOM.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addUOM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addUOM.setText("Add New");
        addUOM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addUOM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addUOM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addUOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUOMActionPerformed(evt);
            }
        });

        updateUOM.setFont(new java.awt.Font("Times New Roman", 0, 12));
        updateUOM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateUOM.setText("Update");
        updateUOM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateUOM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateUOM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateUOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUOMActionPerformed(evt);
            }
        });

        deleteUOM.setFont(new java.awt.Font("Times New Roman", 0, 12));
        deleteUOM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteUOM.setText("Delete");
        deleteUOM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteUOM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteUOM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteUOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUOMActionPerformed(evt);
            }
        });

        searchUOM.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchUOM.setFont(new java.awt.Font("Times New Roman", 1, 18));
        searchUOM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUOMKeyReleased(evt);
            }
        });

        jButton8.setText("Search");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UOMTabLayout = new javax.swing.GroupLayout(UOMTab);
        UOMTab.setLayout(UOMTabLayout);
        UOMTabLayout.setHorizontalGroup(
            UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UOMTabLayout.createSequentialGroup()
                .addGroup(UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UOMTabLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(addUOM, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateUOM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteUOM)
                        .addGap(30, 30, 30)
                        .addComponent(searchUOM, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UOMTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)))
                .addContainerGap())
        );

        UOMTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addUOM, deleteUOM, updateUOM});

        UOMTabLayout.setVerticalGroup(
            UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UOMTabLayout.createSequentialGroup()
                .addGroup(UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UOMTabLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchUOM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                    .addGroup(UOMTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UOMTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateUOM)
                            .addComponent(addUOM)
                            .addComponent(deleteUOM))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        tabbedPane.addTab("UOM", UOMTab);

        stockTable.setFont(new java.awt.Font("Times New Roman", 0, 16));
        stockTable.setModel(stockTableModel);
        jScrollPane5.setViewportView(stockTable);

        addStock.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addStock.setText("Add Stock");
        addStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockActionPerformed(evt);
            }
        });

        updateStock.setFont(new java.awt.Font("Times New Roman", 0, 12));
        updateStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateStock.setText("Update ");
        updateStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockActionPerformed(evt);
            }
        });

        deleteStockItem.setFont(new java.awt.Font("Times New Roman", 0, 12));
        deleteStockItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteStockItem.setText("Delete");
        deleteStockItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteStockItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteStockItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteStockItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockItemActionPerformed(evt);
            }
        });

        refresh.setFont(new java.awt.Font("Times New Roman", 0, 12));
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/refresh.png"))); // NOI18N
        refresh.setText("Refresh");
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refresh.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        refresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        searchStockItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        searchStockItem.setFont(new java.awt.Font("Times New Roman", 1, 24));
        searchStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStockItemKeyReleased(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jButton5.setText("Search");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockTabLayout = new javax.swing.GroupLayout(stockTab);
        stockTab.setLayout(stockTabLayout);
        stockTabLayout.setHorizontalGroup(
            stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockTabLayout.createSequentialGroup()
                .addGroup(stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockTabLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteStockItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh)
                        .addGap(18, 18, 18)
                        .addComponent(searchStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stockTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)))
                .addContainerGap())
        );

        stockTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addStock, deleteStockItem, refresh, updateStock});

        stockTabLayout.setVerticalGroup(
            stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addStock)
                        .addComponent(updateStock)
                        .addComponent(deleteStockItem)
                        .addComponent(refresh))
                    .addGroup(stockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );

        stockTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addStock, deleteStockItem, refresh, updateStock});

        tabbedPane.addTab("STOCK", stockTab);

        addCashier.setFont(new java.awt.Font("Times New Roman", 0, 12));
        addCashier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/add.png"))); // NOI18N
        addCashier.setText("Add");
        addCashier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCashier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCashier.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCashierActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cashierTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        cashierTable.setModel(cashierTableModel);
        jScrollPane6.setViewportView(cashierTable);

        javax.swing.GroupLayout CashierTabLayout = new javax.swing.GroupLayout(CashierTab);
        CashierTab.setLayout(CashierTabLayout);
        CashierTabLayout.setHorizontalGroup(
            CashierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashierTabLayout.createSequentialGroup()
                .addGroup(CashierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashierTabLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(addCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(CashierTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)))
                .addContainerGap())
        );

        CashierTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addCashier, jButton2, jButton3});

        CashierTabLayout.setVerticalGroup(
            CashierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashierTabLayout.createSequentialGroup()
                .addGroup(CashierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addCashier)
                        .addComponent(jButton2))
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );

        CashierTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addCashier, jButton2, jButton3});

        tabbedPane.addTab("CASHIER", CashierTab);

        reportsTab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        dayTotalCollection.setFont(new java.awt.Font("Times New Roman", 0, 12));
        dayTotalCollection.setForeground(new java.awt.Color(22, 1, 1));
        dayTotalCollection.setText("Day's Collections");
        dayTotalCollection.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dayTotalCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayTotalCollectionActionPerformed(evt);
            }
        });

        customView.setFont(new java.awt.Font("Times New Roman", 0, 12));
        customView.setForeground(new java.awt.Color(22, 1, 1));
        customView.setText("Custom Collection");
        customView.setToolTipText("customise total collection view");
        customView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customViewActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jButton1.setForeground(new java.awt.Color(22, 1, 1));
        jButton1.setText("Expenditure");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        productSales.setFont(new java.awt.Font("Times New Roman", 0, 12));
        productSales.setForeground(new java.awt.Color(22, 1, 1));
        productSales.setText("Product Sales");
        productSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productSalesActionPerformed(evt);
            }
        });

        viewStock.setFont(new java.awt.Font("Times New Roman", 0, 12));
        viewStock.setForeground(new java.awt.Color(22, 1, 1));
        viewStock.setText("Stock");
        viewStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStockActionPerformed(evt);
            }
        });

        poleDisplay.setFont(new java.awt.Font("Times New Roman", 0, 12));
        poleDisplay.setForeground(new java.awt.Color(22, 1, 1));
        poleDisplay.setText("Pole Display");
        poleDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        poleDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poleDisplayActionPerformed(evt);
            }
        });

        itemList.setFont(new java.awt.Font("Times New Roman", 0, 12));
        itemList.setForeground(new java.awt.Color(22, 1, 1));
        itemList.setText("Item List");
        itemList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 12));
        jButton9.setForeground(new java.awt.Color(22, 1, 1));
        jButton9.setText("Backup");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportsTabLayout = new javax.swing.GroupLayout(reportsTab);
        reportsTab.setLayout(reportsTabLayout);
        reportsTabLayout.setHorizontalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayTotalCollection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productSales)
                .addGap(18, 18, 18)
                .addComponent(viewStock)
                .addGap(18, 18, 18)
                .addComponent(itemList, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(poleDisplay)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        reportsTabLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {itemList, jButton1, jButton9, poleDisplay, productSales, viewStock});

        reportsTabLayout.setVerticalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayTotalCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customView, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(productSales)
                    .addComponent(viewStock)
                    .addComponent(poleDisplay)
                    .addComponent(itemList)
                    .addComponent(jButton9))
                .addContainerGap(441, Short.MAX_VALUE))
        );

        reportsTabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {customView, dayTotalCollection, itemList, jButton1, jButton9, poleDisplay, productSales, viewStock});

        tabbedPane.addTab("REPORTS", reportsTab);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 18));

        supplierContactsTable.setFont(new java.awt.Font("Times New Roman", 1, 14));
        supplierContactsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Contact Id", "Supplier", "Contact", "Creation Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierContactsTable.setGridColor(new java.awt.Color(204, 204, 204));
        supplierContactsTable.setRowHeight(30);
        supplierContactsTable.setSelectionBackground(new java.awt.Color(255, 153, 51));
        jScrollPane8.setViewportView(supplierContactsTable);

        addSupplierContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addSupplierContact.setText("Add");
        addSupplierContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSupplierContact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSupplierContact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addSupplierContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierContactActionPerformed(evt);
            }
        });

        updateSupplierContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateSupplierContact.setText("Edit");
        updateSupplierContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateSupplierContact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateSupplierContact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateSupplierContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSupplierContactActionPerformed(evt);
            }
        });

        deleteSupplierContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteSupplierContact.setText("Delete");
        deleteSupplierContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSupplierContact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteSupplierContact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteSupplierContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addSupplierContact, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateSupplierContact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSupplierContact)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplierContact, deleteSupplierContact, updateSupplierContact});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addSupplierContact)
                        .addComponent(updateSupplierContact))
                    .addComponent(deleteSupplierContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Supplier Contacts", jPanel4);

        addSupplierproduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/12.png"))); // NOI18N
        addSupplierproduct.setText("Add");
        addSupplierproduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSupplierproduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSupplierproduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addSupplierproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierproductActionPerformed(evt);
            }
        });

        updateSupplierProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update1.png"))); // NOI18N
        updateSupplierProduct.setText("Edit");
        updateSupplierProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateSupplierProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateSupplierProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateSupplierProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSupplierProductActionPerformed(evt);
            }
        });

        deleteSupplierProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete_page.png"))); // NOI18N
        deleteSupplierProduct.setText("Delete");
        deleteSupplierProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSupplierProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteSupplierProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteSupplierProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierProductActionPerformed(evt);
            }
        });

        supplierProductsTable.setFont(new java.awt.Font("Times New Roman", 1, 14));
        supplierProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Contact Id", "Supplier", "Product", "Creation Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierProductsTable.setGridColor(new java.awt.Color(204, 204, 204));
        supplierProductsTable.setRowHeight(30);
        supplierProductsTable.setSelectionBackground(new java.awt.Color(255, 153, 51));
        jScrollPane9.setViewportView(supplierProductsTable);

        printSupplierList.setFont(new java.awt.Font("Times New Roman", 0, 12));
        printSupplierList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/printer.png"))); // NOI18N
        printSupplierList.setText("Print");
        printSupplierList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printSupplierList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printSupplierList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        printSupplierList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printSupplierList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printSupplierListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(addSupplierproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateSupplierProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSupplierProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printSupplierList)))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplierproduct, deleteSupplierProduct, printSupplierList, updateSupplierProduct});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addSupplierproduct)
                        .addComponent(updateSupplierProduct))
                    .addComponent(deleteSupplierProduct)
                    .addComponent(printSupplierList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addSupplierproduct, deleteSupplierProduct, printSupplierList, updateSupplierProduct});

        jTabbedPane1.addTab("Supplier Products", jPanel5);

        supplierTable.setAutoCreateRowSorter(true);
        supplierTable.setFont(new java.awt.Font("Times New Roman", 1, 14));
        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No.", "ID", "NAME", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierTable.setGridColor(new java.awt.Color(204, 204, 204));
        supplierTable.setRowHeight(30);
        supplierTable.setSelectionBackground(new java.awt.Color(255, 153, 51));
        jScrollPane7.setViewportView(supplierTable);

        addSupplies.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/add.png"))); // NOI18N
        addSupplies.setText("Add");
        addSupplies.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSupplies.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSupplies.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addSupplies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuppliesActionPerformed(evt);
            }
        });

        editSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/update.png"))); // NOI18N
        editSupplier.setText("Edit");
        editSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editSupplier.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierActionPerformed(evt);
            }
        });

        deleteSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vpsposclient/images/delete.png"))); // NOI18N
        deleteSupplier.setText("Delete");
        deleteSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteSupplier.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(addSupplies, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editSupplier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSupplier))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplies, deleteSupplier, editSupplier});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplies, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteSupplier)
                    .addComponent(editSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addSupplies, deleteSupplier, editSupplier});

        jTabbedPane1.addTab("Supplier", jPanel3);

        javax.swing.GroupLayout supplierTabLayout = new javax.swing.GroupLayout(supplierTab);
        supplierTab.setLayout(supplierTabLayout);
        supplierTabLayout.setHorizontalGroup(
            supplierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                .addContainerGap())
        );
        supplierTabLayout.setVerticalGroup(
            supplierTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        tabbedPane.addTab("SUPPLIER", supplierTab);

        logout.setFont(new java.awt.Font("Times New Roman", 0, 14));
        logout.setForeground(new java.awt.Color(0, 0, 255));
        logout.setText("Logout");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });

        changePassword.setForeground(new java.awt.Color(0, 0, 255));
        changePassword.setText("Change PassWord");
        changePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changePasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changePasswordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(changePassword)
                        .addGap(30, 30, 30)
                        .addComponent(logout)
                        .addGap(199, 199, 199))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changePassword)
                    .addComponent(logout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void addStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockActionPerformed
// TODO add your handling code here:

    addStockDialog = new AddStockDialog(Welcome.mainWindow, true);
    addStockDialog.setTitle("Add new Stock");
    addStockDialog.setLocation(getWidth() / 4, getHeight() / 5);
    addStockDialog.setVisible(true);
}//GEN-LAST:event_addStockActionPerformed

private void addUOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUOMActionPerformed
// TODO add your handling code here:

    addUOMDialog = new AddUOMDialog(Welcome.mainWindow, true);
    addUOMDialog.setTitle("Add new UOM");
    addUOMDialog.setLocation(getWidth() / 4, getHeight() / 5);
    addUOMDialog.setVisible(true);
}//GEN-LAST:event_addUOMActionPerformed

private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
// TODO add your handling code here:
    addItemDialog = new AddItemDialog(Welcome.mainWindow, true);
    addItemDialog.setTitle("Add new Item");
    addItemDialog.setLocation(getWidth() / 6, getHeight() / 5);
    addItemDialog.setVisible(true);
}//GEN-LAST:event_addItemActionPerformed

private void addSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubCategoryActionPerformed
// TODO add your handling code here:
    addSubCategoryDialog = new AddSubCategoryDialog(Welcome.mainWindow, true);
    addSubCategoryDialog.setTitle("Add new Sub Category");
    addSubCategoryDialog.setLocation(getWidth() / 4, getHeight() / 5);
    addSubCategoryDialog.setVisible(true);
}//GEN-LAST:event_addSubCategoryActionPerformed

private void addNewCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCategoryActionPerformed
// TODO add your handling code here:

    mainCategoryAddDialog = new MainCategoryAddDialog(Welcome.mainWindow, true);
    mainCategoryAddDialog.setTitle("Add new Main Category");
    mainCategoryAddDialog.setLocation(getWidth() / 4, getHeight() / 5);
    mainCategoryAddDialog.setVisible(true);
}//GEN-LAST:event_addNewCategoryActionPerformed

private void deleteMainCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMainCategoryActionPerformed
// TODO add your handling code here:
    try {
        int row = mainCategoryTable.getSelectedRow();
        Object id = mainCategoryTable.getModel().getValueAt(row, 1);
        Object name = mainCategoryTable.getModel().getValueAt(row, 2);
        String mainCategoryId = id.toString();
        String mainCategoryName = name.toString();
        new Controller().deleteMainCategory(mainCategoryId, mainCategoryName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Main catgegory to delete");

    }

}//GEN-LAST:event_deleteMainCategoryActionPerformed

private void deleteSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSubCategoryActionPerformed
// TODO add your handling code here:
    try {
        int row = subCategoryTable.getSelectedRow();
        Object id = subCategoryTable.getModel().getValueAt(row, 1);
        Object name = subCategoryTable.getModel().getValueAt(row, 2);
        String subCategoryId = id.toString();
        String subCategoryName = name.toString();
        new Controller().deleteSubCategory(subCategoryId, subCategoryName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Sub catgegory to delete");

    }
}//GEN-LAST:event_deleteSubCategoryActionPerformed

private void deleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemActionPerformed
// TODO add your handling code here:
    try {
        int row = itemTable.getSelectedRow();
        Object id = itemTable.getModel().getValueAt(row, 1);
        Object name = itemTable.getModel().getValueAt(row, 2);
        String itemId = id.toString();
        String itemName = name.toString();
        new Controller().deleteItem(itemId, itemName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Item to delete");

    }
}//GEN-LAST:event_deleteItemActionPerformed

private void deleteStockItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockItemActionPerformed
// TODO add your handling code here:
    try {
        int row = stockTable.getSelectedRow();
        Object id = stockTable.getModel().getValueAt(row, 1);
        Object name = stockTable.getModel().getValueAt(row, 2);
        String itemId = id.toString();
        String itemName = name.toString();
        new Controller().deleteStockItem(itemId, itemName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Item to delete");

    }
}//GEN-LAST:event_deleteStockItemActionPerformed

private void deleteUOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUOMActionPerformed
// TODO add your handling code here:
    try {
        int row = uOMTable.getSelectedRow();
        Object id = uOMTable.getModel().getValueAt(row, 1);
        Object name = uOMTable.getModel().getValueAt(row, 2);
        String itemId = id.toString();
        String itemName = name.toString();
        new Controller().deleteUOM(itemId, itemName);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the UOM to delete");

    }
}//GEN-LAST:event_deleteUOMActionPerformed

private void updateMainCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMainCategoryActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = mainCategoryTable.getSelectedRow();
        Object id = mainCategoryTable.getModel().getValueAt(row, 1);
        Object name = mainCategoryTable.getModel().getValueAt(row, 2);
        Object status = mainCategoryTable.getModel().getValueAt(row, 3);
        Object creationDate = mainCategoryTable.getModel().getValueAt(row, 4);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        String CategoryId = id.toString();
        String CategoryName = name.toString();
        String categoryStatus = status.toString();
        updateMainCategoryDialog = new UpdateMainCategory(Welcome.mainWindow, true);
        updateMainCategoryDialog.setTitle("Update Main Category");
        UpdateMainCategory.id = CategoryId;
        UpdateMainCategory.name.setText(CategoryName);
        UpdateMainCategory.status.setSelectedItem(categoryStatus);
        UpdateMainCategory.date.setSelectedItem(date);
        UpdateMainCategory.month.setSelectedItem(month);
        UpdateMainCategory.year.setSelectedItem(year);
        updateMainCategoryDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateMainCategoryDialog.setVisible(true);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Main Category to Update");

    }


}//GEN-LAST:event_updateMainCategoryActionPerformed

private void updateSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSubCategoryActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = subCategoryTable.getSelectedRow();
        Object id = subCategoryTable.getModel().getValueAt(row, 1);
        Object name = subCategoryTable.getModel().getValueAt(row, 2);
        Object mainCat = subCategoryTable.getModel().getValueAt(row, 3);
        Object status = subCategoryTable.getModel().getValueAt(row, 4);
        Object creationDate = subCategoryTable.getModel().getValueAt(row, 5);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        String CategoryId = id.toString();
        String CategoryName = name.toString();
        String categoryStatus = status.toString();
        updateSubCategoryDialog = new UpdateSubCategory(Welcome.mainWindow, true);
        updateSubCategoryDialog.setTitle("Update Sub Category");
        UpdateSubCategory.subCategoryId = CategoryId;
        UpdateSubCategory.name.setText(CategoryName);
        UpdateSubCategory.mainCategory.setSelectedItem(mainCat);
        UpdateSubCategory.status.setSelectedItem(categoryStatus);
        UpdateSubCategory.date.setSelectedItem(date);
        UpdateSubCategory.month.setSelectedItem(month);
        UpdateSubCategory.year.setSelectedItem(year);
        updateSubCategoryDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateSubCategoryDialog.setVisible(true);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Sub Category to Update");

    }
}//GEN-LAST:event_updateSubCategoryActionPerformed

private void updateItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = itemTable.getSelectedRow();
        Object id = itemTable.getModel().getValueAt(row, 1);
        Object name = itemTable.getModel().getValueAt(row, 2);
        Object price = itemTable.getModel().getValueAt(row, 3);
        Object subCat = itemTable.getModel().getValueAt(row, 4);
        Object status = itemTable.getModel().getValueAt(row, 5);
        Object creationDate = itemTable.getModel().getValueAt(row, 6);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        updateItemDialog = new UpdateItem(Welcome.mainWindow, true);
        updateItemDialog.setTitle("Update Item");
        UpdateItem.itemId = id.toString();
        UpdateItem.name.setText(name.toString());
        UpdateItem.price.setText(price.toString());
        UpdateItem.category.setSelectedItem(subCat);
        UpdateItem.status.setSelectedItem(status);
        UpdateItem.date.setSelectedItem(date);
        UpdateItem.month.setSelectedItem(month);
        UpdateItem.year.setSelectedItem(year);
        updateItemDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateItemDialog.setVisible(true);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Item to Update");
    }


}//GEN-LAST:event_updateItemActionPerformed

private void updateUOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUOMActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = uOMTable.getSelectedRow();
        Object id = uOMTable.getModel().getValueAt(row, 1);
        Object name = uOMTable.getModel().getValueAt(row, 2);
        Object status = uOMTable.getModel().getValueAt(row, 3);
        Object creationDate = uOMTable.getModel().getValueAt(row, 4);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        updateUOMDialog = new UpdateUOM(Welcome.mainWindow, true);
        updateUOMDialog.setTitle("Update UOM");
        UpdateUOM.uOMId = id.toString();
        UpdateUOM.name.setText(name.toString());
        UpdateUOM.status.setSelectedItem(status);
        UpdateUOM.date.setSelectedItem(date);
        UpdateUOM.month.setSelectedItem(month);
        UpdateUOM.year.setSelectedItem(year);
        updateUOMDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateUOMDialog.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the UOM to Update");

    }
}//GEN-LAST:event_updateUOMActionPerformed

private void updateStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = stockTable.getSelectedRow();
        Object id = stockTable.getModel().getValueAt(row, 1);
        Object name = stockTable.getModel().getValueAt(row, 2);
        Object quantity = stockTable.getModel().getValueAt(row, 3);
        Object uOM = stockTable.getModel().getValueAt(row, 4);
        Object costPrice = stockTable.getModel().getValueAt(row, 5);
        Object creationDate = stockTable.getModel().getValueAt(row, 7);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        updateStockDialog = new UpdateStock(Welcome.mainWindow, true);
        updateStockDialog.setTitle("Update Stock Item");
        UpdateStock.stockItemId = id.toString();
        UpdateStock.item.setSelectedItem(name);
        UpdateStock.quantity.setText(quantity.toString());
        UpdateStock.uom.setSelectedItem(uOM);
        UpdateStock.costPrice.setText(costPrice.toString());
        UpdateStock.date.setSelectedItem(date);
        UpdateStock.month.setSelectedItem(month);
        UpdateStock.year.setSelectedItem(year);
        updateStockDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateStockDialog.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Stock Item to Update");

    }
}//GEN-LAST:event_updateStockActionPerformed

private void addCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCashierActionPerformed
// TODO add your handling code here:
    AddCashierDialog cashierAddDialog = new AddCashierDialog(Welcome.mainWindow, true);
    cashierAddDialog.setTitle("Add new Cashier");
    cashierAddDialog.setLocation(getWidth() / 4, getHeight() / 5);
    cashierAddDialog.setVisible(true);
}//GEN-LAST:event_addCashierActionPerformed

private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
// TODO add your handling code here:
    new Controller().logout();
}//GEN-LAST:event_logoutMouseClicked

private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
// TODO add your handling code here:
    logout.setForeground(new Color(255, 204, 102));
}//GEN-LAST:event_logoutMouseEntered

private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
// TODO add your handling code here:
    logout.setForeground(new Color(0, 0, 255));
}//GEN-LAST:event_logoutMouseExited

private void dayTotalCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayTotalCollectionActionPerformed
// TODO add your handling code here:
    try {
        boolean processing = true;
        do {
            dayTotalCollection.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            new Controller().generateTotalDayCollectionReport();
            processing = false;
        } while (processing == true);
        dayTotalCollection.setCursor(new Cursor(Cursor.HAND_CURSOR));
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }

}//GEN-LAST:event_dayTotalCollectionActionPerformed

private void customViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customViewActionPerformed
// TODO add your handling code here:
    customCollectionReport = new CustomCollectionReport(Welcome.mainWindow, true);
    customCollectionReport.setTitle("Customize total collection View");
    customCollectionReport.setLocation(getWidth() / 4, getHeight() / 5);
    customCollectionReport.setVisible(true);
}//GEN-LAST:event_customViewActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    expenditureReport = new ExpenditureReport(Welcome.mainWindow, true);
    expenditureReport.setTitle("Custom Expenditure View");
    expenditureReport.setLocation(getWidth() / 4, getHeight() / 5);
    expenditureReport.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void productSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productSalesActionPerformed
// TODO add your handling code here:
    productSalesDialog = new ProductSalesDialog(Welcome.mainWindow, true);
    productSalesDialog.setTitle("Individual Product Sales");
    productSalesDialog.setLocation(getWidth() / 4, getHeight() / 5);
    productSalesDialog.setVisible(true);
}//GEN-LAST:event_productSalesActionPerformed

private void changePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordMouseEntered
// TODO add your handling code here:
    changePassword.setForeground(new Color(255, 204, 102));
}//GEN-LAST:event_changePasswordMouseEntered

private void changePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordMouseExited
// TODO add your handling code here:
    changePassword.setForeground(new Color(0, 0, 255));
}//GEN-LAST:event_changePasswordMouseExited

private void changePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePasswordMouseClicked
// TODO add your handling code here:
    AdminChangePassWord adminChangePassWord = new AdminChangePassWord(Welcome.mainWindow, true);
    adminChangePassWord.setTitle("Change PassWord");
    adminChangePassWord.setLocation(getWidth() / 4, getHeight() / 5);
    adminChangePassWord.setVisible(true);
}//GEN-LAST:event_changePasswordMouseClicked

private void viewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStockActionPerformed
// TODO add your handling code here:
    try {
        viewStockDialog = new ViewStockDialog(Welcome.mainWindow, true);
        viewStockDialog.setTitle("View Openning and Closing Stock");
        viewStockDialog.setLocation(getWidth() / 4, getHeight() / 5);
        viewStockDialog.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }

}//GEN-LAST:event_viewStockActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    try {
        int row = cashierTable.getSelectedRow();
        Object id = cashierTable.getModel().getValueAt(row, 1);
        String cashierId = id.toString();
        new Controller().deleteCashier(cashierId);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Cashier to delete");

    }
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    try {
        int row = cashierTable.getSelectedRow();
        Object id = cashierTable.getModel().getValueAt(row, 1);
        Object fname = cashierTable.getModel().getValueAt(row, 2);
        Object lname = cashierTable.getModel().getValueAt(row, 3);
        Object creationDate = cashierTable.getModel().getValueAt(row, 4);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        updateCashier = new UpdateCashier(Welcome.mainWindow, true);
        updateCashier.setTitle("Update Cashier");
        UpdateCashier.id = id.toString();
        UpdateCashier.firstName.setText(fname.toString());
        UpdateCashier.lastName.setText(lname.toString());
        UpdateCashier.date.setSelectedItem(date);
        UpdateCashier.month.setSelectedItem(month);
        UpdateCashier.year.setSelectedItem(year);
        updateCashier.setLocation(getWidth() / 4, getHeight() / 5);
        updateCashier.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the UOM to Update");

    }
}//GEN-LAST:event_jButton2ActionPerformed

private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
// TODO add your handling code here:
    try {
        stockTable.setModel(new Controller().stockTableModel("all"));
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_refreshActionPerformed

private void poleDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poleDisplayActionPerformed
// TODO add your handling code here:
    try {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Port in use!");
        } else {
            System.out.println(portIdentifier.getName());

            SerialPort serialPort = (SerialPort) portIdentifier.open("ListPortClass", 300);
            int b = serialPort.getBaudRate();
            System.out.println(Integer.toString(b));
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            OutputStream mOutputToPort = serialPort.getOutputStream();
            InputStream mInputFromPort = serialPort.getInputStream();
            String mValue = "AT\r";
            System.out.println("beginning to Write . \r\n");
            mOutputToPort.write(mValue.getBytes());
            System.out.println("AT Command Written to Port. \r\n");
            mOutputToPort.flush();
            System.out.println("Waiting for Reply \r\n");
            Thread.sleep(500);
            byte mBytesIn[] = new byte[20];
            mInputFromPort.read(mBytesIn);
            mInputFromPort.read(mBytesIn);
            String value = new String(mBytesIn);
            System.out.println("Response from Serial Device: " + value);
            mOutputToPort.close();
            mInputFromPort.close();
        }

    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_poleDisplayActionPerformed

private void addSuppliesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuppliesActionPerformed
// TODO add your handling code here:
    try {
        addSupplier = new AddSupplier(Welcome.mainWindow, true);
        addSupplier.setTitle("Add new Supplier");
        addSupplier.setLocation(getWidth() / 4, getHeight() / 5);
        addSupplier.setVisible(true);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());

    }
}//GEN-LAST:event_addSuppliesActionPerformed

private void deleteSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierActionPerformed
// TODO add your handling code here:
    try {
        int row = supplierTable.getSelectedRow();
        Object id = supplierTable.getModel().getValueAt(row, 1);
        String supplierId = id.toString();
        boolean deleted = new Controller().deleteSupplier(supplierId);
        if (deleted == true) {
            JOptionPane.showMessageDialog(null, "Supplier Deleted Successfully");
            supplierTable.setModel(new Controller().supplierTableModel());
        } else {
            JOptionPane.showMessageDialog(null, "Supplier \nNOT\n deleted ");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Supplier to delete");

    }
}//GEN-LAST:event_deleteSupplierActionPerformed

private void editSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    UpdateSupplierDialog updateSupplierDialog = new UpdateSupplierDialog(Welcome.mainWindow, true);
    try {
        int row = supplierTable.getSelectedRow();
        Object id = supplierTable.getModel().getValueAt(row, 1);
        Object name = supplierTable.getModel().getValueAt(row, 2);
        Object creationDate = supplierTable.getModel().getValueAt(row, 3);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        UpdateSupplierDialog.id = id.toString();
        UpdateSupplierDialog.supplierName.setText(name.toString());
        UpdateSupplierDialog.date.setSelectedItem(date);
        UpdateSupplierDialog.month.setSelectedItem(month);
        UpdateSupplierDialog.year.setSelectedItem(year);
        updateSupplierDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateSupplierDialog.setVisible(true);

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_editSupplierActionPerformed

private void addSupplierContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierContactActionPerformed
// TODO add your handling code here:
    try {
        addSupplierContactDialog = new AddSupplierContactDialog(Welcome.mainWindow, true);
        addSupplierContactDialog.setTitle("Add new Supplier Contact");
        addSupplierContactDialog.setLocation(getWidth() / 4, getHeight() / 5);
        addSupplierContactDialog.setVisible(true);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_addSupplierContactActionPerformed

private void deleteSupplierContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierContactActionPerformed
// TODO add your handling code here:
    try {
        int row = supplierContactsTable.getSelectedRow();
        Object id = supplierContactsTable.getModel().getValueAt(row, 1);
        String contactId = id.toString();
        boolean deleted = new Controller().deleteSupplierContact(contactId);
        if (deleted == true) {
            JOptionPane.showMessageDialog(null, "Supplier Contact Deleted Successfully");
            supplierContactsTable.setModel(new Controller().supplierContactTableModel());
        } else {
            JOptionPane.showMessageDialog(null, "Contact \nNOT\n deleted ");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Contact to delete");

    }
}//GEN-LAST:event_deleteSupplierContactActionPerformed

private void updateSupplierContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSupplierContactActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    UpdateSupplierContactDialog updateSupplierContactDialog = new UpdateSupplierContactDialog(Welcome.mainWindow, true);
    try {
        int row = supplierContactsTable.getSelectedRow();
        Object id = supplierContactsTable.getModel().getValueAt(row, 1);
        Object supplier = supplierContactsTable.getModel().getValueAt(row, 2);
        Object supplierContact = supplierContactsTable.getModel().getValueAt(row, 3);
        Object creationDate = supplierContactsTable.getModel().getValueAt(row, 4);
        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        UpdateSupplierContactDialog.id = id.toString();
        UpdateSupplierContactDialog.supplier.setSelectedItem(supplier);
        UpdateSupplierContactDialog.contact.setText(supplierContact.toString());
        UpdateSupplierContactDialog.date.setSelectedItem(date);
        UpdateSupplierContactDialog.month.setSelectedItem(month);
        UpdateSupplierContactDialog.year.setSelectedItem(year);
        updateSupplierContactDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateSupplierContactDialog.setVisible(true);

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_updateSupplierContactActionPerformed

private void addSupplierproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierproductActionPerformed
// TODO add your handling code here:
    try {
        AddSupplierProductDialog addSupplierproductDialog = new AddSupplierProductDialog(Welcome.mainWindow, true);
        addSupplierproductDialog.setTitle("Add new Supplier Product");
        addSupplierproductDialog.setLocation(getWidth() / 4, getHeight() / 5);
        addSupplierproductDialog.setVisible(true);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_addSupplierproductActionPerformed

private void updateSupplierProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSupplierProductActionPerformed
// TODO add your handling code here:
    ArrayList<String> list = new ArrayList<String>();
    UpdateSupplierProductDialog updateSupplierProductDialog = new UpdateSupplierProductDialog(Welcome.mainWindow, true);
    try {
        int row = supplierProductsTable.getSelectedRow();
        Object id = supplierProductsTable.getModel().getValueAt(row, 1);
        Object supplier = supplierProductsTable.getModel().getValueAt(row, 2);
        Object supplierProduct = supplierProductsTable.getModel().getValueAt(row, 3);
        Object supplierPrice = supplierProductsTable.getModel().getValueAt(row, 4);
        Object uom = supplierProductsTable.getModel().getValueAt(row, 5);
        Object creationDate = supplierProductsTable.getModel().getValueAt(row, 6);

        list = new Controller().dateTokens(creationDate.toString());
        String date = list.get(0);
        String month = list.get(1);
        String year = list.get(2);
        UpdateSupplierProductDialog.id = id.toString();
        UpdateSupplierProductDialog.supplier.setSelectedItem(supplier);
        UpdateSupplierProductDialog.product.setText(supplierProduct.toString());
        UpdateSupplierProductDialog.price.setText(supplierPrice.toString());
        UpdateSupplierProductDialog.uom.setSelectedItem(uom);

        UpdateSupplierProductDialog.date.setSelectedItem(date);
        UpdateSupplierProductDialog.month.setSelectedItem(month);
        UpdateSupplierProductDialog.year.setSelectedItem(year);
        updateSupplierProductDialog.setLocation(getWidth() / 4, getHeight() / 5);
        updateSupplierProductDialog.setVisible(true);

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_updateSupplierProductActionPerformed

private void deleteSupplierProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierProductActionPerformed
// TODO add your handling code here:
    try {
        int row = supplierProductsTable.getSelectedRow();
        Object id = supplierProductsTable.getModel().getValueAt(row, 1);
        String contactId = id.toString();
        boolean deleted = new Controller().deleteSupplierProduct(contactId);
        if (deleted == true) {
            JOptionPane.showMessageDialog(null, "Supplier Product Deleted Successfully");
            supplierProductsTable.setModel(new Controller().supplierProductTableModel());
        } else {
            JOptionPane.showMessageDialog(null, "Product \nNOT\n deleted ");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Please Select the Product to delete");

    }

}//GEN-LAST:event_deleteSupplierProductActionPerformed

private void searchMainCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMainCategoryKeyPressed
// TODO add your handling code here:
   /* try {
    if(searchMainCategory.getText().isEmpty()){
    mainCategoryTable.setModel(new Controller().mainCategoryTableModel("all"));
    }
    else{
    mainCategoryTable.setModel(new Controller().mainCategoryTableModel(searchMainCategory.getText()));
    }
    } catch (Exception ex) {
    System.out.println(ex.getMessage());
    }*/
}//GEN-LAST:event_searchMainCategoryKeyPressed

private void searchMainCategoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMainCategoryKeyTyped
// TODO add your handling code here:
   /* try {
    if(searchMainCategory.getText().isEmpty()){
    mainCategoryTable.setModel(new Controller().mainCategoryTableModel("all"));
    }
    else{
    mainCategoryTable.setModel(new Controller().mainCategoryTableModel(searchMainCategory.getText()));
    }
    } catch (Exception ex) {
    System.out.println(ex.getMessage());
    }*/
}//GEN-LAST:event_searchMainCategoryKeyTyped

private void searchMainCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMainCategoryKeyReleased
// TODO add your handling code here:
    try {
        if (searchMainCategory.getText().isEmpty()) {
            mainCategoryTable.setModel(new Controller().mainCategoryTableModel("all"));
        } else {
            mainCategoryTable.setModel(new Controller().mainCategoryTableModel(searchMainCategory.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_searchMainCategoryKeyReleased

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
    try {
        if (searchMainCategory.getText().isEmpty()) {
            mainCategoryTable.setModel(new Controller().mainCategoryTableModel("all"));
        } else {
            mainCategoryTable.setModel(new Controller().mainCategoryTableModel(searchMainCategory.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton4ActionPerformed

private void searchStockItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchStockItemKeyReleased
// TODO add your handling code here:
    try {
        if (searchStockItem.getText().isEmpty()) {
            stockTable.setModel(new Controller().stockTableModel("all"));
        } else {
            stockTable.setModel(new Controller().stockTableModel(searchStockItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_searchStockItemKeyReleased

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
    try {
        if (searchStockItem.getText().isEmpty()) {
            stockTable.setModel(new Controller().stockTableModel("all"));
        } else {
            stockTable.setModel(new Controller().stockTableModel(searchStockItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton5ActionPerformed

private void searchItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchItemKeyReleased
// TODO add your handling code here:
    try {
        if (searchItem.getText().isEmpty()) {
            itemTable.setModel(new Controller().itemTableModel("all"));
        } else {
            itemTable.setModel(new Controller().itemTableModel(searchItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_searchItemKeyReleased

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO add your handling code here:
    try {
        if (searchItem.getText().isEmpty()) {
            itemTable.setModel(new Controller().itemTableModel("all"));
        } else {
            itemTable.setModel(new Controller().itemTableModel(searchItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton6ActionPerformed

private void searchSubCategryItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSubCategryItemKeyReleased
// TODO add your handling code here:
    try {
        if (searchSubCategryItem.getText().isEmpty()) {
            subCategoryTable.setModel(new Controller().subCategoryTableModel("all"));
        } else {
            subCategoryTable.setModel(new Controller().subCategoryTableModel(searchSubCategryItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_searchSubCategryItemKeyReleased

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO add your handling code here:
    try {
        if (searchSubCategryItem.getText().isEmpty()) {
            subCategoryTable.setModel(new Controller().subCategoryTableModel("all"));
        } else {
            subCategoryTable.setModel(new Controller().subCategoryTableModel(searchSubCategryItem.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton7ActionPerformed

private void searchUOMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchUOMKeyReleased
// TODO add your handling code here:
    try {
        if (searchUOM.getText().isEmpty()) {
            uOMTable.setModel(new Controller().UOMTableModel("all"));
        } else {
            uOMTable.setModel(new Controller().UOMTableModel(searchUOM.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_searchUOMKeyReleased

private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// TODO add your handling code here:
    try {
        if (searchUOM.getText().isEmpty()) {
            uOMTable.setModel(new Controller().UOMTableModel("all"));
        } else {
            uOMTable.setModel(new Controller().UOMTableModel(searchUOM.getText()));
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton8ActionPerformed

private void itemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListActionPerformed
// TODO add your handling code here:
    try {
        boolean processing = true;
        do {
            itemList.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            new Controller().generateItemListLine();
            processing = false;
        } while (processing == true);
        itemList.setCursor(new Cursor(Cursor.HAND_CURSOR));
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_itemListActionPerformed

private void printSupplierListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printSupplierListActionPerformed
// TODO add your handling code here:
    try {
        new Controller().getSupplierInfoReport();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_printSupplierListActionPerformed

private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
// TODO add your handling code here:
    try {
        boolean processing = true;
        JFileChooser chooseFolder = new JFileChooser();
        chooseFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseFolder.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Integer opt = chooseFolder.showSaveDialog(this);
        if (opt == JFileChooser.APPROVE_OPTION) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date date = new Date();
            String rootFolder = chooseFolder.getSelectedFile().toString();
            String backupdirectory = rootFolder + "/DataBaseBackUps/" + dateFormat.format(date);
            boolean backedUp;
            do {
                reportsTab.requestFocus();
                reportsTab.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                backedUp = new Controller().backupDB(backupdirectory);
                processing = false;
            } while (processing == true);
            reportsTab.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if (backedUp == true) {
                JOptionPane.showMessageDialog(this, "DataBase BackedUp Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "ERROR\n NOT \nBacked Successfully");
            }
        }


    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}//GEN-LAST:event_jButton9ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CashierTab;
    private javax.swing.JPanel UOMTab;
    private javax.swing.JButton addCashier;
    private javax.swing.JButton addItem;
    private javax.swing.JButton addNewCategory;
    private javax.swing.JButton addStock;
    private javax.swing.JButton addSubCategory;
    private javax.swing.JButton addSupplierContact;
    private javax.swing.JButton addSupplierproduct;
    private javax.swing.JButton addSupplies;
    private javax.swing.JButton addUOM;
    public static javax.swing.JTable cashierTable;
    private javax.swing.JLabel changePassword;
    private javax.swing.JButton customView;
    private javax.swing.JButton dayTotalCollection;
    private javax.swing.JButton deleteItem;
    private javax.swing.JButton deleteMainCategory;
    private javax.swing.JButton deleteStockItem;
    private javax.swing.JButton deleteSubCategory;
    private javax.swing.JButton deleteSupplier;
    private javax.swing.JButton deleteSupplierContact;
    private javax.swing.JButton deleteSupplierProduct;
    private javax.swing.JButton deleteUOM;
    private javax.swing.JButton editSupplier;
    private javax.swing.JButton itemList;
    private javax.swing.JPanel itemTab;
    public static javax.swing.JTable itemTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel logout;
    public static javax.swing.JTable mainCategoryTable;
    private javax.swing.JPanel maincategoryTab;
    private javax.swing.JButton poleDisplay;
    private javax.swing.JButton printSupplierList;
    private javax.swing.JButton productSales;
    private javax.swing.JButton refresh;
    public static javax.swing.JPanel reportsTab;
    private javax.swing.JFormattedTextField searchItem;
    private javax.swing.JFormattedTextField searchMainCategory;
    private javax.swing.JFormattedTextField searchStockItem;
    private javax.swing.JFormattedTextField searchSubCategryItem;
    private javax.swing.JFormattedTextField searchUOM;
    private javax.swing.JPanel stockTab;
    public static javax.swing.JTable stockTable;
    public static javax.swing.JTable subCategoryTable;
    private javax.swing.JPanel subcategoryTab;
    public static javax.swing.JTable supplierContactsTable;
    public static javax.swing.JTable supplierProductsTable;
    private javax.swing.JPanel supplierTab;
    public static javax.swing.JTable supplierTable;
    private javax.swing.JTabbedPane tabbedPane;
    public static javax.swing.JTable uOMTable;
    private javax.swing.JButton updateItem;
    private javax.swing.JButton updateMainCategory;
    private javax.swing.JButton updateStock;
    private javax.swing.JButton updateSubCategory;
    private javax.swing.JButton updateSupplierContact;
    private javax.swing.JButton updateSupplierProduct;
    private javax.swing.JButton updateUOM;
    private javax.swing.JButton viewStock;
    // End of variables declaration//GEN-END:variables
}
