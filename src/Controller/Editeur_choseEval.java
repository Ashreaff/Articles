package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.EvaluationDAO;
import DAO.EvaluateurDAO;
import DAO.SoumissionDAO;
import Model.Soumission;
import Model.Chercheur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Editeur_choseEval {


    // Table for soumission
    @FXML
    private TableView<Soumission> soumissionTable;

    @FXML
    private TableColumn<Soumission, Integer> idSOUColumn;
    @FXML
    private TableColumn<Soumission, Integer> idSArColumn;

    @FXML
    private TableColumn<Soumission, Integer> taille;

    @FXML
    private TableColumn<Soumission, String> nameColumn;

    private final SoumissionDAO soumissionDAO = new SoumissionDAO(); 


    // Table for evaluators
    @FXML
    private TableView<Chercheur> evaluateursTable;

    @FXML
    private TableColumn<Chercheur, Integer> evalIdColumn;

    @FXML
    private TableColumn<Chercheur, String> evalNameColumn;

    private final EvaluateurDAO daoEvaluateur = new EvaluateurDAO();

    @FXML
    public void initialize() {

        idSOUColumn.setCellValueFactory(new PropertyValueFactory<>("idSoumission"));
        idSArColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        taille.setCellValueFactory(new PropertyValueFactory<>("taille"));
        loadSoumission();

        // Initialize evaluator table
        evalIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        evalNameColumn.setCellValueFactory(data -> {
            String fullName = data.getValue().getNom() + " " + data.getValue().getPrenom();
            return new javafx.beans.property.SimpleStringProperty(fullName);
        });
        loadEvaluateurs();
    }

    private void loadSoumission() {
        try {
            List<Soumission> soumissions = soumissionDAO.getSoumissionsNonAffectees(); 
            ObservableList<Soumission> soumissionsList = FXCollections.observableArrayList(soumissions); 
            soumissionTable.setItems(soumissionsList); 

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private void loadEvaluateurs() {
        try {
            List<Chercheur> evaluateurs = daoEvaluateur.getAllEvaluateurs();
            ObservableList<Chercheur> evaluateursList = FXCollections.observableArrayList(evaluateurs);
            evaluateursTable.setItems(evaluateursList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
private void handleValidateButtonClick() {
    Soumission selectedSoumission = soumissionTable.getSelectionModel().getSelectedItem();
    ObservableList<Chercheur> selectedEvaluateurs = evaluateursTable.getSelectionModel().getSelectedItems();

    if (selectedSoumission != null && !selectedEvaluateurs.isEmpty()) {
        int idSoumission = selectedSoumission.getIdSoumission();
        List<Integer> idEvaluateurs = new ArrayList<>();
        for (Chercheur evaluateur : selectedEvaluateurs) {
            idEvaluateurs.add(evaluateur.getId());
        }
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        boolean success = evaluationDAO.insertEvaluationWithEvaluators(idSoumission, idEvaluateurs);

        if (success) {
            System.out.println("Évaluation et évaluateurs insérés avec succès.");
        } else {
            System.out.println("Erreur lors de l'insertion des données.");
        }
    } else {
        System.out.println("Veuillez sélectionner une soumission et au moins un évaluateur.");
    }
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
