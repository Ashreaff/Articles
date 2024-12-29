package DAO;

import Model.Soumission;
import DataBase.DatabaseConnection;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoumissionDAO {

    private static final Logger LOGGER = Logger.getLogger(SoumissionDAO.class.getName());


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
    
    

    public List<Soumission> getSoumissionsByCorrespondant(int idCorrespondant) throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT * FROM soumission WHERE id_correspondant = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCorrespondant);
            try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Soumission soumission = new Soumission();
                soumission.setIdSoumission(rs.getInt("id_soumission"));
                soumission.setIdArticle(rs.getInt("id_article"));
                soumission.setIdCorrespondant(rs.getInt("id_correspondant"));
                soumission.setDateSoumission(rs.getDate("date_soumission").toLocalDate());
                soumission.setAffecter(rs.getBoolean("affecter"));
                soumissions.add(soumission);
            }
            }
        }
        return soumissions;
    }
    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.*, a.titre, a.resume FROM soumission s JOIN article a ON s.id_article = a.id_article WHERE s.id_soumission = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSoumission);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nDate de soumission: %s\nAffecté: %s",
                        rs.getInt("id_soumission"),
                        rs.getString("titre"),
                        rs.getString("resume"),
                        rs.getDate("date_soumission"),
                        rs.getBoolean("affecter") ? "Oui" : "Non"
                    );
                }
            }
        }
        return "Détails non disponibles";
    }
    
}

