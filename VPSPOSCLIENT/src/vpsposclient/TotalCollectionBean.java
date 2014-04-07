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
public class TotalCollectionBean implements Serializable {

    private String item = "";
    private String quantity = "";
    private String amount = "";
    private String id = "";
    private String date = "";
    private String total = "";
    private String profit = "";
    private String totalProfit = "";

    public TotalCollectionBean() {
    }

    public TotalCollectionBean(String id, String item, String quantity, String amount, String total, String date, String profit, String totalProfit) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.total = total;
        this.date = date;
        this.profit = profit;
        this.totalProfit = totalProfit;
    }
/*
    public TotalCollectionBean(String id, String item, String quantity, String amount, String total, String date) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.total = total;
        this.date = date;
    }*/
    public TotalCollectionBean(String id, String item, String quantity, String amount, String profit, String totalProfit) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.profit = profit;
        this.totalProfit = totalProfit;
    }

    public TotalCollectionBean(String id, String item, String quantity, String amount) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProfit() {
        return profit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public String getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
