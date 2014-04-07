/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author royal
 */
public class Sale {
    private String item;
    private String receiptId;
    private int quantity;
    private int discount;
    private int price;
    private String cashier;
    private String date;
    public Sale(String item, int quantity, int price){
        this.item=item;
        this.price=price;
        this.quantity=quantity;
        
    }
    public Sale(){
    }
    
    public Sale(String item,String receiptId, int quantity,int price,String cashier,String date){
        this.item=item;
        this.receiptId=receiptId;
        this.quantity=quantity;
        this.price=price;
        this.cashier=cashier;
        this.date=date;
        
    }
    public Sale(String item,String receiptId, int quantity,String cashier,String date){
        this.item=item;
        this.receiptId=receiptId;
        this.quantity=quantity;
        this.cashier=cashier;
        this.date=date;
        
    }
    public String getItem(){
    return item;
    }
    public String getReceiptId(){
    return receiptId;
    
    }
    public int getQuantity(){
    return quantity;
    }
    public int getDiscount(){
    return discount;
    }
    public int getPrice(){
    return price;
    }
    public String getCashier(){
    return cashier;
    }
    public String getDate(){
    return date;
    }
    
    public void setPrice(int price){
    this.price= price;
    }
    
    public void setCashier(String cashier){
    this.cashier= cashier;
    }
    public void setDate(String date){
    this.date=date;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    
    
}
