import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Bibliotheque {
    private HashMap<Integer, Livre> livres;

    public Bibliotheque() {
        this.livres = new HashMap<>();
    }

    // 1. Ajouter un livre
    public void ajouter_Livre(Livre livre) {
        livres.put(livre.getId_livre(), livre);
    }

    // 2. Supprimer un livre
    public void supprimer_Livre(int id_livre) {
        livres.remove(id_livre);
    }

    // 3. Modifier un livre
    public void modifier_Livre(int id_livre, Livre nouveau_livre) {
        livres.put(id_livre, nouveau_livre);
    }

    // 4. Rechercher un livre par son titre ou son nom
    public Livre rechercher_Selon_Titre(String titre) {
        for (Livre livre : livres.values()) {
            if (livre.getTitre_livre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    // 5. Lister les livres par ordre alphabétique de a à z
    public List<Livre> arranger_selon_PremierLettre(char premeir_lettre) {
        List<Livre> resultat_recherche = new ArrayList<>();
        for (Livre livre : livres.values()) {
            if (Character.toUpperCase(livre.getTitre_livre().charAt(0)) == Character.toUpperCase(premeir_lettre)) {
                resultat_recherche.add(livre);
            }
        }
        return resultat_recherche;
    }

    // 6. Avoir le nombre de livres
    public int getNombre_Livres() {
        return livres.size();
    }

    // 7. Afficher les livres par leur catégorie
    public List<Livre> getAfficher_Par_Categorie(String categorieLivre) {
        List<Livre> resultat_Affichage = new ArrayList<>();
        for (Livre livre : livres.values()) {
            if (livre.getCategorie_livre().equalsIgnoreCase(categorieLivre)) {
                resultat_Affichage.add(livre);
            }
        }
        return resultat_Affichage;
    }

    // 8. Afficher les détails d'un livre par son identifiant
    public Livre getAfficher_par_identifiant(int identifiantLivre) {
        Livre livre = livres.get(identifiantLivre);
        if (livre == null) {
            throw new IllegalArgumentException("Livre introuvable avec l'identifiant : " + identifiantLivre);
        }
        return livre;
    }

    // 9.a. Trier les livres par leur titre ou par leur nom
    public List<Livre> getTrier_Par_Titre() {
        List<Livre> Tries_livres = new ArrayList<>(livres.values());
        Tries_livres.sort(Comparator.comparing(Livre::getTitre_livre));
        return Tries_livres;
    }

    // 9.b. Afficher les livres les plus populaires de la bibliothèque
    public List<Livre> getAfficher_livre_plus_populaires(int populaire) {
        List<Livre> Tries_livres = new ArrayList<>(livres.values());
        Tries_livres.sort(Comparator.comparing(Livre::getScorePopularite).reversed());
        return Tries_livres.subList(0, Math.min(populaire, Tries_livres.size()));
    }

    public void ajouterLivre(Livre livre) {
        livres.put(livre.getId_livre(), livre);
    }

    public void sauvegarderDonnees() {
        SauvegarderDonnees.sauvegarderDonnees(livres);
    }
}

