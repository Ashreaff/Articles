package DAO;

import Model.Soumission;
import DataBase.DatabaseConnection;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoumissionDAO {

<<<<<<< HEAD
    // Fonction pour récupérer toutes les soumissions
    public List<Soumission> getAllSoumissions() throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille " + // Ajout du titre et de la taille de l'article
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
                        rs.getString("titre"), // Récupère le titre
                        rs.getInt("taille") // Récupère la taille
                ));
            }
        }
        return soumissions;
    }

    // Fonction pour récupérer les soumissions non affectées
    public List<Soumission> getSoumissionsNonAffectees() throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille " + // Ajout du titre et de la taille de l'article
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
                        rs.getString("titre"), // Récupère le titre
                        rs.getInt("taille") // Récupère la taille
                ));
            }
        }
        return soumissions;
    }

    // Fonction pour récupérer les soumissions par correspondant (auteur)
    public List<Soumission> getSoumissionsByCorrespondant(int idCorrespondant) throws SQLException {
        List<Soumission> soumissions = new ArrayList<>();
        String sql = "SELECT s.id_soumission, s.id_article, s.id_correspondant, s.date_soumission, s.affecter, " +
                     "a.titre, a.taille " + // Ajout du titre et de la taille de l'article
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.id_correspondant = ?";
=======
    private static final Logger LOGGER = Logger.getLogger(SoumissionDAO.class.getName());
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81


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
<<<<<<< HEAD
                while (rs.next()) {
                    soumissions.add(new Soumission(
                            rs.getInt("id_soumission"),
                            rs.getInt("id_article"),
                            rs.getInt("id_correspondant"),
                            rs.getDate("date_soumission").toLocalDate(),
                            rs.getBoolean("affecter"),
                            rs.getString("titre"), // Récupère le titre
                            rs.getInt("taille") // Récupère la taille
                    ));
                }
=======
            while (rs.next()) {
                Soumission soumission = new Soumission();
                soumission.setIdSoumission(rs.getInt("id_soumission"));
                soumission.setIdArticle(rs.getInt("id_article"));
                soumission.setIdCorrespondant(rs.getInt("id_correspondant"));
                soumission.setDateSoumission(rs.getDate("date_soumission").toLocalDate());
                soumission.setAffecter(rs.getBoolean("affecter"));
                soumissions.add(soumission);
            }
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
            }
        }
        return soumissions;
    }
<<<<<<< HEAD

    // Fonction pour récupérer les détails d'une soumission spécifique
    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.id_soumission, a.titre, a.resume, a.taille, s.date_soumission, s.affecter, s.id_correspondant " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.id_soumission = ?";

=======
    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.*, a.titre, a.resume FROM soumission s JOIN article a ON s.id_article = a.id_article WHERE s.id_soumission = ?";
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSoumission);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
<<<<<<< HEAD
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nTaille de l'article: %d\nDate de soumission: %s\nID Correspondant: %d\nAffectée: %s",
                            rs.getInt("id_soumission"),
                            rs.getString("titre"),
                            rs.getString("resume"),
                            rs.getInt("taille"),  // Récupère la taille de l'article
                            rs.getDate("date_soumission").toString(),
                            rs.getInt("id_correspondant"),
                            rs.getBoolean("affecter") ? "Oui" : "Non"
=======
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nDate de soumission: %s\nAffecté: %s",
                        rs.getInt("id_soumission"),
                        rs.getString("titre"),
                        rs.getString("resume"),
                        rs.getDate("date_soumission"),
                        rs.getBoolean("affecter") ? "Oui" : "Non"
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81
                    );
                }
            }
        }
        return "Détails non disponibles";
    }
    
}

