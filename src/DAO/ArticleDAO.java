package DAO;

import DataBase.DatabaseConnection;
import Model.Article;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public int saveArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (titre, id_auteur, resume, taille, contenu, est_court, affecter) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, article.getTitre());
            pstmt.setInt(2, article.getIdAuteur());
            pstmt.setString(3, article.getResume());
            pstmt.setInt(4, article.getTaille());
            pstmt.setString(5, article.getContenu());
            pstmt.setBoolean(6, article.isEstCourt());
            pstmt.setBoolean(7, article.isAffecter()); // Insérer la valeur de `affecter` (par défaut 0)

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating article failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating article failed, no ID obtained.");
                }
            }
        }
    }

    public void createSoumission(int idArticle, int idAuteur) throws SQLException {
        String sql = "INSERT INTO soumission (id_article, id_correspondant, date_soumission, id_evaluateur) VALUES (?, ?, ?, NULL)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idArticle);
            pstmt.setInt(2, idAuteur);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating submission failed, no rows affected.");
            }
        }
    }

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String query = """
            SELECT id_article, titre, id_auteur, resume, taille, contenu, 
                   est_court, affecter 
            FROM article 
            WHERE affecter = false
            """;
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
    
            while (resultSet.next()) {
                Article article = new Article(
                    resultSet.getInt("id_article"),
                    resultSet.getString("titre"),
                    resultSet.getInt("id_auteur"),
                    resultSet.getString("resume"),
                    resultSet.getInt("taille"),
                    resultSet.getString("contenu"),
                    resultSet.getBoolean("est_court"),
                    resultSet.getBoolean("affecter")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return articles;
    }
    
}
