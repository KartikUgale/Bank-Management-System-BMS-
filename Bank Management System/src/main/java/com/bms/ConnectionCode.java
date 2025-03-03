package main.java.com.bms;

import java.sql.*;

public class ConnectionCode {
    Connection conn;
    Statement st;


    // Execute Query
    // Close Connection
    public ConnectionCode() {
        try {
            //Class.forName(com.mySql.cj.jdbc.Driver);  // no need of this line java will pick it  // 1. Register The Driver
            conn = DriverManager.getConnection("jdbc:mysql:///bankusers", "root", "Kartik@SQL911");    // 2. Create Connection
            st = conn.createStatement();     // 3. Create Statement
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
