package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues{
    private final int numRows;
    private final int numCols;
    private final int[][] rowClues;
    private final int[][] colClues;

    public CluesImpl(int[][] rowClues, int[][] colClues) {
        // Constructor code here
        this.rowClues = rowClues;
        this.colClues = colClues;
        numRows = rowClues.length;
        numCols = colClues.length;
    }

    public int getWidth(){
        return numCols;
    }

    public int getHeight(){
        return numRows;
    }

    public int[] getRowClues(int index){
        return rowClues[index];
    }

    public int[] getColClues(int index){
        return colClues[index];
    }

    /** Getter method to retrieve the length of the row clue lists */
    public int getRowCluesLength(){
        return getRowClues(0).length;
    }

    /** Getter method to retrieve the length of the column clue lists */
    public int getColCluesLength(){
       return getColClues(0).length;
    }


}
