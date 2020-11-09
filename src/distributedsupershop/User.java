/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedsupershop;


import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author DAFFODIL
 * 
 */

class User {
    private int receipt_id,customer_id,product_id;
    private String unit;
    private float amount;
    private Timestamp time;
    private int Employee_id,Salary;
    private String Employee_name,Employee_address,Employee_post;
    private Date Joining_date;
   
    public User(int receipt_id,int customer_id,int product_id,String unit,float amount,Timestamp time){
    this.receipt_id=receipt_id;
    this.customer_id=customer_id;
    this.product_id=product_id;
    this.unit=unit;
    this.amount=amount;
    this.time=time;
  
    
    
    
    }

    public User(int Employee_id,String Employee_name,String Employee_address,String Employee_post,int Salary,Date Joining_date){
        this.Employee_id=Employee_id;
        this.Employee_name=Employee_name;
        this.Employee_address=Employee_address;
        this.Employee_post=Employee_post;
        this.Salary=Salary;
        this.Joining_date=Joining_date;
        
    }
    User(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getreceipt_id(){
    return receipt_id;
    }
    public int getcustomer_id(){
    return customer_id;
    }
    public int getproduct_id(){
    return product_id;
    }
    public String get_unit(){
    return unit;
    }
    public float get_amount(){
    return amount;
    }
    public Timestamp get_time(){
    return time;
    }

    Object Admin_id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object Admin_name() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object Admin_address() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    
}
