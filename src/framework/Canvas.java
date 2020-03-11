/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import javax.swing.JComponent;

/**
 *
 * @author Zachary Foede
 */
abstract public class Canvas extends JComponent
{
    public Canvas(State state)
    {
        currState = state;
        lastState = null;
    }

    public State getCurrState()
    {
        return currState;
    }

    public State getLastState()
    {
        return lastState;
    }

    public void setCurrState(State currState)
    {
        this.currState = currState;
    }

    public void setLastState(State lastState) 
    {
        this.lastState = lastState;
    }
    
    @Override
    public String toString()
    {
        return currState.toString();
    }
    
    private State currState;
    private State lastState;
}
