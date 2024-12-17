package Model;


public class Revue {

    private int idRevue;
    private String nomRevue;
    private int idEditeur;

    public Revue(int idRevue, String nomRevue, int idEditeur) {
        this.idRevue = idRevue;
        this.nomRevue=nomRevue;
        this.idEditeur = idEditeur;
    }

    public int getIdRevue() {
        return idRevue;
    }

    public void setIdRevue(int idRevue) {
        this.idRevue = idRevue;
    }

    public String getNomRevue() {
        return nomRevue;
    }

    public void setNomRevue(String nomRevue) {
        if (nomRevue == null || nomRevue.isEmpty()) {
            throw new IllegalArgumentException("Le nom de la revue ne peut pas être nul ou vide.");
        }
        this.nomRevue = nomRevue;
    }

    public int getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(int idEditeur) {
        this.idEditeur = idEditeur;
    }

    @Override
    public String toString() {
        return "Revue{" +
                "idRevue=" + idRevue +
                ", nomRevue='" + nomRevue + '\'' +
                ", idEditeur=" + idEditeur +
                '}';
    }
}
