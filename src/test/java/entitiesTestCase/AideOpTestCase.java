package entitiesTestCase;

import entities.AideOperatoire;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AideOpTestCase {

    @Test
    public void shouldGetAllPropertiesOfAideOps() {
        //GIVEN
        AideOperatoire aideops = new AideOperatoire("homme", "name", "firstname",
                LocalDate.of(2018, 11, 10), "france", "Lille", 59800,
                "rue de toul", "infirmier", "adresse@mail.test", "0606060606", "mdp");

        //WHEN
        String civilite = aideops.getCivilite();
        String nom = aideops.getNom();
        String prenom = aideops.getPrenom();
        LocalDate dateNaissance = aideops.getDateNaissance();
        String pays = aideops.getPays();
        String ville = aideops.getVille();
        Integer codeP = aideops.getCodePostal();
        String adresse = aideops.getAdresse();
        String poste = aideops.getPosteOccupe();
        String mail = aideops.getMail();
        String numero = aideops.getNumeroTel();
        String mdp = aideops.getMdp();

        //THEN
        assertThat(civilite).isEqualTo("homme");
        assertThat(nom).isEqualTo("name");
        assertThat(prenom).isEqualTo("firstname");
        assertThat(dateNaissance).isEqualTo(LocalDate.of(2018, 11, 10));
        assertThat(pays).isEqualTo("france");
        assertThat(ville).isEqualTo("Lille");
        assertThat(codeP).isEqualTo(59800);
        assertThat(adresse).isEqualTo("rue de toul");
        assertThat(poste).isEqualTo("infirmier");
        assertThat(mail).isEqualTo("adresse@mail.test");
        assertThat(numero).isEqualTo("0606060606");
        assertThat(mdp).isEqualTo("mdp");

    }

    @Test
    public void shouldSetAllPropertiesofAideops() {
        //GIVEN
        AideOperatoire aideops = new AideOperatoire(null, null, null,
                null, null, null, null,
                null, null, null, null, null);

        //WHEN
        aideops.setCivilite("homme");
        aideops.setNom("name");
        aideops.setPrenom("firstname");
        aideops.setDateNaissance(LocalDate.of(2018, 11, 10));
        aideops.setPays("france");
        aideops.setVille("Lille");
        aideops.setCodePostal(59800);
        aideops.setAdresse("rue de toul");
        aideops.setPosteOccupe("infirmier");
        aideops.setMail("adresse@mail.test");
        aideops.setNumeroTel("0606060606");
        aideops.setMdp("mdp");

        //THEN
        assertThat(aideops.getCivilite()).isEqualTo("homme");
        assertThat(aideops.getNom()).isEqualTo("name");
        assertThat(aideops.getPrenom()).isEqualTo("firstname");
        assertThat(aideops.getDateNaissance()).isEqualTo(LocalDate.of(2018, 11, 10));
        assertThat(aideops.getPays()).isEqualTo("france");
        assertThat(aideops.getVille()).isEqualTo("Lille");
        assertThat(aideops.getCodePostal()).isEqualTo(59800);
        assertThat(aideops.getAdresse()).isEqualTo("rue de toul");
        assertThat(aideops.getPosteOccupe()).isEqualTo("infirmier");
        assertThat(aideops.getMail()).isEqualTo("adresse@mail.test");
        assertThat(aideops.getNumeroTel()).isEqualTo("0606060606");
        assertThat(aideops.getMdp()).isEqualTo("mdp");

    }


}
