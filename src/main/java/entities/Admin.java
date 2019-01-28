package entities;

public class Admin {
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;


    public Admin(Integer id, String nom, String prenom, String mail, String mdp) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.mdp=mdp;
    }

    public Admin( String nom, String prenom, String mail, String mdp) {
        this.id=null;
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.mdp=mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
