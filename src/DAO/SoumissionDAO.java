package DAO;

import DataBase.DatabaseConnection;
import Model.Soumission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoumissionDAO {

    public List<Soumission> getAllSoumissions() throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille, a.pdf_file_path " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                soumissions.add(new Soumission(
                        rs.getInt("id_soumission"),
                        rs.getInt("id_article"),
                        rs.getInt("id_correspondant"),
                        rs.getDate("date_soumission").toLocalDate(),
                        rs.getBoolean("affecter"),
                        rs.getString("titre"),
                        rs.getInt("taille"),
                        rs.getString("pdf_file_path")
                ));
            }
        }
        return soumissions;
    }

    public List<Soumission> getSoumissionsNonAffectees() throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille, a.pdf_file_path " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.affecter = FALSE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                soumissions.add(new Soumission(
                        rs.getInt("id_soumission"),
                        rs.getInt("id_article"),
                        rs.getInt("id_correspondant"),
                        rs.getDate("date_soumission").toLocalDate(),
                        rs.getBoolean("affecter"),
                        rs.getString("titre"),
                        rs.getInt("taille"),
                        rs.getString("pdf_file_path")
                ));
            }
        }
        return soumissions;
    }

    public List<Soumission> getSoumissionsByCorrespondant(int idCorrespondant) throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille, a.pdf_file_path " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.id_correspondant = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCorrespondant);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    soumissions.add(new Soumission(
                            rs.getInt("id_soumission"),
                            rs.getInt("id_article"),
                            rs.getInt("id_correspondant"),
                            rs.getDate("date_soumission").toLocalDate(),
                            rs.getBoolean("affecter"),
                            rs.getString("titre"),
                            rs.getInt("taille"),
                            rs.getString("pdf_file_path")
                    ));
                }
            }
        }
        return soumissions;
    }

    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.id_soumission, a.titre, a.resume, a.taille, s.date_soumission, s.affecter, s.id_correspondant, a.pdf_file_path " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.id_soumission = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSoumission);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nTaille de l'article: %d\nDate de soumission: %s\nID Correspondant: %d\nAffectée: %s\nChemin du PDF: %s",
                            rs.getInt("id_soumission"),
                            rs.getString("titre"),
                            rs.getString("resume"),
                            rs.getInt("taille"),  
                            rs.getDate("date_soumission").toString(),
                            rs.getInt("id_correspondant"),
                            rs.getBoolean("affecter") ? "Oui" : "Non",
                            rs.getString("pdf_file_path")
                    );
                }
            }
        }
        return "Détails non disponibles";
    }

    public int saveSoumission(Soumission soumission) throws SQLException {
        String sql = "INSERT INTO soumission (id_article, id_correspondant, date_soumission, affecter) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, soumission.getIdArticle());
            pstmt.setInt(2, soumission.getIdCorrespondant());
            pstmt.setDate(3, java.sql.Date.valueOf(soumission.getDateSoumission()));
            pstmt.setBoolean(4, soumission.isAffecter());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating soumission failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating soumission failed, no ID obtained.");
                }
            }
        }
    }

    public void deleteSoumission(int idSoumission) throws SQLException {
        String sql = "DELETE FROM soumission WHERE id_soumission = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idSoumission);
            pstmt.executeUpdate();
        }
    }
}

