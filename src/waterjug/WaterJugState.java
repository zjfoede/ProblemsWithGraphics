package waterjug;

import framework.State;
import graph.SimpleVertex;

/**
 * This class represents states of the Water Jug problem.
 * It creates new water jug states, tests states for equality,
 * and produces string representations of them.
 * Note that this class implements the <b>State</b> interface
 * and therefore imports <b>framework.State</b>.
 */
public class WaterJugState extends SimpleVertex implements State {

    // You must provide a constructor
    public WaterJugState(int xGallons, int yGallons)
    {
        this.xGallons = xGallons;
        this.yGallons = yGallons;
    }

    /**
       Tests for equality between this state and the argument state.
       Two states are equal if the X jugs have the same amount of water
       and the Y jugs have the same amount of water.
       @param other the state to test against this state
       @return whether the states are equal
    */
    public boolean equals(Object other) {
	WaterJugState state = (WaterJugState) other;
        return (getXGallons() == state.getXGallons() &&
                getYGallons() == state.getYGallons());
    }

    /**
       Creates a primitive, non-GUI representation of this WaterJugState object.
       @return the string representation of this water jug state
     */
    public String toString() {
	String jugs = "";
        String full = "|***|";
        String empty = "|   |";
        jugs += "       ";
        if (yGallons == 4) jugs += full;
        else jugs += empty;
        jugs += "\n";
        for (int i = 3; i > 0; i--)
        {
            if (xGallons >= i) jugs += full;
            else jugs += empty;
            jugs += "  ";
            if (yGallons >= i) jugs += full;
            else jugs += empty;
            jugs += "\n";
        }
        jugs += "+---+  +---+\n";
        jugs += "  X      Y  \n";
        return jugs;
    }
    
    public int getXGallons()
    {
        return xGallons;
    }
    
    public int getYGallons()
    {
        return yGallons;
    }
    
    // Private methods and instance fields should go here
    int xGallons;
    int yGallons;

    @Override
    public int getHeuristic(State goal)
    {
        return 0;
    }
}
