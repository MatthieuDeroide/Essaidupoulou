package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OffreDto {
    private String titre;
    private String description;
    private String datedebut;
    private String datefin;
    private String professionRecherche;
    public String adresse;
    public String etablissement;
    public String codePostal;
    public String auteurId;
    public String isAideOp;

    public Integer getAuteurId() {
        return Integer.parseInt(auteurId);
    }

    public String getIsAideOp() {
        return isAideOp;
    }

    public void setIsAideOp(String isAideOp) {
        this.isAideOp = isAideOp;
    }

    public void setAuteurId(String auteurId) {
        this.auteurId = auteurId;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public void setProfessionRecherche(String professionRecherchee) {
        this.professionRecherche = professionRecherchee;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTitre(){
        return titre;
    }

    public String getDescription(){
        return description;
    }

    public LocalDate getDateDebut(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  LocalDate.parse(datedebut, dateFormat);
    }

    public LocalDate getDateFin(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  LocalDate.parse(datefin, dateFormat);
    }

    public String getProfessionRecherche(){
        return professionRecherche;
    }

    public String getAdresse(){
        return adresse;
    }

    public  String getEtablissement(){
        return etablissement;
    }

    public  int getCodePostal(){
        return Integer.parseInt(codePostal);
    }
}
