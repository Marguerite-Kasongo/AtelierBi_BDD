public class ErreurFormatFichierException extends Exception {
    public ErreurFormatFichierException(String cheminFichier, String messageErreur) {
        super("Erreur de format dans le fichier " + cheminFichier + " : " + messageErreur);
    }
}