package DAO;

import DataBase.DatabaseConnection;
import Model.Revue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class revueDAO {


        public List<Revue> getAllRevues() {
        List<Revue> revues = new ArrayList<>();
        String query = "SELECT * FROM revue";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idRevue = resultSet.getInt("id_revue");
                int idEvaluation = resultSet.getInt("id_evaluation");
                String decision = resultSet.getString("decision");

                revues.add(new Revue(idRevue, idEvaluation, decision));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revues;
    }


    public boolean insertRevue(Revue revue) {
        Connection conn = null;
        PreparedStatement pstmtRevue = null;
        PreparedStatement pstmtEvaluation = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 
            
            String sqlRevue = "INSERT INTO revue (id_evaluation, decision) VALUES (?, ?)";
            pstmtRevue = conn.prepareStatement(sqlRevue);
            pstmtRevue.setInt(1, revue.getIdEvaluation());
            pstmtRevue.setString(2, revue.getDecision());
            
            String sqlEvaluation = "UPDATE evaluation SET avis = ? WHERE id_evaluation = ?";
            pstmtEvaluation = conn.prepareStatement(sqlEvaluation);
            pstmtEvaluation.setString(1, revue.getDecision());
            pstmtEvaluation.setInt(2, revue.getIdEvaluation());
            
            int revueRows = pstmtRevue.executeUpdate();
            int evaluationRows = pstmtEvaluation.executeUpdate();
            
            if (revueRows > 0 && evaluationRows > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
            
        } finally {
            try {
                if (pstmtRevue != null) pstmtRevue.close();
                if (pstmtEvaluation != null) pstmtEvaluation.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
