/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author Royal
 */
public class OpeningStock {
    String item;
    int quantity;
    String date;
    public OpeningStock(String item,int quantity,String date){
    this.item=item;
    this.quantity=quantity;
    this.date=date;
    }
    public String getItem(){
        return item;
    }
    public int getQuantity(){
    return quantity;
    }
    public String getDate(){
    return date;
    }
    
}
