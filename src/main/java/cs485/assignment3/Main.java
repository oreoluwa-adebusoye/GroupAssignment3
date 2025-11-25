package cs485.assignment3;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String ConUrl = "jdbc:mysql://localhost"; //protocol + url
        String Port = "3306"; //default MySQL port
        String Database = "groupassignment3"; // database/schema name
        String Username = "root"; //read this from a local file
        String Password = "password"; //Also read this from a file

        try{
            String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;
            Connection con = DriverManager.getConnection(url);


            con.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }

    }
}
