package Controller;

import java.util.List;

import DAO.ArticleDAO;
import DAO.EvaluateurDAO;
import Model.Article;
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
import java.util.List;

public class Editeur_choseEval {


    // Table for articles
    @FXML
    private TableView<Article> articlesTable;

    @FXML
    private TableColumn<Article, Integer> idColumn;

    @FXML
    private TableColumn<Article, String> nameColumn;

    private final ArticleDAO articleDAO = new ArticleDAO();

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
        // Initialize article table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        loadArticles();

        // Initialize evaluator table
        evalIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        evalNameColumn.setCellValueFactory(data -> {
            String fullName = data.getValue().getNom() + " " + data.getValue().getPrenom();
            return new javafx.beans.property.SimpleStringProperty(fullName);
        });
        loadEvaluateurs();
    }

    private void loadArticles() {
        try {
            List<Article> articles = articleDAO.getAllArticles();
            ObservableList<Article> articlesList = FXCollections.observableArrayList(articles);
            articlesTable.setItems(articlesList);
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
