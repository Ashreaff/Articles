package Controller;

import Model.Soumission;
import DAO.SoumissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @FXML private Button downloadButton;
    @FXML private Button deleteButton;

    private SoumissionDAO soumissionDAO = new SoumissionDAO();
    private ObservableList<Soumission> soumissions = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        dateSoumissionColumn.setCellValueFactory(cellData -> 
            cellData.getValue().dateSoumissionProperty().asString());
        tailleColumn.setCellValueFactory(cellData -> cellData.getValue().tailleProperty());

        soumissionsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                showSoumissionDetails(newValue);
                boolean hasSelection = (newValue != null);
                downloadButton.setDisable(!hasSelection);
                deleteButton.setDisable(!hasSelection);
            });

        soumissionsTable.setItems(soumissions);

        downloadButton.setOnAction(event -> downloadSelectedSoumission());
        deleteButton.setOnAction(event -> deleteSelectedSoumission());
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

    private void downloadSelectedSoumission() {
        Soumission selectedSoumission = soumissionsTable.getSelectionModel().getSelectedItem();
        if (selectedSoumission != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le PDF");
            fileChooser.setInitialFileName(selectedSoumission.getTitre() + ".pdf");
            File file = fileChooser.showSaveDialog(soumissionsTable.getScene().getWindow());
            
            if (file != null) {
                try {
                    Files.copy(Paths.get(selectedSoumission.getPdfFilePath()), file.toPath());
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Le PDF a été téléchargé avec succès.");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Erreur lors du téléchargement du PDF", e);
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de télécharger le PDF: " + e.getMessage());
                }
            }
        }
    }

    private void deleteSelectedSoumission() {
        Soumission selectedSoumission = soumissionsTable.getSelectionModel().getSelectedItem();
        if (selectedSoumission != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmation de suppression");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette soumission ?");

            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    soumissionDAO.deleteSoumission(selectedSoumission.getIdSoumission());
                    soumissions.remove(selectedSoumission);
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "La soumission a été supprimée avec succès.");
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de la soumission", e);
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de supprimer la soumission: " + e.getMessage());
                }
            }
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

