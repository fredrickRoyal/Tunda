/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

/**
 *
 * @author Royal
 */
public class Summary {
    private String cashierId;
    private String signIn;
    private String signOut;
    private String date;
    private int totalCollection;
    private int totalExpenditure;
    private int cashAtHand;
    public Summary(String cashierId, String signIn,String signOut,String date,int totalCollection, int totalExpenditure,int cashAtHand){
        this.cashierId=cashierId;
        this.signIn=signIn;
        this.signOut=signOut;
        this.date=date;
        this.totalCollection=totalCollection;
        this.totalExpenditure=totalExpenditure;
        this.cashAtHand=cashAtHand;
    }
    public String getCashierId(){
    return cashierId;
    }
    public String getSignIn(){
    return signIn;
    }
    public String getSignOut(){
    return signOut;
    }
    public String getDate(){
    return date;
    }
    public int getTotalCollection(){
    return totalCollection;
    }
    public int getTotalExpenditure(){
        return totalExpenditure;
    }
    public int getCashAtHand(){
    return cashAtHand;
    }
    
    
    
}
