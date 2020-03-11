package bridge;

import framework.Console;

/**
 * This class provides a terminal console interface to the Bridge
 * Crossing problem.
 * The user attempts to solve the problem, with invalid moves rejected,
 * and the user can quit at any time.
 * If a move is valid, the new state is displayed.
 * If the solution is found, a message is given showing the number of moves
 * attempted, and processing halts.
 * @author Zachary Foede
 */
public class BridgeConsole {
    
    /**
     * This method launches the console
     * @param args ignored
     */
    public static void main(String[] args) {
        Console console = new Console(new BridgeProblem());
        console.start();
    }

}
