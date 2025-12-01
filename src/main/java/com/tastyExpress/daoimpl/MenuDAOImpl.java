package com.tastyExpress.daoimpl;

import com.tastyExpress.dao.MenuDAO;
import com.tastyExpress.model.Menu;
import com.tastyExpress.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_MENU =
            "INSERT INTO menu (restaurantId, itemName, description, price, ratings, available, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_MENU =
            "SELECT * FROM menu WHERE menuId = ?";

    private static final String UPDATE_MENU =
            "UPDATE menu SET restaurantId = ?, itemName = ?, description = ?, price = ?, ratings = ?, available = ?, imageUrl = ? WHERE menuId = ?";

    private static final String DELETE_MENU =
            "DELETE FROM menu WHERE menuId = ?";

    private static final String GET_ALL_MENUS =
            "SELECT * FROM menu";

    private static final String GET_BY_RESTAURANT =
            "SELECT * FROM menu WHERE restaurantId = ?";


    // Convert DB tinyint(1) to boolean for safety
    private boolean getBooleanValue(int value) {
        return value == 1;
    }

    @Override
    public void addMenu(Menu menu) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_MENU)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getItemName());
            ps.setString(3, menu.getDescription());
            ps.setInt(4, menu.getPrice());
            ps.setDouble(5, menu.getRatings()); // ratings may come as int or double
            ps.setBoolean(6, menu.isAvailable());
            ps.setString(7, menu.getImageUrl());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_MENU)) {

            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                menu = new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("itemName"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getDouble("ratings"),   // even if DB is INT, Java converts to double
                        getBooleanValue(rs.getInt("available")),
                        rs.getString("imageUrl")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_MENU)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getItemName());
            ps.setString(3, menu.getDescription());
            ps.setInt(4, menu.getPrice());
            ps.setDouble(5, menu.getRatings());
            ps.setBoolean(6, menu.isAvailable());
            ps.setString(7, menu.getImageUrl());
            ps.setInt(8, menu.getMenuId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_MENU)) {

            ps.setInt(1, menuId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_MENUS)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("itemName"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getDouble("ratings"),
                        getBooleanValue(rs.getInt("available")),
                        rs.getString("imageUrl")
                );

                list.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Menu> getAllMenusByRestaurent(int restaurantId) {
        List<Menu> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_BY_RESTAURANT)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("itemName"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getDouble("ratings"),
                        getBooleanValue(rs.getInt("available")),
                        rs.getString("imageUrl")
                );

                list.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
