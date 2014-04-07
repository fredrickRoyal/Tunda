/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

import java.util.ArrayList;

/**
 *
 * @author Royal
 */
public class ReceiptReportFactory {

    static java.util.Vector collection;
    
    static ArrayList<ReceiptBean> itemList = new ArrayList<ReceiptBean>();

    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();
        for (int i = 0; i < itemList.size(); i++) {
            String itemName=itemList.get(i).getItemName();
            String price=itemList.get(i).getPrice();
            String total=itemList.get(i).getTotal();
            String cash=itemList.get(i).getCash();
            String change=itemList.get(i).getChange();
            String receiptCode=itemList.get(i).getReceiptCode();
            String date=itemList.get(i).getDate();
            String quantity=itemList.get(i).getQuantity();
            String totalPrice=itemList.get(i).getTotalPrice();
            String cashier=itemList.get(i).getCashier();
            collection.add(new ReceiptBean(receiptCode, date, itemName, quantity, price, totalPrice, total, cash, change,cashier));
        }

        return collection;
    }
}
