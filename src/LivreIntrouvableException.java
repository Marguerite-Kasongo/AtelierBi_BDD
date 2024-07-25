public class LivreIntrouvableException extends Exception {
    public LivreIntrouvableException(int idLivre) {
        super("Le livre avec l'ID " + idLivre + " n'a pas été trouvé dans la bibliothèque.");
    }
}
