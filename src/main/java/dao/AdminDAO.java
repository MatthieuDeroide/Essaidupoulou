package dao;

import entities.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public Admin getAdmin(Integer id) {
        String sqlQuery = "SELECT * FROM admin WHERE id_admin=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Admin(
                                resultSet.getInt("id_admin"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getString("mail"),
                                resultSet.getString("mdp")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Admin getAdmin(String mail) {
        String sqlQuery = "SELECT * FROM admin WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, mail);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Admin(
                                resultSet.getInt("id_admin"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getString("mail"),
                                resultSet.getString("mdp")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> listAdmin() {
        List<Admin> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM admin;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(new Admin(
                                resultSet.getInt("id_admin"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getString("mail"),
                                resultSet.getString("mdp")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
