import java.sql.*;
import java.util.Properties;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Bibliotheque Tech_library = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);

        // 1. Ajouter des livres
        Tech_library.ajouter_Livre(new Livre(1, "Au centre de la terre", "Harper Lee", "Fiction"));
        Tech_library.ajouter_Livre(new Livre(3, "Mortel Combat", "Walter Isaacson", "Biographie"));

        // Demander à l'utilisateur d'ajouter un nouveau livre
        System.out.print("Entrez l'ID du nouveau livre : ");
        int nouveauId = scanner.nextInt();
        scanner.nextLine(); // Consommer le saut de ligne
        System.out.print("Entrez le titre du nouveau livre : ");
        String nouveauTitre = scanner.nextLine();
        System.out.print("Entrez l'auteur du nouveau livre : ");
        String nouveauAuteur = scanner.nextLine();
        System.out.print("Entrez la catégorie du nouveau livre : ");
        String nouvelleCategorie = scanner.nextLine();
        Tech_library.ajouter_Livre(new Livre(nouveauId, nouveauTitre, nouveauAuteur, nouvelleCategorie));

        // Demander à l'utilisateur de supprimer un livre
        System.out.print("Entrez l'ID du livre à supprimer : ");
        int idLivreASupprimer = scanner.nextInt();
        scanner.nextLine(); // Consommer le saut de ligne
        Tech_library.supprimer_Livre(idLivreASupprimer);

        // Sauvegarder les données dans la base de données
        BDD_Bibliotheque.sauvegarderDonnees(Tech_library);
        System.out.println("Connexion à la base de données réussie.");

        // Appels aux méthodes de la classe Bibliotheque
        // 5. Lister les livres par ordre alphabétique de a à z
        List<Livre> livresParOrdreAlphabetique = Tech_library.arranger_selon_PremierLettre('A');
        System.out.println("Livres par ordre alphabétique :");
        for (Livre livre : livresParOrdreAlphabetique) {
            System.out.println(livre.getTitre_livre());
        }

        // 6. Avoir le nombre de livres
        int nombreLivres = Tech_library.getNombre_Livres();
        System.out.println("Nombre de livres dans la bibliothèque : " + nombreLivres);

        // 7. Afficher les livres par leur catégorie
        List<Livre> livresParCategorie = Tech_library.getAfficher_Par_Categorie("Fiction");
        System.out.println("Livres de la catégorie 'Fiction' :");
        for (Livre livre : livresParCategorie) {
            System.out.println(livre.getTitre_livre());
        }

        // 8. Afficher les détails d'un livre par son identifiant
        Livre livre = Tech_library.getAfficher_par_identifiant(1);
        System.out.println("Détails du livre avec l'ID 1 :");
        System.out.println("Titre : " + livre.getTitre_livre());
        System.out.println("Auteur : " + livre.getAuteur_livre());
        System.out.println("Catégorie : " + livre.getCategorie_livre());

        // 9.a. Trier les livres par leur titre
        List<Livre> livresTries = Tech_library.getTrier_Par_Titre();
        System.out.println("Livres triés par titre :");
        for (Livre l : livresTries) {
            System.out.println(l.getTitre_livre());
        }

        // 9.b. Afficher les livres les plus populaires de la bibliothèque
        List<Livre> livresPopulaires = Tech_library.getAfficher_livre_plus_populaires(5);
        System.out.println("Livres les plus populaires :");
        for (Livre l : livresPopulaires) {
            System.out.println(l.getTitre_livre());
        }
    }
}