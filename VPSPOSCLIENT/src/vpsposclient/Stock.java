/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author royal
 */
public class Stock {

    private String item;
    private int quantity;
    private String UOM;
    private int unitCostPrice;
    private int sellingPrice;
    private String date;
    private String itemId;
    public Stock(String itemId,String item, int quantity, String UOM, int unitCostPrice, int sellingPrice, String date) {
        this.item = item;
        this.quantity = quantity;
        this.UOM = UOM;
        this.unitCostPrice = unitCostPrice;
        this.sellingPrice = sellingPrice;
        this.date = date;
        this.itemId=itemId;

    }

    public Stock(String item, int quantity, String UOM, int unitCostPrice, int sellingPrice, String date) {
        this.item = item;
        this.quantity = quantity;
        this.UOM = UOM;
        this.unitCostPrice = unitCostPrice;
        this.sellingPrice = sellingPrice;
        this.date = date;

    }

    public Stock(String item, int quantity, String UOM, int unitCostPrice, String date) {
        this.item = item;
        this.quantity = quantity;
        this.UOM = UOM;
        this.unitCostPrice = unitCostPrice;
        this.date = date;

    }
    public String getItemId(){
    
    return itemId;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUOM() {
        return UOM;
    }

    public int getUnitCostPrice() {
        return unitCostPrice;
    }

    public String getDate() {
        return date;
    }

    public int getSellingPrice() {

        return sellingPrice;
    }
    public void setItemId(String itemId){
    this.itemId=itemId;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public void setUnitCostPrice(int unitCostPrice) {
        this.unitCostPrice = unitCostPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
