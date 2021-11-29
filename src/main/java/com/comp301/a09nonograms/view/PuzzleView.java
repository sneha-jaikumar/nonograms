package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.Puzzle;
import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.concurrent.atomic.AtomicInteger;


public class PuzzleView implements FXComponent {
    private final Controller controller;

    public PuzzleView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox layout = new VBox();

        //MessageView
        MessageView messageView = new MessageView(controller);
        layout.getChildren().add(messageView.render());

        GridPane grid = new GridPane();
        grid.setStyle(" -fx-background-color: transparent;");
        for(int i = 0; i < controller.getClues().getHeight(); i++){
            for(int j= 0; j < controller.getClues().getWidth(); j++){
                ToggleButton button = new ToggleButton("    ");
                grid.add(button,j,i,1,1);
                int finalI = i;
                int finalJ = j;
                button.setOnMouseClicked((MouseEvent event) -> {
                    if (event.getButton() == MouseButton.PRIMARY){
                        controller.toggleShaded(finalI, finalJ);
                    }
                    if(event.getButton() == MouseButton.SECONDARY){
                        controller.toggleEliminated(finalI,finalJ);
                    }
                });
                if (controller.isShaded(i,j)){
                    button.setStyle("-fx-background-color: #000000;");
                }
                else if (controller.isEliminated(i,j)){
                    button.setText("X");
                    button.setStyle("-fx-text-fill: red");
                }
            }
        }
        //Solved Message
        if(controller.isSolved()){
            Label solved = new Label("Congratulations, you solved the puzzle!");
            solved.setFont(new Font(20));
            solved.setStyle("-fx-text-fill: green;");
            grid.add(solved,15,3,3,3);
        }


        //column clues
        GridPane grid1 = new GridPane();
        grid1.setMaxWidth(40);
        grid1.setMinWidth(40);
        grid1.setStyle("-fx-background-color: transparent; -fx-padding: 0 0 0 58;");
        for(int i = 0; i< controller.getClues().getColCluesLength(); i++){
            for(int j= 0; j < controller.getClues().getWidth(); j++){

                if(controller.getClues().getColClues(j)[i] == 0){
                    grid1.add(new Label(""),j,i,1,1);
                }
                else{
                    grid1.add(new Label(Integer.toString(controller.getClues().getColClues(j)[i])),j,i,1,1);
                }
            }
        }
        grid1.setHgap(23);

        /*create a grid to the left of the cell grid for row clues(width of this grid should be row clues length, height
        should be puzzle height)*/
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER_LEFT);
        grid2.setStyle("-fx-background-color: transparent;-fx-padding: 4;");
        for(int i = 0; i< controller.getClues().getHeight(); i++){
            for(int j= 0; j < controller.getClues().getRowCluesLength(); j++){
                if(controller.getClues().getRowClues(i)[j] == 0){
                    grid2.add(new Label(""),j,i,1,1);
                }
                else{
                    grid2.add(new Label(Integer.toString(controller.getClues().getRowClues(i)[j])),j,i,1,1);
                }
            }
        }
        grid2.setVgap(10);
        grid2.setHgap(15);


        BorderPane border = new BorderPane();
        border.setLeft(grid2);
        border.setCenter(grid);
        border.setTop(grid1);
        layout.getChildren().add(border);

        //ControlsView
        ControlsView controlsView = new ControlsView(controller);
        layout.getChildren().add(controlsView.render());



        return layout;
    }
}
