package distributedsupershop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Server {
    PrintStream printStrm; Scanner scanner;
     ObjectInputStream inStream = null;   
ObjectOutputStream outputStream = null;
    public Server() {
        try {
            System.out.println("Server");
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            scanner = new Scanner(s.getInputStream());
            printStrm = new PrintStream(s.getOutputStream());
             outputStream = new ObjectOutputStream(s.getOutputStream());
            inStream = new ObjectInputStream(s.getInputStream());
            int choice= scanner.nextInt();
            
            switch(choice){
                case 1:
                    printStrm.println("Whatever"); s.close();ss.close(); new Server();
                    break;
                    
                case 2: memberCheck(); s.close();ss.close(); new Server();
                    break;
                case 3: findProducts();s.close();ss.close(); new Server();
                    break;
                     case 4:noOfrow();s.close();ss.close(); new Server();
                    break;
                case 5:productList();outputStream.flush();
                         outputStream.close();s.close();ss.close(); new Server();
                case 6:findCustomer();s.close();ss.close(); new Server();
                    break;
            
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void memberCheck(){
        try {
            int customer_id =scanner.nextInt();
            DataBaseConnection db = new DataBaseConnection();
            db.rs = db.stmt.executeQuery
                ("select Membership_start,Membership_end,Membership_points from Customer where Customer_id ="+customer_id+" and sysDate between Membership_start and Membership_end");
            while(db.rs.next()){
                printStrm.println(true);
                double price = scanner.nextDouble(); System.out.println("Server : "+price);
                int payOrupdate=scanner.nextInt();System.out.println("Server : "+ payOrupdate);
                double Membership_points = db.rs.getDouble("Membership_points");
                if(payOrupdate==1){
                    if(price >= Membership_points){
                        price = price - Membership_points;
                        Membership_points=0;
                    }
                    else{
                        Membership_points = Membership_points - price;
                        price = 0;
                    }
                }
                else{
                    Membership_points = PriceCalculator.round(Membership_points+ (price*0.03),2);
                }
                db.rs = db.stmt.executeQuery
                        ("update Customer set Membership_points = "+Membership_points+" where Customer_id ="+customer_id);
                System.out.println("Server : "+ price);
                printStrm.println(price);
                return;
            }
            printStrm.println(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    private void findProducts(){
        try{
            String name,unit;
            float amount;
            DataBaseConnection db = new DataBaseConnection();
            int product_id=scanner.nextInt();
            db.pstmt= db.con.prepareStatement("select Product_name,Product_unit,Product_amount from Products where Product_id=?");
            db.pstmt.setInt(1, product_id);
            db.rs = db.pstmt.executeQuery();
            while (db.rs.next()){
                name=db.rs.getString("Product_name");
                unit=db.rs.getString("Product_unit");
                amount=db.rs.getFloat("Product_amount");
                printStrm.println(name);
                printStrm.println(unit);
                printStrm.println(amount);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
       public void noOfrow(){
         ArrayList<Integer> idList=new ArrayList<>();
       
        String sql="select Product_id from products";
        try{
             DataBaseConnection db = new DataBaseConnection();
        
        
       
         db.pstmt= db.con.prepareStatement(sql);
        db.rs=db.pstmt.executeQuery();
        while(db.rs.next()){
            int id=db.rs.getInt("Product_id");
            idList.add(id);
            
        }
        outputStream.writeObject(idList);
        
            
        }catch(Exception e){
            
        }
    }
    public void productList()throws IOException{
        ArrayList<String> pro=new ArrayList<>();
        
        System.out.println("productList connected");
       
        int product_id= scanner.nextInt();
        System.out.println(product_id);
        String name,type,unit,id,amount,price;
     /*    String name,type,unit;
       int id;
        float amount,price;*/
        
        String sql="select Product_id,Product_name,Product_type,Price,Product_amount,Product_unit from products where product_id=?";
         try{
        DataBaseConnection db = new DataBaseConnection();
       
        
           
       db.pstmt= db.con.prepareStatement(sql);
      db.pstmt.setInt(1,product_id);
       db.rs=db.pstmt.executeQuery();
       while (db.rs.next()){
           
           id=String.valueOf(db.rs.getInt("Product_id"));
           name=db.rs.getString("Product_name");
           type=db.rs.getString("Product_type");
           price=String.valueOf(db.rs.getFloat("Price"));
           
           amount=String.valueOf(db.rs.getFloat("Product_amount"));
           unit=db.rs.getString("Product_unit");
          
          pro.add(id);
           pro.add(name);
           pro.add(type);
           pro.add(price);
           pro.add(amount);
           pro.add(unit);
       
           
           outputStream.writeObject(pro);
           
       }
       }catch(Exception e){
            
            e.printStackTrace();
        }
    }
      private void findCustomer(){
        try{
            String name,address,mem_start,mem_end;
            //Date mem_start = new Date();
        //Date mem_end = new Date();
        
            float point;
            DataBaseConnection db = new DataBaseConnection();
            int customer_id=scanner.nextInt();
            db.pstmt= db.con.prepareStatement("select customer_name,customer_address,membership_start,membership_end,membership_point from customer where customer_id=?");
            db.pstmt.setInt(1, customer_id);
            db.rs = db.pstmt.executeQuery();
            while (db.rs.next()){
                name=db.rs.getString("customer_name");
                address=db.rs.getString("customer_address");
                mem_start=""+db.rs.getDate("membership_start");
                mem_end=""+db.rs.getDate("membership_end");
                point=db.rs.getFloat("membership_point");
                printStrm.println(name);
                printStrm.println(address);
                printStrm.println(mem_start);
                printStrm.println(mem_end);
                printStrm.println(point);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

        
        
    }
   

