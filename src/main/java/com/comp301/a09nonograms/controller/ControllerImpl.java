package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

public class ControllerImpl implements Controller {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  /** Gets the clues for the active puzzle board */
  public Clues getClues() {

    int[][] rowClues = new int[model.getHeight()][model.getRowCluesLength()];
    int[][] colClues = new int[model.getWidth()][model.getColCluesLength()];

    for (int i = 0; i < model.getHeight(); i++) {
      rowClues[i] = model.getRowClues(i);
    }
    for (int j = 0; j < model.getWidth(); j++) {
      colClues[j] = model.getColClues(j);
    }

    Clues modelClues = new CluesImpl(rowClues, colClues);
    return modelClues;
  }

  /** Returns true only if the active puzzle board satisfies the puzzle's clues */
  public boolean isSolved() {
    return model.isSolved();
  }

  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  public void nextPuzzle() {
    if (model.getPuzzleIndex() + 1 < model.getPuzzleCount()) {
      model.setPuzzleIndex(model.getPuzzleIndex() + 1);
    } else {
      model.setPuzzleIndex(0);
    }
  }

  public void prevPuzzle() {
    if (model.getPuzzleIndex() - 1 >= 0) {
      model.setPuzzleIndex(model.getPuzzleIndex() - 1);
    } else {
      model.setPuzzleIndex(model.getPuzzleCount() - 1);
    }
  }

  public void randPuzzle() {
    int newIndex = model.getPuzzleIndex();
    while(newIndex == model.getPuzzleIndex()){
      newIndex = (int) Math.floor(Math.random() * ((model.getPuzzleCount() - 1) + 1));
    }
    model.setPuzzleIndex(newIndex);
  }

  public void clearBoard() {
    model.clear();
  }

  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
