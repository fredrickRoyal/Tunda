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
public class ClosingStockFactory {
    static java.util.Vector collection;
    static ArrayList<ClosingStock> itemList = new ArrayList<ClosingStock>();
    static String date;

    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();
        for (int i = 0; i < itemList.size(); i++) {
            String item=itemList.get(i).getItem();
            int quantity=itemList.get(i).getQuantity();
            int amount=itemList.get(i).getAmount();
            int openingStock=itemList.get(i).getOpeningStock();
            int closingStock=itemList.get(i).getClosingStock();
            collection.add(new ClosingStock(item, quantity, amount, openingStock, closingStock,date));
        }
        return collection;
    }
    
}
