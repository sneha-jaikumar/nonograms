package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlsView implements FXComponent{
    private final Controller controller;

    public ControlsView(Controller controller) {
            this.controller = controller;
        }

    @Override
    public Parent render() {

        HBox layout = new HBox();
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setSpacing(15);
        layout.setStyle("-fx-padding: 70 7 7 10;");

        Button prevPuzzle = new Button("Previous");
        layout.getChildren().add(prevPuzzle);
        prevPuzzle.setOnAction((ActionEvent event) -> {
            controller.prevPuzzle();
        });


        Button randPuzzle = new Button("Randomize");
        layout.getChildren().add(randPuzzle);
        randPuzzle.setOnAction((ActionEvent event) -> {
            controller.randPuzzle();
        });

        Button clear = new Button("Clear");
        layout.getChildren().add(clear);
        clear.setOnAction((ActionEvent event) -> {
            controller.clearBoard();
        });


        Button nextPuzzle = new Button("Next");
        layout.getChildren().add(nextPuzzle);
        nextPuzzle.setOnAction((ActionEvent event) -> {
            controller.nextPuzzle();
        });



        return layout;
    }
}
