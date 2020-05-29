/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Abu Da Best
 */
public class dbConnection {
 
    private static Connection con;
     public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOP", "root", "");
          //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOP", "root", "12345");
        return con;
        
        
    }
   

}


