import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SauvegarderDonnees {
    private static final String CHEMIN_FICHIER = "donnees_bibliotheque.txt";

    public static void sauvegarderDonnees(HashMap<Integer, Livre> livres) {
        try {
            saveToDisk(livres, CHEMIN_FICHIER);
        } catch (FichierIntrouvableException | ErreurFormatFichierException e) {
            System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    private static void saveToDisk(Map<Integer, Livre> livres, String cheminFichier) throws FichierIntrouvableException, ErreurFormatFichierException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Livre livre : livres.values()) {
                writer.write(livre.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                throw new FichierIntrouvableException(cheminFichier);
            } else {
                throw new ErreurFormatFichierException(cheminFichier, e.getMessage());
            }
        }
    }
}
