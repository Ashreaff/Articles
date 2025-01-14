package Model;

public class Revue {
    private int idRevue;
    private int idEvaluation;
    private String decision;

    public Revue(int idRevue, int idEvaluation, String decision) {
        this.idRevue = idRevue;
        this.idEvaluation = idEvaluation;
        this.decision = decision;
    }

    // Getters
    public int getIdRevue() {
        return idRevue;
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

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }


}
