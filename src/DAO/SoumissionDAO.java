package DAO;

import DataBase.DatabaseConnection;
import Model.Soumission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoumissionDAO {

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
                            rs.getString("titre"), // Récupère le titre
                            rs.getInt("taille") // Récupère la taille
                    ));
                }
            }
        }
        return soumissions;
    }

    // Fonction pour récupérer les détails d'une soumission spécifique
    public String getSoumissionDetails(int idSoumission) throws SQLException {
        String sql = "SELECT s.id_soumission, a.titre, a.resume, a.taille, s.date_soumission, s.affecter, s.id_correspondant " +
                     "FROM soumission s " +
                     "JOIN article a ON s.id_article = a.id_article " +
                     "WHERE s.id_soumission = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSoumission);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("ID Soumission: %d\nTitre: %s\nRésumé: %s\nTaille de l'article: %d\nDate de soumission: %s\nID Correspondant: %d\nAffectée: %s",
                            rs.getInt("id_soumission"),
                            rs.getString("titre"),
                            rs.getString("resume"),
                            rs.getInt("taille"),  // Récupère la taille de l'article
                            rs.getDate("date_soumission").toString(),
                            rs.getInt("id_correspondant"),
                            rs.getBoolean("affecter") ? "Oui" : "Non"
                    );
                }
            }
        }
        return "Détails non disponibles";
    }
}
