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
public class TotalCollectionReportFactory {
    static java.util.Vector collection;
    
    static ArrayList<TotalCollectionBean> itemList = new ArrayList<TotalCollectionBean>();
    static String total;
    static String date;
    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();
        
        for (int i = 0; i < itemList.size(); i++) {
            String id=itemList.get(i).getId();
            String item=itemList.get(i).getItem();
            String quantity=itemList.get(i).getQuantity();
            String amount=itemList.get(i).getAmount();
            String profit=itemList.get(i).getProfit();
            String totalProfit=itemList.get(i).getTotalProfit();
            collection.add(new TotalCollectionBean(id,item, quantity, amount,total,date,profit,totalProfit));
        }

        return collection;
    }
    
}
