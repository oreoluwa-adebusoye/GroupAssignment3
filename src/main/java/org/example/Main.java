package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
            String url = "";
            Connection con = DriverManager.getConnection(url);
        }catch(SQLException ex){
            System.out.println("Error in getting a DB Connection");
        }

    }
    }
}
