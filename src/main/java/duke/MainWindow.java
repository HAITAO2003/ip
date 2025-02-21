package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

/**
 * Controller for the main window of the Duke chat application.
 * Handles the UI interactions and connections between the Duke logic and the view.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Initializes the UI components of the main window.
     * This method is automatically called after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(dialogContainer);

        dialogContainer.setSpacing(10);
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setMinWidth(Region.USE_PREF_SIZE);
        dialogContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
        dialogContainer.setMaxWidth(Double.MAX_VALUE);
        dialogContainer.setStyle("-fx-background-color: #F5F5DC;");

        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setVvalue(1.0));
    }

    /**
     * Sets the Duke instance and displays a welcome message.
     *
     * @param d The Duke instance to use for processing commands
     */
    public void setDuke(Duke d) {
        duke = d;
        DialogBox welcomeMessage = DialogBox.getDukeDialog(
                "Hello! I'm Duke\nWhat can I do for you?");
        dialogContainer.getChildren().add(welcomeMessage);
    }

    /**
     * Handles the user input when the send button is clicked or Enter key is pressed.
     * Processes the input through Duke and displays the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getDukeDialog(response)
            );
            userInput.clear();
        }
    }
}