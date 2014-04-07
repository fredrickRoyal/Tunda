/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author royal
 */
public class SubCategory {
    private String id;
    private String mainCatId;
    private String name;
    private String status;
    private String date;// date of creation

    public SubCategory() {    
    }
    public SubCategory (String id,String mainCatId, String name,String status, String date){
        this.id=id;
        this.name=name;
        this.status=status;
        this.date=date;
        this.mainCatId=mainCatId;
    }
    public SubCategory (String name,String status, String date){
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
    public String getMainCatId(){
    return mainCatId;
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
    public void setMainCatId(String mainCatId){
        this.mainCatId=mainCatId;
    }
    
    
}
