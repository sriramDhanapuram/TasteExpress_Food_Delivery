package com.tastyExpress.daoimpl;

import com.tastyExpress.dao.OrdersDAO;
import com.tastyExpress.model.Orders;
import com.tastyExpress.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    private static final String INSERT_ORDER =
            "INSERT INTO orders (orderId, userId, restaurentId, orderDate, totalAmount, status, paymentMode) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ORDER =
            "SELECT * FROM orders WHERE orderId = ?";

    private static final String UPDATE_ORDER =
            "UPDATE orders SET userId = ?, restaurentId = ?, totalAmount = ?, status = ?, paymentMode = ? " +
            "WHERE orderId = ?";

    private static final String DELETE_ORDER =
            "DELETE FROM orders WHERE orderId = ?";

    private static final String GET_ALL_ORDERS =
            "SELECT * FROM orders";

    private static final String GET_ORDERS_BY_USER = 
    	    "SELECT * FROM orders WHERE userId = ? ORDER BY orderDate DESC";


    // ---------------------------------------------------------
    // ADD ORDER
    // ---------------------------------------------------------
    @Override
    public void addOrder(Orders order) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER)) {

            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getUserId());
            ps.setInt(3, order.getRestaurantId());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // auto current time
            ps.setDouble(5, order.getTotalAmount());
            ps.setString(6, order.getStatus());
            ps.setString(7, order.getPaymentMode());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ---------------------------------------------------------
    // GET ONE ORDER
    // ---------------------------------------------------------
    @Override
    public Orders getOrder(int orderId) {

        Orders order = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ORDER)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Orders(
                        rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getInt("restaurentId"),
                        rs.getTimestamp("orderDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }


    // ---------------------------------------------------------
    // UPDATE ORDER
    // ---------------------------------------------------------
    @Override
    public void updateOrder(Orders order) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ORDER)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getPaymentMode());
            ps.setInt(6, order.getOrderId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ---------------------------------------------------------
    // DELETE ORDER
    // ---------------------------------------------------------
    @Override
    public void deleteOrder(int orderId) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ---------------------------------------------------------
    // LIST ALL ORDERS
    // ---------------------------------------------------------
    @Override
    public List<Orders> getAllOrders() {

        List<Orders> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_ORDERS)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getInt("restaurentId"),
                        rs.getTimestamp("orderDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                );

                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // ---------------------------------------------------------
    // LIST ORDERS BY USERID
    // ---------------------------------------------------------
    @Override
    public List<Orders> getOrdersByUserId(int userId) {

        List<Orders> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ORDERS_BY_USER)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders o = new Orders(
                        rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getInt("restaurantId"),
                        rs.getTimestamp("orderDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode")
                );
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
