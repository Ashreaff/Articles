package DAO;

import DataBase.DatabaseConnection;
import Model.Soumission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoumissionDAO {

    public List<Soumission> getSoumissionsByAuteur(int idAuteur) throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, a.titre, s.id_correspondant, s.date_soumission, s.id_evaluateur, " +
                     "CASE WHEN e.id_evaluation IS NULL THEN 'En attente' ELSE e.avis END AS statut " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "LEFT JOIN evaluation e ON s.id_soumission = e.id_soumission " +
                     "WHERE a.id_auteur = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAuteur);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    soumissions.add(new Soumission(
                            rs.getInt("id_soumission"),
                            rs.getInt("id_article"),
                            rs.getInt("id_correspondant"),
                            rs.getDate("date_soumission").toLocalDate(),
                            rs.getObject("id_evaluateur") != null ? rs.getInt("id_evaluateur") : null,
                            rs.getString("titre"),
                            rs.getString("statut")
                    ));
                }
            }
        }
        return soumissions;
    }

    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.id_soumission, a.titre, a.resume, s.date_soumission, s.id_evaluateur, " +
                     "CASE WHEN e.id_evaluation IS NULL THEN 'En attente' ELSE e.avis END AS statut, " +
                     "e.date_evaluation " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "LEFT JOIN evaluation e ON s.id_soumission = e.id_soumission " +
                     "WHERE s.id_soumission = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSoumission);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nDate de soumission: %s\nID Évaluateur: %s\nStatut: %s\nDate d'évaluation: %s",
                            rs.getInt("id_soumission"),
                            rs.getString("titre"),
                            rs.getString("resume"),
                            rs.getDate("date_soumission"),
                            rs.getObject("id_evaluateur") != null ? rs.getInt("id_evaluateur") : "Non assigné",
                            rs.getString("statut"),
                            rs.getDate("date_evaluation") != null ? rs.getDate("date_evaluation").toString() : "N/A"
                    );
                }
            }
        }
        return "Détails non disponibles";
    }
}
