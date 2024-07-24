import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Bibliotheque {
    private HashMap<Integer, Livre> livres;
    private Fonctionnement_Bibliotheque fonctionnement_bibliotheque;

    public Bibliotheque() {
        this.livres = new HashMap<>();
        this.fonctionnement_bibliotheque = new Fonctionnement_Bibliotheque(this);
    }

    // 1. Ajouter un livre
    public void ajouter_Livre(Livre livre) {
        this.livres.put(livre.getId_livre(), livre);
    }

    // 2. Supprimer un livre
    public void supprimer_Livre(int id_livre) {
        this.livres.remove(id_livre);
    }

    // 3. Modifier un livre
    public void modifier_Livre(int id_livre, Livre nouveau_livre) {
        this.livres.put(id_livre, nouveau_livre);
    }

    // 4. Rechercher un livre par son titre ou son nom
    public Livre rechercher_Selon_Titre(String titre) {
        for (Livre livre : this.livres.values()) {
            if (livre.getTitre_livre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    // 5. Lister les livres par ordre alphabétique de a à z
    public List<Livre> arranger_selon_PremierLettre(char premierLettre) {
        List<Livre> resultat_recherche = new ArrayList<>();
        for (Livre livre : this.livres.values()) {
            if (livre.getTitre_livre().toLowerCase().startsWith(String.valueOf(premierLettre))) {
                resultat_recherche.add(livre);
            }
        }
        return resultat_recherche;
    }

    public int getNombre_Livres() {
        return this.livres.size();
    }

    public List<Livre> getAfficher_Par_Categorie(String categorie) {
        List<Livre> livres_Categorie = new ArrayList<>();
        for (Livre livre : this.livres.values()) {
            if (livre.getCategorie_livre().equalsIgnoreCase(categorie)) {
                livres_Categorie.add(livre);
            }
        }
        return livres_Categorie;
    }

    public Livre getAfficher_par_identifiant(int id) {
        return this.livres.get(id);
    }

    public List<Livre> getTrier_Par_Titre() {
        List<Livre> livres_Trie = new ArrayList<>(this.livres.values());
        livres_Trie.sort(Comparator.comparing(Livre::getTitre_livre));
        return livres_Trie;
    }

    public List<Livre> getAfficher_livre_plus_populaires(int nb_livres) {
        List<Livre> livres_Populaires = new ArrayList<>(this.livres.values());
        livres_Populaires.sort(Comparator.comparing(Livre::getScorePopularite).reversed());
        return livres_Populaires.subList(0, Math.min(nb_livres, livres_Populaires.size()));
    }

    public HashMap<Integer,Livre> getLivre() {
        return null;
    }
}
