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

import java.util.Objects;

/**
 * Represents the main window of the application that serves as the primary user interface.
 * This class extends {@code AnchorPane} and handles user interactions as well as output display.
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

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.jpg"))
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    /**
     * Initializes the main UI parts within the {@code MainWindow} class.
     * This method sets up a binding between the vertical scrolling value of the
     * {@code ScrollPane} and the height of the {@code VBox} dialog container.
     * Ensures that the scroll position automatically adjusts to display the
     * most recently added dialog at the bottom of the container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Handles user input from the text field and processes it as a command.
     * This method first retrieves the user input, parses it into a command using the {@code Parser} class,
     * executes the command, and updates the dialog container with appropriate responses. If the command
     * signals an exit condition, the application is terminated.
     * <p>
     * In case of an invalid input (e.g., unrecognized or malformed command), an error message
     * is displayed in the dialog container. The method ensures that the user's input field is
     * cleared after the command is handled, regardless of the outcome.
     * <p>
     * Key operations include:
     * - Parsing user input into a {@code Command} object.
     * - Executing the parsed command and capturing its response.
     * - Adding dialog boxes to the {@code dialogContainer} to display both the user's input and the application's response.
     * - Handling {@code IllegalArgumentException} to display meaningful error messages for invalid inputs.
     * - Clearing the user input field after processing.
     * <p>
     * This method is triggered by an associated event in the JavaFX interface, typically tied
     * to a button click or pressing the Enter key when the input field is focused.
     */
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
