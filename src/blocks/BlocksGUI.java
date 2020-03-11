/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import framework.GUI;
import javax.swing.JFrame;

/**
 *
 * @author Zach
 */
public class BlocksGUI extends JFrame
{
    public BlocksGUI()
    {
        BlockProblem prob = new BlockProblem();
        add(new GUI(prob, new BlocksCanvas((BlockState) prob.getCurrentState()),
        new BlocksCanvas((BlockState) prob.getFinalState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new BlocksGUI();
    }
}