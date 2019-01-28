package dao;

import entities.AideOperatoire;
import managers.MainManager;
import org.junit.Before;
import org.junit.Test;
import utilisateurs.MotDePasseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AideOpDaoTestCase {

    AideOperatoireDAO aideOperatoireDAO = new AideOperatoireDAO();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM aideoperatoire");
            stmt.executeUpdate("INSERT INTO `aideoperatoire`(`id`,`civilite`,`nom`,`prenom`,`dateNaissance`,`pays`,`ville`,`codePostal`,`adresse`,`posteOccupe`,`mail`,`numeroTel`,`mdp`) VALUES (1,'test1', 'test2', 'test3', '2018-11-09', 'testpays', 'testville', 59, 'testadresse','testposte', 'aideop@test.fr', 'testnumeroTel','"+MotDePasseUtils.genererMotDePasse("test")+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetAideOperatoireById() {
//WHEN
        aideOperatoireDAO.getAideOperatoire(1);
        String sqlQuery = "SELECT * FROM aideoperatoire WHERE id=?;";
//THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, 1);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        assertThat(result.getInt("id")).isGreaterThan(0);
                        assertThat(result.getString("civilite")).isEqualTo("test1");
                    }
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetAideOperatoireByMail() {
        // WHEN
        AideOperatoire result = aideOperatoireDAO.getAideOperatoire("aideop@test.fr");
        //THEN
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getCivilite()).isEqualTo("test1");
        assertThat(result.getNom()).isEqualTo("test2");
        assertThat(result.getPrenom()).isEqualTo("test3");
        assertThat(result.getAdresse()).isEqualTo("testadresse");
        assertThat(result.getCodePostal()).isEqualTo(59);
        assertThat(result.getVille()).isEqualTo("testville");
        assertThat(result.getPays()).isEqualTo("testpays");
        assertThat(result.getPosteOccupe()).isEqualTo("testposte");
        assertThat(MotDePasseUtils.validerMotDePasse("test",result.getMdp())).isTrue();



    }


    @Test
    public void shouldListAideOperatoire() {
        // WHEN
        List<AideOperatoire> list = aideOperatoireDAO.listAideOperatoire();
        // THEN
        assertThat(list).hasSize(1);
        assertThat(list).extracting("civilite", "nom", "prenom").containsOnly(tuple("test1", "test2", "test3"));
    }


    @Test
    public void addAideOperatoire() {
        // WHEN
        aideOperatoireDAO.addAideOperatoire(new AideOperatoire("test1", "test2", "test3", LocalDate.of(2018, 11, 9), "testpays", "testville", 59, "testadresse", "testposte", "test@test.fr", "testnumeroTel", MotDePasseUtils.genererMotDePasse("test")));
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM aideoperatoire WHERE mail = 'test@test.fr'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("id")).isGreaterThan(0);
                assertThat(rs.getString("mail")).isEqualTo("test@test.fr");
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateAideOp(){
        //GIVEN
        AideOperatoire aideOperatoire = aideOperatoireDAO.getAideOperatoire("aideop@test.fr");

        //WHEN
        aideOperatoire.setAdresse("testupdateadresse");
        aideOperatoireDAO.updateAideop(aideOperatoire);

        //THEN
        assertThat(aideOperatoireDAO.getAideOperatoire("aideop@test.fr").getAdresse()).isEqualTo("testupdateadresse");

    }

    @Test
    public void shouldUpdateAideOpPassword(){
        //GIVEN
        AideOperatoire aideOperatoire = aideOperatoireDAO.getAideOperatoire("aideop@test.fr");

        //WHEN
        aideOperatoire.setMdp(MotDePasseUtils.genererMotDePasse("testtest"));
        aideOperatoireDAO.updateAideopPassword(aideOperatoire);

        //THEN
        assertThat(MotDePasseUtils.validerMotDePasse("testtest",aideOperatoireDAO.getAideOperatoire("aideop@test.fr").getMdp())).isTrue();

    }

    @Test
    public void shouldDeleteAideOp(){
        //GIVEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO `aideoperatoire`(`id`,`civilite`,`nom`,`prenom`,`dateNaissance`,`pays`,`ville`,`codePostal`,`adresse`,`posteOccupe`,`mail`,`numeroTel`,`mdp`) VALUES (2,'testdelete', 'testdelete', 'testdelete', '2018-11-09', 'testpays', 'testville', 59, 'testadresse','testposte', 'aideop@test.fr', 'testnumeroTel','"+MotDePasseUtils.genererMotDePasse("test")+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //WHEN
        aideOperatoireDAO.deleteAideOperatoire(2);

        //THEN
        assertThat(aideOperatoireDAO.listAideOperatoire().size()).isEqualTo(1);

    }

}
