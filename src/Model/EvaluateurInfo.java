package Model;

public class EvaluateurInfo {
    private int idEvaluateur;
    private String nom;
    private String prenom;
    private String avis;
    private  String Remarque;

    public EvaluateurInfo(int idEvaluateur, String nom, String prenom, String avis, String Remarque) {
        this.idEvaluateur = idEvaluateur;
        this.nom = nom;
        this.prenom = prenom;
        this.avis = avis;
        this.Remarque=Remarque;
    }

    public int getIdEvaluateur() {
        return idEvaluateur;
    }

    public void setIdEvaluateur(int idEvaluateur) {
        this.idEvaluateur = idEvaluateur;
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

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        Remarque = remarque;
    }

}
