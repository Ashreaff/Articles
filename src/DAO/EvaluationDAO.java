package DAO;

import DataBase.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class EvaluationDAO {


    public boolean insertEvaluationWithEvaluators(int idSoumission, List<Integer> idEvaluateurs) {
    String evaluationSQL = "INSERT INTO evaluation (id_soumission, date_evaluation, evaluer) VALUES (?, ?, ?)";
    String evaluationEvaluateurSQL = "INSERT INTO evaluation_evaluateur (id_evaluation, id_evaluateur) VALUES (?, ?)";

    try (Connection conn = DatabaseConnection.getConnection()) {
        conn.setAutoCommit(false);
        int idEvaluation;
        try (PreparedStatement pstmt = conn.prepareStatement(evaluationSQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idSoumission);
            pstmt.setDate(2, Date.valueOf(java.time.LocalDate.now())); 
            pstmt.setBoolean(3, false); 
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idEvaluation = rs.getInt(1);
                } else {
                    throw new SQLException("Échec de l'insertion dans la table evaluation, aucun ID généré.");
                }
            }
        }
        try (PreparedStatement pstmt = conn.prepareStatement(evaluationEvaluateurSQL)) {
            for (int idEvaluateur : idEvaluateurs) {
                pstmt.setInt(1, idEvaluation);
                pstmt.setInt(2, idEvaluateur);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
        conn.commit();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}





}
