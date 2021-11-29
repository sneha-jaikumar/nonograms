package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    // Model
    List<Clues> clues = PuzzleLibrary.create();
    Model model = new ModelImpl(clues);

    // Controller
    Controller controller = new ControllerImpl(model);

    // View
    PuzzleView view = new PuzzleView(controller);

    // Make scene
    Scene scene = new Scene(view.render(), 600, 600);
    stage.setScene(scene);

    // Refresh view when model changes
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    // Show the stage
    stage.setTitle("Puzzle View Example");
    stage.show();
  }
}
