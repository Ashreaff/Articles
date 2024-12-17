package Model;

import java.util.Date;

public class Evaluation {

    private int idEvaluation;      
    private String avis;           
    private Date dateRevision;    
    private String remarques;      
    private int nombreRevision;    
    private int idEvaluateur;      
    private int idArticle;         
    private int nouvelleNotification; 

    
    public Evaluation() {
        
    }

    public Evaluation(int idEvaluation, String avis, Date dateRevision, String remarques, 
                      int nombreRevision, int idEvaluateur, int idArticle, int nouvelleNotification) {
        this.idEvaluation = idEvaluation;
        this.avis = avis;
        this.dateRevision = dateRevision;
        this.remarques = remarques;
        this.nombreRevision = nombreRevision;
        this.idEvaluateur = idEvaluateur;
        this.idArticle = idArticle;
        this.nouvelleNotification = nouvelleNotification;
    }

    public Evaluation(String avis, String remarques, int idArticle, int idEvaluateur, int nouvelleNotification) {
        this.avis = avis;
        this.remarques = remarques;
        this.idArticle = idArticle;
        this.idEvaluateur = idEvaluateur;
        this.nouvelleNotification = nouvelleNotification;
    }

    // Getters et Setters
    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Date getDateRevision() {
        return dateRevision;
    }

    public void setDateRevision(Date dateRevision) {
        this.dateRevision = dateRevision;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public int getNombreRevision() {
        return nombreRevision;
    }

    public void incrementerNombreRevision() {
        this.nombreRevision++;
    }

    public int getIdEvaluateur() {
        return idEvaluateur;
    }

    public void setIdEvaluateur(int idEvaluateur) {
        this.idEvaluateur = idEvaluateur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getNouvelleNotification() {
        return nouvelleNotification;
    }

    public void setNouvelleNotification(int nouvelleNotification) {
        this.nouvelleNotification = nouvelleNotification;
    }

    @Override
    public String toString() {
        return "Évaluation [idEvaluation=" + idEvaluation + ", avis=" + avis + ", dateRevision=" + dateRevision
                + ", remarques=" + remarques + ", nombreRevision=" + nombreRevision + ", idEvaluateur=" + idEvaluateur
                + ", idArticle=" + idArticle + ", nouvelleNotification=" + nouvelleNotification + "]";
    }
}
