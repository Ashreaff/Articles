package Controller;

import Model.Article;
import DAO.ArticleDAO;
import DAO.AuteurDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

public class SoumettreArticleController extends AuteurBaseController {

    @FXML private TextField titreField;
    @FXML private TextArea resumeArea;
    @FXML private TextArea contenuArea;
    @FXML private TextField tailleField;
    @FXML private CheckBox estCourtCheckBox;

    private ArticleDAO articleDAO = new ArticleDAO();
    private AuteurDAO auteurDAO = new AuteurDAO();

    @FXML
    private void handleSoumettre() {
        if (validateInputs()) {
            try {
                int idAuteur = getIdAuteur();
                System.out.println("ID Auteur utilisé: " + idAuteur);
                String auteurDetails = auteurDAO.getAuteurDetails(idAuteur);
                
                if (!auteurDAO.auteurExists(idAuteur)) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "L'auteur n'existe pas dans la base de données ou n'est pas enregistré comme auteur.\n\nDétails:\n" + auteurDetails);
                    return;
                }

                Article article = new Article(
                    0, 
                    titreField.getText(),
                    idAuteur,
                    resumeArea.getText(),
                    Integer.parseInt(tailleField.getText()),
                    contenuArea.getText(),
                    estCourtCheckBox.isSelected()
                );

                int articleId = articleDAO.saveArticle(article);
                articleDAO.createSoumission(articleId, idAuteur);
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
        try {
            int taille = Integer.parseInt(tailleField.getText());
            if (taille <= 0) {
                errorMessage.append("La taille doit être un nombre positif.\n");
            }
        } catch (NumberFormatException e) {
            errorMessage.append("La taille doit être un nombre valide.\n");
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
        tailleField.clear();
        estCourtCheckBox.setSelected(false);
    }
}

