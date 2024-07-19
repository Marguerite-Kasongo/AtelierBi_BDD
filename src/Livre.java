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
        this.scorePopularite = 0; // Initialisation du score de popularité à 0
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

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public void setTitre_livre(String titre_livre) {
        this.titre_livre = titre_livre;
    }

    public void setAuteur_livre(String auteur_livre) {
        this.auteur_livre = auteur_livre;
    }

    public void setCategorie_livre(String categorie_livre) {
        this.categorie_livre = categorie_livre;
    }

    public void incrementerScorePopularite() {
        this.scorePopularite++;
    }
}