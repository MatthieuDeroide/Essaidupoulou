package entitiesTestCase;

import entities.Admin;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminTestCase {

    @Test
    public void shouldReturnAllPropertiesOfAdmin(){
        //GIVEN
        Admin admin = new Admin(1,"Kleinsberg","Coco","coco.kleinsberg@gmail.com","test");

        //WHEN
        Integer id=admin.getId();
        String nom=admin.getNom();
        String prenom=admin.getPrenom();
        String mail=admin.getMail();
        String mdp=admin.getMdp();

        //THEN
        assertThat(id).isEqualTo(1);
        assertThat(nom).isEqualTo("Kleinsberg");
        assertThat(prenom).isEqualTo("Coco");
        assertThat(mail).isEqualTo("coco.kleinsberg@gmail.com");
        assertThat(mdp).isEqualTo("test");

    }


    @Test
    public void shouldSetAllPropertiesOfAdmin(){
        //GIVEN
        Admin admin = new Admin(null,null,null,null,null);

        //WHEN
        admin.setId(1);
        admin.setNom("Kleinsberg");
        admin.setPrenom("Coco");
        admin.setMail("coco.kleinsberg@gmail.com");
        admin.setMdp("test");

        //THEN
        assertThat(admin.getId()).isEqualTo(1);
        assertThat(admin.getNom()).isEqualTo("Kleinsberg");
        assertThat(admin.getPrenom()).isEqualTo("Coco");
        assertThat(admin.getMail()).isEqualTo("coco.kleinsberg@gmail.com");
        assertThat(admin.getMdp()).isEqualTo("test");
    }
}
