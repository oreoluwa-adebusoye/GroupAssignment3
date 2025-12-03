package cs485.assignment3.model.dao;

import cs485.assignment3.model.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO extends AbstractDAO<Service> {

    @Override
    public void create(Service entity) throws SQLException {
        Connection con = getConnection();

        String sql = "INSERT INTO service " +
                "(service_name, service_type, description, base_price, estimated_duration) " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, entity.getName());
        pst.setString(2, entity.getType());
        pst.setString(3, entity.getDescription());
        pst.setDouble(4, entity.getBasePrice());
        pst.setInt(5, entity.getEstimatedDuration());

        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            entity.setID(rs.getInt(1));
        }

        con.close();
    }

    @Override
    public Service read(int ID) throws SQLException {
        Service s = new Service();
        Connection con = getConnection();

        String sql = "SELECT * FROM service WHERE service_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            s.setID(rs.getInt("service_id"));
            s.setName(rs.getString("service_name"));
            s.setType(rs.getString("service_type"));
            s.setDescription(rs.getString("description"));
            s.setBasePrice(rs.getDouble("base_price"));
            s.setEstimatedDuration(rs.getInt("estimated_duration"));
        }

        con.close();
        return s;
    }

    @Override
    public void update(Service entity) throws SQLException {
        Connection con = getConnection();

        String sql = "UPDATE service " +
                "SET service_name = ?, service_type = ?, description = ?, " +
                "base_price = ?, estimated_duration = ? " +
                "WHERE service_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, entity.getName());
        pst.setString(2, entity.getType());
        pst.setString(3, entity.getDescription());
        pst.setDouble(4, entity.getBasePrice());
        pst.setInt(5, entity.getEstimatedDuration());
        pst.setInt(6, entity.getID());

        pst.executeUpdate();
        con.close();
    }

    @Override
    public void delete(int ID) throws SQLException {
        Connection con = getConnection();

        String sql = "DELETE FROM service WHERE service_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ID);
        pst.executeUpdate();

        con.close();
    }

    @Override
    public List<Service> list() throws SQLException {
        ArrayList<Service> list = new ArrayList<>();

        Connection con = getConnection();
        String sql = "SELECT * FROM service ORDER BY service_name";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Service s = new Service();
            s.setID(rs.getInt("service_id"));
            s.setName(rs.getString("service_name"));
            s.setType(rs.getString("service_type"));
            s.setDescription(rs.getString("description"));
            s.setBasePrice(rs.getDouble("base_price"));
            s.setEstimatedDuration(rs.getInt("estimated_duration"));
            list.add(s);
        }

        con.close();
        return list;
    }
}
