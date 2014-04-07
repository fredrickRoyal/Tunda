/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author royal
 */
public class UOM {

    private String id;
    private String name;
    private String status;
    private String date;
    public UOM(){
    }

    public UOM(String id, String name, String status, String date) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
    
    public void setId(String id) {
        this.id= id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setStatus(String status) {
        this.status= status;
    }

    public void setDate(String date) {
        this.date=date;
    }
}
