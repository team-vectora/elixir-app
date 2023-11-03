package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Folder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CharacterViewDAO extends CrudDAO {

    public static class Column {
        String name;
        Class<?> type;

        public Column(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }
    }

    @Override
    public int create(Object model) throws SQLException {
        return 0;
    }

    @Override
    public void update(Object model) throws SQLException {

    }

    @Override
    public Map read(Object userId) throws SQLException {
        String query = "SELECT f.id_user, c.*, a.*, cy.*, i.*, s.*, sp.* FROM `Character` c \n" +
                "LEFT JOIN Folder f ON c.id_folder = f.id\n" +
                "LEFT JOIN `User` u ON f.id_user = u.id\n" +
                "LEFT JOIN Attribute a ON a.id = c.id_attribute\n" +
                "LEFT JOIN Currency cy ON cy.id_character = c.id\n" +
                "LEFT JOIN Inventory i ON i.id_character = c.id\n" +
                "LEFT JOIN Slots s ON s.id_character = c.id\n" +
                "LEFT JOIN Speech sp ON sp.id_character = c.id\n" +
                "WHERE u.id = ?;";
        ResultSet resultSet = null;
        Map<String, Object> viewMap = new HashMap<>();

        Column[] columns = {
                new Column("id_user", String.class),
                new Column("id", Integer.class),
                new Column("id_alignment", Integer.class),
                new Column("id_attribute", Integer.class),
                new Column("id_class", Integer.class),
                new Column("id_race", Integer.class),
                new Column("id_folder", Integer.class),
                new Column("name", String.class),
                new Column("player_name", String.class),
                new Column("experience", Integer.class),
                new Column("height", Integer.class),
                new Column("weight", Integer.class),
                new Column("current_pv", Integer.class),
                new Column("max_pv", Integer.class),
                new Column("class_armor_bonus", Integer.class),
                new Column("apperance", String.class),
                new Column("background", String.class),
                new Column("image_path", String.class),
                new Column("id", Integer.class),
                new Column("strength", Integer.class),
                new Column("dexterity", Integer.class),
                new Column("constitution", Integer.class),
                new Column("intelligence", Integer.class),
                new Column("wisdom", Integer.class),
                new Column("charisma", Integer.class),
                new Column("id", Integer.class),
                new Column("id_character", Integer.class),
                new Column("gold", Integer.class),
                new Column("silver", Integer.class),
                new Column("copper", Integer.class),
                new Column("electrium", Integer.class),
                new Column("platinium", Integer.class),
                new Column("id", Integer.class),
                new Column("id_character", Integer.class),
                new Column("item_id", Integer.class),
                new Column("type_item_id", Integer.class),
                new Column("id", Integer.class),
                new Column("id_character", Integer.class),
                new Column("I_level", Integer.class),
                new Column("II_level", Integer.class),
                new Column("III_level", Integer.class),
                new Column("IV_level", Integer.class),
                new Column("V_level", Integer.class),
                new Column("VI_level", Integer.class),
                new Column("VII_level", Integer.class),
                new Column("VIII_level", Integer.class),
                new Column("IX_level", Integer.class),
                new Column("id", Integer.class),
                new Column("id_character", Integer.class),
                new Column("id_language", Integer.class)
        };

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, (Integer) userId);
            resultSet = stmt.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                String columnName = columns[count].getName();
                Class<?> columnClass = columns[count].getType();
                viewMap.put(columnName, columnClass == Integer.class ? resultSet.getInt(columnName) : resultSet.getString(columnName));
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return viewMap;
    }

    @Override
    public Map read() throws SQLException {
        return null;
    }

    @Override
    public void delete(Object model) throws SQLException {

    }
}