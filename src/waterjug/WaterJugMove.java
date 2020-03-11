package waterjug;

import framework.Move;
import framework.State;

/**
 * This class represents moves in the Water Jug problem.
 * A move object stores its move name and knows how to apply itself
 * to a water jug state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * @author your name here
*/
public class WaterJugMove extends Move {

    /**
     * Constructs a new water jug move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "Fill Jug X" </li>
     * <li> "Fill Jug Y" </li>
     * <li> "Empty Jug X" </li>
     * <li> "Empty Jug Y" </li>
     * <li> "Transfer Jug X to Jug Y" </li>
     * <li> "Transfer Jug Y to Jug X" </li>
     * </ul>
     */
    public WaterJugMove(String moveName) {
	super(moveName);
        // You must provide the rest
    }

    /**
     * Attempts to perform this move on a given water jug state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>WaterJugState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new water jug state object is returned that
     * reflects this move.
     * A move cannot be performed if trying to fill or transfer to an already
     * full jug, or if trying to empty or transfer from an empty jug.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the water jug state on which this move is to be performed
     * @return a new water jug state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {
	WaterJugState state = (WaterJugState) otherState;
        String verb = getMoveName().substring(0, getMoveName().indexOf(" "));
        State toReturn = null;
        if (verb.equals("Fill"))
        {
            toReturn = fill(getMoveName().substring(9), state);
        }
        else if (verb.equals("Empty"))
        {
            toReturn = empty(getMoveName().substring(10), state);
        }
        else    //Transfer
        {
            toReturn = transfer(getMoveName().substring(13, 14), getMoveName().substring(22), state);
        }
        return toReturn; // You must provide
    }
    
    // Private methods and instance fields should go here
    /**
     * Fills the indicated jug.
     * @param jug The jug to fill
     * @param state The state to act on
     * @return A state with the indicated jug filled
     */
    private WaterJugState fill(String jug, WaterJugState state)
    {
        WaterJugState toReturn = null;
        if (jug.equals("X"))
        {
            if (state.getXGallons() < 3)
            {
                toReturn = new WaterJugState(3, state.getYGallons());
            }
        }
        else
        {
            if (state.getYGallons() < 4)
                toReturn = new WaterJugState(state.getXGallons(), 4);
        }
        return toReturn;
    }
    /**
     * Empties the indicated jug.
     * @param jug The jug to empty
     * @param state The state to act on
     * @return A state with the indicated jug emptied
     */
    private WaterJugState empty(String jug, WaterJugState state)
    {
        WaterJugState toReturn = null;
        if (jug.equals("X"))
        {
            if (state.getXGallons() != 0)
                toReturn = new WaterJugState(0, state.getYGallons());
        }
        else
        {
            if (state.getYGallons() != 0)
                toReturn = new WaterJugState(state.getXGallons(), 0);
        }
        return toReturn;
    }
    /**
     * Transfers the contents of one jug the other
     * @param giving The jug that is giving its contents
     * @param getting The jug receiving water
     * @param state The state to act on
     * @return A state with the transfer complete
     */
    private WaterJugState transfer(String giving, String getting, WaterJugState state)
    {
        WaterJugState toReturn = null;
        if (giving.equals("X"))
        {
            if (state.getXGallons() != 0 && state.getYGallons() != 4)
            {
                //Transfer all
                int freeSpace = 4 - state.getYGallons();
                if (state.getXGallons() <= freeSpace)
                {
                    toReturn = new WaterJugState(0, state.getYGallons() + state.getXGallons());
                }
                //Transfer partial
                else
                {
                    toReturn = new WaterJugState(state.getXGallons() - freeSpace, 4);
                }
            }
        }
        else    //Y is giving
        {
            if (state.getYGallons() != 0 && state.getXGallons() != 3)
            {
                //Transfer all
                int freeSpace = 3 - state.getXGallons();
                if (state.getYGallons() <= freeSpace)
                {
                    toReturn = new WaterJugState(state.getYGallons() + state.getXGallons(), 0);
                }
                //Transfer partial
                else
                {
                    toReturn = new WaterJugState(3, state.getYGallons() - freeSpace);
                }
            }
        }
        return toReturn;
    }
}
