package Controller;

import DataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class logincontroller {

    @FXML
    private TextField tf_mail;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label lbl_errorMail;

    @FXML
    private Label lbl_errorPassword;

    @FXML
    public void login(ActionEvent event) {
        // Réinitialisation des messages d'erreur
        lbl_errorMail.setText("");
        lbl_errorPassword.setText("");

        String mail = tf_mail.getText().trim();
        String password = tf_password.getText().trim();

        boolean hasError = false;

        // Vérification des champs vides
        if (mail.isEmpty()) {
            lbl_errorMail.setText("Email is required.");
            hasError = true;
        }

        if (password.isEmpty()) {
            lbl_errorPassword.setText("Password is required.");
            hasError = true;
        }

        if (hasError) return;

        // Validation des informations
        LoginResult result = validateLoginAndGetRoleAndId(mail, password);

        if (result == null) {
            // Vérification si l'email existe
            if (!isEmailValid(mail)) {
                lbl_errorMail.setText("Email does not exist.");
            } else {
                lbl_errorPassword.setText("Incorrect password.");
            }
        } else {
            redirectToRolePage(result.role, result.id);
        }
    }

    private LoginResult validateLoginAndGetRoleAndId(String mail, String password) {
        String query = "SELECT c.id_chercheur, " +
                       "(CASE " +
                       " WHEN EXISTS (SELECT 1 FROM autheur WHERE autheur.id_autheur = c.id_chercheur) THEN 'auteur' " +
                       " WHEN EXISTS (SELECT 1 FROM evaluateur WHERE evaluateur.id_evaluateur = c.id_chercheur) THEN 'evaluateur' " +
                       " WHEN EXISTS (SELECT 1 FROM editeur WHERE editeur.id_editeur = c.id_chercheur) THEN 'editeur' " +
                       " ELSE NULL END) AS role " +
                       "FROM chercheur c " +
                       "WHERE c.mail = ? AND c.password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mail);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("id_chercheur");
                return new LoginResult(role, id);
            }

        } catch (Exception e) {
            System.err.println("Login validation error: " + e.getMessage());
        }

        return null;
    }

    private boolean isEmailValid(String mail) {
        String query = "SELECT 1 FROM chercheur WHERE mail = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mail);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.err.println("Email validation error: " + e.getMessage());
        }

        return false;
    }

    private void redirectToRolePage(String role, int id) {
        String fxmlFile;
        switch (role) {
            case "auteur":
                fxmlFile = "/View/Author/homeAuteur.fxml";
                break;
            case "evaluateur":
                fxmlFile = "/View/Evaluator/homeEvaluateur.fxml";
                break;
            case "editeur":
                fxmlFile = "/View/Editor/homeEditeur.fxml";
                break;
            default:
                lbl_errorMail.setText("Unknown role.");
                return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            
            // Set the ID in the appropriate controller
            Object controller = loader.getController();
            if (controller instanceof AuteurBaseController) {
                ((AuteurBaseController) controller).setIdAuteur(id);
            } 
            
            Stage stage = (Stage) tf_mail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lbl_errorMail.setText("Error loading role page.");
        }
    }

    @FXML
    public void goToSign() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/signUpScene.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tf_mail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tf_mail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LoginResult {
        String role;
        int id;

        LoginResult(String role, int id) {
            this.role = role;
            this.id = id;
        }
    }
}

