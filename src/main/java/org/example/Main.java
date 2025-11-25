package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
            String url = "jdbc:mysql://localhost:3306/test_db?user=root&password=";
            Connection con = DriverManager.getConnection(url);
        }catch(SQLException ex){
            System.out.println("Error in getting a DB Connection");
        }

    }
    }

