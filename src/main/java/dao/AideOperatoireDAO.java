package dao;

import dao.DataSourceProvider;
import entities.AideOperatoire;
import entities.Docteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AideOperatoireDAO {

    public AideOperatoire getAideOperatoire(Integer id) {
        String sqlQuery = "SELECT * FROM aideoperatoire WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new AideOperatoire(
                                resultSet.getString("civilite"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getDate("dateNaissance").toLocalDate(),
                                resultSet.getString("pays"),
                                resultSet.getString("ville"),
                                resultSet.getInt("codePostal"),
                                resultSet.getString("adresse"),
                                resultSet.getString("posteOccupe"),
                                resultSet.getString("mail"),
                                resultSet.getString("numeroTel"),
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

    public AideOperatoire getAideOperatoire(String mail) {
        String sqlQuery = "SELECT * FROM aideoperatoire WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, mail);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        AideOperatoire aideOperatoire = new AideOperatoire(
                                resultSet.getString("civilite"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getDate("dateNaissance").toLocalDate(),
                                resultSet.getString("pays"),
                                resultSet.getString("ville"),
                                resultSet.getInt("codePostal"),
                                resultSet.getString("adresse"),
                                resultSet.getString("posteOccupe"),
                                resultSet.getString("mail"),
                                resultSet.getString("numeroTel"),
                                resultSet.getString("mdp")
                        );
                        aideOperatoire.setId(resultSet.getInt("id"));
                        return aideOperatoire;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AideOperatoire> listAideOperatoire() {
        List<AideOperatoire> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM aideoperatoire;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        AideOperatoire aideOperatoire = new AideOperatoire(
                                resultSet.getString("civilite"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getDate("dateNaissance").toLocalDate(),
                                resultSet.getString("pays"),
                                resultSet.getString("ville"),
                                resultSet.getInt("codePostal"),
                                resultSet.getString("adresse"),
                                resultSet.getString("posteOccupe"),
                                resultSet.getString("mail"),
                                resultSet.getString("numeroTel"),
                                resultSet.getString("mdp")
                        );
                        aideOperatoire.setId(resultSet.getInt("id"));
                        list.add(aideOperatoire);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addAideOperatoire(AideOperatoire aideOperatoire) {
        String sqlQuery = "INSERT INTO aideoperatoire(civilite, nom, prenom, dateNaissance,pays,ville,codePostal,adresse,posteOccupe, mail,numeroTel, mdp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, aideOperatoire.getCivilite());
                statement.setString(2, aideOperatoire.getNom());
                statement.setString(3, aideOperatoire.getPrenom());
                statement.setDate(4, Date.valueOf(aideOperatoire.getDateNaissance()));
                statement.setString(5, aideOperatoire.getPays());
                statement.setString(6, aideOperatoire.getVille());
                statement.setInt(7, aideOperatoire.getCodePostal());
                statement.setString(8, aideOperatoire.getAdresse());
                statement.setString(9, aideOperatoire.getPosteOccupe());
                statement.setString(10, aideOperatoire.getMail());
                statement.setString(11, aideOperatoire.getNumeroTel());
                statement.setString(12, aideOperatoire.getMdp());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public HashMap<String, String> mapAideOp() {
        HashMap<String, String> map = new HashMap<>();
        String sqlQuery = "SELECT mail,mdp FROM aideope;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        map.put(
                                resultSet.getString("mail"),
                                resultSet.getString("mdp")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }*/

    public void updateAideop(AideOperatoire aideOperatoire){
        String sqlQuery = "UPDATE aideoperatoire SET pays=?,ville=?,codePostal=?,adresse=?,numeroTel=? WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, aideOperatoire.getPays());
                statement.setString(2, aideOperatoire.getVille());
                statement.setInt(3, aideOperatoire.getCodePostal());
                statement.setString(4, aideOperatoire.getAdresse());
                statement.setString(5, aideOperatoire.getNumeroTel());
                statement.setString(6,aideOperatoire.getMail());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAideopPassword(AideOperatoire aideOperatoire){
        String sqlQuery = "UPDATE aideoperatoire SET mdp=? WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, aideOperatoire.getMdp());
                statement.setString(2,aideOperatoire.getMail());


                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAideOperatoire(Integer id) {
        String sqlQuery = "DELETE FROM aideoperatoire WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}

