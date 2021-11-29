package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class MessageView implements FXComponent{
    private final Controller controller;

    public MessageView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        BorderPane layout = new BorderPane();
        VBox messageBox = new VBox();
        Label puzzleNumber = new Label("Puzzle: " + (controller.getPuzzleIndex() + 1) + "/" + controller.getPuzzleCount());
        puzzleNumber.setStyle("-fx-padding: 30 10 7 5;");
        puzzleNumber.setFont(new Font(30));
        VBox solvedMessage = new VBox();
        messageBox.getChildren().add(puzzleNumber);

        messageBox.setSpacing(10);
        layout.setRight(messageBox);
        layout.setCenter(solvedMessage);


        return  layout;

    }
}
