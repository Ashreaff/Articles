package Model;


public class Article {

    private int idArticle;
    private String titre;
    private String resumer;
    private String motCles;
    private String auteurs;
    private String categorie;
    private String cheminImage;
    private String remarque;
    private String etat;
    private String contenu;
    private String avis;
    private int nombreRevision = 0;
    private String nouvelleVersion = " ";

    public Article(int idArticle, String titre, String resumer, String auteurs, String categorie, 
                   String cheminImage, String remarque, String etat, String contenu, String avis) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.resumer = resumer;
        this.auteurs = auteurs;
        this.categorie = categorie;
        this.cheminImage = cheminImage;
        this.remarque = remarque;
        this.etat = etat;
        this.contenu = contenu;
        this.avis = avis;
    }

    public Article(int idArticle, String titre, String resumer) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.resumer = resumer;
    }

    public Article(int idArticle, String remarque) {
        this.idArticle = idArticle;
        this.remarque = remarque;
    }

    public Article() {
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        }
        this.titre = titre;
    }

    public String getResumer() {
        return resumer;
    }

    public void setResumer(String resumer) {
        if (resumer == null || resumer.trim().isEmpty()) {
            throw new IllegalArgumentException("Le résumé ne peut pas être vide.");
        }
        this.resumer = resumer;
    }

    public String getMotCles() {
        return motCles;
    }

    public void setMotCles(String motCles) {
        this.motCles = motCles;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(String auteurs) {
        this.auteurs = auteurs;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getNombreRevision() {
        return nombreRevision;
    }

    public void setNombreRevision(int nombreRevision) {
        this.nombreRevision = nombreRevision;
    }

    public String getNouvelleVersion() {
        return nouvelleVersion;
    }

    public void setNouvelleVersion(String nouvelleVersion) {
        this.nouvelleVersion = nouvelleVersion;
    }

    // Méthode pour incrémenter le nombre de révisions
    public void incrementerRevision() {
        this.nombreRevision++;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", titre='" + titre + '\'' +
                ", resumer='" + resumer + '\'' +
                ", motCles='" + motCles + '\'' +
                ", auteurs='" + auteurs + '\'' +
                ", categorie='" + categorie + '\'' +
                ", cheminImage='" + cheminImage + '\'' +
                ", remarque='" + remarque + '\'' +
                ", etat='" + etat + '\'' +
                ", contenu='" + contenu + '\'' +
                ", avis='" + avis + '\'' +
                ", nombreRevision=" + nombreRevision +
                ", nouvelleVersion='" + nouvelleVersion + '\'' +
                '}';
    }
}
