package dao;

import entities.Docteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocteurDAO {


    public Docteur getDocteur(Integer id) {
        String sqlQuery = "SELECT * FROM docteur WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Docteur(
                                resultSet.getBoolean("professeur"),
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

    public Docteur getDocteur(String mail){
        String sqlQuery="SELECT * FROM docteur WHERE mail=?";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()) {
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,mail);
                try(ResultSet resultSet=statement.executeQuery()){
                    if(resultSet.next()){
                        Docteur docteur = new Docteur(
                                false,
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
                        docteur.setId(resultSet.getInt("id"));
                        return docteur;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Docteur> listDocteur(){
        List<Docteur> list= new ArrayList<>();
        String sqlQuery="SELECT * FROM docteur;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                try(ResultSet resultSet=statement.executeQuery()){
                    while(resultSet.next()){
                        Docteur docteur = new Docteur(
                                resultSet.getBoolean("professeur"),
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
                        docteur.setId(resultSet.getInt("id"));
                        list.add(docteur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addDocteur(Docteur docteur) {
        String sqlQuery = "INSERT INTO docteur(civilite, nom, prenom, dateNaissance,pays,ville,codePostal,adresse,posteOccupe, mail,numeroTel, mdp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, docteur.getCivilite());
                statement.setString(2, docteur.getNom());
                statement.setString(3, docteur.getPrenom());
                statement.setDate(4, Date.valueOf(docteur.getDateNaissance()));
                statement.setString(5, docteur.getPays());
                statement.setString(6, docteur.getVille());
                statement.setInt(7, docteur.getCodePostal());
                statement.setString(8, docteur.getAdresse());
                statement.setString(9, docteur.getPosteOccupe());
                statement.setString(10, docteur.getMail());
                statement.setString(11, docteur.getNumeroTel());
                statement.setString(12, docteur.getMdp());

                int nbR = statement.executeUpdate();
                System.out.println(nbR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDocteur(Docteur docteur){
        String sqlQuery = "UPDATE docteur SET pays=?,ville=?,codePostal=?,adresse=?,numeroTel=? WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, docteur.getPays());
                statement.setString(2, docteur.getVille());
                statement.setInt(3, docteur.getCodePostal());
                statement.setString(4, docteur.getAdresse());
                statement.setString(5, docteur.getNumeroTel());
                statement.setString(6,docteur.getMail());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDocteurPassword(Docteur docteur){
        String sqlQuery = "UPDATE docteur SET mdp=? WHERE mail=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, docteur.getMdp());
                statement.setString(2,docteur.getMail());


                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDocteur(Integer id) {
        String sqlQuery = "DELETE FROM docteur WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


   /* public HashMap<String, String> mapdocteur() {
        HashMap<String, String> map = new HashMap<>();
        String sqlQuery = "SELECT mail,mdp FROM docteur;";
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


}

