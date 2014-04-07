/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

import java.io.Serializable;

/**
 *
 * @author Royal
 */
public class ItemListBean implements Serializable{
    private String item;
    private String price;
    public ItemListBean(String item,String price){
        this.item=item;
        this.price=price;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
}
