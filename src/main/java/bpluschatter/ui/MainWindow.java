package bpluschatter.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BPlusChatter bPlusChatter;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bPlusChatterImage = new Image(this.getClass().getResourceAsStream("/images/BPlusChatter.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getBPlusChatterDialog(
                "Hello! I'm BPlusChatter :)\nWhat can I do for you?",
                bPlusChatterImage, false));
    }

    /** Injects the BPlusChatter instance */
    public void setBPlusChatter(BPlusChatter bPlusChatter) {
        this.bPlusChatter = bPlusChatter;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing BPlusChatter's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bPlusChatter.run(input);
        boolean isError = bPlusChatter.getError();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBPlusChatterDialog(response, bPlusChatterImage, isError)
        );
        userInput.clear();
    }
}
