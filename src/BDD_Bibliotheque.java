import java.sql.*;
import java.util.HashMap;

public class BDD_Bibliotheque {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void sauvegarderDonnees(Bibliotheque bibliotheque) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createTables(conn);
            insertLivres(conn, bibliotheque.getLivre());
        }
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

    public static void insererLivreBase(Livre livre) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO livre (id_livre, titre_livre, auteur_livre, categorie_livre) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, livre.getId_livre());
                pstmt.setString(2, livre.getTitre_livre());
                pstmt.setString(3, livre.getAuteur_livre());
                pstmt.setString(4, livre.getCategorie_livre());
                pstmt.executeUpdate();
            }
        }
    }

    public static void supprimerLivreBase(int idLivreASupprimer) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String deleteQuery = "DELETE FROM livre WHERE id_livre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, idLivreASupprimer);
                pstmt.executeUpdate();
            }
        }
    }

    public static Livre rechercherLivreBase(int idLivre) throws SQLException {
        Livre livre = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM livre WHERE id_livre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                pstmt.setInt(1, idLivre);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id_livre");
                        String titre = rs.getString("titre_livre");
                        String auteur = rs.getString("auteur_livre");
                        String categorie = rs.getString("categorie_livre");
                        livre = new Livre(id, titre, auteur, categorie);
                    }
                }
            }
        }
        return livre;
    }
}
