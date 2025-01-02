package Model;

public class Revue {
    private int idRevue;
    private int idEditeur;
    private int idEvaluation;
    private String decision;  

    public Revue(int idRevue, int idEditeur, int idEvaluation, String decision) {
        this.idRevue = idRevue;
        this.idEditeur = idEditeur;
        this.idEvaluation = idEvaluation;
        this.decision = decision;
    }

    // Getters
    public int getIdRevue() {
        return idRevue;
    }

    public int getIdEditeur() {
        return idEditeur;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public String getDecision() {
        return decision;
    }

    // Setters
    public void setIdRevue(int idRevue) {
        this.idRevue = idRevue;
    }

    public void setIdEditeur(int idEditeur) {
        this.idEditeur = idEditeur;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
