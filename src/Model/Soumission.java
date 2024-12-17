package Model;

import java.util.Date;


public class Soumission {

    private int idSoumission;
    private Date dateSoumission;
    private String decisionEditeur;
    private int idEditeur;
    private int idAuteurCorrespondant;
    private int idArticle;
    private int etat; // 0 : En cours, 1 : Soumis, 2 : Annulé

    public Soumission(int idSoumission, Date dateSoumission, String decisionEditeur, int idEditeur,
                      int idAuteurCorrespondant, int idArticle, int etat) {
        this.idSoumission = idSoumission;
        this.dateSoumission = dateSoumission;
        this.decisionEditeur=decisionEditeur;
        this.idEditeur = idEditeur;
        this.idAuteurCorrespondant = idAuteurCorrespondant;
        this.idArticle = idArticle;
        this.etat =etat;
    }

    public int getIdSoumission() {
        return idSoumission;
    }

    public void setIdSoumission(int idSoumission) {
        this.idSoumission = idSoumission;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getDecisionEditeur() {
        return decisionEditeur;
    }

    public void setDecisionEditeur(String decisionEditeur) {
        if (decisionEditeur == null || decisionEditeur.isEmpty()) {
            throw new IllegalArgumentException("La décision de l'éditeur ne peut pas être nulle ou vide.");
        }
        this.decisionEditeur = decisionEditeur;
    }

    public int getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(int idEditeur) {
        this.idEditeur = idEditeur;
    }

    public int getIdAuteurCorrespondant() {
        return idAuteurCorrespondant;
    }

    public void setIdAuteurCorrespondant(int idAuteurCorrespondant) {
        this.idAuteurCorrespondant = idAuteurCorrespondant;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        if (etat < 0 || etat > 2) {
            throw new IllegalArgumentException("État invalide. Les valeurs autorisées sont 0, 1 ou 2.");
        }
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Soumission{" +
                "idSoumission=" + idSoumission +
                ", dateSoumission=" + dateSoumission +
                ", decisionEditeur='" + decisionEditeur + '\'' +
                ", idEditeur=" + idEditeur +
                ", idAuteurCorrespondant=" + idAuteurCorrespondant +
                ", idArticle=" + idArticle +
                ", etat=" + etat +
                '}';
    }
}
