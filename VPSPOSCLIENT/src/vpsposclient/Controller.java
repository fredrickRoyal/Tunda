/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * life is about sharing what God has given us.
 */
package vpsposclient;
//716:51:00 
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import java.util.*;
import javax.print.attribute.standard.MediaSizeName;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author royal
 */
public class Controller {

    String host;
    int port;

    public Controller() {

        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File("server.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                PrintWriter bw = new PrintWriter(fw);
                bw.println(1090);
                bw.append("localhost");
                bw.close();
            }
            FileReader fw = new FileReader(file.getAbsoluteFile());
            BufferedReader read = new BufferedReader(fw);
            while (true) {
                String line = read.readLine();
                if (line == null) {
                    break;
                } else {
                    list.add(line);
                }

            }
            port = Integer.parseInt(list.get(0));
            host = list.get(1);
            //ServerSettingDialog.p=port;
            //ServerSettingDialog.ip=host;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());


        }
    }

    public void logout() {
        Welcome.mainWindow.setContentPane(new LoginWindow());
        Welcome.mainWindow.setVisible(true);

    }

    public void adminLogin(String id, String userName) {
        if ((id.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Please get the right UserName and PassWord");
        } else {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "adminLogin^" + userName + "^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String confirm = in.readLine();
                if (confirm.equalsIgnoreCase("true")) {
                    Welcome.mainWindow.setContentPane(new AdminWindow());
                    Welcome.mainWindow.setVisible(true);
                } else if (confirm.equalsIgnoreCase("false")) {
                    JOptionPane.showMessageDialog(null, "Please get the right UserName and PassWord");

                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }


    }

    public boolean adminChangePassword(String oldPassWord, String newPassWord) {
        boolean changed = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "adminChangePassword^" + oldPassWord + "^" + newPassWord;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String confirm = in.readLine();
            if (confirm.equalsIgnoreCase("true")) {
                changed = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return changed;
    }

    public void cashierLogin(String id, String userName) {
        if ((id.equals("")) || (id.equals(" ")) || userName.equals("") || userName.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Please get the right UserName and PassWord");
        } else {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "cashierLogin^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cashier = in.readLine();
                if (cashier.equalsIgnoreCase("false")) {
                    JOptionPane.showMessageDialog(null, "Please get the right UserName and PassWord");
                } else {
                    SalesTerminal.cashierName = cashier;
                    SalesTerminal.cashierId = id;
                    Welcome.mainWindow.setContentPane(new SalesTerminal());
                    Welcome.mainWindow.setVisible(true);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public boolean validateCashier(String CashierId) {
        boolean valid = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "cashierLogin^" + CashierId;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String cashier = in.readLine();
            if (cashier.equalsIgnoreCase("false")) {
                valid = false;
            } else {
                valid = true;

            }

        } catch (Exception e) {
        }
        return valid;

    }
    /*this will deduct items quntantity  from stock every after each sale*/

    public boolean deductStock(Sale sale) {
        boolean update = false;
        String item = sale.getItem();
        int quantity = sale.getQuantity();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "deductStock^" + item + "^" + quantity;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String deducted = in.readLine();
            if (deducted.equalsIgnoreCase("true")) {
                //saleItem(sale);
                update = true;

            } else if (deducted.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "Item not  deducted");
            } else if (deducted.equalsIgnoreCase("unavailable")) {
                JOptionPane.showMessageDialog(null, "Available Stock can't accomodate this sale");

            } else if (deducted.equalsIgnoreCase("unknownItem")) {
                JOptionPane.showMessageDialog(null, "Unknown item in Stock");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return update;
    }

    public void recordReceipt(Receipt receipt) {
        String id = receipt.getId();
        int total = receipt.getTotal();
        int discount = receipt.getDiscount();
        int cash = receipt.getCash();
        int balanceDue = receipt.getBalanceDue();
        int change = receipt.getChange();
        String date = receipt.getDate();

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "recordReceipt^" + id + "^" + total + "^" + discount + "^" + cash + "^" + balanceDue + "^" + change + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
            } else if (added.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "not saved error");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
    }

    public void saleItem(Sale sale) {
        String item = sale.getItem();
        String receiptId = sale.getReceiptId();
        int quantity = sale.getQuantity();
        String cashier = sale.getCashier();
        String date = sale.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "saleItem^" + item + "^" + receiptId + "^" + quantity + "^" + cashier + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
            } else if (added.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "not saved error");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
    }

    public void updatesoldItem(String item, String receiptId, int quantity) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updatesoldItem^" + item + "^" + receiptId + "^" + quantity;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
            } else if (updated.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "not updated error");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
    }

    public boolean backupDB(String directory) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "backupDB^"+directory;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                return true;
            } else if (updated.equalsIgnoreCase("false")) {
                return false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    /*returns the list of UOM(Unit Of Measurement) to be displayed in combo box( dropdown list)
     *this will the data entrant to select the UOM from dropdown list.
     */

    public ArrayList<ClosingStock> getClosingStockItems(String date) {
        ArrayList<ClosingStock> list = new ArrayList<ClosingStock>();
        ArrayList<String> rowList = new ArrayList<String>();
        ArrayList<String> columnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getClosingStockItems^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String row = null;
            while (true) {
                row = in.readLine();
                if (row == null) {
                    break;
                } else {
                    rowList.add(row);
                }
            }

            if (rowList != null) {
                for (int i = 0; i < rowList.size(); i++) {
                    columnList = tokens(rowList.get(i));
                    if (columnList != null) {
                        String item = columnList.get(0);
                        int quantity = Integer.parseInt(columnList.get(1));
                        int amount = Integer.parseInt(columnList.get(2));
                        int openingStock = Integer.parseInt(columnList.get(3));
                        int closingStock = Integer.parseInt(columnList.get(4));
                        list.add(new ClosingStock(item, quantity, amount, openingStock, closingStock));
                    }
                }
            }
            /*SORTING*/
            Collections.sort(list, new Comparator<ClosingStock>() {

                public int compare(ClosingStock s1, ClosingStock s2) {
                    return s1.getItem().compareToIgnoreCase(s2.getItem());
                }
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return list;

    }

    public ArrayList<UOM> getUOMList() {
        ArrayList<UOM> uOMList = new ArrayList<UOM>();
        ArrayList<String> uOMRowList = new ArrayList<String>();
        ArrayList<String> uOMColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getUOMList^";
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String uOMRow = null;
            while (true) {
                uOMRow = in.readLine();
                if (uOMRow == null) {
                    break;
                } else {
                    uOMRowList.add(uOMRow);
                }
            }

            if (uOMRowList != null) {
                for (int i = 0; i < uOMRowList.size(); i++) {
                    uOMColumnList = tokens(uOMRowList.get(i));
                    if (uOMColumnList != null) {
                        String id = uOMColumnList.get(0);
                        String name = uOMColumnList.get(1);
                        String status = uOMColumnList.get(2);
                        String date = uOMColumnList.get(3);
                        uOMList.add(new UOM(id, name, status, date));
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return uOMList;
    }

    /*Returns a list of all items to be displayed in a Combobox*/
    public ArrayList<Item> getItemList(String searchText) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getItemList^" + searchText;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String itemRow = null;
            while (true) {
                itemRow = in.readLine();
                if (itemRow == null) {
                    break;
                } else {
                    itemRowList.add(itemRow);
                }

            }
            if (itemRowList != null) {
                for (int i = 0; i < itemRowList.size(); i++) {
                    itemColumnList = tokens(itemRowList.get(i));
                    if (itemColumnList != null) {
                        String id = itemColumnList.get(0);
                        String subCatId = itemColumnList.get(1);
                        String name = itemColumnList.get(2);
                        int price = Integer.parseInt(itemColumnList.get(3));
                        String status = itemColumnList.get(4);
                        String date = itemColumnList.get(5);
                        itemList.add(new Item(id, subCatId, name, price, status, date));

                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return itemList;
    }

    /*returns the list of Sub categogry to be displayed in combo box( dropdown list)
     *this will the data entrant to select the Sub category from dropdown list.
     */
    public ArrayList<SubCategory> getSubCategoryList(String searchText) {
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        ArrayList<String> subCategoryRowList = new ArrayList<String>();
        ArrayList<String> subCategoryColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getSubCategoryList^" + searchText;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String subCategoryRow = null;
            while (true) {
                subCategoryRow = in.readLine();
                if (subCategoryRow == null) {
                    break;
                } else {
                    subCategoryRowList.add(subCategoryRow);
                }

            }

            if (subCategoryRowList != null) {
                for (int i = 0; i < subCategoryRowList.size(); i++) {
                    subCategoryColumnList = tokens(subCategoryRowList.get(i));
                    if (subCategoryColumnList != null) {
                        String id = subCategoryColumnList.get(0);
                        String mainCatId = subCategoryColumnList.get(1);
                        String name = subCategoryColumnList.get(2);
                        String status = subCategoryColumnList.get(3);
                        String date = subCategoryColumnList.get(4);
                        subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));

                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return subCategoryList;
    }
    /*returns the list of main categogry to be displayed in combo box( dropdown list)
     *this will the data entrant to select the main category from dropdown list.
     */

    public ArrayList<MainCategory> getMainCategoryList(String searchText) {
        ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
        ArrayList<String> mainCategoryRowList = new ArrayList<String>();
        ArrayList<String> mainCategoryColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getMainCategoryList^" + searchText;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String mainCategoryRow = null;
            while (true) {
                mainCategoryRow = in.readLine();
                if (mainCategoryRow == null) {
                    break;
                } else {
                    mainCategoryRowList.add(mainCategoryRow);
                }
            }
            if (mainCategoryRowList != null) {

                for (int i = 0; i < mainCategoryRowList.size(); i++) {
                    mainCategoryColumnList = tokens(mainCategoryRowList.get(i));
                    if (mainCategoryColumnList != null) {
                        String id = mainCategoryColumnList.get(0);
                        String name = mainCategoryColumnList.get(1);
                        String status = mainCategoryColumnList.get(2);
                        String date = mainCategoryColumnList.get(3);
                        mainCategoryList.add(new MainCategory(id, name, status, date));

                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return mainCategoryList;
    }

    public void addStockToDB(Stock stock) {
        String item = stock.getItem();
        int quantity = stock.getQuantity();
        String uom = stock.getUOM();
        int costPrice = stock.getUnitCostPrice();
        String date = stock.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addStockToDB^" + item + "^" + quantity + "^" + uom + "^" + costPrice + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New Stock added succesfully");
                AdminWindow.stockTable.setModel(stockTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not saved: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void addItemToDB(Item item) {
        String id = item.getId();
        String subCatId = item.getSubCategoryId();
        String name = item.getName();
        int price = item.getPrice();
        String status = item.getStatus();
        String date = item.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addItemToDB^" + id + "^" + subCatId + "^" + name + "^" + price + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New Item added succesfully");
                AdminWindow.itemTable.setModel(itemTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not saved: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void addUOMToDB(UOM uOM) {
        String id = uOM.getId();
        String name = uOM.getName();
        String status = uOM.getStatus();
        String date = uOM.getDate();

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "ddUOMToDB^" + id + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New UOM added succesfully");
                AdminWindow.uOMTable.setModel(UOMTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not saved: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void addSubCategoryToDB(SubCategory subCategory) {
        String id = subCategory.getId();
        String mainCatId = subCategory.getMainCatId();
        String name = subCategory.getName();
        String status = subCategory.getStatus();
        String date = subCategory.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addSubCategoryToDB^" + id + "^" + mainCatId + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New Sub Category added succesfully");
                AdminWindow.subCategoryTable.setModel(subCategoryTableModel("all"));

            } else {
                JOptionPane.showMessageDialog(null, "Not saved: please try again");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void addMainCategoryToDB(String id, String name, String status, String date) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addMainCategoryToDB^" + id + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New main Category added succesfully");
                AdminWindow.mainCategoryTable.setModel(mainCategoryTableModel("all"));
            } else {

                JOptionPane.showMessageDialog(null, "Not saved: please try again");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void addCashierToDB(String id, String firstName, String lastName, String date) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addCashierToDB^" + id + "^" + firstName + "^" + lastName + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "New Cashier added succesfully");
                AdminWindow.cashierTable.setModel(cashierTableModel());
            } else {

                JOptionPane.showMessageDialog(null, "Not saved: please try again");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public boolean addCashierExpenditureToDB(CashierExpendure cashierExpendure) {
        boolean saved = false;
        String expenditureId = cashierExpendure.getExpenditureId();
        String cashierId = cashierExpendure.getCashierId();
        int amount = cashierExpendure.getAmount();
        String reason = cashierExpendure.getReason();
        String date = cashierExpendure.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addCashierExpenditureToDB^" + expenditureId + "^" + cashierId + "^" + amount + "^" + reason + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "New Cashier added succesfully");
                ExpenditureViewDialog.expenditureTable.setModel(this.cashierExpenditureTableModel(date));
                saved = true;
            } else {
                //JOptionPane.showMessageDialog(null, "Not saved: please try again");
            }

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return saved;

    }

    public boolean cashierSignOff(Summary summary) {
        boolean signedOff = false;
        String cashierId = summary.getCashierId();
        String signIn = summary.getSignIn();
        String signOut = summary.getSignOut();
        String date = summary.getDate();
        int totalCollection = summary.getTotalCollection();
        int totalExpenditure = summary.getTotalExpenditure();
        int cashAtHand = summary.getCashAtHand();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "cashierSignOff^" + cashierId + "^" + signIn + "^" + signOut + "^" + date + "^" + totalCollection + "^" + totalExpenditure + "^" + cashAtHand;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String summarised = in.readLine();
            if (summarised.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "New Cashier added succesfully");
                signedOff = true;
            } else {
                //JOptionPane.showMessageDialog(null, "Not saved: please try again");
            }

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return signedOff;

    }

    public TableModel cashierTableModel() {
        DefaultTableModel cashier = null;
        ArrayList<Cashier> cashierList = new ArrayList<Cashier>();
        ArrayList<String> cashierRowList = new ArrayList<String>();
        ArrayList<String> cashierColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "CASHIER ID", "FIRSTNAME", "LASTNAME", "CREATION DATE"};
        String[][] cashierRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "cashierTableModel^";
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cashierRow = null;
                while (true) {
                    cashierRow = in.readLine();
                    if (cashierRow == null) {
                        break;
                    } else {
                        cashierRowList.add(cashierRow);
                    }
                }
                if (cashierRowList != null) {
                    for (int i = 0; i < cashierRowList.size(); i++) {
                        cashierColumnList = tokens(cashierRowList.get(i));
                        if (cashierColumnList != null) {
                            String id = cashierColumnList.get(0);
                            String firstName = cashierColumnList.get(1);
                            String lastName = cashierColumnList.get(2);
                            String date = cashierColumnList.get(3);
                            cashierList.add(new Cashier(id, firstName, lastName, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (cashierList == null) {
                rowNumber = 12;
                cashierRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = cashierList.size() + 10;
                cashierRecord = new String[rowNumber][columnNames.length];
                for (int i = 0; i < cashierList.size(); i++) {
                    cashierRecord[i][0] = Integer.toString((i + 1));
                    cashierRecord[i][1] = cashierList.get(i).getId();
                    cashierRecord[i][2] = cashierList.get(i).getFirstName();
                    cashierRecord[i][3] = cashierList.get(i).getLastName();
                    cashierRecord[i][4] = cashierList.get(i).getDate();


                }
            }
            cashier = new DefaultTableModel(cashierRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return cashier;
    }

    public TableModel supplierTableModel() {
        DefaultTableModel supplier = null;
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        ArrayList<String> supplierRowList = new ArrayList<String>();
        ArrayList<String> supplierColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "SUPPLIER ID", "NAME", "CREATION DATE"};
        String[][] supplierRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "supplierTableModel^";
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String supplierRow = null;
                while (true) {
                    supplierRow = in.readLine();
                    if (supplierRow == null) {
                        break;
                    } else {
                        supplierRowList.add(supplierRow);
                    }
                }
                if (supplierRowList != null) {
                    for (int i = 0; i < supplierRowList.size(); i++) {
                        supplierColumnList = tokens(supplierRowList.get(i));
                        if (supplierColumnList != null) {
                            String id = supplierColumnList.get(0);
                            String name = supplierColumnList.get(1);
                            String date = supplierColumnList.get(2);
                            supplierList.add(new Supplier(id, name, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (supplierList == null) {
                rowNumber = 12;
                supplierRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = supplierList.size() + 10;
                supplierRecord = new String[rowNumber][columnNames.length];
                Collections.sort(supplierList, new Comparator<Supplier>() {

                    public int compare(Supplier s1, Supplier s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < supplierList.size(); i++) {
                    supplierRecord[i][0] = Integer.toString((i + 1));
                    supplierRecord[i][1] = supplierList.get(i).getId();
                    supplierRecord[i][2] = supplierList.get(i).getName();
                    supplierRecord[i][3] = supplierList.get(i).getDate();


                }
            }
            supplier = new DefaultTableModel(supplierRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return supplier;
    }

    public TableModel cashierExpenditureTableModel(String expenditureDate) {
        DefaultTableModel expenditure = null;
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        ArrayList<String> expenditureRowList = new ArrayList<String>();
        ArrayList<String> expenditureColumnList = new ArrayList<String>();
        String[] columnNames = {"Reason", "Amount", "Date"};
        String[][] expenditureRecord = null;
        int rowNumber = 0;
        int total = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "cashierExpenditureTableModel^" + expenditureDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cashierRow = null;
                while (true) {
                    cashierRow = in.readLine();
                    if (cashierRow == null) {
                        break;
                    } else {
                        expenditureRowList.add(cashierRow);
                    }
                }
                if (expenditureRowList != null) {
                    for (int i = 0; i < expenditureRowList.size(); i++) {
                        expenditureColumnList = tokens(expenditureRowList.get(i));
                        if (expenditureColumnList != null) {

                            String id = expenditureColumnList.get(0);
                            int amount = Integer.parseInt(expenditureColumnList.get(1));
                            String reason = expenditureColumnList.get(2);
                            String date = expenditureColumnList.get(3);
                            total += amount;
                            expenditureList.add(new CashierExpendure(id, amount, reason, date));


                        }

                    }

                }
                ExpenditureViewDialog.expenditure = this.formatCash(total);
                ExpenditureViewDialog.expenditureList = expenditureList;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error1");
            }
            if (expenditureList == null) {
                rowNumber = 12;
                expenditureRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = expenditureList.size() + 10;
                expenditureRecord = new String[rowNumber][columnNames.length];
                for (int i = 0; i < expenditureList.size(); i++) {
                    expenditureRecord[i][0] = expenditureList.get(i).getReason();
                    expenditureRecord[i][1] = formatCash(expenditureList.get(i).getAmount());
                    expenditureRecord[i][2] = expenditureList.get(i).getDate();


                }
            }
            expenditure = new DefaultTableModel(expenditureRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error2");

        }


        return expenditure;
    }

    public ArrayList<CashierExpendure> getTotalExpenditure(String fromDate, String toDate) {
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        ArrayList<String> expenditureRowList = new ArrayList<String>();
        ArrayList<String> expenditureColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getTotalExpenditure^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cashierRow = null;
                while (true) {
                    cashierRow = in.readLine();
                    if (cashierRow == null) {
                        break;
                    } else {
                        expenditureRowList.add(cashierRow);
                    }
                }
                if (expenditureRowList != null) {
                    for (int i = 0; i < expenditureRowList.size(); i++) {
                        expenditureColumnList = tokens(expenditureRowList.get(i));
                        if (expenditureColumnList != null) {

                            String id = expenditureColumnList.get(0);
                            int amount = Integer.parseInt(expenditureColumnList.get(1));
                            String reason = expenditureColumnList.get(2);
                            String date = expenditureColumnList.get(3);
                            expenditureList.add(new CashierExpendure(id, amount, reason, date));


                        }

                    }

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error1");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error2");

        }


        return expenditureList;
    }

    public ArrayList<CashierExpendure> getCustomCashierTotalExpenditure(String cashierId, String fromDate, String toDate) {
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        ArrayList<String> expenditureRowList = new ArrayList<String>();
        ArrayList<String> expenditureColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getCustomCashierTotalExpenditure^" + cashierId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String cashierRow = null;
                while (true) {
                    cashierRow = in.readLine();
                    if (cashierRow == null) {
                        break;
                    } else {
                        expenditureRowList.add(cashierRow);
                    }
                }
                if (expenditureRowList != null) {
                    for (int i = 0; i < expenditureRowList.size(); i++) {
                        expenditureColumnList = tokens(expenditureRowList.get(i));
                        if (expenditureColumnList != null) {

                            String id = expenditureColumnList.get(0);
                            int amount = Integer.parseInt(expenditureColumnList.get(1));
                            String reason = expenditureColumnList.get(2);
                            String date = expenditureColumnList.get(3);
                            expenditureList.add(new CashierExpendure(id, amount, reason, date));


                        }

                    }

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error1");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error2");

        }


        return expenditureList;
    }

    public TableModel mainCategoryTableModel(String searchText) {
        DefaultTableModel mainCategory = null;
        ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
        ArrayList<String> mainCategoryRowList = new ArrayList<String>();
        ArrayList<String> mainCategoryColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "CATEGORY ID", "CATEGORY NAME", "STATUS", "CREATION DATE"};
        String[][] mainCategoryRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "mainCategoryTableModel^" + searchText;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String mainCategoryRow = null;
                while (true) {
                    mainCategoryRow = in.readLine();
                    if (mainCategoryRow == null) {
                        break;
                    } else {
                        mainCategoryRowList.add(mainCategoryRow);
                    }
                }
                if (mainCategoryRowList != null) {
                    for (int i = 0; i < mainCategoryRowList.size(); i++) {
                        mainCategoryColumnList = tokens(mainCategoryRowList.get(i));
                        if (mainCategoryColumnList != null) {
                            String id = mainCategoryColumnList.get(0);
                            String name = mainCategoryColumnList.get(1);
                            String status = mainCategoryColumnList.get(2);
                            String date = mainCategoryColumnList.get(3);
                            mainCategoryList.add(new MainCategory(id, name, status, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (mainCategoryList == null) {
                rowNumber = 12;
                mainCategoryRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = mainCategoryList.size() + 10;
                mainCategoryRecord = new String[rowNumber][columnNames.length];
                /*SORTING*/
                Collections.sort(mainCategoryList, new Comparator<MainCategory>() {

                    public int compare(MainCategory s1, MainCategory s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < mainCategoryList.size(); i++) {
                    mainCategoryRecord[i][0] = Integer.toString((i + 1));
                    mainCategoryRecord[i][1] = mainCategoryList.get(i).getId();
                    mainCategoryRecord[i][2] = mainCategoryList.get(i).getName();
                    mainCategoryRecord[i][3] = mainCategoryList.get(i).getStatus();
                    mainCategoryRecord[i][4] = mainCategoryList.get(i).getDate();


                }
            }
            mainCategory = new DefaultTableModel(mainCategoryRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return mainCategory;
    }

    public TableModel subCategoryTableModel(String searchText) {
        DefaultTableModel subCategory = null;
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        ArrayList<String> subCategoryRowList = new ArrayList<String>();
        ArrayList<String> subCategoryColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "SUB CATEGORY ID", "SUB CATEGORY", "MAIN CATEGORY", "STATUS", "CREATION DATE"};
        String[][] subCategoryRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "subCategoryTableModel^" + searchText;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String subCategoryRow = null;
                while (true) {
                    subCategoryRow = in.readLine();
                    if (subCategoryRow == null) {
                        break;
                    } else {
                        subCategoryRowList.add(subCategoryRow);
                    }

                }

                if (subCategoryRowList != null) {
                    for (int i = 0; i < subCategoryRowList.size(); i++) {
                        subCategoryColumnList = tokens(subCategoryRowList.get(i));
                        if (subCategoryColumnList != null) {
                            String id = subCategoryColumnList.get(0);
                            String mainCatId = subCategoryColumnList.get(1);
                            String name = subCategoryColumnList.get(2);
                            String status = subCategoryColumnList.get(3);
                            String date = subCategoryColumnList.get(4);
                            subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (subCategoryList == null) {
                rowNumber = 12;
                subCategoryRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = subCategoryList.size() + 10;
                subCategoryRecord = new String[rowNumber][columnNames.length];
                /*SHORTING*/
                Collections.sort(subCategoryList, new Comparator<SubCategory>() {

                    public int compare(SubCategory s1, SubCategory s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < subCategoryList.size(); i++) {
                    subCategoryRecord[i][0] = Integer.toString((i + 1));
                    subCategoryRecord[i][1] = subCategoryList.get(i).getId();
                    subCategoryRecord[i][2] = subCategoryList.get(i).getName();
                    subCategoryRecord[i][3] = subCategoryList.get(i).getMainCatId();
                    subCategoryRecord[i][4] = subCategoryList.get(i).getStatus();
                    subCategoryRecord[i][5] = subCategoryList.get(i).getDate();


                }
            }
            subCategory = new DefaultTableModel(subCategoryRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }
        return subCategory;
    }

    public TableModel itemTableModel(String searchText) {
        DefaultTableModel item = null;
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "ITEM ID", "ITEM NAME", "PRICE", "CATEGORY", "STATUS", "CREATION DATE"};
        String[][] itemRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "itemTableModel^" + searchText;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String subCatId = itemColumnList.get(1);
                            String name = itemColumnList.get(2);
                            int price = Integer.parseInt(itemColumnList.get(3));
                            String status = itemColumnList.get(4);
                            String date = itemColumnList.get(5);
                            itemList.add(new Item(id, subCatId, name, price, status, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
            if (itemList == null) {
                rowNumber = 12;
                itemRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = itemList.size() + 10;
                itemRecord = new String[rowNumber][columnNames.length];
                /*SORTING*/
                Collections.sort(itemList, new Comparator<Item>() {

                    public int compare(Item s1, Item s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
                for (int i = 0; i < itemList.size(); i++) {
                    itemRecord[i][0] = Integer.toString((i + 1));
                    itemRecord[i][1] = itemList.get(i).getId();
                    itemRecord[i][2] = itemList.get(i).getName();
                    itemRecord[i][3] = Integer.toString(itemList.get(i).getPrice());
                    itemRecord[i][4] = itemList.get(i).getSubCategoryId();
                    itemRecord[i][5] = itemList.get(i).getStatus();
                    itemRecord[i][6] = itemList.get(i).getDate();


                }
            }

            item = new DefaultTableModel(itemRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return item;
    }

    public TableModel totalCollectionTableModel(String cashierId, String date) {
        DefaultTableModel itemTable = null;
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        String[] columnNames = {"Item Code", "Item Name", "Quantity", "Amount"};
        String[][] itemRecord = null;
        int rowNumber = 0;
        int total = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "totalCollectionTableModel^" + cashierId + "^" + date;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            itemList.add(new Item(id, name, quantity, amount));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
            if (itemList == null) {
                rowNumber = 12;
                itemRecord = new String[rowNumber][columnNames.length];

            } else {
                System.out.println(itemList.size());
                rowNumber = itemList.size() + 10;
                itemRecord = new String[rowNumber][columnNames.length];
                for (int i = 0; i < itemList.size(); i++) {
                    itemRecord[i][0] = itemList.get(i).getId();
                    itemRecord[i][1] = itemList.get(i).getName();
                    itemRecord[i][2] = itemList.get(i).getQuantity();
                    itemRecord[i][3] = new Controller().formatCash(Integer.parseInt(itemList.get(i).getAmount()));
                    total += Integer.parseInt(itemList.get(i).getAmount());

                }

                CashierTotalCollectionReport.total = new Controller().formatCash(total);
            }

            itemTable = new DefaultTableModel(itemRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemTable;
    }

    public ArrayList<Item> getDayTotalCollection(String date) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "generalDayTotalCollection^" + date;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            String totalCostPrice = itemColumnList.get(4);
                            itemList.add(new Item(id, name, quantity, amount, Integer.parseInt(totalCostPrice)));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getCustomDateTotalCollection(String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getCustomDateTotalCollection^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            String totalcostPrice = itemColumnList.get(4);
                            itemList.add(new Item(id, name, quantity, amount, Integer.parseInt(totalcostPrice)));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getCustomCashierDateTotalCollection(String cashierId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getCustomCashierDateTotalCollection^" + cashierId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            int totalCostPrice = Integer.parseInt(itemColumnList.get(4));
                            itemList.add(new Item(id, name, quantity, amount, totalCostPrice));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getMainCategoryCollection(String mainCategoryId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getMainCategoryCollection^" + mainCategoryId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            itemList.add(new Item(id, name, quantity, amount));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getCashierMainCategoryCollection(String cashierId, String mainCategoryId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getCashierMainCategoryCollection^" + cashierId + "^" + mainCategoryId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            itemList.add(new Item(id, name, quantity, amount));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getSubCategoryCollection(String subCategoryId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getSubCategoryCollection^" + subCategoryId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            itemList.add(new Item(id, name, quantity, amount));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> getCashierSubCategoryCollection(String cashierId, String subCategoryId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> itemRowList = new ArrayList<String>();
        ArrayList<String> itemColumnList = new ArrayList<String>();
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "getCashierSubCategoryCollection^" + cashierId + "^" + subCategoryId + "^" + fromDate + "^" + toDate;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String itemRow = null;
                while (true) {
                    itemRow = in.readLine();
                    if (itemRow == null) {
                        break;
                    } else {
                        itemRowList.add(itemRow);
                    }

                }
                if (itemRowList != null) {
                    System.out.println(itemRowList.size());
                    for (int i = 0; i < itemRowList.size(); i++) {
                        itemColumnList = tokens(itemRowList.get(i));
                        if (itemColumnList != null) {
                            String id = itemColumnList.get(0);
                            String name = itemColumnList.get(1);
                            String quantity = itemColumnList.get(2);
                            String amount = itemColumnList.get(3);
                            itemList.add(new Item(id, name, quantity, amount));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return itemList;
    }

    public TableModel UOMTableModel(String searchText) {
        DefaultTableModel uOM = null;
        ArrayList<UOM> uOMList = new ArrayList<UOM>();
        ArrayList<String> uOMRowList = new ArrayList<String>();
        ArrayList<String> uOMColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "UOM ID", "UOM NAME", "STATUS", "CREATION DATE"};
        String[][] uOMRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "UOMTableModel^" + searchText;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String uOMRow = null;
                while (true) {
                    uOMRow = in.readLine();
                    if (uOMRow == null) {
                        break;
                    } else {
                        uOMRowList.add(uOMRow);
                    }
                }

                if (uOMRowList != null) {
                    for (int i = 0; i < uOMRowList.size(); i++) {
                        uOMColumnList = tokens(uOMRowList.get(i));
                        if (uOMColumnList != null) {
                            String id = uOMColumnList.get(0);
                            String name = uOMColumnList.get(1);
                            String status = uOMColumnList.get(2);
                            String date = uOMColumnList.get(3);
                            uOMList.add(new UOM(id, name, status, date));
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
            if (uOMList == null) {
                rowNumber = 12;
                uOMRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = uOMList.size() + 10;
                uOMRecord = new String[rowNumber][columnNames.length];
                for (int i = 0; i < uOMList.size(); i++) {
                    uOMRecord[i][0] = Integer.toString((i + 1));
                    uOMRecord[i][1] = uOMList.get(i).getId();
                    uOMRecord[i][2] = uOMList.get(i).getName();
                    uOMRecord[i][3] = uOMList.get(i).getStatus();
                    uOMRecord[i][4] = uOMList.get(i).getDate();


                }
            }
            uOM = new DefaultTableModel(uOMRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "");

        }
        return uOM;
    }

    public TableModel stockTableModel(String searchText) {
        DefaultTableModel stock = null;
        ArrayList<Stock> stockList = new ArrayList<Stock>();
        ArrayList<String> stockRowList = new ArrayList<String>();
        ArrayList<String> stockColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "ITEM ID", "ITEM", "QUANTITY", "UOM", "COST PRICE", "SELLING PRICE", "CREATION DATE"};
        String[][] stockRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "stockTableModel^" + searchText;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String stockRow = null;
                while (true) {
                    stockRow = in.readLine();
                    if (stockRow == null) {
                        break;
                    } else {
                        stockRowList.add(stockRow);
                    }
                }


                if (stockRowList != null) {
                    for (int i = 0; i < stockRowList.size(); i++) {
                        stockColumnList = tokens(stockRowList.get(i));
                        if (stockColumnList != null) {
                            String itemId = stockColumnList.get(0);
                            String item = stockColumnList.get(1);
                            int quantity = Integer.parseInt(stockColumnList.get(2));
                            String uom = stockColumnList.get(3);
                            int costPrice = Integer.parseInt(stockColumnList.get(4));
                            int sellingPrice = Integer.parseInt(stockColumnList.get(5));
                            String date = stockColumnList.get(6);
                            stockList.add(new Stock(itemId, item, quantity, uom, costPrice, sellingPrice, date));
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");
            }
            if (stockList == null) {
                rowNumber = 12;
                stockRecord = new String[rowNumber][columnNames.length];

            } else {

                rowNumber = stockList.size() + 10;
                stockRecord = new String[rowNumber][columnNames.length];
                /*SORTING*/
                Collections.sort(stockList, new Comparator<Stock>() {

                    public int compare(Stock s1, Stock s2) {
                        return s1.getItem().compareToIgnoreCase(s2.getItem());
                    }
                });
                for (int i = 0; i < stockList.size(); i++) {
                    stockRecord[i][0] = Integer.toString((i + 1));
                    stockRecord[i][1] = stockList.get(i).getItemId();
                    stockRecord[i][2] = stockList.get(i).getItem();
                    stockRecord[i][3] = Integer.toString(stockList.get(i).getQuantity());
                    stockRecord[i][4] = stockList.get(i).getUOM();
                    stockRecord[i][5] = Integer.toString(stockList.get(i).getUnitCostPrice());
                    stockRecord[i][6] = Integer.toString(stockList.get(i).getSellingPrice());
                    stockRecord[i][7] = stockList.get(i).getDate();


                }
            }
            stock = new DefaultTableModel(stockRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return stock;
    }
    /*return list of items  being  sold*/

    public TableModel getItem(ArrayList<Sale> salesList) {
        String[] table_headers = {"ITEMID", "ITEM", "QUANTIY", "UNIT PRICE", "TOTAL"};

        ArrayList<String> salesColumnList = new ArrayList<String>();
        DefaultTableModel sale = new DefaultTableModel(table_headers, 0) {

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String item = null;
        String itemQuantity = null;
        String price = "0";
        String totalPrice = "0";
        int total = 0;
        if (salesList != null) {
            for (int i = 0; i < salesList.size(); i++) {
                try {
                    Socket conn = new Socket(host, port);
                    PrintWriter out = new PrintWriter(conn.getOutputStream());
                    String message = "getItem^" + salesList.get(i).getItem();

                    out.println(message);
                    out.flush();
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String salesRow = in.readLine();

                    if (salesRow != null) {
                        salesColumnList = tokens(salesRow);
                        if (salesColumnList != null) {
                            item = salesColumnList.get(0);
                            price = salesColumnList.get(1);
                            itemQuantity = Integer.toString(salesList.get(i).getQuantity());

                            totalPrice = Integer.toString((Integer.parseInt(price) * Integer.parseInt(itemQuantity)));
                            String[] row = {salesList.get(i).getItem(), item, itemQuantity, price, totalPrice};
                            //String itemLine = item + "\t" + itemQuantity + "\t" + price + "\t" + totalPrice;
                            sale.addRow(row);
                            //generateReceipt(itemLine);
                            //JOptionPane.showMessageDialog(null, itemLine);
                            SalesTerminal.itemLine.add(new Item(item, itemQuantity, Integer.parseInt(price), totalPrice));
                            total += Integer.parseInt(totalPrice);
                            //SalesTerminal.salesTotal.setText(Integer.toString(total));
                            SalesTerminal.salesTotal.setText(formatCash(total));
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "item does not exist");
                        if (salesList != null) {
                            salesList.remove(i);
                        } else {
                            sale.setRowCount(sale.getRowCount() + 10);
                        }

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            sale.setRowCount(sale.getRowCount() + 10);


        }
        return sale;
    }

    public void deleteCashier(String id) {
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  Cashier");
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteCashier^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Cashier deleted successfully");
                    AdminWindow.cashierTable.setModel(cashierTableModel());
                    AdminWindow.subCategoryTable.setModel(cashierTableModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Cashier not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public boolean deleteSupplier(String id) {
        boolean supplierDeleted = false;
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  Supplier");
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteSupplier^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    supplierDeleted = true;
                } else {
                    /*SupplierDeleted remainds false*/
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
        return supplierDeleted;
    }

    public void deleteMainCategory(String id, String name) {
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name);
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteMainCategory^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Main category " + name + " deleted successfully");
                    AdminWindow.mainCategoryTable.setModel(mainCategoryTableModel("all"));
                    AdminWindow.subCategoryTable.setModel(subCategoryTableModel("all"));
                } else {
                    JOptionPane.showMessageDialog(null, "Main category " + name + " not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void deleteSubCategory(String id, String name) {
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name);
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteSubCategory^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Sub category " + name + " deleted successfully");
                    AdminWindow.subCategoryTable.setModel(subCategoryTableModel("all"));
                    AdminWindow.itemTable.setModel(itemTableModel("all"));
                } else {
                    JOptionPane.showMessageDialog(null, "Sub category " + name + " not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void deleteItem(String id, String name) {
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name);
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteItem^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Item " + name + " deleted successfully");
                    AdminWindow.itemTable.setModel(itemTableModel("all"));
                    AdminWindow.stockTable.setModel(stockTableModel("all"));
                } else {
                    JOptionPane.showMessageDialog(null, "Item " + name + " not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void deleteStockItem(String id, String name) {

        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name);
        if (deleteComformation == 0) {

            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteStockItem^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Item " + name + " deleted successfully");
                    AdminWindow.stockTable.setModel(stockTableModel("all"));
                } else {
                    JOptionPane.showMessageDialog(null, "Item " + name + " not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void deleteUOM(String id, String name) {

        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name);
        if (deleteComformation == 0) {

            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteUOM^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "UOM " + name + " deleted successfully");
                    AdminWindow.uOMTable.setModel(UOMTableModel("all"));
                    AdminWindow.stockTable.setModel(stockTableModel("all"));
                } else {
                    JOptionPane.showMessageDialog(null, "UOM " + name + " not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void deleteExpenditure(String id, String date) {

        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete \n this EXPENDITURE");
        if (deleteComformation == 0) {

            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteExpenditure^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    JOptionPane.showMessageDialog(null, "Expenditure deleted successfully");
                    ExpenditureViewDialog.expenditureTable.setModel(this.cashierExpenditureTableModel(date));
                } else {
                    JOptionPane.showMessageDialog(null, "Expenditure not deleted");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
    }

    public void updateMainCategory(String id, String name, String status, String date) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateMainCategory^" + id + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "main Category UPDATED succesfully");
                AdminWindow.mainCategoryTable.setModel(mainCategoryTableModel("all"));
                AdminWindow.subCategoryTable.setModel(subCategoryTableModel("all"));
            } else {

                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void updateSubCategory(SubCategory subCategory) {
        String id = subCategory.getId();
        String mainCatId = subCategory.getMainCatId();
        String name = subCategory.getName();
        String status = subCategory.getStatus();
        String date = subCategory.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateSubCategory^" + id + "^" + mainCatId + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "Sub Category UPDATED succesfully");
                AdminWindow.subCategoryTable.setModel(subCategoryTableModel("all"));
                AdminWindow.itemTable.setModel(itemTableModel("all"));

            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void updateItem(Item item) {
        String id = item.getId();
        String subCatId = item.getSubCategoryId();
        String name = item.getName();
        int price = item.getPrice();
        String status = item.getStatus();
        String date = item.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateItem^" + id + "^" + subCatId + "^" + name + "^" + price + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "Item UPDATED succesfully");
                AdminWindow.itemTable.setModel(itemTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void updateCashier(Cashier cashier) {
        String id = cashier.getId();
        String firstName = cashier.getFirstName();
        String lastName = cashier.getLastName();
        String date = cashier.getDate();

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateCashier^" + id + "^" + firstName + "^" + lastName + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "Cashier UPDATED succesfully");
                AdminWindow.cashierTable.setModel(cashierTableModel());
            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public boolean updateSupplier(Supplier supplier) {
        String id = supplier.getId();
        String name = supplier.getName();
        String date = supplier.getDate();
        boolean supplierUpdated = false;

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateSupplier^" + id + "^" + name + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                supplierUpdated = true;
            } else {
                /*supplierUpdated remains false*/
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return supplierUpdated;

    }

    public void updateUOM(UOM uOM) {
        String id = uOM.getId();
        String name = uOM.getName();
        String status = uOM.getStatus();
        String date = uOM.getDate();

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateUOM^" + id + "^" + name + "^" + status + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "UOM UPDATED succesfully");
                AdminWindow.uOMTable.setModel(UOMTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void updateExpenditure(CashierExpendure cashierExpendure) {
        String id = cashierExpendure.getCashierId();
        int amount = cashierExpendure.getAmount();
        String reason = cashierExpendure.getReason();
        String date = cashierExpendure.getDate();

        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateExpenditure^" + id + "^" + amount + "^" + reason + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "Expenditure UPDATED succesfully");
                ExpenditureViewDialog.expenditureTable.setModel(this.cashierExpenditureTableModel(date));
            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public void updateStock(Stock stock) {
        String item = stock.getItem();
        int quantity = stock.getQuantity();
        String uom = stock.getUOM();
        int costPrice = stock.getUnitCostPrice();
        String date = stock.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateStock^" + item + "^" + quantity + "^" + uom + "^" + costPrice + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                JOptionPane.showMessageDialog(null, "Stock Item  UPDATED succesfully");
                AdminWindow.stockTable.setModel(stockTableModel("all"));
            } else {
                JOptionPane.showMessageDialog(null, "Not UPDATED: please try again");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    public String generateId() {
        String id = null;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "generateId^";
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String generatedId = in.readLine();
            if (generatedId != null) {
                id = generatedId;
            } else {
                JOptionPane.showMessageDialog(null, "Error in generating Id");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return id;
    }

    public String initialise() {
        String id = null;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "initialise^";
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String initialised = in.readLine();
            if (initialised.equalsIgnoreCase("true")) {
                System.out.println("All tables created");
            } else if (initialised.equalsIgnoreCase("false")) {
                System.out.println("All tables not created");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return id;
    }

    private ArrayList<String> tokens(String message) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(message, "^");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;

    }

    public ArrayList<String> dateTokens(String message) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(message, "/");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;

    }

    public String getCurrentTimeStamp() {
        String[] months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        int date = (Integer.parseInt(strDate.substring(0, 2)));
        String currentMonth = months[Integer.parseInt(strDate.substring(3, 5)) - 1];
        String year = strDate.substring(6, 10);

        return date + "/" + currentMonth + "/" + year;
    }
    public String getMonth(int monthInDigits){
        String month=Integer.toHexString(monthInDigits);
        String[] months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        String currentMonth = months[monthInDigits];
        month=currentMonth;
        return month;
     }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(unformatCash(input));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String formatCash(int cash) {
        String formatedCash = null;
        NumberFormat formatter = new DecimalFormat("#,###,###");
        formatedCash = formatter.format(cash);
        return formatedCash;
    }

    public String unformatCash(String cash) {
        String unformatedCash = null;
        try {
            unformatedCash = cash.replaceAll(",", "");
        } catch (Exception e) {
        }
        return unformatedCash;
    }

    public boolean deleteItemFromList(String itemId, String receiptId, int quantity) {
        boolean deleted = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "deleteItemFromList^" + itemId + "^" + receiptId + "^" + quantity;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
                deleted = true;
            } else if (updated.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "not updated error");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return deleted;
    }
    /*if the item has been removed from the  itemline on the at the time pof sale, the it should added back to the
     * corresponding quantity in stock.
     */

    public boolean reAddStock(String itemId, int quantity) {
        boolean addedBack = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "reAddStock^" + itemId + "^" + quantity;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
                addedBack = true;
            } else if (updated.equalsIgnoreCase("false")) {
                addedBack = false;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return addedBack;

    }

    public boolean removeItemFromSales(String itemId, String receiptId) {
        boolean removed = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "removeItemFromSales^" + itemId + "^" + receiptId;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                //JOptionPane.showMessageDialog(null, "sale Saved");
                removed = true;
            } else if (updated.equalsIgnoreCase("false")) {
                JOptionPane.showMessageDialog(null, "not updated error");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return removed;
    }

    /*1*/ public void printReport() throws IOException, PrintException {
        String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
        //JOptionPane.showMessageDialog(null, "Default printer: " + defaultPrinter);
        System.out.println("Default printer: " + defaultPrinter);

        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        FileInputStream in = new FileInputStream(new File("ReportReceipt.pdf"));
        //FileInputStream in = new FileInputStream(new File("ReportReceipt.txt"));

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(2));
        pras.add(MediaSizeName.ISO_B4);

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(in, flavor, null);

        DocPrintJob job = service.createPrintJob();
        PrintJobWatcher pjw = new PrintJobWatcher(job);
        job.print(doc, pras);
        pjw.waitForDone();
        in.close();

        // send FF to eject the page
        InputStream ff = new ByteArrayInputStream("\f".getBytes());
        Doc docff = new SimpleDoc(ff, flavor, null);
        DocPrintJob jobff = service.createPrintJob();
        pjw = new PrintJobWatcher(jobff);
        jobff.print(docff, null);
        pjw.waitForDone();
    }

    public void generateReceipt(ArrayList<Item> itemLine, String receiptCode, String total, String cash, String change, String cashier, String date) {

        String outFileName = "ReportReceipt.pdf";
        ReceiptReportFactory.itemList = new ArrayList<ReceiptBean>();
        HashMap hm = new HashMap();
        //PrintRequestAttributeSet hm = new HashPrintRequestAttributeSet();

        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String price = Integer.toString(itemLine.get(i).getPrice());
                String amount = itemLine.get(i).getAmount();
                ReceiptReportFactory.itemList.add(new ReceiptBean(receiptCode, date, itemName, quantity, price, amount, total, cash, change, cashier));
            }
            InputStream input = getClass().getResourceAsStream("jrxml/ReportReceipt.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(ReceiptReportFactory.generateCollection()));
            JasperViewer jv = new JasperViewer(print);
            jv.setVisible(false);
            // Create a PDF exporter
            //JasperPrintManager.printReport(print, false);
            if (print != null) {
                JasperPrintManager.printReport(print, false);
            }
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateClosingStockReport(String date) {

        String outFileName = "closing_stock.pdf";
        ClosingStockFactory.itemList = new ArrayList<ClosingStock>();
        ArrayList<ClosingStock> itemLine = new Controller().getClosingStockItems(date);
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String item = itemLine.get(i).getItem();
                int quantity = itemLine.get(i).getQuantity();
                int amount = itemLine.get(i).getAmount();
                int openingStock = itemLine.get(i).getOpeningStock();
                int closingStock = itemLine.get(i).getClosingStock();
                ClosingStockFactory.itemList.add(new ClosingStock(item, quantity, amount, openingStock, closingStock));
            }
            ClosingStockFactory.date = date;
            InputStream input = getClass().getResourceAsStream("jrxml/ClosingStock.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(ClosingStockFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateTotalDayCollectionReport() {

        String outFileName = "CollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getDayTotalCollection(getCurrentTimeStamp());

        int total = 0;
        int totalProfit = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                int profit = Integer.parseInt(itemLine.get(i).getAmount()) - itemLine.get(i).getTotalCostPrice();
                total += Integer.parseInt(amount);
                totalProfit += profit;
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, formatCash(Integer.parseInt(amount)), formatCash(profit), formatCash(totalProfit)));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = getCurrentTimeStamp();
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage() + "kaso");
        }


    }

    public void generateItemListLine() {

        String outFileName = "ItemListLine.pdf";
        ItemListFactory.itemList = new ArrayList<ItemListBean>();
        ArrayList<Item> itemLine = this.getItemList("all");
        HashMap hm = new HashMap();
        try {
            /*SORTING*/
            Collections.sort(itemLine, new Comparator<Item>() {

                public int compare(Item s1, Item s2) {
                    return s1.getName().compareToIgnoreCase(s2.getName());
                }
            });
            for (int i = 0; i < itemLine.size(); i++) {
                String item = itemLine.get(i).getName();
                String price = Integer.toString(itemLine.get(i).getPrice());
                ItemListFactory.itemList.add(new ItemListBean(item, price));
            }
            InputStream input = getClass().getResourceAsStream("jrxml/ItemList.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(ItemListFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage() + "kaso");
        }


    }

    public void generateCustomDateTotalCollectionReport(String fromDate, String toDate) {

        String outFileName = "CustomDateCollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getCustomDateTotalCollection(fromDate, toDate);
        int total = 0;
        int totalProfit = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                int profit = Integer.parseInt(itemLine.get(i).getAmount()) - itemLine.get(i).getTotalCostPrice();
                total += Integer.parseInt(amount);
                totalProfit += profit;
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, formatCash(Integer.parseInt(amount)), formatCash(profit), formatCash(totalProfit)));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "----" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage() + "kasoma");
        }


    }

    public void generateCustomCashierDateTotalCollectionReport(String cashierId, String fromDate, String toDate) {

        String outFileName = "CustomCashierDateReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getCustomCashierDateTotalCollection(cashierId, fromDate, toDate);
        int total = 0;
        int totalProfit = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                int profit = Integer.parseInt(itemLine.get(i).getAmount()) - itemLine.get(i).getTotalCostPrice();
                total += Integer.parseInt(amount);
                totalProfit += profit;
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, formatCash(Integer.parseInt(amount)), formatCash(profit), formatCash(totalProfit)));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "---" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateMainCategoryCollectionReport(String mainCatId, String fromDate, String toDate) {

        String outFileName = "MainCategoryCollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getMainCategoryCollection(mainCatId, fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                total += Integer.parseInt(amount);
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, amount));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "---" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateSubCategoryCollectionReport(String subCatId, String fromDate, String toDate) {

        String outFileName = "SubCategoryCollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getSubCategoryCollection(subCatId, fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                total += Integer.parseInt(amount);
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, amount));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "---" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateCashierSubCategoryCollectionReport(String cashierId, String subCatId, String fromDate, String toDate) {

        String outFileName = "CashierSubCategoryCollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getCashierSubCategoryCollection(cashierId, subCatId, fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                total += Integer.parseInt(amount);
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, amount));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "---" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateCashierMainCategoryCollectionReport(String cashierId, String mainCatId, String fromDate, String toDate) {

        String outFileName = "CashierMainCategoryCollectionReport.pdf";
        TotalCollectionReportFactory.itemList = new ArrayList<TotalCollectionBean>();
        ArrayList<Item> itemLine = new Controller().getCashierMainCategoryCollection(cashierId, mainCatId, fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < itemLine.size(); i++) {
                String id = itemLine.get(i).getId();
                String itemName = itemLine.get(i).getName();
                String quantity = itemLine.get(i).getQuantity();
                String amount = itemLine.get(i).getAmount();
                total += Integer.parseInt(amount);
                TotalCollectionReportFactory.itemList.add(new TotalCollectionBean(id, itemName, quantity, amount));
            }
            TotalCollectionReportFactory.total = formatCash(total);
            TotalCollectionReportFactory.date = fromDate + "---" + toDate;
            InputStream input = getClass().getResourceAsStream("jrxml/Report.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalCollectionReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateCustomCashierDateTotalExpenditureReport(String cashierId, String fromDate, String toDate) {

        String outFileName = "CashierExpenditreReport.pdf";
        TotalExpenditureReportFactory.expenditureList = new ArrayList<TotalExpenditureBean>();
        ArrayList<CashierExpendure> expenditureLine = new Controller().getCustomCashierTotalExpenditure(cashierId, fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < expenditureLine.size(); i++) {
                int amount = expenditureLine.get(i).getAmount();
                String reason = expenditureLine.get(i).getReason();
                String date = expenditureLine.get(i).getDate();
                total += amount;
                TotalExpenditureReportFactory.expenditureList.add(new TotalExpenditureBean(formatCash(amount), reason, date));
            }
            TotalExpenditureReportFactory.total = formatCash(total);
            TotalExpenditureReportFactory.dateLimit = fromDate + "---" + toDate;
            TotalExpenditureReportFactory.title = "TOTAL CASHIER EXPENDITURE AS ON";
            TotalExpenditureReportFactory.cashier = "CASHIER: " + expenditureLine.get(0).getCashierId() + "\tID: " + cashierId;
            InputStream input = getClass().getResourceAsStream("jrxml/CashierTotalExpenditureReport.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalExpenditureReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }

    public void generateDateTotalExpenditureReport(String fromDate, String toDate) {

        String outFileName = "ExpenditreReport.pdf";
        TotalExpenditureReportFactory.expenditureList = new ArrayList<TotalExpenditureBean>();
        ArrayList<CashierExpendure> expenditureLine = new Controller().getTotalExpenditure(fromDate, toDate);
        int total = 0;
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < expenditureLine.size(); i++) {
                int amount = expenditureLine.get(i).getAmount();
                String reason = expenditureLine.get(i).getReason();
                String date = expenditureLine.get(i).getDate();
                total += amount;
                TotalExpenditureReportFactory.expenditureList.add(new TotalExpenditureBean(formatCash(amount), reason, date));
            }
            TotalExpenditureReportFactory.total = formatCash(total);
            TotalExpenditureReportFactory.dateLimit = fromDate + "---" + toDate;
            TotalExpenditureReportFactory.title = "TOTAL EXPENDITURE REPORT AS ON";
            TotalExpenditureReportFactory.cashier = "";
            InputStream input = getClass().getResourceAsStream("jrxml/CashierTotalExpenditureReport.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(TotalExpenditureReportFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }


    /*6*/ public void printWithDesktop() {
        try {
            File receipt = new File("ReportReceipt.pdf");
            //File receipt = new File("ReportReceipt.txt");
            Desktop desktop = Desktop.getDesktop();
            desktop.print(receipt);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    /* public void generateJasperReceipt() {
    String fileName = "C:\\Users\\Royal\\Documents\\NetBeansProjects\\VPSPOSCLIENT\\src\\vpsposclient\\ReportReceipt.jasper";
    String outFileName = "ReportReceipt.pdf";
    HashMap hm = new HashMap();
    try {
    // Fill the report using an empty data source
    JasperPrint print = JasperFillManager.fillReport(fileName, hm, new JRBeanCollectionDataSource(ReceiptReportFactory.generateCollection()));
    
    // Create a PDF exporter
    JRExporter exporter = new JRPdfExporter();
    
    // Configure the exporter (set output file name and print object)
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
    
    // Export the PDF file
    exporter.exportReport();
    
    } catch (JRException e) {
    e.printStackTrace();
    System.exit(1);
    } catch (Exception e) {
    e.printStackTrace();
    System.exit(1);
    }
    
    }*/
    /* public void generateReceiptTextFile(ArrayList<Item> itemLine, String receiptCode, String total, String cash, String change, String cashier, String date) {
    String outFileName = "ReportReceipt.txt";
    HashMap hm = new HashMap();
    ReceiptReportFactory.itemList = new ArrayList<ReceiptBean>();
    try {
    for (int i = 0; i < itemLine.size(); i++) {
    String itemName = itemLine.get(i).getName();
    String quantity = itemLine.get(i).getQuantity();
    String price = Integer.toString(itemLine.get(i).getPrice());
    String amount = itemLine.get(i).getAmount();
    ReceiptReportFactory.itemList.add(new ReceiptBean(receiptCode, date, itemName, quantity, price, amount, total, cash, change));
    }
    //JOptionPane.showMessageDialog(null, ClassLoader.getSystemResource("vpsposclient/jrxml/ReportReceipt.jrxml"));
    //JasperDesign jasperDesign = JRXmlLoader.load(getFilePath("jrxml/ReportReceipt.jrxml", 9));
    //JasperReport jasperReport = (JasperReport) JasperCompileManager.compileReport(jasperDesign);
    InputStream input = getClass().getResourceAsStream("jrxml/ReportReceipt.jrxml");
    JasperDesign design = JRXmlLoader.load(input);
    JasperReport report = JasperCompileManager.compileReport(design);
    JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(ReceiptReportFactory.generateCollection()));
    
    JRTextExporter exporterTxt = new JRTextExporter();
    exporterTxt.setParameter(JRExporterParameter.JASPER_PRINT, print);
    
    exporterTxt.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
    exporterTxt.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(5.5));
    exporterTxt.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(11));
    
    exporterTxt.exportReport();
    
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(null, ex.getMessage());
    System.out.println(ex.getMessage());
    }
    }*/
    public TableModel search(String category, String firstItemCharacters) {
        DefaultTableModel searchItemTableModel = null;
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> searchRowList = new ArrayList<String>();
        ArrayList<String> searchColumnList = new ArrayList<String>();
        String[] columnNames = {"CODE", "ITEM NAME"};
        String[][] searchRecord = null;
        int rowNumber = 0;
        //if (!(firstItemCharacters.isEmpty() || firstItemCharacters.equalsIgnoreCase(" "))) {
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = null;
            if (firstItemCharacters.isEmpty()) {
                message = "searchItem^" + "all";
            } else {
                message = "searchItem^" + category + "^" + firstItemCharacters;
            }
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String searchRow = null;
            while (true) {
                searchRow = in.readLine();
                if (searchRow == null) {
                    break;
                } else {
                    searchRowList.add(searchRow);
                }
            }

            if (searchRowList != null) {
                for (int i = 0; i < searchRowList.size(); i++) {
                    searchColumnList = tokens(searchRowList.get(i));
                    if (searchColumnList != null) {
                        String id = searchColumnList.get(0);
                        String name = searchColumnList.get(1);
                        itemList.add(new Item(id, name));
                    }
                }
            }
            if (itemList == null) {
                rowNumber = 6;
                searchRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = itemList.size() + 10;
                searchRecord = new String[rowNumber][columnNames.length];
                for (int i = 0; i < itemList.size(); i++) {
                    //searchRecord[i][0] = Integer.toString((i + 1));
                    searchRecord[i][0] = itemList.get(i).getId();
                    searchRecord[i][1] = itemList.get(i).getName();


                }
            }
            searchItemTableModel = new DefaultTableModel(searchRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //} else {
        //   JOptionPane.showMessageDialog(null, "Item can't be found");
        //}
        return searchItemTableModel;
    }
    /*NEW CUSTOMISITION*/

    public boolean addSupplier(Supplier supplier) {
        boolean supplierAdded = false;
        String id = supplier.getId();
        String name = supplier.getName();
        String date = supplier.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addSupplier^" + id + "^" + "^" + name + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                supplierAdded = true;
            } else {
                /*supplierAdded remains false*/
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return supplierAdded;
    }

    public boolean addSupplierContact(SupplierContact contact) {
        boolean contactAdded = false;
        String id = contact.getId();
        String supplier = contact.getSupplier();
        String supplierContact = contact.getContact();
        String date = contact.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addSupplierContact^" + id + "^" + supplier + "^" + supplierContact + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                contactAdded = true;
            } else {
                /*contactAdded remains false*/
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return contactAdded;
    }

    public boolean updateSupplierContact(SupplierContact contact) {
        boolean contactUpdated = false;
        String id = contact.getId();
        String supplier = contact.getSupplier();
        String supplierContact = contact.getContact();
        String date = contact.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateSupplierContact^" + id + "^" + supplier + "^" + supplierContact + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                contactUpdated = true;
            } else {
                /*supplierUpdated remains false*/
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return contactUpdated;

    }

    public boolean deleteSupplierContact(String id) {
        boolean contactDeleted = false;
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  Contact");
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteSupplierContact^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    contactDeleted = true;
                } else {
                    /*SupplierDeleted remainds false*/
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
        return contactDeleted;
    }

    public ArrayList<Supplier> getSupplierList() {
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        ArrayList<String> supplierRowList = new ArrayList<String>();
        ArrayList<String> supplierColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "getSupplierList^";
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String supplierRow = null;
            while (true) {
                supplierRow = in.readLine();
                if (supplierRow == null) {
                    break;
                } else {
                    supplierRowList.add(supplierRow);
                }

            }
            if (supplierRowList != null) {
                for (int i = 0; i < supplierRowList.size(); i++) {
                    supplierColumnList = tokens(supplierRowList.get(i));
                    if (supplierColumnList != null) {
                        String id = supplierColumnList.get(0);
                        String name = supplierColumnList.get(1);
                        String date = supplierColumnList.get(2);
                        supplierList.add(new Supplier(id, name, date));

                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return supplierList;
    }

    public TableModel supplierContactTableModel() {
        DefaultTableModel supplierContact = null;
        ArrayList<SupplierContact> contactList = new ArrayList<SupplierContact>();
        ArrayList<String> contactRowList = new ArrayList<String>();
        ArrayList<String> contactColumnList = new ArrayList<String>();
        String[] columnNames = {"No_", "CONTACT ID", "SUPPLIER", "CONTACT", "CREATION DATE"};
        String[][] contactRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "supplierContactTableModel^";
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String contactRow = null;
                while (true) {
                    contactRow = in.readLine();
                    if (contactRow == null) {
                        break;
                    } else {
                        contactRowList.add(contactRow);
                    }
                }
                if (contactRowList != null) {
                    for (int i = 0; i < contactRowList.size(); i++) {
                        contactColumnList = tokens(contactRowList.get(i));
                        if (contactColumnList != null) {
                            String id = contactColumnList.get(0);
                            String supplierName = contactColumnList.get(1);
                            String contact = contactColumnList.get(2);
                            String date = contactColumnList.get(3);
                            contactList.add(new SupplierContact(id, supplierName, contact, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (contactList == null) {
                rowNumber = 12;
                contactRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = contactList.size() + 10;
                contactRecord = new String[rowNumber][columnNames.length];
                Collections.sort(contactList, new Comparator<SupplierContact>() {

                    public int compare(SupplierContact s1, SupplierContact s2) {
                        return s1.getSupplier().compareToIgnoreCase(s2.getSupplier());
                    }
                });
                for (int i = 0; i < contactList.size(); i++) {
                    contactRecord[i][0] = Integer.toString((i + 1));
                    contactRecord[i][1] = contactList.get(i).getId();
                    contactRecord[i][2] = contactList.get(i).getSupplier();
                    contactRecord[i][3] = contactList.get(i).getContact();
                    contactRecord[i][4] = contactList.get(i).getDate();


                }
            }
            supplierContact = new DefaultTableModel(contactRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return supplierContact;
    }

    public TableModel supplierProductTableModel() {
        DefaultTableModel supplierProduct = null;
        ArrayList<SupplierProduct> productList = new ArrayList<SupplierProduct>();
        ArrayList<String> productRowList = new ArrayList<String>();
        ArrayList<String> productColumnList = new ArrayList<String>();
        String[] columnNames = {"#", "CONTACT ID", "SUPPLIER", "PRODUCT", "PRICE", "UOM", "PURCHASE DATE"};
        String[][] productRecord = null;
        int rowNumber = 0;
        try {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "supplierProductTableModel^";
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String productRow = null;
                while (true) {
                    productRow = in.readLine();
                    if (productRow == null) {
                        break;
                    } else {
                        productRowList.add(productRow);
                    }
                }
                if (productRowList != null) {
                    for (int i = 0; i < productRowList.size(); i++) {
                        productColumnList = tokens(productRowList.get(i));
                        if (productColumnList != null) {
                            String id = productColumnList.get(0);
                            String supplierName = productColumnList.get(1);
                            String product = productColumnList.get(2);
                            String price = productColumnList.get(3);
                            String uom = productColumnList.get(4);
                            String date = productColumnList.get(5);
                            productList.add(new SupplierProduct(id, supplierName, product, price, uom, date));

                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
            }
            if (productList == null) {
                rowNumber = 12;
                productRecord = new String[rowNumber][columnNames.length];

            } else {
                rowNumber = productList.size() + 10;
                productRecord = new String[rowNumber][columnNames.length];
                Collections.sort(productList, new Comparator<SupplierProduct>() {

                    public int compare(SupplierProduct s1, SupplierProduct s2) {
                        return s1.getSupplier().compareToIgnoreCase(s2.getSupplier());
                    }
                });
                for (int i = 0; i < productList.size(); i++) {
                    productRecord[i][0] = Integer.toString((i + 1));
                    productRecord[i][1] = productList.get(i).getId();
                    productRecord[i][2] = productList.get(i).getSupplier();
                    productRecord[i][3] = productList.get(i).getProduct();
                    productRecord[i][4] = productList.get(i).getPrice();
                    productRecord[i][5] = productList.get(i).getUOM();
                    productRecord[i][6] = productList.get(i).getDate();


                }
            }
            supplierProduct = new DefaultTableModel(productRecord, columnNames) {

                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "Error");

        }


        return supplierProduct;
    }

    public boolean addSupplierProduct(SupplierProduct product) {
        boolean productAdded = false;
        String id = product.getId();
        String supplier = product.getSupplier();
        String supplierProduct = product.getProduct();
        String price = product.getPrice();
        String uom = product.getUOM();
        String date = product.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "addSupplierProduct^" + id + "^" + supplier + "^" + supplierProduct + "^" + price + "^" + uom + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String added = in.readLine();
            if (added.equalsIgnoreCase("true")) {
                productAdded = true;
            } else {
                /*contactAdded remains false*/
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return productAdded;
    }

    public boolean updateSupplierProduct(SupplierProduct product) {
        boolean productUpdated = false;
        String id = product.getId();
        String supplier = product.getSupplier();
        String supplierProduct = product.getProduct();
        String price = product.getPrice();
        String uom = product.getUOM();
        String date = product.getDate();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "updateSupplierProduct^" + id + "^" + supplier + "^" + supplierProduct + "^" + price + "^" + uom + "^" + date;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String updated = in.readLine();
            if (updated.equalsIgnoreCase("true")) {
                productUpdated = true;
            } else {
                /*supplierUpdated remains false*/
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return productUpdated;

    }

    public boolean deleteSupplierProduct(String id) {
        boolean productDeleted = false;
        int deleteComformation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete  Product");
        if (deleteComformation == 0) {
            try {
                Socket conn = new Socket(host, port);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                String message = "deleteSupplierProduct^" + id;
                out.println(message);
                out.flush();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String deleted = in.readLine();
                if (deleted.equalsIgnoreCase("true")) {
                    productDeleted = true;
                } else {
                    /*SupplierDeleted remainds false*/
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        } else if (deleteComformation == 1) {
        } else if (deleteComformation == 2) {
        }
        return productDeleted;
    }

    public boolean findIfExists(String itemId) {
        boolean exists = false;
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "findIfExists^" + itemId;
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String itemExists = in.readLine();
            if (itemExists.equalsIgnoreCase("true")) {
                exists = true;
            } else {
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return exists;
    }

    public ArrayList<SupplierProduct> getSupplierInfo() {
        ArrayList<SupplierProduct> productList = new ArrayList<SupplierProduct>();
        ArrayList<String> productRowList = new ArrayList<String>();
        ArrayList<String> productColumnList = new ArrayList<String>();
        try {
            Socket conn = new Socket(host, port);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            String message = "supplierProductTableModel^";
            out.println(message);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String productRow = null;
            while (true) {
                productRow = in.readLine();
                if (productRow == null) {
                    break;
                } else {
                    productRowList.add(productRow);
                }
            }
            if (productRowList != null) {
                for (int i = 0; i < productRowList.size(); i++) {
                    productColumnList = tokens(productRowList.get(i));
                    if (productColumnList != null) {
                        String id = productColumnList.get(0);
                        String supplierName = productColumnList.get(1);
                        String product = productColumnList.get(2);
                        String price = productColumnList.get(3);
                        String uom = productColumnList.get(4);
                        String date = productColumnList.get(5);
                        productList.add(new SupplierProduct(id, supplierName, product, price, uom, date));

                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " Error");
        }
        Collections.sort(productList, new Comparator<SupplierProduct>() {

            public int compare(SupplierProduct s1, SupplierProduct s2) {
                return s1.getSupplier().compareToIgnoreCase(s2.getSupplier());
            }
        });
        return productList;
    }

    public void getSupplierInfoReport() {

        String outFileName = "getSupplierInfo.pdf";
        SupplierInfoFactory.productList = new ArrayList<SupplierProduct>();
        ArrayList<SupplierProduct> supplierList = new Controller().getSupplierInfo();
        HashMap hm = new HashMap();
        try {
            for (int i = 0; i < supplierList.size(); i++) {
                String id = supplierList.get(i).getId();
                String supplierName = supplierList.get(i).getSupplier();
                String product = supplierList.get(i).getProduct();
                String price = supplierList.get(i).getPrice();
                String uom = supplierList.get(i).getUOM();
                String date = supplierList.get(i).getDate();
                SupplierInfoFactory.productList.add(new SupplierProduct(id, supplierName, product, price, uom, date));
            }
            InputStream input = getClass().getResourceAsStream("jrxml/SupplierInfo.jrxml");
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, hm, new JRBeanCollectionDataSource(SupplierInfoFactory.generateCollection()));
            //JasperViewer jv = new JasperViewer(print);
            //jv.setVisible(true);
            JasperViewer.viewReport(print, false);
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();

            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            // Export the PDF file
            exporter.exportReport();


            input.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage() + "kaso");
        }


    }
}
