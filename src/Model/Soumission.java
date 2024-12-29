package Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Soumission {
    private final IntegerProperty idSoumission;
    private final IntegerProperty idArticle;
    private final IntegerProperty idCorrespondant;
    private final ObjectProperty<LocalDate> dateSoumission;
    private final BooleanProperty affecter;
<<<<<<< HEAD
    private final StringProperty titre;  // Nouvelle propriété pour le titre de l'article
    private final IntegerProperty taille;  // Nouvelle propriété pour la taille de l'article

    // Constructeur mis à jour
    public Soumission(int idSoumission, int idArticle, int idCorrespondant, LocalDate dateSoumission, 
                      boolean affecter, String titre, int taille) {
=======
    private final StringProperty titre;
    private final IntegerProperty taille;

    public Soumission(int idSoumission, int idArticle, int idCorrespondant, LocalDate dateSoumission, boolean affecter, String titre, int taille) {
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
        this.idSoumission = new SimpleIntegerProperty(idSoumission);
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.idCorrespondant = new SimpleIntegerProperty(idCorrespondant);
        this.dateSoumission = new SimpleObjectProperty<>(dateSoumission);
        this.affecter = new SimpleBooleanProperty(affecter);
<<<<<<< HEAD
        this.titre = new SimpleStringProperty(titre);  // Initialisation du titre
        this.taille = new SimpleIntegerProperty(taille);  // Initialisation de la taille
    }

=======
        this.titre = new SimpleStringProperty(titre);
        this.taille = new SimpleIntegerProperty(taille);
    }

    public Soumission(int idArticle, int idCorrespondant, LocalDate dateSoumission, boolean affecter, String titre, int taille) {
        this.idSoumission = new SimpleIntegerProperty(0);
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.idCorrespondant = new SimpleIntegerProperty(idCorrespondant);
        this.dateSoumission = new SimpleObjectProperty<>(dateSoumission);
        this.affecter = new SimpleBooleanProperty(affecter);
        this.titre = new SimpleStringProperty(titre);
        this.taille = new SimpleIntegerProperty(taille);
    }

    public Soumission() {
        this.idSoumission = new SimpleIntegerProperty(0);
        this.idArticle = new SimpleIntegerProperty(0);
        this.idCorrespondant = new SimpleIntegerProperty(0);
        this.dateSoumission = new SimpleObjectProperty<>(LocalDate.now());
        this.affecter = new SimpleBooleanProperty(false);
        this.titre = new SimpleStringProperty("");
        this.taille = new SimpleIntegerProperty(0);
    }

    // Getters and setters for all properties
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
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

<<<<<<< HEAD
    public boolean isAffecter() { return affecter.get(); }  
    public BooleanProperty affecterProperty() { return affecter; }  
    public void setAffecter(boolean affecter) { this.affecter.set(affecter); }

    public String getTitre() { return titre.get(); }  // Getter pour le titre
    public StringProperty titreProperty() { return titre; }  // Propriété pour le titre
    public void setTitre(String titre) { this.titre.set(titre); }  // Setter pour le titre

    public int getTaille() { return taille.get(); }  // Getter pour la taille
    public IntegerProperty tailleProperty() { return taille; }  // Propriété pour la taille
    public void setTaille(int taille) { this.taille.set(taille); }  // Setter pour la taille
=======
    public boolean isAffecter() { return affecter.get(); }
    public BooleanProperty affecterProperty() { return affecter; }
    public void setAffecter(boolean affecter) { this.affecter.set(affecter); }

    public String getTitre() { return titre.get(); }
    public StringProperty titreProperty() { return titre; }
    public void setTitre(String titre) { this.titre.set(titre); }

    public int getTaille() { return taille.get(); }
    public IntegerProperty tailleProperty() { return taille; }
    public void setTaille(int taille) { this.taille.set(taille); }
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
}
