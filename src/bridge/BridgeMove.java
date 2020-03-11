package bridge;

import java.util.ArrayList;

import framework.State;
import framework.Move;

/**
 * This class represents moves in the Bridge Crossing problem.
 * A move object stores its move name and knows how to apply itself
 * to a bridge state to create a new state representing the result
 * of the move.
 * @author Zachary Foede
 */
public class BridgeMove extends Move {

    
    /**
     * Constructs a new bridge move object.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "P1 crosses alone" </li>
     * <li> "P2 crosses alone" </li>
     * <li> "P5 crosses alone" </li>
     * <li> "P10 crosses alone" </li>
     * <li> "P1 crosses with P2" </li>
     * <li> "P1 crosses with P5" </li>
     * <li> "P1 crosses with P10" </li>
     * <li> "P2 crosses with P5" </li>
     * <li> "P2 crosses with P10" </li>
     * <li> "P5 crosses with P10" </li>
     * </ul>
     */
    public BridgeMove(String moveName) {
        super(moveName);
    }

    /**
     * Getter (accessor) for this move object's move name.
     * @return this move object's move name
     */
    /*@Override
    public String getMoveName() {
        return moveName; // You must provide
    }*/
    
    /**
     * Moves a single player across the bridge from where they were.
     * @param player A string containing the name of the player to move
     * @param state The current bridge state
     * @return The bridge state with the given player moved, null on bad parameter
     */
    
    private BridgeState singleMove(String player, BridgeState state)
    {
        BridgeState newState = null;
         if (player.equals("P1"))
            {
                Position playerPos = state.getP1Position();
                    if (playerPos.equals(Position.WEST))
                    {
                        newState = new BridgeState(Position.EAST, state.getP2Position(),
                                state.getFlashlightPosition(), state.getP5Position(),
                                state.getP10Position(), state.getTimeSoFar());
                    }
                    else
                    {
                        newState = new BridgeState(Position.WEST, state.getP2Position(),
                                state.getFlashlightPosition(), state.getP5Position(),
                                state.getP10Position(), state.getTimeSoFar());
                    }
                }
         else if (player.equals("P2"))
            {
                Position playerPos = state.getP2Position();
                    if (playerPos.equals(Position.WEST))
                    {
                        newState = new BridgeState(state.getP1Position(), Position.EAST,
                                state.getFlashlightPosition(), state.getP5Position(),
                                state.getP10Position(), state.getTimeSoFar());
                    }
                    else
                    {
                        newState = new BridgeState(state.getP1Position(), Position.WEST,
                                state.getFlashlightPosition(), state.getP5Position(),
                                state.getP10Position(), state.getTimeSoFar());
                    }
            }
         else if (player.equals("P5"))
            {
                Position playerPos = state.getP5Position();
                    if (playerPos.equals(Position.WEST))
                    {
                        newState = new BridgeState(state.getP1Position(), state.getP2Position(),
                                state.getFlashlightPosition(), Position.EAST,
                                state.getP10Position(), state.getTimeSoFar());
                    }
                    else
                    {
                        newState = new BridgeState(state.getP1Position(), state.getP2Position(),
                                state.getFlashlightPosition(), Position.WEST,
                                state.getP10Position(), state.getTimeSoFar());
                    }
            }
         else if (player.equals("P10"))
            {
                Position playerPos = state.getP10Position();
                    if (playerPos.equals(Position.WEST))
                    {
                        newState = new BridgeState(state.getP1Position(), state.getP2Position(),
                                state.getFlashlightPosition(), state.getP5Position(),
                                Position.EAST, state.getTimeSoFar());
                    }
                    else
                    {
                        newState = new BridgeState(state.getP1Position(), state.getP2Position(),
                                state.getFlashlightPosition(), state.getP5Position(),
                                Position.WEST, state.getTimeSoFar());
                    }
            }
        return newState;
    }
    
    private BridgeState addTime(int time, BridgeState state)
    {
        if (time < 0)
        {
            return state;
        }
        return new BridgeState(state.getP1Position(), state.getP2Position(),
                                state.getFlashlightPosition(), state.getP5Position(),
                                state.getP10Position(), state.getTimeSoFar() + time);
    }
    
    /**
     * Moves the flashlight across the bridge.
     * @param state The current state
     * @return The new state with the moved flashlight
     */
    
    private BridgeState moveLight(BridgeState state)
    {
        if (state.getFlashlightPosition().equals(Position.EAST))
        {
            return new BridgeState(state.getP1Position(), state.getP2Position(),
                            Position.WEST, state.getP5Position(),
                            state.getP10Position(), state.getTimeSoFar());
        }
        else
        {
            return new BridgeState(state.getP1Position(), state.getP2Position(),
                            Position.EAST, state.getP5Position(),
                            state.getP10Position(), state.getTimeSoFar());
        }
    }
    
    private boolean canGetLight(String player, BridgeState state)
    {
        if (player.equals("P1"))
        {
            return state.getP1Position().equals(state.getFlashlightPosition());
        }
        else if (player.equals("P2"))
        {
            return state.getP2Position().equals(state.getFlashlightPosition());
        }
        else if (player.equals("P5"))
        {
            return state.getP5Position().equals(state.getFlashlightPosition());
        }
        else if (player.equals("P10"))
        {
            return state.getP10Position().equals(state.getFlashlightPosition());
        }
        else return false;
    }
    
    /**
     * Attempts to perform this move on a given bridge state.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new bridge state object is returned that
     * reflects this move.
     * A move cannot be performed if the flashlight is not on the same side
     * as the crossing person(s), or if a pair of persons who are crossing are not
     * on the same side.
     * If the move cannot be performed <b>null</b> is returned.
     * @param state the bridge state on which this move is to be performed
     * @return a new bridge state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    @Override
    public BridgeState doMove(State state) {
        BridgeState newState = null;
        BridgeState bState = (BridgeState) state;
        if (getMoveName().endsWith("alone"))
        {
            String player = getMoveName().substring(0, getMoveName().indexOf(" "));
            if (canGetLight(player, bState))
            {
                newState = singleMove(player, bState);
                newState = moveLight(newState);
                newState = addTime(Integer.parseInt(player.substring(1)), newState);
            }
        }
        else
        {
            String player1 = getMoveName().substring(0, getMoveName().indexOf(" "));
            String player2 = getMoveName().substring(16, getMoveName().length());         //Sorry about the magic numbers, got lazy
            if (canGetLight(player1, bState) && canGetLight(player2, bState))
            {
                newState = singleMove(player1, bState);
                newState = singleMove(player2, newState);
                newState = moveLight(newState);
                int time1 = Integer.parseInt(player1.substring(1));
                int time2 = Integer.parseInt(player2.substring(1));
                newState = addTime(Integer.max(time1, time2), newState);
            }
        }
        return newState;
    }
}