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
public class ReceiptBean implements Serializable {

    private String itemName = "";
    private String price = "";
    private String total = "";
    private String cash = "";
    private String change = "";
    private String receiptCode = "";
    private String date = "";
    private String quantity = "";
    private String totalPrice = "";
    private String cashier = "";

    public ReceiptBean() {
    }

    public ReceiptBean(String receiptCode, String date, String itemName, String quantity, String price, String totalPrice, String total, String cash, String change, String cashier) {
        this.receiptCode = receiptCode;
        this.date = date;
        this.itemName = itemName;
        this.price = price;
        this.total = total;
        this.cash = cash;
        this.change = change;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cashier = cashier;

    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public String getCashier() {
        return cashier;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
}
