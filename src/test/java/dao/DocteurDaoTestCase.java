package dao;

import entities.AideOperatoire;
import entities.Docteur;
import managers.MainManager;
import org.junit.Before;
import org.junit.Test;
import utilisateurs.MotDePasseUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class DocteurDaoTestCase {

    DocteurDAO docteurDAO = new DocteurDAO();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM docteur");
            stmt.executeUpdate("INSERT INTO `docteur`(`id`,`civilite`,`nom`,`prenom`,`dateNaissance`,`pays`,`ville`,`codePostal`,`adresse`,`posteOccupe`,`mail`,`numeroTel`,`mdp`) VALUES (1,'test1', 'test2', 'test3', '2018-11-09', 'testpays', 'testville', 59, 'testadresse','testposte', 'docteur@test.fr', 'testnumeroTel', '"+MotDePasseUtils.genererMotDePasse("test")+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void shouldListDocteur() {
        // WHEN
        List<Docteur> list = docteurDAO.listDocteur();
        // THEN
        assertThat(list).hasSize(1);
        assertThat(list).extracting("civilite", "nom", "prenom").containsOnly(tuple("test1", "test2", "test3"));
    }


    @Test
    public void addDocteur() {
        // WHEN
        docteurDAO.addDocteur(new Docteur(true, "Homme", "test2", "test3", LocalDate.of(2018, 11, 9), "testpays", "testville", 59, "testadresse", "testposte", "test@test.fr", "testnumeroTel", MotDePasseUtils.genererMotDePasse("test")));
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM docteur WHERE mail='test@test.fr'")) {
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
    public void shouldGetDocteurByMail() {
        // WHEN
        Docteur result = docteurDAO.getDocteur("docteur@test.fr");
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
    public void shouldGetDocteurById() {
        // WHEN
        Docteur result = docteurDAO.getDocteur(1);
        //THEN
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
    public void shouldUpdateDocteur(){
        //GIVEN
        Docteur docteur = docteurDAO.getDocteur("docteur@test.fr");

        //WHEN
        docteur.setAdresse("testupdateadresse");
        docteurDAO.updateDocteur(docteur);

        //THEN
        assertThat(docteurDAO.getDocteur("docteur@test.fr").getAdresse()).isEqualTo("testupdateadresse");

    }

    @Test
    public void shouldUpdateDocteurPassword(){
        //GIVEN
        Docteur docteur = docteurDAO.getDocteur("docteur@test.fr");

        //WHEN
        docteur.setMdp(MotDePasseUtils.genererMotDePasse("testtest"));
        docteurDAO.updateDocteurPassword(docteur);

        //THEN
        assertThat(MotDePasseUtils.validerMotDePasse("testtest",docteurDAO.getDocteur("docteur@test.fr").getMdp())).isTrue();

    }

    @Test
    public void shouldDeleteDocteur(){
        //GIVEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO `docteur`(`id`,`civilite`,`nom`,`prenom`,`dateNaissance`,`pays`,`ville`,`codePostal`,`adresse`,`posteOccupe`,`mail`,`numeroTel`,`mdp`) VALUES (2,'testdelete', 'testdelete', 'testdelete', '2018-11-09', 'testpays', 'testville', 59, 'testadresse','testposte', 'aideop@test.fr', 'testnumeroTel','"+MotDePasseUtils.genererMotDePasse("test")+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //WHEN
        docteurDAO.deleteDocteur(2);

        //THEN
        assertThat(docteurDAO.listDocteur().size()).isEqualTo(1);

    }


}

