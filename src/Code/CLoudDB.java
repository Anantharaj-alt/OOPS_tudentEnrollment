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
 * @author HP
 */
public class CLoudDB {
    
    private static Connection con;
     public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12313236", "sql12313236", "BZazm6XRax");
        return con;
     }
}
