package com.tastyExpress.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tastyExpress.dao.RestaurentDAO;
import com.tastyExpress.model.Restaurent;
import com.tastyExpress.util.DBConnection;

public class RestaurentDAOImpl implements RestaurentDAO {

    // INSERT includes isVeg
    private static final String INSERT_RESTAURENT =
        "INSERT INTO restaurent (restaurentId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath, isVeg) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    private static final String GET_RESTAURENT =
        "SELECT * FROM restaurent WHERE restaurentId = ?";

    // UPDATE includes isVeg
    private static final String UPDATE_RESTAURENT =
        "UPDATE restaurent SET name=?, address=?, phone=?, rating=?, cusineType=?, isActive=?, eta=?, adminUserId=?, imagePath=?, isVeg=? " +
        "WHERE restaurentId=?";

    private static final String DELETE_RESTAURENT =
        "DELETE FROM restaurent WHERE restaurentId = ?";

    private static final String GET_ALL_RESTAURENTS =
        "SELECT * FROM restaurent";


    @Override
    public void addRestaurent(Restaurent r) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_RESTAURENT)) {

            ps.setInt(1, r.getRestaurentId());
            ps.setString(2, r.getName());
            ps.setString(3, r.getAddress());
            ps.setLong(4, r.getPhone());
            ps.setFloat(5, r.getRating());
            ps.setString(6, r.getCusineType());
            ps.setString(7, r.getIsActive());
            ps.setString(8, r.getEta());
            ps.setInt(9, r.getAdminUserId());
            ps.setString(10, r.getImagePath());
            ps.setString(11, r.getIsVeg());      // NEW FIELD

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Restaurent getRestaurent(int id) {

        Restaurent r = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_RESTAURENT)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = mapRestaurent(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }


    @Override
    public void updateRestaurent(Restaurent r) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_RESTAURENT)) {

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setLong(3, r.getPhone());
            ps.setFloat(4, r.getRating());
            ps.setString(5, r.getCusineType());
            ps.setString(6, r.getIsActive());
            ps.setString(7, r.getEta());
            ps.setInt(8, r.getAdminUserId());
            ps.setString(9, r.getImagePath());
            ps.setString(10, r.getIsVeg());      // NEW FIELD
            ps.setInt(11, r.getRestaurentId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteRestaurent(int id) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_RESTAURENT)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Restaurent> getAllRestaurents() {

        List<Restaurent> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_RESTAURENTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRestaurent(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // Helper method
    private Restaurent mapRestaurent(ResultSet rs) throws SQLException {

        Restaurent r = new Restaurent(
                rs.getInt("restaurentId"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getLong("phone"),
                rs.getFloat("rating"),
                rs.getString("cusineType"),
                rs.getString("isActive"),
                rs.getString("eta"),
                rs.getInt("adminUserId"),
                rs.getString("imagePath")
        );

        r.setIsVeg(rs.getString("isVeg"));   // NEW FIELD

        return r;
    }
}
