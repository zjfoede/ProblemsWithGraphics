/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import framework.GUI;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Zach
 */
public class PuzzleGUI extends JFrame
{
    public PuzzleGUI()
    {
        PuzzleProblem prob = new PuzzleProblem();
        add(new GUI(prob, new PuzzleCanvas((PuzzleState) prob.getCurrentState()),
        new PuzzleCanvas((PuzzleState) prob.getFinalState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PuzzleGUI();
    }
}
