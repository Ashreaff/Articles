package Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Soumission {
    private final IntegerProperty idSoumission;
    private final IntegerProperty idArticle;
    private final IntegerProperty idCorrespondant;
    private final ObjectProperty<LocalDate> dateSoumission;
    private final IntegerProperty idEvaluateur;

    // Propriétés supplémentaires pour les informations jointes
    private final StringProperty titreArticle;
    private final StringProperty statut;

    public Soumission(int idSoumission, int idArticle, int idCorrespondant, LocalDate dateSoumission, Integer idEvaluateur, String titreArticle, String statut) {
        this.idSoumission = new SimpleIntegerProperty(idSoumission);
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.idCorrespondant = new SimpleIntegerProperty(idCorrespondant);
        this.dateSoumission = new SimpleObjectProperty<>(dateSoumission);
        this.idEvaluateur = new SimpleIntegerProperty(idEvaluateur != null ? idEvaluateur : 0);
        this.titreArticle = new SimpleStringProperty(titreArticle);
        this.statut = new SimpleStringProperty(statut);
    }

    // Getters et setters pour toutes les propriétés
    public int getIdSoumission() { return idSoumission.get(); }
    public IntegerProperty idSoumissionProperty() { return idSoumission; }
    public void setIdSoumission(int idSoumission) { this.idSoumission.set(idSoumission); }

    public int getIdArticle() { return idArticle.get(); }
    public IntegerProperty idArticleProperty() { return idArticle; }
    public void setIdArticle(int idArticle) { this.idArticle.set(idArticle); }

    public int getIdCorrespondant() { return idCorrespondant.get(); }
    public IntegerProperty idCorrespondantProperty() { return idCorrespondant; }
    public void setIdCorrespondant(int idCorrespondant) { this.idCorrespondant.set(idCorrespondant); }

    public LocalDate getDateSoumission() { return dateSoumission.get(); }
    public ObjectProperty<LocalDate> dateSoumissionProperty() { return dateSoumission; }
    public void setDateSoumission(LocalDate dateSoumission) { this.dateSoumission.set(dateSoumission); }

    public int getIdEvaluateur() { return idEvaluateur.get(); }
    public IntegerProperty idEvaluateurProperty() { return idEvaluateur; }
    public void setIdEvaluateur(int idEvaluateur) { this.idEvaluateur.set(idEvaluateur); }

    public String getTitreArticle() { return titreArticle.get(); }
    public StringProperty titreArticleProperty() { return titreArticle; }
    public void setTitreArticle(String titreArticle) { this.titreArticle.set(titreArticle); }

    public String getStatut() { return statut.get(); }
    public StringProperty statutProperty() { return statut; }
    public void setStatut(String statut) { this.statut.set(statut); }
}

