import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BibliothequeController {

    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField categoryField;
    private Bibliotheque techLibrary;

    @FXML
    private void showAddBookDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Ajouter un livre");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 300, 250);
        TextField idField = new TextField();
        idField.setPromptText("ID du livre");
        TextField titleField = new TextField();
        titleField.setPromptText("Titre du livre");
        TextField authorField = new TextField();
        authorField.setPromptText("Auteur du livre");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Catégorie du livre");
        Button submitButton = new Button("Ajouter");
        submitButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                Livre newBook = new Livre(id, title, author, category);
                techLibrary.ajouter_Livre(newBook);
                BDD_Bibliotheque.insererLivreBase(newBook);
                dialog.close();
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                showAlert("Erreur", "Veuillez entrer des informations valides.");
            }
        });
        vbox.getChildren().addAll(idField, titleField, authorField, categoryField, submitButton);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void showDeleteBookDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Supprimer un livre");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 300, 150);
        TextField idField = new TextField();
        idField.setPromptText("ID du livre à supprimer");
        Button submitButton = new Button("Supprimer");
        submitButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                techLibrary.supprimer_Livre(id);
                BDD_Bibliotheque.supprimerLivreBase(id);
                dialog.close();
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                showAlert("Erreur", "Veuillez entrer un ID valide.");
            }
        });
        vbox.getChildren().addAll(idField, submitButton);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void saveData() {
        showAlert("Action non implémentée", "Fonctionnalité de sauvegarde des données à implémenter.");
    }

    @FXML
    private void listBooks() {
        showAlert("Action non implémentée", "Fonctionnalité de lister les livres à implémenter.");
    }

    @FXML
    private void countBooks() {
        showAlert("Action non implémentée", "Fonctionnalité de compter les livres à implémenter.");
    }

    @FXML
    private void showBooksByCategoryDialog() {
        showAlert("Action non implémentée", "Fonctionnalité d'afficher les livres par catégorie à implémenter.");
    }

    @FXML
    private void showBookDetailsDialog() {
        showAlert("Action non implémentée", "Fonctionnalité d'afficher les détails d'un livre à implémenter.");
    }

    @FXML
    private void sortBooks() {
        showAlert("Action non implémentée", "Fonctionnalité de trier les livres à implémenter.");
    }

    @FXML
    private void showPopularBooks() {
        showAlert("Action non implémentée", "Fonctionnalité d'afficher les livres populaires à implémenter.");
    }

    @FXML
    private void exitApplication() {
        showAlert("Action non implémentée", "Fonctionnalité de quitter l'application à implémenter.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}