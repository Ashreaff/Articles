package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signUpController {
 @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_fistName;

    @FXML
    private TextField tf_lastName;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_domaine;

    @FXML
    private TextField tf_institustion;

    @FXML
    private TextField tf_adresse;


    @FXML
    private TextField tf_role; 
    
    @FXML
    public void btnCreateClicked(ActionEvent event) {
        String nom = tf_fistName.getText().trim();
        String prenom = tf_lastName.getText().trim();
        String adresse = tf_adresse.getText().trim();
        String email = tf_email.getText().trim();
        String password = tf_password.getText().trim();
        String domaine = tf_domaine.getText().trim();
        String institution = tf_institustion.getText().trim();
        String role = tf_role.getText().trim(); 
    
        // Validation des champs
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || password.isEmpty() ||
            domaine.isEmpty() || institution.isEmpty() || role.isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Veuillez remplir tous les champs.");
            return;
        }
    
        // Insérer l'utilisateur dans la base de données
        if (registerUser(nom, prenom, adresse, password, domaine, institution, role, email)) {
            showAlert(AlertType.INFORMATION, "Succès", "Inscription réussie !");
        } else {
            showAlert(AlertType.ERROR, "Échec", "Erreur lors de l'inscription.");
        }
    }
    
    private boolean registerUser(String nom, String prenom, String adresse, String password, 
                                  String domaine, String institution, String role,String email) {
        String query = "INSERT INTO chercheur (nom, prenom, mail, password, adresse , domaine, institution) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
    
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, adresse);
            stmt.setString(6, domaine);
            stmt.setString(7, institution);
    
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                try (var rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idChercheur = rs.getInt(1); 
                        insertRole(idChercheur, role);  
                    }
                }
                return true;
            } else {
                return false;
            }
    
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription : " + e.getMessage());
            return false;
        }
    }
    
    private void insertRole(int idChercheur, String role) {
        String query = "";
        
        switch (role.toLowerCase()) {
            case "author":
                query = "INSERT INTO auteur (id_autheur) VALUES (?)";
                break;
            case "editor":
                query = "INSERT INTO editeur (id_editeur) VALUES (?)";
                break;
            case "reviewer":
                query = "INSERT INTO evaluateur (id_evaluateur) VALUES (?)";
                break;
            default:
                System.out.println("Rôle invalide");
                return;
        }
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idChercheur);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'insertion du rôle : " + e.getMessage());
        }
    }
    
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

    @FXML
    public void goToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tf_fistName.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
       
        

}
