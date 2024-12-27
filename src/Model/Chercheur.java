package Model;

public class Chercheur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String password;
    private String Domaine;
    private String Institution;
    private String Role;
    
    public Chercheur(int id, String nom, String prenom, String email, String adresse, String password, String domaine,
            String institution, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
        Domaine = domaine;
        Institution = institution;
        Role = role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDomaine() {
        return Domaine;
    }
    public void setDomaine(String domaine) {
        Domaine = domaine;
    }
    public String getInstitution() {
        return Institution;
    }
    public void setInstitution(String institution) {
        Institution = institution;
    }
    public String getRole() {
        return Role;
    }
    public void setRole(String role) {
        Role = role;
    };





}
