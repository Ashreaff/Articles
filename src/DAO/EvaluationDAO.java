package DAO;

import Model.Evaluation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluationDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion", "root", "");
    }

    public List<Evaluation> getEvaluationsByAuteur(int idAuteur) throws SQLException {
        List<Evaluation> evaluations = new ArrayList<>();
        String sql = "SELECT e.id_evaluation, e.id_soumission, a.titre, e.id_evaluateur, e.avis, e.date_evaluation " +
                     "FROM evaluation e " +
                     "JOIN soumission s ON e.id_soumission = s.id_soumission " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE a.id_auteur = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAuteur);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    evaluations.add(new Evaluation(
                        rs.getInt("id_evaluation"),
                        rs.getInt("id_soumission"),
                        rs.getInt("id_evaluateur"),
                        rs.getString("avis"),
                        rs.getDate("date_evaluation") != null ? rs.getDate("date_evaluation").toLocalDate() : null,
                        rs.getString("titre")
                    ));
                }
            }
        }
        return evaluations;
    }

    public String getEvaluationDetails(int idEvaluation) throws SQLException {
        String sql = "SELECT e.id_evaluation, a.titre, a.resume, e.avis, e.date_evaluation, c.nom, c.prenom " +
                     "FROM evaluation e " +
                     "JOIN soumission s ON e.id_soumission = s.id_soumission " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "JOIN evaluateur ev ON e.id_evaluateur = ev.id_evaluateur " +
                     "JOIN chercheur c ON ev.id_evaluateur = c.id_chercheur " +
                     "WHERE e.id_evaluation = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEvaluation);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("Titre de l'article: %s\n\nRésumé: %s\n\nAvis: %s\n\nDate d'évaluation: %s\n\nÉvaluateur: %s %s",
                        rs.getString("titre"),
                        rs.getString("resume"),
                        rs.getString("avis"),
                        rs.getDate("date_evaluation"),
                        rs.getString("prenom"),
                        rs.getString("nom")
                    );
                }
            }
        }
        return "Détails non disponibles";
    }
}

