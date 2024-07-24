import java.util.Objects;

public class Livre {
    private int id_livre;
    private String titre_livre;
    private String auteur_livre;
    private String categorie_livre;
    private int scorePopularite;

    public Livre(int id_livre, String titre_livre, String auteur_livre, String categorie_livre) {
        this.id_livre = id_livre;
        this.titre_livre = titre_livre;
        this.auteur_livre = auteur_livre;
        this.categorie_livre = categorie_livre;
        this.scorePopularite = 0;
    }

    public int getId_livre() {
        return id_livre;
    }

    public String getTitre_livre() {
        return titre_livre;
    }

    public String getAuteur_livre() {
        return auteur_livre;
    }

    public String getCategorie_livre() {
        return categorie_livre;
    }

    public int getScorePopularite() {
        return scorePopularite;
    }

    public void setScorePopularite(int scorePopularite) {
        this.scorePopularite = scorePopularite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return id_livre == livre.id_livre && scorePopularite == livre.scorePopularite && Objects.equals(titre_livre, livre.titre_livre) && Objects.equals(auteur_livre, livre.auteur_livre) && Objects.equals(categorie_livre, livre.categorie_livre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_livre, titre_livre, auteur_livre, categorie_livre, scorePopularite);
    }
}