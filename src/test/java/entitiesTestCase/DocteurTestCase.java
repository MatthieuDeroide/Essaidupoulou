package entitiesTestCase;

import entities.Docteur;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DocteurTestCase {



    @Test
    public void shouldReturnAllPropertiesOfDocteur(){
        //GIVEN
        Docteur docteur=new Docteur(false,"homme","name","firstname",
                LocalDate.of(2018,11,10),"france","Lille",59800,
                "rue de toul","chirurgien","adresse@mail.test","0606060606","mdp");
        //WHEN
        String professeur=docteur.isProfesseur();
        String civilite=docteur.getCivilite();
        String nom=docteur.getNom();
        String prenom=docteur.getPrenom();
        LocalDate dateNaissance=docteur.getDateNaissance();
        String pays=docteur.getPays();
        String ville=docteur.getVille();
        Integer codeP=docteur.getCodePostal();
        String adresse=docteur.getAdresse();
        String poste=docteur.getPosteOccupe();
        String mail=docteur.getMail();
        String numero=docteur.getNumeroTel();
        String mdp=docteur.getMdp();

        //THEN
        assertThat(professeur).isEqualTo("Docteur");
        assertThat(civilite).isEqualTo("homme");
        assertThat(nom).isEqualTo("name");
        assertThat(prenom).isEqualTo("firstname");
        assertThat(dateNaissance).isEqualTo(LocalDate.of(2018,11,10));
        assertThat(pays).isEqualTo("france");
        assertThat(ville).isEqualTo("Lille");
        assertThat(codeP).isEqualTo(59800);
        assertThat(adresse).isEqualTo("rue de toul");
        assertThat(poste).isEqualTo("chirurgien");
        assertThat(mail).isEqualTo("adresse@mail.test");
        assertThat(numero).isEqualTo("0606060606");
        assertThat(mdp).isEqualTo("mdp");
    }

    @Test
    public void shouldSetAllPropertiesOfDocteur(){
        //GIVEN
        Docteur docteur=new Docteur(false,null,null,null,
                null,null,null,null,
                null,null,null,null,null);

        //WHEN
        docteur.setProfesseur(true);
        docteur.setCivilite("homme");
        docteur.setNom("name");
        docteur.setPrenom("firstname");
        docteur.setDateNaissance(LocalDate.of(2018,11,10));
        docteur.setPays("france");
        docteur.setVille("Lille");
        docteur.setCodePostal(59800);
        docteur.setAdresse("rue de toul");
        docteur.setPosteOccupe("chirurgien");
        docteur.setMail("adresse@mail.test");
        docteur.setNumeroTel("0606060606");
        docteur.setMdp("mdp");

        //THEN
        assertThat(docteur.isProfesseur()).isEqualTo("Professeur");
        assertThat(docteur.getCivilite()).isEqualTo("homme");
        assertThat(docteur.getNom()).isEqualTo("name");
        assertThat(docteur.getPrenom()).isEqualTo("firstname");
        assertThat(docteur.getDateNaissance()).isEqualTo(LocalDate.of(2018,11,10));
        assertThat(docteur.getPays()).isEqualTo("france");
        assertThat(docteur.getVille()).isEqualTo("Lille");
        assertThat(docteur.getCodePostal()).isEqualTo(59800);
        assertThat(docteur.getAdresse()).isEqualTo("rue de toul");
        assertThat(docteur.getPosteOccupe()).isEqualTo("chirurgien");
        assertThat(docteur.getMail()).isEqualTo("adresse@mail.test");
        assertThat(docteur.getNumeroTel()).isEqualTo("0606060606");
        assertThat(docteur.getMdp()).isEqualTo("mdp");

    }
}
