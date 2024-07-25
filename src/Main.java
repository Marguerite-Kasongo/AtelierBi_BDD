import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Déclarations précédentes inchangées

    public static void main(String[] args) throws SQLException {
        Bibliotheque Tech_library = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoix des fonctionnalités :");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Sauvegarder les données dans la base de données");
            System.out.println("4. Lister les livres par ordre alphabétique");
            System.out.println("5. Avoir le nombre de livres");
            System.out.println("6. Afficher les livres par catégorie");
            System.out.println("7. Afficher les détails d'un livre");
            System.out.println("8. Trier les livres par titre");
            System.out.println("9. Afficher les livres les plus populaires");
            System.out.println("0. Quitter");
            System.out.print("\nEntrez votre choix (0-9) : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choix) {
                case 1:
                    // 1. Ajouter un nouveau livre
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
                    BDD_Bibliotheque.insererLivreBase(new Livre(nouveauId, nouveauTitre, nouveauAuteur, nouvelleCategorie));
                    break;

                case 2:
                    // 2. Supprimer un livre
                    System.out.print("Entrez l'ID du livre à supprimer : ");
                    int idLivreASupprimer = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    Tech_library.supprimer_Livre(idLivreASupprimer);
                    BDD_Bibliotheque.supprimerLivreBase(idLivreASupprimer);
                    break;

                case 7:
                    // 7. Afficher les détails d'un livre
                    System.out.print("Entrez l'ID du livre à afficher : ");
                    int idLivreAfficher = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    Livre livre = BDD_Bibliotheque.rechercherLivreBase(idLivreAfficher);
                    if (livre != null) {
                        System.out.println("Détails du livre avec l'ID " + idLivreAfficher + ":");
                        System.out.println("Titre : " + livre.getTitre_livre());
                        System.out.println("Auteur : " + livre.getAuteur_livre());
                        System.out.println("Catégorie : " + livre.getCategorie_livre());
                    } else {
                        System.out.println("Livre non trouvé dans la base de données.");
                    }
                    break;
                case 8:
                    // 8. Trier les livres par titre
                    List<Livre> livresTries = Tech_library.getTrier_Par_Titre();
                    System.out.println("Livres triés par titre :");
                    for (Livre l : livresTries) {
                        System.out.println(l.getTitre_livre());
                    }
                    break;

                case 9:
                    // 9. Afficher les livres les plus populaires
                    int nombreLivresPopulaires = 5;
                    List<Livre> livresPopulaires = Tech_library.getAfficher_livre_plus_populaires(nombreLivresPopulaires);
                    System.out.println("Livres les plus populaires :");
                    for (Livre l : livresPopulaires) {
                        System.out.println(l.getTitre_livre());
                    }
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    return;


                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}
