package Model;

public class Auteur extends Chercheur {
    public Auteur(int idAuteur, String nom, String prenom, String mail, String adresse, String password, 
                   String domaine, String institution) {
        super(idAuteur, nom, prenom, mail, password, adresse, domaine, institution, "author");
    }
}

