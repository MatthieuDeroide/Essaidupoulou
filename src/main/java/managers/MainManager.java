package managers;


import dao.AideOperatoireDAO;
import dao.DocteurDAO;
import dao.OffreDAO;
import entities.AideOperatoire;
import entities.Docteur;
import entities.Offre;
import exceptions.OfferAlreadyExistsException;
import exceptions.OfferNotFoundException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import javax.print.Doc;
import java.util.List;

public class MainManager {
    private static class MainManagerHolder {
        private static MainManager instance = new MainManager();
    }

    public static MainManager getInstance() {
        return MainManagerHolder.instance;
    }

    private MainManager() {
    }

    //DAO AIDEOP

    private DocteurDAO docteurDAO = new DocteurDAO();
    private AideOperatoireDAO aideOperatoireDAO = new AideOperatoireDAO();
    private OffreDAO offreDAO=new OffreDAO();

    public AideOperatoire getAideOperatoire(Integer id) {
        if (aideOperatoireDAO.getAideOperatoire(id)==null){
            throw new UserNotFoundException(String.format("User with the id %d cannot be found",id));
        }
        return aideOperatoireDAO.getAideOperatoire(id);
    }
    public AideOperatoire getAideOperatoire(String mail) {
        AideOperatoire aideOperatoire = aideOperatoireDAO.getAideOperatoire(mail);
        if (aideOperatoire==null){
            throw new UserNotFoundException(String.format("User with the mail %s cannot be found",mail));
        }
        return aideOperatoire;
    }

    public List<AideOperatoire> listAideOperatoire() {
        return aideOperatoireDAO.listAideOperatoire();
    }

    public void addAideOperatoire(AideOperatoire aideOperatoire) {
        checkAideop(aideOperatoire);
        if (aideOperatoireDAO.getAideOperatoire(aideOperatoire.getMail())!=null){
            throw new UserAlreadyExistsException("An user already exists with this mail");
        }
        aideOperatoireDAO.addAideOperatoire(aideOperatoire);
    }

    public void updateAideop(AideOperatoire aideOperatoire){
        checkAideop(aideOperatoire);
        if (aideOperatoireDAO.getAideOperatoire(aideOperatoire.getMail())==null){
            throw new UserNotFoundException("Can't update an user that does not exists");
        }
        aideOperatoireDAO.updateAideop(aideOperatoire);
    }

    public void updateAideopPassword(AideOperatoire aideOperatoire){
        checkAideop(aideOperatoire);
        if (aideOperatoireDAO.getAideOperatoire(aideOperatoire.getMail())==null){
            throw new UserNotFoundException("Can't update a password for an user that does not exists");
        }
        aideOperatoireDAO.updateAideopPassword(aideOperatoire);
    }

    public void deleteAideop(Integer id){
        if (id == null){
            throw new IllegalArgumentException("L'id doit être rentré");
        }
        if (aideOperatoireDAO.getAideOperatoire(id) == null){
            throw  new UserNotFoundException("Pas d'utilisateur sous cet id");
        }
        aideOperatoireDAO.deleteAideOperatoire(id);
    }

    private void checkAideop(AideOperatoire aideOperatoire){
        if (aideOperatoire==null){
            throw new IllegalArgumentException("An aideop must be defined");
        }
    }


    //DAO DOCTEUR

    public List<Docteur> listDocteur() {
        return docteurDAO.listDocteur();
    }

    public void addDocteur(Docteur docteur) {
        checkDocteur(docteur);
        if(docteurDAO.getDocteur(docteur.getMail())!=null){
            throw new UserAlreadyExistsException("An user already exists with this mail");
        }
        docteurDAO.addDocteur(docteur);
    }

    public Docteur getDocteur(String mail) {
        if (docteurDAO.getDocteur(mail)==null){
            throw new UserNotFoundException(String.format("User with the mail %s cannot be found",mail));
        }
        return docteurDAO.getDocteur(mail);
    }

    public Docteur getDocteur(Integer id) {
        if (docteurDAO.getDocteur(id)==null){
            throw new UserNotFoundException(String.format("User with the mail %d cannot be found",id));
        }
        return docteurDAO.getDocteur(id);
    }

    public void updateDocteur(Docteur docteur){
        checkDocteur(docteur);
        if(docteurDAO.getDocteur(docteur.getMail())==null){
            throw new UserNotFoundException("Can't update an user that does not exists");
        }
        docteurDAO.updateDocteur(docteur);
    }

    public void updateDocteurPassword(Docteur docteur){
        checkDocteur(docteur);
        if (docteurDAO.getDocteur(docteur.getMail())==null){
            throw new UserNotFoundException("Can't update a password for an user that does not exists");
        }
        docteurDAO.updateDocteurPassword(docteur);
    }

    private void checkDocteur(Docteur docteur){
        if (docteur==null) {
            throw new IllegalArgumentException("A Doctor must be provided");
        }
    }

    public void deleteDocteur(Integer id ){
        if (id == null){
            throw new IllegalArgumentException("Un id doit être rentré");
        }
        if (docteurDAO.getDocteur(id) == null){
            throw new UserNotFoundException("On ne peut supprimer un utilisateur qui n'existe pas");
        }
        docteurDAO.deleteDocteur(id);
    }



        //MANAGER DES OFFRES
        public List<Offre> listoffre () { return offreDAO.listOffre();}

        public List<Offre> listoffre (Integer profession, Integer userId) { return offreDAO.listOffre(profession,userId);}

    public Offre getOffre(int id) {
        if (offreDAO.getOffre(id)==null){
            throw new OfferNotFoundException(String.format("Offer with the id %d cannot be found",id));
        }
        return offreDAO.getOffre(id);
    }

    public Offre addOffre(Offre offre, Integer auteurId,Boolean isAideOp) {
        checkOffer(offre);

        return offreDAO.addOffre(offre,auteurId,isAideOp);
    }

    public void deleteOffre(Integer idOffre) {
        if (idOffre == null){
            throw  new IllegalArgumentException("Pas d'offre sous cet id");
        }
        if (offreDAO.getOffre(idOffre)==null){
            throw new OfferNotFoundException(String.format("Offer with the id %d cannot be found so it can't be deleted",idOffre));
        }
        offreDAO.deleteOffre(idOffre);
    }

    private void checkOffer(Offre offre){
        if (offre==null){
            throw new IllegalArgumentException("An offer must be provided");
        }
    }

}
