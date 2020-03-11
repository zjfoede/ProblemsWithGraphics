/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import framework.Move;
import framework.Problem;
import framework.State;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zach
 */
public class PuzzleProblem extends Problem
{
    public PuzzleProblem()
    {
        setCurrentState(new PuzzleState());
        int[] winCondition = {1, 2, 3, 8, -1, 4, 7, 6, 5};
        setFinalState(new PuzzleState(winCondition));
        
        //Setup for problemchooser
        int[] start1 = {2, 8, 3, 1, 6, 4, 7, -1, 5};
        int[] start2 = {3, 6, 4, 1, -1, 2, 8, 7, 5};
        int[] start3 = {3, -1, 4, 1, 6, 5, 8, 2, 7};
        int[] start4 = {2, 1, 3, 8, -1, 4, 6, 7, 5};
        int[] start5 = {4, 2, -1, 8, 3, 6, 7, 5, 1};
        int[] start6 = {1, 6, 3, 4, -1, 8, 7, 2, 5};
        int[] start7 = {5, 2, 7, 8, -1, 4, 3, 6, 1};
        int[] start8 = {5, 6, 7, 4, -1, 8, 3, 2, 1};
        
        List<State> initialStates = new ArrayList<State>();
        List<State> finalStates = new ArrayList<State>();
        List<Integer> moveCounts = new ArrayList<Integer>();
        
        initialStates.add(new PuzzleState(start1)); finalStates.add(getFinalState()); moveCounts.add(5);
        initialStates.add(new PuzzleState(start2)); finalStates.add(getFinalState()); moveCounts.add(10);
        initialStates.add(new PuzzleState(start3)); finalStates.add(getFinalState()); moveCounts.add(13);
        initialStates.add(new PuzzleState(start4)); finalStates.add(getFinalState()); moveCounts.add(18);
        initialStates.add(new PuzzleState(start5)); finalStates.add(getFinalState()); moveCounts.add(20);
        initialStates.add(new PuzzleState(start6)); finalStates.add(getFinalState()); moveCounts.add(24);
        initialStates.add(new PuzzleState(start7)); finalStates.add(getFinalState()); moveCounts.add(30);
        initialStates.add(new PuzzleState(start8)); finalStates.add(getFinalState()); moveCounts.add(30);
        
        setInitialStates(initialStates);
        setFinalStates(finalStates);
        setMoveCounts(moveCounts);
        
        setIntroduction("Welcome to the 8-Puzzle Problem!\n\n" +
                "You are given a 3x3 board in which 8 numbered tiles can " +
                "slide around.\nThere is one space that holds no tile. A legal " +
                "move consists \nof sliding a tile into the black space if the " +
                "tile is adjacent to it.\nThe goal is to move tiles around until " +
                "the board looks like the final state below.\n" +
                "            +---+---+---+\n" +
                "            | 1 | 2 | 3 |\n" +
                "            +---+---+---+\n" +
                "            | 8 |   | 4 |\n" +
                "            +---+---+---+\n" +
                "            | 7 | 6 | 5 |\n" +
                "            +---+---+---+");
        setMoves(getMoves());
    }
    
    @Override
    public List<Move> getMoves()
    {
        List<Move> validMoves = new ArrayList<Move>();
        for (int i = 1; i < 9; i++)
        {
            validMoves.add(new PuzzleMove("Slide Tile " + Integer.toString(i)));
        }
        return validMoves;
    }
    
    @Override
    public boolean success() {
        PuzzleState s = (PuzzleState) getCurrentState();
        int[] winCondition = ((PuzzleState) getFinalState()).getTiles();
        for (int i = 0; i < 9; i++)
        {
            if (s.getTiles()[i] != winCondition[i])
            {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean searchSuccess(State s)
    {
        PuzzleState state = (PuzzleState) s;
        for (int i = 0; i < 9; i++)
        {
            if (state.getTiles()[i] != ((PuzzleState) getFinalState()).getTiles()[i])
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void reset() {
        setCurrentState(new PuzzleState());
    }

    @Override
    public boolean usesChooser() 
    {
        return true;
    }
    
}
