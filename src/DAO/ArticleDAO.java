package DAO;

import Model.Article;
import DataBase.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    public int saveArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (titre, id_auteur, resume, taille, contenu, mots_cle, est_court) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, article.getTitre());
            pstmt.setInt(2, article.getIdAuteur());
            pstmt.setString(3, article.getResume());
            pstmt.setInt(4, article.getTaille());
            pstmt.setString(5, article.getContenu());
            pstmt.setString(6, article.getMotsCle());
            pstmt.setBoolean(7, article.isEstCourt());
            
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
                        rs.getString("contenu"),
                        rs.getString("mots_cle"),
                        rs.getBoolean("est_court")
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
                        rs.getString("contenu"),
                        rs.getString("mots_cle"),
                        rs.getBoolean("est_court")
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
                    rs.getString("contenu"),
                    rs.getString("mots_cle"),
                    rs.getBoolean("est_court")
                ));
            }
        }
        return articles;
    }
}

