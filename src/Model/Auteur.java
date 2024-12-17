package Model;

public class Auteur extends Utilisateur {
    private int institutionAuteur;
    private boolean estCorrespondant;

    // Constructeur
    public Auteur(int id, String nom, String prenom, String adresse, String userName, String password, int institutionAuteur, boolean estCorrespondant) {
        super(id, nom, prenom, adresse, userName, password);
        this.institutionAuteur = institutionAuteur;
        this.estCorrespondant = estCorrespondant;
    }

    // Getters et Setters
    public int getInstitutionAuteur() {
        return institutionAuteur;
    }

    public void setInstitutionAuteur(int institutionAuteur) {
        this.institutionAuteur = institutionAuteur;
    }

    public boolean isCorrespondant() {
        return estCorrespondant;
    }

    public void setCorrespondant(boolean estCorrespondant) {
        this.estCorrespondant = estCorrespondant;
    }
}
