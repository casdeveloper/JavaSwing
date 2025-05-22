package com.casdeveloper.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends GenericDao {

    public ContactDao(ConnectionDatabase connection) {
        super(connection);
    }

    public void save(Contact contact) throws SQLException {
        String query = "INSERT INTO contact (name, nickname, birth_day) VALUES (?, ?, ?)";
        super.save(query, contact.getName(), contact.getNickName(), contact.getBirth_Day());
    }

    public void update(Contact contact) throws SQLException {
        String query = "UPDATE contact SET name = ?, nickname = ?, birth_day = ? WHERE id = ?";
        super.update(query, contact.getName(), contact.getNickName(), contact.getBirth_Day(), contact.getId());
    }

    public void delete(Contact contact) throws SQLException {
        String query = "DELETE FROM contact WHERE id = ?";
        super.delete(query, contact.getId());
    }

    public Contact findByName(String name) throws SQLException {
        String query = "SELECT * FROM contact WHERE name = ?";
        Contact contact = null;
        PreparedStatement stmt = getConnection().getConnection().prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setName(rs.getString("name"));
            contact.setNickName(rs.getString("nickname"));
            contact.setBirth_Day(rs.getDate("birth_day"));
        }
        rs.close();
        stmt.close();
        getConnection().getConnection().close();
        return contact;
    }

    public List<Contact> findAll() throws SQLException {
        String query = "SELECT * FROM contact";
        PreparedStatement stmt = getConnection().getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        List<Contact> contacts = new ArrayList<>();
        Contact contact;
        while (rs.next()) {
            contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setName(rs.getString("name"));
            contact.setNickName(rs.getString("nickname"));
            contact.setBirth_Day(rs.getDate("birth_day"));
            contacts.add(contact);
        }

        rs.close();
        stmt.close();
        getConnection().getConnection().close();
        return contacts;
    }
}
