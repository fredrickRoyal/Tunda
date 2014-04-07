/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author Royal
 */
public class Cashier {
    private String id;
    private String firstName;
    private String lastName;
    private String date;
    public Cashier(){
    }
    public Cashier(String id,String firstName,String lastName, String date){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.date=date;
    }
    public String getId(){
    return id;
    }
    public String getFirstName(){
    return firstName;
    }
    public String getLastName(){
    return lastName;
    }
     public String getDate(){
    return date;
    }
    
    public void setId(String id){
    this.id= id;
    }
    public void setFirstName(String firstName){
    this.firstName= firstName;
    }
    public void setLastName(String lastName){
    this.lastName= lastName;
    }
    public void setDate(String date){
    this.date= date;
    }
    
}
