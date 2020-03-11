package bridge;

import java.util.ArrayList;
import java.util.List;

import framework.State;
import framework.Move;
import framework.Problem;
import graph.Vertex;

/**
 * This class represents the Bridge Crossing problem.
 * It provides an introductory message describing the problem,
 * stores the problem's possible moves and current state, and
 * tests for whether the problem has been successfully solved.
 * @author Zachary Foede
 */
public class BridgeProblem extends Problem {
    
    /**
     * The bridge problem constructor should create the initial bridge state
     * object and store it as the problem's current state.
     * It should also create the 10 valid bridge move objects and store them
     * on an accessible list.
     */
    public BridgeProblem() {
        setCurrentState(new BridgeState(Position.WEST, Position.WEST, 
                                        Position.WEST, Position.WEST,
                                        Position.WEST, 0));
        setFinalState(new BridgeState(Position.EAST, Position.EAST,
                                      Position.EAST, Position.EAST,
                                      Position.EAST, 17));
        setIntroduction("Welcome to the Bridge Crossing Problem.\n\n" +
                "Person Pn can cross the bridge in n minutes.\n" +
                "Only one or two persons can cross at a time because it is dark,\n" +
                "and the flashlight must be taken on every crossing.\n" +
                "When two people cross, they travel at the speed of the slowest person.\n" +
                "Devise a sequence of crossings so that all four people get across\n" +
                "the bridge in no more than 17 minutes.\n");
        //search((Vertex) getCurrentState(), getTailAdder());
    }

    /**
     * Getter (accessor) for this problem's list of valid bridge move objects.
     * @return the list of bridge moves
     */
    @Override
    public final List<Move> getMoves() {

        List<Move> validMoves = new ArrayList<Move>(10);
        validMoves.add(new BridgeMove("P1 crosses alone"));
        validMoves.add(new BridgeMove("P2 crosses alone"));
        validMoves.add(new BridgeMove("P5 crosses alone"));
        validMoves.add(new BridgeMove("P10 crosses alone"));
        validMoves.add(new BridgeMove("P1 crosses with P2"));
        validMoves.add(new BridgeMove("P1 crosses with P5"));
        validMoves.add(new BridgeMove("P1 crosses with P10"));
        validMoves.add(new BridgeMove("P2 crosses with P5"));
        validMoves.add(new BridgeMove("P2 crosses with P10"));
        validMoves.add(new BridgeMove("P5 crosses with P10"));
        
        return validMoves;
    }
    
    @Override
    public void reset()
    {
        setCurrentState(new BridgeState(Position.WEST, Position.WEST, 
                                       Position.WEST, Position.WEST,
                                       Position.WEST, 0));
    }
    
    /**
     * Tests for whether the current state of this problem indicates that the
     * problem has been successfully solved.
     * @return true if the problem has been solved, false otherwise
     */
    @Override
    public boolean success() {
        BridgeState bState = (BridgeState) getCurrentState();
        return (bState.getP1Position().equals(Position.EAST) &&
                bState.getP2Position().equals(Position.EAST) &&
                bState.getP5Position().equals(Position.EAST) &&
                bState.getP10Position().equals(Position.EAST) &&
                bState.getFlashlightPosition().equals(Position.EAST) &&
                bState.getTimeSoFar() <= 17);
    }
    
    @Override
    public boolean searchSuccess(State s)
    {
        BridgeState bState = (BridgeState) s;
        return (bState.getP1Position().equals(Position.EAST) &&
                bState.getP2Position().equals(Position.EAST) &&
                bState.getP5Position().equals(Position.EAST) &&
                bState.getP10Position().equals(Position.EAST) &&
                bState.getFlashlightPosition().equals(Position.EAST) &&
                bState.getTimeSoFar() <= 17);
    }

    @Override
    public boolean usesChooser()
    {
        return false;
    }
}
