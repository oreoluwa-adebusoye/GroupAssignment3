package cs485.assignment3;

import cs485.assignment3.view.MainFrame;

import java.sql.*;

public class Main {
    static String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    static String Port = "3306"; //default MySQL port
    static String Database = "groupassignment3"; // database/schema name
    static String Username = "root"; //read this from a local file
    static String Password = "password"; //Also read this from a file

    static String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

    }
}
