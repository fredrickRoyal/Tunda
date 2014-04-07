/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *100-240v 1.6amps 50-60
 * 19v 3.42amps 64W
 * Millennium computers
 * @author royal
 */
public class VPSPOSSEVER {

    /**
     * @param args the command line arguments
     */
    public VPSPOSSEVER() {

        ArrayList<String> dataTokens = new ArrayList<String>();
        String message = null;
        try {

            ServerSocket socket = new ServerSocket(1090);
            System.out.println("listening at port 1090");
            String command = null;
            while (true) {
                Socket connect = socket.accept();
                BufferedReader incoming = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                message = incoming.readLine();
                dataTokens = tokens(message);
                command = dataTokens.get(0);
                if (command.equalsIgnoreCase("adminLogin")) {
                    try {
                        boolean login = new Controller().adminLogin(dataTokens.get(1), dataTokens.get(2));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (login == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }


                } else if (command.equalsIgnoreCase("adminChangePassword")) {
                    try {
                        boolean changePassWord = new Controller().adminChangePassword(dataTokens.get(1), dataTokens.get(2));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (changePassWord == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }


                } else if (command.equalsIgnoreCase("cashierLogin")) {
                    try {
                        String cashier = new Controller().cashierLogin(dataTokens.get(1));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (cashier == null) {
                            out.println("false");
                            out.flush();
                            out.close();
                        } else {

                            out.println(cashier);
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("initialise")) {
                    try {
                        boolean initialised = new Controller().initialise();
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (initialised == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("addCashierToDB")) {
                    String id = dataTokens.get(1);
                    String firstName = dataTokens.get(2);
                    String lastName = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean saved = new Controller().addCashierToDB(id, firstName, lastName, date);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addSupplier")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String date = dataTokens.get(3);
                    try {
                        boolean saved = new Controller().addSupplier(new Supplier(id, name, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addSupplierContact")) {
                    String id = dataTokens.get(1);
                    String supplier = dataTokens.get(2);
                    String contact = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean saved = new Controller().addSupplierContact(new SupplierContact(id, supplier, contact, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addSupplierProduct")) {
                    String id = dataTokens.get(1);
                    String supplier = dataTokens.get(2);
                    String product = dataTokens.get(3);
                    String price = dataTokens.get(4);
                    String uom = dataTokens.get(5);
                    String date = dataTokens.get(6);
                    try {
                        boolean saved = new Controller().addSupplierProduct(new SupplierProduct(id, supplier, product, price, uom, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addCashierExpenditureToDB")) {
                    String expenditureId = dataTokens.get(1);
                    String cashierId = dataTokens.get(2);
                    int amount = Integer.parseInt(dataTokens.get(3));
                    String reason = dataTokens.get(4);
                    String date = dataTokens.get(5);
                    try {
                        boolean saved = new Controller().addCashierExpenditureToDB(new CashierExpendure(expenditureId, cashierId, amount, reason, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("cashierSignOff")) {
                    String cashierId = dataTokens.get(1);
                    String signIn = dataTokens.get(2);
                    String signOut = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    int totalCollection = Integer.parseInt(dataTokens.get(5));
                    int totalExpenditure = Integer.parseInt(dataTokens.get(6));
                    int cashAtHand = Integer.parseInt(dataTokens.get(7));
                    try {
                        boolean summaried = new Controller().cashierSignOff(new Summary(cashierId, signIn, signOut, date, totalCollection, totalExpenditure, cashAtHand));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (summaried == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addMainCategoryToDB")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String status = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean saved = new Controller().addMainCategoryToDB(id, name, status, date);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (saved == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addSubCategoryToDB")) {
                    String id = dataTokens.get(1);
                    String mainCatId = dataTokens.get(2);
                    String name = dataTokens.get(3);
                    String status = dataTokens.get(4);
                    String date = dataTokens.get(5);
                    try {
                        boolean add = new Controller().addSubCategoryToDB(new SubCategory(id, mainCatId, name, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (add == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("addItemToDB")) {
                    String id = dataTokens.get(1);
                    String subCategoryId = dataTokens.get(2);
                    String name = dataTokens.get(3);
                    int price = Integer.parseInt(dataTokens.get(4));
                    String status = dataTokens.get(5);
                    String date = dataTokens.get(6);
                    try {
                        boolean add = new Controller().addItemToDB(new Item(id, subCategoryId, name, price, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (add == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("ddUOMToDB")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String status = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean add = new Controller().addUOMToDB(new UOM(id, name, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (add == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }



                } else if (command.equalsIgnoreCase("addStockToDB")) {
                    String item = dataTokens.get(1);
                    int quantity = Integer.parseInt(dataTokens.get(2));
                    String uom = dataTokens.get(3);
                    int costPrice = Integer.parseInt(dataTokens.get(4));
                    String date = dataTokens.get(5);
                    try {
                        boolean add = new Controller().addStockToDB(new Stock(item, quantity, uom, costPrice, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (add == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("cashierTableModel")) {
                    ArrayList<Cashier> cashierList = new ArrayList<Cashier>();
                    cashierList = new Controller().forCashierTableModel();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (cashierList != null) {
                            for (int i = 0; i < cashierList.size(); i++) {
                                String id = cashierList.get(i).getId();
                                String firstName = cashierList.get(i).getFirstName();
                                String lastName = cashierList.get(i).getLastName();
                                String date = cashierList.get(i).getDate();
                                String cashierRow = id + "^" + firstName + "^" + lastName + "^" + date;
                                out.println(cashierRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("supplierTableModel")) {
                    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
                    supplierList = new Controller().forSupplierTableModel();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (supplierList != null) {
                            for (int i = 0; i < supplierList.size(); i++) {
                                String id = supplierList.get(i).getId();
                                String name = supplierList.get(i).getName();
                                String date = supplierList.get(i).getDate();
                                String supplierRow = id + "^" + name + "^" + date;
                                out.println(supplierRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("supplierContactTableModel")) {
                    ArrayList<SupplierContact> contactList = new ArrayList<SupplierContact>();
                    contactList = new Controller().forSupplierContactTableModel();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (contactList != null) {
                            for (int i = 0; i < contactList.size(); i++) {
                                String id = contactList.get(i).getId();
                                String supplier = contactList.get(i).getSupplier();
                                String contact = contactList.get(i).getContact();
                                String date = contactList.get(i).getDate();
                                String contactRow = id + "^" + supplier + "^" + contact + "^" + date;
                                out.println(contactRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("supplierProductTableModel")) {
                    ArrayList<SupplierProduct> productList = new ArrayList<SupplierProduct>();
                    productList = new Controller().forSupplierProductTableModel();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (productList != null) {
                            for (int i = 0; i < productList.size(); i++) {
                                String id = productList.get(i).getId();
                                String supplier = productList.get(i).getSupplier();
                                String product = productList.get(i).getProduct();
                                String price = productList.get(i).getPrice();
                                String uom = productList.get(i).getUOM();
                                String date = productList.get(i).getDate();
                                String productRow = id + "^" + supplier + "^" + product + "^" + price + "^" + uom + "^" + date;
                                out.println(productRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("cashierExpenditureTableModel")) {
                    ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
                    expenditureList = new Controller().forCashierExpenditureTableModel(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (expenditureList != null) {
                            for (int i = 0; i < expenditureList.size(); i++) {
                                String id = expenditureList.get(i).getCashierId();
                                int amount = expenditureList.get(i).getAmount();
                                String reason = expenditureList.get(i).getReason();
                                String date = expenditureList.get(i).getDate();
                                String expenditureRow = id + "^" + amount + "^" + reason + "^" + date;
                                out.println(expenditureRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("getTotalExpenditure")) {
                    ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
                    expenditureList = new Controller().getTotalExpenditure(dataTokens.get(1), dataTokens.get(2));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (expenditureList != null) {
                            for (int i = 0; i < expenditureList.size(); i++) {
                                String id = expenditureList.get(i).getCashierId();
                                int amount = expenditureList.get(i).getAmount();
                                String reason = expenditureList.get(i).getReason();
                                String date = expenditureList.get(i).getDate();
                                String expenditureRow = id + "^" + amount + "^" + reason + "^" + date;
                                out.println(expenditureRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("getCustomCashierTotalExpenditure")) {
                    ArrayList<CashierExpendure> expenditureList = new ArrayList<CashierExpendure>();
                    expenditureList = new Controller().getCustomCashierTotalExpenditure(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (expenditureList != null) {
                            for (int i = 0; i < expenditureList.size(); i++) {
                                String id = expenditureList.get(i).getCashierId();
                                int amount = expenditureList.get(i).getAmount();
                                String reason = expenditureList.get(i).getReason();
                                String date = expenditureList.get(i).getDate();
                                String expenditureRow = id + "^" + amount + "^" + reason + "^" + date;
                                out.println(expenditureRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("mainCategoryTableModel")) {
                    ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
                    mainCategoryList = new Controller().forMainCategoryTableModel(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (mainCategoryList != null) {
                            for (int i = 0; i < mainCategoryList.size(); i++) {
                                String id = mainCategoryList.get(i).getId();
                                String name = mainCategoryList.get(i).getName();
                                String status = mainCategoryList.get(i).getStatus();
                                String date = mainCategoryList.get(i).getDate();
                                String mainCategoryRow = id + "^" + name + "^" + status + "^" + date;
                                out.println(mainCategoryRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("subCategoryTableModel")) {
                    ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
                    subCategoryList = new Controller().forSubCategoryTableModel(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (subCategoryList != null) {
                            for (int i = 0; i < subCategoryList.size(); i++) {
                                String id = subCategoryList.get(i).getId();
                                String mainCatid = subCategoryList.get(i).getMainCatId();
                                String name = subCategoryList.get(i).getName();
                                String status = subCategoryList.get(i).getStatus();
                                String date = subCategoryList.get(i).getDate();
                                String subCategoryRow = id + "^" + mainCatid + "^" + name + "^" + status + "^" + date;
                                out.println(subCategoryRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("itemTableModel")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forItemTableModel(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String subCatid = itemList.get(i).getSubCategoryId();
                                String name = itemList.get(i).getName();
                                int price = itemList.get(i).getPrice();
                                String status = itemList.get(i).getStatus();
                                String date = itemList.get(i).getDate();
                                String itemRow = id + "^" + subCatid + "^" + name + "^" + price + "^" + status + "^" + date;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("totalCollectionTableModel")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().fortotalCollectionTableModel(dataTokens.get(1), dataTokens.get(2));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("generalDayTotalCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgeneralDayTotalCollection(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                int totalCostPrice = itemList.get(i).getTotalCostPrice();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount + "^" + totalCostPrice;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getCustomDateTotalCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetCustomDateTotalCollection(dataTokens.get(1), dataTokens.get(2));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                int totalCostPrice = itemList.get(i).getTotalCostPrice();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount + "^" + totalCostPrice;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getCustomCashierDateTotalCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetCustomCashierDateTotalCollection(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                int totalCostPrice = itemList.get(i).getTotalCostPrice();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount + "^" + totalCostPrice;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getMainCategoryCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetMainCategoryCollection(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getSubCategoryCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetSubCategoryCollection(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getCashierMainCategoryCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetCashierMainCategoryCollection(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3), dataTokens.get(4));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getCashierSubCategoryCollection")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().forgetCashierSubCategoryCollection(dataTokens.get(1), dataTokens.get(2), dataTokens.get(3), dataTokens.get(4));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String quantity = itemList.get(i).getQuantity();
                                String amount = itemList.get(i).getAmount();
                                String itemRow = id + "^" + name + "^" + quantity + "^" + amount;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("UOMTableModel")) {
                    ArrayList<UOM> uOMList = new ArrayList<UOM>();
                    uOMList = new Controller().forUOMTableModel(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (uOMList != null) {
                            for (int i = 0; i < uOMList.size(); i++) {
                                String id = uOMList.get(i).getId();
                                String name = uOMList.get(i).getName();
                                String status = uOMList.get(i).getStatus();
                                String date = uOMList.get(i).getDate();
                                String itemRow = id + "^" + name + "^" + status + "^" + date;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }



                } else if (command.equalsIgnoreCase("searchItem")) {
                    String category = dataTokens.get(1);
                    String firstItemCharacters = dataTokens.get(2);
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    if (firstItemCharacters.equalsIgnoreCase("all")) {
                        itemList = new Controller().searchAll();
                    } else {
                        itemList = new Controller().search(category, firstItemCharacters);
                    }

                    System.out.println(firstItemCharacters);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String name = itemList.get(i).getName();
                                String itemRow = id + "^" + name;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("stockTableModel")) {
                    ArrayList<Stock> stockList = new ArrayList<Stock>();
                    stockList = new Controller().forStockTableModel(dataTokens.get(1));
                    System.out.println(dataTokens.get(1) + "stock");
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (stockList != null) {
                            for (int i = 0; i < stockList.size(); i++) {
                                String itemId = stockList.get(i).getItemId();
                                String item = stockList.get(i).getItem();
                                int quantity = stockList.get(i).getQuantity();
                                String uom = stockList.get(i).getUOM();
                                int costPrice = stockList.get(i).getUnitCostPrice();
                                int sellingPrice = stockList.get(i).getSellingPrice();
                                String date = stockList.get(i).getDate();
                                String itemRow = itemId + "^" + item + "^" + quantity + "^" + uom + "^" + costPrice + "^" + sellingPrice + "^" + date;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }


                } else if (command.equalsIgnoreCase("getClosingStockItems")) {
                    ArrayList<ClosingStock> stockList = new ArrayList<ClosingStock>();
                    stockList = new Controller().getClosingStockItems(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (stockList != null) {
                            for (int i = 0; i < stockList.size(); i++) {
                                String item = stockList.get(i).getItem();
                                int quantity = stockList.get(i).getQuantity();
                                int amount = stockList.get(i).getAmount();
                                int openingStock = stockList.get(i).getOpeningStock();
                                int closingStock = stockList.get(i).getClosingStock();
                                String stockRow = item + "^" + quantity + "^" + amount + "^" + openingStock + "^" + closingStock;
                                out.println(stockRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("getMainCategoryList")) {
                    ArrayList<MainCategory> mainCategoryList = new ArrayList<MainCategory>();
                    mainCategoryList = new Controller().getMainCategoryList(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (mainCategoryList != null) {
                            for (int i = 0; i < mainCategoryList.size(); i++) {
                                String id = mainCategoryList.get(i).getId();
                                String name = mainCategoryList.get(i).getName();
                                String status = mainCategoryList.get(i).getStatus();
                                String date = mainCategoryList.get(i).getDate();
                                String mainCategoryRow = id + "^" + name + "^" + status + "^" + date;
                                out.println(mainCategoryRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getSubCategoryList")) {
                    ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
                    subCategoryList = new Controller().getSubCategoryList(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (subCategoryList != null) {
                            for (int i = 0; i < subCategoryList.size(); i++) {
                                String id = subCategoryList.get(i).getId();
                                String mainCatid = subCategoryList.get(i).getMainCatId();
                                String name = subCategoryList.get(i).getName();
                                String status = subCategoryList.get(i).getStatus();
                                String date = subCategoryList.get(i).getDate();
                                String subCategoryRow = id + "^" + mainCatid + "^" + name + "^" + status + "^" + date;
                                out.println(subCategoryRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("getItemList")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().getItemList(dataTokens.get(1));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String id = itemList.get(i).getId();
                                String subCatid = itemList.get(i).getSubCategoryId();
                                String name = itemList.get(i).getName();
                                int price = itemList.get(i).getPrice();
                                String status = itemList.get(i).getStatus();
                                String date = itemList.get(i).getDate();
                                String itemRow = id + "^" + subCatid + "^" + name + "^" + price + "^" + status + "^" + date;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getSupplierList")) {
                    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
                    supplierList = new Controller().getSupplierList();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (supplierList != null) {
                            for (int i = 0; i < supplierList.size(); i++) {
                                String id = supplierList.get(i).getId();
                                String name = supplierList.get(i).getName();
                                String date = supplierList.get(i).getDate();
                                String supplierRow = id + "^" + name + "^" + date;
                                out.println(supplierRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("getUOMList")) {
                    ArrayList<UOM> uOMList = new ArrayList<UOM>();
                    uOMList = new Controller().getUOMList();
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (uOMList != null) {
                            for (int i = 0; i < uOMList.size(); i++) {
                                String id = uOMList.get(i).getId();
                                String name = uOMList.get(i).getName();
                                String status = uOMList.get(i).getStatus();
                                String date = uOMList.get(i).getDate();
                                String itemRow = id + "^" + name + "^" + status + "^" + date;
                                out.println(itemRow);
                                out.flush();
                            }
                            out.close();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("getItem")) {
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    itemList = new Controller().getItem(dataTokens.get(1));
                    System.out.println(itemList.size());
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (itemList != null) {
                            for (int i = 0; i < itemList.size(); i++) {
                                String item = itemList.get(i).getName();
                                int price = itemList.get(i).getPrice();
                                String itemRow = item + "^" + price;
                                out.println(itemRow);
                                out.flush();
                            }

                        } else if (itemList.isEmpty()) {
                            out.println("false");
                            out.flush();
                        }
                        out.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("saleItem")) {
                    String item = dataTokens.get(1);
                    String receiptId = dataTokens.get(2);
                    int quantity = Integer.parseInt(dataTokens.get(3));
                    String cashier = dataTokens.get(4);
                    String date = dataTokens.get(5);
                    boolean saleItem = new Controller().saleItem(new Sale(item, receiptId, quantity, cashier, date));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (saleItem == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("updatesoldItem")) {
                    String item = dataTokens.get(1);
                    String receiptId = dataTokens.get(2);
                    int quantity = Integer.parseInt(dataTokens.get(3));
                    //System.out.println(item+receiptId+quantity);
                    boolean updateSale = new Controller().updatesoldItem(item, receiptId, quantity);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (updateSale == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("backupDB")) {
                    String directory = dataTokens.get(1);
                    boolean backedUp = new Controller().backUpDatabase(directory);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (backedUp == true) {
                            System.out.println("backed up database to " + directory);
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("deleteItemFromList")) {
                    String item = dataTokens.get(1);
                    String receiptId = dataTokens.get(2);
                    int quantity = Integer.parseInt(dataTokens.get(3));
                    //System.out.println(item+receiptId+quantity);
                    boolean removeSale = new Controller().deleteItemFromList(item, receiptId, quantity);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (removeSale == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }

                } else if (command.equalsIgnoreCase("deductStock")) {
                    String item = dataTokens.get(1);
                    int quantity = Integer.parseInt(dataTokens.get(2));
                    String deducted = new Controller().deductStock(new Sale(item, quantity));
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (deducted.equalsIgnoreCase("true")) {
                            out.println("true");
                            out.flush();
                        } else if (deducted.equalsIgnoreCase("false")) {
                            out.println("false");
                            out.flush();
                        } else if (deducted.equalsIgnoreCase("unavailable")) {
                            out.println("unavailable");
                            out.flush();

                        } else if (deducted.equalsIgnoreCase("unknownItem")) {
                            out.println("unknownItem");
                            out.flush();
                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("reAddStock")) {
                    String itemId = dataTokens.get(1);
                    int quantity = Integer.parseInt(dataTokens.get(2));
                    boolean reAdded = new Controller().reAddStock(itemId, quantity);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (reAdded == true) {
                            out.println("true");
                            out.flush();
                        } else if (reAdded == false) {
                            out.println("false");
                            out.flush();
                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("removeItemFromSales")) {
                    String itemId = dataTokens.get(1);
                    String receiptId = dataTokens.get(2);
                    boolean removed = new Controller().removeItemFromSales(itemId, receiptId);
                    try {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));

                        if (removed == true) {
                            out.println("true");
                            out.flush();
                        } else if (removed == false) {
                            out.println("false");
                            out.flush();
                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("generateId")) {
                    try {
                        String id = new Controller().generateId();
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (id != null) {
                            out.println(id);
                            out.flush();
                        } else {
                            out.println();
                            out.flush();
                        }
                        out.close();



                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

                    }



                } else if (command.equalsIgnoreCase("deleteCashier")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteCashier(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteSupplier")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteSupplier(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteSupplierContact")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteSupplierContact(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteSupplierProduct")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteSupplierProduct(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteMainCategory")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteMainCategory(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteSubCategory")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteSubCategory(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteItem")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteItem(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteStockItem")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteStockItem(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteUOM")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteUOM(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("deleteExpenditure")) {
                    String id = dataTokens.get(1);
                    try {
                        boolean deleted = new Controller().deleteExpenditure(id);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (deleted == true) {
                            out.println("true");
                            out.flush();
                        } else {
                            out.println("false");
                            out.flush();

                        }
                        out.close();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("updateMainCategory")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String status = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean updated = new Controller().updateMainCategory(id, name, status, date);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();

                        } else {
                            out.println("false");
                            out.flush();
                            out.close();

                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateSubCategory")) {
                    String id = dataTokens.get(1);
                    String mainCatId = dataTokens.get(2);
                    String name = dataTokens.get(3);
                    String status = dataTokens.get(4);
                    String date = dataTokens.get(5);
                    try {
                        boolean updated = new Controller().updateSubCategory(new SubCategory(id, mainCatId, name, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("updateItem")) {
                    String id = dataTokens.get(1);
                    String subCategoryId = dataTokens.get(2);
                    String name = dataTokens.get(3);
                    int price = Integer.parseInt(dataTokens.get(4));
                    String status = dataTokens.get(5);
                    String date = dataTokens.get(6);
                    try {
                        boolean add = new Controller().updateItem(new Item(id, subCategoryId, name, price, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (add == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (command.equalsIgnoreCase("updateCashier")) {
                    String id = dataTokens.get(1);
                    String fname = dataTokens.get(2);
                    String lname = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean updated = new Controller().updateCashier(new Cashier(id, fname, lname, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateSupplier")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String date = dataTokens.get(3);
                    try {
                        boolean updated = new Controller().updateSupplier(new Supplier(id, name, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateSupplierContact")) {
                    String id = dataTokens.get(1);
                    String supplier = dataTokens.get(2);
                    String contact = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean updated = new Controller().updateSupplierContact(new SupplierContact(id, supplier, contact, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateSupplierProduct")) {
                    String id = dataTokens.get(1);
                    String supplier = dataTokens.get(2);
                    String product = dataTokens.get(3);
                    String price = dataTokens.get(4);
                    String uom = dataTokens.get(5);
                    String date = dataTokens.get(6);
                    try {
                        boolean updated = new Controller().updateSupplierProduct(new SupplierProduct(id, supplier, product, price, uom, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateUOM")) {
                    String id = dataTokens.get(1);
                    String name = dataTokens.get(2);
                    String status = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean updated = new Controller().updateUOM(new UOM(id, name, status, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateExpenditure")) {
                    String id = dataTokens.get(1);
                    int amount = Integer.parseInt(dataTokens.get(2));
                    String reason = dataTokens.get(3);
                    String date = dataTokens.get(4);
                    try {
                        boolean updated = new Controller().updateExpenditure(new CashierExpendure(id, amount, reason, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("updateStock")) {
                    String item = dataTokens.get(1);
                    int quantity = Integer.parseInt(dataTokens.get(2));
                    String uom = dataTokens.get(3);
                    int costPrice = Integer.parseInt(dataTokens.get(4));
                    String date = dataTokens.get(5);
                    try {
                        boolean updated = new Controller().updateStock(new Stock(item, quantity, uom, costPrice, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (updated == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("findIfExists")) {
                    String itemId = dataTokens.get(1);
                    try {
                        boolean exists = new Controller().findIfExists(itemId);
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (exists == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (command.equalsIgnoreCase("recordReceipt")) {
                    //System.out.println();
                    String receiptId = dataTokens.get(1);
                    int total = Integer.parseInt(dataTokens.get(2));
                    int discount = Integer.parseInt(dataTokens.get(3));
                    int cash = Integer.parseInt(dataTokens.get(4));
                    int balanceDue = Integer.parseInt(dataTokens.get(5));
                    int change = Integer.parseInt(dataTokens.get(6));
                    String date = dataTokens.get(7) + " " + Controller.getCurrentTimeStamp();
                    try {
                        boolean record = new Controller().recordReceipt(new Receipt(receiptId, total, discount, cash, balanceDue, change, date));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
                        if (record == true) {
                            out.println("true");
                            out.flush();
                            out.close();
                        } else {
                            out.println("false");
                            out.flush();
                            out.close();
                        }


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                }

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new VPSPOSSEVER();

    }

    private static ArrayList<String> tokens(String message) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(message, "^");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;

    }
}
