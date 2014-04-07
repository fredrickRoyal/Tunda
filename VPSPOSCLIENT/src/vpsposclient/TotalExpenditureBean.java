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
public class TotalExpenditureBean implements Serializable {

    private String amount = "";
    private String reason = "";
    private String date = "";
    private String total="";
    private String dateLimit="";
    private String title="";
    private String cashier="";

    public TotalExpenditureBean() {
    }

    public TotalExpenditureBean(String amount, String reason, String date) {
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }

    public TotalExpenditureBean(String amount, String reason, String date, String cashier, String total, String dateLimit, String title) {
        this.amount = amount;
        this.reason = reason;
        this.date = date;
        this.cashier=cashier;
        this.total=total;
        this.dateLimit=dateLimit;
        this.title=title;
    }

    public String getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public String getDateLimit() {
        return dateLimit;
    }

    public String getTitle() {
        return title;
    }

    public String getCashier() {
        return cashier;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
}
