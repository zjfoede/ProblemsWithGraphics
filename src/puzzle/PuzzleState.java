/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import framework.State;
import graph.SimpleVertex;
import static java.lang.Math.abs;
import static java.lang.Math.ceil;

/**
 *
 * @author Zach
 */
public class PuzzleState extends SimpleVertex implements State
{
    public PuzzleState()
    {
        tiles = new int[9];
        tiles[0] = 2;
        tiles[1] = 8;
        tiles[2] = 3;
        tiles[3] = 1;
        tiles[4] = 6;
        tiles[5] = 4;
        tiles[6] = 7;
        tiles[7] = -1;
        tiles[8] = 5;
    }
    
    public PuzzleState(int[] tiles)
    {
        this.tiles = new int[9];
        int size = tiles.length;
        for(int i = 0; i < 9; i++)
        {
            if (i >= size)  //Prevent out-of-bounds errors
            {
                this.tiles[i] = 0;
            }
            else this.tiles[i] = tiles[i];
        }
    }
    
    public int getTile(int x, int y)
    {
        int i = x + 3*y;
        if (i > -1 && i < 9) return tiles[i];
        else return 0;
    }
    
    public String toString()
    {
        String s = "";
        for (int i = 0; i < 9; i++)
        {
           if (getTiles()[i] == -1)
           {
               s = s.concat(" ");
           }
           else
           {
               s = s.concat(Integer.toString(getTiles()[i]));
           }
           if (i == 2 || i == 5) s = s.concat("\n");
           else s = s.concat(" ");
        }
        return s;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other.getClass() != PuzzleState.class) return false;
        PuzzleState s = (PuzzleState) other;
        return (getTiles().equals(s.getTiles()));
    }
    
    public int[] getTiles()
    {
        return tiles;
    }
    
    private int[] tiles;

    @Override
    public int getHeuristic(State goal)
    {
        PuzzleState s = (PuzzleState) goal;
        return sumManhattan(s);
    }
    
    public int sumManhattan(PuzzleState goal)
    {
        int count = 0;
        for (int i = 0; i < 9; i++)
        {
            int desiredPosition = findVal(goal.getTiles(), getTiles()[i]);
            int x = abs((desiredPosition % 3) - (i % 3));
            int y = (int) abs((desiredPosition / 3) - (i / 3));
            count += x;
            count += y;
        }
        return count;
    }
    
    private int findVal(int[] array, int toFind)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == toFind)
            {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args)
    {
        int[] test1Tiles = {5, 2, 7, 8, -1, 4, 3, 6, 1};
        int[] test2Tiles = {5, 6, 7, 4, -1, 8, 3, 2, 1};
        PuzzleState test1 =
            new PuzzleState(test1Tiles);

        // Here is test1:
        //          +---+---+---+
        //          | 5 | 2 | 7 |
        //          +---+---+---+
        //          | 8 |   | 4 |
        //          +---+---+---+
        //          | 3 | 6 | 1 |
        //          +---+---+---+

        PuzzleState test2 =
            new PuzzleState(test2Tiles);

        // Here is test2:
        //          +---+---+---+
        //          | 5 | 6 | 7 |
        //          +---+---+---+
        //          | 4 |   | 8 |
        //          +---+---+---+
        //          | 3 | 2 | 1 |
        //          +---+---+---+
        int[] winCondition = {1, 2, 3, 8, -1, 4, 7, 6, 5};
        PuzzleState goal = new PuzzleState(winCondition);
        System.out.println(test1.sumManhattan(goal) == 16);
        System.out.println(test2.sumManhattan(goal) == 24);
    }
}
