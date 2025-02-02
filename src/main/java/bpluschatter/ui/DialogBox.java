package bpluschatter.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns DialogBox for user.
     *
     * @param text User's message.
     * @param img User's picture.
     * @return DialogBox for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns DialogBox for chatbot.
     *
     * @param text Chatbot's message.
     * @param img Chatbot's picture.
     * @param isError Determine if message is an error message.
     * @return DialogBox for chatbot.
     */
    public static DialogBox getBPlusChatterDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(isError);
        return db;
    }

    /**
     * Changes dialog box style when error occurs.
     *
     * @param isError Error status of message.
     */
    private void changeDialogStyle(boolean isError) {
        if (isError) {
            dialog.getStyleClass().add("error-label");
        }
    }
}
