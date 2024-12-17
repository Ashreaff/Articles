// package Model;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import dataBase.DatabaseConnector;

// public class Chercheur extends Utilisateur {
//     private String domaine;
//     private String institution;
//     private Connection con;

//     // Constructeur
//     public Chercheur(int id, String nom, String prenom, String adresse, String userName, String password, String domaine, String institution) {
//         super(id, nom, prenom, adresse, userName, password);
//         this.domaine = domaine;
//         this.institution = institution;
//         DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
//         con = dbc.getConnection();
//     }

//     public Chercheur() {
//         super(0, null, null, null, null, null);
//         DatabaseConnector dbc = DatabaseConnector.getDatabaseConnection();
//         con = dbc.getConnection();
//     }

//     // Getters et Setters
//     public String getDomaine() {
//         return domaine;
//     }

//     public void setDomaine(String domaine) {
//         this.domaine = domaine;
//     }

//     public String getInstitution() {
//         return institution;
//     }

//     public void setInstitution(String institution) {
//         this.institution = institution;
//     }

//     // Méthode pour récupérer un chercheur par ID
//     public Chercheur getChercheurById(int id) {
//         Chercheur chercheur = null;
//         String query = "SELECT * FROM chercheur WHERE id_chercheur = ?";
//         try (PreparedStatement ps = con.prepareStatement(query)) {
//             ps.setInt(1, id);
//             ResultSet rs = ps.executeQuery();
//             if (rs.next()) {
//                 chercheur = new Chercheur(
//                     rs.getInt("id_chercheur"),
//                     rs.getString("nom"),
//                     rs.getString("prenom"),
//                     rs.getString("adresse"),
//                     rs.getString("userName"),
//                     null, // Password non récupéré pour des raisons de sécurité
//                     rs.getString("domaine"),
//                     rs.getString("institution")
//                 );
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return chercheur;
//     }
// }
