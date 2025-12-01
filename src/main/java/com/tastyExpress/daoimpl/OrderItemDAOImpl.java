package com.tastyExpress.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tastyExpress.dao.OrderItemDAO;
import com.tastyExpress.model.OrderItem;
import com.tastyExpress.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_ORDERITEM =
            "INSERT INTO orderItem (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";

    private static final String GET_ORDERITEM =
            "SELECT * FROM orderItem WHERE orderItemId = ?";

    private static final String UPDATE_ORDERITEM =
            "UPDATE orderItem SET orderId = ?, menuId = ?, quantity = ?, totalPrice = ? WHERE orderItemId = ?";

    private static final String DELETE_ORDERITEM =
            "DELETE FROM orderItem WHERE orderItemId = ?";

    private static final String GET_ALL_ORDERITEMS =
            "SELECT * FROM orderItem";


    @Override
    public void addOrderItem(OrderItem item) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDERITEM)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getTotalPrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public OrderItem getOderItem(int orderItemId) {
        OrderItem item = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ORDERITEM)) {

            ps.setInt(1, orderItemId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }


    @Override
    public void updateOrderItem(OrderItem item) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ORDERITEM)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getTotalPrice());
            ps.setInt(5, item.getOrderItemId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDERITEM)) {

            ps.setInt(1, orderItemId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_ORDERITEMS)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice")
                );

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
