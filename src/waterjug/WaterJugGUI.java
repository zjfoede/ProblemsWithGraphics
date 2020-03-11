package waterjug;

import framework.GUI;
import framework.State;
import javax.swing.JFrame;

/**
 * A class to test your GUI class on the water jug problem.
 * @author tcolburn
 */
public class WaterJugGUI extends JFrame {
    
    public WaterJugGUI() {
        WaterJugProblem prob = new WaterJugProblem();
        WaterJugCanvas pCanvas = new WaterJugCanvas((WaterJugState) prob.getCurrentState());
        WaterJugCanvas fCanvas = new WaterJugCanvas((WaterJugState) prob.getFinalState());
        add(new GUI(prob, pCanvas, fCanvas));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * This method launches the gui.
     * @param args ignored
     */
    public static void main(String[] args) {
        new WaterJugGUI();
    }
    
}
