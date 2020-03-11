package framework.test;

import blocks.BlockProblem;
import blocks.BlockState;
import blocks.BlocksCanvas;
import bridge.BridgeCanvas;
import bridge.BridgeProblem;
import bridge.BridgeState;
import framework.Canvas;
import framework.GUI;
import framework.ProblemPane;
import javax.swing.JFrame;
import puzzle.PuzzleCanvas;
import puzzle.PuzzleProblem;
import puzzle.PuzzleState;
import waterjug.WaterJugCanvas;
import waterjug.WaterJugProblem;
import waterjug.WaterJugState;

/**
 * A class to display the bridge crossing and water jug problems in a tabbed pane
 * within an application frame.
 * @author tcolburn
 */
public class TestFrame extends JFrame {
    
    public TestFrame() {
        super("Testing Bridge and Water Jug Problems");
        ProblemPane problemPane = new ProblemPane();
        problemPane.add("Bridge", new GUI(new BridgeProblem(), new BridgeCanvas((BridgeState) new BridgeProblem().getCurrentState()),
        new BridgeCanvas((BridgeState) new BridgeProblem().getFinalState())));
        
        problemPane.add("Water Jug", new GUI(new WaterJugProblem(), new WaterJugCanvas((WaterJugState) new WaterJugProblem().getCurrentState()),
        new WaterJugCanvas((WaterJugState) new WaterJugProblem().getFinalState())));
        
        problemPane.add("8-Puzzle", new GUI(new PuzzleProblem(), new PuzzleCanvas(new PuzzleState()),
        new PuzzleCanvas((PuzzleState)new PuzzleProblem().getFinalState())));
        
        problemPane.add("Blocks World", new GUI(new BlockProblem(), new BlocksCanvas((BlockState) new BlockProblem().getCurrentState()),
        new BlocksCanvas((BlockState) new BlockProblem().getFinalState())));
        
        add(problemPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestFrame();
    }
    
}
