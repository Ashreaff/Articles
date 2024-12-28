package Model;

import javafx.beans.property.*;

public class Article {
    private final IntegerProperty idArticle;
    private final StringProperty titre;
    private final IntegerProperty idAuteur;
    private final StringProperty resume;
    private final IntegerProperty taille;
    private final StringProperty contenu;
    private final BooleanProperty estCourt;
    



    
    public Article(int idArticle, String titre, int idAuteur, String resume, int taille, 
                   String contenu, boolean estCourt, boolean affecter) {
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.titre = new SimpleStringProperty(titre);
        this.idAuteur = new SimpleIntegerProperty(idAuteur);
        this.resume = new SimpleStringProperty(resume);
        this.taille = new SimpleIntegerProperty(taille);
        this.contenu = new SimpleStringProperty(contenu);
        this.estCourt = new SimpleBooleanProperty(estCourt);
    }

    public Article(String titre, int idAuteur, String resume, int taille, String contenu, 
                   boolean estCourt, boolean affecter) {
        this.idArticle = new SimpleIntegerProperty(0); 
        this.titre = new SimpleStringProperty(titre);
        this.idAuteur = new SimpleIntegerProperty(idAuteur);
        this.resume = new SimpleStringProperty(resume);
        this.taille = new SimpleIntegerProperty(taille);
        this.contenu = new SimpleStringProperty(contenu);
        this.estCourt = new SimpleBooleanProperty(estCourt);
    }
   


    
    // Getters and setters for all properties
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

    public String getContenu() { return contenu.get(); }
    public StringProperty contenuProperty() { return contenu; }
    public void setContenu(String contenu) { this.contenu.set(contenu); }

    public boolean isEstCourt() { return estCourt.get(); }
    public BooleanProperty estCourtProperty() { return estCourt; }
    public void setEstCourt(boolean estCourt) { this.estCourt.set(estCourt); }

}
