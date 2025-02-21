package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label avatar;

    private DialogBox(String text, String avatarText) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up the dialog label
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setPrefWidth(300.0);
        dialog.setMaxWidth(300.0);
        dialog.setPadding(new Insets(10));
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #E8E8E8;");

        // Set up the avatar label
        avatar.setText(avatarText);
        avatar.setAlignment(Pos.CENTER);
        avatar.setTextAlignment(TextAlignment.CENTER);
        avatar.setMinSize(40, 40);
        avatar.setMaxSize(40, 40);
        avatar.setStyle("-fx-background-radius: 20; -fx-background-color: #7289DA; -fx-text-fill: white; -fx-font-weight: bold;");

        // Set HBox properties
        this.setSpacing(10);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setFillHeight(true);
        this.setMinHeight(USE_PREF_SIZE);
        this.setPrefWidth(Region.USE_COMPUTED_SIZE);
        this.setMaxWidth(Double.MAX_VALUE);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #DCF8C6;");
    }

    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, "U");
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.setAlignment(Pos.CENTER_RIGHT);
        db.dialog.setStyle("-fx-background-radius: 15; -fx-background-color: #ADD8E6; -fx-text-fill: black;");
        db.avatar.setStyle("-fx-background-radius: 20; -fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-weight: bold;");
        return db;
    }

    public static DialogBox getDukeDialog(String text) {
        var db = new DialogBox(text, "D");
        db.avatar.setStyle("-fx-background-radius: 20; -fx-background-color: #9B59B6; -fx-text-fill: white; -fx-font-weight: bold;");
        db.flip();
        return db;
    }
}