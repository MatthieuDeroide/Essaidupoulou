package entities;

import java.time.LocalDate;

public class Offre {

    private Integer id;
    private String titre;
    private String description;
    private LocalDate datedebut;
    private LocalDate datefin;
    private String professionRecherchee;
    private String adresse;
    private String etablissement;
    private int codePostal;
    private Integer auteurId;
    private boolean isAideOp;


    public Offre(String titre, String description, LocalDate datedebut, LocalDate datefin, String professionRecherchee, String adresse, String etablissement, int codePostal) {

        this.id = null;
        this.titre = titre;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.professionRecherchee = professionRecherchee;
        this.adresse = adresse;
        this.etablissement = etablissement;
        this.codePostal = codePostal;
        this.auteurId = null;
        this.isAideOp = false;
    }

    public Offre(OffreDto offreDto){
        this.id = null;
        this.titre = offreDto.getTitre();
        this.description = offreDto.getDescription();
        this.datedebut = offreDto.getDateDebut();
        this.datefin = offreDto.getDateFin();
        this.professionRecherchee = offreDto.getProfessionRecherche();
        this.adresse = offreDto.getAdresse();
        this.etablissement = offreDto.getEtablissement();
        this.codePostal = offreDto.getCodePostal();
        this.auteurId = offreDto.getAuteurId();
        this.isAideOp = Boolean.parseBoolean(offreDto.getIsAideOp());
    }

    public boolean isAideOp() {
        return isAideOp;
    }

    public void setAideOp(boolean aideOp) {
        isAideOp = aideOp;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return datedebut;
    }

    public void setDateDebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDateFin() {
        return datefin;
    }

    public void setDateFin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public String getProfessionRecherchee() {
        return professionRecherchee;
    }

    public void setProfessionRecherchee(String professionRecherchee) {
        this.professionRecherchee = professionRecherchee;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public Integer getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(Integer auteurId) {
        this.auteurId = auteurId;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public Boolean getAideOp() {
        return isAideOp;
    }

    public void setAideOp(Boolean aideOp) {
        isAideOp = aideOp;
    }
}