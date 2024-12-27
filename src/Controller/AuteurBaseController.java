package Controller;

import javafx.scene.control.Alert;

public class AuteurBaseController {
    private int idAuteur;

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
        System.out.println("ID Auteur set to: " + idAuteur); 
    }

    protected int getIdAuteur() {
        System.out.println("Returning ID Auteur: " + idAuteur); 
        return idAuteur;
    }

    protected void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


