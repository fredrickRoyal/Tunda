/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author Royal
 */
public class OpeningStock {
    private String item;
    private int quantity;
    private String date;
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
