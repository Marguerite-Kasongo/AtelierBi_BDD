import java.util.List;

public class Fonctionnement_Bibliotheque {
    private Bibliotheque bibliotheque;

    public Fonctionnement_Bibliotheque(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public Fonctionnement_Bibliotheque() {
        this.bibliotheque = new Bibliotheque();
    }

    public void ajouter_Livre(Livre livre) {
        this.bibliotheque.ajouter_Livre(livre);
    }

    public void supprimer_Livre(int id_livre) {
        this.bibliotheque.supprimer_Livre(id_livre);
    }

    public void run() {
        Bibliotheque Tech_library = new Bibliotheque();
        Fonctionnement_Bibliotheque fonctionnement = new Fonctionnement_Bibliotheque(Tech_library);

        // 3. Lister les livres par lettre initiale
        List<Livre> premierLettre_qui_est_a = Tech_library.arranger_selon_PremierLettre('a');
        System.out.println("Voici les livres commençant par A : " + premierLettre_qui_est_a);

        // 4. Afficher le nombre de livres
        int Nb_Livres = Tech_library.getNombre_Livres();
        System.out.println("Le nombre total de livres est de : " + Nb_Livres);

        // 5. Afficher les livres par catégorie
        List<Livre> livres_Fiction = Tech_library.getAfficher_Par_Categorie("Fiction");
        System.out.println("Voici les livres de la categorie fiction : " + livres_Fiction);

        // 6. Afficher les détails d'un livre par son identifiant
        Livre Identifiant_livre = Tech_library.getAfficher_par_identifiant(2);
        System.out.printf("Voici les détails du livre recherché : %s par %s%n",
                Identifiant_livre.getTitre_livre(),
                Identifiant_livre.getAuteur_livre());

        // 7. Trier les livres par titre
        List<Livre> livres_Tri = Tech_library.getTrier_Par_Titre();
        System.out.println("Voici les Livres triés par leur titres et par leurs noms : " + livres_Tri);

        // 8. Afficher les livres les plus populaires
        List<Livre> livres_Populaires = Tech_library.getAfficher_livre_plus_populaires(3);
        System.out.println(" Voici les livres les plus populaires de la bibliotheque : " + livres_Populaires);
    }
}