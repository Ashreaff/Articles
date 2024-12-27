package Controller;

import Model.Soumission;
import DAO.SoumissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public class MesSoumissionsController extends AuteurBaseController {

    @FXML private TableView<Soumission> soumissionsTable;
    @FXML private TableColumn<Soumission, String> titreColumn;
    @FXML private TableColumn<Soumission, String> dateSoumissionColumn;
    @FXML private TableColumn<Soumission, String> statutColumn;
    @FXML private TextArea detailsArea;

    private SoumissionDAO soumissionDAO = new SoumissionDAO();
    private ObservableList<Soumission> soumissions = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreArticleProperty());
        dateSoumissionColumn.setCellValueFactory(cellData -> 
            cellData.getValue().dateSoumissionProperty().asString());
        statutColumn.setCellValueFactory(cellData -> cellData.getValue().statutProperty());

        soumissionsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showSoumissionDetails(newValue));

        soumissionsTable.setItems(soumissions);

        loadSoumissions();
    }

    private void loadSoumissions() {
        try {
            soumissions.setAll(soumissionDAO.getSoumissionsByAuteur(getIdAuteur()));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les soumissions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showSoumissionDetails(Soumission soumission) {
        if (soumission != null) {
            try {
                String details = soumissionDAO.getSoumissionDetails(soumission.getIdSoumission());
                detailsArea.setText(details);
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les détails de la soumission: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            detailsArea.clear();
        }
    }

    protected void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
}

