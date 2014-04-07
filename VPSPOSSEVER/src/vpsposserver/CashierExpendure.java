/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author Royal
 */
public class CashierExpendure {

    private String expenditureId;
    private String cashierId;
    private int amount;
    private String reason;
    private String date;

    public CashierExpendure(String cashierId, int amount, String reason, String date) {
        this.cashierId = cashierId;
        this.amount = amount;
        this.reason = reason;
        this.date = date;

    }
     public CashierExpendure( int amount, String reason, String date) {
        this.amount = amount;
        this.reason = reason;
        this.date = date;

    }

    public CashierExpendure(String expenditureId, String cashierId, int amount, String reason, String date) {
        this.expenditureId = expenditureId;
        this.cashierId = cashierId;
        this.amount = amount;
        this.reason = reason;
        this.date = date;

    }

    public String getExpenditureId() {
        return expenditureId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public int getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public void setExpenditureId(String expenditureId) {
        this.expenditureId = expenditureId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public void setAmount(int amount) {
        this.amount = amount;

    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
