/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import framework.Move;
import framework.State;

/**
 *
 * @author Zach
 */
public class PuzzleMove extends Move
{
    public PuzzleMove(String moveName)
    {
        super(moveName);
    }
    
    /**
     * determines whether the ith tile can move.
     * @param i the tile to check
     * @return true if the tile can slide, false otherwise
     */
    private boolean canSlide(int tileX, int tileY, PuzzleState s)
    {
        if (s.getTile(tileX, tileY) == -1) return false;
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                if ((x != 0) ^ (y != 0))    //one or the other must be 0, not both
                {
                    //System.out.println("Tile is adjacent");
                    if ((s.getTile(tileX + x, tileY + y) == -1) /*&& onBoard(tileX + x, tileY + y)*/)
                    {
                        //System.out.println("Adjacent tile is blank and on the board");
                        return (0 <= tileX + x && tileX + x < 3 && 0 <= tileY + y && tileY + y < 3);
                    }
                }
            }
        }   //I'm wishing I used a 2D array for the state at this point
        return false;    //But I've sunk too much time into this to change it
    }
    
    private int getEmptyTile(PuzzleState s)
    {
        int[] tiles = s.getTiles();
        for (int i = 0; i < 9; i++)
        {
            if (tiles[i] == -1) return i;
        }
        return 0; //Shouldn't get here
    }
    private int getTileContaining(PuzzleState s, int toFind)
    {
        int[] tiles = s.getTiles();
        for (int i = 0; i < 9; i++)
        {
            if (tiles[i] == toFind) return i;
        }
        return 0; //Shouldn't get here
    }
    
    private boolean onBoard(int x, int y)
    {
        return ((x >=0 && x <=2) && (y >= 0 && y <= 2));
    }
    
    @Override
    public State doMove(State state)
    {
        //System.out.println("State before doMove:\n" + state + "\n");
        PuzzleState s = (PuzzleState) state;
        PuzzleState newState = null;
        String moveString = getMoveName();
        String tileString = moveString.substring(moveString.length() - 1);
        int toSlideValue = Integer.parseInt(tileString);
        int toSlide = getTileContaining(s, toSlideValue);
        //System.out.println("State halfway through doMove:\n" + state + "\n");
        if (canSlide(toSlide % 3, toSlide / 3, s))  //Tile has adjacent empty space
        {   //Much ugliness could have been avoided here with a 2D array
            int[] newTiles = s.getTiles().clone();  //Copy old state
            //System.out.println("State @ point 1 in doMove:\n" + state + "\n");
            newTiles[getEmptyTile(s)] = toSlideValue;   //Replace empty tile with adjacent one
            //System.out.println("State @ point 2 in doMove:\n" + state + "\n");
            newTiles[toSlide] = -1;     //old tile is now empty
            //System.out.println("State @ point 3 in doMove:\n" + state + "\n");
            newState = new PuzzleState(newTiles);
        }
        //System.out.println("State after doMove:\n" + state + "\n");
        return newState;
    }
    
}
