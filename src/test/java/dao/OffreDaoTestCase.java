package dao;

import entities.AideOperatoire;
import entities.Offre;
import managers.MainManager;
import org.junit.Before;
import org.junit.Test;
import utilisateurs.MotDePasseUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class OffreDaoTestCase {
    OffreDAO offreDAO = new OffreDAO();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM offres");
            stmt.executeUpdate("INSERT INTO `offres`(`id`,`titre`,`description`,`datedebut`,`datefin`,`professionRecherchee`,`adresse`,`etablissement`,`codePostal`,`auteur`,`isaideop`) VALUES (1,'test1', 'test2', '2018-11-08', '2018-11-09', 'testprofession', 'testadresse','testetablissement',59,1,0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetOffre() {
//WHEN
       offreDAO.getOffre(1);
        String sqlQuery = "SELECT * FROM offres WHERE id=?;";
//THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, 1);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        assertThat(result.getInt("id")).isGreaterThan(0);
                        assertThat(result.getString("titre")).isEqualTo("test1");
                    }
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ;
    }


    @Test
    public void shouldListOffre() {
        // WHEN
        List<Offre> list = offreDAO.listOffre();
        List<Offre> list2 = offreDAO.listOffre(0,1);
        // THEN
        assertThat(list).hasSize(1);
        assertThat(list2).hasSize(1);
        assertThat(list).extracting("titre", "description").containsOnly(tuple("test1", "test2"));
        assertThat(list2).extracting("titre", "description").containsOnly(tuple("test1", "test2"));
    }


    @Test
    public void shouldAddOffre() {
        // WHEN
       offreDAO.addOffre(new Offre("test1", "test2", LocalDate.of(2018,11,8), LocalDate.of(2018,11,9), "testprofession", "testadresse","testetablissement",59),0,false);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM offres WHERE titre = 'test1'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id")).isGreaterThan(0);
                assertThat(rs.getString("titre")).isEqualTo("test1");
                assertThat(rs.next()).isTrue();
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldDeleteOffre() {
        //GIVEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO `offres`(`id`,`titre`,`description`,`datedebut`,`datefin`,`professionRecherchee`,`adresse`,`etablissement`,`codePostal`,`auteur`,`isaideop`) VALUES (2,'testdelete', 'testdelete', '2018-11-08', '2018-11-09', 'testprofession', 'testadresse','testetablissement',59,1,0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //WHEN
        offreDAO.deleteOffre(2);

        //THEN
        assertThat(offreDAO.listOffre().size()).isEqualTo(1);
    }


}

