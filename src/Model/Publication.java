package Model;


public class Publication {

    private int idPublication;
    private String nomFichier;
    private String idSoumission;

    public Publication(int idPublication, String nomFichier, String idSoumission) {
        this.idPublication = idPublication;
        this.nomFichier =nomFichier;
        this.idSoumission = idSoumission;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        if (nomFichier == null || nomFichier.isEmpty()) {
            throw new IllegalArgumentException("Le nom du fichier ne peut pas être nul ou vide.");
        }
        this.nomFichier = nomFichier;
    }

    public String getIdSoumission() {
        return idSoumission;
    }

    public void setIdSoumission(String idSoumission) {
        if (idSoumission == null || idSoumission.isEmpty()) {
            throw new IllegalArgumentException("L'ID de soumission ne peut pas être nul ou vide.");
        }
        this.idSoumission = idSoumission;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "idPublication=" + idPublication +
                ", nomFichier='" + nomFichier + '\'' +
                ", idSoumission='" + idSoumission + '\'' +
                '}';
    }
}
