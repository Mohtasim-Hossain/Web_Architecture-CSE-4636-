package com.example.assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection getConnection(){
        try {
            String driver="com.mysql.cj.jdbc.Driver";
            String databaseUrl="jdbc:mysql://localhost:3306/web_arch";
            String user="root";
            String password="dominno4848";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseUrl,user,password);
            System.out.println("Database connected");

            return  conn;
        }catch (Exception e){
            System.out.println(e);
        }
        return  null;
    }
}
