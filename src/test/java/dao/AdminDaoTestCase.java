package dao;

import entities.Admin;
import org.junit.Before;
import org.junit.Test;
import utilisateurs.MotDePasseUtils;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.tuple;

public class AdminDaoTestCase {

    AdminDAO adminDAO = new AdminDAO();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM admin");
            stmt.executeUpdate("INSERT INTO `admin`(`id_admin`,`nom`,`prenom`,`mail`,`mdp`) VALUES (1,'test1', 'test2','admin@test.fr','"+ MotDePasseUtils.genererMotDePasse("test")+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldGetAdminByMail() {
        //WHEN
        Admin result =adminDAO.getAdmin("admin@test.fr");
        //THEN
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNom()).isEqualTo("test1");
        assertThat(result.getPrenom()).isEqualTo("test2");
        assertThat(result.getPrenom()).isEqualTo("test2");
        assertThat(MotDePasseUtils.validerMotDePasse("test",result.getMdp())).isTrue();


    }

    @Test
    public void shouldGetAdminById() {
        //WHEN
        Admin result =adminDAO.getAdmin(1);
        //THEN
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNom()).isEqualTo("test1");
        assertThat(result.getPrenom()).isEqualTo("test2");
        assertThat(MotDePasseUtils.validerMotDePasse("test",result.getMdp())).isTrue();


    }



    @Test
    public void shouldListAdmin() {
        // WHEN
        List<Admin> list = adminDAO.listAdmin();
        // THEN
        assertThat(list).hasSize(1);
        assertThat(list).extracting( "id","nom","prenom","mail").containsOnly(tuple(1,"test1", "test2","admin@test.fr"));
    }


}
