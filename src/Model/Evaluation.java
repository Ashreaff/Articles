package Model;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.List;

public class Evaluation {
    private final IntegerProperty idEvaluation;
    private final IntegerProperty idSoumission;
    private final StringProperty avis;
    private final ObjectProperty<LocalDate> dateEvaluation;
    private final BooleanProperty evaluer; 
    private List<EvaluateurInfo> evaluateurs; 

    public Evaluation(int idEvaluation, int idSoumission, String avis, LocalDate dateEvaluation, boolean evaluer, List<EvaluateurInfo> evaluateurs) {
        this.idEvaluation = new SimpleIntegerProperty(idEvaluation);
        this.idSoumission = new SimpleIntegerProperty(idSoumission);
        this.avis = new SimpleStringProperty(avis);
        this.dateEvaluation = new SimpleObjectProperty<>(dateEvaluation);
        this.evaluer = new SimpleBooleanProperty(evaluer);
        this.evaluateurs = evaluateurs; 
    }

    public int getIdEvaluation() { return idEvaluation.get(); }
    public IntegerProperty idEvaluationProperty() { return idEvaluation; }
    public void setIdEvaluation(int idEvaluation) { this.idEvaluation.set(idEvaluation); }

    public int getIdSoumission() { return idSoumission.get(); }
    public IntegerProperty idSoumissionProperty() { return idSoumission; }
    public void setIdSoumission(int idSoumission) { this.idSoumission.set(idSoumission); }

    public String getAvis() { return avis.get(); }
    public StringProperty avisProperty() { return avis; }
    public void setAvis(String avis) { this.avis.set(avis); }

    public LocalDate getDateEvaluation() { return dateEvaluation.get(); }
    public ObjectProperty<LocalDate> dateEvaluationProperty() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation.set(dateEvaluation); }

    public boolean isEvaluer() { return evaluer.get(); }
    public BooleanProperty evaluerProperty() { return evaluer; }
    public void setEvaluer(boolean evaluer) { this.evaluer.set(evaluer); }

    public List<EvaluateurInfo> getEvaluateurs() {
        return evaluateurs;
    }
    public void setEvaluateurs(List<EvaluateurInfo> evaluateurs) {
        this.evaluateurs = evaluateurs;
    }
}
