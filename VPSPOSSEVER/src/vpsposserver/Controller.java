/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * among the people who will go to hell, cowords are there.
 */
package vpsposserver;
/*716:51:00 
 * Union jack
 * 
 */

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author royal
 */
public class Controller {

    public Controller() {
        //new DerbyDataBase().createTableSummary();
       /* boolean initialise = initialise();
        if(initialise==true){
        System.out.println("All table Created");
        }else{
        System.out.println("NOT All table Created");
        }*/
    }

    public boolean adminLogin(String userName, String id) {
        System.out.println(userName + id);
        Connection connect = null;
        boolean login = false;
        try {
            connect = connectToDB();
            PreparedStatement validateAdmin = connect.prepareStatement("select * from Admin where USERNAME=? and PASSWORD=?");
            validateAdmin.setString(1, userName.toLowerCase());
            validateAdmin.setString(2, id);
            ResultSet returnedUser = validateAdmin.executeQuery();
            if (returnedUser.next()) {
                if (OpeningStockExists(getCurrentDate()) == false) {
                    recordOpeningStock(getStock(getCurrentDate()));
                } else {
                    //System.out.println("Opening Stock Already Recorded");
                }
                login = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            login = false;
        }
        return login;
    }

    public boolean passWordMatch(String oldPassWord) {
        boolean match = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement matchPassword = connect.prepareStatement("Select PASSWORD from Admin where PASSWORD=?");
            matchPassword.setString(1, oldPassWord);
            ResultSet matchPasswordResult = matchPassword.executeQuery();
            while (matchPasswordResult.next()) {
                match = true;
            }
        } catch (Exception ex) {
            System.out.println();
        }
        return match;
    }

    public boolean adminChangePassword(String oldPassWord, String newPassWord) {
        boolean changed = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            if ((passWordMatch(oldPassWord)) == true) {
                PreparedStatement changePassWord = connect.prepareStatement("update Admin set PASSWORD=?");
                changePassWord.setString(1, newPassWord);
                int changePassWordResult = changePassWord.executeUpdate();
                if (changePassWordResult > 0) {
                    changed = true;
                }
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return changed;
    }

    public String cashierLogin(String id) {
        Connection connect = null;
        String cashier = null;
        try {
            connect = connectToDB();
            PreparedStatement validateUser = connect.prepareStatement("select * from Cashier where ID=?");
            validateUser.setString(1, id);
            ResultSet returnedUser = validateUser.executeQuery();
            if (returnedUser.next()) {
                cashier = returnedUser.getString("FIRSTNAME");
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cashier;
    }

    public String deductStock(Sale sale) {
        Connection connect = null;
        int quantity;
        int newQuantity = 0;
        String update = null;
        try {
            connect = connectToDB();
            PreparedStatement itemQuantity = connect.prepareStatement("select QUANTITY from Stock where ITEMID=?");
            itemQuantity.setString(1, sale.getItem());
            ResultSet quantityReturn = itemQuantity.executeQuery();
            if (quantityReturn.next()) {
                quantity = quantityReturn.getInt("QUANTITY");
                /* if (quantity >= sale.getQuantity()) {*/
                newQuantity = (quantity - sale.getQuantity());
                PreparedStatement deductStockItem = connect.prepareStatement("update Stock set QUANTITY=? where ITEMID=?");
                deductStockItem.setInt(1, newQuantity);
                deductStockItem.setString(2, sale.getItem());
                int updateResult = deductStockItem.executeUpdate();
                if (updateResult > 0) {
                    update = "true";
                    //saleItem(sale);
                } else {
                    update = "false";

                }
                /*} else {
                System.out.println("Available Stock can't accomodate this sale");
                update = "unavailable";
                
                }*/
            } else {
                update = "unknownItem";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return update;
    }

    public boolean reAddStock(String itemId, int quantity) {
        Connection connect = null;
        int currentQuantity = 0;
        int newQuantity = 0;
        boolean update = false;
        try {
            connect = connectToDB();
            PreparedStatement itemQuantity = connect.prepareStatement("select QUANTITY from Stock where ITEMID=?");
            itemQuantity.setString(1, itemId);
            ResultSet quantityReturn = itemQuantity.executeQuery();
            if (quantityReturn.next()) {
                currentQuantity = quantityReturn.getInt("QUANTITY");
                newQuantity = (currentQuantity + quantity);
                PreparedStatement addStockItem = connect.prepareStatement("update Stock set QUANTITY=? where ITEMID=?");
                addStockItem.setInt(1, newQuantity);
                addStockItem.setString(2, itemId);
                int updateResult = addStockItem.executeUpdate();
                if (updateResult > 0) {
                    update = true;
                } else {
                    update = false;

                }
            } else {
                update = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return update;
    }

    public boolean removeItemFromSales(String itemId, String receiptId) {
        boolean removed = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement removeItem = connect.prepareStatement("delete from Sale where ITEMID=? and RECEIPTID=?");
            removeItem.setString(1, itemId);
            removeItem.setString(2, receiptId);
            int removeItemResult = removeItem.executeUpdate();
            if (removeItemResult > 0) {
                removed = true;

            } else {
                removed = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return removed;

    }

    public boolean saleItem(Sale sale) {
        Connection connect = null;
        boolean itemSold = false;
        try {
            connect = connectToDB();
            PreparedStatement checkItem = connect.prepareStatement("select PRICE from Item where ITEMID=?");
            checkItem.setString(1, sale.getItem());
            ResultSet checkResult = checkItem.executeQuery();
            if (checkResult.next()) {
                int price = checkResult.getInt("PRICE") * sale.getQuantity();
                PreparedStatement saveSale = connect.prepareStatement("insert into Sale (ITEMID,RECEIPTID,QUANTITY,PRICE,USAR,DATE) values (?,?,?,?,?,?)");
                saveSale.setString(1, sale.getItem());
                saveSale.setString(2, sale.getReceiptId());
                saveSale.setInt(3, sale.getQuantity());
                saveSale.setInt(4, price);
                saveSale.setString(5, sale.getCashier());
                saveSale.setString(6, sale.getDate());
                int saveResult = saveSale.executeUpdate();
                if (saveResult > 0) {
                    itemSold = true;
                } else {
                    itemSold = false;

                }
            } else {
                itemSold = false;
            }



        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return itemSold;
    }

    public boolean updatesoldItem(String item, String receiptId, int quantity) {
        Connection connect = null;
        boolean saleUpdated = false;
        try {
            connect = connectToDB();
            PreparedStatement checkItem = connect.prepareStatement("select PRICE from Item where ITEMID=?");
            checkItem.setString(1, item);
            ResultSet checkResult = checkItem.executeQuery();
            if (checkResult.next()) {
                int price = checkResult.getInt("PRICE") * quantity;
                PreparedStatement updateSale = connect.prepareStatement("update Sale set QUANTITY=?,PRICE=? where ITEMID=? and RECEIPTID=?");
                updateSale.setInt(1, quantity);
                updateSale.setInt(2, price);
                updateSale.setString(3, item);
                updateSale.setString(4, receiptId);
                int updateResult = updateSale.executeUpdate();
                if (updateResult > 0) {
                    saleUpdated = true;
                } else {
                    saleUpdated = false;

                }
            } else {
                saleUpdated = false;
            }



        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return saleUpdated;
    }

    public boolean deleteItemFromList(String item, String receiptId, int quantity) {
        Connection connect = null;
        boolean saleUpdated = false;
        try {
            connect = connectToDB();
            PreparedStatement checkItem = connect.prepareStatement("select PRICE from Item where ITEMID=?");
            checkItem.setString(1, item);
            ResultSet checkResult = checkItem.executeQuery();
            if (checkResult.next()) {
                int price = checkResult.getInt("PRICE") * quantity;
                PreparedStatement updateSale = connect.prepareStatement("update Sale set QUANTITY=?,PRICE=? where ITEMID=? and RECEIPTID=?");
                updateSale.setInt(1, quantity);
                updateSale.setInt(2, price);
                updateSale.setString(3, item);
                updateSale.setString(4, receiptId);
                int updateResult = updateSale.executeUpdate();
                if (updateResult > 0) {
                    saleUpdated = true;
                } else {
                    saleUpdated = false;

                }
            } else {
                saleUpdated = false;
            }



        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return saleUpdated;
    }

    public ArrayList<UOM> getUOMList() {
        ArrayList<UOM> uOMList = new ArrayList<UOM>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getUOMList = connect.createStatement();
            ResultSet returnUOMList = getUOMList.executeQuery("select * from UOM");
            while (returnUOMList.next()) {
                String id = returnUOMList.getString("ID");
                String name = returnUOMList.getString("NAME");
                String status = returnUOMList.getString("STATUS");
                String date = returnUOMList.getString("DATE");
                uOMList.add(new UOM(id, name, status, date));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return uOMList;
    }

    /*Returns a list of all items to be displayed in a Combobox*/
    public ArrayList<Item> getItemList(String searchText) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getItemList = connect.createStatement();
            ResultSet returnItemList = getItemList.executeQuery("select * from Item");
            while (returnItemList.next()) {
                String id = returnItemList.getString("ITEMID");
                String subCatId = returnItemList.getString("SUBID");
                String name = returnItemList.getString("NAME");
                int price = returnItemList.getInt("PRICE");
                String status = returnItemList.getString("STATUS");
                String date = returnItemList.getString("DATE");

                if (searchText.equalsIgnoreCase("all")) {
                    itemList.add(new Item(id, subCatId, name, price, status, date));
                } else {
                    if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                        itemList.add(new Item(id, subCatId, name, price, status, date));
                    } else {
                        /*dont add to the List*/
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return itemList;
    }

    public ArrayList<Supplier> getSupplierList() {
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getSupplierList = connect.createStatement();
            ResultSet returnSupplierList = getSupplierList.executeQuery("select * from Supplier");
            while (returnSupplierList.next()) {
                String id = returnSupplierList.getString("Id");
                String supplier = returnSupplierList.getString("Name");
                String date = returnSupplierList.getString("Date");
                supplierList.add(new Supplier(id, supplier, date));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return supplierList;
    }

    public ArrayList<Item> search(String category, String firstItemCharacters) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getItemList = connect.createStatement();
            ResultSet returnItemList = getItemList.executeQuery("select ITEMID,NAME from Item order by NAME");
            while (returnItemList.next()) {
                String id = returnItemList.getString("ITEMID");
                String name = returnItemList.getString("NAME").toLowerCase();
                if ((id.startsWith(firstItemCharacters)) || name.startsWith(firstItemCharacters.toLowerCase())) {
                    itemList.add(new Item(id, name));
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return itemList;



    }

    public ArrayList<Item> searchAll() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getItemList = connect.createStatement();
            ResultSet returnItemList = getItemList.executeQuery("select ITEMID,NAME from Item order by NAME");
            while (returnItemList.next()) {
                String id = returnItemList.getString("ITEMID");
                String name = returnItemList.getString("NAME").toLowerCase();
                itemList.add(new Item(id, name));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return itemList;



    }

    public ArrayList<SubCategory> getSubCategoryList(String searchText) {
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getSubCategoryList = connect.createStatement();
            ResultSet returnSubCategoryList = getSubCategoryList.executeQuery("select * from SubCategory");
            while (returnSubCategoryList.next()) {
                String id = returnSubCategoryList.getString("SUBID");
                String mainCatId = returnSubCategoryList.getString("MAINID");
                String name = returnSubCategoryList.getString("NAME");
                String status = returnSubCategoryList.getString("STATUS");
                String date = returnSubCategoryList.getString("DATE");

                if (searchText.equalsIgnoreCase("all")) {
                    subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));
                } else {
                    if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                        subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));
                    } else {
                        /*dont add to the List*/
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return subCategoryList;
    }

    public ArrayList<MainCategory> getMainCategoryList(String searchText) {
        ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getMainCategoryList = connect.createStatement();
            ResultSet returnMainCategoryList = getMainCategoryList.executeQuery("select * from MainCategory order by NAME DESC");
            while (returnMainCategoryList.next()) {
                String id = returnMainCategoryList.getString("MAINID");
                String name = returnMainCategoryList.getString("NAME");
                String status = returnMainCategoryList.getString("STATUS");
                String date = returnMainCategoryList.getString("DATE");

                if (searchText.equalsIgnoreCase("all")) {
                    mainCategoryList.add(new MainCategory(id, name, status, date));
                } else {
                    if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                        mainCategoryList.add(new MainCategory(id, name, status, date));
                    } else {
                        /*dont add to the List*/
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return mainCategoryList;
    }

    public boolean addStockToDB(Stock stock) {
        Connection connect = null;
        boolean added = false;
        try {
            connect = connectToDB();
            PreparedStatement checkItem = connect.prepareStatement("select * from Stock where ITEMID=?");
            checkItem.setString(1, stock.getItem());
            ResultSet checkItemResult = checkItem.executeQuery();
            if (checkItemResult.next()) {
                int quantity = checkItemResult.getInt("QUANTITY") + stock.getQuantity();
                PreparedStatement updateStock = connect.prepareStatement("update Stock set QUANTITY=? where ITEMID=?");
                updateStock.setInt(1, quantity);
                updateStock.setString(2, stock.getItem());
                int updateResult = updateStock.executeUpdate();
                if (updateResult > 0) {
                    added = true;
                } else {
                    added = false;

                }
            } else {
                PreparedStatement addStock = connect.prepareStatement("insert into Stock (ITEMID,QUANTITY, UOMID, COSTPRICE,DATE) values(?,?,?,?,?)");
                addStock.setString(1, stock.getItem());
                addStock.setInt(2, stock.getQuantity());
                addStock.setString(3, stock.getUOM());
                addStock.setInt(4, stock.getUnitCostPrice());
                addStock.setString(5, stock.getDate());
                int insertResult = addStock.executeUpdate();
                if (insertResult > 0) {
                    added = true;
                } else {
                    added = false;

                }
            }
            /**/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            added = false;

        }
        return added;

    }

    public boolean addItemToDB(Item item) {
        Connection connect = null;
        boolean added = false;
        try {
            connect = connectToDB();
            PreparedStatement addItem = connect.prepareStatement("insert into Item (ITEMID,SUBID, NAME,PRICE, STATUS,DATE) values(?,?,?,?,?,?)");
            addItem.setString(1, item.getId());
            addItem.setString(2, item.getSubCategoryId());
            addItem.setString(3, item.getName());
            addItem.setInt(4, item.getPrice());
            addItem.setString(5, item.getStatus());
            addItem.setString(6, item.getDate());
            int insertResult = addItem.executeUpdate();
            if (insertResult > 0) {
                added = true;

            } else {
                added = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            added = false;

        }
        return added;

    }

    public boolean addUOMToDB(UOM uOM) {
        Connection connect = null;
        boolean added = false;
        try {
            connect = connectToDB();
            PreparedStatement addUOM = connect.prepareStatement("insert into UOM (ID, NAME, STATUS,DATE) values(?,?,?,?)");
            addUOM.setString(1, uOM.getId());
            addUOM.setString(2, uOM.getName());
            addUOM.setString(3, uOM.getStatus());
            addUOM.setString(4, uOM.getDate());
            int insertResult = addUOM.executeUpdate();
            if (insertResult > 0) {
                added = true;
            } else {
                added = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            added = false;
        }
        return added;
    }

    public boolean addSubCategoryToDB(SubCategory subCategory) {
        Connection connect = null;
        boolean saved = false;
        try {
            connect = connectToDB();
            PreparedStatement addSubCategory = connect.prepareStatement("insert into SubCategory (SUBID,MAINID, NAME, STATUS,DATE) values(?,?,?,?,?)");
            addSubCategory.setString(1, subCategory.getId());
            addSubCategory.setString(2, subCategory.getMainCatId());
            addSubCategory.setString(3, subCategory.getName());
            addSubCategory.setString(4, subCategory.getStatus());
            addSubCategory.setString(5, subCategory.getDate());
            int insertResult = addSubCategory.executeUpdate();
            if (insertResult > 0) {
                saved = true;
            } else {
                saved = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            saved = false;

        }
        return saved;

    }

    public boolean addCashierToDB(String id, String firstName, String lastName, String date) {
        Connection connect = null;
        boolean saved = false;
        try {
            connect = connectToDB();
            PreparedStatement addCashier = connect.prepareStatement("insert into Cashier (ID, FIRSTNAME, LASTNAME,DATE) values(?,?,?,?)");
            addCashier.setString(1, id);
            addCashier.setString(2, firstName);
            addCashier.setString(3, lastName);
            addCashier.setString(4, date);
            int insertResult = addCashier.executeUpdate();
            if (insertResult > 0) {
                saved = true;
            } else {
                saved = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            saved = false;

        }
        return saved;

    }

    public boolean addCashierExpenditureToDB(CashierExpendure cashierExpendure) {
        Connection connect = null;
        boolean saved = false;
        String expenditureId = cashierExpendure.getExpenditureId();
        String cashierId = cashierExpendure.getCashierId();
        int amount = cashierExpendure.getAmount();
        String reason = cashierExpendure.getReason();
        String date = cashierExpendure.getDate();
        try {
            connect = connectToDB();
            PreparedStatement addCashierExpenditure = connect.prepareStatement("insert into CashierExpenditure(ID,CASHIER,AMOUNT,REASON,DATE ) values(?,?,?,?,?)");
            addCashierExpenditure.setString(1, expenditureId);
            addCashierExpenditure.setString(2, cashierId);
            addCashierExpenditure.setInt(3, amount);
            addCashierExpenditure.setString(4, reason);
            addCashierExpenditure.setString(5, date);
            int insertResult = addCashierExpenditure.executeUpdate();
            if (insertResult > 0) {
                saved = true;
            } else {
                //saved = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());


        }
        return saved;

    }

    public boolean cashierSignOff(Summary summary) {
        Connection connect = null;
        boolean signedOff = false;
        String cashierId = summary.getCashierId();
        String signIn = summary.getSignIn();
        String signOut = summary.getSignOut();
        String date = summary.getDate();
        int totalCollection = summary.getTotalCollection();
        int totalExpenditure = summary.getTotalExpenditure();
        int cashAtHand = summary.getCashAtHand();
        try {
            connect = connectToDB();
            PreparedStatement addSummary = connect.prepareStatement("insert into Summary(CASHIER,SIGNIN,SIGNOUT,DATE,TOTALCOLLECTION,TOTALEXPENDITURE,CASHATHAND ) values(?,?,?,?,?,?,?)");
            addSummary.setString(1, cashierId);
            addSummary.setString(2, signIn);
            addSummary.setString(3, signOut);
            addSummary.setString(4, date);
            addSummary.setInt(5, totalCollection);
            addSummary.setInt(6, totalExpenditure);
            addSummary.setInt(7, cashAtHand);
            int addSummaryResult = addSummary.executeUpdate();
            if (addSummaryResult > 0) {
                signedOff = true;
            } else {
                //saved = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());


        }
        return signedOff;

    }

    public boolean addMainCategoryToDB(String id, String name, String status, String date) {
        Connection connect = null;
        boolean saved = false;
        try {
            connect = connectToDB();
            PreparedStatement addMainCategory = connect.prepareStatement("insert into MainCategory (MAINID, NAME, STATUS,DATE) values(?,?,?,?)");
            addMainCategory.setString(1, id);
            addMainCategory.setString(2, name);
            addMainCategory.setString(3, status);
            addMainCategory.setString(4, date);
            int insertResult = addMainCategory.executeUpdate();
            if (insertResult > 0) {
                saved = true;
            } else {
                saved = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            saved = false;

        }
        return saved;

    }

    public ArrayList<Cashier> forCashierTableModel() {
        ArrayList<Cashier> cashierList = new ArrayList<Cashier>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getCashierList = connect.createStatement();
            ResultSet returnCashierList = getCashierList.executeQuery("select * from Cashier");
            while (returnCashierList.next()) {
                String id = returnCashierList.getString("ID");
                String firstName = returnCashierList.getString("FIRSTNAME");
                String lastName = returnCashierList.getString("LASTNAME");
                String date = returnCashierList.getString("DATE");
                cashierList.add(new Cashier(id, firstName, lastName, date));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return cashierList;
    }

    public ArrayList<Supplier> forSupplierTableModel() {
        ArrayList<Supplier> List = new ArrayList<Supplier>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getList = connect.createStatement();
            ResultSet returnList = getList.executeQuery("select * from Supplier");
            while (returnList.next()) {
                String id = returnList.getString("Id");
                String name = returnList.getString("Name");
                String date = returnList.getString("Date");
                List.add(new Supplier(id, name, date));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return List;
    }

    public ArrayList<CashierExpendure> forCashierExpenditureTableModel(String expenditureDate) {
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getExpenditureList = connect.prepareStatement("select * from CashierExpenditure where DATE=?");
            getExpenditureList.setString(1, expenditureDate);
            //PreparedStatement getExpenditureList = connect.prepareStatement("select * from CashierExpenditure");

            ResultSet returnExpenditreList = getExpenditureList.executeQuery();
            while (returnExpenditreList.next()) {
                String id = returnExpenditreList.getString("ID");
                int amount = returnExpenditreList.getInt("AMOUNT");
                String reason = returnExpenditreList.getString("REASON");
                String date = returnExpenditreList.getString("DATE");
                //System.out.println(id);
                expenditureList.add(new CashierExpendure(id, amount, reason, date));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return expenditureList;
    }

    public ArrayList<CashierExpendure> getTotalExpenditure(String fromDate, String toDate) {
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getExpenditureList = connect.prepareStatement("select * from CashierExpenditure");
            ResultSet returnExpenditreList = getExpenditureList.executeQuery();
            while (returnExpenditreList.next()) {
                String id = returnExpenditreList.getString("ID");
                int amount = returnExpenditreList.getInt("AMOUNT");
                String reason = returnExpenditreList.getString("REASON");
                String date = returnExpenditreList.getString("DATE");
                if ((compareDate(fromDate, date, toDate)) == true) {
                    expenditureList.add(new CashierExpendure(id, amount, reason, date));
                } else {
                    /*Do not add that expenditure to the list*/
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return expenditureList;
    }

    public String getCashier(String id) {
        String cashier = null;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getCashier = connect.prepareStatement("select * from Cashier where ID=?");
            getCashier.setString(1, id);
            ResultSet getCashierResult = getCashier.executeQuery();
            if (getCashierResult.next()) {
                cashier = getCashierResult.getString("FIRSTNAME") + "  " + getCashierResult.getString("LASTNAME");
            } else {
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cashier;
    }

    public ArrayList<CashierExpendure> getCustomCashierTotalExpenditure(String cashierId, String fromDate, String toDate) {
        ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
        Connection connect = null;
        try {
            connect = connectToDB();
            String id = getCashier(cashierId);
            PreparedStatement getExpenditureList = connect.prepareStatement("select * from CashierExpenditure where CASHIER=?");
            getExpenditureList.setString(1, cashierId);
            ResultSet returnExpenditreList = getExpenditureList.executeQuery();
            while (returnExpenditreList.next()) {

                int amount = returnExpenditreList.getInt("AMOUNT");
                String reason = returnExpenditreList.getString("REASON");
                String date = returnExpenditreList.getString("DATE");


                if ((compareDate(fromDate, date, toDate)) == true) {
                    expenditureList.add(new CashierExpendure(id, amount, reason, date));
                } else {
                    /*Do not add that expenditure to the list*/
                }


            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return expenditureList;
    }

    /*list of all main category to be sent to  the client */
    public ArrayList<MainCategory> forMainCategoryTableModel(String searchText) {
        ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
        Connection connect = null;
        try {
            connect = connectToDB();
            Statement getMainCategoryList = connect.createStatement();
            ResultSet returnMainCategoryList = getMainCategoryList.executeQuery("select * from MainCategory");
            while (returnMainCategoryList.next()) {
                String id = returnMainCategoryList.getString("MAINID");
                String name = returnMainCategoryList.getString("NAME");
                String status = returnMainCategoryList.getString("STATUS");
                String date = returnMainCategoryList.getString("DATE");
                if (searchText.equalsIgnoreCase("all")) {
                    mainCategoryList.add(new MainCategory(id, name, status, date));
                } else {
                    if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                        mainCategoryList.add(new MainCategory(id, name, status, date));
                    } else {
                        /*dont add to the List*/
                    }

                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return mainCategoryList;
    }

    /*list of all Sub category to be sent to  the client */
    public ArrayList<SubCategory> forSubCategoryTableModel(String searchText) {
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        Connection connect = null;
        try {
            try {
                connect = connectToDB();
                Statement getSubCategoryList = connect.createStatement();
                ResultSet returnSubCategoryList = getSubCategoryList.executeQuery("select * from SubCategory");
                while (returnSubCategoryList.next()) {
                    String id = returnSubCategoryList.getString("SUBID");
                    String mainCatId = returnSubCategoryList.getString("MAINID");
                    String name = returnSubCategoryList.getString("NAME");
                    String status = returnSubCategoryList.getString("STATUS");
                    String date = returnSubCategoryList.getString("DATE");
                    PreparedStatement getMainCatName = connect.prepareStatement("select NAME from MainCategory where MAINID=?");
                    getMainCatName.setString(1, mainCatId);
                    ResultSet mainCatName = getMainCatName.executeQuery();
                    if (mainCatName.next()) {
                        mainCatId = mainCatName.getString("NAME");
                    }
                    if (searchText.equalsIgnoreCase("all")) {
                        subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));
                    } else {
                        if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                            subCategoryList.add(new SubCategory(id, mainCatId, name, status, date));
                        } else {
                            /*dont add to the List*/
                        }

                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }
        return subCategoryList;
    }

    public ArrayList<SupplierContact> forSupplierContactTableModel() {
        ArrayList<SupplierContact> contactList = new ArrayList<SupplierContact>();
        Connection connect = null;
        try {
            try {
                connect = connectToDB();
                Statement getList = connect.createStatement();
                ResultSet returnList = getList.executeQuery("select * from SupplierContact");
                while (returnList.next()) {
                    String id = returnList.getString("Id");
                    String supplier = returnList.getString("Supplier");
                    String contact = returnList.getString("Contact");
                    String date = returnList.getString("Date");
                    PreparedStatement getName = connect.prepareStatement("select Name from Supplier where Id=?");
                    getName.setString(1, supplier);
                    ResultSet supplierName = getName.executeQuery();
                    if (supplierName.next()) {
                        supplier = supplierName.getString("Name");
                    }
                    contactList.add(new SupplierContact(id, supplier, contact, date));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }
        return contactList;
    }

    public ArrayList<SupplierProduct> forSupplierProductTableModel() {
        ArrayList<SupplierProduct> productList = new ArrayList<SupplierProduct>();
        Connection connect = null;
        try {
            try {
                connect = connectToDB();
                Statement getList = connect.createStatement();
                ResultSet returnList = getList.executeQuery("select * from SupplierProduct");
                while (returnList.next()) {
                    String id = returnList.getString("Id");
                    String supplier = returnList.getString("Supplier");
                    String product = returnList.getString("Product");
                    String price = returnList.getString("Price");
                    String UOM = returnList.getString("UOM");
                    String date = returnList.getString("Date");
                    if (!getUOM(UOM).trim().isEmpty()) {
                        UOM = getUOM(UOM);
                    }
                    PreparedStatement getName = connect.prepareStatement("select Name from Supplier where Id=?");
                    getName.setString(1, supplier);
                    ResultSet supplierName = getName.executeQuery();
                    if (supplierName.next()) {
                        supplier = supplierName.getString("Name");
                    }
                    productList.add(new SupplierProduct(id, supplier, product, price, UOM, date));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error kasoma");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }
        return productList;
    }
    /*list of all items to be sent to  the client */

    public ArrayList<Item> forItemTableModel(String searchText) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<SubCategory> categoryList = getSubCategoryList("all");
        Connection connect = null;
        try {
            try {
                connect = connectToDB();
                Statement getItemList = connect.createStatement();
                ResultSet returnitemList = getItemList.executeQuery("select * from Item");
                while (returnitemList.next()) {
                    String id = returnitemList.getString("ITEMID");
                    String subCatId = returnitemList.getString("SUBID");
                    String name = returnitemList.getString("NAME");
                    int price = returnitemList.getInt("PRICE");
                    String status = returnitemList.getString("STATUS");
                    String date = returnitemList.getString("DATE");

                    /*PreparedStatement getSubCatName = connect.prepareStatement("select NAME from SubCategory where SUBID=?");
                    getSubCatName.setString(1, subCatId);
                    ResultSet subCatName = getSubCatName.executeQuery();
                    if (subCatName.next()) {
                    subCatId = subCatName.getString("NAME");
                    }*/
                    if (categoryList.isEmpty()) {
                    } else {
                        for (int i = 0; i < categoryList.size(); i++) {
                            if (categoryList.get(i).getId().equalsIgnoreCase(subCatId)) {
                                subCatId = categoryList.get(i).getName();
                            }
                        }
                    }
                    if (searchText.equalsIgnoreCase("all")) {
                        itemList.add(new Item(id, subCatId, name, price, status, date));
                    } else {
                        if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                            itemList.add(new Item(id, subCatId, name, price, status, date));
                        } else {
                            /*dont add to the List*/
                        }

                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }


        return itemList;
    }

    public ArrayList<Item> fortotalCollectionTableModel(String cashierId, String date) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale where USAR=? and DATE=?");
            getItemList.setString(1, cashierId);
            getItemList.setString(2, date);
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String name = id;

                PreparedStatement getItemName = connect.prepareStatement("select NAME from Item where ITEMID=?");
                getItemName.setString(1, id);
                ResultSet itemName = getItemName.executeQuery();
                if (itemName.next()) {
                    name = itemName.getString("NAME");
                }
                /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                if (itemList.isEmpty()) {
                    itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                } else {
                    /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                     * then we do the substitution accordingly.
                     */
                    boolean found = false;
                    for (int i = 0; i < itemList.size(); i++) {
                        /*looping through the ArrayList to chech if the item already exists.
                         * and set found to true.
                         */
                        if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                            int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                            int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                            itemList.get(i).setAmount(Integer.toString(newAmount));
                            itemList.get(i).setQuantity(Integer.toString(newQuantity));
                            found = true;
                        } else {
                            /*We will do nothing since found is already set to false and only changes to true if
                             * a match is found.
                             */
                        }
                    }
                    /* add a new item if there was no match at all.*/
                    if (found == false) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                    } else {
                        /*Do nothing*/
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<Item> forgeneralDayTotalCollection(String date) {

        ArrayList<Item> itemList = new ArrayList<Item>();
        //ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale where DATE=?");
            getItemList.setString(1, date);
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String name = id;
                int costPrice = getCostPrice(connect, id);

                PreparedStatement getItemName = connect.prepareStatement("select NAME from Item where ITEMID=?");
                getItemName.setString(1, id);
                ResultSet itemName = getItemName.executeQuery();
                if (itemName.next()) {
                    name = itemName.getString("NAME");
                }
                /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                if (itemList.isEmpty()) {
                    itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                } else {
                    /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                     * then we do the substitution accordingly.
                     */
                    boolean found = false;
                    for (int i = 0; i < itemList.size(); i++) {
                        /*looping through the ArrayList to check if the item already exists.
                         * and set found to true.
                         */
                        if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                            int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                            int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                            int totalCostPrice = itemList.get(i).getTotalCostPrice() + (costPrice * quantity);
                            itemList.get(i).setAmount(Integer.toString(newAmount));
                            itemList.get(i).setQuantity(Integer.toString(newQuantity));
                            itemList.get(i).setTotalCostPrice(totalCostPrice);
                            found = true;
                        } else {
                            /*We will do nothing since found is already set to false and only changes to true if
                             * a match is found.
                             */
                        }
                    }
                    /* add a new item if there was no match at all.*/
                    if (found == false) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                    } else {
                        /*Do nothing*/
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<String> dateTokens(String message) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(message, "/");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;

    }

    public String convert(String date) {
        String[] months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        int monthNumber = 0;
        ArrayList<String> dateTokens = dateTokens(date);
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(dateTokens.get(1))) {
                monthNumber = i + 1;
            }
        }
        String newDate = dateTokens.get(0) + "/" + monthNumber + "/" + dateTokens.get(2);

        return newDate;
    }

    public boolean compareDate(String before, String now, String after) {
        boolean found = false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf.parse(convert(before));
            Date date2 = sdf.parse(convert(after));
            Date date3 = sdf.parse(convert(now));

            if (date3.equals(date1) || date3.equals(date2) || (date3.after(date1) && date3.before(date2))) {
                found = true;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return found;

    }

    public ArrayList<Item> forgetCustomDateTotalCollection(String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale");
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;
                int costPrice = getCostPrice(connect, id);

                PreparedStatement getItemName = connect.prepareStatement("select NAME from Item where ITEMID=?");
                getItemName.setString(1, id);
                ResultSet itemName = getItemName.executeQuery();
                if (itemName.next()) {
                    name = itemName.getString("NAME");
                }

                /*Check if the date is within the range*/
                if ((new Controller().compareDate(fromDate, date, toDate)) == true) {


                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                int totalCostPrice = itemList.get(i).getTotalCostPrice() + (costPrice * quantity);
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                itemList.get(i).setTotalCostPrice(totalCostPrice);
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                        } else {
                            /*Do nothing*/
                        }
                    }

                } else {
                    /*Date doesn't match*/
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<Item> forgetCustomCashierDateTotalCollection(String cashierId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale where USAR=?");
            getItemList.setString(1, cashierId);
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;
                int costPrice = getCostPrice(connect, id);

                PreparedStatement getItemName = connect.prepareStatement("select NAME from Item where ITEMID=?");
                getItemName.setString(1, id);
                ResultSet itemName = getItemName.executeQuery();
                if (itemName.next()) {
                    name = itemName.getString("NAME");
                }
                if ((new Controller().compareDate(fromDate, date, toDate)) == true) {
                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                int totalCostPrice = itemList.get(i).getTotalCostPrice() + (costPrice * quantity);
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                itemList.get(i).setTotalCostPrice(totalCostPrice);
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount), costPrice));

                        } else {
                            /*Do nothing*/
                        }
                    }
                } else {
                    /*the date is not within the range
                     * therefore that record should not be added to the list.
                     * Just go back and get another record.
                     */
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<String> returnSubCategoty(String mainCatId) {
        ArrayList<String> subCatList = new ArrayList<String>();
        Connection connect = null;

        try {
            connect = connectToDB();
            PreparedStatement getSubCat = connect.prepareStatement("select SUBID from SubCategory where MAINID=?");
            getSubCat.setString(1, mainCatId);
            ResultSet getSubCatReturn = getSubCat.executeQuery();
            while (getSubCatReturn.next()) {
                subCatList.add(getSubCatReturn.getString("SUBID"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subCatList;
    }

    public boolean ItemBelongsTo(ArrayList<String> subCatList, String subCatId) {
        boolean belongs = false;
        try {
            for (int i = 0; i < subCatList.size(); i++) {
                if (subCatList.get(i).equalsIgnoreCase(subCatId)) {
                    belongs = true;
                }
            }

        } catch (Exception e) {
        }
        return belongs;
    }

    public ArrayList<Item> forgetMainCategoryCollection(String mainCatId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> subCatList = returnSubCategoty(mainCatId);
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale");
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;
                String subCatId = null;
                PreparedStatement getItem = connect.prepareStatement("select NAME,SUBID from Item where ITEMID=?");
                getItem.setString(1, id);
                ResultSet getItemResult = getItem.executeQuery();
                if (getItemResult.next()) {
                    name = getItemResult.getString("NAME");
                    subCatId = getItemResult.getString("SUBID");
                }
                if (((new Controller().compareDate(fromDate, date, toDate)) == true) && (ItemBelongsTo(subCatList, subCatId) == true)) {
                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                        } else {
                            /*Do nothing*/
                        }
                    }
                } else {
                    /*the date is not within the range
                     * therefore that record should not be added to the list.
                     * Just go back and get another record.
                     */
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<Item> forgetCashierMainCategoryCollection(String cashierId, String mainCatId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<String> subCatList = returnSubCategoty(mainCatId);
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale where USAR=?");
            getItemList.setString(1, cashierId);
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {

                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;
                String subCatId = null;
                PreparedStatement getItem = connect.prepareStatement("select NAME,SUBID from Item where ITEMID=?");
                getItem.setString(1, id);
                ResultSet getItemResult = getItem.executeQuery();
                if (getItemResult.next()) {
                    name = getItemResult.getString("NAME");
                    subCatId = getItemResult.getString("SUBID");
                }
                if (((new Controller().compareDate(fromDate, date, toDate)) == true) && (ItemBelongsTo(subCatList, subCatId) == true)) {
                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                        } else {
                            /*Do nothing*/
                        }
                    }
                } else {
                    /*the date is not within the range
                     * therefore that record should not be added to the list.
                     * Just go back and get another record.
                     */
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<Item> forgetSubCategoryCollection(String subCatId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //ArrayList<String> subCatList = returnSubCategoty(mainCatId);
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale");
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {
                boolean subCategoryMatch = false;
                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;
                //String subCatId=null;
                PreparedStatement getItem = connect.prepareStatement("select NAME from Item where ITEMID=? and SUBID=?");
                getItem.setString(1, id);
                getItem.setString(2, subCatId);
                ResultSet getItemResult = getItem.executeQuery();
                if (getItemResult.next()) {
                    name = getItemResult.getString("NAME");
                    subCategoryMatch = true;
                }
                if (((new Controller().compareDate(fromDate, date, toDate)) == true) && (subCategoryMatch == true)) {
                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                        } else {
                            /*Do nothing*/
                        }
                    }
                } else {
                    /*the date is not within the range
                     * therefore that record should not be added to the list.
                     * Just go back and get another record.
                     */
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }

    public ArrayList<Item> forgetCashierSubCategoryCollection(String cashierId, String subCatId, String fromDate, String toDate) {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItemList = connect.prepareStatement("select * from Sale where USAR=?");
            getItemList.setString(1, cashierId);
            ResultSet returnitemList = getItemList.executeQuery();
            while (returnitemList.next()) {
                boolean subCategoryMatch = false;
                String id = returnitemList.getString("ITEMID");
                int quantity = returnitemList.getInt("QUANTITY");
                int amount = returnitemList.getInt("PRICE");
                String date = returnitemList.getString("DATE");
                String name = id;

                PreparedStatement getItem = connect.prepareStatement("select NAME from Item where ITEMID=? and SUBID=?");
                getItem.setString(1, id);
                getItem.setString(2, subCatId);
                ResultSet getItemResult = getItem.executeQuery();
                if (getItemResult.next()) {
                    name = getItemResult.getString("NAME");
                    subCategoryMatch = true;
                    System.out.println(subCategoryMatch);
                }

                if (((new Controller().compareDate(fromDate, date, toDate)) == true) && (subCategoryMatch == true)) {
                    /*Checking if the ArrayList is empty, the i dont comapare, i just add a new Item into the List*/
                    if (itemList.isEmpty()) {
                        itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                    } else {
                        /*initialise found to false, and set to true if we compare and find that the same Item is already in the List
                         * then we do the substitution accordingly.
                         */
                        boolean found = false;
                        for (int i = 0; i < itemList.size(); i++) {
                            /*looping through the ArrayList to chech if the item already exists.
                             * and set found to true.
                             */
                            if (id.equalsIgnoreCase(itemList.get(i).getId())) {
                                int newQuantity = Integer.parseInt(itemList.get(i).getQuantity()) + quantity;
                                int newAmount = Integer.parseInt(itemList.get(i).getAmount()) + amount;
                                itemList.get(i).setAmount(Integer.toString(newAmount));
                                itemList.get(i).setQuantity(Integer.toString(newQuantity));
                                found = true;
                            } else {
                                /*We will do nothing since found is already set to false and only changes to true if
                                 * a match is found.
                                 */
                            }
                        }
                        /* add a new item if there was no match at all.*/
                        if (found == false) {
                            itemList.add(new Item(id, name, Integer.toString(quantity), Integer.toString(amount)));

                        } else {
                            /*Do nothing*/
                        }
                    }
                } else {
                    /*the date is not within the range
                     * therefore that record should not be added to the list.
                     * Just go back and get another record.
                     */
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }


        return itemList;
    }
    /*list of all UOM to be sent to  the client */

    public ArrayList<UOM> forUOMTableModel(String searchText) {
        ArrayList<UOM> uOMList = new ArrayList<UOM>();
        Connection connect = null;
        try {
            try {
                connect = connectToDB();
                Statement getUOMList = connect.createStatement();
                ResultSet returnUOMList = getUOMList.executeQuery("select * from UOM");
                while (returnUOMList.next()) {
                    String id = returnUOMList.getString("ID");
                    String name = returnUOMList.getString("NAME");
                    String status = returnUOMList.getString("STATUS");
                    String date = returnUOMList.getString("DATE");
                    if (searchText.equalsIgnoreCase("all")) {
                        uOMList.add(new UOM(id, name, status, date));
                    } else {
                        if ((id.startsWith(searchText)) || name.toLowerCase().startsWith(searchText.toLowerCase())) {
                            uOMList.add(new UOM(id, name, status, date));
                        } else {
                            /*dont add to the List*/
                        }

                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }
        return uOMList;
    }

    public ArrayList<Stock> forStockTableModel(String searchText) {
        ArrayList<Stock> stockList = new ArrayList<Stock>();
        ArrayList<Item> itemList = getItemList("all");
        ArrayList<UOM> UOMList = this.getUOMList();
        Connection connect = null;

        try {
            try {

                connect = connectToDB();
                Statement getStockList = connect.createStatement();
                ResultSet returnStockList = getStockList.executeQuery("select * from Stock");
                String item = null;
                while (returnStockList.next()) {
                    String itemId = returnStockList.getString("ITEMID");
                    int quantity = returnStockList.getInt("QUANTITY");
                    String uom = returnStockList.getString("UOMID");
                    int costPrice = returnStockList.getInt("COSTPRICE");
                    String date = returnStockList.getString("DATE");
                    int salingPrice = 0;

                    /*PreparedStatement getItem = connect.prepareStatement("select * from Item where ITEMID=?");
                    getItem.setString(1, itemId);
                    ResultSet itemResult = getItem.executeQuery();
                    if (itemResult.next()) {
                    item = itemResult.getString("NAME");
                    salingPrice = itemResult.getInt("PRICE");
                    
                    }*/
                    if (itemList.isEmpty()) {
                    } else {
                        for (int i = 0; i < itemList.size(); i++) {
                            if (itemList.get(i).getId().equalsIgnoreCase(itemId)) {
                                item = new String(itemList.get(i).getName());
                                salingPrice = itemList.get(i).getPrice();

                            }
                        }
                    }
                    /*PreparedStatement getUOM = connect.prepareStatement("select NAME from UOM where ID=?");
                    getUOM.setString(1, uom);
                    ResultSet uomResult = getUOM.executeQuery();
                    if (uomResult.next()) {
                    uom = uomResult.getString("NAME");
                    }*/
                    if (UOMList.isEmpty()) {
                    } else {
                        for (int i = 0; i < UOMList.size(); i++) {
                            if (UOMList.get(i).getId().equalsIgnoreCase(uom)) {
                                uom = UOMList.get(i).getName();
                            }
                        }
                    }

                    if (searchText.equalsIgnoreCase("all")) {
                        stockList.add(new Stock(itemId, item, quantity, uom, costPrice, salingPrice, date));
                    } else {
                        if ((itemId.startsWith(searchText)) || (item.toLowerCase().startsWith(searchText.toLowerCase()))) {
                            stockList.add(new Stock(itemId, item, quantity, uom, costPrice, salingPrice, date));
                        } else {
                            /*dont add to the List*/
                        }

                    }


                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "Error");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");

        }
        return stockList;
    }
    /*return list of items  being  sold*/

    public ArrayList<Item> getItem(String itemId) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement selectItemName = connect.prepareStatement("select * from Item where ITEMID=?");
            selectItemName.setString(1, itemId);
            ResultSet itemResult = selectItemName.executeQuery();

            if (itemResult.next()) {
                String item = itemResult.getString("NAME");
                int price = itemResult.getInt("PRICE");
                itemList.add(new Item(item, price));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return itemList;
    }

    public boolean deleteCashier(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteCashier = connect.prepareStatement("delete from Cashier where ID=?");
            deleteCashier.setString(1, id);
            int deleteResult = deleteCashier.executeUpdate();
            if (deleteResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;
    }

    public boolean deleteMainCategory(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteMainCategory = connect.prepareStatement("delete from MainCategory where MAINID=?");
            deleteMainCategory.setString(1, id);
            int deleteMainCategoryResult = deleteMainCategory.executeUpdate();
            if (deleteMainCategoryResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean deleteSubCategory(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteSubCategory = connect.prepareStatement("delete from SubCategory where SUBID=?");
            deleteSubCategory.setString(1, id);
            int deleteSubCategoryResult = deleteSubCategory.executeUpdate();
            if (deleteSubCategoryResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean deleteItem(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteItem = connect.prepareStatement("delete from Item where ITEMID=?");
            deleteItem.setString(1, id);
            int deleteItemResult = deleteItem.executeUpdate();
            if (deleteItemResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean deleteStockItem(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteItem = connect.prepareStatement("delete from Stock where ITEMID=?");
            deleteItem.setString(1, id);
            int deleteItemResult = deleteItem.executeUpdate();
            if (deleteItemResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean deleteUOM(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteUOM = connect.prepareStatement("delete from UOM where ID=?");
            deleteUOM.setString(1, id);
            int deleteUOMResult = deleteUOM.executeUpdate();
            if (deleteUOMResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean deleteExpenditure(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteExpenditure = connect.prepareStatement("delete from CashierExpenditure where ID=?");
            deleteExpenditure.setString(1, id);
            int deleteExpenditureResult = deleteExpenditure.executeUpdate();
            if (deleteExpenditureResult > 0) {
                deleted = true;

            } else {
                deleted = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return deleted;

    }

    public boolean updateMainCategory(String id, String name, String status, String date) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateMainCategory = connect.prepareStatement("update MainCategory set NAME=?, STATUS=?,DATE=? where  MAINID=?");
            updateMainCategory.setString(1, name);
            updateMainCategory.setString(2, status);
            updateMainCategory.setString(3, date);
            updateMainCategory.setString(4, id);
            int updateResult = updateMainCategory.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            updated = false;

        }
        return updated;

    }

    public boolean updateSubCategory(SubCategory subCategory) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateSubCategory = connect.prepareStatement("update SubCategory set MAINID=?, NAME=?, STATUS=?,DATE=? where SUBID=?");
            updateSubCategory.setString(1, subCategory.getMainCatId());
            updateSubCategory.setString(2, subCategory.getName());
            updateSubCategory.setString(3, subCategory.getStatus());
            updateSubCategory.setString(4, subCategory.getDate());
            updateSubCategory.setString(5, subCategory.getId());
            int updateResult = updateSubCategory.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "error");
            updated = false;

        }
        return updated;

    }

    public boolean updateItem(Item item) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateItem = connect.prepareStatement("update Item set SUBID=?,NAME=?,PRICE=?,STATUS=?,DATE=? where ITEMID=?");

            updateItem.setString(1, item.getSubCategoryId());
            updateItem.setString(2, item.getName());
            updateItem.setInt(3, item.getPrice());
            updateItem.setString(4, item.getStatus());
            updateItem.setString(5, item.getDate());
            updateItem.setString(6, item.getId());
            int updateResult = updateItem.executeUpdate();
            if (updateResult > 0) {
                updated = true;

            } else {
                updated = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "meeeee");
            updated = false;

        }
        return updated;

    }

    public boolean updateCashier(Cashier cashier) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateCashier = connect.prepareStatement("update Cashier set FIRSTNAME=?, LASTNAME=?,DATE=? where ID=?");
            updateCashier.setString(1, cashier.getFirstName());
            updateCashier.setString(2, cashier.getLastName());
            updateCashier.setString(3, cashier.getDate());
            updateCashier.setString(4, cashier.getId());
            int updateResult = updateCashier.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            updated = false;
        }
        return updated;
    }

    public boolean updateUOM(UOM uOM) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateUOM = connect.prepareStatement("update UOM set NAME=?, STATUS=?,DATE=? where ID=?");
            updateUOM.setString(1, uOM.getName());
            updateUOM.setString(2, uOM.getStatus());
            updateUOM.setString(3, uOM.getDate());
            updateUOM.setString(4, uOM.getId());
            int updateResult = updateUOM.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            updated = false;
        }
        return updated;
    }

    public boolean updateExpenditure(CashierExpendure cashierExpendure) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateExpenditure = connect.prepareStatement("update CashierExpenditure set AMOUNT=?, REASON=?,DATE=? where ID=?");
            updateExpenditure.setInt(1, cashierExpendure.getAmount());
            updateExpenditure.setString(2, cashierExpendure.getReason());
            updateExpenditure.setString(3, cashierExpendure.getDate());
            updateExpenditure.setString(4, cashierExpendure.getCashierId());
            int updateResult = updateExpenditure.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            updated = false;
        }
        return updated;
    }

    public boolean updateStock(Stock stock) {
        Connection connect = null;
        boolean updated = false;
        try {
            connect = connectToDB();
            PreparedStatement updateStock = connect.prepareStatement("update Stock set QUANTITY=?, UOMID=?, COSTPRICE=?,DATE=? where ITEMID=?");

            updateStock.setInt(1, stock.getQuantity());
            updateStock.setString(2, stock.getUOM());
            updateStock.setInt(3, stock.getUnitCostPrice());
            updateStock.setString(4, stock.getDate());
            updateStock.setString(5, stock.getItem());
            int updateResult = updateStock.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            } else {
                updated = false;

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            updated = false;

        }
        return updated;

    }

    public boolean recordReceipt(Receipt receipt) {
        String id = receipt.getId();
        int total = receipt.getTotal();
        int discount = receipt.getDiscount();
        int cash = receipt.getCash();
        int balanceDue = receipt.getBalanceDue();
        int change = receipt.getChange();
        String date = receipt.getDate();
        boolean recorded = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement recordReceipt = connect.prepareStatement("insert into Receipt (ID ,TOTAL,DISCOUNT,BALANCEDUE,CASH,CHANG,DATE) values(?,?,?,?,?,?,?)");
            recordReceipt.setString(1, id);
            recordReceipt.setInt(2, total);
            recordReceipt.setInt(3, discount);
            recordReceipt.setInt(4, balanceDue);
            recordReceipt.setInt(5, cash);
            recordReceipt.setInt(6, change);
            recordReceipt.setString(7, date);
            int recordReceiptResult = recordReceipt.executeUpdate();
            if (recordReceiptResult > 0) {
                recorded = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return recorded;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private String getCurrentDate() {
        String[] months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        int date = (Integer.parseInt(strDate.substring(0, 2)));
        String currentMonth = months[Integer.parseInt(strDate.substring(3, 5)) - 1];
        String year = strDate.substring(6, 10);

        return date + "/" + currentMonth + "/" + year;
    }

    private ArrayList<OpeningStock> getStock(String date) {
        ArrayList<OpeningStock> list = new ArrayList<OpeningStock>();
        try {
            Connection connect = this.connectToDB();
            Statement getList = connect.createStatement();
            ResultSet result = getList.executeQuery("select * from Stock");
            while (result.next()) {
                String item = result.getString("ITEMID");
                int quantity = result.getInt("QUANTITY");
                list.add(new OpeningStock(item, quantity, date));
            }
            System.out.print(list.size() + "Stock size");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    private void recordOpeningStock(ArrayList<OpeningStock> list) {
        Connection connect = null;
        PreparedStatement addItem = null;
        int stockCount = 0;
        try {
            connect = this.connectToDB();
            for (int i = 0; i < list.size(); i++) {
                addItem = connect.prepareStatement("insert into OpeningStock(Item,Quantity,Date) values(?,?,?)");
                addItem.setString(1, list.get(i).getItem());
                addItem.setInt(2, list.get(i).getQuantity());
                addItem.setString(3, list.get(i).getDate());
                addItem.executeUpdate();
                stockCount++;
            }
            System.out.println(stockCount);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public int[] getProductQuantitySolid(String item, String date) {
        int[] quantityAmount = new int[2];
        Connection connect = null;
        try {
            connect = this.connectToDB();
            PreparedStatement getQuantity = connect.prepareStatement("select QUANTITY,PRICE from Sale where ITEMID=? and DATE=?");
            getQuantity.setString(1, item);
            getQuantity.setString(2, date);
            ResultSet quantityResult = getQuantity.executeQuery();
            while (quantityResult.next()) {
                quantityAmount[0] += quantityResult.getInt("QUANTITY");
                quantityAmount[1] += quantityResult.getInt("PRICE");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return quantityAmount;

    }

    public String getProductName(String id) {
        String productName = null;
        Connection connect = null;
        try {
            connect = this.connectToDB();
            PreparedStatement getName = connect.prepareStatement("Select NAME from Item where ITEMID=?");
            getName.setString(1, id);
            ResultSet nameResult = getName.executeQuery();
            if (nameResult.next()) {
                productName = nameResult.getString("NAME");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return productName;

    }

    public ArrayList<ClosingStock> getClosingStockItems(String date) {
        ArrayList<ClosingStock> list = new ArrayList<ClosingStock>();
        Connection connect = null;
        int errorLine = 0;
        try {
            connect = this.connectToDB();
            PreparedStatement getList = connect.prepareStatement("Select * from OpeningStock where Date=?");
            getList.setString(1, date);
            ResultSet listResult = getList.executeQuery();
            String itemName = null;
            while (listResult.next()) {

                String item = listResult.getString("Item");
                int openingStock = listResult.getInt("Quantity");
                int[] product = new Controller().getProductQuantitySolid(item, date);
                int quantity = product[0];

                int amount = product[1];
                itemName = this.getProductName(item);
                int closingStock = openingStock - quantity;

                if (itemName == null) {
                    list.add(new ClosingStock(item, quantity, amount, openingStock, closingStock));

                } else {
                    // System.out.println(itemName);
                    list.add(new ClosingStock(itemName, quantity, amount, openingStock, closingStock));

                }

            }
            System.out.println("OpeningClosing Stock" + list.size());

        } catch (Exception ex) {
            System.out.println(ex.getCause() + "OpeningClosing Stock ERROR" + errorLine);
        }
        return list;
    }

    private boolean OpeningStockExists(String date) {
        boolean exists = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement check = connect.prepareStatement("select * from OpeningStock where Date=?");
            check.setString(1, date);
            ResultSet checkResult = check.executeQuery();
            if (checkResult.next()) {
                exists = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }

    public boolean initialise() {
        boolean initialised = false;
        /*if (createTables() == true) {
        initialised = true;
        }*/
        if (new DerbyDataBase().initialise() == true) {
            initialised = true;
        }

        return initialised;

    }

    public boolean createTables() {
        //createOpeningStockTable();
        if (createDataBase() && createTabeMainCategory() && createTableSubCategory() && createTableItem() && createTableIdStore()
                && createTableUOM() && createTableStock() && createTableSale() && createTableReceipt() && createTableIdAdmin()
                && createTableCashier() && createCashierExpeditureTable() && createTableSummary() && createOpeningStockTable()
                && createTableSupplier() && createTableSupplierContact() && createTableSupplierProduct()) {
            return true;
        } else {
            return false;

        }
    }

    public Connection connectToDB() {
        Connection connect = null;
        try {
            /*
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String host = "localhost";
            String port = "3306";
            String db = "VPS";
            String mysqlURL = "jdbc:mysql://" + host + ":" + port + "/" + db;
            connect = DriverManager.getConnection(mysqlURL, "root", "");
             */
            connect = new DerbyDataBase().connectToDB();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return connect;
    }

    public boolean createDataBase() {
        Connection connect = null;
        boolean created = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String host = "localhost";
            String port = "3306";
            String db = "VPS";
            String user = "root";
            String passWord = "";
            connect = DriverManager.getConnection("jdbc:mysql://" + host + "/?user=" + user + "&password=" + passWord + "");
            Statement createDataBase = connect.createStatement();
            int createResult = createDataBase.executeUpdate("create database if not exists " + db + "");
            if (createResult > 0) {
                created = true;
                /*JOptionPane.showMessageDialog(null, "created " + db + " DataBase");
                ResultSet useDB=createDataBase.executeQuery("use "+db+"");
                if(useDB!=null){
                JOptionPane.showMessageDialog(null, "DataBase Changed");
                }*/
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return created;

    }

    public boolean createTabeMainCategory() {
        Connection connect;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists MainCategory(MAINID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table MainCategory created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return created;
    }

    public boolean createTableSubCategory() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists SubCategory(SUBID varchar(25) not null,MAINID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table SubCategory created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableItem() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Item(ITEMID varchar(25) not null,SUBID varchar(25) not null,NAME varchar(80) not null,PRICE int not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Item created");

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableUOM() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists UOM(ID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table UOM created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableStock() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Stock(ITEMID varchar(25) not null,QUANTITY int not null,UOMID varchar(10) not null,COSTPRICE int not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Stock created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSale() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Sale(ITEMID varchar(25) not null,RECEIPTID varchar(25) not null,QUANTITY int not null,PRICE int not null,USAR varchar(25) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Sale created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableReceipt() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Receipt(ID varchar(25) not null,TOTAL int not null,DISCOUNT int not null,BALANCEDUE int not null,CASH int not null,CHANG int not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Receipt created");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableIdStore() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists IdStore(ID int not null auto_increment, primary key(ID))");
            if (createResult == 0) {
                created = true;
                System.out.println("Table IdStore created");

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableCashier() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Cashier(ID varchar(25) not null,FIRSTNAME varchar(25) not null,LASTNAME varchar(25) not null, DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Cashier created");

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableIdAdmin() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Admin(PASSWORD varchar(25) not null, USERNAME varchar(25) not null)");
            if (createResult == 0) {
                ResultSet checkTable = createTable.executeQuery("Select * from Admin");
                if (!(checkTable.next())) {
                    int initialAdmin = createTable.executeUpdate("insert into Admin(PASSWORD,USERNAME) values('123','admin')");
                    System.out.println("Table Admin created");
                    if (initialAdmin > 0) {
                        created = true;
                        System.out.println("Admin created");
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createCashierExpeditureTable() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists CashierExpenditure(ID varchar(25) not null,CASHIER varchar(25) not null,AMOUNT int not null,REASON varchar(25) not null, DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table CashierExpenditure created");

            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;

    }

    public boolean createTableSummary() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table if not exists Summary(CASHIER varchar(25) not null,SIGNIN varchar(25) not null,SIGNOUT varchar(25) not null,DATE varchar(25) not null, TOTALCOLLECTION int not null,TOTALEXPENDITURE int not null,CASHATHAND int not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Summary created");

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createOpeningStockTable() {
        boolean created = false;
        Connection connect = null;
        try {
            connect = this.connectToDB();
            Statement createTable = connect.createStatement();
            int result = createTable.executeUpdate("create table OpeningStock(Item varchar(50) not null,Quantity int not null,Date varchar(25) not null)");
            if (result == 0) {
                created = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSupplier() {
        boolean created = false;
        Connection connect = null;
        try {
            connect = this.connectToDB();
            Statement createTable = connect.createStatement();
            int result = createTable.executeUpdate("create table Supplier(Id varchar(50) not null,Name varchar(100) not null,Date varchar(25) not null)");
            if (result == 0) {
                created = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSupplierContact() {
        boolean created = false;
        Connection connect = null;
        try {
            connect = this.connectToDB();
            Statement createTable = connect.createStatement();
            int result = createTable.executeUpdate("create table SupplierContact(Id varchar(50) not null,Supplier varchar(100) not null,Contact varchar(100) not null,Date varchar(25) not null)");
            if (result == 0) {
                created = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSupplierProduct() {
        boolean created = false;
        Connection connect = null;
        try {
            connect = this.connectToDB();
            Statement createTable = connect.createStatement();
            int result = createTable.executeUpdate("create table SupplierProduct(Id varchar(50) not null,Supplier varchar(100) not null,Product varchar(100) not null,Date varchar(25) not null)");
            if (result == 0) {
                created = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }

    /*When connected to Wamp*/
    /*public String generateId() {
    Connection connect = null;
    int idCount = 0;
    String id = null;
    try {
    connect = connectToDB();
    Statement insertData = connect.createStatement();
    int inserResult = insertData.executeUpdate("insert into IdStore(ID) values (null)");
    if (inserResult > 0) {
    ResultSet getId = insertData.executeQuery("select * from IdStore");
    while (getId.next()) {
    idCount++;
    }
    long n = idCount;
    int digits = 6;
    char[] zeros = new char[digits];
    Arrays.fill(zeros, '0');
    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
    id = df.format(n);
    }
    
    } catch (Exception ex) {
    System.out.println(ex.getMessage());
    }
    return id;
    }*/
    /*When connected to Derby*/
    public String generateId() {
        Connection connect = null;
        int idCount = 0;
        String id = null;
        try {
            connect = connectToDB();
            Statement insertData = connect.createStatement();
            int inserResult = insertData.executeUpdate("insert into IdStore(ID) values(default)");
            if (inserResult > 0) {
                ResultSet getId = insertData.executeQuery("select * from IdStore");
                while (getId.next()) {
                    idCount++;
                }
                long n = idCount;
                int digits = 6;
                char[] zeros = new char[digits];
                Arrays.fill(zeros, '0');
                DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
                id = df.format(n);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "kasoma");
        }



        return id;
    }
    //downloaded

    /*NEW CUSTOMISATION*/
    public boolean addSupplier(Supplier supplier) {
        boolean added = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement addSupplier = connect.prepareStatement("insert into Supplier(Id,Name,Date) values(?,?,?)");
            addSupplier.setString(1, supplier.getId());
            addSupplier.setString(2, supplier.getName());
            addSupplier.setString(3, supplier.getDate());
            int addResult = addSupplier.executeUpdate();
            if (addResult > 0) {
                added = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return added;
    }

    public boolean deleteSupplier(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement deleteSupplier = connect.prepareStatement("delete from Supplier where Id=?");
            deleteSupplier.setString(1, id);
            int deleteResult = deleteSupplier.executeUpdate();
            if (deleteResult > 0) {
                deleted = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return deleted;
    }

    public boolean updateSupplier(Supplier supplier) {
        boolean updated = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement update = connect.prepareStatement("update Supplier set Name=?,Date=? where Id=?");
            update.setString(1, supplier.getName());
            update.setString(2, supplier.getDate());
            update.setString(3, supplier.getId());
            int updateResult = update.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return updated;
    }

    public boolean addSupplierContact(SupplierContact contact) {
        boolean added = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement addContact = connect.prepareStatement("insert into SupplierContact(Id,Supplier,Contact,Date) values(?,?,?,?)");
            addContact.setString(1, contact.getId());
            addContact.setString(2, contact.getSupplier());
            addContact.setString(3, contact.getContact());
            addContact.setString(4, contact.getDate());
            int addResult = addContact.executeUpdate();
            if (addResult > 0) {
                added = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return added;
    }

    public boolean updateSupplierContact(SupplierContact contact) {
        boolean updated = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement update = connect.prepareStatement("update SupplierContact set Supplier=?,Contact=?,Date=? where Id=?");
            update.setString(1, contact.getSupplier());
            update.setString(2, contact.getContact());
            update.setString(3, contact.getDate());
            update.setString(4, contact.getId());
            int updateResult = update.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return updated;

    }

    public boolean deleteSupplierContact(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement delete = connect.prepareStatement("delete from SupplierContact where Id=?");
            delete.setString(1, id);
            int deleteResult = delete.executeUpdate();
            if (deleteResult > 0) {
                deleted = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return deleted;
    }

    public boolean addSupplierProduct(SupplierProduct product) {
        boolean added = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement addProduct = connect.prepareStatement("insert into SupplierProduct(Id,Supplier,Product,Price,UOM,Date) values(?,?,?,?,?,?)");
            addProduct.setString(1, product.getId());
            addProduct.setString(2, product.getSupplier());
            addProduct.setString(3, product.getProduct());
            addProduct.setString(4, product.getPrice());
            addProduct.setString(5, product.getUOM());
            addProduct.setString(6, product.getDate());
            int addResult = addProduct.executeUpdate();
            if (addResult > 0) {
                added = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return added;
    }

    public boolean updateSupplierProduct(SupplierProduct product) {
        boolean updated = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement update = connect.prepareStatement("update SupplierProduct set Supplier=?,Product=?,Price=?,UOM=?,Date=? where Id=?");
            update.setString(1, product.getSupplier());
            update.setString(2, product.getProduct());
            update.setString(3, product.getPrice());
            update.setString(4, product.getUOM());
            update.setString(5, product.getDate());
            update.setString(6, product.getId());
            int updateResult = update.executeUpdate();
            if (updateResult > 0) {
                updated = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return updated;

    }

    public boolean deleteSupplierProduct(String id) {
        boolean deleted = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement delete = connect.prepareStatement("delete from SupplierProduct where Id=?");
            delete.setString(1, id);
            int deleteResult = delete.executeUpdate();
            if (deleteResult > 0) {
                deleted = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return deleted;
    }

    public int getCostPrice(Connection connect, String id) {
        int costPrice = 0;
        try {
            PreparedStatement getCostPrice = connect.prepareStatement("select COSTPRICE from Stock where ITEMID=?");
            getCostPrice.setString(1, id);
            ResultSet costPriceResult = getCostPrice.executeQuery();
            while (costPriceResult.next()) {
                costPrice = costPriceResult.getInt("COSTPRICE");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return costPrice;
    }

    private String getUOM(String id) {
        String UOM = "";
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getUOM = connect.prepareStatement("select NAME from UOM where ID=?");
            getUOM.setString(1, id);
            ResultSet UOMResult = getUOM.executeQuery();
            while (UOMResult.next()) {
                UOM = UOMResult.getString("NAME");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return UOM;
    }

    public boolean findIfExists(String itemId) {
        boolean exists = false;
        Connection connect = null;
        try {
            connect = connectToDB();
            PreparedStatement getItem = connect.prepareStatement("Select ITEMID from Item where ITEMID=?");
            getItem.setString(1, itemId);
            ResultSet getItemResult = getItem.executeQuery();
            if (getItemResult.next()) {
                exists = true;
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Eden");
        }
        return exists;
    }

    public boolean backUpDatabase(String backupdirectory) {
        try {
            Connection conn = connectToDB();
            CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
            cs.setString(1, backupdirectory);
            int backups = cs.executeUpdate();
            if (backups == 0) {
                cs.close();
                return true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }
}
