package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void test1() {
    List<Clues> clues = PuzzleLibrary.create();
    Model model1 = new ModelImpl(clues);
    model1.getColCluesLength();
  }

  @Test
  public void test2() {
    List<Clues> clues = PuzzleLibrary.create();
    Model model1 = new ModelImpl(clues);
    model1.setPuzzleIndex(0);
    assertEquals(0, model1.getPuzzleIndex());
    assertEquals(5, model1.getPuzzleCount());
    assertEquals(2, model1.getRowCluesLength());
    assertEquals(8, model1.getWidth());
    model1.toggleCellShaded(1,1);
    model1.toggleCellShaded(0,2);
    model1.toggleCellShaded(0, 4);
    model1.toggleCellShaded(1, 0);
    model1.toggleCellShaded(1, 2);
    model1.toggleCellShaded(1, 3);
    model1.toggleCellShaded(1,4);
    model1.toggleCellShaded(2,3);
    model1.toggleCellShaded(2,4);
    model1.toggleCellShaded(3,0);
    model1.toggleCellShaded(3,1);
    model1.toggleCellShaded(4,0);
    model1.toggleCellShaded(4,1);
    //assertTrue(model1.isSolved());
  }
}
