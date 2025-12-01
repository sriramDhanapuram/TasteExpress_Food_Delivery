package com.tastyExpress.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tastyExpress.dao.UserDAO;
import com.tastyExpress.model.User;
import com.tastyExpress.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    // ----------------------------------------------------
    // SQL QUERIES
    // ----------------------------------------------------
    private static final String INSERT_USER =
        "INSERT INTO user (userId, userName, password, email, phone, address, role, createdDate, lastLoginDate) " +
        "VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String GET_USER =
        "SELECT * FROM user WHERE userId = ?";

    private static final String UPDATE_USER =
        "UPDATE user SET userName=?, password=?, email=?, phone=?, address=?, role=? WHERE userId = ?";

    private static final String DELETE_USER =
        "DELETE FROM user WHERE userId = ?";

    private static final String GET_ALL_USERS =
        "SELECT * FROM user";

    private static final String LOGIN_USER =
        "SELECT * FROM user WHERE email=? AND password=?";


    // ----------------------------------------------------
    // ADD USER — Runs during Sign-Up
    // ----------------------------------------------------
    @Override
    public void addUser(User user) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_USER)) {

            // Insert basic user data
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setLong(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());

            // Automatically set timestamps
            Timestamp now = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(8, now);  // createdDate
            ps.setTimestamp(9, now);  // lastLoginDate

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ----------------------------------------------------
    // GET USER BY ID — Fetch full user profile
    // ----------------------------------------------------
    @Override
    public User getUser(int userId) {

        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_USER)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);   // Convert ResultSet → User object
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    // ----------------------------------------------------
    // UPDATE USER — Edit name, email, password, phone, etc.
    // ----------------------------------------------------
    @Override
    public void updateUser(User user) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_USER)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            ps.setInt(7, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ----------------------------------------------------
    // DELETE USER — Remove a user from system
    // ----------------------------------------------------
    @Override
    public void deleteUser(int userId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_USER)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ----------------------------------------------------
    // GET ALL USERS — Mainly for admin dashboards
    // ----------------------------------------------------
    @Override
    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_USERS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapUser(rs));   // Convert each row into User object
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // ----------------------------------------------------
    // LOGIN — Verifies Email + Password
    // ----------------------------------------------------
    @Override
    public User login(String email, String password) {

        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(LOGIN_USER)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);        // Login success → map user
                updateLastLogin(user.getUserId()); // Update login time
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;   // Returns null → login failed
    }


    // ----------------------------------------------------
    // Update last login timestamp — called during login()
    // ----------------------------------------------------
    private void updateLastLogin(int userId) {

        String sql = "UPDATE user SET lastLoginDate=? WHERE userId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ----------------------------------------------------
    // Helper: Converts ResultSet row → User object
    // ----------------------------------------------------
    private User mapUser(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt("userId"),
                rs.getString("userName"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getLong("phone"),
                rs.getString("address"),
                rs.getString("role"),
                rs.getTimestamp("createdDate"),
                rs.getTimestamp("lastLoginDate")
        );
    }
}
