package Controller;

import Model.Article;
import Model.Soumission;
import DAO.ArticleDAO;
import DAO.SoumissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class SoumettreArticleController extends AuteurBaseController {

    @FXML private TextField titreField;
    @FXML private TextArea resumeArea;
    @FXML private TextArea contenuArea;
    @FXML private TextField motsClesField;
    @FXML private Label tailleLabel;

    private ArticleDAO articleDAO = new ArticleDAO();
    private SoumissionDAO soumissionDAO = new SoumissionDAO();

    @FXML
    private void initialize() {
        contenuArea.textProperty().addListener((observable, oldValue, newValue) -> {
            updateWordCount();
        });
    }

    private void updateWordCount() {
        int wordCount = contenuArea.getText().split("\\s+").length;
        tailleLabel.setText("Nombre de mots : " + wordCount);
    }

    @FXML
    private void handleSoumettre() {
        if (validateInputs()) {
            try {
                int idAuteur = getIdAuteur();
                int wordCount = contenuArea.getText().split("\\s+").length;
                boolean estCourt = wordCount < 4000;

                Article article = new Article(
                    0, // id will be set by the database
                    titreField.getText(),
                    idAuteur,
                    resumeArea.getText(),
                    wordCount,
                    contenuArea.getText(),
                    motsClesField.getText(),
                    estCourt
                );

                int articleId = articleDAO.saveArticle(article);

                Soumission soumission = new Soumission(
                    0, // id will be set by the database
                    articleId,
                    idAuteur,
                    LocalDate.now(),
                    false, // not yet assigned
                    titreField.getText(),
                    wordCount
                );

                soumissionDAO.saveSoumission(soumission);

                showAlert(Alert.AlertType.INFORMATION, "Succès", "L'article a été soumis avec succès.");
                clearFields();
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la soumission de l'article: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleAnnuler() {
        clearFields();
    }

    private boolean validateInputs() {
        StringBuilder errorMessage = new StringBuilder();

        if (titreField.getText().trim().isEmpty()) {
            errorMessage.append("Le titre ne peut pas être vide.\n");
        }
        if (resumeArea.getText().trim().isEmpty()) {
            errorMessage.append("Le résumé ne peut pas être vide.\n");
        }
        if (contenuArea.getText().trim().isEmpty()) {
            errorMessage.append("Le contenu ne peut pas être vide.\n");
        }
        if (motsClesField.getText().trim().isEmpty()) {
            errorMessage.append("Veuillez entrer au moins un mot-clé.\n");
        }

        if (errorMessage.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Erreur de validation", errorMessage.toString());
            return false;
        }
        return true;
    }

    private void clearFields() {
        titreField.clear();
        resumeArea.clear();
        contenuArea.clear();
        motsClesField.clear();
        tailleLabel.setText("Nombre de mots : 0");
    }

    protected void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
}


