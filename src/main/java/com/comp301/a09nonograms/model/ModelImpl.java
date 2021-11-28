package com.comp301.a09nonograms.model;

import com.comp301.a09nonograms.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ModelImpl implements Model {
  private List<Puzzle> availablePuzzles;
  private int index;
  private ArrayList<ModelObserver> activeObservers;

  public ModelImpl(List<Clues> clues) {
    availablePuzzles = new ArrayList<>();
    activeObservers = new ArrayList<>();
    // initialize index
    for (Clues clue : clues) {
      availablePuzzles.add(new Puzzle(clue));
    }
  }

  public int getPuzzleCount() {
    return availablePuzzles.size();
  }

  public int getPuzzleIndex() {
    // null pointer exception potentially?
    return index;
  }

  public void setPuzzleIndex(int index) {
    // illegal argument exception?
    this.index = index;
  }

  public void addObserver(ModelObserver observer) {
    activeObservers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    // what if it's not there?
      if(!activeObservers.contains(observer)){
          throw new NoSuchElementException();
      }
      else{
          activeObservers.remove(observer);
      }
  }

  private void notifyObservers() {
    for (ModelObserver observer : activeObservers) {
      observer.update(this);
    }
  }

  public boolean isSolved() {
    // computation. go through and check if everything is shaded
    // checking if the column clues were done right for each column
    for (int col = 0; col < this.getWidth(); col++) {
      int clueNum = 0;
      int tally = 0;
      boolean streak = false;
      int clue = this.getColClues(col)[clueNum];
      int numClues = this.getColCluesLength();
      for (int row = 0; row < this.getHeight(); row++) {
        while (clue == 0) {
          if (clueNum < numClues - 1) {
            clueNum++;
            clue = this.getColClues(col)[clueNum];
          } else {
            break;
          }
        }

        if (this.isShaded(row, col)) {
          streak = true;
          tally += 1;
          // safe = true;
        } else if (!this.isShaded(row, col) && (streak == true)) {
          if (tally != clue) {
            // notifyObservers();
            return false;
          } else if (clueNum < numClues - 1) {
            clueNum++;
            clue = this.getColClues(col)[clueNum];
            tally = 0;
            streak = false;
          } else {
            break;
          }
        }
      }
      // check last cell
      // if(tally != 0){
      if (tally != clue && clue != 0) {
        // notifyObservers();
        return false;
      }
      // }
    }

    // checking if the row clues were done right for each row
    for (int row = 0; row < this.getHeight(); row++) {
      int clueNum = 0;
      int tally = 0;
      boolean streak = false;
      int clue = this.getRowClues(row)[clueNum];
      int numClues = this.getRowCluesLength();
      for (int col = 0; col < this.getWidth(); col++) {
        while (clue == 0) {
          if (clueNum < numClues - 1) {
            clueNum++;
            clue = this.getRowClues(row)[clueNum];
          } else {
            break;
          }
        }

        if (this.isShaded(row, col)) {
          streak = true;
          tally += 1;
        } else if (!this.isShaded(row, col) && (streak == true || clue == 0)) {
          if (tally != clue) {
            // notifyObservers();
            return false;
          } else if (clueNum < numClues - 1) {
            clueNum++;
            clue = this.getRowClues(row)[clueNum];
            tally = 0;
            streak = false;
          } else {
            break;
          }
        }
      }
      // check last cell
      //            if(tally != 0){
      if (tally != clue && clue != 0) {
        // notifyObservers();
        return false;
      }
      // }
    }

    /*go to first column of puzzle. check if that column meet the clue for that column
        so if clue is all 0's, check for NO shaded, so all either spaces or eliminated
        if clue is of the 00x pattern, check for x shaded ones TOUCHING each other
        if clue is of the 0xy pattern, check for x shaded ones TOUCHING, then at least ONE space,
            then y shaded ones TOUCHING each other
        if clue is of the xyz pattern, check for x shaded ones TOUCHING, then at least ONE space,
            then y shaded ones TOUCHING each other, then at least ONE space, than z shaded ones TOUCHING each other
        continue this however many times...
    repeat this for each column
    that do the same for each row
     */
    // notifyObservers();
    return true;
  }

  // BOARD STUFF

  public boolean isShaded(int row, int col) {
    // how do we know if it's shaded?
    // null pointer exception potentially if index has not been set
    // null pointer exception potentially if index out of bounds
    if (availablePuzzles.get(index).getCellStatus(row, col) == 1) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isEliminated(int row, int col) {
    // how do we know if it is eliminated?
    // null pointer exception potentially if index has not been set
    // null pointer exception potentially if index out of bounds
    if (availablePuzzles.get(index).getCellStatus(row, col) == 2) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isSpace(int row, int col) {
    // how do we know if there is a space??
    // null pointer exception potentially if index has not been set
    // null pointer exception potentially if index out of bounds
    if (availablePuzzles.get(index).getCellStatus(row, col) == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void toggleCellShaded(int row, int col) {
    // null pointer exception potentially if index has not been set
    // null pointer exception potentially if index out of bounds
    availablePuzzles.get(index).toggleShaded(row, col);
    notifyObservers();
  }

  public void toggleCellEliminated(int row, int col) {
    // null pointer exception potentially if index has not been set
    // null pointer exception potentially if index out of bounds
    availablePuzzles.get(index).toggleEliminated(row, col);
    notifyObservers();
  }

  public void clear() {
    // null pointer exception potentially if index has not been set
    availablePuzzles.get(index).clear();
    notifyObservers();
  }

  // CLUE STUFF
  public int getWidth() {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(index).getClue().getWidth();
  }

  public int getHeight() {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(index).getClue().getHeight();
  }

  public int[] getRowClues(int index) {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(getPuzzleIndex()).getClue().getRowClues(index);
  }

  public int[] getColClues(int index) {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(getPuzzleIndex()).getClue().getColClues(index);
  }

  public int getRowCluesLength() {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(index).getClue().getRowCluesLength();
  }

  public int getColCluesLength() {
    // null pointer exception potentially if index has not been set
    return availablePuzzles.get(index).getClue().getColCluesLength();
  }
}
