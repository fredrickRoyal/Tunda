/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposserver;

/**
 *
 * @author royal
 */
public class MainCategory {

    private String id;
    private String name;
    private String status;
    private String date;// date of creation

    public MainCategory() {    
    }
    public MainCategory (String id, String name,String status, String date){
        this.id=id;
        this.name=name;
        this.status=status;
        this.date=date;
    }
    public MainCategory (String name,String status, String date){
        this.name=name;
        this.status=status;
        this.date=date;
    }
    //getMethods
    public String getId(){
    return id;
    }
    public String getName(){
    return name;
    }
    public String getStatus(){
    return status;
    }
    public String getDate(){
    return date;
    }
    //setter methods
   public void setId( String id){
       this.id=id;
    
    }
    public void setName(String name){
    this.name=name;
    }
    public void setStatus(String status){
        this.status=status;
    
    }
    public void setDate(String date){
        this.date=date;
   
    } 
    
}
