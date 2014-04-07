/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Royal
 */
public class DerbyDataBase {

    public DerbyDataBase() {
    }

    public boolean initialise() {

        boolean initialised = false;
        if (createTables() == true) {
            initialised = true;
        }

        return initialised;

    }

    public boolean createTables() {
        String dBaseCreated = createDB();
        createTableSupplier();
        createTableSupplierContact();
        createTableSupplierProduct();
        if (dBaseCreated.equalsIgnoreCase("true")) {
            if (createTabeMainCategory() && createTableSubCategory() && createTableItem() && createTableIdStore()
                    && createTableUOM() && createTableStock() && createTableSale() && createTableReceipt() && createTableIdAdmin()
                    && createTableCashier() && createTableSummary() && createOpeningStockTable() && createCashierExpeditureTable()
                    && createTableSupplier() && createTableSupplierContact()&&createTableSupplierProduct()) {
                return true;
            } else {
                return false;

            }

        } else if (dBaseCreated.equalsIgnoreCase("false")) {
            return false;
        } else {
            return false;
        }

    }

    public String createDB() {
        Connection conn = null;
        String created = new String("false");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String strUrl = "jdbc:derby:VPS;create=true;user=vps;password=vps";
            conn = DriverManager.getConnection(strUrl);
            if (conn != null) {
                created = new String("true");
            }

        } catch (Exception e) {
            created = new String("already Exists");
        }
        return created;
    }
    /*End of connecting to the databases*/

    public Connection connectToDB() {
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String strUrl = "jdbc:derby:VPS;user=vps;password=vps";
            conn = DriverManager.getConnection(strUrl);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean createTabeMainCategory() {
        Connection connect;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table MainCategory(MAINID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table MainCategory created by kasoma");
            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSubCategory() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table  SubCategory(SUBID varchar(25) not null,MAINID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table SubCategory created");
            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableItem() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table Item(ITEMID varchar(25) not null,SUBID varchar(25) not null,NAME varchar(80) not null,PRICE int not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Item created");

            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableUOM() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table UOM(ID varchar(25) not null,NAME varchar(80) not null,STATUS varchar(10) not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table UOM created");
            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableStock() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table Stock(ITEMID varchar(25) not null,QUANTITY int not null,UOMID varchar(10) not null,COSTPRICE int not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Stock created");
            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableSale() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table Sale(ITEMID varchar(25) not null,RECEIPTID varchar(25) not null,QUANTITY int not null,PRICE int not null,USAR varchar(25) not null,DATE varchar(25) not null)");
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
            int createResult = createTable.executeUpdate("create table Receipt(ID varchar(25) not null,TOTAL int not null,DISCOUNT int not null,BALANCEDUE int not null,CASH int not null,CHANG int not null,DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Receipt created");
            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableIdStore() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table IdStore(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY)");
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
            int createResult = createTable.executeUpdate("create table Cashier(ID varchar(25) not null,FIRSTNAME varchar(25) not null,LASTNAME varchar(25) not null, DATE varchar(25) not null)");
            if (createResult == 0) {
                created = true;
                System.out.println("Table Cashier created");

            }

        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
        return created;
    }

    public boolean createTableIdAdmin() {
        Connection connect = null;
        boolean created = false;
        try {
            connect = connectToDB();
            Statement createTable = connect.createStatement();
            int createResult = createTable.executeUpdate("create table  Admin(PASSWORD varchar(25) not null, USERNAME varchar(25) not null)");
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
            int createResult = createTable.executeUpdate("create table CashierExpenditure(ID varchar(25) not null,CASHIER varchar(25) not null,AMOUNT int not null,REASON varchar(25) not null, DATE varchar(25) not null)");
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
            int createResult = createTable.executeUpdate("create table Summary(CASHIER varchar(25) not null,SIGNIN varchar(25) not null,SIGNOUT varchar(25) not null,DATE varchar(25) not null, TOTALCOLLECTION int not null,TOTALEXPENDITURE int not null,CASHATHAND int not null)");
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
                System.out.println("Table Summary Opening Stock created");
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
            int result = createTable.executeUpdate("create table SupplierProduct(Id varchar(50) not null,Supplier varchar(100) not null,Product varchar(100) not null,Price varchar(100) not null,UOM varchar(100) not null, Date varchar(25) not null)");
            if (result == 0) {
                created = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return created;
    }
}
