package DAO;

import DataBase.DatabaseConnection;
import Model.Chercheur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvaluateurDAO {

    private static final String QUERY_GET_ALL_EVALUATEURS =
            "SELECT c.id_chercheur, c.nom, c.prenom, c.mail, c.adresse, c.domaine, c.institution " +
            "FROM chercheur c " +
            "INNER JOIN evaluateur e ON c.id_chercheur = e.id_evaluateur";

    public List<Chercheur> getAllEvaluateurs() throws SQLException {
        List<Chercheur> evaluateurs = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_EVALUATEURS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_chercheur");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("mail");
                String adresse = resultSet.getString("adresse");
                String domaine = resultSet.getString("domaine");
                String institution = resultSet.getString("institution");

                Chercheur chercheur = new Chercheur(id, nom, prenom, email, adresse, null, domaine, institution, "Evaluateur");
                evaluateurs.add(chercheur);
            }
        }
        return evaluateurs;
    }
}
