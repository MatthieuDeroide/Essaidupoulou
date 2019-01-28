package entities;

import java.time.LocalDate;
import java.util.Date;

public class Docteur {
    private Integer id;
    private boolean professeur;
    private String civilite;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String pays;
    private String ville;
    private Integer codePostal;
    private String adresse;
    private String posteOccupe;
    private String mail;
    private String numeroTel;
    private String mdp;


    public Docteur(boolean professeur, String civilite, String nom, String prenom,
                   LocalDate dateNaissance, String pays, String ville, Integer codePostal, String adresse,
                   String posteOccupe, String mail, String numeroTel, String mdp) {
        this.id = null;
        this.professeur = professeur;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
        this.ville = ville;
        this.codePostal = codePostal;
        this.adresse = adresse;
        this.posteOccupe = posteOccupe;
        this.mail = mail;
        this.numeroTel = numeroTel;
        this.mdp = mdp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String isProfesseur() {
        if (professeur) return "Professeur";
        else return "Docteur";
    }

    public void setProfesseur(boolean professeur) {
        this.professeur = professeur;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPosteOccupe() {
        return posteOccupe;
    }

    public void setPosteOccupe(String posteOccupe) {
        this.posteOccupe = posteOccupe;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
