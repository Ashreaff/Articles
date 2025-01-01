package Controller;

import Model.Soumission;
import DAO.SoumissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MesSoumissionsController extends AuteurBaseController {
    private static final Logger LOGGER = Logger.getLogger(MesSoumissionsController.class.getName());

    @FXML private TableView<Soumission> soumissionsTable;
    @FXML private TableColumn<Soumission, String> titreColumn;
    @FXML private TableColumn<Soumission, String> dateSoumissionColumn;
    @FXML private TableColumn<Soumission, Number> tailleColumn;
    @FXML private TextArea detailsArea;

    private SoumissionDAO soumissionDAO = new SoumissionDAO();
    private ObservableList<Soumission> soumissions = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        dateSoumissionColumn.setCellValueFactory(cellData -> 
            cellData.getValue().dateSoumissionProperty().asString());
        tailleColumn.setCellValueFactory(cellData -> cellData.getValue().tailleProperty());

        soumissionsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showSoumissionDetails(newValue));

        soumissionsTable.setItems(soumissions);
    }

    @Override
    public void setIdAuteur(int id) {
        super.setIdAuteur(id);
        LOGGER.info("ID Auteur set in MesSoumissionsController: " + id);
        loadSoumissions();
    }

    private void loadSoumissions() {
        try {
            int idAuteur = getIdAuteur();
            LOGGER.info("Loading soumissions for author with ID: " + idAuteur);
            soumissions.setAll(soumissionDAO.getSoumissionsByCorrespondant(idAuteur));
            LOGGER.info("Loaded " + soumissions.size() + " soumissions");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error loading soumissions", e);
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les soumissions: " + e.getMessage());
        }
    }

    private void showSoumissionDetails(Soumission soumission) {
        if (soumission != null) {
            try {
                String details = soumissionDAO.getSoumissionDetails(soumission.getIdSoumission());
                detailsArea.setText(details);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error loading soumission details", e);
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les détails de la soumission: " + e.getMessage());
            }
        } else {
            detailsArea.setText("");
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

