package com.comp301.a09nonograms;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
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
    model1.setPuzzleIndex(1);
    assertEquals(1, model1.getPuzzleIndex());
    assertEquals(7, model1.getPuzzleCount());
    assertEquals(2, model1.getRowCluesLength());
    assertEquals(2, model1.getWidth());
    model1.toggleCellEliminated(0, 0);
    model1.toggleCellEliminated(0, 1);
    model1.toggleCellEliminated(1, 0);
    model1.toggleCellEliminated(1, 1);
    model1.toggleCellEliminated(2, 0);
    model1.toggleCellEliminated(2, 1);
    assertTrue(model1.isSolved());
  }

  @Test
  public void test3() {
    List<Clues> clues = PuzzleLibrary.create();
    Model model1 = new ModelImpl(clues);
    model1.setPuzzleIndex(2);
    assertEquals(2, model1.getPuzzleIndex());
    assertEquals(7, model1.getPuzzleCount());
    assertEquals(2, model1.getRowCluesLength());
    assertEquals(3, model1.getWidth());
    model1.toggleCellShaded(0, 1);
    // model1.toggleCellShaded(1,0);
    // model1.toggleCellShaded(1, 2);
    // model1.toggleCellShaded(2, 0);
    model1.toggleCellShaded(2, 1);
    assertFalse(model1.isSolved());
  }

  @Test
  public void test4() {
    List<Clues> clues = PuzzleLibrary.create();
    Model model1 = new ModelImpl(clues);
    model1.setPuzzleIndex(2);
    model1.getRowClues(0);
    Controller controller = new ControllerImpl(model1);
    controller.getClues();
  }
}
