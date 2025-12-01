package com.tastyExpress.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tastyExpress.dao.ContactDAO;
import com.tastyExpress.model.ContactMessage;
import com.tastyExpress.util.DBConnection;

public class ContactDAOImpl implements ContactDAO {

    private static final String INSERT_MSG =
            "INSERT INTO contact_messages (name, email, phone, message) VALUES (?,?,?,?)";

    @Override
    public void saveMessage(ContactMessage msg) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_MSG)) {

            ps.setString(1, msg.getName());
            ps.setString(2, msg.getEmail());
            ps.setString(3, msg.getPhone());
            ps.setString(4, msg.getMessage());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
