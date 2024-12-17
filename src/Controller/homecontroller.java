package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.Node;
import javafx.stage.Stage;




public class homecontroller {

    
    @FXML
    public void btn_AuthentificateClicked(ActionEvent event) {
        // Charger la scène de SignUp
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/signUpScene.fxml"));
            Parent loginRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Create a new scene with the login view
            Scene signUpScene = new Scene(loginRoot);

            
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btn_LoginClicked(ActionEvent event) {
        // Charger la scène de SignUp
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            Parent loginRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Create a new scene with the login view
            Scene signUpScene = new Scene(loginRoot);

            
            stage.setScene(signUpScene);
            stage.setTitle("Log in");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
