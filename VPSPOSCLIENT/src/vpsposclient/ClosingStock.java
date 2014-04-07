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
public class ClosingStock implements Serializable{

    private String item;
    private int quantity;
    private String date;
    private int amount;
    private int openingStock;
    private int closingStock;

    public ClosingStock() {
    }

    public ClosingStock(String item, int quantity, int amount, int openingStock, int closingStock) {
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.openingStock = openingStock;
        this.closingStock = closingStock;
    }
    public ClosingStock(String item, int quantity, int amount, int openingStock, int closingStock,String date) {
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.openingStock = openingStock;
        this.closingStock = closingStock;
        this.date=date;
    }

    public String getItem() {
        return item;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmount() {
        return amount;
    }

    public int getOpeningStock() {
        return openingStock;
    }

    public int getClosingStock() {
        return closingStock;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setOpeningStock(int openingStock) {
        this.openingStock = openingStock;
    }

    public void setClosingStock(int closingStock) {
        this.closingStock = closingStock;
    }
}
