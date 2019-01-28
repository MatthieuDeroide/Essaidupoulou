package entitiesTestCase;

import entities.AideOperatoire;
import entities.Offre;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class OffreTestCase {
    @Test
    public void shouldGetAllPropertiesOfOffre() {
        //GIVEN
        Offre offre = new Offre("titre", "description", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 12), "professionRecherchee", "adresse", "etablissement", 59);

        //WHEN
        String titre = offre.getTitre();
        String description = offre.getDescription();
        LocalDate datedebut = offre.getDateDebut();
        LocalDate datefin = offre.getDateFin();
        String profession = offre.getProfessionRecherchee();
        String adresse = offre.getAdresse();
        String etablissement = offre.getEtablissement();
        Integer codepostal = offre.getCodePostal();

        //THEN
        assertThat(titre).isEqualTo("titre");
        assertThat(description).isEqualTo("description");
        assertThat(datedebut).isEqualTo(LocalDate.of(2018, 11, 10));
        assertThat(datefin).isEqualTo(LocalDate.of(2018, 11, 12));
        assertThat(profession).isEqualTo("professionRecherchee");
        assertThat(adresse).isEqualTo("adresse");
        assertThat(etablissement).isEqualTo("etablissement");
        assertThat(adresse).isEqualTo("adresse");
        assertThat(codepostal).isEqualTo(59);

    }

    @Test
    public void shouldSetAllPropertiesofOffre() {
        //GIVEN
        Offre offre = new Offre(null, null, null,
                null, null, null, null,
                0);

        //WHEN
        offre.setTitre("titre");
        offre.setDescription("description");
        offre.setDateDebut(LocalDate.of(2018, 11, 10));
        offre.setDateFin(LocalDate.of(2018, 11, 11));
        offre.setProfessionRecherchee("professionRecherchee");
        offre.setAdresse("adresse");
        offre.setEtablissement("etablissement");
        offre.setCodePostal(59);

        //THEN
        assertThat(offre.getTitre()).isEqualTo("titre");
        assertThat(offre.getDescription()).isEqualTo("description");
        assertThat(offre.getDateDebut()).isEqualTo(LocalDate.of(2018, 11, 10));
        assertThat(offre.getDateFin()).isEqualTo(LocalDate.of(2018, 11, 11));
        assertThat(offre.getProfessionRecherchee()).isEqualTo("professionRecherchee");
        assertThat(offre.getAdresse()).isEqualTo("adresse");
        assertThat(offre.getEtablissement()).isEqualTo("etablissement");
        assertThat(offre.getCodePostal()).isEqualTo(59);

    }
}
