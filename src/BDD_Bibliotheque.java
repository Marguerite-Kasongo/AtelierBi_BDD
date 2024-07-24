import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class BDD_Bibliotheque {
    public static void sauvegarderDonnees(Bibliotheque bibliotheque) {
        try {
            // Connexion à la base de données
            Connection conn = getConnection();

            // Créer les tables si elles n'existent pas
            createTables(conn);

            // Insérer les livres dans la base de données
            insertLivres(conn, bibliotheque.getLivre());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        // Configuration de la connexion à la base de données
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        return DriverManager.getConnection("jdbc:postgresql://localhost:3306/mysql", props);
    }

    private static void createTables(Connection conn) throws SQLException {
        String createTableLivre = "CREATE TABLE IF NOT EXISTS livre (" +
                "id_livre INT PRIMARY KEY," +
                "titre_livre VARCHAR(255)," +
                "auteur_livre VARCHAR(255)," +
                "categorie_livre VARCHAR(255))";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableLivre);
        }
    }

    private static void insertLivres(Connection conn, HashMap<Integer, Livre> livres) throws SQLException {
        String insertQuery = "INSERT INTO livre (id_livre, titre_livre, auteur_livre, categorie_livre) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            for (Livre livre : livres.values()) {
                pstmt.setInt(1, livre.getId_livre());
                pstmt.setString(2, livre.getTitre_livre());
                pstmt.setString(3, livre.getAuteur_livre());
                pstmt.setString(4, livre.getCategorie_livre());
                pstmt.executeUpdate();
            }
        }
    }
}