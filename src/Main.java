import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private void handle(ActionEvent e) {
        listBooks();
    }

    private final Bibliotheque techLibrary = new Bibliotheque();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bibliothèque");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Ajouter des boutons pour chaque fonctionnalité
        Button addButton = new Button("Ajouter un livre");
        Button deleteButton = new Button("Supprimer un livre");
        Button saveButton = new Button("Sauvegarder les données");
        saveButton.setOnAction(e -> saveData());
        Button listButton = new Button("Lister les livres");
        listButton.setOnAction(e -> listBooks());
        Button countButton = new Button("Nombre de livres");
        Button categoryButton = new Button("Afficher par catégorie");
        Button detailsButton = new Button("Afficher les détails");
        Button sortButton = new Button("Trier les livres");
        Button popularButton = new Button("Livres populaires");
        Button exitButton = new Button("Quitter");

        addButton.setOnAction(e -> showAddBookDialog());
        deleteButton.setOnAction(e -> showDeleteBookDialog());
        countButton.setOnAction(e -> countBooks());
        categoryButton.setOnAction(e -> showBooksByCategoryDialog());
        detailsButton.setOnAction(e -> showBookDetailsDialog());
        sortButton.setOnAction(e -> sortBooks());
        popularButton.setOnAction(e -> showPopularBooks());
        exitButton.setOnAction(e -> primaryStage.close());

        vbox.getChildren().addAll(addButton, deleteButton, saveButton, listButton, countButton, categoryButton, detailsButton, sortButton, popularButton, exitButton);

        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private void saveData() {
        try {
            BDD_Bibliotheque.sauvegarderDonnees(techLibrary);
        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert("Erreur", "Erreur lors de la sauvegarde des données.");
        }
    }

    private void listBooks() {
        List<Livre> books = (List<Livre>) techLibrary.getLivres();
        String sb = books.stream().map(book -> book.getTitre_livre() + "\n").collect(Collectors.joining());
        showAlert("Livres", sb);
    }

    private void countBooks() {
        int count = techLibrary.getLivres().size();
        showAlert("Nombre de livres", "Il y a " + count + " livres.");
    }

    private void showBooksByCategoryDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Afficher les livres par catégorie");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 300, 150);

        TextField categoryField = new TextField();
        categoryField.setPromptText("Catégorie");

        Button submitButton = new Button("Afficher");
        submitButton.setOnAction(e -> {
            String category = categoryField.getText();
            List<Livre> books = techLibrary.getLivresParCategorie(category);
            StringBuilder sb = new StringBuilder();
            for (Livre book : books) {
                sb.append(book.getTitre_livre()).append("\n");
            }
            showAlert("Livres par catégorie", sb.toString());
            dialog.close();
        });

        vbox.getChildren().addAll(categoryField, submitButton);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showBookDetailsDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Afficher les détails du livre");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 300, 150);

        TextField idField = new TextField();
        idField.setPromptText("ID du livre");

        Button submitButton = new Button("Afficher");
        submitButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Livre book = BDD_Bibliotheque.rechercherLivreBase(id);
                if (book != null) {
                    String details = String.format("Titre: %s\nAuteur: %s\nCatégorie: %s",
                            book.getTitre_livre(), book.getAuteur_livre(), book.getCategorie_livre());
                    showAlert("Détails du livre", details);
                } else {
                    showAlert("Erreur", "Livre non trouvé.");
                }
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

    private void sortBooks() {
        List<Livre> sortedBooks = techLibrary.getTrier_Par_Titre();
        StringBuilder sb = new StringBuilder();
        for (Livre book : sortedBooks) {
            sb.append(book.getTitre_livre()).append("\n");
        }
        showAlert("Livres triés par titre", sb.toString());
    }

    private void showPopularBooks() {
        List<Livre> popularBooks = techLibrary.getAfficher_livre_plus_populaires(5);
        StringBuilder sb = new StringBuilder();
        for (Livre book : popularBooks) {
            sb.append(book.getTitre_livre()).append("\n");
        }
        showAlert("Livres populaires", sb.toString());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}

