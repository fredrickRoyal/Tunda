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
public class SupplierProduct implements Serializable{
    private String id;
    private String supplier;
    private String product;
    private String price;
    private String UOM;
    private String date;
    private String contact;

    public SupplierProduct(String id, String supplier, String product,String price,String UOM, String date) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.price=price;
        this.UOM=UOM;
        this.date = date;
    }
    public SupplierProduct(String id, String supplier, String product,String price,String UOM, String date,String contact) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.price=price;
        this.UOM=UOM;
        this.date = date;
        this.contact=contact;
    }
    

    public String getId() {
        return id;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getProduct() {
        return product;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setProduct(String contact) {
        this.product = contact;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the UOM
     */
    public String getUOM() {
        return UOM;
    }

    /**
     * @param UOM the UOM to set
     */
    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    
}
