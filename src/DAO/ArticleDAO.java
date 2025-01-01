package DAO;

import Model.Article;
import DataBase.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ArticleDAO {
    private static final Logger LOGGER = Logger.getLogger(ArticleDAO.class.getName());

    public int saveArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (titre, id_auteur, resume, taille, mots_cle, est_court, pdf_file_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, article.getTitre());
            pstmt.setInt(2, article.getIdAuteur());
            pstmt.setString(3, article.getResume());
            pstmt.setInt(4, article.getTaille());
            pstmt.setString(5, article.getMotsCle());
            pstmt.setBoolean(6, article.isEstCourt());
            pstmt.setString(7, article.getPdfFilePath());
            
            LOGGER.info("Exécution de la requête SQL : " + pstmt.toString());
            LOGGER.info("Taille de l'article à sauvegarder : " + article.getTaille());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating article failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    LOGGER.info("Article sauvegardé avec succès. ID: " + id + ", Taille: " + article.getTaille());
                    return id;
                } else {
                    throw new SQLException("Creating article failed, no ID obtained.");
                }
            }
        }
    }
    public Article getArticleById(int idArticle) throws SQLException {
        String sql = "SELECT * FROM article WHERE id_article = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idArticle);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Article(
                        rs.getInt("id_article"),
                        rs.getString("titre"),
                        rs.getInt("id_auteur"),
                        rs.getString("resume"),
                        rs.getInt("taille"),
                        rs.getString("mots_cle"),
                        rs.getBoolean("est_court"),
                        rs.getString("pdf_file_path")
                    );
                }
            }
        }
        return null;
    }

    public List<Article> getArticlesByAuteur(int idAuteur) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE id_auteur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAuteur);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    articles.add(new Article(
                        rs.getInt("id_article"),
                        rs.getString("titre"),
                        rs.getInt("id_auteur"),
                        rs.getString("resume"),
                        rs.getInt("taille"),
                        rs.getString("mots_cle"),
                        rs.getBoolean("est_court"),
                        rs.getString("pdf_file_path")
                    ));
                }
            }
        }
        return articles;
    }

    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                articles.add(new Article(
                    rs.getInt("id_article"),
                    rs.getString("titre"),
                    rs.getInt("id_auteur"),
                    rs.getString("resume"),
                    rs.getInt("taille"),
                    rs.getString("mots_cle"),
                    rs.getBoolean("est_court"),
                    rs.getString("pdf_file_path")
                ));
            }
        }
        return articles;
    }
}

