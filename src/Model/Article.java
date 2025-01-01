package Model;

import javafx.beans.property.*;

public class Article {
    private final IntegerProperty idArticle;
    private final StringProperty titre;
    private final IntegerProperty idAuteur;
    private final StringProperty resume;
    private final IntegerProperty taille;
    private final StringProperty motsCle;
    private final BooleanProperty estCourt;
    private final StringProperty pdfFilePath;

    public Article(int idArticle, String titre, int idAuteur, String resume, int taille, 
                   String motsCle, boolean estCourt, String pdfFilePath) {
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.titre = new SimpleStringProperty(titre);
        this.idAuteur = new SimpleIntegerProperty(idAuteur);
        this.resume = new SimpleStringProperty(resume);
        this.taille = new SimpleIntegerProperty(taille);
        this.motsCle = new SimpleStringProperty(motsCle);
        this.estCourt = new SimpleBooleanProperty(estCourt);
        this.pdfFilePath = new SimpleStringProperty(pdfFilePath);
    }

    // Getters and setters...

    public int getIdArticle() { return idArticle.get(); }
    public IntegerProperty idArticleProperty() { return idArticle; }
    public void setIdArticle(int idArticle) { this.idArticle.set(idArticle); }

    public String getTitre() { return titre.get(); }
    public StringProperty titreProperty() { return titre; }
    public void setTitre(String titre) { this.titre.set(titre); }

    public int getIdAuteur() { return idAuteur.get(); }
    public IntegerProperty idAuteurProperty() { return idAuteur; }
    public void setIdAuteur(int idAuteur) { this.idAuteur.set(idAuteur); }

    public String getResume() { return resume.get(); }
    public StringProperty resumeProperty() { return resume; }
    public void setResume(String resume) { this.resume.set(resume); }

    public int getTaille() { return taille.get(); }
    public IntegerProperty tailleProperty() { return taille; }
    public void setTaille(int taille) { this.taille.set(taille); }

    public String getMotsCle() { return motsCle.get(); }
    public StringProperty motsCleProperty() { return motsCle; }
    public void setMotsCle(String motsCle) { this.motsCle.set(motsCle); }

    public boolean isEstCourt() { return estCourt.get(); }
    public BooleanProperty estCourtProperty() { return estCourt; }
    public void setEstCourt(boolean estCourt) { this.estCourt.set(estCourt); }

    public String getPdfFilePath() { return pdfFilePath.get(); }
    public StringProperty pdfFilePathProperty() { return pdfFilePath; }
    public void setPdfFilePath(String pdfFilePath) { this.pdfFilePath.set(pdfFilePath); }
}

