/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author royal
 */
public class Item {

    private String id;
    private String subCategoryId;
    private String name;
    private int price;
    private String status;
    private String date;// date of creation
    private String quantity;
    private String amount;
    private int totalCostPrice;

    public Item() {
    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(String id, String name, String quantity, String amount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Item(String id, String name, String quantity, String amount, int totalCostPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.totalCostPrice = totalCostPrice;
    }

    public Item(String id, String subCategoryId, String name, int price, String status, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.date = date;
        this.subCategoryId = subCategoryId;
    }

    public Item(String name, String status, String date) {
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;

    }
    //getMethods

    public int getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(int totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {

        return amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getSubCategoryId() {
        return subCategoryId;

    }
    //setter methods

    public void setQuantity(String quantity) {
        this.quantity = quantity;

    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(int price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;

    }

    public void setDate(String date) {
        this.date = date;

    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
