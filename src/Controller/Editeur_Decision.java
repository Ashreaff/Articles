package Controller;

import DAO.EvaluationDAO;
import Model.Evaluation;

import javafx.scene.Node;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Editeur_Decision {


    @FXML
    private ComboBox<String> decisionComboBox;
    @FXML
    private TableView<Evaluation> evaluationsTable;
    @FXML
    private TableColumn<Evaluation, Integer> idEvalColumn;
    @FXML
    private TableColumn<Evaluation, Integer> idSoumissionColumn;
    @FXML
    private TableColumn<Evaluation, String> evaluateurColumn;
    @FXML
    private TableColumn<Evaluation, String> avisColumn;
    @FXML
    private TableColumn<Evaluation, String> dateColumn;

    private ObservableList<Evaluation> evaluationList = FXCollections.observableArrayList();
    private EvaluationDAO evaluationDAO = new EvaluationDAO(); // Initialize the DAO

    @FXML
    public void initialize() {
        // Initialize ComboBox
        decisionComboBox.setItems(FXCollections.observableArrayList(
            "Minor Revision", 
            "Major Revision", 
            "Refusal", 
            "Acceptance"
        ));

        // Initialize TableView columns
        idEvalColumn.setCellValueFactory(cellData -> cellData.getValue().idEvaluationProperty().asObject());
        idSoumissionColumn.setCellValueFactory(cellData -> cellData.getValue().idSoumissionProperty().asObject());
        
        // Displaying the list of evaluators (a simple toString representation for now)
        evaluateurColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvaluateurs().toString()));
        
        avisColumn.setCellValueFactory(cellData -> cellData.getValue().avisProperty());
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateEvaluation().toString()));

        // Fetch evaluations where evaluer is true and has evaluators
        List<Evaluation> evaluations = evaluationDAO.getEvaluationsWithEvaluerTrueAndEvaluators();

        // Add evaluations to the observable list
        evaluationList.setAll(evaluations);

        // Set the items in the TableView
        evaluationsTable.setItems(evaluationList);
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
