/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author Royal
 */
public class Receipt {

    private String id;
    private int total;
    private int discount;
    private int cash;
    private int balanceDue;
    private int change;
    private String date;

    public Receipt(String id, int total, int discount, int cash, int balanceDue, int change, String date) {
        this.id = id;
        this.total = total;
        this.discount = discount;
        this.cash = cash;
        this.balanceDue = balanceDue;
        this.change = change;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public int getDiscount() {
        return discount;
    }

    public int getCash() {
        return cash;
    }

    public int getBalanceDue() {
        return balanceDue;
    }

    public int getChange() {
        return change;
    }

    public String getDate() {
        return date;
    }
    
     public void setId(String id) {
        this.id=id;
    }

    public void setTotal(int total) {
        this.total=total;
    }

    public void setDiscount(int discount) {
        this.discount=discount;
    }

    public void setCash(int cash) {
        this.cash= cash;
    }

    public void setBalanceDue(int balanceDue) {
        this.balanceDue= balanceDue;
    }

    public void setChange(int change) {
        this.change= change;
    }

    public void setDate(String date) {
        this.date= date;
    }
}
