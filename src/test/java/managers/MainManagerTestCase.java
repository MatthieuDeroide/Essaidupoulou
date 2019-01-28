package managers;


import dao.AdminDAO;
import dao.AideOperatoireDAO;
import dao.DocteurDAO;
import dao.OffreDAO;
import entities.Admin;
import entities.AideOperatoire;
import entities.Docteur;
import entities.Offre;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MainManagerTestCase {

    @Mock
    private DocteurDAO docteurDAO=new DocteurDAO();

    @Mock
    private AideOperatoireDAO aideOperatoireDAO=new AideOperatoireDAO();

    @Mock
    private OffreDAO offreDAO=new OffreDAO();


    @InjectMocks
    private MainManager mainManager=MainManager.getInstance();


    //TEST AIDEOP

    @Test
    public void shouldGetAideOperatoire(){
        //GIVEN
        AideOperatoire element=new AideOperatoire("Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Infirmier","coco.kleinsberg@gmail.com","0687653489","test1");
        element.setId(1);
        Mockito.when(aideOperatoireDAO.getAideOperatoire(element.getMail())).thenReturn(element);
        Mockito.when(aideOperatoireDAO.getAideOperatoire(element.getId())).thenReturn(element);

        //WHEN
        AideOperatoire resultMail=mainManager.getAideOperatoire("coco.kleinsberg@gmail.com");
        AideOperatoire resultId=mainManager.getAideOperatoire(1);

        //THEN
        assertThat(resultMail).isEqualTo(element);
        assertThat(resultId).isEqualTo(element);
    }

    @Test
    public void shouldListAideOperatoire(){
        //WHEN
        mainManager.listAideOperatoire();
        //THEN
        Mockito.verify(aideOperatoireDAO,Mockito.atLeastOnce()).listAideOperatoire();

    }

    @Test
    public void shouldAddAideOperatoire(){
        //GIVEN
        AideOperatoire aideOperatoire=new AideOperatoire("Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Infirmier","coco.kleinsberg@gmail.com","0687653489","test1");

        //WHEN
        mainManager.addAideOperatoire(aideOperatoire);

        //THEN
        Mockito.verify(aideOperatoireDAO,Mockito.atLeastOnce()).addAideOperatoire(aideOperatoire);
    }

    @Test
    public void shouldUpdateAideOperatoire(){
        //GIVEN
        AideOperatoire updatedAideOp = new AideOperatoire("Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Infirmier","coco.kleinsberg@gmail.com","0687653489","test1");
        updatedAideOp.setPays("Bénin");
        updatedAideOp.setVille("Kotonou");
        updatedAideOp.setAdresse("35 boulevard de la liberté");
        updatedAideOp.setCodePostal(23198);
        updatedAideOp.setNumeroTel("0657495749");
        Mockito.when(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com")).thenReturn(updatedAideOp);

        //WHEN
        mainManager.updateAideop(updatedAideOp);

        //THEN
        Mockito.verify(aideOperatoireDAO).updateAideop(updatedAideOp);
        assertEquals(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com").getVille(),"Kotonou");
        assertEquals(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com").getAdresse(),"35 boulevard de la liberté");
        Assertions.assertThat(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com").getCodePostal()).isEqualTo(23198);
        assertEquals(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com").getNumeroTel(),"0657495749");

    }



    @Test
    public void shouldUpdateAideOperatoirePassword() {

        //GIVEN
        AideOperatoire updatedAideOp = new AideOperatoire("Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Infirmier","coco.kleinsberg@gmail.com","0687653489","test1");
        updatedAideOp.setMdp("test1bis");
        Mockito.when(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com")).thenReturn(updatedAideOp);

        //WHEN
        mainManager.updateAideopPassword(updatedAideOp);

        //THEN
        Mockito.verify(aideOperatoireDAO).updateAideopPassword(updatedAideOp);
        assertEquals(aideOperatoireDAO.getAideOperatoire("coco.kleinsberg@gmail.com").getMdp(),"test1bis");

    }



    //TEST DOCTEUR

    @Test
    public void shouldGetDocteur(){
        //GIVEN
        Docteur element=new Docteur(true,"Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Infirmier","coco.kleinsberg@gmail.com","0687653489","test1");
        element.setId(1);
        Mockito.when(docteurDAO.getDocteur(element.getMail())).thenReturn(element);
        Mockito.when(docteurDAO.getDocteur(element.getId())).thenReturn(element);

        //WHEN
        Docteur resultMail=mainManager.getDocteur("coco.kleinsberg@gmail.com");
        Docteur resultId=mainManager.getDocteur(1);

        //THEN
        assertThat(resultMail).isEqualTo(element);
        assertThat(resultId).isEqualTo(element);

    }

    @Test
    public void shouldListDocteur(){
        //WHEN
        mainManager.listDocteur();
        //THEN
        Mockito.verify(docteurDAO,Mockito.atLeastOnce()).listDocteur();
    }

    @Test
    public void shouldAddDocteur(){
        //GIVEN
        Docteur docteur=new Docteur(true,"Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Chirurgien","coco.kleinsberg@gmail.com","0687653489","test1");

        //WHEN
        mainManager.addDocteur(docteur);

        //THEN
        Mockito.verify(docteurDAO,Mockito.atLeastOnce()).addDocteur(docteur);
    }

    @Test
    public void shouldUpdateDocteur(){
        //GIVEN
        Docteur updatedDocteur = new Docteur(true,"Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Chirurgien","coco.kleinsberg@gmail.com","0687653489","test1");
        updatedDocteur.setPays("Bénin");
        updatedDocteur.setVille("Kotonou");
        updatedDocteur.setAdresse("35 boulevard de la liberté");
        updatedDocteur.setCodePostal(23198);
        updatedDocteur.setNumeroTel("0657495749");
        Mockito.when(docteurDAO.getDocteur("coco.kleinsberg@gmail.com")).thenReturn(updatedDocteur);

        //WHEN
        mainManager.updateDocteur(updatedDocteur);

        //THEN
        Mockito.verify(docteurDAO).updateDocteur(updatedDocteur);
        assertEquals(docteurDAO.getDocteur("coco.kleinsberg@gmail.com").getVille(),"Kotonou");
        assertEquals(docteurDAO.getDocteur("coco.kleinsberg@gmail.com").getAdresse(),"35 boulevard de la liberté");
        Assertions.assertThat(docteurDAO.getDocteur("coco.kleinsberg@gmail.com").getCodePostal()).isEqualTo(23198);
        assertEquals(docteurDAO.getDocteur("coco.kleinsberg@gmail.com").getNumeroTel(),"0657495749");

    }

    @Test
    public void shouldUpdateDocteurPassword() {

        //GIVEN
        Docteur updatedDocteur = new Docteur(true,"Monsieur","Kleinsberg","Coco",LocalDate.of(1985,10,3),"France","Lille",59000,"15 rue Faidherbe","Chirurgien","coco.kleinsberg@gmail.com","0687653489","test1");
        updatedDocteur.setMdp("test1bis");
        Mockito.when(docteurDAO.getDocteur("coco.kleinsberg@gmail.com")).thenReturn(updatedDocteur);

        //WHEN
        mainManager.updateDocteurPassword(updatedDocteur);

        //THEN
        Mockito.verify(docteurDAO).updateDocteurPassword(updatedDocteur);
        assertEquals(docteurDAO.getDocteur("coco.kleinsberg@gmail.com").getMdp(),"test1bis");

    }



    //TEST OFFRES

    @Test
    public void shouldGetOffre(){
        //GIVEN
        Offre element=new Offre("testoffre","testtesttest",LocalDate.of(2018,11,28),LocalDate.of(2019,3,14),"Infirmier","54 avenue de Strasbourg","CHU Lille",59000);
        element.setId(1);
        Mockito.when(offreDAO.getOffre(element.getId())).thenReturn(element);


        //WHEN
        Offre result=mainManager.getOffre(1);

        //THEN
        assertThat(result).isEqualTo(element);

    }

    @Test
    public void shouldListOffre(){
        //WHEN
        mainManager.listoffre();
        //THEN
        Mockito.verify(offreDAO,Mockito.atLeastOnce()).listOffre();

    }

    @Test
    public void shouldAddOffre(){
        //GIVEN
        Offre offre=new Offre("testoffre","testtesttest",LocalDate.of(2018,11,28),LocalDate.of(2019,3,14),"Infirmier","54 avenue de Strasbourg","CHU Lille",59000);
        offre.setId(1);

        //WHEN
        mainManager.addOffre(offre,1,true);

        //THEN
        Mockito.verify(offreDAO,Mockito.atLeastOnce()).addOffre(offre,1,true);
    }

    @Test
    public void shouldDeleteOffre(){
        //GIVEN
        Offre offre = new Offre("testoffre","testtesttest",LocalDate.of(2018,11,28),LocalDate.of(2019,3,14),"Infirmier","54 avenue de Strasbourg","CHU Lille",59000);
        offre.setId(1);
        Mockito.when(offreDAO.getOffre(offre.getId())).thenReturn(offre);

        //WHEN
        mainManager.deleteOffre(1);

        //THEN
        Mockito.verify(offreDAO,times(1)).deleteOffre(1);

    }

    @Test(expected = UserNotFoundException.class)
    public void  shouldNotGetAideOpByIdAndThrowException() throws UserNotFoundException{
        //WHEN
        mainManager.getAideOperatoire(99);

        //THEN
        fail("");
    }

    @Test(expected = UserNotFoundException.class)
    public void  shouldNotGetAideOpByMailAndThrowException() throws UserNotFoundException{
        //WHEN
        mainManager.getAideOperatoire("n'importequoi");

        //THEN
        fail("");
    }



}




