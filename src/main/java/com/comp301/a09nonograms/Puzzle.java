package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.Board;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;

import java.util.ArrayList;

public class Puzzle {
    private Clues clue;
    private int[][] puzzle;

    public Puzzle(Clues clue){
        this.clue= clue;
        puzzle = new int[this.clue.getHeight()][this.clue.getWidth()];
    }

    public Clues getClue() {
        return clue;
    }

    public int getCellStatus(int row, int col) {
        return puzzle[row][col];
    }

    public void toggleShaded(int row, int col) {
        if(puzzle[row][col] == 0){
            puzzle[row][col] = 1;
        }
        else if(puzzle[row][col] == 1){
            puzzle[row][col] = 0;
        }
    }

    public void toggleEliminated(int row, int col) {
        if(puzzle[row][col] == 0){
            puzzle[row][col] = 2;
        }
        else if(puzzle[row][col] == 2){
            puzzle[row][col] = 0;
        }
    }

    public void clear() {
        for(int i = 0; i < clue.getHeight(); i++){
            for(int j = 0; j < clue.getWidth(); j++){
                puzzle[i][j] = 0;
            }
        }
    }

}
