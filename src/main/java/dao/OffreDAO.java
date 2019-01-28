package dao;

import entities.Offre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreDAO {

    public Offre getOffre(Integer id) {
        String sqlQuery = "SELECT * FROM offres WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Offre offre = new Offre(resultSet.getString("titre"),
                                resultSet.getString("description"),
                                resultSet.getDate("datedebut").toLocalDate(),
                                resultSet.getDate("datefin").toLocalDate(),
                                resultSet.getString("professionRecherchee"),
                                resultSet.getString("adresse"),
                                resultSet.getString("etablissement"),
                                resultSet.getInt("codePostal")
                        );
                        offre.setId(resultSet.getInt("id"));
                        return offre;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Offre> listOffre() {
        List<Offre> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM offres;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Offre offre = new Offre(
                                resultSet.getString("titre"),
                                resultSet.getString("description"),
                                resultSet.getDate("datedebut").toLocalDate(),
                                resultSet.getDate("datefin").toLocalDate(),
                                resultSet.getString("professionRecherchee"),
                                resultSet.getString("adresse"),
                                resultSet.getString("etablissement"),
                                resultSet.getInt("codePostal")
                        );
                        offre.setId(resultSet.getInt("id"));
                        list.add(offre);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Offre> listOffre(Integer profession, Integer userId) {
        List<Offre> list = new ArrayList<>();
        Integer isAideOp;
        if (profession==1){
            isAideOp=1;
        }else {
            isAideOp=0;
        }
        String sqlQuery = "SELECT * FROM offres WHERE isaideop=? AND auteur=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1,isAideOp);
                statement.setInt(2,userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Offre offre = new Offre(
                                resultSet.getString("titre"),
                                resultSet.getString("description"),
                                resultSet.getDate("datedebut").toLocalDate(),
                                resultSet.getDate("datefin").toLocalDate(),
                                resultSet.getString("professionRecherchee"),
                                resultSet.getString("adresse"),
                                resultSet.getString("etablissement"),
                                resultSet.getInt("codePostal")
                        );
                        offre.setId(resultSet.getInt("id"));
                        list.add(offre);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Offre addOffre(Offre offre,Integer auteurId, Boolean isAideOp) {
        String sqlQuery = "INSERT INTO offres (titre, description, datedebut, datefin, professionRecherchee, adresse, etablissement, codePostal, auteur, isaideop) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, offre.getTitre());
                statement.setString(2, offre.getDescription());
                statement.setDate(3, Date.valueOf(offre.getDateDebut()));
                statement.setDate(4, Date.valueOf(offre.getDateFin()));
                statement.setString(5, offre.getProfessionRecherchee());
                statement.setString(6, offre.getAdresse());
                statement.setString(7, offre.getEtablissement());
                statement.setInt(8, offre.getCodePostal());
                statement.setInt(9,auteurId);
                statement.setBoolean(10,isAideOp);
                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        offre.setId(generatedKeys.getInt(1));
                        return offre;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteOffre(int idOffre) {
        String sqlQuery = "DELETE FROM offres WHERE id=?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1,idOffre);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

