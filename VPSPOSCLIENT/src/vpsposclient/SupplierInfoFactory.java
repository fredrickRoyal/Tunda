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
public class SupplierInfoFactory {

    static java.util.Vector collection;
    static ArrayList<SupplierProduct> productList = new ArrayList<SupplierProduct>();

    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();
        for (int i = 0; i < productList.size(); i++) {
            String id = productList.get(i).getId();
            String supplierName = productList.get(i).getSupplier();
            String product = productList.get(i).getProduct();
            String price = productList.get(i).getPrice();
            String uom = productList.get(i).getUOM();
            String date = productList.get(i).getDate();
            collection.add(new SupplierProduct(id, supplierName, product, price, uom, date));
        }

        return collection;
    }
}
