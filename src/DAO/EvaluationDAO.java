package DAO;

import DataBase.DatabaseConnection;
import Model.EvaluateurInfo;
import Model.Evaluation;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationDAO {


public boolean insertEvaluationWithEvaluators(int idSoumission, List<Integer> idEvaluateurs) {
    String evaluationSQL = "INSERT INTO evaluation (id_soumission, date_evaluation, evaluer) VALUES (?, ?, ?)";
    String evaluationEvaluateurSQL = "INSERT INTO evaluation_evaluateur (id_evaluation, id_evaluateur) VALUES (?, ?)";
    String updateSoumissionSQL = "UPDATE soumission SET affecter = ? WHERE id_soumission = ?";
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
        try (PreparedStatement pstmt = conn.prepareStatement(updateSoumissionSQL)) {
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, idSoumission);
            pstmt.executeUpdate();
        }
        conn.commit();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


public List<Evaluation> getEvaluationsWithEvaluerTrueAndEvaluators() {
    String query = "SELECT e.id_evaluation, e.id_soumission, e.avis AS evaluation_avis, e.date_evaluation, e.evaluer, " +
                   "ee.id_evaluateur, c.nom AS evaluateur_nom, c.prenom AS evaluateur_prenom, " +
                   "ee.avis AS evaluateur_avis, ee.Remarque AS evaluateur_remarque " +
                   "FROM evaluation e " +
                   "JOIN evaluation_evaluateur ee ON e.id_evaluation = ee.id_evaluation " +
                   "JOIN evaluateur ev ON ee.id_evaluateur = ev.id_evaluateur " +
                   "JOIN chercheur c ON ev.id_evaluateur = c.id_chercheur " +
                   "WHERE e.evaluer = true AND e.avis IS NULL";
    
    List<Evaluation> evaluations = new ArrayList<>();
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
         
        Map<Integer, Evaluation> evaluationMap = new HashMap<>();
        
        while (rs.next()) {
            int idEvaluation = rs.getInt("id_evaluation");
            if (!evaluationMap.containsKey(idEvaluation)) {
                Evaluation evaluation = new Evaluation(
                    idEvaluation,
                    rs.getInt("id_soumission"),
                    rs.getString("evaluation_avis"),
                    rs.getDate("date_evaluation").toLocalDate(),
                    rs.getBoolean("evaluer"),
                    new ArrayList<>()
                );
                evaluationMap.put(idEvaluation, evaluation);
            }
            
            Evaluation evaluation = evaluationMap.get(idEvaluation);
            
            EvaluateurInfo evaluator = new EvaluateurInfo(
                rs.getInt("id_evaluateur"),
                rs.getString("evaluateur_nom"),
                rs.getString("evaluateur_prenom"),
                rs.getString("evaluateur_avis"), 
                rs.getString("evaluateur_remarque")
            );
            
            evaluation.getEvaluateurs().add(evaluator); // Store the full EvaluateurInfo object
        }
        
        evaluations.addAll(evaluationMap.values());
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return evaluations;
}






}
