package DAO;

import DataBase.DatabaseConnection;
import java.sql.*;

public class AuteurDAO {

    public boolean auteurExists(int idAuteur) throws SQLException {
        String sql = "SELECT COUNT(*) FROM chercheur c " +
                     "JOIN autheur a ON c.id_chercheur = a.id_autheur " +
                     "WHERE c.id_chercheur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAuteur);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public String getAuteurDetails(int idAuteur) throws SQLException {
        String chercheurSql = "SELECT id_chercheur, nom, prenom FROM chercheur WHERE id_chercheur = ?";
        String autheurSql = "SELECT id_autheur FROM autheur WHERE id_autheur = ?";
        StringBuilder details = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check chercheur table
            try (PreparedStatement pstmt = conn.prepareStatement(chercheurSql)) {
                pstmt.setInt(1, idAuteur);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        details.append(String.format("Chercheur trouvé - ID: %d, Nom: %s, Prénom: %s\n",
                                rs.getInt("id_chercheur"),
                                rs.getString("nom"),
                                rs.getString("prenom")));
                    } else {
                        details.append("Aucun chercheur trouvé avec cet ID.\n");
                    }
                }
            }

            // Check autheur table
            try (PreparedStatement pstmt = conn.prepareStatement(autheurSql)) {
                pstmt.setInt(1, idAuteur);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        details.append(String.format("Auteur trouvé - ID: %d\n", rs.getInt("id_autheur")));
                    } else {
                        details.append("Aucun auteur trouvé avec cet ID.\n");
                    }
                }
            }
        }

        return details.toString();
    }
}
