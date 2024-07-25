public class FichierIntrouvableException extends Exception {
    public FichierIntrouvableException(String cheminFichier) {
        super("Le fichier " + cheminFichier + " est introuvable.");
    }
}