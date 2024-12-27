package Controller;

import Model.Evaluation;
import DAO.EvaluationDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public class MesEvaluationsController extends AuteurBaseController {

    @FXML private TableView<Evaluation> evaluationsTable;
    @FXML private TableColumn<Evaluation, String> titreArticleColumn;
    @FXML private TableColumn<Evaluation, String> dateEvaluationColumn;
    @FXML private TableColumn<Evaluation, String> avisColumn;
    @FXML private TextArea detailsArea;

    private EvaluationDAO evaluationDAO = new EvaluationDAO();
    private ObservableList<Evaluation> evaluations = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titreArticleColumn.setCellValueFactory(cellData -> cellData.getValue().titreArticleProperty());
        dateEvaluationColumn.setCellValueFactory(cellData -> 
            cellData.getValue().dateEvaluationProperty().asString());
        avisColumn.setCellValueFactory(cellData -> cellData.getValue().avisProperty());

        evaluationsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showEvaluationDetails(newValue));

        evaluationsTable.setItems(evaluations);

        loadEvaluations();
    }

    private void loadEvaluations() {
        try {
            evaluations.setAll(evaluationDAO.getEvaluationsByAuteur(getIdAuteur()));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les évaluations: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showEvaluationDetails(Evaluation evaluation) {
        if (evaluation != null) {
            try {
                String details = evaluationDAO.getEvaluationDetails(evaluation.getIdEvaluation());
                detailsArea.setText(details);
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les détails de l'évaluation: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            detailsArea.clear();
        }
    }
}

