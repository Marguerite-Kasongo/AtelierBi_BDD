public class LimiteAtteintException extends Exception {
    public LimiteAtteintException(int limiteMaximale) {
        super("La limite maximale de " + limiteMaximale + " livres a été atteinte.");
    }
}