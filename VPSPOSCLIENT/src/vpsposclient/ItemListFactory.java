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
public class ItemListFactory {
    static java.util.Vector collection;
    
    static ArrayList<ItemListBean> itemList = new ArrayList<ItemListBean>();
    static String date;

    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();
        for (int i = 0; i < itemList.size(); i++) {
            String item=itemList.get(i).getItem();
            String price=itemList.get(i).getPrice();
            collection.add(new ItemListBean(item,price));
        }
        return collection;
    }
    
}
