package Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent; // Import pour MouseEvent
import javafx.stage.Stage;
import javafx.util.Duration; // Import pour Duration
import javafx.scene.Node;

public class homecontroller {

    @FXML
    private void handleMouseEnter(MouseEvent event) {
        Button button = (Button) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    @FXML
    public void btn_AuthentificateClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/signUpScene.fxml"));
            Parent signUpRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene signUpScene = new Scene(signUpRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_LoginClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setTitle("Log in");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
