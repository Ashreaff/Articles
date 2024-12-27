package DAO;

import java.sql.*;

public class AuteurDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion", "root", "");
    }

    public boolean auteurExists(int idAuteur) throws SQLException {
        String sql = "SELECT COUNT(*) FROM chercheur c " +
                     "JOIN autheur a ON c.id_chercheur = a.id_autheur " +
                     "WHERE c.id_chercheur = ?";
        System.out.println("Executing SQL: " + sql + " with ID: " + idAuteur); // Debug print
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAuteur);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Result count: " + count); // Debug print
                    return count > 0;
                }
            }
        }
        return false;
    }

    public String getAuteurDetails(int idAuteur) throws SQLException {
        String chercheurSql = "SELECT id_chercheur, nom, prenom FROM chercheur WHERE id_chercheur = ?";
        String autheurSql = "SELECT id_autheur FROM autheur WHERE id_autheur = ?";
        
        StringBuilder details = new StringBuilder();
        
        try (Connection conn = getConnection()) {
            // Check chercheur table
            System.out.println("Executing SQL: " + chercheurSql + " with ID: " + idAuteur); // Debug print
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
            System.out.println("Executing SQL: " + autheurSql + " with ID: " + idAuteur); // Debug print
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

