package framework;

import graph.DequeAdder;
import graph.Vertex;
import static java.lang.Integer.max;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * This abstract class represents a problem in a problem solving domain.
 * Note that this class does not provide a constructor.
 * It provides getters and setters for the current state
 * of the problem, the list of moves for the problem, and the problem's
 * introduction string.
 * Extending classes need not have instance fields for these attributes, 
 * as they are inherited from this class.
 * Extending classes must set these values in their constructors using 
 * the setters (mutators) provided in this class.
 * Extending classes must also override the abstract <b>success</b> method.
 */
public abstract class Problem {

    /**
     * Determines whether the current state of this problem is a success.
     * Extending classes need to override this method.
     * @return whether the current state is a success
     */
    public abstract boolean success();
    
    public abstract boolean searchSuccess(State s);

    /**
     * Resets the problem to it's initial condition
     */
    public abstract void reset();
    
    /**
     * Determines if the current problem needs to use the ProblemChooser
     * @return true if the ProblemChooser should be used, false otherwise
     */
    public abstract boolean usesChooser();
    
    /**
     * Gets the current state of the problem.
     * @return the current state
     */
    public State getCurrentState() {
        return currentState;
    }
    
    /**
     * Sets the current state of the problem.
     * @param currentState the current state
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Gets an explanatory introduction string for the problem.
     * @return the introduction string
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Sets the introduction string for this problem.
     * @param introduction the introduction string
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * Gets the list of moves for this problem.
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Sets the list of moves for this problem.
     * @param moves the list of moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    
    public boolean occursOnPath(Vertex v, Vertex ancestor) {
        if ( ancestor == null )
            return false;
        else if (v.equals(ancestor))
            return true;
        else
            try
            {
                return occursOnPath(v, ancestor.getPredecessor());
            }
            catch(StackOverflowError e)
            {
                System.out.println("Stack overflow! Abandon ship!");
                System.exit(0);
            }
        return false;   //Shouldn't get here ever
    }
    
    public List<Vertex> expand(final Vertex v)
    {
        List<Vertex> children = new LinkedList<Vertex>();
        for (Move move : getMoves())
        {
            Vertex child = (Vertex)move.doMove((State)v);
            if (child != null && !occursOnPath(child, v))
            {
                child.setDistance(v.getDistance() + 1);
                child.setPredecessor(v);
                children.add(child);
            }
        }
        return children;
    }
    
    public boolean search(Vertex s)
    {
        s.setOpen(false);
        s.setDistance(0);
        s.setPredecessor(null);
        PriorityQueue<Vertex> d = new PriorityQueue<Vertex>(
                new Comparator<Vertex>()
                {

                    @Override
                    public int compare(Vertex t, Vertex t1)
                    {
                        int h1 = ((State) t).getHeuristic(getFinalState());
                        int d1 = t.getDistance();
                        int h2 = ((State) t1).getHeuristic(getFinalState());
                        int d2 = t1.getDistance();
                        return (h1 + d1) - (h2 + d2);
                    }
                    
                });
        d.add(s);
        int pqOps = 1;
        int maxSize = 1;
        int size = 1;
        while (!d.isEmpty())
        {
            Vertex currentVertex = d.remove();
            size--;
            pqOps++;
            State state = (State) currentVertex;
            if (searchSuccess(state))
            {
                System.out.println("found a solution");
                solution = getSolutionMoves(currentVertex);
                setQueueOps(pqOps);
                setQueueSize(size);
                setMaxQueueSize(maxSize);
                return true;
            }
            else
            {
                System.out.println("Expanding\n" + currentVertex + "\n-----------------------------------");
                for (Vertex v : expand(currentVertex))
                {
                    System.out.println(v);
                    d.add(v);
                    pqOps++;
                    size++;
                    maxSize = max(size, maxSize);
                }
                System.out.println("-------------------------------------------");
            }
            if (d.size() > maxSize) maxSize = d.size();
        }
        return false;
    }
    private Stack<Vertex> getSolutionMoves(Vertex d)
    {
        Stack<Vertex> toReturn = new Stack<Vertex>();
        toReturn.push(d);
        while (d.getPredecessor() != null)
        {
            toReturn.push(d.getPredecessor());
            d = d.getPredecessor();
        }
        toReturn.pop(); //Top state is the same as the current state
        return toReturn;
    }
    /**
     * The current state of this problem
     */
    private State currentState;

    /**
     * The explanatory string for this problem.
     */
    private String introduction;

    /**
     * The list of moves for this problem.
     */
    
    private List<Move> moves;
    private Stack<Vertex> solution;

    public int getQueueOps() {
        return queueOps;
    }

    public void setQueueOps(int queueOps) {
        this.queueOps = queueOps;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }
    
    private int queueOps;
    private int queueSize;
    private int maxQueueSize;
    
    
    private State finalState;

    public State getFinalState() {
        return finalState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    public Stack<Vertex> getSolution()
    {
        return solution;
    }
    
    private final DequeAdder tailAdder = new DequeAdder()
    {
        @Override
        public void add(Vertex v, Deque<Vertex> d)
        {
            d.addLast(v);
        }
    };

    public DequeAdder getTailAdder() {
        return tailAdder;
    }

    public DequeAdder getHeadAdder() {
        return headAdder;
    }
    
    private final DequeAdder headAdder = new DequeAdder()
    {
        @Override
        public void add(Vertex v, Deque<Vertex> d)
        {
            d.addFirst(v);
        }
    };

    public List<State> getInitialStates() {
        return InitialStates;
    }

    public void setInitialStates(List<State> InitialStates) {
        this.InitialStates = InitialStates;
    }

    public List<State> getFinalStates() {
        return FinalStates;
    }

    public void setFinalStates(List<State> FinalStates) {
        this.FinalStates = FinalStates;
    }

    public List<Integer> getMoveCounts() {
        return moveCounts;
    }

    public void setMoveCounts(List<Integer> moveCounts) {
        this.moveCounts = moveCounts;
    }
    
    List<State> InitialStates;
    List<State> FinalStates;
    List<Integer> moveCounts;
}
