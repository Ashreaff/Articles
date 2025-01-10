package Controller;

import DAO.EvaluationDAO;
import Model.EvaluateurInfo;
import Model.Evaluation;
import Model.Revue;
import DAO.revueDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private TableColumn<Evaluation, String> evaluateurNomColumn;

    @FXML
    private TableColumn<Evaluation, String> evaluateurPrenomColumn;

    @FXML
    private TableColumn<Evaluation, String> evaluateurAvisColumn;

    // @FXML
    // private TableColumn<Evaluation, String> avisEvaluationColumn;

    @FXML
    private TableColumn<Evaluation, String> dateEvaluationColumn;

    @FXML
    private TableColumn<Evaluation, String> RemarqueColumn;

    private ObservableList<Evaluation> evaluationList = FXCollections.observableArrayList();
    
    private EvaluationDAO evaluationDAO = new EvaluationDAO(); // Assume DAO is correctly set up

    @FXML
    public void initialize() {
        // Initialize ComboBox for decision options
        decisionComboBox.setItems(FXCollections.observableArrayList(
            // "Minor Revision", 
            // "Major Revision", 
            "Refusal", 
            "Acceptance"
        ));

        // Initialize the Table columns
        idEvalColumn.setCellValueFactory(cellData -> cellData.getValue().idEvaluationProperty().asObject());
        idSoumissionColumn.setCellValueFactory(cellData -> cellData.getValue().idSoumissionProperty().asObject());
        
        // Each evaluator's name and first name
        evaluateurNomColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEvaluateurs().get(0).getNom());
        });

        evaluateurPrenomColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEvaluateurs().get(0).getPrenom());
        });

        // Each evaluator's opinion (Avis)
        evaluateurAvisColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEvaluateurs().get(0).getAvis());
        });

        // Global evaluation opinion
        // avisEvaluationColumn.setCellValueFactory(cellData -> cellData.getValue().avisProperty());
        
        // Evaluation date
        dateEvaluationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateEvaluation().toString()));
        RemarqueColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEvaluateurs().get(0).getRemarque());
        });
        

        // Load evaluations from the database
        List<Evaluation> evaluations = evaluationDAO.getEvaluationsWithEvaluerTrueAndEvaluators();

        // Flatten the evaluators for each evaluation, so we can display them as individual rows
        ObservableList<Evaluation> flattenedEvaluations = FXCollections.observableArrayList();

        // For each evaluation, create a new row for each evaluator and add it to the table
        for (Evaluation evaluation : evaluations) {
            for (EvaluateurInfo evaluator : evaluation.getEvaluateurs()) {
                Evaluation evaluatorEvaluation = new Evaluation(
                    evaluation.getIdEvaluation(),
                    evaluation.getIdSoumission(),
                    evaluation.getAvis(),
                    evaluation.getDateEvaluation(),
                    evaluation.isEvaluer(),
                    List.of(evaluator) // Each row will only contain one evaluator
                );
                flattenedEvaluations.add(evaluatorEvaluation);
            }
        }

        // Populate the table with the flattened evaluations (each row is an evaluator)
        evaluationsTable.setItems(flattenedEvaluations);
    }






    

    @FXML
private void handleSubmitButtonClick() {
    // Récupérer l'évaluation sélectionnée
    Evaluation selectedEvaluation = evaluationsTable.getSelectionModel().getSelectedItem();
    if (selectedEvaluation == null) {
        showAlert("Erreur", "Veuillez sélectionner une évaluation.", Alert.AlertType.ERROR);
        return;
    }

    // Récupérer la décision
    String decision = decisionComboBox.getValue();
    if (decision == null || decision.isEmpty()) {
        showAlert("Erreur", "Veuillez sélectionner une décision.", Alert.AlertType.ERROR);
        return;
    }

    // Créer un objet Revue
    Revue revue = new Revue(0, selectedEvaluation.getIdEvaluation(), decision);

    // Insérer dans la base de données
    revueDAO revueDAO = new revueDAO();
    boolean success = revueDAO.insertRevue(revue);

    if (success) {
        showAlert("Succès", "La revue a été insérée avec succès.", Alert.AlertType.INFORMATION);
    } else {
        showAlert("Erreur", "Une erreur s'est produite lors de l'insertion de la revue.", Alert.AlertType.ERROR);
    }
}

// Méthode utilitaire pour afficher une alerte
private void showAlert(String title, String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
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
