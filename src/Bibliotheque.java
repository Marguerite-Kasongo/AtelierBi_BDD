import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
    }

    public void ajouter_Livre(Livre livre) {
        livres.add(livre);
    }

    public void supprimer_Livre(int idLivre) {
        livres.removeIf(l -> l.getId_livre() == idLivre);
    }

    public List<Livre> arranger_selon_PremierLettre(char premiereLetre) {
        List<Livre> livresTriesAlpha = new ArrayList<>(livres);
        livresTriesAlpha.sort(Comparator.comparing(Livre::getTitre_livre));
        return livresTriesAlpha;
    }

    public int getNombre_Livres() {
        return livres.size();
    }

    public List<Livre> getAfficher_Par_Categorie(String categorie) {
        List<Livre> livresParCategorie = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getCategorie_livre().equalsIgnoreCase(categorie)) {
                livresParCategorie.add(livre);
            }
        }
        return livresParCategorie;
    }

    public Livre getAfficher_par_identifiant(int idLivre) {
        return livres.stream()
                .filter(l -> l.getId_livre() == idLivre)
                .findFirst()
                .orElse(null);
    }

    public List<Livre> getTrier_Par_Titre() {
        List<Livre> livresTries = new ArrayList<>(livres);
        livresTries.sort(Comparator.comparing(Livre::getTitre_livre));
        return livresTries;
    }

    public List<Livre> getAfficher_livre_plus_populaires(int nombreLivres) {
        List<Livre> livresPopulaires = new ArrayList<>(livres);
        livresPopulaires.sort(Comparator.comparing(Livre::getScorePopularite).reversed());
        return livresPopulaires.subList(0, Math.min(nombreLivres, livresPopulaires.size()));
    }

    public HashMap<Integer,Livre> getLivre() {
        return null;
    }
}