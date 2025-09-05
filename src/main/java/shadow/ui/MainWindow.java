package shadow.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shadow.commands.Command;
import shadow.commands.Parser;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    @FXML
    private void handleUserInput() {
        String userTextInput = userInput.getText();

        try {
            Command shadowCommand = Parser.parse(userTextInput);
            if (shadowCommand.isExit()) {
                Platform.exit();
            }
            String shadowResponse = shadowCommand.execute();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userTextInput, userImage),
                    DialogBox.getDukeDialog(shadowResponse, dukeImage)
            );
        } catch (IllegalArgumentException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userTextInput, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        } finally {
            userInput.clear();
        }
    }
}
