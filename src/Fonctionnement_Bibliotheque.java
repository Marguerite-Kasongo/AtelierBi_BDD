import  java.util.List;
public class Fonctionnement_Bibliotheque {
    public static void main(String[] args) {
        Bibliotheque Tech_library = new Bibliotheque();

        // 1. Ajouter des livres
        Tech_library.ajouter_Livre(new Livre(1, "Au centre de la terre", "Harper Lee", "Fiction"));
        Tech_library.ajouter_Livre(new Livre(3, "Mortel Combat", "Walter Isaacson", "Biographie"));
        Tech_library.ajouter_Livre(new Livre(2, "1984", "Selma Margo", "Science Fiction"));
        // 2. Rechercher un livre par titre
        Livre livre = Tech_library.rechercher_Selon_Titre("Au centre de la terre");
        System.out.println(" Votre livre est trouvé : " + livre.getTitre_livre());

        // 3. Lister les livres par lettre initiale
        List<Livre> premierLettre_qui_est_a = Tech_library.arranger_selon_PremierLettre('a');
        System.out.println("Voici les livres commençant par A : " + premierLettre_qui_est_a );

        // 4. Afficher le nombre de livres
        int Nb_Livres = Tech_library.getNombre_Livres();
        System.out.println("Le nombre total de livres est de : " + Nb_Livres);

        // 5. Afficher les livres par catégorie
        List<Livre> livres_Fiction = Tech_library.getAfficher_Par_Categorie("Fiction");
        System.out.println("Voici les livres de la categorie fiction : " + livres_Fiction);

        // 6. Afficher les détails d'un livre par son identifiant
        Livre Identifiant_livre = Tech_library.getAfficher_par_identifiant(2);
        System.out.printf("Voic les détails du livre recherché : %s par %s%n", Identifiant_livre.getTitre_livre(), Identifiant_livre.getAuteur_livre());

        // 7. Trier les livres par titre
        List<Livre> livres_Tri = Tech_library.getTrier_Par_Titre();
        System.out.println("Voici les Livres triés par leur titres et par leurs noms : " + livres_Tri);

        // 8. Afficher les livres les plus populaires
        List<Livre> livres_Populaires = Tech_library.getAfficher_livre_plus_populaires(3);
        System.out.println(" Voici les livres les plus populaires de la bibliotheque : " + livres_Populaires);
    }
}

