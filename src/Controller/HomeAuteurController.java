package Controller;

import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAuteurController extends AuteurBaseController {

    @FXML private StackPane contentArea;
   
    private int currentAuteurId;

    public void setCurrentAuteurId(int auteurId) {
        this.currentAuteurId = auteurId;
    }

    private int idAuteur;

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    @FXML
    private void initialize() {
        System.out.println("HomeAuteurController initialized with ID: " + getIdAuteur());
      
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
    private void handleHomeClick() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Bienvenue à la Page d'Accueil de l'Auteur"));
    }

    @FXML
    private void handleSoumettreArticle() {
        loadView("soumettreArticle.fxml");
    }

    @FXML
    private void handleMesSoumissions() {
        loadView("mesSoumissions.fxml");
    }

    @FXML
    private void handleMesEvaluations() {
        loadView("mesEvaluations.fxml");
    }
    
    @FXML
    private void logoutClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            Parent loginRoot = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene signUpScene = new Scene(loginRoot);
            stage.setScene(signUpScene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Author/" + fxmlFile));
            Parent view = loader.load();
            
            Object controller = loader.getController();
            if (controller instanceof AuteurBaseController) {
                ((AuteurBaseController) controller).setIdAuteur(idAuteur);
            }
            
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

