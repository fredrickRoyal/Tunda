/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author Royal
 */
public class SupplierContact {

    private String id;
    private String supplier;
    private String contact;
    private String date;

    public SupplierContact(String id, String supplier, String contact, String date) {
        this.id = id;
        this.supplier = supplier;
        this.contact = contact;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getContact() {
        return contact;
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

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
