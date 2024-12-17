package Controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Editeur_process {

    
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
    private void handChooseEvalClick(MouseEvent event) {
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
    private void handDecissionClick(MouseEvent event) {
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
    private void handleAddEditorClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Editor/Editor_AddEditor.fxml"));
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
    

    
}
