package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent; 

public class Editeur_AddEditor {


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
    public void btnCreateClicked(ActionEvent event) {
        String nom = tf_fistName.getText().trim();
        String prenom = tf_lastName.getText().trim();
        String adresse = tf_adresse.getText().trim();
        String email = tf_email.getText().trim();
        String password = tf_password.getText().trim();
        String domaine = tf_domaine.getText().trim();
        String institution = tf_institustion.getText().trim();
    
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || password.isEmpty() ||
            domaine.isEmpty() || institution.isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Veuillez remplir tous les champs.");
            return;
        }
    
        // Insérer l'utilisateur dans la base de données
        if (registerUser(nom, prenom, adresse, password, domaine, institution, email)) {
            showAlert(AlertType.INFORMATION, "Succès", "Inscription réussie !");
        } else {
            showAlert(AlertType.ERROR, "Échec", "Erreur lors de l'inscription.");
        }
    }
    
    private boolean registerUser(String nom, String prenom, String adresse, String password, 
                                  String domaine, String institution, String email) {
        String query = "INSERT INTO chercheur (nom, prenom, mail, password, adresse , domaine, institution) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
                        insertEditorRole(idChercheur); // Insert the 'editor' role directly
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
    
    private void insertEditorRole(int idChercheur) {
        String query = "INSERT INTO editeur (id_editeur) VALUES (?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idChercheur);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'insertion du rôle 'editeur' : " + e.getMessage());
        }
    }
    
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    


    @FXML
    private void handleHoverEnter(MouseEvent event) {
        HBox source = (HBox) event.getSource();
        source.setStyle("-fx-padding: 10 5; -fx-background-color: #1ABC9C; -fx-border-radius: 5; -fx-background-radius: 5; -fx-cursor: hand; -fx-transition: all 0.3s ease-in-out;");
        source.getChildren().forEach(node -> {
            if (node instanceof javafx.scene.control.Label) {
                ((javafx.scene.control.Label) node).setTextFill(Color.WHITE);
            }
        });
    }
    

    @FXML
    private void handleHoverExit(MouseEvent event) {
        HBox source = (HBox) event.getSource();
        source.setStyle("-fx-padding: 10 5; -fx-background-color: transparent; -fx-border-radius: 5; -fx-background-radius: 5; -fx-cursor: hand; -fx-transition: all 0.3s ease-in-out;");
        source.getChildren().forEach(node -> {
            if (node instanceof javafx.scene.control.Label) {
                ((javafx.scene.control.Label) node).setTextFill(Color.web("#ECF0F1"));
            }
        });
    }

    @FXML
    private void handleHomeClick(MouseEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Editor/homeEditeur.fxml"));
            Parent loginRoot = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene signUpScene = new Scene(loginRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void handleChooseEvaluatorsClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Editor/Editor_chooseEval.fxml"));
            Parent loginRoot = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene signUpScene = new Scene(loginRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void handleMakeDecisionClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Editor/Editor_Decision.fxml"));
            Parent loginRoot = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene signUpScene = new Scene(loginRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void handleEvaluationProcessClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Editor/Editor_process.fxml"));
            Parent loginRoot = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene signUpScene = new Scene(loginRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }      }

    
}
