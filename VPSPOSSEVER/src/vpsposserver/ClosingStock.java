/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author Royal
 */
public class ClosingStock {
    String item;
    int quantity;
    String date;
    int amount;
    int openingStock;
    int closingStock;
    public ClosingStock(){
    }
    public ClosingStock(String item,int quantity,int amount,int openingStock,int closingStock){
        this.item=item;
        this.quantity=quantity;
        this.amount=amount;
        this.openingStock=openingStock;
        this.closingStock=closingStock;
    }
    public String getItem(){
    return item;
    }
    public String getDate(){
    return date;
    }
    public int getQuantity(){
    return quantity;
    }
    public int getAmount(){
    return amount;
    }
    public int getOpeningStock(){
    return openingStock;
    }
    public int getClosingStock(){
    return closingStock;
    }
    
}
