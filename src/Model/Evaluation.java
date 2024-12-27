package Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Evaluation {
    private final IntegerProperty idEvaluation;
    private final IntegerProperty idSoumission;
    private final IntegerProperty idEvaluateur;
    private final StringProperty avis;
    private final ObjectProperty<LocalDate> dateEvaluation;
    private final StringProperty titreArticle; // New field for article title

    public Evaluation(int idEvaluation, int idSoumission, int idEvaluateur, String avis, LocalDate dateEvaluation, String titreArticle) {
        this.idEvaluation = new SimpleIntegerProperty(idEvaluation);
        this.idSoumission = new SimpleIntegerProperty(idSoumission);
        this.idEvaluateur = new SimpleIntegerProperty(idEvaluateur);
        this.avis = new SimpleStringProperty(avis);
        this.dateEvaluation = new SimpleObjectProperty<>(dateEvaluation);
        this.titreArticle = new SimpleStringProperty(titreArticle);
    }

    // Existing getters and setters...
    public int getIdEvaluation() { return idEvaluation.get(); }
    public IntegerProperty idEvaluationProperty() { return idEvaluation; }
    public void setIdEvaluation(int idEvaluation) { this.idEvaluation.set(idEvaluation); }

    public int getIdSoumission() { return idSoumission.get(); }
    public IntegerProperty idSoumissionProperty() { return idSoumission; }
    public void setIdSoumission(int idSoumission) { this.idSoumission.set(idSoumission); }

    public int getIdEvaluateur() { return idEvaluateur.get(); }
    public IntegerProperty idEvaluateurProperty() { return idEvaluateur; }
    public void setIdEvaluateur(int idEvaluateur) { this.idEvaluateur.set(idEvaluateur); }

    public String getAvis() { return avis.get(); }
    public StringProperty avisProperty() { return avis; }
    public void setAvis(String avis) { this.avis.set(avis); }

    public LocalDate getDateEvaluation() { return dateEvaluation.get(); }
    public ObjectProperty<LocalDate> dateEvaluationProperty() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation.set(dateEvaluation); }

// New getter and setter for titreArticle
    public String getTitreArticle() { return titreArticle.get(); }
    public StringProperty titreArticleProperty() { return titreArticle; }
    public void setTitreArticle(String titreArticle) { this.titreArticle.set(titreArticle); }

}

    
