package DAO;

import Model.Article;
import java.sql.*;
import java.time.LocalDate;

public class ArticleDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion", "root", "");
    }

    public int saveArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (titre, id_auteur, resume, taille, contenu, est_court) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, article.getTitre());
            pstmt.setInt(2, article.getIdAuteur());
            pstmt.setString(3, article.getResume());
            pstmt.setInt(4, article.getTaille());
            pstmt.setString(5, article.getContenu());
            pstmt.setBoolean(6, article.isEstCourt());
            
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
        try (Connection conn = getConnection();
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
}
