package Controller;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import Model.Article;
import Model.Soumission;
import DAO.ArticleDAO;
import DAO.SoumissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class SoumettreArticleController extends AuteurBaseController {

    @FXML private TextField titreField;
    @FXML private TextArea resumeArea;
    @FXML private TextField motsClesField;
    @FXML private Label tailleLabel;
    @FXML private Button importButton;
    @FXML private Label fileNameLabel;

    private ArticleDAO articleDAO = new ArticleDAO();
    private SoumissionDAO soumissionDAO = new SoumissionDAO();
    private File selectedFile;
    private int wordCount;

    @FXML
    private void initialize() {
        importButton.setOnAction(event -> importPDF());
    }

    private void importPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            fileNameLabel.setText(selectedFile.getName());
            wordCount = countWordsInPDF(selectedFile);
            tailleLabel.setText("Nombre de mots : " + wordCount);
        }
    }

    private int countWordsInPDF(File file) {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\s+");
            int count = words.length;
            System.out.println("Nombre de mots comptés : " + count);  // Pour le débogage
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de lire le fichier PDF: " + e.getMessage());
            return 0;
        }
    }

    @FXML
    private void handleSoumettre() {
        if (validateInputs()) {
            try {
                int idAuteur = getIdAuteur();
                boolean estCourt = wordCount < 4000;

                Article article = new Article(
                    0, // id will be set by the database
                    titreField.getText(),
                    idAuteur,
                    resumeArea.getText(),
                    wordCount,
                    motsClesField.getText(),
                    estCourt,
                    selectedFile.getAbsolutePath()
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

    private boolean validateInputs() {
        StringBuilder errorMessage = new StringBuilder();

        if (titreField.getText().trim().isEmpty()) {
            errorMessage.append("Le titre ne peut pas être vide.\n");
        }
        if (resumeArea.getText().trim().isEmpty()) {
            errorMessage.append("Le résumé ne peut pas être vide.\n");
        }
        if (motsClesField.getText().trim().isEmpty()) {
            errorMessage.append("Veuillez entrer au moins un mot-clé.\n");
        }
        if (selectedFile == null) {
            errorMessage.append("Veuillez importer un fichier PDF.\n");
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
        motsClesField.clear();
        fileNameLabel.setText("");
        tailleLabel.setText("Nombre de mots : 0");
        selectedFile = null;
        wordCount = 0;
    }

    @FXML
    private void handleAnnuler() {
        clearFields();
    }

    protected void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

