package cs485.assignment3.model.dao;

import cs485.assignment3.model.entity.AbstractEntity;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity> {
    //By using Generics, we can make sure each DAO is bound to a specific Entity

    private String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    private String Port = "3306"; //default MySQL port
    private String Database = "groupassignment3"; // database/schema name
    private String Username = "root"; //read this from a local file
    private String Password = "password"; //Also read this from a file

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    public void setTestDatabase(){
        this.Database = "vet_clinic_test";
    }

    // Abstract Methods for each CRUD operation
    public abstract void create(E entity) throws SQLException;
    public abstract E read(int ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(int ID) throws SQLException;
    public abstract List<E> list() throws SQLException;
}
