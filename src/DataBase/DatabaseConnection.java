package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion";
    private static final String USER = "root"; 
<<<<<<< HEAD
    private static final String PASSWORD = ""; 
=======
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe MySQL
>>>>>>> 3f63757c77dd1aeb6a4ffca69a82f2f5385fba81

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC non trouvé : " + e.getMessage());
            throw new SQLException("Pilote JDBC non trouvé.");
        }
    }
}
