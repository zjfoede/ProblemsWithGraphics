package bridge;

import framework.Canvas;
import framework.GUI;
import javax.swing.JFrame;

/**
 * A class to test your GUI class on the bridge crossing problem.
 * @author tcolburn
 */
public class BridgeGUI extends JFrame {
    
    public BridgeGUI() {
        BridgeProblem prob = new BridgeProblem();
        //Holy parentheses batman!
        add(new GUI(prob, new BridgeCanvas((BridgeState) prob.getCurrentState()),
                new BridgeCanvas((BridgeState) prob.getFinalState())));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * This method launches the gui.
     * @param args ignored
     */
    public static void main(String[] args) {
        new BridgeGUI();
    }
    
}
