package bridge;

import framework.State;
import graph.SimpleVertex;

/**
 * This class represents states of the Bridge Crossing problem.
 * It creates new bridge states, tests states for equality,
 * and produces string representations of them.
 * @author your name here
 */
public class BridgeState extends SimpleVertex implements State{

    /**
     * Creates a new bridge state.  
     * Besides storing the positions of the persons and flashlight, a
     * bridge state should also store the time taken so far to cross in
     * integer minutes.
     * @param p1Position position of the person who can cross in 1 minute
     * @param p2Position position of the person who can cross in 2 minutes
     * @param flashlightPosition position of the flashlight
     * @param p5Position position of the person who can cross in 5 minutes
     * @param p10Position  position of the person who can cross in 10 minutes
     * @param timeSoFar time taken so far
     */
    private Position p1Position;
    private Position p2Position;
    private Position p5Position;
    private Position p10Position;
    private Position flashlightPosition;
    private int timeSoFar;
    
    public BridgeState(Position p1Position, 
                       Position p2Position, 
                       Position flashlightPosition, 
                       Position p5Position, 
                       Position p10Position,
                       int timeSoFar) {
        this.p1Position = p1Position;
        this.p2Position = p2Position;
        this.p5Position = p5Position;
        this.p10Position = p10Position;
        this.flashlightPosition = flashlightPosition;
        this.timeSoFar = timeSoFar;
    }
    
    /**
     * Tests this bridge state with another for equality.
     * Two bridge states are equal if the positions of the persons and 
     * flashlight in one state are matched by their positions in the other.
     * Note that the time taken to cross so far is not taken into account
     * when considering equality.
     * @param other the other bridge state to be tested against this one.
     * @return true if this state is equal to the other state, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        BridgeState state = (BridgeState) other;
        return (
                p1Position.equals(state.getP1Position()) &&
                p2Position.equals(state.getP2Position()) &&
                p5Position.equals(state.getP5Position()) &&
                p10Position.equals(state.getP10Position()) &&
                flashlightPosition.equals(state.getFlashlightPosition())
                );
    }
    
    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem.
     * Note that the time so far to cross is part of the string representation.
     * @return the string representation of this state
     */
    @Override
    public String toString() {
	String bridge = "";
        if (p1Position.equals(Position.WEST))
        {
            bridge = bridge.concat(" P1 |   |\n");
        }
        else
        {
            bridge = bridge.concat("    |   | P1\n");
        }
        if (p2Position.equals(Position.WEST))
        {
            bridge = bridge.concat(" P2 |   |\n");
        }
        else
        {
            bridge = bridge.concat("    |   | P2\n");
        }
        if (flashlightPosition.equals(Position.WEST))
        {
            bridge = bridge.concat("  f |===|\n");
        }
        else
        {
            bridge = bridge.concat("    |===| f\n");
        }
        if (p5Position.equals(Position.WEST))
        {
            bridge = bridge.concat(" P5 |   |\n");
        }
        else
        {
            bridge = bridge.concat("    |   | P5\n");
        }
        if (p10Position.equals(Position.WEST))
        {
            bridge = bridge.concat("P10 |   |\n");
        }
        else
        {
            bridge = bridge.concat("    |   | P10\n");
        }
        bridge = bridge.concat("Time elapsed so far: " + 
                                String.format("%02d", timeSoFar) + " minutes.\n");
        return bridge;
    }

    public Position getFlashlightPosition() {
        return flashlightPosition;  // You must provide
    }

    public Position getP10Position() {
        return p10Position;  // You must provide
    }

    public Position getP1Position() {
        return p1Position;  // You must provide
    }

    public Position getP2Position() {
        return p2Position;  // You must provide
    }

    public Position getP5Position() {
        return p5Position;  // You must provide
    }

    public int getTimeSoFar() {
        return timeSoFar;  // You must provide
    }

    @Override
    public int getHeuristic(State goal)
    {
        return 0;
    }
    
}
